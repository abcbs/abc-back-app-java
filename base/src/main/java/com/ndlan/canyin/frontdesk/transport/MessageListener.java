package com.ndlan.canyin.frontdesk.transport;

import io.netty.channel.ChannelHandlerContext;

public abstract interface MessageListener extends ProtocolFamily
{
  public abstract void onMessageReceived(ChannelHandlerContext paramChannelHandlerContext, String paramString);

  public abstract void onExceptionCaught(ChannelHandlerContext paramChannelHandlerContext, Throwable paramThrowable);

  public abstract void onConnectionSuccess(ChannelHandlerContext paramChannelHandlerContext, Object paramObject);

  public abstract void onConnectionBreak(ChannelHandlerContext paramChannelHandlerContext, Object paramObject);

  public abstract void onMemberAdded(ChannelHandlerContext paramChannelHandlerContext, Object paramObject);

  public abstract void onMemberRemoved(ChannelHandlerContext paramChannelHandlerContext, Object paramObject);

  public abstract void userEventTriggered(ChannelHandlerContext paramChannelHandlerContext, Object paramObject);

  public abstract void onRecieveFileStart(ChannelHandlerContext paramChannelHandlerContext, Object paramObject);

  public abstract void onRecieveFileInProgress(ChannelHandlerContext paramChannelHandlerContext, Object paramObject);

  public abstract void onRecieveFileEnd(ChannelHandlerContext paramChannelHandlerContext, Object paramObject);
}

