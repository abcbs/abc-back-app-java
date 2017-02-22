 package com.ndlan.canyin.frontdesk.message.ware;
 
 import java.nio.charset.Charset;
 import java.nio.charset.CharsetDecoder;
 import java.nio.charset.CharsetEncoder;
 import java.util.Observable;
 
 public abstract class GeneralObserver extends Observable
 {
   public static final int SEV_ON = 0;
   public static final int SEV_OFF = 1;
   public static final int CLT_CONNECT = 2;
   public static final int CLT_DISCONNECT = 3;
   public static final int MSG_SEND = 4;
   public static final int MSG_RECEIVE = 5;
   public static final int ERROR = 6;
   protected static final int BUFFERSIZE = 10240;
   protected static final String CHARSET = "UTF-8";
   protected static CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
   protected static CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
   protected int status;
 
   public int getStatus()
   {
     return this.status;
   }
 
   protected void notifyStateChanged(int status, Object arg)
   {
     this.status = status;
     notifyStateChanged(arg);
   }
 
   protected void notifyStateChanged(Object arg)
   {
     setChanged();
     notifyObservers(arg);
   }
 }

