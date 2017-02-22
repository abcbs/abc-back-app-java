 package com.ndlan.canyin.frontdesk.service.hygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillZiZhuService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
 import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
 import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhu;
 import com.ndlan.canyin.base.entity.sygl.PaymentType;
 import com.ndlan.canyin.base.repository.hygl.MembershipCardDao;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.CardOperationTypeEnum;
 import com.ndlan.canyin.core.common.Constants;
 import com.ndlan.canyin.core.common.MemberCardStatusEnum;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.utils.DateProvider;
 import com.ndlan.canyin.sharelogic.util.MessageDataUtil;
 import java.math.BigDecimal;
 import java.util.ArrayList;
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
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class MembershipCardService extends BaseService<MembershipCardDao, MembershipCard>
 {
   private MembershipCardDao membershipCardDao;
 
   @Autowired
   private MembershipCardOperationService membershipCardOperationService;
 
   @Autowired
   private PaymentTypeService paymentTypeService;
 
   @Autowired
   DinerBillZiZhuService dinerBillZiZhuService;
 
   @Autowired
   RestMemberInfoService restMemberInfoService;
 
   @Autowired
   public void setDao(MembershipCardDao dao)
   {
     super.setDao(dao);
     this.membershipCardDao = dao;
   }
 
   public List<MembershipCard> findByRestId(String restId) {
     return this.membershipCardDao.findByRestId(restId);
   }
 
   public Page<MembershipCard> searchPageCard(Map<String, Object> searchParams, String restId, String mcclass_id, int pageNumber, int pagzSize, String keywords, String[] sortType)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, sortType));
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     filters.put("restMemberInfo", new SearchFilter("restMemberInfo", SearchFilter.Operator.ISNOTNULL, null));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), MembershipCard.class);
     return this.membershipCardDao.findAll(spec, pageRequest);
   }
 
   public Page<MembershipCard> searchPageNoMemberCard(Map<String, Object> searchParams, String restId, String mcclass_id, int pageNumber, int pagzSize, String keywords, String[] sortType)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, sortType));
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     filters.put("restMemberInfo", new SearchFilter("restMemberInfo", SearchFilter.Operator.ISNULL, null));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), MembershipCard.class);
     return this.membershipCardDao.findAll(spec, pageRequest);
   }
 
   public List<MembershipCard> searchCard(final String restId,final  String kewWords)
   {
     PageRequest pageRequest = new PageRequest(0, Constants.PAGE_SIZE, new Sort(Sort.Direction.DESC, new String[] { "createTime" }));
     return this.membershipCardDao.findAll(new Specification()
     {
//       public Predicate toPredicate(Root<MembershipCard> root, CriteriaQuery<?> cq, CriteriaBuilder cb)
       public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb)
       {
         Predicate predicate = cb.conjunction();
 
         Path p_restId = root.get("restId");
         predicate = cb.and(cb.equal(p_restId, restId), predicate);
 
         Path cardStatus = root.get("cardStatus");
         predicate = cb.and(cb.equal(cardStatus, MemberCardStatusEnum.NORMAL.getCode()), predicate);
 
         if (StringUtils.isNotEmpty(kewWords)) {
           Join restMemberInfo = root.join("restMemberInfo", JoinType.LEFT);
 
           Path cardNo = root.get("cardNo");
 
           Path mobile = restMemberInfo.get("mobile");
 
           Path nameCode = restMemberInfo.get("nameCode");
           predicate = cb.and(cb.or(new Predicate[] { cb.like(cardNo, "%" + kewWords + "%"), cb.like(mobile, "%" + kewWords + "%"), cb.like(nameCode, "%" + kewWords + "%") }), predicate);
         }
         return predicate;
       }
     }
     , pageRequest).getContent();
   }
 
   public Page<MembershipCard> getPage(PageRequest pageRequest, String cardNo, String isProvide, String membershipCardClassId, String cardStatus, String memberIntegral_le, String memberIntegral_ge, String is_equalCardNo, String restId)
   {
     return ((MembershipCardDao)getDao()).findAll(MembershipCardSpecs.searchPage(cardNo, isProvide, membershipCardClassId, cardStatus, memberIntegral_le, memberIntegral_ge, is_equalCardNo, restId), pageRequest);
   }
 
   @Transactional(readOnly=false)
   public void recharge(MembershipCardOperation obj)
   {
     obj.setCardOperationType(CardOperationTypeEnum.RECHARGE.getCode());
 
     MembershipCard membershipCard = obj.getMembershipCard();
 
     BigDecimal balance = obj.getRechargeCash();
     membershipCard.setBalance(membershipCard.getBalance().add(balance));
 
     BigDecimal addIntegral = obj.getAddIntegral();
     membershipCard.setMemberIntegral(membershipCard.getMemberIntegral().add(addIntegral));
 
     this.self.save(membershipCard);
 
     this.membershipCardOperationService.save(obj);
   }
 
   @Transactional(readOnly=false)
   public String batchDeleteCards(String[] ids)
   {
     List<String> errorCard = new ArrayList();
     int success = 0;
     int error = 0;
     String result = new String();
     MembershipCard membershipCard;
     for (int i = 0; i < ids.length; i++) {
       membershipCard = (MembershipCard)getOne(ids[i]);
       if (membershipCard.getMembershipCardOperations() != null) {
         if ((membershipCard.getMembershipCardOperations().size() > 0) || (membershipCard.getDinerBills().size() > 0)) {
           errorCard.add(membershipCard.getCardNo());
           error++;
         } else {
           delete(ids[i]);
           success++;
         }
       } else {
         delete(ids[i]);
         success++;
       }
     }
     if (success > 0) {
       result = result + "删除成功" + String.valueOf(success) + "条记录!";
     }
     if (error > 0) {
       result = result + "删除失败" + String.valueOf(error) + "条记录,删除失败的卡号为";
       for (String cardNo : errorCard) {
         result = result + cardNo + ";";
       }
       result = result.substring(0, result.length() - 1);
     }
     return result;
   }
 
   public MembershipCard findByCardNoAndCardPassword(String cardNo, String cardPassword, String restId)
   {
     return this.membershipCardDao.findByCardNoAndCardPasswordAndRestId(cardNo, cardPassword, restId);
   }
 
   public List<MembershipCard> findUnProvide(String restId)
   {
     return ((MembershipCardDao)getDao()).findByRestIdAndRestMemberInfoIsNull(restId);
   }
 
   @Transactional(readOnly=false)
   public String batchInsert(MembershipCardClass membershipCardClass, String frontNumber, String backNumber, String midNumber_ge, String midNumber_le, String specNumber, String cardPassword, String rechargeCash, String addIntegral, String restId)
   {
     String result = "";
     for (int i = Integer.valueOf(midNumber_ge).intValue(); i <= Integer.valueOf(midNumber_le).intValue(); i++) {
       String cardNo = "";
       if (StringUtils.isNotEmpty(frontNumber)) {
         cardNo = frontNumber;
       }
       cardNo = cardNo + String.valueOf(i);
       if (StringUtils.isNotEmpty(backNumber)) {
         cardNo = cardNo + backNumber;
       }
       if (cardNo.indexOf("4") == -1) {
         MembershipCard card = new MembershipCard();
         MembershipCardOperation operation = new MembershipCardOperation();
         card.setCardNo(cardNo);
         card.setMembershipCardClass(membershipCardClass);
         if (StringUtils.isNotEmpty(rechargeCash)) {
           card.setBalance(new BigDecimal(rechargeCash));
           operation.setRechargeCash(new BigDecimal(rechargeCash));
         } else {
           card.setBalance(BigDecimal.ZERO);
           operation.setRechargeCash(BigDecimal.ZERO);
         }
         if (StringUtils.isNotEmpty(addIntegral)) {
           card.setMemberIntegral(new BigDecimal(rechargeCash));
           operation.setAddIntegral(new BigDecimal(rechargeCash));
           operation.setTotalIntegral(new BigDecimal(rechargeCash));
         } else {
           card.setMemberIntegral(BigDecimal.ZERO);
           operation.setAddIntegral(BigDecimal.ZERO);
           operation.setTotalIntegral(BigDecimal.ZERO);
         }
         if (StringUtils.isNotEmpty(restId)) {
           card.setRestId(restId);
         }
 
         card.setCardStatus(MemberCardStatusEnum.WAIT.getCode());
         card.setCashPledge(BigDecimal.ZERO);
         this.self.save(card);
         operation.setPaidinCash(BigDecimal.ZERO);
         operation.setCardOperationType(CardOperationTypeEnum.RECHARGE.getCode());
         operation.setMembershipCard(card);
         operation.setRemarks("批量创建卡");
         this.membershipCardOperationService.save(operation);
       }
     }
     return result;
   }
 
   @Transactional(readOnly=false)
   public MembershipCard changeCardStatus(String mcId, String cardStatus, LinkedHashMap<String, Object> map)
   {
     if ((cardStatus != null) && (!cardStatus.isEmpty()))
     {
       MembershipCard card = (MembershipCard)this.self.getOne(mcId);
       card.setCardStatus(cardStatus);
 
       this.self.save(card);
 
       map.put(card.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), card);
 
       return card;
     }
     return null;
   }
 
   @Transactional(readOnly=false)
   public void tuika(String mcId, String tuikaBalance, String tuikaCashPledge, LinkedHashMap<String, Object> map)
   {
     MembershipCard card = (MembershipCard)this.self.getOne(mcId);
 
     RestMemberInfo RestMemberInfo = card.getRestMemberInfo();
     BigDecimal balance = card.getBalance();
     BigDecimal cashPledge = card.getCashPledge();
     card.setCardStatus(MemberCardStatusEnum.CANCEL.getCode());
     if (TrueFalseEnum.TRUE.getCode().equals(tuikaBalance)) {
       card.setBalance(BigDecimal.ZERO);
     }
     if (TrueFalseEnum.TRUE.getCode().equals(tuikaCashPledge)) {
       card.setCashPledge(BigDecimal.ZERO);
     }
     card.setMemberIntegral(BigDecimal.ZERO);
     card.setCardPassword(null);
     card.setRestMemberInfo(null);
 
     this.self.save(card);
 
     map.put(card.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), card);
 
     MembershipCardOperation operation = new MembershipCardOperation();
     operation.setRestMemberInfo(RestMemberInfo);
     operation.setCardOperationType(CardOperationTypeEnum.CANCEL_CARD.getCode());
     operation.setMembershipCard(card);
     operation.setRemarks("退卡");
     if (TrueFalseEnum.TRUE.getCode().equals(tuikaBalance)) {
       operation.setBalance(balance);
     }
     if (TrueFalseEnum.TRUE.getCode().equals(tuikaCashPledge)) {
       operation.setCashPledge(cashPledge);
     }
     this.membershipCardOperationService.save(operation);
 
     map.put(operation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), operation);
   }
 
   @Transactional(readOnly=false)
   public MembershipCard cardChongzhi(MembershipCard membershipCard, String cptId, BigDecimal new_memberIntegral, LinkedHashMap<String, Object> map, ArrayList<String> cloudMethodParams)
   {
     MembershipCard card = (MembershipCard)this.self.getOne(membershipCard.getMcId());
 
     new_memberIntegral = BigDecimalUtil.formatRoundDown(new_memberIntegral);
 
     PaymentType paymentType = null;
     if (StringUtils.isNotEmpty(cptId)) {
       paymentType = (PaymentType)this.paymentTypeService.loadOne(cptId);
     }
     BigDecimal balance = card.getBalance();
     BigDecimal rechargeCash = membershipCard.getRechargeCash();
 
     card.setBalance(balance.add(rechargeCash));
     if (card.getMemberIntegral() == null)
     {
       card.setMemberIntegral(BigDecimal.ZERO);
     }
     card.setMemberIntegral(BigDecimalUtil.formatRoundDown(card.getMemberIntegral().add(new_memberIntegral)));
     this.self.save(card);
 
     map.put(card.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), card);
 
     MembershipCardOperation operation = new MembershipCardOperation();
 
     if (card.getRestMemberInfo() != null)
     {
       RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(card.getRestMemberInfo().getMbId());
       operation.setRestMemberInfo(restMemberInfo);
     }
 
     operation.setCardOperationType(CardOperationTypeEnum.RECHARGE.getCode());
     operation.setMembershipCard(card);
     operation.setRemarks("充值");
     operation.setPaidinCash(membershipCard.getPaidinCash());
     operation.setRechargeCash(membershipCard.getRechargeCash());
 
     operation.setAddIntegral(new_memberIntegral);
 
     operation.setTotalIntegral(card.getMemberIntegral());
     operation.setPaymentType(paymentType);
     if (operation.getTotalIntegral() == null)
     {
       operation.setTotalIntegral(BigDecimal.ZERO);
     }
 
     this.membershipCardOperationService.save(operation);
 
     map.put(operation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), operation);
 
     MessageDataUtil messageDataUtil = new MessageDataUtil();
     messageDataUtil.rechargeMessageData(cloudMethodParams, card.getRestMemberInfo().getMbId(), card.getMcId(), rechargeCash, card.getBalance(), new_memberIntegral, card.getMemberIntegral());
     return card;
   }
 
   @Transactional(readOnly=false)
   public void memberCardPay(MembershipCard membershipCard, DinerBillZiZhu newZizhu, LinkedHashMap<String, Object> map)
   {
     BigDecimal memberIntegral = membershipCard.getMemberIntegral();
     BigDecimal addIntegral = BigDecimal.ZERO;
     PaymentType paymentType = null;
 
     if (memberIntegral == null) {
       memberIntegral = BigDecimal.ZERO;
     }
     if (TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral()))
     {
       addIntegral = newZizhu.getRealCost();
     }
 
     membershipCard.setBalance(membershipCard.getBalance().subtract(newZizhu.getRealCost()));
     membershipCard.setMemberIntegral(memberIntegral.add(addIntegral));
     this.self.save(membershipCard);
 
     map.put(membershipCard.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), membershipCard);
 
     MembershipCardOperation cardOperation = new MembershipCardOperation();
     cardOperation.setRestMemberInfo(membershipCard.getRestMemberInfo());
     cardOperation.setMembershipCard(membershipCard);
     cardOperation.setCardOperationType(CardOperationTypeEnum.CONSUME.getCode());
     cardOperation.setAddIntegral(addIntegral);
     if (cardOperation.getTotalIntegral() != null)
       cardOperation.setTotalIntegral(cardOperation.getTotalIntegral().add(addIntegral));
     else {
       cardOperation.setTotalIntegral(addIntegral);
     }
     cardOperation.setBillNo(newZizhu.getBillNo());
     cardOperation.setBalance(membershipCard.getBalance());
     cardOperation.setConsumeCash(newZizhu.getRealCost());
     cardOperation.setRechargeCash(newZizhu.getRealCost());
     cardOperation.setPaidinCash(newZizhu.getRealCost());
     cardOperation.setPaymentType(paymentType);
     cardOperation.setRemarks("自助点餐");
     this.membershipCardOperationService.save(cardOperation);
 
     map.put(cardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), cardOperation);
 
     newZizhu.setPayTime(DateProvider.DEFAULT.getDate());
     newZizhu.setBillStatus(BillStatusEnum.SETTLE.getCode());
     newZizhu.setPrintTimes(Integer.valueOf(1));
     newZizhu.setMbId(membershipCard.getRestMemberInfo().getMbId());
     newZizhu.setMcId(membershipCard.getMcId());
     this.dinerBillZiZhuService.save(newZizhu);
 
     map.put(newZizhu.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), newZizhu);
   }
 
   public List<MembershipCard> findByCardNo(String cardNo, String restId)
   {
     return ((MembershipCardDao)getDao()).findByCardNoAndRestId(cardNo, restId);
   }
 
   public List<MembershipCard> findByRestMemberInfoAndRestIdAndCardStatus(RestMemberInfo restMemberInfo, String restId, String cardStatus) {
     return ((MembershipCardDao)getDao()).findByRestMemberInfoAndRestIdAndCardStatus(restMemberInfo, restId, cardStatus);
   }
 }

