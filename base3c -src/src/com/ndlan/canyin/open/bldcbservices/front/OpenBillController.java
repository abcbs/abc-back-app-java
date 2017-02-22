 package com.ndlan.canyin.open.bldcbservices.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
import com.ndlan.canyin.open.utils.OpenResponseDataFormat;
import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.core.common.OpenInterfaceDefineEnum;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("OpenBillController")
 @Resource(name="openBillController")
 @RequestMapping({"/mxbapi/bldcb/bill"})
 @Lazy
 public class OpenBillController extends BaseManageController
 {
 
   @Autowired
   DinerBillService dinerBillService;
 
   @Autowired
   TableService tableService;
 
   @Autowired
   DinerBillDisheService dinerBillDisheService;
 
   @RequestMapping(value={"findbillbytab"}, produces={"application/json"})
   @ResponseBody
   public Object[] getBillByTableNo(@RequestParam(value="tabno", required=true) String tabNo, Model model, HttpServletRequest request)
   {
     Table table = this.tableService.findByTabNoAndRestIdAndIsEnable(tabNo, getCurrentUserRestId());
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BILL_FBT.getCode(), "");
     DinerBill db = null;
     List l = new ArrayList();
     if (table != null) {
       List lt = new ArrayList();
       lt.add(table);
       this.tableService.flushCurrentTable(lt);
       table = (Table)lt.get(0);
       TableBillRelation lastedTableNormalBillRelation = this.tableService.getLastedTableNormalBillRelation(table);
       db = lastedTableNormalBillRelation != null ? lastedTableNormalBillRelation.getDinerBill() : null;
       if (db != null) {
         Map map = new HashMap();
 
         map.put("billNo", db.getBillNo());
         map.put("tableNo", table.getTabNo());
         map.put("tableName", table.getTabName());
         map.put("time", db.getCreateTime().toString());
         map.put("billPrices", db.getConsumeCost());
 
         map.put("billStatus", db.getBillStatus());
         map.put("billAvoidIds", db.getAvoidfoodIdArray());
         map.put("billTasteIds", db.getTasteIdArray());
         map.put("billPungentLv", Integer.valueOf(db.getPungentLevel()));
         map.put("billNotes", db.getNotes());
         map.put("billAllNotes", db.getAllNotes());
 
         l.add(map);
 
         List list_dbd = this.dinerBillDisheService.findByRestIdAndBillId(getCurrentUserRestId(), db.getBillId());
         Map map_dish = null;
         if ((list_dbd != null) && (list_dbd.size() > 0)) {
           for (int i = 0; i < list_dbd.size(); i++) {
             map_dish = new HashMap();
             DinerBillDishe bean = (DinerBillDishe)list_dbd.get(i);
 
             map_dish.put("dishNo", bean.getDishesCode());
             map_dish.put("dishName", bean.getDishesName());
             map_dish.put("price", bean.getRealUnitPrice());
             map_dish.put("count", bean.getUnitNumStr());
             map_dish.put("unit", bean.getUnitName());
             map_dish.put("prices", bean.getRealCost());
             map_dish.put("dishesStatus", bean.getDishesStatus());
             map_dish.put("avoidIds", bean.getAvoidfoodIdArray());
             map_dish.put("tasteIds", bean.getTasteIdArray());
             map_dish.put("pungentLv", Integer.valueOf(bean.getPungentLevel()));
             map_dish.put("notes", bean.getNotes());
             map_dish.put("allNotes", bean.getAllNotes());
             l.add(map_dish);
           }
         }
         return ordf.succsess(l);
       }
 
       return ordf.custom(411, "查询账单失败，该餐台没有账单");
     }
 
     return ordf.custom(412, "查询账单失败，该餐台不存在");
   }
 }

