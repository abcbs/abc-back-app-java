 package com.ndlan.canyin.frontdesk.message.ware;
 
 import com.ndlan.canyin.frontdesk.dto3c.MessageVO;
import com.ndlan.canyin.frontdesk.util.MessageCarrierUtil;

 import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
 
 public class MessageCenterNativeServer
   implements Observer, Runnable
 {
   private static final String BLANK = " ";
   private HashMap<InetSocketAddress, String> onLineMap;
   private NativePortServer server;
 
   public void start()
   {
     this.server = new NativePortServer(8899);
     this.server.addObserver(this);
     this.server.start();
     new Thread(this).start();
   }
 
   public void update(Observable o, Object arg)
   {
     NativePortServer server = (NativePortServer)o;
     switch (server.getStatus()) {
     case 0:
       System.out.println("消息中心开启");
       this.onLineMap = new HashMap();
       break;
     case 1:
       System.out.println("消息中心关闭");
       this.onLineMap = null;
       break;
     case 2:
       break;
     case 3:
       quit((InetSocketAddress)arg);
       break;
     case 4:
       break;
     case 5:
       Message msg = Message.getInstance();
       msg.create(server.getReceiveMessage());
       msg.setFromIp((InetSocketAddress)arg);
       handleMsg(msg);
       break;
     case 6:
       System.out.println("error : " + ((Exception)arg).getMessage());
     }
   }
 
   private void handleMsg(Message msg)
   {
     int type = msg.getType();
     InetSocketAddress formIp = msg.getFromIp();
     switch (type)
     {
     case 1:
       this.onLineMap.put(formIp, msg.getMsg());
 
       msg.setType(2);
 
       this.server.send(msg.toString(), new InetSocketAddress[0]);
 
       msg.setType(3);
       msg.setOnLineMap(this.onLineMap);
       this.server.send(msg.toString(), new InetSocketAddress[] { formIp });
 
       break;
     case 4:
       System.out.println((String)this.onLineMap.get(msg.getFromIp()) + " " + "said" + " " + msg.getMsg());
 
       msg.setType(6);
       this.server.send(msg.toString(), new InetSocketAddress[0]);
       break;
     case 7:
       quit(formIp);
       break;
     case 2:
     case 3:
     case 5:
     case 6:
     case 8:
     case 9:
     }
   }
   private void quit(InetSocketAddress address) { if (this.onLineMap.get(address) != null)
     {
       this.onLineMap.remove(address);
       Message msg = Message.getInstance();
       msg.setType(8);
       msg.setFromIp(address);
       this.server.send(msg.toString(), new InetSocketAddress[0]);
     }
   }
 
   public void run()
   {
   }
 
   public boolean notifyAllWaiter(String message)
   {
     Message msg = Message.getInstance();
     msg.setType(4);
     msg.setMsg(message);
     this.server.send(msg.toString(), new InetSocketAddress[0]);
     return true;
   }
 
   public static void main(String[] args) {
     new MessageCenterNativeServer().start();
   }
 
   public HashMap<InetSocketAddress, String> getOnLineMap()
   {
     return this.onLineMap;
   }
 
   public void setOnLineMap(HashMap<InetSocketAddress, String> onLineMap) {
     this.onLineMap = onLineMap;
   }
 
   public NativePortServer getServer() {
     return this.server;
   }
 
   public void setServer(NativePortServer server) {
     this.server = server;
   }
 
   class ServerThread extends Thread
   {
     ServerThread()
     {
     }
 
     public void run()
     {
       super.run();
       while (true) {
         try {
           Thread.sleep(2000L);
         } catch (InterruptedException e) {
           e.printStackTrace();
         }
         Message msg = Message.getInstance();
         msg.setType(4);
 
         String res = "";
         MessageVO messageVO = new MessageVO();
         messageVO.setType(1);
 
         messageVO.setCallType("1");
         messageVO.setCode(1);
         messageVO.setContent("客人localhost:999 呼叫下单");
         messageVO.setName("andy");
         messageVO.setFrom("01");
         res = MessageCarrierUtil.beanToJson(messageVO);
         msg.setMsg(res);
         MessageCenterNativeServer.this.server.send(msg.toString(), new InetSocketAddress[0]);
       }
     }
   }
 }

