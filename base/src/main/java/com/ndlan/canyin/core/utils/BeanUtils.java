 package com.ndlan.canyin.core.utils;
 
 import java.io.ByteArrayInputStream;
 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.io.UnsupportedEncodingException;
 
 public class BeanUtils
 {
   public static Object deepCopy(Object o)
     throws IOException, ClassNotFoundException
   {
     try
     {
       ByteArrayOutputStream bos = new ByteArrayOutputStream();
       ObjectOutputStream oos = new ObjectOutputStream(bos);
       oos.writeObject(o);
       ObjectInputStream ois = new ObjectInputStream(
         new ByteArrayInputStream(bos.toByteArray()));
       return ois.readObject();
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return o;
   }
 
   public static Object test(Object o) throws IOException, ClassNotFoundException {
     ByteArrayOutputStream bos = new ByteArrayOutputStream();
     ObjectOutputStream oos = new ObjectOutputStream(bos);
     oos.writeObject(o);
     String tmp = new String(bos.toByteArray(), "ISO-8859-1");
     ObjectInputStream ois = new ObjectInputStream(
       new ByteArrayInputStream(tmp.getBytes("ISO-8859-1")));
     return ois.readObject();
   }
 
   public static String objectToStr(Object o) {
     try {
       ByteArrayOutputStream bos = new ByteArrayOutputStream();
       ObjectOutputStream oos = new ObjectOutputStream(bos);
       oos.writeObject(o);
       return new String(bos.toByteArray(), "ISO-8859-1");
     }
     catch (UnsupportedEncodingException e) {
       e.printStackTrace();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static Object stringToObj(String str)
   {
     try {
       ObjectInputStream ois = new ObjectInputStream(
         new ByteArrayInputStream(str.getBytes("ISO-8859-1")));
       return ois.readObject();
     }
     catch (UnsupportedEncodingException e) {
       e.printStackTrace();
     }
     catch (ClassNotFoundException e) {
       e.printStackTrace();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     return str;
   }
 
   public static void main(String[] args)
   {
   }
 }

