 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.base.entity.cygl.Dishe;
 import com.ndlan.canyin.base.entity.cygl.DishesSet;
 import com.ndlan.canyin.base.entity.cygl.SpecialDishe;
 import com.ndlan.canyin.base.entity.cygl.SpecialPriceInterval;
 import com.ndlan.canyin.base.repository.cygl.SpecialDisheDao;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import com.ndlan.canyin.core.utils.DateProvider;
 import java.math.BigDecimal;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class SpecialDisheServie extends BaseService<SpecialDisheDao, SpecialDishe>
 {
   public Page<SpecialDishe> search(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType, String restId)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.ASC, new String[] { "showSeq" }));
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), SpecialDishe.class);
 
     return ((SpecialDisheDao)getDao()).findAll(spec, pageRequest);
   }
 
   public List<SpecialDishe> getAllSpecialDisheByRestId(String restId) {
     return ((SpecialDisheDao)getDao()).findByRestIdOrderByShowSeqAsc(restId);
   }
 
   public List<SpecialDishe> findByDishe(Dishe dishe, String restId) {
     return ((SpecialDisheDao)getDao()).findByDisheAndRestId(dishe, restId);
   }
 
   public BigDecimal getSpecialPrice(Dishe dishe, String restId)
   {
     BigDecimal price = dishe.getPrice();
 
     List<SpecialDishe> specialDishes = findByDishe(dishe, restId);
     if (specialDishes != null)
     {
       for (SpecialDishe specialDishe : specialDishes) {
         if (isInSpecialInterval(specialDishe.getSpecialPriceInterval()))
         {
           price = specialDishe.getSpecialPrice();
           return price;
         }
       }
     }
     return price;
   }
 
   public boolean isInSpecialInterval(SpecialPriceInterval specialPriceInterval)
   {
     String is_time_limit = specialPriceInterval.getIsTimeLimit();
     if (TrueFalseEnum.FALSE.getCode().equals(is_time_limit))
     {
       return true;
     }
 
     String isDateValid = specialPriceInterval.getIsDateValid();
     if (TrueFalseEnum.TRUE.getCode().equals(isDateValid))
     {
       Date now = DateProvider.DEFAULT.getDate();
       Date start_date = specialPriceInterval.getStartDate();
       Date end_date = specialPriceInterval.getEndDate();
       if ((!now.after(start_date)) || (!now.before(end_date)))
       {
         return false;
       }
 
     }
 
     String is_time_valid = specialPriceInterval.getIsTimeValid();
     if (TrueFalseEnum.TRUE.getCode().equals(is_time_valid))
     {
       Date now = DateProvider.DEFAULT.getDate();
       String start_time = specialPriceInterval.getStartTime();
       String end_time = specialPriceInterval.getEndTime();
       Date start_date = DateUtil.toDateTime(start_time);
       Date end_date = DateUtil.toDateTime(end_time);
       if ((!now.after(start_date)) || (!now.before(end_date)))
       {
         return false;
       }
     }
 
     String is_special_day = specialPriceInterval.getIsSpecialDay();
     if (TrueFalseEnum.TRUE.getCode().equals(is_special_day))
     {
       Date now = DateProvider.DEFAULT.getDate();
       int weekDay = getWeekOfDate(now);
 
       switch (weekDay)
       {
       case 1:
         String is_monday = specialPriceInterval.getIsMonday();
         if (TrueFalseEnum.TRUE.getCode().equals(is_monday))
           break;
         return false;
       case 2:
         String is_tuesday = specialPriceInterval.getIsTuesday();
         if (TrueFalseEnum.TRUE.getCode().equals(is_tuesday))
           break;
         return false;
       case 3:
         String is_wednesday = specialPriceInterval.getIsWednesday();
         if (TrueFalseEnum.TRUE.getCode().equals(is_wednesday))
           break;
         return false;
       case 4:
         String is_thursday = specialPriceInterval.getIsThursday();
         if (TrueFalseEnum.TRUE.getCode().equals(is_thursday))
           break;
         return false;
       case 5:
         String is_friday = specialPriceInterval.getIsFriday();
         if (TrueFalseEnum.TRUE.getCode().equals(is_friday))
           break;
         return false;
       case 6:
         String is_saturday = specialPriceInterval.getIsSaturday();
         if (TrueFalseEnum.TRUE.getCode().equals(is_saturday))
           break;
         return false;
       case 7:
         String is_sunday = specialPriceInterval.getIsSunday();
         if (TrueFalseEnum.TRUE.getCode().equals(is_sunday))
           break;
         return false;
       }
 
     }
 
     return true;
   }
 
   public int getWeekOfDate(Date dt)
   {
     int[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
     Calendar cal = Calendar.getInstance();
     cal.setTime(dt);
 
     int w = cal.get(7) - 1;
     if (w < 0) {
       w = 0;
     }
     return weekDays[w];
   }
 
   public List<SpecialDishe> findByDishesSetAndRestId(DishesSet dishesSet, String restId)
   {
     return ((SpecialDisheDao)getDao()).findByDishesSetAndRestId(dishesSet, restId);
   }
   @Autowired
   public void setBaseDao(SpecialDisheDao specialDisheDao) {
     super.setDao(specialDisheDao);
   }
 }

