 package com.ndlan.canyin.sharelogic.util;
 
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 
 public class MessageDataUtil
 {
   public void consumeMessageData(ArrayList<String> cloudMethodParams, MembershipCard membershipCard, BigDecimal consumeCash, BigDecimal addIntegral)
   {
     if (cloudMethodParams != null)
     {
       cloudMethodParams.add(membershipCard.getRestMemberInfo().getMbId());
 
       cloudMethodParams.add(membershipCard.getMcId());
 
       cloudMethodParams.add(String.valueOf(consumeCash));
 
       cloudMethodParams.add(String.valueOf(membershipCard.getBalance()));
 
       cloudMethodParams.add(String.valueOf(addIntegral));
 
       cloudMethodParams.add(String.valueOf(membershipCard.getMemberIntegral()));
     }
   }
 
   public void rechargeMessageData(ArrayList<String> cloudMethodParams, String mbId, String mcId, BigDecimal rechargeCash, BigDecimal balance, BigDecimal addIntegral, BigDecimal memberIntegral)
   {
     cloudMethodParams.add(mbId);
 
     cloudMethodParams.add(mcId);
 
     cloudMethodParams.add(String.valueOf(rechargeCash));
 
     cloudMethodParams.add(String.valueOf(balance));
 
     cloudMethodParams.add(String.valueOf(addIntegral));
 
     cloudMethodParams.add(String.valueOf(memberIntegral));
   }
 
   public void balanceMessageData(ArrayList<String> cloudMethodParams, MembershipCard membershipCard)
   {
     cloudMethodParams.add(membershipCard.getRestMemberInfo().getMbId());
 
     cloudMethodParams.add(membershipCard.getMcId());
 
     cloudMethodParams.add(String.valueOf(membershipCard.getBalance()));
   }
 
   public void lossRegisterMessageData(ArrayList<String> cloudMethodParams, MembershipCard membershipCard)
   {
	 if(membershipCard.getRestMemberInfo()!=null){
		 cloudMethodParams.add(membershipCard.getRestMemberInfo().getMbId());
	 }
	 cloudMethodParams.add(membershipCard.getMcId());
   }
 
   public void newMembersMessageData(ArrayList<String> cloudMethodParams, MembershipCard membershipCard)
   {
     cloudMethodParams.add(membershipCard.getRestMemberInfo().getMbId());
     cloudMethodParams.add(membershipCard.getMcId());
   }
 }

