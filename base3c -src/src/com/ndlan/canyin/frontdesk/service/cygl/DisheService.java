 package com.ndlan.canyin.frontdesk.service.cygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.Dishe;
 import com.ndlan.canyin.base.entity.cygl.DishesSet;
 import com.ndlan.canyin.base.repository.cygl.DisheDao;
 import com.ndlan.canyin.base.repository.cygl.DishesCategoryDao;
 import com.ndlan.canyin.base.repository.cygl.DishesMaterialDao;
 import com.ndlan.canyin.base.repository.cygl.DishesPicDao;
 import com.ndlan.canyin.base.repository.cygl.DishesSetDao;
 import com.ndlan.canyin.base.repository.cygl.DishesStyleDao;
 import com.ndlan.canyin.base.repository.cygl.DishesTasteDao;
 import com.ndlan.canyin.base.repository.cygl.DishesUnitDao;
 import com.ndlan.canyin.base.repository.mybatis.DinerBillDishMyDao;
 import com.ndlan.canyin.base.repository.mybatis.DinerBillMyDao;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.HashMap;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageImpl;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.data.jpa.domain.Specifications;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class DisheService extends BaseService<DisheDao, Dishe>
 {
 
   @Autowired
   DishesCategoryDao dishesCategoryDao;
 
   @Autowired
   DishesStyleDao dishesStyleDao;
 
   @Autowired
   DishesTasteDao dishesTasteDao;
 
   @Autowired
   DishesUnitDao dishesUnitDao;
 
   @Autowired
   DishesMaterialDao dishesMaterialDao;
 
   @Autowired
   DishesPicDao dishesPicDao;
 
   @Autowired
   DinerBillMyDao dinerBillMyDao;
 
   @Autowired
   DinerBillDishMyDao dinerBillDishMyDao;
 
   @Autowired
   DishesSetService dishesSetService;
 
   @Autowired
   DishesSetDao dishesSetDao;
   
   public Page<Dishe> iosDishe(String restId, String categoryId, String dsCategoryId, String keywords, String estimateStatus, String isTakeout, int pageNumber, int pagzSize, String orderBy)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { "showSeq" }));
     if (StringUtils.isEmpty(estimateStatus))
     {
       estimateStatus = null;
     }
     if (StringUtils.isEmpty(categoryId))
     {
       categoryId = null;
     }
     if (StringUtils.isEmpty(keywords))
     {
       keywords = null;
     }
     if (StringUtils.isEmpty(dsCategoryId))
     {
       dsCategoryId = null;
     }
     if (StringUtils.isEmpty(isTakeout))
     {
       isTakeout = null;
     }
     List list = null;
     int totalSize = 0;
 
     if ((categoryId == null) && (dsCategoryId == null))
     {
       list = this.dinerBillMyDao.getALLDishesAndSet(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getALLDishesAndSetCount(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout);
     }
     else if ((categoryId != null) && (dsCategoryId == null))
     {
       list = this.dinerBillMyDao.getDishes(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getDishesCount(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout);
     }
     else if ((categoryId == null) && (dsCategoryId != null))
     {
       list = this.dinerBillMyDao.getSet(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getSetCount(restId, categoryId, dsCategoryId, keywords, isTakeout, estimateStatus);
     }
 
     Page page = new PageImpl(list, pageRequest, totalSize);
     return page;
   }
   
   public Page<Dishe> searchDishe(String restId, String categoryId, String dsCategoryId, String keywords, String estimateStatus, String isTakeout, int pageNumber, int pagzSize, String orderBy)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { "showSeq" }));
     if (StringUtils.isEmpty(estimateStatus))
     {
       estimateStatus = null;
     }
     if (StringUtils.isEmpty(categoryId))
     {
       categoryId = null;
     }
     if (StringUtils.isEmpty(keywords))
     {
       keywords = null;
     }
     if (StringUtils.isEmpty(dsCategoryId))
     {
       dsCategoryId = null;
     }
     if (StringUtils.isEmpty(isTakeout))
     {
       isTakeout = null;
     }
     List list = null;
     int totalSize = 0;
 
     if ((categoryId == null) && (dsCategoryId == null))
     {
       list = this.dinerBillMyDao.getALLDishesAndSet(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getALLDishesAndSetCount(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout);
     }
     else if ((categoryId != null) && (dsCategoryId == null))
     {
       list = this.dinerBillMyDao.getDishes(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getDishesCount(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout);
     }
     else if ((categoryId == null) && (dsCategoryId != null))
     {
       list = this.dinerBillMyDao.getSet(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getSetCount(restId, categoryId, dsCategoryId, keywords, isTakeout, estimateStatus);
     }
 
     Page page = new PageImpl(list, pageRequest, totalSize);
     return page;
   }
 
   public Page<Dishe> estimateSearchDishe(String restId, String categoryId, String dsCategoryId, String keywords, String estimateStatus, String isTakeout, int pageNumber, int pagzSize, String orderBy)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { "showSeq" }));
     if (StringUtils.isEmpty(estimateStatus))
     {
       estimateStatus = null;
     }
     if (StringUtils.isEmpty(categoryId))
     {
       categoryId = null;
     }
     if (StringUtils.isEmpty(keywords))
     {
       keywords = null;
     }
     if (StringUtils.isEmpty(dsCategoryId))
     {
       dsCategoryId = null;
     }
     if (StringUtils.isEmpty(isTakeout))
     {
       isTakeout = null;
     }
     List list = null;
     int totalSize = 0;
 
     if ((categoryId == null) && (dsCategoryId == null))
     {
       list = this.dinerBillMyDao.getALLDishesAndSet(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getALLDishesAndSetCount(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout);
     }
     else if ((categoryId != null) && (dsCategoryId == null))
     {
       list = this.dinerBillMyDao.getDishes(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getDishesCount(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout);
     }
     else if ((categoryId == null) && (dsCategoryId != null))
     {
       list = this.dinerBillMyDao.getSet(restId, categoryId, dsCategoryId, keywords, estimateStatus, isTakeout, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillMyDao.getSetCount(restId, categoryId, dsCategoryId, keywords, isTakeout, estimateStatus);
     }
 
     Page page = new PageImpl(list, pageRequest, totalSize);
     return page;
   }
 
   public Page<Dishe> searchDishe4Self(String restId, String categoryId, String dsCategoryId, String keywords, String estimateStatus, String isRecommend, int pageNumber, int pagzSize, String orderBy)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { "showSeq" }));
     if (StringUtils.isEmpty(estimateStatus))
     {
       estimateStatus = null;
     }
     if (StringUtils.isEmpty(categoryId))
     {
       categoryId = null;
     }
     if (StringUtils.isEmpty(keywords))
     {
       keywords = null;
     }
     if (StringUtils.isEmpty(dsCategoryId))
     {
       dsCategoryId = null;
     }
     if (StringUtils.isEmpty(isRecommend))
     {
       isRecommend = null;
     }
     List list = null;
     int totalSize = 0;
 
     if ((categoryId == null) && (dsCategoryId == null))
     {
       list = this.dinerBillDishMyDao.getALLDishesAndSet(restId, categoryId, dsCategoryId, keywords, estimateStatus, isRecommend, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillDishMyDao.getALLDishesAndSetCount(restId, categoryId, dsCategoryId, keywords, estimateStatus, isRecommend);
     }
     else if ((categoryId != null) && (dsCategoryId == null))
     {
       list = this.dinerBillDishMyDao.getDishes(restId, categoryId, dsCategoryId, keywords, estimateStatus, isRecommend, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillDishMyDao.getDishesCount(restId, categoryId, dsCategoryId, keywords, estimateStatus, isRecommend);
     }
     else if ((categoryId == null) && (dsCategoryId != null))
     {
       list = this.dinerBillDishMyDao.getSet(restId, categoryId, dsCategoryId, keywords, estimateStatus, isRecommend, orderBy, pageRequest.getOffset(), pageRequest.getPageSize());
       totalSize = this.dinerBillDishMyDao.getSetCount(restId, categoryId, dsCategoryId, keywords, isRecommend, estimateStatus);
     }
 
     Page page = new PageImpl(list, pageRequest, totalSize);
     return page;
   }
 
   public Page<Dishe> searchDishe(String restId, Map<String, Object> searchParams, int pageNumber, int pagzSize, String searchType, String[] sortType)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, sortType));
 
     Map filtersOr = SearchFilter.parse(SearchFilter.getOnlyKey(searchParams, new String[] { "LIKE_dishesName", "LIKE_dishesCode" }));
     Specification specOr = DynamicSpecifications.bySearchFilterWithOr(filtersOr.values(), Dishe.class);
 
     searchParams = SearchFilter.getRemoveKey(searchParams, new String[] { "LIKE_dishesName", "LIKE_dishesCode" });
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     filters.put("isUserDefined", new SearchFilter("isUserDefined", SearchFilter.Operator.EQ, TrueFalseEnum.FALSE.getCode()));
     filters.put("isStopSell", new SearchFilter("isStopSell", SearchFilter.Operator.NEQ, TrueFalseEnum.TRUE.getCode()));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), Dishe.class);
     return ((DisheDao)getDao()).findAll(Specifications.where(specOr).and(spec), pageRequest);
   }
 
   public Page<Dishe> searchDishe(String restId, String type, Map<String, Object> searchParams, int pageNumber, int pagzSize, String searchType, String sortType, String isRecommend)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, getRequiredSort(sortType));
 
     Map filtersOr = new HashMap();
     if (!StringUtils.isEmpty(isRecommend))
     {
       filtersOr.put("isSpecialty", new SearchFilter("isSpecialty", SearchFilter.Operator.EQ, TrueFalseEnum.TRUE.getCode()));
       filtersOr.put("isRecommend", new SearchFilter("isRecommend", SearchFilter.Operator.EQ, TrueFalseEnum.TRUE.getCode()));
     }
     Specification specOr = DynamicSpecifications.bySearchFilterWithOr(filtersOr.values(), Dishe.class);
 
     String keyWords = (String)searchParams.get("LIKE_dishesName");
     Map filtersOrKeyCode = new HashMap();
     if (!StringUtils.isEmpty(keyWords))
     {
       filtersOrKeyCode.put("dishesName", new SearchFilter("dishesName", SearchFilter.Operator.LIKE, keyWords));
       filtersOrKeyCode.put("dishesCode", new SearchFilter("dishesCode", SearchFilter.Operator.LIKE, keyWords));
     }
     Specification specOrKeyWord = DynamicSpecifications.bySearchFilterWithOr(filtersOrKeyCode.values(), Dishe.class);
 
     searchParams = SearchFilter.getRemoveKey(searchParams, new String[] { "LIKE_dishesName", "LIKE_dishesCode" });
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     filters.put("isUserDefined", new SearchFilter("isUserDefined", SearchFilter.Operator.EQ, TrueFalseEnum.FALSE.getCode()));
     filters.put("isStopSell", new SearchFilter("isStopSell", SearchFilter.Operator.NEQ, TrueFalseEnum.TRUE.getCode()));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), Dishe.class);
 
     return ((DisheDao)getDao()).findAll(Specifications.where(specOr).and(specOrKeyWord).and(spec), pageRequest);
   }
 
   @Transactional(readOnly=false)
   public void allQxguqing(String restId, LinkedHashMap<String, Object> map)
   {
     List<Dishe> disheList = ((DisheDao)getDao()).findByRestIdAndEstimateIsNotNull(restId);
     for (Dishe dishe : disheList) {
       dishe.setEstimate(null);
       this.self.save(dishe);
 
       map.put(dishe.getDishesId() + "_" + OperationTypeEnum.UPDATE.getCode(), dishe);
     }
 
     List<DishesSet> dishesSets = this.dishesSetDao.findByRestIdAndEstimateIsNotNull(restId);
     for (DishesSet dishesSet : dishesSets) {
       dishesSet.setEstimate(null);
       this.dishesSetService.save(dishesSet);
 
       map.put(dishesSet.getDsId() + "_" + OperationTypeEnum.UPDATE.getCode(), dishesSet);
     }
   }
 
   public List<Dishe> findAllNotStopSell(String restId)
   {
     return ((DisheDao)getDao()).findAllNotStopSell(restId);
   }
 
   public List<Dishe> findByRestIdOrderByShowSeqAsc(String restId) {
     return ((DisheDao)getDao()).findByRestIdAndIsUserDefinedOrderByShowSeqAsc(restId, TrueFalseEnum.FALSE.getCode());
   }
 
   public List<Dishe> findByRestIdAndIsUserDefinedAndIsStopSellOrderByShowSeqAsc(String restId) {
     return ((DisheDao)getDao()).findByRestIdAndIsUserDefinedAndIsStopSellOrderByShowSeqAsc(restId, TrueFalseEnum.FALSE.getCode(), TrueFalseEnum.FALSE.getCode());
   }
 
   public List<Dishe> findByRestIdOrderByShowSeqTakeoutAsc(String restId) {
     return ((DisheDao)getDao()).findByRestIdOrderByShowSeqTakeoutAsc(restId);
   }
 
   public List<Dishe> findByRestIdOrderByShowSeqRecommendAsc(String restId) {
     return ((DisheDao)getDao()).findByRestIdOrderByShowSeqRecommendAsc(restId);
   }
 
   public List<Dishe> findByDishesNameAndRestId(String dishesName, String restId) {
     return ((DisheDao)getDao()).findByDishesNameAndRestId(dishesName, restId);
   }
   public List<Dishe> findByDishesCodeAndRestId(String dishesCode, String restId) {
     return ((DisheDao)getDao()).findByDishesCodeAndRestId(dishesCode, restId);
   }
   public List<Dishe> findByDishesNameAndRestIdAndIsUserDefined(String dishesName, String restId, String isUserDefined) {
     return ((DisheDao)getDao()).findByDishesNameAndRestIdAndIsUserDefined(dishesName, restId, isUserDefined);
   }
   public List<Dishe> findByDishesIdAndRestId(String dishesId, String restId) {
     return ((DisheDao)getDao()).findByDishesIdAndRestId(dishesId, restId);
   }
 
   public List<Dishe> findByDishesIdAndRestIdAndIsStopSell(String dishesId, String restId)
   {
     return ((DisheDao)getDao()).findByDishesIdAndRestIdAndIsStopSell(dishesId, restId, TrueFalseEnum.FALSE.getCode());
   }
 
   public List<Dishe> findByDishesCodeAndRestIdAndIsStopSell(String dishesCode, String restId)
   {
     return ((DisheDao)getDao()).findByDishesCodeAndRestIdAndIsStopSell(dishesCode, restId, TrueFalseEnum.FALSE.getCode());
   }
 
   public List<Dishe> findByDishesNameAndRestIdAndIsStopSell(String dishesName, String restId)
   {
     return ((DisheDao)getDao()).findByDishesNameAndRestIdAndIsStopSell(dishesName, restId, TrueFalseEnum.FALSE.getCode());
   }
   @Autowired
   public void setBaseDao(DisheDao disheDao) {
     super.setDao(disheDao);
   }
 }

