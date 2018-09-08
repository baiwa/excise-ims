package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_ASSET_WORK_SHEET database table.
 * 
 */
@Entity
@Table(name="IA_ASSET_WORK_SHEET")
public class IaAssetWorkSheet extends BaseEntity {
	
	private static final long serialVersionUID = -1915768843288445249L;

	@Id
	@SequenceGenerator(name="IA_ASSET_WORK_SHEET_ASSETWORKSHEETID_GENERATOR", sequenceName="IA_ASSET_WORK_SHEET_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_ASSET_WORK_SHEET_ASSETWORKSHEETID_GENERATOR")
	@Column(name="ASSET_WORK_SHEET_ID")
	private long assetWorkSheetId;

	@Column(name="ACCUMULATED_DEPRECIATION_1")
	private BigDecimal accumulatedDepreciation1;

	@Column(name="ACCUMULATED_DEPRECIATION_2")
	private BigDecimal accumulatedDepreciation2;

	@Column(name="ACQUISITION_VALUE_1")
	private BigDecimal acquisitionValue1;

	@Column(name="ACQUISITION_VALUE_2")
	private BigDecimal acquisitionValue2;

	@Column(name="ASSET_DESCRIPTION_1")
	private String assetDescription1;

	@Column(name="ASSET_DESCRIPTION_2")
	private String assetDescription2;

	@Column(name="ASSET_NUMBER_1")
	private BigDecimal assetNumber1;

	@Column(name="ASSET_NUMBER_2")
	private BigDecimal assetNumber2;

	@Column(name="ASSET_SUB_NUMBER_1")
	private BigDecimal assetSubNumber1;

	@Column(name="ASSET_SUB_NUMBER_2")
	private BigDecimal assetSubNumber2;

	@Column(name="BOOK_VALUE_1")
	private BigDecimal bookValue1;

	@Column(name="BOOK_VALUE_2")
	private BigDecimal bookValue2;

	@Temporal(TemporalType.DATE)
	@Column(name="FUND_TRANSFER_DATE_1")
	private Date fundTransferDate1;

	@Temporal(TemporalType.DATE)
	@Column(name="FUND_TRANSFER_DATE_2")
	private Date fundTransferDate2;

	public long getAssetWorkSheetId() {
		return assetWorkSheetId;
	}

	public void setAssetWorkSheetId(long assetWorkSheetId) {
		this.assetWorkSheetId = assetWorkSheetId;
	}

	public BigDecimal getAccumulatedDepreciation1() {
		return accumulatedDepreciation1;
	}

	public void setAccumulatedDepreciation1(BigDecimal accumulatedDepreciation1) {
		this.accumulatedDepreciation1 = accumulatedDepreciation1;
	}

	public BigDecimal getAccumulatedDepreciation2() {
		return accumulatedDepreciation2;
	}

	public void setAccumulatedDepreciation2(BigDecimal accumulatedDepreciation2) {
		this.accumulatedDepreciation2 = accumulatedDepreciation2;
	}

	public BigDecimal getAcquisitionValue1() {
		return acquisitionValue1;
	}

	public void setAcquisitionValue1(BigDecimal acquisitionValue1) {
		this.acquisitionValue1 = acquisitionValue1;
	}

	public BigDecimal getAcquisitionValue2() {
		return acquisitionValue2;
	}

	public void setAcquisitionValue2(BigDecimal acquisitionValue2) {
		this.acquisitionValue2 = acquisitionValue2;
	}

	public String getAssetDescription1() {
		return assetDescription1;
	}

	public void setAssetDescription1(String assetDescription1) {
		this.assetDescription1 = assetDescription1;
	}

	public String getAssetDescription2() {
		return assetDescription2;
	}

	public void setAssetDescription2(String assetDescription2) {
		this.assetDescription2 = assetDescription2;
	}

	public BigDecimal getAssetNumber1() {
		return assetNumber1;
	}

	public void setAssetNumber1(BigDecimal assetNumber1) {
		this.assetNumber1 = assetNumber1;
	}

	public BigDecimal getAssetNumber2() {
		return assetNumber2;
	}

	public void setAssetNumber2(BigDecimal assetNumber2) {
		this.assetNumber2 = assetNumber2;
	}

	public BigDecimal getAssetSubNumber1() {
		return assetSubNumber1;
	}

	public void setAssetSubNumber1(BigDecimal assetSubNumber1) {
		this.assetSubNumber1 = assetSubNumber1;
	}

	public BigDecimal getAssetSubNumber2() {
		return assetSubNumber2;
	}

	public void setAssetSubNumber2(BigDecimal assetSubNumber2) {
		this.assetSubNumber2 = assetSubNumber2;
	}

	public BigDecimal getBookValue1() {
		return bookValue1;
	}

	public void setBookValue1(BigDecimal bookValue1) {
		this.bookValue1 = bookValue1;
	}

	public BigDecimal getBookValue2() {
		return bookValue2;
	}

	public void setBookValue2(BigDecimal bookValue2) {
		this.bookValue2 = bookValue2;
	}

	public Date getFundTransferDate1() {
		return fundTransferDate1;
	}

	public void setFundTransferDate1(Date fundTransferDate1) {
		this.fundTransferDate1 = fundTransferDate1;
	}

	public Date getFundTransferDate2() {
		return fundTransferDate2;
	}

	public void setFundTransferDate2(Date fundTransferDate2) {
		this.fundTransferDate2 = fundTransferDate2;
	}

}