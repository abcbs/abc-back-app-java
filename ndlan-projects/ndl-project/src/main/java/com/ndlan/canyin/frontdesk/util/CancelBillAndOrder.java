 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.TableOrder;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.common.OrderStatusEnum;
 import com.ndlan.canyin.core.common.TakeoutStatusEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.math.BigDecimal;
 import java.util.Date;
 import java.util.LinkedHashMap;
 
 public class CancelBillAndOrder
 {
   public void updateUserOrder(BigDecimal membercardCost, String onlineBillId, TableOrder tableOrder, String cancleReason, LinkedHashMap<String, Object> map)
   {
     String nowTime = DateUtil.toString(new Date());
     if (membercardCost != null)
     {
       map.put(onlineBillId + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_order set order_status = '" + OrderStatusEnum.CANCEL.getCode() + "',member_card_refund_amount='" + tableOrder.getPrepay() + "',member_card_return_status='" + TrueFalseEnum.TRUE.getCode() + "',member_card_return_time='" + nowTime + "',cancel_reason_desc='" + cancleReason + "',cancel_time='" + nowTime + "' where uo_id = '" + onlineBillId + "' ");
     }
     else
     {
       map.put(onlineBillId + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_order set order_status = '" + OrderStatusEnum.CANCEL.getCode() + "',cancel_reason_desc='" + cancleReason + "',cancel_time='" + nowTime + "' where uo_id = '" + onlineBillId + "' ");
     }
 
     map.put(tableOrder.getOnlineUobId() + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_order_bill set order_bill_status = '" + OrderStatusEnum.CANCEL.getCode() + "' where uo_id = '" + onlineBillId + "' and uob_id = '" + tableOrder.getOnlineUobId() + "' ");
   }
 
   public void updateUserTakeout(BigDecimal membercardCost, String onlineBillId, DinerBill dinerBill, String cancleReason, LinkedHashMap<String, Object> map)
   {
     String nowTime = DateUtil.toString(new Date());
     if (membercardCost != null)
     {
       map.put(onlineBillId + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_takeout set order_status = '" + TakeoutStatusEnum.CANCEL.getCode() + "',member_card_refund_amount='" + membercardCost + "',member_card_return_status='" + TrueFalseEnum.TRUE.getCode() + "',member_card_return_time='" + nowTime + "',cancel_reason_desc='" + cancleReason + "',cancel_time='" + nowTime + "' where ut_id = '" + onlineBillId + "' ");
     }
     else
     {
       map.put(onlineBillId + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_takeout set order_status = '" + TakeoutStatusEnum.CANCEL.getCode() + "',cancel_reason_desc='" + cancleReason + "',cancel_time='" + nowTime + "' where ut_id = '" + onlineBillId + "' ");
     }
   }
 }

