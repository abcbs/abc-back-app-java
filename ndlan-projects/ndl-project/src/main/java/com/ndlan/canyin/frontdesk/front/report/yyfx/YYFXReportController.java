package com.ndlan.canyin.frontdesk.front.report.yyfx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.common.Constantsq;
import com.ndlan.canyin.frontdesk.service.report.yyfx.YYFXReportService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDishesSetService;
import com.ndlan.canyin.frontdesk.util.DateUtil;


@Controller
@RequestMapping({"/report/yyfx"})
@Lazy
public class YYFXReportController extends BaseManageController {
	
	  @Autowired
	   YYFXReportService yyfxReportService;
	  
	  
	  /**
	   * 
	   * @param model
	   * @param pageType    时间类型      
	   * @param categoryId   菜肴菜系id
	   * @param dsId         套餐菜系id
	   * @param endDate
	   * @param startDate
	   * sorting=asc正序    sorting= desc  倒序
	   */
	  @RequestMapping({"iosList"})
	  public void getIosList(Model model,String pageType,String sorting,String categoryId,String dsId,String endDate, String startDate ){
		  List<Map<String,String>> dinerBillDishe=null;
//		  List<Map<String,String>> dinerBillDisheSet=null;
		  
		
	   
		 if(startDate!=""&&endDate!=""&&startDate!=null&&endDate!=null){
			 if(startDate.equals(endDate)){
				  pageType="today";
				  endDate=null; startDate=null;
			  }
			 
		 }
		  
		  
		  
		  dinerBillDishe= yyfxReportService.getTop10(pageType,categoryId,getCurrentUserRestId(),startDate,endDate,sorting);
		  //下面注释代码请保留
		  
		  /*// getTop10 是获得普通菜肴    getSetTop10 是获得套餐  
		  if(categoryId==""&&dsId==""){
			   dinerBillDishe= yyfxReportService.getTop10(pageType,null,getCurrentUserRestId(),startDate,endDate);
			   dinerBillDisheSet= yyfxReportService.getSetTop10(pageType,null,getCurrentUserRestId());
		  }else if(categoryId!=""){     //现在只需要做普通菜品 
			  //当startDate 和 endDate 有值时    pageType的值为空    。。当pageType有值的时候    startDate 和 endDate 为空
			   dinerBillDishe= yyfxReportService.getTop10(pageType,categoryId,getCurrentUserRestId(),startDate,endDate);
		  }else if(dsId!=""){
			   dinerBillDisheSet= yyfxReportService.getSetTop10(pageType,dsId,getCurrentUserRestId());
		  }*/
		  
		 model.addAttribute("dinerBillDishe", dinerBillDishe);
//		 model.addAttribute("dinerBillDisheSet", dinerBillDisheSet);
		  
		  
	  }
	  /**
	    * 上座率 = 来店人数÷总餐位数×100%
	    * @param endDate
	    * @param startDate
	    * @param viewFlag
	    * @param model
	    * @param request
	    * @param response
	    * @param work
	    * @param mod
	    * @return
	    * @throws Exception
	    */
	   @RequestMapping({"timeDivSeatOccupancyRateStatList"})
	   public List timeDivSeatOccupancyRateStatList(String endDate, String startDate, String viewFlag, Model model, ServletRequest request, HttpServletResponse response, @RequestParam(value="work", defaultValue="") String work, @RequestParam(value="monthOrDay", defaultValue="month") String mod)
	     throws Exception
	   {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		  
	     List sit_list;
	     if(startDate=="" && startDate==""){
		     if(viewFlag.equals("today")){
			     sit_list = this.yyfxReportService.getTimeDivSeatOccupancyRateStatszl(df.format(new Date()),  getCurrentUserRestId());
		     }else{
		    	   Calendar   c   =   Calendar.getInstance(); 
				   c.add(Calendar.DAY_OF_MONTH,-6);//7天前.... 
			     sit_list = this.yyfxReportService.get7DayDivSeatOccupancyRateStat(df.format(c.getTime()),df.format(new Date()),  getCurrentUserRestId());
		     }
	     }else{
	    	 sit_list = this.yyfxReportService.get7DayDivSeatOccupancyRateStat(startDate,endDate,  getCurrentUserRestId());
	     }    
	     model.addAttribute("startDate", startDate);
		   model.addAttribute("endDate", startDate);
//	    model.addAttribute("sit_list", sit_list);
	     return sit_list;
	   }
	   
	   
	   /***
	    * 营收总和
	    * @param endDate
	    * @param startDate
	    * @param model
	    * @param request
	    * @return
	    * @throws Exception
	    * @name:huojianxiao
	    */

