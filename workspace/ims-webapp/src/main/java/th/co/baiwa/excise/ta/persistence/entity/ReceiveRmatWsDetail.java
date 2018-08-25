package th.co.baiwa.excise.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the TA_RECEIVE_RMAT_WS_DETAIL database table.
 * 
 */
@Entity
@Table(name="TA_RECEIVE_RMAT_WS_DETAIL")
public class ReceiveRmatWsDetail extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8640233448291592034L;

	@Id
	@SequenceGenerator(name="TA_RECEIVE_RMAT_WS_DETAIL_GEN", sequenceName="TA_RECEIVE_RMAT_WS_DETAIL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_RECEIVE_RMAT_WS_DETAIL_GEN")
	@Column(name="TA_RECEIVE_RMAT_WS_DETAIL_ID")
	private long taReceiveRmatWsDetailId;

	@Column(name="DAY_BOOK")
	private Long dayBook;

	@Column(name="EXTERNAL_DATA")
	private Long externalData;

	@Column(name="MAX_VALUES")
	private Long maxValues;

	@Column(name="MONTH_BOOK")
	private Long monthBook;

	@Column(name="PURCHASE_TAX_INV")
	private Long purchaseTaxInv;

	@Column(name="RECEIVE_RMAT_DETAIL_NO")
	private Long receiveRmatDetailNo;

	@Column(name="RECEIVE_RMAT_DETAIL_ORDER")
	private String receiveRmatDetailOrder;

	@Column(name="RESULT")
	private Long result;

	@Column(name="TA_RECEIVE_RMAT_HEADER_ID")
	private Long taReceiveRmatHeaderId;

	public long getTaReceiveRmatWsDetailId() {
		return taReceiveRmatWsDetailId;
	}

	public void setTaReceiveRmatWsDetailId(long taReceiveRmatWsDetailId) {
		this.taReceiveRmatWsDetailId = taReceiveRmatWsDetailId;
	}

	public Long getDayBook() {
		return dayBook;
	}

	public void setDayBook(Long dayBook) {
		this.dayBook = dayBook;
	}

	public Long getExternalData() {
		return externalData;
	}

	public void setExternalData(Long externalData) {
		this.externalData = externalData;
	}

	public Long getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(Long maxValues) {
		this.maxValues = maxValues;
	}

	public Long getMonthBook() {
		return monthBook;
	}

	public void setMonthBook(Long monthBook) {
		this.monthBook = monthBook;
	}

	public Long getPurchaseTaxInv() {
		return purchaseTaxInv;
	}

	public void setPurchaseTaxInv(Long purchaseTaxInv) {
		this.purchaseTaxInv = purchaseTaxInv;
	}

	public Long getReceiveRmatDetailNo() {
		return receiveRmatDetailNo;
	}

	public void setReceiveRmatDetailNo(Long receiveRmatDetailNo) {
		this.receiveRmatDetailNo = receiveRmatDetailNo;
	}

	public String getReceiveRmatDetailOrder() {
		return receiveRmatDetailOrder;
	}

	public void setReceiveRmatDetailOrder(String receiveRmatDetailOrder) {
		this.receiveRmatDetailOrder = receiveRmatDetailOrder;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public Long getTaReceiveRmatHeaderId() {
		return taReceiveRmatHeaderId;
	}

	public void setTaReceiveRmatHeaderId(Long taReceiveRmatHeaderId) {
		this.taReceiveRmatHeaderId = taReceiveRmatHeaderId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}