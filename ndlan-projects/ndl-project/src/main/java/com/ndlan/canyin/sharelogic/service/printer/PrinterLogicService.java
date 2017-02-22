 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.cygl.DishesCategory;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.AreaTypeEnum;
 import com.ndlan.canyin.core.common.BillTypeEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.BeanUtils;
 import java.util.ArrayList;
 import java.util.List;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class PrinterLogicService extends BaseLogicService<PrinterDao, Printer>
 {
   private PrinterDao printerDao;
 
   public List<Printer> findAllPrinters(String restId)
   {
     return this.printerDao.findByRestIdAndStatus(restId, EnableStatusEnum.NORMAL.getCode());
   }
 
   public List<Printer> findByRestIdAndStatusAndType(String restId, String status, String type) {
     return this.printerDao.findByRestIdAndStatusAndType(restId, status, type);
   }
 
   public List<Printer> findPrintersByArea(String area, String billType, String restId, String status, String type)
   {
     if (StringUtils.isEmpty(area)) {
       if (BillTypeEnum.KUAICAN_BILL.getCode().equalsIgnoreCase(billType)) {
         area = AreaTypeEnum.KUAICAN.getCode();
       }
       else if (BillTypeEnum.WAIMAI_BILL.getCode().equalsIgnoreCase(billType)) {
         area = AreaTypeEnum.KUAICAN.getCode();
       }
       else {
         area = "";
       }
 
     }
 
     List<Printer> printers = this.printerDao.findByRestIdAndStatusAndType(restId, status, type);
     List printersList = new ArrayList();
 
     for (Printer printer : printers)
     {
       if (TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(printer.getIsAllArea())) {
         printersList.add(printer);
       }
       else
       {
         String areaIds = printer.getTableArea();
         if ((StringUtils.isNotEmpty(areaIds)) && (areaIds.indexOf(area) != -1)) {
           printersList.add(printer);
         }
       }
     }
 
     return printersList;
   }
 
   public List<DinerBillDishe> findDinnerDishesByDisheCategorys(boolean allDisheCattegorys, String disheCategorys, List<DinerBillDishe> disheAndDisheSetqsList)
     throws Exception
   {
     List list = new ArrayList();
 
     for (DinerBillDishe dishe : disheAndDisheSetqsList) {
       if (TrueFalseEnum.TRUE.getCode().equals(dishe.getIsSet())) {
         List disheSetDishesList = new ArrayList();
         for (DinerBillDishe disheSetDishes : dishe.getDishesSetDishesList()) {
           if ((allDisheCattegorys) || ((StringUtils.isNotEmpty(disheCategorys)) && (disheCategorys.indexOf(disheSetDishes.getDishesCategory().getCategoryId()) != -1))) {
             disheSetDishesList.add(disheSetDishes);
           }
         }
         if ((allDisheCattegorys) || ((disheSetDishesList != null) && (disheSetDishesList.size() > 0))) {
           BeanUtils beanUtils = new BeanUtils();
           DinerBillDishe newBillDishe = (DinerBillDishe)BeanUtils.deepCopy(dishe);
           newBillDishe.setDishesSetDishesList(disheSetDishesList);
           list.add(newBillDishe);
         }
       }
       else if ((allDisheCattegorys) || ((StringUtils.isNotEmpty(disheCategorys)) && (disheCategorys.indexOf(dishe.getDishesCategory().getCategoryId()) != -1))) {
         list.add(dishe);
       }
     }
 
     return list;
   }
 
   public DinerBillDishe findDinnerDishesByDisheCategorysForSet(boolean allDisheCattegorys, String disheCategorys, DinerBillDishe dinerBillDishe)
     throws Exception
   {
     DinerBillDishe newBillDishe = new DinerBillDishe();
     List disheSetDishesList = new ArrayList();
     for (DinerBillDishe disheSetDishes : dinerBillDishe.getDishesSetDishesList()) {
       if ((allDisheCattegorys) || ((StringUtils.isNotEmpty(disheCategorys)) && (disheCategorys.indexOf(disheSetDishes.getDishesCategory().getCategoryId()) != -1))) {
         disheSetDishesList.add(disheSetDishes);
       }
     }
     if ((allDisheCattegorys) || ((disheSetDishesList != null) && (disheSetDishesList.size() > 0))) {
       BeanUtils beanUtils = new BeanUtils();
       newBillDishe = (DinerBillDishe)BeanUtils.deepCopy(dinerBillDishe);
       newBillDishe.setDishesSetDishesList(disheSetDishesList);
     }
     return newBillDishe;
   }
   @Autowired
   public void setPrinterDao(PrinterDao printerDao) {
     super.setDao(printerDao);
     this.printerDao = printerDao;
   }
 }

