 package com.ndlan.canyin.open.bldcbservices.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesAvoidfoodService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesTasteService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesUnitService;
import com.ndlan.canyin.frontdesk.service.sygl.SpeOpReasonService;
import com.ndlan.canyin.open.utils.OpenResponseDataFormat;
import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.core.common.DinnerStatusEnum;
 import com.ndlan.canyin.core.common.OpenInterfaceDefineEnum;
 import com.ndlan.canyin.core.common.PungentLevelEnum;
 import com.ndlan.canyin.core.common.SpecialReasonTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("openBasicDataController")
 @Resource(name="openBasicDataController")
 @RequestMapping({"/mxbapi/bldcb/basic"})
 @Lazy
 public class OpenBasicDataController extends BaseManageController
 {
 
   @Autowired
   DisheService disheService;
 
   @Autowired
   DishesCategoryService disheCategoryService;
 
   @Autowired
   DishesUnitService dishesUnitService;
 
   @Autowired
   DishesAvoidfoodService dishesAvoidfoodService;
 
   @Autowired
   DishesTasteService dishesTasteService;
 
   @Autowired
   SpeOpReasonService speOpReasonService;
 
   @Autowired
   TableService tableService;
 
   @RequestMapping(value={"getalldishes"}, produces={"application/json"})
   @ResponseBody
   public Object[] getAllDishes(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_AD.getCode(), "");
     List list = this.disheService.findByRestIdAndIsUserDefinedAndIsStopSellOrderByShowSeqAsc(getCurrentUserRestId());
     if ((list != null) && (list.size() > 0)) {
       return ordf.succsess(list);
     }
 
     return ordf.noDataSuccsess();
   }
 
   @RequestMapping(value={"getdishecategorys"}, produces={"application/json"})
   @ResponseBody
   public Object[] getDisheCategorys(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_AC.getCode(), "");
     List list = this.disheCategoryService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     if ((list != null) && (list.size() > 0)) {
       return ordf.succsess(list);
     }
 
     return ordf.noDataSuccsess();
   }
 
   @RequestMapping(value={"getunits"}, produces={"application/json"})
   @ResponseBody
   public Object[] getDisheUnits(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_AU.getCode(), "");
     List list = this.dishesUnitService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     if ((list != null) && (list.size() > 0)) {
       return ordf.succsess(list);
     }
 
     return ordf.noDataSuccsess();
   }
 
   @RequestMapping(value={"getavoidfoods"}, produces={"application/json"})
   @ResponseBody
   public Object[] getAvoidDishes(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_AA.getCode(), "");
     List list = this.dishesAvoidfoodService.findAllDishesAvoidfoodByRestId(getCurrentUserRestId());
     if ((list != null) && (list.size() > 0)) {
       return ordf.succsess(list);
     }
 
     return ordf.noDataSuccsess();
   }
 
   @RequestMapping(value={"gettastes"}, produces={"application/json"})
   @ResponseBody
   public Object[] getTastes(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_A_TASTE.getCode(), "");
     List list = this.dishesTasteService.findAllDishesTasteByRestId(getCurrentUserRestId());
     if ((list != null) && (list.size() > 0)) {
       return ordf.succsess(list);
     }
 
     return ordf.noDataSuccsess();
   }
 
   @RequestMapping(value={"getpungentlevel"}, produces={"application/json"})
   @ResponseBody
   public Object getPungentLevel(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_A_PUNGENTLEVEL.getCode(), "");
     List pungentLevel = new ArrayList();
     for (PungentLevelEnum status : PungentLevelEnum.values()) {
       if (StringUtils.isNotEmpty(status.getDesc())) {
         Map map = new HashMap();
         map.put("code", status.getCode());
         map.put("name", status.getDesc());
         pungentLevel.add(map);
       }
     }
 
     if ((pungentLevel != null) && (pungentLevel.size() > 0)) {
       return ordf.succsess(pungentLevel);
     }
 
     return ordf.noDataSuccsess();
   }
 
   @RequestMapping(value={"getbackFoodReasons"}, produces={"application/json"})
   @ResponseBody
   public Object[] getBackFoodReason(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_AB.getCode(), "");
     List list = this.speOpReasonService.findByRestIdAndReaType(getCurrentUserRestId(), SpecialReasonTypeEnum.CANCEL_DISH_REASON.getCode());
     if ((list != null) && (list.size() > 0)) {
       return ordf.succsess(list);
     }
 
     return ordf.noDataSuccsess();
   }
 
   @RequestMapping(value={"getalltables"}, produces={"application/json"})
   @ResponseBody
   public Object[] getAllTables(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_AT.getCode(), "");
     List list = this.tableService.findByRestIdAndIsEnable(getCurrentUserRestId());
     this.tableService.flushCurrentTable(list);
     if ((list != null) && (list.size() > 0)) {
       return ordf.succsess(list);
     }
 
     return ordf.noDataSuccsess();
   }
 
   @RequestMapping(value={"getallowopentables"}, produces={"application/json"})
   @ResponseBody
   public Object[] getAllowOpenTables(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.BASIC_AT_O.getCode(), "");
     List<Table> table_list = this.tableService.findByRestIdAndIsEnable(getCurrentUserRestId());
     this.tableService.flushCurrentTable(table_list);
     List list = new ArrayList();
     for (Table table : table_list) {
       if (DinnerStatusEnum.IDLE.getCode().equalsIgnoreCase(table.getDinnerStatus())) {
         if ((TrueFalseEnum.TRUE.getCode().equalsIgnoreCase(table.getIsHasTableOrder())) && (TrueFalseEnum.FALSE.getCode().equalsIgnoreCase(table.getIsAtOrderWarnTime())))
         {
           continue;
         }
 
         list.add(table);
       }
     }
 
     if ((list != null) && (list.size() > 0)) {
       return ordf.succsess(list);
     }
 
     return ordf.noDataSuccsess();
   }
 }

