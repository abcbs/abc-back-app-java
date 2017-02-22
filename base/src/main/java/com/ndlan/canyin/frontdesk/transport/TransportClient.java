 package com.ndlan.canyin.frontdesk.transport;
 
 import com.ndlan.canyin.frontdesk.service.cloud.CloudBillService;
import com.ndlan.canyin.frontdesk.service.synch.TransferCarrierService;
import com.ndlan.canyin.frontdesk.util.JsonMapper;
import com.ndlan.canyin.core.common.EnvelopeSignEnum;
 import io.netty.bootstrap.Bootstrap;
 import io.netty.channel.Channel;
 import io.netty.channel.ChannelFuture;
 import io.netty.channel.ChannelHandlerContext;
 import io.netty.channel.ChannelOption;
 import io.netty.channel.EventLoopGroup;
 import io.netty.channel.nio.NioEventLoopGroup;
 import io.netty.channel.socket.nio.NioSocketChannel;
 import io.netty.handler.timeout.IdleState;
 import io.netty.handler.timeout.IdleStateEvent;
 import io.netty.handler.timeout.ReadTimeoutException;
 import io.netty.handler.timeout.WriteTimeoutException;
 import io.netty.util.concurrent.Future;
 import io.netty.util.concurrent.FutureListener;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.net.ConnectException;
 import java.net.NoRouteToHostException;
 import java.net.SocketException;
 import java.util.Date;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
 
 public class TransportClient
   implements MessageListener
 {
   private static final Logger logger = LoggerFactory.getLogger(TransportClient.class.getName());
   public static boolean DEBUG = true;
   private static String restId = "402882b746ae1bad0146ae228eb00003";
   private String host;
   private int port;
   private Channel channel;
   private EventLoopGroup group;
   private Bootstrap bootstrap;
   private boolean RUNNING = false;
   public int TIMES = 0;
   private ChannelFuture channelFuture = null;
   private ApplicationContext applicationContext;
 
   @Autowired
   private TransferCarrierService transferCarrierService;
 
   @Autowired
   private CloudBillService cloudBillService;
 
   public TransportClient(String host, int port)
   {
     this.host = host;
     this.port = port;
   }
 
   public TransportClient()
   {
   }
 
   public void run() {
     this.group = new NioEventLoopGroup();
     try {
       this.bootstrap = new Bootstrap();
 
       ((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)this.bootstrap.group(this.group)).channel(NioSocketChannel.class))
         .option(ChannelOption.TCP_NODELAY, Boolean.valueOf(true))).option(ChannelOption.AUTO_READ, Boolean.valueOf(true)))
         .handler(new TransportClientInitializer(this));
 
       this.channel = this.bootstrap.connect(this.host, this.port).sync().channel();
       this.RUNNING = true;
 
       notifyOnline();
     } catch (Exception e) {
       this.RUNNING = false;
       if (((e instanceof ConnectException)) && (e.getMessage().contains("Connection refused")))
         System.err.println("云服务端未启动，本地客户端未建立连接");
       try
       {
         stopQuitely();
       } catch (IOException e1) {
         e1.printStackTrace();
       }
       reboot();
     }
   }
 
   private void reconnect()
   {
     try
     {
       logger.info("----------------------------------> reconnect");
       this.channel = this.bootstrap.connect(this.host, this.port).sync().channel();
       notifyOnline();
     } catch (Exception e) {
       e.printStackTrace();
       onExceptionCaught(null, e.getCause());
     }
   }
 
   private void reboot()
   {
     try {
       this.RUNNING = false;
       while (!this.RUNNING) {
         stopQuitely();
         logger.info("----------------------------------> reboot");
         Thread.currentThread(); Thread.sleep(60000L);
         this.TIMES += 1;
         run();
       }
     } catch (Exception e) {
       e.printStackTrace();
       onExceptionCaught(null, e.getCause());
     }
   }
 
   private void notifyOnline() {
     Envelope envelope = new Envelope();
     envelope.setSign(101);
     envelope.setRestId(restId);
     send(envelope);
   }
 
   public void stopQuitely() throws IOException {
     try {
       if (this.channelFuture != null) {
         try {
           this.channelFuture.sync();
         } catch (InterruptedException e) {
           e.printStackTrace();
         }
       }
       this.group.shutdownGracefully();
     } catch (Exception e) {
       logger.error("------------ stopQuitely");
       e.printStackTrace();
     }
   }
 
   private void send(String content) throws IOException {
     this.channelFuture = this.channel.writeAndFlush(content + "\r\n");
     if (this.channelFuture != null)
       this.channelFuture.addListener(new FutureListener()
       {
         public void operationComplete(Future future) throws Exception {
           TransportClient.logger.debug("future isSuccess：" + future.isSuccess() + "--------------" + "future.isCancelled()：" + future.isCancelled());
           if ((future.isCancelled()) || (!future.isSuccess()))
             System.err.println("消息发送失败");
         }
       });
   }
 
   public void send(Object object)
   {
     String content = JsonMapper.nonEmptyMapper().toJson(object);
     try {
       send(content);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void start() {
     try {
       new Thread("TransportClient") {
         public void run() {
           TransportClient.this.host = com.ndlan.canyin.core.common.Constants.getSocketServer();
           TransportClient.this.port = 8890;
           try {
             TransportClient.this.run();
           } catch (Exception e) {
             e.printStackTrace();
           }
         }
       }
       .start();
     }
     catch (Exception e) {
       e.printStackTrace();
       onExceptionCaught(null, e.getCause());
     }
   }
 
   public static void main(String[] args) throws Exception {
     for (int i = 0; i < 3; i++)
     {
       args = new String[] { "localhost", "8890" };
 
       String host = args[0];
       int port = Integer.parseInt(args[1]);
       restId += i;
       new TransportClient(host, port).run();
     }
   }
 
   private void ping()
   {
     this.channel.writeAndFlush(new Envelope(100, getRestId()).toJsonString() + "\r\n");
   }
 
   public void onMessageReceived(ChannelHandlerContext ctx, String messageContent)
   {
     logger.debug(messageContent);
     onMessageReceived((Envelope)JsonMapper.nonEmptyMapper().fromJson(messageContent, Envelope.class));
   }
 
   public void onMessageReceived(Envelope envelope) {
     if (envelope != null) {
       System.out.println(envelope.getIdSize() + "@---" + envelope.getRestId());
       if (envelope.getSign() == EnvelopeSignEnum.BOOK.getCode()) {
         com.ndlan.canyin.frontdesk.common.Constants.BOOK_SIZE = envelope.getIdSize();
         com.ndlan.canyin.frontdesk.common.Constants.TIME = envelope.getTime().getTime();
         if (com.ndlan.canyin.frontdesk.common.Constants.TIME_LAST == 0L) {
           com.ndlan.canyin.frontdesk.common.Constants.TIME_LAST = com.ndlan.canyin.frontdesk.common.Constants.TIME;
           com.ndlan.canyin.frontdesk.common.Constants.BOOK_CHANGED = true;
         } else if (com.ndlan.canyin.frontdesk.common.Constants.TIME_LAST != com.ndlan.canyin.frontdesk.common.Constants.TIME) {
           com.ndlan.canyin.frontdesk.common.Constants.BOOK_CHANGED = true;
         } else {
           com.ndlan.canyin.frontdesk.common.Constants.BOOK_CHANGED = false;
         }
       } else if (envelope.getSign() == EnvelopeSignEnum.TAKEOUT.getCode()) {
         com.ndlan.canyin.frontdesk.common.Constants.TAKEOUT_SIZE = envelope.getIdSize();
         com.ndlan.canyin.frontdesk.common.Constants.TIME = envelope.getTime().getTime();
         if (com.ndlan.canyin.frontdesk.common.Constants.TIME_LAST == 0L) {
           com.ndlan.canyin.frontdesk.common.Constants.TIME_LAST = com.ndlan.canyin.frontdesk.common.Constants.TIME;
           com.ndlan.canyin.frontdesk.common.Constants.TAKEOUT_CHANGED = true;
         } else if (com.ndlan.canyin.frontdesk.common.Constants.TIME_LAST != com.ndlan.canyin.frontdesk.common.Constants.TIME) {
           com.ndlan.canyin.frontdesk.common.Constants.TAKEOUT_CHANGED = true;
         } else {
           com.ndlan.canyin.frontdesk.common.Constants.TAKEOUT_CHANGED = false;
         }
       } else if (envelope.getSign() == EnvelopeSignEnum.SYNDATA.getCode()) {
         this.cloudBillService.synCloudReturnResult(envelope.getRestId(), envelope.getContent());
       }
       envelope = null;
     }
   }
 
   public String getHost() {
     return this.host;
   }
 
   public void setHost(String host) {
     this.host = host;
   }
 
   public int getPort() {
     return this.port;
   }
 
   public void setPort(int port) {
     this.port = port;
   }
 
   public Channel getChannel() {
     return this.channel;
   }
 
   public void setChannel(Channel channel) {
     this.channel = channel;
   }
 
   public EventLoopGroup getGroup() {
     return this.group;
   }
 
   public void setGroup(EventLoopGroup group) {
     this.group = group;
   }
 
   public Bootstrap getBootstrap() {
     return this.bootstrap;
   }
 
   public void setBootstrap(Bootstrap bootstrap) {
     this.bootstrap = bootstrap;
   }
 
   public ApplicationContext getApplicationContext() {
     return this.applicationContext;
   }
 
   public void setApplicationContext(ApplicationContext applicationContext) {
     this.applicationContext = applicationContext;
   }
 
   public TransferCarrierService getTransferCarrierService() {
     return this.transferCarrierService;
   }
 
   public void setTransferCarrierService(TransferCarrierService transferCarrierService) {
     this.transferCarrierService = transferCarrierService;
   }
 
   public String getRestId() {
     return restId;
   }
 
   public void setRestId(String restId)
   {
     restId = restId;
   }
 
   public void onExceptionCaught(ChannelHandlerContext ctx, Throwable cause)
   {
     if ((cause instanceof ReadTimeoutException)) {
       logger.warn("读数据超时");
       reconnect();
     } else if (((cause instanceof IOException)) && (cause.getMessage().contains("远程主机强迫关闭了一个现有的连接。"))) {
       System.err.println("远程主机 挂了Channel 强迫关闭了一个现有的连接");
       reboot();
     } else if ((cause instanceof WriteTimeoutException)) {
       logger.warn("写数据超时");
       reconnect();
     } else if ((cause instanceof NoRouteToHostException)) {
       logger.warn("链路中断、或者是路由表损坏,请 检查路由表是否有足够的信息指示程序把TCP/UDP请求路由至目标主机");
       reconnect();
     } else if ((cause instanceof SocketException)) {
       logger.warn(cause.getMessage());
       reconnect();
     } else {
       logger.warn("未知异常");
       reboot();
     }
   }
 
   public void onConnectionSuccess(ChannelHandlerContext ctx, Object obj)
   {
   }
 
   public void onConnectionBreak(ChannelHandlerContext ctx, Object obj)
   {
     System.err.println("onConnectionBreak");
     reconnect();
   }
 
   public void onMemberAdded(ChannelHandlerContext ctx, Object obj)
   {
   }
 
   public void onMemberRemoved(ChannelHandlerContext ctx, Object obj)
   {
   }
 
   public void onRecieveFileStart(ChannelHandlerContext ctx, Object obj)
   {
   }
 
   public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
   {
     if ((evt instanceof IdleStateEvent)) {
       IdleStateEvent e = (IdleStateEvent)evt;
       if (e.state() == IdleState.READER_IDLE)
         ping();
       else if (e.state() == IdleState.WRITER_IDLE) {
         ping();
       }
       logger.warn("事件状态：" + e.state() + " 首次发生：" + e.isFirst());
     }
   }
 
   public void onRecieveFileInProgress(ChannelHandlerContext ctx, Object obj)
   {
   }
 
   public void onRecieveFileEnd(ChannelHandlerContext ctx, Object obj)
   {
   }
 
   class HeartBeatThread extends Thread
   {
     public HeartBeatThread()
     {
       super();
     }
 
     public void run()
     {
       while (true) {
         try {
           Thread.currentThread(); Thread.sleep(360000L);
         } catch (InterruptedException e) {
           e.printStackTrace();
         }
         TransportClient.this.ping();
       }
     }
   }
 
   public class SystemMonitor extends Thread
   {
     public SystemMonitor()
     {
       super();
     }
 
     public void run()
     {
     }
   }
 }

