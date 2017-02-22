 package com.ndlan.canyin.base.entity.hygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.Iterator;
 import java.util.Set;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_membership_card_class")
 public class MembershipCardClass extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="mcclass_id", unique=true, nullable=false, length=36)
   private String mcclassId;
 
   @Column(name="cash_pledge")
   private BigDecimal cashPledge;
 
   @JsonIgnore
   @ManyToOne
   @JoinColumn(name="ccd_id")
   private CashDiscount cashDiscount;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="is_consume_integral", length=36)
   private String isConsumeIntegral;
 
   @JsonIgnore
   @OneToMany(mappedBy="membershipCardClass")
   private Set<MembershipCard> membershipCards;
 
   public String getIsConsumeIntegralDesc()
   {
     return TrueFalseEnum.getDesc(this.isConsumeIntegral);
   }
 
   public String getIsConsumeIntegral() {
     return this.isConsumeIntegral;
   }
 
   public void setIsConsumeIntegral(String isConsumeIntegral) {
     this.isConsumeIntegral = isConsumeIntegral;
   }
 
   public String getRestMemberNum()
   {
     int i = 0;
     if (this.membershipCards != null)
     {
       Iterator it = this.membershipCards.iterator();
 
       while (it.hasNext()) {
         MembershipCard card = (MembershipCard)it.next();
         if (card.getRestMemberInfo() != null) {
           i++;
         }
       }
     }
     return String.valueOf(i);
   }
 
   public static long getSerialversionuid() {
     return 1L;
   }
 
   public String getMcclassId()
   {
     return this.mcclassId;
   }
 
   public void setMcclassId(String mcclassId) {
     this.mcclassId = mcclassId;
   }
 
   public BigDecimal getCashPledge() {
     return this.cashPledge;
   }
 
   public void setCashPledge(BigDecimal cashPledge) {
     this.cashPledge = cashPledge;
   }
 
   public CashDiscount getCashDiscount() {
     return this.cashDiscount;
   }
 
   public void setCashDiscount(CashDiscount cashDiscount) {
     this.cashDiscount = cashDiscount;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Set<MembershipCard> getMembershipCards() {
     return this.membershipCards;
   }
 
   public void setMembershipCards(Set<MembershipCard> membershipCards) {
     this.membershipCards = membershipCards;
   }
 }

