 package com.ndlan.canyin.open.bldcbservices.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesAvoidfoodService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesTasteService;
import com.ndlan.canyin.frontdesk.service.jour.JourService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableBillRelationService;
import com.ndlan.canyin.open.defines.MyException;
import com.ndlan.canyin.open.utils.OpenResponseDataFormat;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
 import com.ndlan.canyin.base.entity.cygl.DishesAvoidfood;
 import com.ndlan.canyin.base.entity.cygl.DishesTaste;
 import com.ndlan.canyin.base.entity.jour.Jour;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.core.common.DinnerStatusEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.OpenInterfaceDefineEnum;
 import com.ndlan.canyin.core.common.OpenNoteTypeEnum;
 import com.ndlan.canyin.core.common.PungentLevelEnum;
 import com.ndlan.canyin.core.utils.DateProvider;
 import com.ndlan.canyin.core.vo.TakingDishesVo;
 import com.ndlan.canyin.sharelogic.service.printer.XiadanPrinterService;
 import java.io.UnsupportedEncodingException;
 import java.net.URLDecoder;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.LinkedHashMap;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.Cookie;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("openDishController")
 @Resource(name="openDishController")
 @RequestMapping({"/mxbapi/bldcb/dish"})
 @Lazy
 public class OpenDishController extends BaseManageController
 {
 
   @Autowired
   private JourService jourService;
 
   @Autowired
   private TableService tableService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   TableBillRelationService tableBillRelationService;
 
   @Autowired
   DisheService disheService;
 
   @Autowired
   DishesAvoidfoodService dishesAvoidfoodService;
 
   @Autowired
   DishesTasteService dishesTasteService;
 
   @Autowired
   XiadanPrinterService xiadanPrinterService;
 
   @RequestMapping(value={"takingdishes"}, produces={"application/json"})
   @ResponseBody
   public Object[] takingdishes(@RequestParam(value="tabno", defaultValue="") String tabNo, @RequestParam(value="tabid", defaultValue="") String tabId, @RequestParam(value="orderflag", defaultValue="0") String orderFlag, @RequestParam(value="billnote", defaultValue="") String billNotes, @RequestParam(value="dishno", required=true) String dishNos, @RequestParam(value="dishcount", required=true) String dishCounts, @RequestParam(value="dishnote", defaultValue="") String dishNotes, Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.DISH_TD.getCode(), "");
 
     Table table = null;
     if (StringUtils.isNotEmpty(tabNo)) {
       table = this.tableService.findByTabNoAndRestIdAndIsEnable(tabNo, getCurrentUserRestId());
       tabId = "";
     }
     else if (StringUtils.isNotEmpty(tabId)) {
       table = (Table)this.tableService.getOne(tabId);
       table = EnableStatusEnum.NORMAL.getCode().equals(table.getIsEnable()) ? table : null;
     }
     else {
       return ordf.custom(331, "点菜失败，未指定餐台");
     }
 
     String[] arr_dishNo = dishNos.split("┍");
     String[] arr_dishNum = dishCounts.split("┍");
 
     String[] arr_dishNote = null;
     if (StringUtils.isNotEmpty(dishNotes)) {
       arr_dishNote = dishNotes.split("┍");
     }
     String[] arr_billNote = null;
     if (StringUtils.isNotEmpty(billNotes)) {
       arr_billNote = billNotes.split("┍");
     }
 
     int len = arr_dishNo.length;
     if (len != arr_dishNum.length) {
       return ordf.custom(332, "点菜失败，菜肴数量错误");
     }
     if ((arr_dishNote != null) && (len != arr_dishNote.length)) {
       return ordf.custom(333, "点菜失败，菜肴备注错误");
     }
 
     if (table == null) {
       return ordf.custom(431, "点菜失败，餐台不存在");
     }
 
     if (!DinnerStatusEnum.USING.getCode().equalsIgnoreCase(table.getDinnerStatus())) {
       return ordf.custom(432, "点菜失败，餐台不在使用中");
     }
 
     List list = new ArrayList();
     list.add(table);
     this.tableService.flushCurrentTable(list);
     table = (Table)list.get(0);
     TableBillRelation lastedTableNormalBillRelation = this.tableService.getLastedTableNormalBillRelation(table);
     DinerBill db = lastedTableNormalBillRelation != null ? lastedTableNormalBillRelation.getDinerBill() : null;
 
     if (db == null) {
       return ordf.custom(433, "点菜失败，餐台无对应账单");
     }
 
     List list_td = new ArrayList();
     String restId = getCurrentUserRestId();
     boolean ytjzdbz = false;
     for (int i = 0; i < arr_dishNo.length; i++) {
       TakingDishesVo bean = new TakingDishesVo();
       bean.setRestId(restId);
       bean.setBillId(db.getBillId());
       bean.setBillId(db.getBillId());
       bean.setDishCode(arr_dishNo[i]);
       bean.setDishNum(Float.parseFloat(arr_dishNum[i]));
       bean.setUserDefined(false);
 
       if ((arr_dishNote != null) && (StringUtils.isNotEmpty(arr_dishNote[i]))) {
         String[] arr_dn = arr_dishNote[i].split("┎");
         for (int j = 0; j < arr_dn.length; j++) {
           String note = arr_dn[j];
           if (StringUtils.isEmpty(note))
           {
             continue;
           }
           if (note.indexOf(OpenNoteTypeEnum.ZIDINGYI.getCode(), 0) == 0)
           {
             String n = note.replaceFirst(OpenNoteTypeEnum.ZIDINGYI.getCode(), "");
             if (StringUtils.isNotEmpty(n)) {
               n = StringUtils.isNotEmpty(bean.getDishCustomNotes()) ? bean.getDishCustomNotes() + ";" + n : n;
               bean.setDishCustomNotes(n);
             }
 
           }
           else if (note.indexOf(OpenNoteTypeEnum.JIKOU.getCode(), 0) == 0)
           {
             String n = note.replaceFirst(OpenNoteTypeEnum.JIKOU.getCode(), "");
             String id = "";
             if (StringUtils.isNotEmpty(n)) {
               List ls = this.dishesAvoidfoodService.findByCodeAndRestId(n, restId);
               if ((ls != null) && (ls.size() == 1)) {
                 id = "," + ((DishesAvoidfood)ls.get(0)).getCdaId();
                 bean.setDishAvoidIds(id);
               }
               else {
                 return ordf.custom(434, "点菜失败，菜肴烹饪备注忌口信息不正确", n);
               }
 
             }
 
           }
           else if (note.indexOf(OpenNoteTypeEnum.KOUWEI.getCode(), 0) == 0)
           {
             String n = note.replaceFirst(OpenNoteTypeEnum.KOUWEI.getCode(), "");
             String id = "";
             if (StringUtils.isNotEmpty(n)) {
               List ls = this.dishesTasteService.findByCodeAndRestIdAndEnableStatus(n, restId);
               if ((ls != null) && (ls.size() == 1)) {
                 id = "," + ((DishesTaste)ls.get(0)).getTasteId();
                 bean.setDishTasteIds(id);
               }
               else {
                 return ordf.custom(435, "点菜失败，菜肴烹饪备注口味信息不正确", n);
               }
 
             }
 
           }
           else if (note.indexOf(OpenNoteTypeEnum.LADU.getCode(), 0) == 0)
           {
             String n = note.replaceFirst(OpenNoteTypeEnum.LADU.getCode(), "");
             if (StringUtils.isNotEmpty(n)) {
               if (StringUtils.isNotEmpty(bean.getDishPungentLevelCode())) {
                 return ordf.custom(436, "点菜失败，同一个菜肴只能选择一个辣度", n);
               }
               if ("未知".equalsIgnoreCase(PungentLevelEnum.getDesc(n))) {
                 return ordf.custom(437, "点菜失败，不存在该辣度", n);
               }
               bean.setDishPungentLevelCode(n);
             }
           }
           else {
             return ordf.custom(438, "点菜失败，未定义的菜肴烹饪备注类型", note);
           }
 
         }
 
       }
 
       if ((!ytjzdbz) && (arr_billNote != null)) {
         for (int j = 0; j < arr_billNote.length; j++) {
           String note = arr_billNote[j];
           if (StringUtils.isEmpty(note))
           {
             continue;
           }
           if (note.indexOf(OpenNoteTypeEnum.ZIDINGYI.getCode(), 0) == 0)
           {
             String n = note.replaceFirst(OpenNoteTypeEnum.ZIDINGYI.getCode(), "");
             if (StringUtils.isNotEmpty(n)) {
               n = StringUtils.isNotEmpty(bean.getBillCustomNotes()) ? bean.getBillCustomNotes() + ";" + n : n;
               bean.setBillCustomNotes(n);
             }
 
           }
           else if (note.indexOf(OpenNoteTypeEnum.JIKOU.getCode(), 0) == 0)
           {
             String n = note.replaceFirst(OpenNoteTypeEnum.JIKOU.getCode(), "");
             String id = "";
             if (StringUtils.isNotEmpty(n)) {
               List ls = this.dishesAvoidfoodService.findByCodeAndRestId(n, restId);
               if ((ls != null) && (ls.size() == 1)) {
                 id = "," + ((DishesAvoidfood)ls.get(0)).getCdaId();
                 bean.setBillAvoidIds(id);
               }
               else {
                 return ordf.custom(444, "点菜失败，整单烹饪备注忌口信息不正确", n);
               }
 
             }
 
           }
           else if (note.indexOf(OpenNoteTypeEnum.KOUWEI.getCode(), 0) == 0)
           {
             String n = note.replaceFirst(OpenNoteTypeEnum.KOUWEI.getCode(), "");
             String id = "";
             if (StringUtils.isNotEmpty(n)) {
               List ls = this.dishesTasteService.findByCodeAndRestIdAndEnableStatus(n, restId);
               if ((ls != null) && (ls.size() == 1)) {
                 id = "," + ((DishesTaste)ls.get(0)).getTasteId();
                 bean.setBillTasteIds(id);
               }
               else {
                 return ordf.custom(445, "点菜失败，整单烹饪备注口味信息不正确");
               }
 
             }
 
           }
           else if (note.indexOf(OpenNoteTypeEnum.LADU.getCode(), 0) == 0)
           {
             String n = note.replaceFirst(OpenNoteTypeEnum.LADU.getCode(), "");
             if (StringUtils.isNotEmpty(n)) {
               if (StringUtils.isNotEmpty(bean.getBillPungentLevelCode())) {
                 return ordf.custom(446, "点菜失败，整单烹饪备注辣度只能选择一个", n);
               }
               if ("未知".equalsIgnoreCase(PungentLevelEnum.getDesc(n))) {
                 return ordf.custom(447, "点菜失败，不存在该辣度", n);
               }
               bean.setBillPungentLevelCode(n);
             }
           }
           else {
             return ordf.custom(448, "点菜失败，未定义的整单烹饪备注类型", note);
           }
         }
         ytjzdbz = true;
       }
 
       list_td.add(bean);
     }
 
     try
     {
       LinkedHashMap map = new LinkedHashMap();
 
       HashMap retMap = this.dinerBillService.piLiangDianCai(list_td, getCurrentUser(), orderFlag, map);
 
       DinerBill dinerBill = null;
       if ("0".equals(orderFlag)) {
         dinerBill = this.dinerBillService.xiadan(restId, db.getBillId(), map);
       }
 
       doSynchMultiTable(map);
 
       String message = retMap != null ? retMap.get("message").toString() : "";
       Jour jour = new Jour();
       jour.setTable(table);
       jour.setBill(db);
       jour.setJourStatus("");
       jour.setOperateType("");
       jour.setNote("");
 
       jour.setReturnMessage(table.getTabName() + "台，" + message + "点菜成功");
       jour.setReturnNo("200");
       jour.setFunctionNo(ordf.getInterfaceNo());
       saveJour(jour, request);
 
       ordf.setJourNo(jour.getJourNo());
       ordf.setInternalJourNo(jour.getInternalJourNo());
 
       if (("0".equals(orderFlag)) && (dinerBill != null)) {
         try {
           HashMap printParaments = new HashMap();
           printParaments.put("isAddDishes", dinerBill != null ? dinerBill.getIsAddDishes() : Boolean.FALSE);
           printParaments.put("operatorName", getCurrentUser() != null ? getCurrentUser().getName() : "未登录");
           this.xiadanPrinterService.printXiadan(restId, dinerBill, table, printParaments);
         }
         catch (Exception e) {
           e.printStackTrace();
           return ordf.custom(500, table.getTabName() + "台，" + message + "点菜成功,下单打印异常！");
         }
       }
 
       return ordf.custom(200, table.getTabName() + "台，" + message + "点菜成功");
     }
     catch (MyException e) {
       return ordf.custom(Integer.parseInt(e.getCode()), e.getMessage(), e.getNotes());
     } catch (UnsupportedEncodingException ue) {
     }
     return ordf.otherErr();
   }
 
   public void getJourInfoFromCookie(HttpServletRequest request, Jour jour)
     throws UnsupportedEncodingException
   {
     jour = jour != null ? jour : new Jour();
     Cookie[] cookies = request.getCookies();
     String str = "";
     if (cookies != null)
       for (Cookie cookie : cookies)
         if ("journo".equals(cookie.getName())) {
           str = URLDecoder.decode(cookie.getValue(), "utf-8");
           jour.setJourNo(str);
         }
         else if ("terminalno".equals(cookie.getName())) {
           str = URLDecoder.decode(cookie.getValue(), "utf-8");
           jour.setTerminalNo(str);
         }
         else if ("terminaltype".equals(cookie.getName())) {
           str = URLDecoder.decode(cookie.getValue(), "utf-8");
           jour.setTerminalType(str);
         }
   }
 
   public void saveJour(Jour jour, HttpServletRequest request)
     throws UnsupportedEncodingException
   {
     jour = jour != null ? jour : new Jour();
     getJourInfoFromCookie(request, jour);
 
     DateProvider dateProvider = DateProvider.DEFAULT;
 
     if (StringUtils.isEmpty(jour.getJourNo())) {
       jour.setJourNo("");
     }
     if (StringUtils.isEmpty(jour.getInternalJourNo())) {
       jour.setInternalJourNo(jour.getJourNo());
     }
     jour.setRestId(getCurrentUserRestId());
     jour.setCreateEmployee(getCurrentUser());
     jour.setJourTime(dateProvider.getDate());
     jour.setActionUrl(request.getRequestURI());
     jour.setTerminalIp(request.getRemoteAddr());
     this.jourService.save(jour);
   }
 }

