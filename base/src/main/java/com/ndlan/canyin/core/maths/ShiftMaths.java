 package com.ndlan.canyin.core.maths;
 
 import java.math.BigDecimal;
 
 public class ShiftMaths
 {
   public static BigDecimal getRealCost(BigDecimal yingyeSum, BigDecimal oddChangeSum)
   {
     yingyeSum = yingyeSum == null ? BigDecimal.ZERO : yingyeSum;
     oddChangeSum = oddChangeSum == null ? BigDecimal.ZERO : oddChangeSum;
     return yingyeSum.subtract(oddChangeSum);
   }
 
   public static BigDecimal getPayableCost(BigDecimal realCost, BigDecimal molingModeCost, BigDecimal discountMoney, BigDecimal forceMoney)
   {
     realCost = realCost == null ? BigDecimal.ZERO : realCost;
     molingModeCost = molingModeCost == null ? BigDecimal.ZERO : molingModeCost;
     discountMoney = discountMoney == null ? BigDecimal.ZERO : discountMoney;
     forceMoney = forceMoney == null ? BigDecimal.ZERO : forceMoney;
     return realCost.add(molingModeCost).add(discountMoney).add(forceMoney);
   }
 }