	   @RequestMapping({"fastfoodDinnerYingShouzonghe"})
	   public String fastfoodDinnerYingShouzonghe(String pageType,String startDate,String endDate,  Model model, HttpServletRequest request) throws Exception {
//		   String[] date = DateUtil.getStartAndEdnDate(startDate, endDate);
//		   model.addAttribute("startDate", startDate);
//		   model.addAttribute("endDate", startDate);
		   if(startDate!=""&&endDate!=""&&startDate!=null&&endDate!=null){
				 if(startDate.equals(endDate)){
					  pageType="today";
					  endDate=null; startDate=null;
				  }
				 
			 }
	     List list;
	     list= this.yyfxReportService.getFastfoodDinnerYingShouzongheFrontWeb(pageType,startDate,endDate, getCurrentUserRestId());
	     model.addAttribute("list", list);
	     
	     return "report/yyfx/fastfoodDinnerYingShouzonghe_dataTableMonth";
	   }
	   
	   
	   /**
	    * 翻台率
	    * @param endDate
	    * @param startDate
	    * @param viewFlag
	    * @param model
	    * @param request
	    * @param response
	    * @param work
	    * @param mod
	    * @return
	    * @throws Exception
	    */
	   @RequestMapping({"fastfoodDinnerfanftailulist"})
	   public List fastfoodDinnerfanftailulist(String endDate, String startDate, String viewFlag, Model model, ServletRequest request, HttpServletResponse response, @RequestParam(value="work", defaultValue="") String work, @RequestParam(value="monthOrDay", defaultValue="month") String mod)
	     throws Exception
	   {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	     
	     List fast_list;
	     if(startDate=="" && startDate==""){
		     if( viewFlag.equals("today")){  //当天数据
			     fast_list = this.yyfxReportService.getDiscountStat(df.format(new Date()), "", getCurrentUserRestId());
		     }else{
		    	 Calendar   c   =   Calendar.getInstance(); 
				   c.add(Calendar.DAY_OF_MONTH,-6);//7天前.... 
		    	 fast_list = this.yyfxReportService.get7DayDiscountStat(df.format(c.getTime()),df.format(new Date()), getCurrentUserRestId());
		     }
	     }
	     else{    //按开始时间，结束时间查询
	    	
	    		fast_list = this.yyfxReportService.get7DayDiscountStat(startDate,endDate, getCurrentUserRestId());
	    	 
	    	// fast_list = this.yyfxReportService.getDiscountStat(df.format(new Date()), "", getCurrentUserRestId());
	    		 
	    	 
	     }  

	     return fast_list;
	   }
	   
	   /**
	    * 月平均翻台率 = (月餐桌使用次数-（总台位数×2餐×30日）)×100% / 总台位数×2餐×30日
	    * @param endDate
	    * @param startDate
	    * @param viewFlag
	    * @param model
	    * @param request
	    * @param response
	    * @param work
	    * @param mod
	    * @return
	    * @throws Exception
	    */
	   
	   @RequestMapping({"fastfoodDinnerfanftailuyuelist"})
	   public List fastfoodDinnerfanftailuyuelist(String endDate, String startDate, String viewFlag, Model model, ServletRequest request, HttpServletResponse response, @RequestParam(value="work", defaultValue="") String work, @RequestParam(value="monthOrDay", defaultValue="month") String mod)
	     throws Exception
	   {
	     String[] date = DateUtil.getStartAndEdnDate(startDate, endDate);
	     model.addAttribute("startDate", date[0]);
	     model.addAttribute("endDate", date[1]);
	     model.addAttribute("viewFlag", viewFlag);
	     List fast_list;
	     fast_list = this.yyfxReportService.getTableAreaStat(date[0], date[1], getCurrentUserRestId());


	     return fast_list;
	   }
	   
	   
	   /**
	    * 支付方式
	    * @param endDate
	    * @param startDate
	    * @param viewFlag
	    * @param model
	    * @param request
	    * @param response
	    * @param work
	    * @param mod
	    * @return
	    * @throws Exception
	    */
	   
	   @RequestMapping({""
	   		+ ""
	   		+ ""})
	   public List paymentTypeStatList(String endDate, String startDate, String viewFlag, Model model, ServletRequest request, HttpServletResponse response, @RequestParam(value="work", defaultValue="") String work, @RequestParam(value="monthOrDay", defaultValue="month") String mod)
	     throws Exception
	   {
	     String[] date = DateUtil.getStartAndEdnDate(startDate, endDate);
	     model.addAttribute("startDate", date[0]);
	     model.addAttribute("endDate", date[1]);
	     model.addAttribute("viewFlag", viewFlag);
	     List fast_list;
	     fast_list = this.yyfxReportService.getPaymentTypeStat(startDate,endDate, getCurrentUserRestId());

	     model.addAttribute("pay_type", fast_list);
	     return fast_list;
	   }
	   
	   /**
	    *修改 支付方式------------------
	    * @param endDate
	    * @param startDate
	    * @param viewFlag
	    * @param model
	    * @param request
	    * @param response
	    * @param work
	    * @param mod
	    * @return
	    * @throws Exception
	    */
	   
