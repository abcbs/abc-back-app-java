 package com.ndlan.canyin.core.utils;
 
 import com.ndlan.canyin.core.common.Constants;
import com.ndlan.canyin.core.service.HttpBaseService;

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import org.apache.http.HttpEntity;
 import org.apache.http.HttpResponse;
 import org.apache.http.StatusLine;
 import org.apache.http.client.ClientProtocolException;
 import org.apache.http.client.HttpClient;
 import org.apache.http.client.entity.UrlEncodedFormEntity;
 import org.apache.http.client.methods.HttpGet;
 import org.apache.http.client.methods.HttpPost;
 import org.apache.http.impl.client.DefaultHttpClient;
 import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
 
 public class CommunicationUtil
 {
   public static int CONNECTION_TIMEOUT = 3000;
   public static int SO_TIMEOUT = 3000;
 
   public static boolean isConnectG2()
   {
     return isValid(Constants.getPingServer());
   }
 
   public static String get(String uri)
   {
     BufferedReader reader = null;
     StringBuffer sb = null;
     String result = "";
     HttpClient client = new DefaultHttpClient();
     client.getParams().setParameter("http.connection.timeout", Integer.valueOf(CONNECTION_TIMEOUT));
     client.getParams().setParameter("http.socket.timeout", Integer.valueOf(SO_TIMEOUT));
     HttpGet request = new HttpGet(uri);
     request.setHeader("Cookie", HttpBaseService.COOKIE_STR);
     try {
       HttpResponse response = client.execute(request);
 
       if (response.getStatusLine().getStatusCode() == 200)
       {
         reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
         sb = new StringBuffer();
         String line = "";
         while ((line = reader.readLine()) != null)
           sb.append(line);
       }
     }
     catch (ClientProtocolException localClientProtocolException)
     {
       try
       {
         if (reader != null) {
           reader.close();
           reader = null;
         }
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     catch (IOException localIOException1)
     {
       try
       {
         if (reader != null) {
           reader.close();
           reader = null;
         }
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (reader != null) {
           reader.close();
           reader = null;
         }
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     if (sb != null) {
       result = sb.toString();
     }
     return result;
   }
 
   public static String post(String uri, Map<Object, Object> parameters)
   {
     BufferedReader reader = null;
     StringBuffer sb = null;
     String result = "";
     HttpClient client = new DefaultHttpClient();
     HttpPost request = new HttpPost(uri);
 
     List params = new ArrayList();
     Set k = parameters.keySet();
     for (Iterator iterator = k.iterator(); iterator.hasNext(); ) {
       Object key = iterator.next();
       Object value = parameters.get(key);
       if (value != null)
         params.add(new BasicNameValuePair(key.toString(), value.toString()));
       else {
         params.add(new BasicNameValuePair(key.toString(), null));
       }
     }
     try
     {
       HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
       request.setEntity(entity);
       request.setHeader("Cookie", HttpBaseService.COOKIE_STR);
       HttpResponse response = client.execute(request);
 
       if (response.getStatusLine().getStatusCode() == 200) {
         System.out.println("post success");
         reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
         sb = new StringBuffer();
         String line = "";
         while ((line = reader.readLine()) != null)
           sb.append(line);
       }
     }
     catch (ClientProtocolException e) {
       e.printStackTrace();
       try
       {
         if (reader != null) {
           reader.close();
           reader = null;
         }
       } catch (IOException e1) {
         e1.printStackTrace();
       }
     }
     catch (IOException e)
     {
       e.printStackTrace();
       try
       {
         if (reader != null) {
           reader.close();
           reader = null;
         }
       } catch (IOException e2) {
         e2.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (reader != null) {
           reader.close();
           reader = null;
         }
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     if (sb != null) {
       result = sb.toString();
     }
     return result;
   }
 
   public static boolean isValid(String strLink)
   {
     try
     {
       URL url = new URL(strLink);
       HttpURLConnection connt = (HttpURLConnection)url.openConnection();
 
       connt.setConnectTimeout(3000);
       connt.setReadTimeout(3000);
       connt.setRequestMethod("HEAD");
       String strMessage = connt.getResponseMessage();
       if (strMessage.compareTo("Not Found") == 0) {
         return false;
       }
       connt.disconnect();
     } catch (Exception e) {
       return false;
     }
     URL url;
     return true;
   }
 }

