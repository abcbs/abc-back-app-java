 package com.ndlan.canyin.frontdesk.transport;
 
 import io.netty.channel.ChannelHandler;
 import io.netty.channel.ChannelHandlerContext;
 import io.netty.channel.ChannelInitializer;
 import io.netty.channel.ChannelPipeline;
 import io.netty.channel.socket.SocketChannel;
 import io.netty.handler.codec.DelimiterBasedFrameDecoder;
 import io.netty.handler.codec.Delimiters;
 import io.netty.handler.codec.string.StringDecoder;
 import io.netty.handler.codec.string.StringEncoder;
 import io.netty.handler.logging.LogLevel;
 import io.netty.handler.logging.LoggingHandler;
 import io.netty.handler.timeout.ReadTimeoutHandler;
 import io.netty.handler.timeout.WriteTimeoutHandler;
 import java.util.concurrent.TimeUnit;
 
 public class TransportClientInitializer extends ChannelInitializer<SocketChannel>
 {
   private static final StringDecoder DECODER = new StringDecoder();
   private static final StringEncoder ENCODER = new StringEncoder();
   private MessageListener messsageListener = null;
 
   public TransportClientInitializer(MessageListener messsageListener)
   {
     this.messsageListener = messsageListener;
   }
 
   public void initChannel(SocketChannel ch) throws Exception
   {
     ChannelPipeline pipeline = ch.pipeline();
     ReadTimeoutHandler readTimeoutHandler = new ReadTimeoutHandler(120L, TimeUnit.SECONDS)
     {
       protected void readTimedOut(ChannelHandlerContext ctx) throws Exception
       {
         super.readTimedOut(ctx);
       }
 
       public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
       {
         super.exceptionCaught(ctx, cause);
       }
     };
     pipeline.addLast(new ChannelHandler[] { readTimeoutHandler });
     pipeline.addLast("writeTimeoutHandler", new WriteTimeoutHandler(50L, TimeUnit.SECONDS));
     pipeline.addLast("idle", new TransportIdleStateHandler(60L, 60L, 100L, TimeUnit.SECONDS));
 
     pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
     pipeline.addLast("decoder", DECODER);
     pipeline.addLast("encoder", ENCODER);
 
     pipeline.addLast("handler", new TransportClientHandler(this.messsageListener));
     if (TransportClient.DEBUG)
       pipeline.addLast("log", new LoggingHandler(LogLevel.INFO));
   }
 }

