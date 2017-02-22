 package com.ndlan.canyin.core.service;
 
 import com.ndlan.canyin.core.utils.CommunicationUtil;
import com.ndlan.canyin.core.utils.NetUtil;
import com.ndlan.canyin.core.service.Constants;
import com.ndlan.canyin.core.service.HttpBaseService;
 
 public class RegisterService extends HttpBaseService
 {
   public static boolean everyDayResiter(String restId, String G2Version, String restDesc)
   {
     String terminalOsType = System.getProperty("os.name").replaceAll("\\s*", "");
     String terminalMac = NetUtil.getLocalMac();
     CommunicationUtil.get(Constants.BASE_SERVIER_URL + "register/startUp" + "?restId=" + restId + 
       "&terminalOsType=" + terminalOsType + "&terminalType=1&G2Version=" + G2Version + "&visitType=1&terminalMac=" + terminalMac + "&restDesc=" + restDesc);
     return true;
   }
 }

