 package com.ndlan.canyin.frontdesk.transport;
 
 import io.netty.channel.Channel;
 import io.netty.channel.ChannelHandlerContext;
 import io.netty.channel.ChannelPromise;
 import io.netty.handler.timeout.IdleStateEvent;
 import io.netty.handler.timeout.IdleStateHandler;
 import io.netty.util.concurrent.EventExecutor;
 import java.net.SocketAddress;
 import java.util.concurrent.TimeUnit;
 
 public class TransportIdleStateHandler extends IdleStateHandler
 {
   public TransportIdleStateHandler(long readerIdleTime, long writerIdleTime, long allIdleTime, TimeUnit unit)
   {
     super(readerIdleTime, writerIdleTime, allIdleTime, unit);
   }
 
   private void initialize(ChannelHandlerContext ctx)
   {
     EventExecutor loop = ctx.executor();
   }
 
   public long getReaderIdleTimeInMillis()
   {
     return super.getReaderIdleTimeInMillis();
   }
 
   public long getWriterIdleTimeInMillis()
   {
     return super.getWriterIdleTimeInMillis();
   }
 
   protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt)
     throws Exception
   {
     super.channelIdle(ctx, evt);
   }
 
   public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise future)
     throws Exception
   {
     super.bind(ctx, localAddress, future);
   }
 
   public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise future)
     throws Exception
   {
     super.connect(ctx, remoteAddress, localAddress, future);
   }
 
   public void disconnect(ChannelHandlerContext ctx, ChannelPromise future)
     throws Exception
   {
     super.disconnect(ctx, future);
   }
 
   public void close(ChannelHandlerContext ctx, ChannelPromise future)
     throws Exception
   {
     super.close(ctx, future);
   }
 
   public void deregister(ChannelHandlerContext ctx, ChannelPromise future)
     throws Exception
   {
     super.deregister(ctx, future);
   }
 
   public void read(ChannelHandlerContext ctx)
     throws Exception
   {
     super.read(ctx);
   }
 
   public void flush(ChannelHandlerContext ctx)
     throws Exception
   {
     super.flush(ctx);
   }
 
   public void handlerAdded(ChannelHandlerContext ctx) throws Exception
   {
     super.handlerAdded(ctx);
   }
 
   final class ReaderIdleTimeoutTask implements Runnable
   {
     private final ChannelHandlerContext ctx;
 
     ReaderIdleTimeoutTask(ChannelHandlerContext ctx) {
       this.ctx = ctx;
     }
 
     public void run()
     {
       if (!this.ctx.channel().isOpen())
         return;
     }
   }
 }

