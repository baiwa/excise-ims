package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PDT_RECEIVE_WS_DTL")
public class TaPdtReceiveWsDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -620527050709114441L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PDT_RECEIVE_WS_DTL_GEN")
	@SequenceGenerator(name = "TA_PDT_RECEIVE_WS_DTL_GEN", sequenceName = "TA_PDT_RECEIVE_WS_DTL_SEQ", allocationSize = 1)

	@Column(name = "TA_PDT_WS_DTL_ID")
	private BigDecimal taPdtWsDtlId;

	@Column(name = "TA_PDT_RECEIVE_WS_HDR")
	private BigDecimal taPdtReceiveWsHdr;

	@Column(name = "TA_PDT_WS_DTL_NO")
	private String taPdtWsDtlNo;

	@Column(name = "TA_PDT_WS_DTL_ORDER")
	private String taPdtWsDtlOrder;

	@Column(name = "PDT_RECEIVE_BILL")
	private String pdtReceiveBill;

	@Column(name = "MONTH_BOOK_07_04")
	private String monthBook0704;

	@Column(name = "ACCOUNT_07_02")
	private String account0702;

	@Column(name = "MAX_VALUES")
	private String maxValues;

	@Column(name = "RESULT")
	private String result;

	public BigDecimal getTaPdtWsDtlId() {
		return taPdtWsDtlId;
	}

	public void setTaPdtWsDtlId(BigDecimal taPdtWsDtlId) {
		this.taPdtWsDtlId = taPdtWsDtlId;
	}

	public BigDecimal getTaPdtReceiveWsHdr() {
		return taPdtReceiveWsHdr;
	}

	public void setTaPdtReceiveWsHdr(BigDecimal taPdtReceiveWsHdr) {
		this.taPdtReceiveWsHdr = taPdtReceiveWsHdr;
	}

	public String getTaPdtWsDtlNo() {
		return taPdtWsDtlNo;
	}

	public void setTaPdtWsDtlNo(String taPdtWsDtlNo) {
		this.taPdtWsDtlNo = taPdtWsDtlNo;
	}

	public String getTaPdtWsDtlOrder() {
		return taPdtWsDtlOrder;
	}

	public void setTaPdtWsDtlOrder(String taPdtWsDtlOrder) {
		this.taPdtWsDtlOrder = taPdtWsDtlOrder;
	}

	public String getPdtReceiveBill() {
		return pdtReceiveBill;
	}

	public void setPdtReceiveBill(String pdtReceiveBill) {
		this.pdtReceiveBill = pdtReceiveBill;
	}

	public String getMonthBook0704() {
		return monthBook0704;
	}

	public void setMonthBook0704(String monthBook0704) {
		this.monthBook0704 = monthBook0704;
	}

	public String getAccount0702() {
		return account0702;
	}

	public void setAccount0702(String account0702) {
		this.account0702 = account0702;
	}

	public String getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(String maxValues) {
		this.maxValues = maxValues;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
