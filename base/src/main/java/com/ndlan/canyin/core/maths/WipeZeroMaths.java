 package com.ndlan.canyin.core.maths;
 
 import com.ndlan.canyin.core.common.MolingModeEnum;
import com.ndlan.canyin.core.utils.BigDecimalUtil;

 import java.math.BigDecimal;
 import java.math.RoundingMode;
import org.apache.commons.lang3.StringUtils;
 
 public class WipeZeroMaths
 {
   public BigDecimal getMolingMoney(BigDecimal oriPayableCost, boolean isRound, String molingModel)
   {
     BigDecimal molingMoney = BigDecimal.ZERO;
 
     if (StringUtils.isNotEmpty(molingModel)) {
       if (MolingModeEnum.NOTDEAL.getCode().equals(molingModel)) {
         return BigDecimal.ZERO;
       }
 
       if (isRound) {
         int scale = 0;
         if (MolingModeEnum.JIAO.getCode().equals(molingModel)) {
           scale = 0;
         }
         else if (MolingModeEnum.YUAN.getCode().equals(molingModel)) {
           scale = -1;
         }
         else if (MolingModeEnum.TENYUAN.getCode().equals(molingModel)) {
           scale = -2;
         }
 
         return BigDecimalUtil.format(oriPayableCost.subtract(new BigDecimal(String.valueOf(oriPayableCost)).setScale(scale, RoundingMode.HALF_UP)));
       }
 
       return BigDecimalUtil.format(oriPayableCost.remainder(BigDecimal.valueOf(Double.valueOf(molingModel).doubleValue())));
     }
 
     return molingMoney;
   }
 }

