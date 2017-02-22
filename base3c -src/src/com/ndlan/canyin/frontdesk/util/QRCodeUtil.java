 package com.ndlan.canyin.frontdesk.util;
 
 import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
 
 public class QRCodeUtil
 {
   private static final String CHARSET = "utf-8";
   private static final String FORMAT_NAME = "JPG";
   private static final int QRCODE_SIZE = 1280;
   private static final int WIDTH = 290;
   private static final int HEIGHT = 260;
 
   public static void generate(String targetPath, String fileName, String data)
     throws Exception
   {
     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
     Map hints = new HashMap();
     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
     BitMatrix bitMatrix = multiFormatWriter.encode(new String(data.getBytes("UTF-8"), "iso-8859-1"), BarcodeFormat.QR_CODE, 400, 400);
     File file1 = new File(targetPath, fileName);
     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
   }
 
   public static void generate(String targetPath, String fileName, String data, int[] width_height)
     throws Exception
   {
     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
     Map hints = new HashMap();
     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
     BitMatrix bitMatrix = multiFormatWriter.encode(new String(data.getBytes("UTF-8"), "iso-8859-1"), BarcodeFormat.QR_CODE, width_height[0], width_height[1]);
     File file1 = new File(targetPath, fileName);
     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
   }
 
   private static String initTableNo(String str) {
     String result = str;
     for (int i = str.length(); i < 3; i++) {
       result = "0" + result;
     }
     return result;
   }
 
   public static void generate2(String targetPath, String fileName, String tabNo, String data) throws Exception {
     createCenterImage("桌号：" + tabNo, new Font("黑体", 1, 110), new File(targetPath + tabNo + ".png"));
     encode(tabNo, data, targetPath + tabNo + ".png", targetPath, true);
   }
 
   public static void main(String[] args) throws Exception
   {
     generate("D:/zcode", "152.jpg", "http://192.168.2.152/canyin-frontdesk/self/c");
     for (int i = 1; i < 100; i++) {
       String tabNo = initTableNo(i + "");
       System.out.println(tabNo);
 
       generate2("d:/qrq/", tabNo + ".jpg", tabNo, "http://192.168.2.139:8668/canyin-frontdesk/self?t=" + tabNo);
     }
   }
 
   public static void createCenterImage(String str, Font font, File outFile) throws Exception {
     Rectangle2D r = font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1.0D, 1.0D), false, false));
     int unitHeight = (int)Math.floor(r.getHeight());
     int width = (int)Math.round(r.getWidth()) + 1;
     int height = unitHeight + 3;
 
     BufferedImage image = new BufferedImage(width, height, 4);
     Graphics g = image.getGraphics();
     g.setColor(Color.BLACK);
     g.fillRect(0, 0, width, height);
     g.setColor(Color.WHITE);
     g.setFont(font);
     g.drawString(str, 0, font.getSize());
     g.dispose();
     ImageIO.write(image, "png", outFile);
   }
 
   private static BufferedImage createHoldImage(String content, String imgPath, boolean needCompress)
     throws Exception
   {
     Hashtable hints = new Hashtable();
     hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
     hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
 
     BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 1280, 1280, hints);
 
     int width = bitMatrix.getWidth();
     int height = bitMatrix.getHeight();
     BufferedImage image = new BufferedImage(width, height, 1);
 
     for (int x = 0; x < width; x++) {
       for (int y = 0; y < height; y++) {
         image.setRGB(x, y, bitMatrix.get(x, y) ? -16777216 : -1);
       }
     }
 
     if ((imgPath == null) || ("".equals(imgPath))) {
       return image;
     }
 
     insertImage(image, imgPath, needCompress);
     return image;
   }
 
   private static void insertImage(BufferedImage source, String imgPath, boolean needCompress)
     throws Exception
   {
     File file = new File(imgPath);
     if (!file.exists()) {
       System.err.println("" + imgPath + "   该文件不存在！");
       return;
     }
     Image src = ImageIO.read(new File(imgPath));
     int width = src.getWidth(null);
     int height = src.getHeight(null);
     if (needCompress) {
       if (width > 290) {
         width = 290;
       }
       if (height > 260) {
         height = 260;
       }
       Image image = src.getScaledInstance(width, height, 4);
 
       BufferedImage tag = new BufferedImage(width, height, 1);
 
       Graphics g = tag.getGraphics();
       g.drawImage(image, 0, 0, null);
       g.dispose();
       src = image;
     }
 
     Graphics2D graph = source.createGraphics();
     int x = (1280 - width) / 2;
     int y = (1280 - height) / 2;
     graph.drawImage(src, x, y, width, height, null);
     Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6.0F, 6.0F);
     graph.setStroke(new BasicStroke(3.0F));
     graph.draw(shape);
     graph.dispose();
   }
 
   public static void mkdirs(String destPath) {
     File file = new File(destPath);
 
     if ((!file.exists()) && (!file.isDirectory()))
       file.mkdirs();
   }
 
   public static void encode(String tabNo, String content, String imgPath, String destPath, boolean needCompress)
     throws Exception
   {
     BufferedImage image = createHoldImage(content, imgPath, needCompress);
 
     mkdirs(destPath);
     ImageIO.write(image, "JPG", new File(destPath + "/" + tabNo + ".jpg"));
     new File(destPath + "/" + tabNo + ".png").deleteOnExit();
   }
 }

