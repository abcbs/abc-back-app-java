 package com.ndlan.canyin.open.bldcbservices.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableBillRelationService;
import com.ndlan.canyin.open.utils.OpenResponseDataFormat;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.DinnerStatusEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.OpenInterfaceDefineEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.ServletRequest;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("openTableController")
 @Resource(name="openTableController")
 @RequestMapping({"/mxbapi/bldcb/table"})
 @Lazy
 public class OpenTableController extends BaseManageController
 {
 
   @Autowired
   private TableService tableService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   TableBillRelationService tableBillRelationService;
 
   @RequestMapping(value={"opentable"}, produces={"application/json"})
   @ResponseBody
   public Object[] openTable(@RequestParam(value="tabno", defaultValue="") String tabNo, @RequestParam(value="tabid", defaultValue="") String tabId, @RequestParam(value="peoplenum", defaultValue="0") int peopleNum, Model model, ServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.TABLE_OT.getCode(), "");
 
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
       return ordf.custom(301, "开台失败，未指定餐台", "未指定餐台编号或餐台ID");
     }
 
     if (table == null) {
       return ordf.custom(412, "开台失败，餐台不存在", tabNo + tabId);
     }
 
     if (!DinnerStatusEnum.IDLE.getCode().equalsIgnoreCase(table.getDinnerStatus())) {
       return ordf.custom(413, "开台失败，餐台非空闲", tabNo + tabId);
     }
 
     List tab_list = new ArrayList();
     tab_list.add(table);
     this.tableService.flushCurrentTable(tab_list);
     table = (Table)tab_list.get(0);
     if ((TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(table.getIsHasTableOrder())) && 
       (TrueFalseEnum.FALSE.getCode().equalsIgnoreCase(table.getIsAtOrderWarnTime()))) {
       return ordf.custom(414, "开台失败，餐台预订锁定中", tabNo + tabId);
     }
 
     DinerBill db = new DinerBill();
     table.setPeopleCount(peopleNum);
     db.setTable(table);
     db.setPeopleNum(Integer.valueOf(peopleNum));
 
     db.setSalesmanId(getCurrentUserId());
     db.setSalesmanName(getCurrentUser() != null ? getCurrentUser().getName() : "未登录");
 
     db.setWaiterId(getCurrentUserId());
     db.setWaiterName(getCurrentUser() != null ? getCurrentUser().getName() : "未登录");
 
     LinkedHashMap map = new LinkedHashMap();
     Object[] res = this.dinerBillService.saveCreateTableDinerBill(getCurrentUserRestId(), db, null, null, map) ? 
       ordf.succsess(tabNo + tabId) : ordf.custom(415, "开台失败,生成账单错误", tabNo + tabId);
 
     doSynchMultiTable(map);
 
     return res;
   }
 
   @RequestMapping(value={"mergetables"}, produces={"application/json"})
   @ResponseBody
   public Object[] mergeTables(@RequestParam(value="orgtabno", defaultValue="") String originalTabNo, @RequestParam(value="orgtabid", defaultValue="") String originalTabId, @RequestParam(value="newtabno", defaultValue="") String newTabNo, @RequestParam(value="newtabid", defaultValue="") String newTabId, Model model, ServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.TABLE_MT.getCode(), "");
 
     Table originalTable = null;
     Table newTable = null;
     if (StringUtils.isNotEmpty(originalTabNo)) {
       originalTable = this.tableService.findByTabNoAndRestIdAndIsEnable(originalTabNo, getCurrentUserRestId());
       originalTabId = "";
     }
     else if (StringUtils.isNotEmpty(originalTabId)) {
       originalTable = (Table)this.tableService.getOne(originalTabId);
       originalTable = EnableStatusEnum.NORMAL.getCode().equals(originalTable.getIsEnable()) ? originalTable : null;
     }
     else {
       return ordf.custom(311, "并台失败,未指定原餐台");
     }
     if (StringUtils.isNotEmpty(newTabNo)) {
       newTable = this.tableService.findByTabNoAndRestIdAndIsEnable(newTabNo, getCurrentUserRestId());
       newTabId = "";
     }
     else if (StringUtils.isNotEmpty(newTabId)) {
       newTable = (Table)this.tableService.getOne(newTabId);
       newTable = EnableStatusEnum.NORMAL.getCode().equals(newTable.getIsEnable()) ? newTable : null;
     }
     else {
       return ordf.custom(312, "并台失败,未指定新餐台");
     }
 
     if ((originalTable == null) || (newTable == null)) {
       return ordf.custom(410, "并台失败,餐台不存在", originalTabNo + originalTabId + "," + newTabNo + newTabId);
     }
     if (originalTable.getTabId().equalsIgnoreCase(newTable.getRestId())) {
       return ordf.custom(411, "并台失败,同一个餐台无法合并", originalTabNo + originalTabId + "," + newTabNo + newTabId);
     }
 
     if (!DinnerStatusEnum.USING.getCode().equalsIgnoreCase(originalTable.getDinnerStatus())) {
       return ordf.custom(412, "并台失败,原餐台未在使用中", originalTabNo + originalTabId + "," + newTabNo + newTabId);
     }
     if (!DinnerStatusEnum.USING.getCode().equalsIgnoreCase(newTable.getDinnerStatus())) {
       return ordf.custom(413, "并台失败,新餐台未在使用中", originalTabNo + originalTabId + "," + newTabNo + newTabId);
     }
 
     List ls_o = new ArrayList();
     ls_o.add(originalTable);
     this.tableService.flushCurrentTable(ls_o);
     originalTable = (Table)ls_o.get(0);
 
     DinerBill dinerBill = originalTable.getCurrentTableBillRelation().getDinerBill();
     if ((BillStatusEnum.NOT_PLACE_ORDER.getCode().equalsIgnoreCase(dinerBill.getBillStatus())) || 
       (BillStatusEnum.PLACE_ORDER.getCode().equalsIgnoreCase(dinerBill.getBillStatus())) || 
       (BillStatusEnum.SOME_PLACE_ORDER.getCode().equalsIgnoreCase(dinerBill.getBillStatus())))
     {
       try
       {
         dinerBill.setOldTabId(originalTable.getTabId());
         dinerBill.setTable(newTable);
         LinkedHashMap map = new LinkedHashMap();
         this.dinerBillService.saveBingtai(getCurrentUserRestId(), dinerBill, map);
 
         doSynchMultiTable(map);
       }
       catch (Exception e)
       {
         e.printStackTrace();
         return ordf.custom(510, "程序异常", originalTabNo + originalTabId + "," + newTabNo + newTabId);
       }
     }
     return ordf.succsess(originalTabNo + originalTabId + "," + newTabNo + newTabId);
   }
 
   @RequestMapping(value={"changetable"}, produces={"application/json"})
   @ResponseBody
   public Object[] changeTable(@RequestParam(value="orgtabno", defaultValue="") String originalTabNo, @RequestParam(value="orgtabid", defaultValue="") String originalTabId, @RequestParam(value="newtabno", defaultValue="") String newTabNo, @RequestParam(value="newtabid", defaultValue="") String newTabId, Model model, ServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.TABLE_CT.getCode(), "");
 
     Table originalTable = null;
     Table newTable = null;
     if (StringUtils.isNotEmpty(originalTabNo)) {
       originalTable = this.tableService.findByTabNoAndRestIdAndIsEnable(originalTabNo, getCurrentUserRestId());
       originalTabId = "";
     }
     else if (StringUtils.isNotEmpty(originalTabId)) {
       originalTable = (Table)this.tableService.getOne(originalTabId);
       originalTable = EnableStatusEnum.NORMAL.getCode().equals(originalTable.getIsEnable()) ? originalTable : null;
     }
     else {
       return ordf.custom(311, "转台失败,未指定原餐台");
     }
     if (StringUtils.isNotEmpty(newTabNo)) {
       newTable = this.tableService.findByTabNoAndRestIdAndIsEnable(newTabNo, getCurrentUserRestId());
       newTabId = "";
     }
     else if (StringUtils.isNotEmpty(newTabId)) {
       newTable = (Table)this.tableService.getOne(newTabId);
       newTable = EnableStatusEnum.NORMAL.getCode().equals(newTable.getIsEnable()) ? newTable : null;
     }
     else {
       return ordf.custom(312, "转台失败,未指定新餐台");
     }
 
     if ((originalTable == null) || (newTable == null)) {
       return ordf.custom(410, "转台失败，找不到餐台", "原餐台：" + originalTabNo + originalTabId + "新餐台：" + newTabId + newTabNo);
     }
     if (originalTable.getTabId().equalsIgnoreCase(newTable.getRestId())) {
       return ordf.custom(411, "转台失败，同一个餐台不需要转台", "原餐台：" + originalTabNo + originalTabId + "新餐台：" + newTabId + newTabNo);
     }
 
     List ls_o = new ArrayList();
     ls_o.add(originalTable);
     this.tableService.flushCurrentTable(ls_o);
     originalTable = (Table)ls_o.get(0);
 
     if (!DinnerStatusEnum.USING.getCode().equalsIgnoreCase(originalTable.getDinnerStatus())) {
       return ordf.custom(412, "转台失败，原餐台未在使用中", "原餐台：" + originalTabNo + originalTabId + "新餐台：" + newTabId + newTabNo);
     }
     if (!DinnerStatusEnum.IDLE.getCode().equalsIgnoreCase(newTable.getDinnerStatus())) {
       return ordf.custom(413, "转台失败，新餐台非空闲", "原餐台：" + originalTabNo + originalTabId + "新餐台：" + newTabId + newTabNo);
     }
 
     List tab_list = new ArrayList();
     tab_list.add(newTable);
     this.tableService.flushCurrentTable(tab_list);
     newTable = (Table)tab_list.get(0);
     if ((TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(newTable.getIsHasTableOrder())) && 
       (TrueFalseEnum.FALSE.getCode().equalsIgnoreCase(newTable.getIsAtOrderWarnTime()))) {
       return ordf.custom(414, "转台失败，新餐台在预订锁定中", "原餐台：" + originalTabNo + originalTabId + "新餐台：" + newTabId + newTabNo);
     }
 
     DinerBill dinerBill = originalTable.getCurrentTableBillRelation().getDinerBill();
     if ((BillStatusEnum.NOT_PLACE_ORDER.getCode().equalsIgnoreCase(dinerBill.getBillStatus())) || 
       (BillStatusEnum.PLACE_ORDER.getCode().equalsIgnoreCase(dinerBill.getBillStatus())) || 
       (BillStatusEnum.SOME_PLACE_ORDER.getCode().equalsIgnoreCase(dinerBill.getBillStatus()))) {
       dinerBill.setOldTabId(originalTable.getTabId());
       dinerBill.setTable(newTable);
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveZhuantai(getCurrentUserRestId(), dinerBill, map);
 
       doSynchMultiTable(map);
     }
 
     return ordf.succsess("原餐台：" + originalTabNo + originalTabId + "新餐台：" + newTabId + newTabNo);
   }
 
   @RequestMapping(value={"revoketable"}, produces={"application/json"})
   @ResponseBody
   public Object[] revokeTable(@RequestParam(value="tabno", defaultValue="") String originalTabNo, @RequestParam(value="tabid", defaultValue="") String originalTabId, Model model, ServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.TABLE_RT.getCode(), "");
     return ordf.nonOpen("业务说明：如果餐台不存在已下单账单，那么餐台状态置为空闲，并删除该餐台当前账单。");
   }
 
   @ModelAttribute
   public void getPaymentType(@RequestParam(value="tabid", required=false) String tabId, @RequestParam(value="tabno", required=false) String tabNo, Model model) {
     if (StringUtils.isNotEmpty(tabNo)) {
       model.addAttribute("table", this.tableService.findByTabNoAndRestIdAndIsEnable(tabNo, getCurrentUserRestId()));
     }
     else if (StringUtils.isNotEmpty(tabId)) {
       Table table = (Table)this.tableService.getOne(tabId);
       table = EnableStatusEnum.NORMAL.getCode().equals(table.getIsEnable()) ? table : null;
       model.addAttribute("table", table);
     }
     else {
       model.addAttribute("table", null);
     }
   }
 }

