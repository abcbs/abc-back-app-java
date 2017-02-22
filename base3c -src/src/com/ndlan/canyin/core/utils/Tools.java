package com.ndlan.canyin.core.utils;

import java.io.UnsupportedEncodingException;

public  class Tools {  
    public static String enCodeStr(String str) {  
        try {  
          return new String(str.getBytes("iso-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    public static String[] enCodeStrback(String str[]) {  
        try {  
        	for(int i=0;i<str.length;i++){
        		str[i]= new String(str[i].getBytes("iso-8859-1"), "UTF-8");
        	}
        	return str;
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}  
