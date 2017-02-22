 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.fasterxml.jackson.core.JsonParseException;
 import com.fasterxml.jackson.databind.JsonMappingException;
 import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
 import com.ndlan.canyin.base.entity.cygl.Dishe;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhu;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhuDishe;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillZiZhuDao;
 import com.ndlan.canyin.core.common.BillSeqTypeEnum;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.BillTypeEnum;
 import com.ndlan.canyin.core.common.DishesStatusEnum;
 import com.ndlan.canyin.core.common.MolingModeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.DateProvider;
 import java.io.IOException;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Lazy
 public class DinerBillZiZhuService extends BaseService<DinerBillZiZhuDao, DinerBillZiZhu>
 {
 
   @Autowired
   DinerBillZiZhuDisheService dinerBillZiZhuDisheService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   DinerBillService dinerBillService;
 
   @Autowired
   DinerBillDisheService dinerBillDisheService;
 
   @Autowired
   private DisheService disheService;
 
   @Autowired
   public void setDao(DinerBillZiZhuDao dao)
   {
     super.setDao(dao);
   }
 
   public List<DinerBillZiZhu> findByRestId(String restId) {
     return ((DinerBillZiZhuDao)getDao()).findByRestId(restId);
   }
 
   @Transactional(readOnly=false)
   public DinerBillZiZhu saveZiZhu(String restId, String billId, String cbsId, String dinerBillZiZhuDisheStr, String realCost)
     throws IOException, ClassNotFoundException
   {
     DinerBillZiZhu newZizhu = null;
     if (StringUtils.isEmpty(billId))
     {
       newZizhu = new DinerBillZiZhu();
       newZizhu.setCbsId(cbsId);
       newZizhu.setRealCost(BigDecimal.ZERO);
       newZizhu.setBillNo(this.dinerBillSeqService.createNewBillNo(restId, BillSeqTypeEnum.ZIZHU, new LinkedHashMap()));
       newZizhu.setIsValid(TrueFalseEnum.TRUE.getCode());
       newZizhu.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
       newZizhu.setBillType(BillTypeEnum.ZIZHU_BILL.getCode());
       newZizhu.setBillTime(DateProvider.DEFAULT.getDate());
       billId = newZizhu.getBillId();
     }
     else
     {
       newZizhu = (DinerBillZiZhu)this.self.getOne(billId);
     }
     int peopleNum = 0;
     BigDecimal totalPrice = BigDecimal.ZERO;
     this.self.save(newZizhu);
     try {
       if (!StringUtils.isEmpty(dinerBillZiZhuDisheStr))
       {
         ObjectMapper mapper = new ObjectMapper();
 
         List list = (List)mapper.readValue(dinerBillZiZhuDisheStr, List.class);
 
         List l = new ArrayList();
         for (int i = 0; i < list.size(); i++) {
           Map map = (Map)list.get(i);
           String unitNum = map.get("unitNum").toString();
           if ((StringUtils.isEmpty(unitNum)) || 
             (Float.valueOf(unitNum).floatValue() == 0.0F))
           {
             continue;
           }
           String ziZhuYouhuiName = map.get("ziZhuYouhuiName").toString();
           String ziZhuYouhuiUnitPrice = map.get("ziZhuYouhuiUnitPrice").toString();
           DinerBillZiZhuDishe newD = new DinerBillZiZhuDishe();
           newD.setBillNo(newZizhu.getBillNo());
           newD.setDinerBillZiZhu(newZizhu);
           newD.setOrderTime(DateProvider.DEFAULT.getDate());
           newD.setZiZhuYouhuiName(ziZhuYouhuiName);
           newD.setUnitNum(Float.valueOf(unitNum).floatValue());
           peopleNum = (int)(peopleNum + newD.getUnitNum());
           newD.setZiZhuYouhuiUnitPrice(BigDecimal.valueOf(Double.valueOf(ziZhuYouhuiUnitPrice).doubleValue()));
           newD.setZiZhuYouhuiTotalPrice(newD.getZiZhuYouhuiUnitPrice().multiply(BigDecimal.valueOf(newD.getUnitNum())));
           totalPrice = totalPrice.add(newD.getZiZhuYouhuiTotalPrice());
           this.dinerBillZiZhuDisheService.save(newD);
           l.add(newD);
         }
         newZizhu.setDinerBillZiZhuDishe(l);
       }
       newZizhu.setRealCost(totalPrice);
       newZizhu.setPeopleNum(Integer.valueOf(peopleNum));
       this.self.save(newZizhu);
     } catch (JsonParseException e) {
       e.printStackTrace();
     } catch (JsonMappingException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
 
     return newZizhu;
   }
 
   @Transactional(readOnly=false)
   public DinerBill saveZiZhuDinerBill(String restId, String billId, String cbsId, String dinerBillZiZhuDisheStr, String realCost) throws IOException, ClassNotFoundException {
     DinerBill newZizhu = null;
     if (StringUtils.isEmpty(billId))
     {
       newZizhu = new DinerBill();
       newZizhu.setRealCost(BigDecimal.ZERO);
       newZizhu.setBillNo(this.dinerBillSeqService.createNewBillNo(restId, BillSeqTypeEnum.ZIZHU, new LinkedHashMap()));
       newZizhu.setIsValid(TrueFalseEnum.TRUE.getCode());
       newZizhu.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
       newZizhu.setBillType(BillTypeEnum.ZIZHU_BILL.getCode());
       newZizhu.setBillTime(DateProvider.DEFAULT.getDate());
       newZizhu.setDiscount(Integer.valueOf(100));
       newZizhu.setServiceChargeMoney(BigDecimal.ZERO);
       newZizhu.setIsShift(TrueFalseEnum.FALSE.getCode());
       billId = newZizhu.getBillId();
     }
     else
     {
       newZizhu = (DinerBill)this.dinerBillService.getOne(billId);
     }
     int peopleNum = 0;
     BigDecimal totalPrice = BigDecimal.ZERO;
     this.dinerBillService.save(newZizhu);
     try {
       if (!StringUtils.isEmpty(dinerBillZiZhuDisheStr))
       {
         ObjectMapper mapper = new ObjectMapper();
 
         List list = (List)mapper.readValue(dinerBillZiZhuDisheStr, List.class);
 
         List l = new ArrayList();
         for (int i = 0; i < list.size(); i++) {
           Map map = (Map)list.get(i);
           String unitNum = map.get("unitNum").toString();
           if ((StringUtils.isEmpty(unitNum)) || 
             (Float.valueOf(unitNum).floatValue() == 0.0F))
           {
             continue;
           }
           String ziZhuYouhuiName = map.get("ziZhuYouhuiName").toString();
           List dishes = this.disheService.findByDishesNameAndRestId(ziZhuYouhuiName, restId);
           if ((dishes == null) || (dishes.size() <= 0))
             continue;
           Dishe dishe = (Dishe)dishes.get(0);
           String unitPrice = dishe.getPrice().toString();
           DinerBillDishe newD = new DinerBillDishe();
 
           newD.setDishesId(dishe.getDishesId());
           newD.setBillNo(newZizhu.getBillNo());
           newD.setDishesCategory(dishe.getDishesCategory());
           newD.setDishesCode(dishe.getDishesCode());
           newD.setDishesStatus(DishesStatusEnum.SERVED.getCode());
           newD.setRestId(restId);
           newD.setDinerBill(newZizhu);
           newD.setDiscount(Integer.valueOf(100));
           newD.setOrderTime(DateProvider.DEFAULT.getDate());
           newD.setDishesName(dishe.getDishesName());
           newD.setUnitNum(Float.valueOf(unitNum).floatValue());
           peopleNum = (int)(peopleNum + newD.getUnitNum());
           newD.setUnitPrice(BigDecimal.valueOf(Double.valueOf(unitPrice).doubleValue()));
           newD.setRealUnitPrice(BigDecimal.valueOf(Double.valueOf(unitPrice).doubleValue()));
           newD.setRealCost(newD.getUnitPrice().multiply(BigDecimal.valueOf(newD.getUnitNum())));
           newD.setOriCost(newD.getUnitPrice().multiply(BigDecimal.valueOf(newD.getUnitNum())));
           totalPrice = totalPrice.add(newD.getRealCost());
           this.dinerBillDisheService.save(newD);
           l.add(newD);
         }
 
         newZizhu.setDinerBillDishes(l);
       }
       newZizhu.setRealCost(totalPrice);
       newZizhu.setOriCost(totalPrice);
       newZizhu.setPayableCost(totalPrice);
       newZizhu.setLosseGain(BigDecimal.ZERO);
       newZizhu.setMolingMode(MolingModeEnum.NOTDEAL.getCode());
       newZizhu.setMolingModeCost(BigDecimal.ZERO);
       newZizhu.setPeopleNum(Integer.valueOf(peopleNum));
       this.dinerBillService.save(newZizhu);
     } catch (JsonParseException e) {
       e.printStackTrace();
     } catch (JsonMappingException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
 
     return newZizhu;
   }
   @Transactional(readOnly=false)
   public DinerBillZiZhu resetZiZhu(String billId) {
     DinerBillZiZhu newZizhu = (DinerBillZiZhu)this.self.getOne(billId);
 
     newZizhu.setRealCost(BigDecimal.ZERO);
     this.self.save(newZizhu);
     List<DinerBillZiZhuDishe> list = newZizhu.getDinerBillZiZhuDishe();
     if ((list != null) && (list.size() > 0))
     {
       for (DinerBillZiZhuDishe e : list)
       {
         this.dinerBillZiZhuDisheService.delete(e.getBdId());
       }
     }
     return newZizhu;
   }
 }

