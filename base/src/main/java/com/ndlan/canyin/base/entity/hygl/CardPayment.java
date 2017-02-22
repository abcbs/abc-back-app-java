package com.ndlan.canyin.base.entity.hygl;

import com.ndlan.canyin.base.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cm_membersship_card_payment")
public class CardPayment extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "card_pay_id", unique = true, nullable = false, length = 36)
	private String cardPayId;

	@Column(name = "cpt_id")
	private String cptId;

	@Column(name = "momey", length = 32)
	private String momey;

	@Column(name = "stutas", length = 32)
	private String stutas;
	
	@Column(name = "out_trade_no", length = 32)
	private String outTradeNo;
	
	@Column(name = "card_id", length = 32)
	private String cardId;
	
	
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardPayId() {
		return cardPayId;
	}

	public void setCardPayId(String cardPayId) {
		this.cardPayId = cardPayId;
	}

	public String getCptId() {
		return cptId;
	}

	public void setCptId(String cptId) {
		this.cptId = cptId;
	}

	public String getMomey() {
		return momey;
	}

	public void setMomey(String momey) {
		this.momey = momey;
	}

	public String getStutas() {
		return stutas;
	}

	public void setStutas(String stutas) {
		this.stutas = stutas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