	   @RequestMapping({"paymentTypeStatLists"})
	   public List paymentTypeStatLists(String pageType,String endDate, String startDate, String viewFlag, Model model, ServletRequest request, HttpServletResponse response, @RequestParam(value="work", defaultValue="") String work, @RequestParam(value="monthOrDay", defaultValue="month") String mod)
	     throws Exception
	   {
	    /* String[] date = DateUtil.getStartAndEdnDate(startDate, endDate);
	     model.addAttribute("startDate", date[0]);
	     model.addAttribute("endDate", date[1]);*/
	     model.addAttribute("viewFlag", viewFlag);
	     if(startDate!=""&&endDate!=""&&startDate!=null&&endDate!=null){
			 if(startDate.equals(endDate)){
				  pageType="today";
				  endDate=null; startDate=null;
			  }
			 
		 }
	     
	     List fast_list;
	     fast_list = this.yyfxReportService.getPaymentTypeStats(pageType,startDate,endDate, getCurrentUserRestId());

	     model.addAttribute("pay_type", fast_list);
	     return fast_list;
	   }
	   
	   /**
	    * 菜肴排行
	    * @param model
	    * @param request
	    * @param response
	    * @param endDate
	    * @param startDate
	    * @param categoryId
	    * @param sortType
	    * @param direction
	    * @param tabNo
	    * @param orderby
	    * @param pageNumber
	    * @param pageSize
	    * @param work
	    * @return
	    * @throws Exception
	    */
	   @RequestMapping({"salesByDish"})
	   public String salesByDish(Model model, ServletRequest request, HttpServletResponse response, @RequestParam(value="endDate", defaultValue="") String endDate, @RequestParam(value="startDate", defaultValue="") String startDate, @RequestParam(value="categoryId", defaultValue="") String categoryId, @RequestParam(value="sortType", defaultValue="salesNum") String sortType, @RequestParam(value="direction", defaultValue="desc") String direction, @RequestParam(value="tabNo", defaultValue="0") String tabNo, @RequestParam(value="orderby", defaultValue="1") String orderby, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize, @RequestParam(value="work", defaultValue="") String work)
	     throws Exception
	   {
	     String[] date = DateUtil.getStartAndEdnDate(startDate, endDate);
	     startDate = date[0];
	     endDate = date[1];
	     pageSize = Constantsq.PAGE_SIZE;
	     if ("exportExcel".equalsIgnoreCase(work)) {
	       pageSize = Constantsq.PAGE_SIZE_EXCEL;
	       pageNumber = 1;
	     }
	     if (orderby.equals("1"))
	     {
	       sortType = "salesNum";
	       direction = "desc";
	     }
	     else if (orderby.equals("2"))
	     {
	       sortType = "salesNum";
	       direction = "asc";
	     }
	     else if (orderby.equals("3"))
	     {
	       sortType = "salesOriCost";
	       direction = "desc";
	     }
	     else if (orderby.equals("4"))
	     {
	       sortType = "salesOriCost";
	       direction = "asc";
	     }
	     Page salesList;
	     salesList= this.yyfxReportService.getSalesByDish(startDate, endDate, categoryId, getCurrentUserRestId(), sortType, direction, pageNumber, pageSize);
	     model.addAttribute("salesList", salesList);
	     List dishesCategorys;
	     dishesCategorys= this.yyfxReportService.findByRestIdOrderByShowSeqAsc(getCurrentUserRestId());
	     List dishesSetCategorys;
	     dishesSetCategorys= this.yyfxReportService.findByRestIdOrderByShowSeqAsc(getCurrentUserRestId());
	     model.addAttribute("dishesCategorys", dishesCategorys);
	     model.addAttribute("dishesSetCategorys", dishesSetCategorys);
	 
	     model.addAttribute("startDate", startDate);
	     model.addAttribute("endDate", endDate);
	     model.addAttribute("categoryId", categoryId);
	     model.addAttribute("tabNo", tabNo);
	 
	     model.addAttribute("sortType", sortType);
	     model.addAttribute("direction", direction);
	     model.addAttribute("orderby", orderby);
	 
	     String searchParams = "startDate=" + startDate + "&endDate=" + endDate + "&categoryId=" + categoryId + "&orderby=" + orderby + "";
	     model.addAttribute("searchParams", searchParams);
	     model.addAttribute("startDate", date[0]);
	     model.addAttribute("endDate", date[1]);
	 
	     Map totalMap;
	     totalMap= this.yyfxReportService.getTotalSalesByDish(startDate, endDate, categoryId, getCurrentUserRestId());
	     if (totalMap != null)
	     {
	       model.addAttribute("totalSalesNum", totalMap.get("salesNum"));
	 
	       model.addAttribute("totalSalesOriCost", totalMap.get("salesOriCost"));
	 
	       model.addAttribute("totalSalesRealCost", totalMap.get("salesRealCost"));
	     }
	    
	     return "yygl/salesByDish";
	   }

}
