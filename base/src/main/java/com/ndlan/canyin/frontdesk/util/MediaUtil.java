 package com.ndlan.canyin.frontdesk.util;
 
 import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.util.ResourceUtils;
 
 public class MediaUtil
 {
   public static void main(String[] args)
   {
     playSound();
   }
 
   public static void playSound() {
     Thread thread = new Thread(new Runnable()
     {
       public void run() {
         for (int i = 0; i < 2; i++)
           MediaUtil.playSoundWraper();
       }
     });
     thread.start();
   }
 
   public static void playSoundWraper()
   {
     try
     {
       File file = ResourceUtils.getFile("classpath:media/sent.mp3");
 
       AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
       AudioFormat audioFormat = audioInputStream.getFormat();
 
       if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
         audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audioFormat.getSampleRate(), 16, audioFormat.getChannels(), audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
 
         audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
       }
 
       DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat, -1);
 
       SourceDataLine sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
       sourceDataLine.open(audioFormat);
       sourceDataLine.start();
 
       byte[] tempBuffer = new byte[320];
       try
       {
         int cnt;
         while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1)
         {
           if (cnt > 0) {
             sourceDataLine.write(tempBuffer, 0, cnt);
           }
         }
 
         sourceDataLine.drain();
         sourceDataLine.close();
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     catch (FileNotFoundException e) {
       e.printStackTrace();
     }
     catch (UnsupportedAudioFileException e) {
       e.printStackTrace();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     catch (LineUnavailableException e) {
       e.printStackTrace();
     }
   }
 }

