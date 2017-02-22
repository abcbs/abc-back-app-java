 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableBillRelationService;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm.ShiroUser;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Role;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.repository.ctzh.TableDao;
import com.ndlan.canyin.base.repository.mybatis.EmployeeMyDao;
import com.ndlan.canyin.core.common.DinnerStatusEnum;
import com.ndlan.canyin.core.common.EnableStatusEnum;
import com.ndlan.canyin.core.common.OrderStatusEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TabBillTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.persistence.DynamicSpecifications;
import com.ndlan.canyin.core.persistence.SearchFilter;
import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
import com.ndlan.canyin.core.utils.DateProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class TableService extends BaseService<TableDao, Table>
 {
   private TableDao tableDao;
 
   @Autowired
   private EmployeeMyDao employeeMyDao;
 
   @Autowired
   private EmployeeService employeeService;
 
   @Autowired
   private TableBillRelationService tableBillRelationService;
 
   @Autowired
   public void setBaseDao(TableDao tableDao)
   {
     super.setDao(tableDao);
     this.tableDao = tableDao;
   }
 
   public Page<Table> searchByFilter(String restId, String tabId, Map<String, Object> searchParams, int pageNumber, int pagzSize, String searchType, String[] sortType)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.ASC, sortType));
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     filters.put("isEnable", new SearchFilter("isEnable", SearchFilter.Operator.EQ, TrueFalseEnum.TRUE.getCode()));
     if (StringUtils.isNotEmpty(tabId)) {
       filters.put("tabId", new SearchFilter("tabId", SearchFilter.Operator.NEQ, tabId));
     }
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), Table.class);
     return this.tableDao.findAll(spec, pageRequest);
   }
 
   public Page<Table> searchBySql(String restId, String tabId, Map<String, Object> searchParams, int pageNumber, int pagzSize, String searchType, String[] sortType,String orderTime)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.ASC, sortType));
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
 
     String isHasAllArea = null;
     Employee employee = (Employee)this.employeeService.loadOne(user.id);
     List<Role> roles = employee.getRoleList();
     for (Role r : roles)
     {
       if (TrueFalseEnum.TRUE.getCode().equals(r.getIsAllTablearea())) {
         isHasAllArea = TrueFalseEnum.TRUE.getCode();
         break;
       }
     }
     String areaId = (String)searchParams.get("EQ_tableArea.areaId");
     if (StringUtils.isEmpty(areaId))
     {
       areaId = null;
     }
     String dinnerStatus = (String)searchParams.get("EQ_dinnerStatus");
     if (StringUtils.isEmpty(dinnerStatus))
     {
       dinnerStatus = null;
     }
 
     String tabNo = (String)searchParams.get("LIKE_tabNo");
     if (StringUtils.isEmpty(tabNo))
     {
       tabNo = null;
     }
     List<Table> tables =null;
     int totalSize =0;
     if( orderTime!="" && orderTime !=null && orderTime.length()>0 ){

    	 //截取日期字符串
    	 String mmm=orderTime.substring(0,10);
		 
    	 Date date=DateUtil.toDate(orderTime);
		 int hour = date.getHours();
		 //时间
		 Date dates = new Date();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 df.format(dates);
		 
		 Date datea=DateUtil.toDate(df.format(dates));
		 int hours = datea.getHours();
		 //预定时段的限定
		// if(hour>=hours+2){
			 List<TableOrder> timeList=null;
			 //餐桌有订订单
			// timeList=this.tableOrderService.findOrdertimezhou(table.getTabId(), getCurrentUserRestId(),mmm);
//			 上午
			 String datebegin=null;
			 String dateend=null;
			 if(hour>=0&&hour<=12)
			 {
				 datebegin=mmm+" 00:00:00";
				 dateend=mmm+" 12:00:00";
			 }
//			 下午
			 if(hour<=24&&hour>=12){
				 datebegin=mmm+" 12:00:00";
				 dateend=mmm+" 23:59:59";
			 }
     
    	 tables = this.employeeMyDao.getOrderAllTable(restId, areaId, dinnerStatus, pageRequest.getOffset(), pageRequest.getPageSize(), isHasAllArea, user.id, tabNo,datebegin,dateend);
//         totalSize = this.employeeMyDao.getOrderAllTableCount(restId, areaId, dinnerStatus, isHasAllArea, user.id, tabNo,orderTime,datebegin,dateend);
     }else{
        tables = this.employeeMyDao.getAllTable(restId, areaId, dinnerStatus, pageRequest.getOffset(), pageRequest.getPageSize(), isHasAllArea, user.id, tabNo);
        totalSize = this.employeeMyDao.getAllTableCount(restId, areaId, dinnerStatus, isHasAllArea, user.id, tabNo);
     }
     for (Table e : tables)
     {
       List tableBillRelations = this.tableBillRelationService.findByTable(e);
       e.setTableBillRelations(tableBillRelations);
     }
     Page page = new PageImpl(tables, pageRequest, totalSize);
 
     return page;
   }
 
   public Page<Table> moblieSearch(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, getRequiredSort(sortType));
     Map filters = SearchFilter.parse(searchParams);
     Class clazz = (Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), clazz);
     return this.tableDao.findAll(spec, pageRequest);
   }
 
   public List<Table> findByRestId(String restId)
   {
     return this.tableDao.findByRestId(restId);
   }
   public List<Table> findByRestIdAndIsEnable(String restId) {
     return this.tableDao.findByRestIdAndIsEnable(restId, EnableStatusEnum.NORMAL.getCode());
   }
 
   public Table findByTabNoAndRestIdAndIsEnable(String tabNo, String restId)
   {
     List ts = this.tableDao.findByTabNoAndRestIdAndIsEnable(tabNo, restId, EnableStatusEnum.NORMAL.getCode());
     if ((ts != null) && (ts.size() > 0))
     {
       return (Table)ts.get(0);
     }
     return null;
   }
 
   private void flushTableOrderStatus(List<Table> tableList)
   {
     if ((tableList == null) || (tableList.size() == 0)) {
       return;
     }
     Date now = DateProvider.DEFAULT.getDate();
     int orderWarnTime = UserSettingCache.getInstance().orderWarnTime;
     int orderLockTime = UserSettingCache.getInstance().orderLockTime;
     int orderExpireTime = UserSettingCache.getInstance().orderExpireTime;
 
     for (Table table : tableList)
     {
       table.setIsHasTableOrder(TrueFalseEnum.FALSE.getCode());
       table.setIsAtOrderWarnTime(TrueFalseEnum.FALSE.getCode());
       table.setIsAtOrderLockTime(TrueFalseEnum.FALSE.getCode());
       table.setIsAtOrderExpireTime(TrueFalseEnum.FALSE.getCode());
       List<TableBillRelation> tableBillRelationList = this.tableBillRelationService.findByTable(table);
       if ((tableBillRelationList == null) || (tableBillRelationList.size() == 0))
       {
         continue;
       }
 
       table.setLastedTableOrderBillRelation(null);
       for (TableBillRelation tableBillRelation : tableBillRelationList)
       {
         if (!TabBillTypeEnum.YUDING.getCode().equalsIgnoreCase(tableBillRelation.getTabBillType())) {
           continue;
         }
         TableOrder tableOrder = tableBillRelation.getTableOrder();
         if ((tableOrder == null) || (tableOrder.getOrderTime() == null)) {
           continue;
         }
         if (!OrderStatusEnum.APPLING.getCode().equalsIgnoreCase(tableOrder.getOrderStatus())) {
           continue;
         }
         long min = DateUtil.getMinutes(tableOrder.getOrderTime(), now).longValue();
         if (min > orderExpireTime) {
           continue;
         }
         if ((table.getLastedTableOrderBillRelation() != null) && 
           (table.getLastedTableOrderBillRelation().getTableOrder() != null) && 
           (table.getLastedTableOrderBillRelation().getTableOrder().getOrderTime() != null) && 
           (table.getLastedTableOrderBillRelation().getTableOrder().getOrderTime().before(tableOrder.getOrderTime())))
         {
           continue;
         }
 
         table.setLastedTableOrderBillRelation(tableBillRelation);
 
         table.setIsHasTableOrder(TrueFalseEnum.FALSE.getCode());
         table.setIsAtOrderWarnTime(TrueFalseEnum.FALSE.getCode());
         table.setIsAtOrderLockTime(TrueFalseEnum.FALSE.getCode());
         table.setIsAtOrderExpireTime(TrueFalseEnum.FALSE.getCode());
 
         if ((orderWarnTime > orderLockTime) && 
           (min < 0L) && (min * -1L <= orderWarnTime)) {
           table.setIsHasTableOrder(TrueFalseEnum.TRUE.getCode());
           table.setIsAtOrderWarnTime(TrueFalseEnum.TRUE.getCode());
         }
 
         if ((min < 0L) && (min * -1L <= orderLockTime)) {
           table.setIsHasTableOrder(TrueFalseEnum.TRUE.getCode());
           table.setIsAtOrderWarnTime(TrueFalseEnum.FALSE.getCode());
           table.setIsAtOrderLockTime(TrueFalseEnum.TRUE.getCode());
           table.setIsAtOrderExpireTime(TrueFalseEnum.FALSE.getCode());
         }
         if ((min >= 0L) && (min <= orderExpireTime)) {
           table.setIsHasTableOrder(TrueFalseEnum.TRUE.getCode());
           table.setIsAtOrderWarnTime(TrueFalseEnum.FALSE.getCode());
           table.setIsAtOrderLockTime(TrueFalseEnum.FALSE.getCode());
           table.setIsAtOrderExpireTime(TrueFalseEnum.TRUE.getCode());
         }
       }
     }
   }
 
   public void flushCurrentTable(List<Table> tableList)
   {
     flushTableOrderStatus(tableList);
 
     for (Table table : tableList)
     {
       if ((DinnerStatusEnum.IDLE.getCode().equalsIgnoreCase(table.getDinnerStatus())) || (DinnerStatusEnum.ORDER.getCode().equalsIgnoreCase(table.getDinnerStatus()))) {
         table.setCurrentTableBillRelation(table.getLastedTableOrderBillRelation());
         if(null!=table.getLastedTableOrderBillRelation()){
	         if(null!=table.getLastedTableOrderBillRelation().getDinerBill()){
	        	 if(null!=table.getLastedTableOrderBillRelation().getDinerBill().getIsChedan()){
	        		 table.setIsChedan(table.getLastedTableOrderBillRelation().getDinerBill().getIsChedan());
	        	 }
	         }
         }
       }
       else
       {
         TableBillRelation lastedTableNormalBillRelation = getLastedTableNormalBillRelation(table);
         table.setCurrentTableBillRelation(lastedTableNormalBillRelation);
         if(lastedTableNormalBillRelation!=null){
        	 if(null!=lastedTableNormalBillRelation.getDinerBill()){
            	 if(null!=lastedTableNormalBillRelation.getDinerBill().getIsChedan()){
            		 table.setIsChedan(lastedTableNormalBillRelation.getDinerBill().getIsChedan());
            	 }
             }
         }
       }
     }
   }
 
   public TableBillRelation getLastedTableNormalBillRelation(Table table)
   {
     TableBillRelation lastedTableNormalBillRelation = null;
     List<TableBillRelation> tableBillRelations = this.tableBillRelationService.findByTable(table);
     if ((tableBillRelations != null) && (tableBillRelations.size() > 0)) {
       for (TableBillRelation tableBillRelation : tableBillRelations) {
         if ((!tableBillRelation.getTabBillType().equalsIgnoreCase(TabBillTypeEnum.ORDER.getCode())) || (
           (lastedTableNormalBillRelation != null) && (!lastedTableNormalBillRelation.getBillTime().after(tableBillRelation.getBillTime())))) continue;
         lastedTableNormalBillRelation = tableBillRelation;
       }
 
     }
 
     table.setCurrentTableBillRelation(lastedTableNormalBillRelation);
     table.setLastedTableNormalBillRelation(lastedTableNormalBillRelation);
     return lastedTableNormalBillRelation;
   }
 
   public List<TableBillRelation> getCurrentTableOrderBillRelationList(Table table)
   {
     List orders = null;
     List<TableBillRelation> tableBillRelations = this.tableBillRelationService.findByTable(table);
     if ((tableBillRelations != null) && (tableBillRelations.size() > 0))
     {
       orders = new ArrayList();
       for (TableBillRelation e : tableBillRelations)
       {
         if (!e.getTabBillType().equals(TabBillTypeEnum.YUDING.getCode()))
           continue;
         orders.add(e);
       }
 
       return orders;
     }
     return orders;
   }
 
   @Transactional(readOnly=true)
   public boolean isLock(String tabId)
   {
     Table table = (Table)getOne(tabId);
     List tableList = new ArrayList();
     tableList.add(table);
     flushCurrentTable(tableList);
     String isAtOrderLocakTime = table.getIsAtOrderLockTime();
 
     return !isAtOrderLocakTime.equals(TrueFalseEnum.FALSE.getCode());
   }
 }

