 package com.ndlan.canyin.frontdesk.service.hygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.ToPinYin;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
 import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import com.ndlan.canyin.base.entity.sygl.PaymentType;
 import com.ndlan.canyin.base.repository.hygl.RestMemberInfoDao;
 import com.ndlan.canyin.base.repository.mybatis.RestMemberInfoMyDao;
 import com.ndlan.canyin.core.common.CardOperationTypeEnum;
 import com.ndlan.canyin.core.common.Constants;
 import com.ndlan.canyin.core.common.MemberCardStatusEnum;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import com.ndlan.canyin.core.utils.DateProvider;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Join;
 import javax.persistence.criteria.JoinType;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageImpl;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
 
 @Service
 public class RestMemberInfoService extends BaseService<RestMemberInfoDao, RestMemberInfo>
 {
   private RestMemberInfoDao restMemberInfoDao;
   private RestMemberInfoMyDao restMemberInfoMyDao;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   private MembershipCardOperationService membershipCardOperationService;
 
   @Autowired
   private PaymentTypeService paymentTypeService;
 
   @Autowired
   public void setDao(RestMemberInfoDao dao)
   {
     super.setDao(dao);
     this.restMemberInfoDao = dao;
   }
 
   public Page<RestMemberInfo> searchRestMember(Map<String, Object> searchParams, String restId, String cardStatus, int pageNumber, int pagzSize, String keywords, String[] sortType)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, sortType));
 
     String mcclass_id = null;
     if (searchParams.get("EQ_membershipCardClass.mcclassId") == null)
     {
       mcclass_id = null;
     }
     else
     {
       mcclass_id = (String)searchParams.get("EQ_membershipCardClass.mcclassId");
       if (mcclass_id.isEmpty())
       {
         mcclass_id = null;
       }
     }
     if ("".equals(cardStatus))
     {
       cardStatus = null;
     }
     List<RestMemberInfo> restMemberInfos = this.restMemberInfoMyDao.getAll(restId, mcclass_id, cardStatus, keywords, pageRequest.getOffset(), pageRequest.getPageSize());
     int totalSize = this.restMemberInfoMyDao.getAllCount(restId, mcclass_id, cardStatus, keywords);
     for (RestMemberInfo r : restMemberInfos)
     {
       r.setShipCards(this.restMemberInfoMyDao.getAllByMemberId(r.getMbId()));
     }
     Page page = new PageImpl(restMemberInfos, pageRequest, totalSize);
 
     return page;
   }
 
   public Page<RestMemberInfo> searchPageRestMember(Map<String, Object> searchParams, String restId, String mcclass_id, int pageNumber, int pagzSize, String keywords, String[] sortType)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, sortType));
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), RestMemberInfo.class);
     return this.restMemberInfoDao.findAll(spec, pageRequest);
   }
 
   public List<RestMemberInfo> searchMember(String restId,final String kewWords)
   {
     PageRequest pageRequest = new PageRequest(0, Constants.PAGE_SIZE, new Sort(Sort.Direction.DESC, new String[] { "createTime" }));
     return this.restMemberInfoDao.findAll(new Specification()
     {
//       public Predicate toPredicate(Root<RestMemberInfo> root, CriteriaQuery<?> cq, CriteriaBuilder cb)
    	 public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb)
       {
         Predicate predicate = cb.conjunction();
 
         Path restId = root.get("restId");
         predicate = cb.and(cb.equal(restId, restId), predicate);
 
         if (StringUtils.isNotEmpty(kewWords))
         {
           Path name = root.get("name");
 
           Path mobile = root.get("mobile");
 
           Path nameCode = root.get("nameCode");
 
           Join cards = root.join("membershipCards", JoinType.LEFT);
           Path p_cardNo = cards.get("cardNo");
 
           predicate = cb.and(cb.or(new Predicate[] { cb.like(name, "%" + kewWords + "%"), cb.like(mobile, "%" + kewWords + "%"), cb.like(nameCode, "%" + kewWords + "%"), cb.like(p_cardNo, "%" + kewWords + "%") }), predicate);
         }
 
         return predicate;
       }
     }
     , pageRequest).getContent();
   }
 
   public Page<RestMemberInfo> getPage(PageRequest pageRequest, String keyWords, String restId, String offset)
   {
     return ((RestMemberInfoDao)getDao()).findAll(RestMemberInfoSpecs.searchPage(keyWords, restId, offset), pageRequest);
   }
 
   public RestMemberInfo findRestMemberInfoById(String id)
   {
     return (RestMemberInfo)((RestMemberInfoDao)getDao()).findOne(id);
   }
 
   public RestMemberInfo findByMobile(String mobile, String restId)
   {
     return ((RestMemberInfoDao)getDao()).findByMobileAndRestId(mobile, restId);
   }
 
   public RestMemberInfo findByNameAndRestId(String name, String restId) {
     List ms = ((RestMemberInfoDao)getDao()).findByNameAndRestId(name, restId);
     if ((ms != null) && (ms.size() > 0))
     {
       return (RestMemberInfo)ms.get(0);
     }
     return null;
   }
 
   public void deleteRestMemberInfo(String id)
   {
     ((RestMemberInfoDao)getDao()).delete(id);
   }
 
   public void deleteRestMemberInfos(String[] idArr)
   {
     ((RestMemberInfoDao)getDao()).batchDelete(idArr);
   }
 
   public RestMemberInfoMyDao getRestMemberInfoMyDao() {
     return this.restMemberInfoMyDao;
   }
   @Autowired
   public void setRestMemberInfoMyDao(RestMemberInfoMyDao restMemberInfoMyDao) { this.restMemberInfoMyDao = restMemberInfoMyDao;
   }
 
   public MembershipCard jiankaCreate(RestMemberInfo restMemberInfo, String jiankaType, String mcId, MembershipCard membershipCard, String cptId, String new_cardOpType, LinkedHashMap<String, Object> map)
   {
     MembershipCard savedCard = membershipCard;
     if (restMemberInfo.getBirthday() != null) {
       restMemberInfo.setBirthdayInt(DateUtil.getOffsetDate(restMemberInfo.getBirthday()).intValue());
     }
 
     PaymentType paymentType = null;
     if (StringUtils.isNotEmpty(cptId)) {
       paymentType = (PaymentType)this.paymentTypeService.loadOne(cptId);
     }
 
     if (StringUtils.isNotEmpty(restMemberInfo.getName())) {
       restMemberInfo.setNameCode(ToPinYin.toPinYin(restMemberInfo.getName()));
     }
     String op = OperationTypeEnum.UPDATE.getCode();
     if (StringUtils.isEmpty(restMemberInfo.getMbId()))
     {
       op = OperationTypeEnum.CREATE.getCode();
       restMemberInfo.setMbId(null);
     }
 
     this.self.save(restMemberInfo);
 
     map.put(restMemberInfo.getMbId() + "_" + op, restMemberInfo);
 
     if (TrueFalseEnum.TRUE.getCode().equals(jiankaType)) {
       MembershipCard alreadyCard = (MembershipCard)this.membershipCardService.getOne(mcId);
       savedCard = alreadyCard;
       alreadyCard.setRestMemberInfo(restMemberInfo);
       alreadyCard.setCardStatus(MemberCardStatusEnum.NORMAL.getCode());
       alreadyCard.setCashPledge(membershipCard.getCashPledge());
       alreadyCard.setCardPassword(membershipCard.getCardPassword());
       alreadyCard.setRechargeCash(membershipCard.getRechargeCash());
       alreadyCard.setPaidinCash(membershipCard.getPaidinCash());
       alreadyCard.setMemberIntegral(membershipCard.getMemberIntegral());
       alreadyCard.setBalance(membershipCard.getRechargeCash());
       alreadyCard.setCardIssueTime(DateProvider.DEFAULT.getDate());
       this.membershipCardService.save(alreadyCard);
 
       map.put(alreadyCard.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), alreadyCard);
 
       MembershipCardOperation membershipCardOperation = new MembershipCardOperation();
       membershipCardOperation.setRestMemberInfo(restMemberInfo);
       membershipCardOperation.setMembershipCard(alreadyCard);
       membershipCardOperation.setCardOperationType(CardOperationTypeEnum.CASH_PLEDGE.getCode());
       membershipCardOperation.setAddIntegral(membershipCard.getMemberIntegral());
       membershipCardOperation.setTotalIntegral(membershipCard.getMemberIntegral());
       membershipCardOperation.setPaidinCash(membershipCard.getPaidinCash());
       membershipCardOperation.setRechargeCash(membershipCard.getRechargeCash());
       membershipCardOperation.setBalance(membershipCard.getBalance());
       membershipCardOperation.setIsDrawBill(TrueFalseEnum.FALSE.getCode());
       membershipCardOperation.setPaymentType(paymentType);
       membershipCardOperation.setCashPledge(membershipCard.getCashPledge());
       this.membershipCardOperationService.save(membershipCardOperation);
 
       map.put(membershipCardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), membershipCardOperation);
 
       if ((membershipCard.getRechargeCash() != null) && (membershipCard.getRechargeCash().compareTo(BigDecimal.ZERO) > 0))
       {
         MembershipCardOperation mOperation = new MembershipCardOperation();
         mOperation.setRestMemberInfo(restMemberInfo);
         mOperation.setMembershipCard(alreadyCard);
         mOperation.setCardOperationType(CardOperationTypeEnum.RECHARGE.getCode());
         mOperation.setAddIntegral(membershipCard.getMemberIntegral());
         mOperation.setTotalIntegral(membershipCard.getMemberIntegral());
         mOperation.setPaidinCash(membershipCard.getPaidinCash());
         mOperation.setRechargeCash(membershipCard.getRechargeCash());
         mOperation.setBalance(membershipCard.getBalance());
         mOperation.setIsDrawBill(TrueFalseEnum.FALSE.getCode());
         mOperation.setPaymentType(paymentType);
         this.membershipCardOperationService.save(mOperation);
 
         map.put(mOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), mOperation);
       }
 
     }
 
     if (TrueFalseEnum.FALSE.getCode().equals(jiankaType)) {
       membershipCard.setCardStatus(MemberCardStatusEnum.NORMAL.getCode());
       membershipCard.setBalance(membershipCard.getRechargeCash());
       membershipCard.setCardIssueTime(DateProvider.DEFAULT.getDate());
       savedCard = (MembershipCard)this.membershipCardService.save(membershipCard);
 
       map.put(membershipCard.getMcId() + "_" + OperationTypeEnum.CREATE.getCode(), membershipCard);
 
       MembershipCardOperation membershipCardOperation = new MembershipCardOperation();
       membershipCardOperation.setRestMemberInfo(restMemberInfo);
       membershipCardOperation.setMembershipCard(membershipCard);
       membershipCardOperation.setCardOperationType(CardOperationTypeEnum.CASH_PLEDGE.getCode());
       membershipCardOperation.setAddIntegral(membershipCard.getRechargeCash());
       membershipCardOperation.setTotalIntegral(membershipCard.getRechargeCash());
       membershipCardOperation.setPaidinCash(membershipCard.getPaidinCash());
       membershipCardOperation.setRechargeCash(membershipCard.getRechargeCash());
       membershipCardOperation.setIsDrawBill(new_cardOpType);
       membershipCardOperation.setBalance(membershipCard.getRechargeCash());
       membershipCardOperation.setDrawBillAmount(membershipCard.getPaidinCash());
       membershipCardOperation.setCashPledge(membershipCard.getCashPledge());
       this.membershipCardOperationService.save(membershipCardOperation);
 
       map.put(membershipCardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), membershipCardOperation);
 
       if ((membershipCard.getRechargeCash() != null) && (membershipCard.getRechargeCash().compareTo(BigDecimal.ZERO) > 0)) {
         membershipCardOperation = new MembershipCardOperation();
         membershipCardOperation.setRestMemberInfo(restMemberInfo);
         membershipCardOperation.setMembershipCard(membershipCard);
         membershipCardOperation.setCardOperationType(CardOperationTypeEnum.RECHARGE.getCode());
         membershipCardOperation.setAddIntegral(membershipCard.getMemberIntegral());
         membershipCardOperation.setTotalIntegral(membershipCard.getMemberIntegral());
         membershipCardOperation.setPaidinCash(membershipCard.getPaidinCash());
         membershipCardOperation.setRechargeCash(membershipCard.getRechargeCash());
         membershipCardOperation.setBalance(membershipCard.getBalance());
         membershipCardOperation.setIsDrawBill(TrueFalseEnum.FALSE.getCode());
         membershipCardOperation.setPaymentType(paymentType);
         this.membershipCardOperationService.save(membershipCardOperation);
 
         map.put(membershipCardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), membershipCardOperation);
       }
     }
 
     return savedCard;
   }
 
   public static void main(String[] args)
   {
     BigDecimal a = new BigDecimal(100);
 
     System.out.println(a.compareTo(BigDecimal.ZERO));
   }
 }

