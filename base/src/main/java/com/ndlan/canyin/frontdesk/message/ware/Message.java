 package com.ndlan.canyin.frontdesk.message.ware;
 
 import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
 
 public class Message
 {
   public static final String SEPARATOR = "`";
   public static final int MSG_1 = 1;
   public static final int MSG_2 = 2;
   public static final int MSG_3 = 3;
   public static final int MSG_4 = 4;
   public static final int MSG_5 = 5;
   public static final int MSG_6 = 6;
   public static final int MSG_7 = 7;
   public static final int MSG_8 = 8;
   public static final int MSG_9 = 9;
   public static final int MSG_10 = 10;
   public static final int MSG_11 = 11;
   private int type;
   private String msg;
   private InetSocketAddress fromIp;
   private ArrayList<InetSocketAddress> toIpList;
   private HashMap<InetSocketAddress, String> onLineMap;
 
   public static Message getInstance()
   {
     return MessageHolder.instance;
   }
 
   public Message()
   {
   }
 
   public Message(int type)
   {
     this.type = type;
   }
 
   public Message(String message)
   {
     create(message);
   }
 
   public void create(String message)
   {
     int type = parseType(message);
     if ((type <= 0) || (type >= 12)) {
       throw new IllegalArgumentException("非法消息?");
     }
 
     this.type = type;
     initValues(message);
   }
 
   private void initValues(String message)
   {
     switch (this.type) {
     case 1:
     case 4:
     case 9:
       this.msg = message.split("`")[1];
       break;
     case 2:
     case 6:
       String[] msg2 = message.split("`");
       this.msg = msg2[1];
       this.fromIp = toIpAddress(msg2[2]);
       break;
     case 3:
       String[] msg3 = message.split("`");
       this.onLineMap = new HashMap();
       for (int i = 1; i < msg3.length; i += 2) {
         this.onLineMap.put(toIpAddress(msg3[i]), msg3[(i + 1)]);
       }
       break;
     case 5:
       String[] msg5 = message.split("`");
       this.msg = msg5[1];
       this.toIpList = new ArrayList();
       for (int i = 2; i < msg5.length; i++) {
         this.toIpList.add(toIpAddress(msg5[i]));
       }
       break;
     case 7:
       break;
     case 8:
       this.fromIp = toIpAddress(message.split("`")[1]);
     }
   }
 
   private int parseType(String message)
   {
     int type = -1;
     try {
       type = Integer.parseInt(message.substring(0, 1));
     } catch (NumberFormatException e) {
     }
     return type;
   }
 
   public String toString()
   {
     switch (this.type) {
     case 1:
     case 4:
     case 9:
       return this.type + "`" + this.msg;
     case 2:
     case 6:
       return this.type + "`" + this.msg + "`" + toIpString(this.fromIp);
     case 3:
       StringBuffer msg3 = new StringBuffer("3");
       Iterator it = this.onLineMap.entrySet().iterator();
 
       while (it.hasNext()) {
         Map.Entry entry = (Map.Entry)it.next();
         msg3.append("`");
         msg3.append(toIpString((InetSocketAddress)entry.getKey()));
         msg3.append("`");
         msg3.append((String)entry.getValue());
       }
       return msg3.toString();
     case 5:
       StringBuffer msg5 = new StringBuffer("5`" + this.msg);
       if (null != this.toIpList) {
         for (InetSocketAddress address : this.toIpList) {
           msg5.append("`");
           msg5.append(toIpString(address));
         }
       }
       return msg5.toString();
     case 7:
       return "7";
     case 8:
       return "8`" + toIpString(this.fromIp);
     }
     return super.toString();
   }
 
   public String toIpString(InetSocketAddress address) {
     return address.getAddress().getHostAddress() + ":" + address.getPort();
   }
 
   public InetSocketAddress toIpAddress(String ipStr) {
     String[] ipAddress = ipStr.split(":");
     return new InetSocketAddress(ipAddress[0], Integer.parseInt(ipAddress[1]));
   }
 
   public int getType()
   {
     return this.type;
   }
 
   public void setType(int type) {
     this.type = type;
   }
 
   public String getMsg() {
     return this.msg;
   }
 
   public void setMsg(String msg) {
     this.msg = msg;
   }
 
   public InetSocketAddress getFromIp() {
     return this.fromIp;
   }
 
   public void setFromIp(InetSocketAddress fromIp) {
     this.fromIp = fromIp;
   }
 
   public ArrayList<InetSocketAddress> getToIpList() {
     return this.toIpList;
   }
 
   public void setToIpList(ArrayList<InetSocketAddress> toIpList) {
     this.toIpList = toIpList;
   }
 
   public HashMap<InetSocketAddress, String> getOnLineMap() {
     return this.onLineMap;
   }
 
   public void setOnLineMap(HashMap<InetSocketAddress, String> onLineMap) {
     this.onLineMap = onLineMap;
   }
 
   static class MessageHolder
   {
     static Message instance = new Message();
   }
 }

