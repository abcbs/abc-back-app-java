 package com.ndlan.canyin.frontdesk.transport;
 
 import io.netty.channel.ChannelHandler.Sharable;
 import io.netty.channel.ChannelHandlerContext;
 import io.netty.channel.SimpleChannelInboundHandler;
 import java.io.PrintStream;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
// @ChannelHandler.Sharable
 public class TransportClientHandler extends SimpleChannelInboundHandler<String>
 {
   private static final Logger logger = LoggerFactory.getLogger(TransportClientHandler.class.getName());
   private MessageListener messsageListener = null;
 
   public TransportClientHandler(MessageListener messsageListener)
   {
     this.messsageListener = messsageListener;
   }
 
   protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception
   {
     this.messsageListener.onMessageReceived(ctx, msg);
   }
 
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
   {
     logger.info("Unexpected exception from downstream.", cause);
 
     this.messsageListener.onExceptionCaught(ctx, cause);
   }
 
   public boolean acceptInboundMessage(Object msg)
     throws Exception
   {
     return super.acceptInboundMessage(msg);
   }
 
   public void channelRead(ChannelHandlerContext ctx, Object msg)
     throws Exception
   {
     super.channelRead(ctx, msg);
   }
 
   public void channelRegistered(ChannelHandlerContext ctx) throws Exception
   {
     super.channelRegistered(ctx);
     System.err.println("channelRegistered");
   }
 
   public void channelUnregistered(ChannelHandlerContext ctx) throws Exception
   {
     super.channelUnregistered(ctx);
     System.err.println(this + "@@channelUnregistered");
   }
 
   public void channelActive(ChannelHandlerContext ctx)
     throws Exception
   {
     super.channelActive(ctx);
   }
 
   public void channelInactive(ChannelHandlerContext ctx) throws Exception
   {
     super.channelInactive(ctx);
   }
 
   public void channelReadComplete(ChannelHandlerContext ctx)
     throws Exception
   {
     super.channelReadComplete(ctx);
   }
 
   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
   {
     super.userEventTriggered(ctx, evt);
     this.messsageListener.userEventTriggered(ctx, evt);
   }
 
   public void channelWritabilityChanged(ChannelHandlerContext ctx)
     throws Exception
   {
     super.channelWritabilityChanged(ctx);
   }
 
   public boolean isSharable()
   {
     return super.isSharable();
   }
 
   public void handlerAdded(ChannelHandlerContext ctx)
     throws Exception
   {
     super.handlerAdded(ctx);
   }
 
   public void handlerRemoved(ChannelHandlerContext ctx)
     throws Exception
   {
     super.handlerRemoved(ctx);
   }
 }

