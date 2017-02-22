package com.ndlan.canyin.frontdesk.service.report.yyfx;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.repository.cygl.DishesSetCategoryDao;
import com.ndlan.canyin.base.repository.qtsy.DinerBillDao;
import com.ndlan.canyin.base.repository.qtsy.DinerBillDisheDao;
import com.ndlan.canyin.base.repository.report.yyfx.YYFXReportMyDao;
import com.ndlan.canyin.base.repository.yygl.DinerBillDisheMyDao;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.util.DateTimeUtil;
import com.ndlan.canyin.frontdesk.util.DateUtil;



@Component
@Transactional(readOnly=true)
public class YYFXReportService extends BaseService<DinerBillDao, DinerBill> {
	
	   @Autowired
	   YYFXReportMyDao yyfxReportMyDao;
	   @Autowired
	   DishesSetCategoryDao dishesSetCategoryDao;
	 
	   
	   /**
	    * 一天24小时上座率
	    * @param startDate
	    * @param restId
	    * @return
	    */
	  public List  getTimeDivSeatOccupancyRateStatszl(String startDate,  String restId)
	   {
		  
		  List<Map> list = this.yyfxReportMyDao.getTimeDivSeatOccupancyRateStat(startDate, restId);
		  Double maxValue = 0.0D;
		  String time_hour = "1";
		  Double index = 0.0;
		  if(CollectionUtils.isNotEmpty(list)){
			  if(list.get(0).get("attendance")!=null){
				  maxValue = (Double)list.get(0).get("attendance");
			  }
		  }
		  for(int i=0;i<list.size();i++){
			  Map map =(Map) list.get(i);
			 if(list.get(i).get("attendance")!=null){
				 maxValue = maxValue>(Double)list.get(i).get("attendance")?maxValue:(Double)list.get(i).get("attendance");
			 }
		  }
		  for(int i=0;i<list.size();i++){
			  Map map =(Map) list.get(i);
			 if(list.get(i).get("attendance")!=null&&maxValue==(Double)list.get(i).get("attendance")){
				 time_hour = (String)list.get(i).get("time_hour");
				 //index = (double) list.get(i).size();
			 }
		  }
		  
		  
		  List returnList = new ArrayList();
		 
		  
		  int i = 0;
		  boolean flag;
		 for(i=1;i<=24;i++){
			 flag= true;
			 Map maptem =new HashMap();
			 for(Map map:list){
				 String hour = (String) map.get("time_hour");
				 int hourint = Integer.parseInt(hour);
				 if(hourint==i){
					 maptem = map;
					 flag =false;
					break;
				 }
			  }
			 if(flag){
			
				 maptem.put("time_hour", ""+i);
				 maptem.put("attendance", 0.0);
				
				 
			 }
			 
			 returnList.add(i-1, maptem);
			 
			
		 }
		 Map temp = new HashMap();
		 if(!time_hour.equals("1")){ //如果time_hour的
			 index = (Double.valueOf(time_hour.toString()))-1;
		 }
		 
		 temp.put("max_time_hour", time_hour);
		 temp.put("max_attendance",maxValue);
		 temp.put("max_index",index);
		 returnList.add(temp);
		 return returnList;
	   }
	  /**
	   * 有起始时间的上座率，近7天的
	   * @param startDate
	   * @param endDate
	   * @param restId
	   * @return
	   */
	  public List  get7DayDivSeatOccupancyRateStat(String startDate,String endDate,  String restId)
	   {
		  List<Map> list = this.yyfxReportMyDao.get7DayDivSeatOccupancyRateStat(startDate, endDate, restId);
		  
		  Double maxValue = 0.0D;
		  String date_days = startDate;
		  Double index = 6.0;
		  if(CollectionUtils.isNotEmpty(list)){
			  if(list.get(0).get("attendance")!=null){
				  maxValue = (Double)list.get(0).get("attendance");
			  }
		  }
		  for(int i=0;i<list.size();i++){
			  Map map =(Map) list.get(i);
			 if(list.get(i).get("attendance")!=null){
				 maxValue = maxValue>(Double)list.get(i).get("attendance")?maxValue:(Double)list.get(i).get("attendance");
			 }
		  }
		  for(int i=0;i<list.size();i++){
			  Map map =(Map) list.get(i);
			 if(list.get(i).get("attendance")!=null&&maxValue==(Double)list.get(i).get("attendance")){
				 date_days = (String)list.get(i).get("date_day");
				 index= (double) list.get(i).size();
			 }
		  }
		  
		  
		  int days=0;
		  try {
			days = DateUtil.daysBetween("2015-07-20","2015-07-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  List returnList = new ArrayList();
		  int i = 0;
		  boolean flag;
		 for(i=0;i<days;i++){
			 flag= true;
			 Map maptem =new HashMap();
			 for(Map map:list){
				 String date_day = (String) map.get("date_day");
				 if(date_day.equals(startDate)){
					 maptem = map;
					 flag =false;
					 break;
				 }
			  }
			 if(flag){
				 maptem.put("date_day", startDate);
				 maptem.put("attendance", "0.0");
			 }
			 if(date_days.equals(startDate)){
				 index = (double)i;   //获得最大值在数组中的索引  
			 }
			 returnList.add(i, maptem);
			
			 startDate = DateTimeUtil.addDay(startDate, 1);
		 }
		 Map temp = new HashMap();
		 temp.put("max_date_day", date_days);
		 temp.put("max_attendance",maxValue);
		 temp.put("max_index",index);
		 returnList.add(temp);
	     return returnList;
	   }
	  
	  
	  
	  /***
	    * 营收总和
	    * @param startDate
	    * @param endDate
	    * @param restId
	    * @return
	    */
	   public List getFastfoodDinnerYingShouzongheFrontWeb(String pageType,String startDate,String endDate, String restId)
	   {
	  /*   if (StringUtils.isEmpty(endDate)) {
	       endDate = DateUtilq.toString(new Date());
	     }
	     endDate = DateUtilq.toYearMonthString(endDate);
	 
	     if (StringUtils.isEmpty(startDate)) {
	       startDate = DateUtilq.getTime4XDaysAgo(30, endDate);
	     }
	     startDate = DateUtilq.toYearMonthString(startDate);
	 */
		   	List list =this.yyfxReportMyDao.getFastfoodDinnerYingShouzongheFrontWeb(pageType,startDate,endDate, restId);
		    for(int i=0;i<list.size();i++){
				   Map map =(Map) list.get(i);
				   if(map!=null){
					   Double   f   = (Double) map.get("totalmoney"); 
					   if(f!=null){
						   BigDecimal   b   =   new   BigDecimal(f);  
						   Double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
						   map.put("totalmoney", f1+"");
					   }else{
						   map.put("totalmoney", 0.00+"");
					   }
						   Double   s   = (Double) map.get("yuding"); 
						   if(s!=null){
						   BigDecimal   b1   =   new   BigDecimal(s);  
						   Double   f2   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
						   map.put("yuding", f2+"");
					   }else{
						   map.put("yuding", 0.00+"");
					   }
					   Double   d   = (Double) map.get("waimai"); 
					   if(d!=null){
						   BigDecimal   b4   =   new   BigDecimal(d);  
						   Double   f3   =   b4.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
						   map.put("waimai", f3+"");
					   }else{
						   map.put("waimai", 0.00+"");
					   }
					   
					   Double   c   = (Double) map.get("shitang"); 
					   if(c!=null){
						   BigDecimal   b5   =   new   BigDecimal(c);  
						   Double   f5   =   b5.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
						   map.put("shitang", f5+"");
					   }else{
						   map.put("shitang", 0.00+"");
					   }
					   
					   
				   };
				   }
				  
		   
	     return list;
	   }
	   
	   /**
	    * 24小时翻台率
	    * @param startDate
	    * @param endDate
	    * @param restId
	    * @return
	    */
	   public List<Map> getDiscountStat(String startDate, String endDate, String restId)
	   {
		   List<Map> list = this.yyfxReportMyDao.getDiscountStat(startDate, endDate, restId);
		   Double maxValue = 0.0D;
			  String date_days = "1";
			  Double index = 0.0;
			  if(CollectionUtils.isNotEmpty(list)){
				  if(list.get(0).get("TableTurnoverRate")!=null){
					  maxValue =  (Double) list.get(0).get("TableTurnoverRate");
				  }
			  }
			  for(int i=0;i<list.size();i++){
				  Map map =(Map) list.get(i);
				 if(list.get(i).get("TableTurnoverRate")!=null){
					 maxValue = (Double) (maxValue>(Double)list.get(i).get("TableTurnoverRate")?maxValue:list.get(i).get("TableTurnoverRate"));
				 }
			  }
			  for(int i=0;i<list.size();i++){
				  Map map =(Map) list.get(i);
				 if(list.get(i).get("TableTurnoverRate")!=null&&maxValue==list.get(i).get("TableTurnoverRate")){
					 date_days = (String)list.get(i).get("time_hour");
					 index = (double) list.get(i).size();
				 }
			  }
			  
		   
		   List returnList = new ArrayList();
			  int i = 0;
			  boolean flag;
			 for(i=1;i<=24;i++){
				 flag= true;
				 Map maptem =new HashMap();
				 for(Map map:list){
					 Double sd = (Double) map.get("TableTurnoverRate");
					 
					
					 String hour = (String) map.get("time_hour");
					 int hourint = Integer.parseInt(hour);
					 if(hourint==i){
						 
						 maptem = map;
						 if(sd<0){
							 map.put("TableTurnoverRate",0.0);
							 
						 }
						 flag =false;
						 break;
					 }
				  }
				 if(flag){
					 maptem.put("time_hour", ""+i);
					 maptem.put("TableTurnoverRate", 0.0);
				 }
				 returnList.add(i-1, maptem);
			 }
			 Map temp = new HashMap();
			 if(maxValue<0){
				String ss= list.get(0).get("time_hour").toString();
				 
				 temp.put("max_time_hour", 1.0);
				 temp.put("max_TableTurnoverRate",0.0);
				 temp.put("max_index",0.0);
			 }else{

				 if(!date_days.equals("1")){ //如果time_hour的
					 index = (Double.valueOf(date_days.toString()))-1;
				 }
				 
				 temp.put("max_time_hour", date_days);
				 temp.put("max_TableTurnoverRate",maxValue);
				 temp.put("max_index",index);
			 }
			 
			 returnList.add(temp);
	     return returnList;
	   }
	   
	   /**
	    * 7天翻台率
	    * @param startDate
	    * @param endDate
	    * @param restId
	    * @return
	    */
	   public List<Map> get7DayDiscountStat(String startDate, String endDate, String restId)
	   {
		   List<Map> list = this.yyfxReportMyDao.get7DayDiscountStat(startDate, endDate, restId);
		   
		   Double maxValue = 0.0D;
			  String date_days = startDate;
			  Double index = 6.0;
			  if(CollectionUtils.isNotEmpty(list)){
				  if(list.get(0).get("TableTurnoverRate")!=null){
					  //maxValue = ((BigDecimal) list.get(0).get("TableTurnoverRate")).doubleValue();
					  
					  maxValue =  (Double) list.get(0).get("TableTurnoverRate");
				  }
			  }
			  for(int i=0;i<list.size();i++){
				  Map map =(Map) list.get(i);
				 if(list.get(i).get("TableTurnoverRate")!=null){
					 maxValue = maxValue>(Double)list.get(i).get("TableTurnoverRate")?maxValue:(Double)list.get(i).get("TableTurnoverRate");
				 }
			  }
			  for(int i=0;i<list.size();i++){
				  Map map =(Map) list.get(i);
				 if(list.get(i).get("TableTurnoverRate")!=null&&maxValue==(Double)list.get(i).get("TableTurnoverRate")){
					 date_days = (String)list.get(i).get("date_day");
//					 index = (double) list.get(i).size();    //
				 }
			  }
		   int days=0;
			  try {
				days = DateUtil.daysBetween("2015-07-20","2015-07-27");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			  List returnList = new ArrayList();
			  int i = 0;
			  boolean flag;
			 for(i=0;i<days;i++){
				 flag= true;
				 Map maptem =new HashMap();
				 for(Map map:list){
					 Double sd = (Double) map.get("TableTurnoverRate");
					 String date_day = (String) map.get("date_day");
					 if(date_day.equals(startDate)){
						 
						 maptem = map;
						 if(sd<0){
							 map.put("TableTurnoverRate",0.0);
							 
						 }
						 flag =false;
						 break;
					 }
				  }
				 if(flag){
					 maptem.put("date_day", startDate);
					 maptem.put("TableTurnoverRate", 0.0);
				 }
				 if(date_days.equals(startDate)){    //获得最大值在数组中的索引  
					 index = (double)i;
				 }
				 returnList.add(i, maptem);
				 startDate = DateTimeUtil.addDay(startDate, 1);
			 }
			 Map temp = new HashMap();
			 
			 if(maxValue<0){
				 String ss= list.get(list.size()-1).get("date_day").toString();
				 temp.put("max_date_day", ss);
				 temp.put("max_TableTurnoverRate",0.0);
				 temp.put("max_index",index);
			 }else{
				 
				 temp.put("max_date_day", date_days);
				 temp.put("max_TableTurnoverRate",maxValue);
				 temp.put("max_index",index);
			 }
			 
			 returnList.add(temp);
	     return returnList;
	   }
	   
	   public List getTableAreaStat(String startDate, String endDate, String restId)
	   {
	     return this.yyfxReportMyDao.getTableAreaStat(startDate, endDate, restId);
	   }
	   
	   public List getPaymentTypeStat(String startDate, String endDate, String restId)
	   {
		   List list;
	      list = this.yyfxReportMyDao.getPaymentTypeStat(startDate, endDate, restId);
	      
	      list.add(this.yyfxReportMyDao.getPaymentTypeAllTotalmoney(startDate, endDate, restId).get(0));
	     return list;
	   }
	   
	   /**
	    * 
	    * @param pageType
	    * @param startDate
	    * @param endDate
	    * @param restId
	    * @return
	    */
	   public List getPaymentTypeStats(String pageType,String startDate, String endDate, String restId)
	   {
		   List list;
	      list = this.yyfxReportMyDao.getPaymentTypeStats( pageType,startDate, endDate, restId);
	     
	      
	   
	      
	      for(int i=0;i<list.size();i++){
	    	  
	    	  Map map =(Map) list.get(i);
			   if(map!=null){
				   map.get("paymentName");
				   if(map.get("paymentName")==null&&map.get("cptIdCount")==null&&map.get("allTotalmoney")==null){
					   map.put("totalmoney", 0);
					   map.put("paymentName", 0);
					   map.put("cptIdCount", 0);
				   }
				  
				   
			   }
	    	  
	      }
	      
	      list.add(this.yyfxReportMyDao.getPaymentTypeAllTotalmoneys(pageType,startDate, endDate, restId).get(0));
	     
	      Map maps =(Map) list.get(0);
		  
	       for(int i=0;i<list.size();i++){
	    	  Map map =(Map) list.get(i);
			   if(map!=null){
				   if(map.get("paymentName")==null&&map.get("cptIdCount")==null&&map.get("allTotalmoney")==null){
					   map.put("totalmoney", 0);
					   map.put("paymentName", 0);
					   map.put("cptIdCount", 0);
				   }
				  
			   }
	    	  
	      }
	     return list;
	   }
	   
		
		 public List<DishesSetCategory> findByRestIdOrderByShowSeqAsc(String restId) {
		     return this.dishesSetCategoryDao.findByRestIdOrderByShowSeqAsc(restId);
		   }
		 
			
		   private static Logger logger = LoggerFactory.getLogger(YYFXReportService.class);
		   private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		   @Autowired
		   private DinerBillDisheMyDao dinerBillDisheMyDao;
		   @Autowired
		   private DinerBillDisheDao dinerBillDisheDao;
		
		
		   public Page getSalesByDish(String startDate, String endDate, String categoryId, String restId, String sortType, String dir, int pageNumber, int pagzSize)
		   {
		     Sort.Direction d = Sort.Direction.fromString(dir);
		     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { sortType }));
		 
		     if (!StringUtils.isEmpty(startDate)) {
		       startDate = startDate + " 00:00:00";
		     }
		     else
		     {
		       startDate = null;
		     }
		     if (!StringUtils.isEmpty(endDate)) {
		       endDate = endDate + " ";
		     }
		     else
		     {
		       endDate = null;
		     }
		     if (StringUtils.isEmpty(categoryId)) {
		       categoryId = null;
		     }
		     sortType = sortType + " " + dir;
		 
		     List l ;
		     l= this.dinerBillDisheMyDao.getSalesByDish(startDate, endDate, categoryId, restId, sortType, pageRequest.getOffset(), pageRequest.getPageSize());
		     int totalSize = this.dinerBillDisheMyDao.getSalesByDishCount(startDate, endDate, categoryId, restId).intValue();
		     Page page = new PageImpl(l, pageRequest, totalSize);
		     return page;
		   }
		 
		   public Map getTotalSalesByDish(String startDate, String endDate, String categoryId, String restId)
		   {
		     if (!StringUtils.isEmpty(startDate)) {
		       startDate = startDate + " 00:00:00";
		     }
		     else
		     {
		       startDate = null;
		     }
		     if (!StringUtils.isEmpty(endDate)) {
		       endDate = endDate + " 23:59:59";
		     }
		     else
		     {
		       endDate = null;
		     }
		     if (StringUtils.isEmpty(categoryId)) {
		       categoryId = null;
		     }
		     List list = this.dinerBillDisheMyDao.getTotalSalesByDish(startDate, endDate, categoryId, restId);
		     if ((list != null) && (list.size() > 0))
		     {
		       Map m = (Map)list.get(0);
		       return m;
		     }
		     return null;
		   }

		
		public List<Map<String,String>> getTop10(String pageType,String categoryId,String restId,String startDate , String endDate,String sorting) {
			// TODO Auto-generated method stub
			return yyfxReportMyDao.getTop10(pageType,categoryId,restId,startDate,endDate,sorting);
		}


		public List getSetTop10(String pageType,String dsId,String restId) {
			// TODO Auto-generated method stub
			return yyfxReportMyDao.getSetTop10(pageType,dsId,restId);
		}
		  
		

}
