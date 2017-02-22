 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.core.vo.MemberCardRechargeVo;
 
 public class MemberCardPrint
 {
   public static MemberCardRechargeVo printChongZhi(MembershipCard pageCard, MembershipCard savedCard, String restName, String isDrawBill)
   {
     MemberCardRechargeVo cardRechargeVo = new MemberCardRechargeVo();
     cardRechargeVo.setAddMemberIntegral(pageCard.getRechargeCash());
     cardRechargeVo.setBalance(savedCard.getBalance());
     cardRechargeVo.setCardNo(savedCard.getCardNo());
     cardRechargeVo.setCashPledge(savedCard.getCashPledge());
     cardRechargeVo.setIsDrawBill(isDrawBill);
     cardRechargeVo.setMemberIntegral(savedCard.getMemberIntegral());
     cardRechargeVo.setOperator(savedCard.getUpdateEmployee().getName());
     cardRechargeVo.setPaidinCash(pageCard.getPaidinCash());
     cardRechargeVo.setRechargeCash(pageCard.getRechargeCash());
     cardRechargeVo.setRechargeTime(savedCard.getUpdateTime());
     cardRechargeVo.setRestName(restName);
     return cardRechargeVo;
   }
 }

