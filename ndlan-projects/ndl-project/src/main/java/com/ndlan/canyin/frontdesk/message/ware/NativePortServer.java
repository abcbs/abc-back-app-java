 package com.ndlan.canyin.frontdesk.message.ware;
 
 import java.io.IOException;
 import java.io.PrintStream;
 import java.net.InetSocketAddress;
 import java.net.ServerSocket;
 import java.net.Socket;
 import java.net.SocketAddress;
 import java.nio.ByteBuffer;
 import java.nio.CharBuffer;
 import java.nio.channels.SelectableChannel;
 import java.nio.channels.SelectionKey;
 import java.nio.channels.Selector;
 import java.nio.channels.ServerSocketChannel;
 import java.nio.channels.SocketChannel;
 import java.nio.charset.CharsetDecoder;
 import java.nio.charset.CharsetEncoder;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.Set;
 
 public class NativePortServer extends GeneralObserver
   implements Runnable
 {
   private boolean isPrepared = false;
   private ServerSocketChannel ssc;
   private Selector selector;
   private ArrayList<SelectionKey> serverKeyList;
   private String receiveMessage;
 
   public NativePortServer(int port)
   {
     try
     {
       this.selector = Selector.open();
       this.ssc = ServerSocketChannel.open();
       this.ssc.configureBlocking(false);
       this.ssc.socket().bind(new InetSocketAddress(port));
       this.ssc.register(this.selector, 16);
       this.serverKeyList = new ArrayList();
       this.isPrepared = true;
     } catch (IOException e) {
       notifyStateChanged(6, e);
       e.printStackTrace();
     }
   }
 
   public void start() {
     if (this.isPrepared)
       new Thread(this).start();
   }
 
   public void send(String msg, InetSocketAddress[] toIps)
   {
     notifyStateChanged(4, msg);
     if ((null == this.serverKeyList) || (this.serverKeyList.size() <= 0)) {
       return;
     }
     if ((null != toIps) && (toIps.length >= 1))
     {
       for (SelectionKey serverKey : this.serverKeyList) {
         SocketChannel sc = (SocketChannel)serverKey.channel();
         SocketAddress ip = sc.socket().getRemoteSocketAddress();
         for (InetSocketAddress toIp : toIps) {
           if (toIp.equals(ip)) {
             serverKey.attach(msg);
             serverKey.interestOps(5);
 
             serverKey.selector().wakeup();
             break;
           }
         }
       }
     }
     else
       for (SelectionKey serverKey : this.serverKeyList) {
         serverKey.attach(msg);
         serverKey.interestOps(5);
 
         serverKey.selector().wakeup();
       }
   }
 
   public void run()
   {
     notifyStateChanged(0, null);
     try {
       while (this.isPrepared) {
         int keysCount = this.selector.select();
         if (keysCount < 1) {
           continue;
         }
         Set set = this.selector.selectedKeys();
         Iterator it = set.iterator();
         while (it.hasNext()) {
           SelectionKey key = (SelectionKey)it.next();
           if (key.isAcceptable()) {
             doAccept(key);
           }
           if ((key.isValid()) && (key.isReadable())) {
             doRead(key);
           }
           if ((key.isValid()) && (key.isWritable())) {
             doWrite(key);
           }
         }
         set.clear();
       }
     } catch (IOException e) {
       e.printStackTrace();
     }
     finally {
       notifyStateChanged(1, null);
     }
   }
 
   public void close() {
     this.isPrepared = false;
     try {
       if (null != this.serverKeyList) {
         for (SelectionKey key : this.serverKeyList) {
           key.channel().close();
         }
       }
       if (null != this.selector) {
         this.selector.close();
       }
       if (null != this.ssc)
         this.ssc.close();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   private void doAccept(SelectionKey key) {
     ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
     try {
       SocketChannel sc = ssc.accept();
       sc.configureBlocking(false);
       SelectionKey newKey = sc.register(this.selector, 1);
 
       this.serverKeyList.add(newKey);
       notifyStateChanged(2, sc.socket().getRemoteSocketAddress());
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   private void doRead(SelectionKey key) {
     SocketChannel sc = (SocketChannel)key.channel();
     ByteBuffer bb = ByteBuffer.allocate(10240);
     StringBuffer sb = new StringBuffer();
     try {
       int count = 0;
       while ((count = sc.read(bb)) > 0) {
         bb.flip();
         sb.append(decoder.decode(bb));
       }
       if (count == -1) {
         disconnect(key, sc);
       } else {
         this.receiveMessage = sb.toString().trim();
         notifyStateChanged(5, sc.socket().getRemoteSocketAddress());
       }
     }
     catch (IOException e)
     {
       disconnect(key, sc);
       System.out.println("远程主机" + sc.socket().getRemoteSocketAddress() + "断开");
     }
   }
 
   private void doWrite(SelectionKey key)
   {
     SocketChannel sc = (SocketChannel)key.channel();
     String msg = (String)key.attachment();
     if (null == msg) {
       key.interestOps(1);
       return;
     }
     try {
       sc.write(encoder.encode(CharBuffer.wrap(msg)));
     } catch (IOException e) {
       disconnect(key, sc);
       e.printStackTrace();
     }
     key.interestOps(1);
   }
 
   private void disconnect(SelectionKey key, SocketChannel sc)
   {
     this.serverKeyList.remove(key);
     notifyStateChanged(3, sc.socket().getRemoteSocketAddress());
     try {
       key.cancel();
       sc.close();
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public String getReceiveMessage() {
     return this.receiveMessage;
   }
 }

