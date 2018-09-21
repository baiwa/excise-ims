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
 * The persistent class for the IA_ASSET_BALANCE database table.
 * 
 */
@Entity
@Table(name="IA_ASSET_BALANCE")
public class AssetBalance extends BaseEntity {
	
	private static final long serialVersionUID = -3315490565128876137L;

	@Id
	@SequenceGenerator(name="IA_ASSET_BALANCE_ASSETBALANCEID_GENERATOR", sequenceName="IA_ASSET_BALANCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_ASSET_BALANCE_ASSETBALANCEID_GENERATOR")
	@Column(name="ASSET_BALANCE_ID")
	private Long assetBalanceId;

	@Column(name="ACCUMULATED_DEPRECIATION")
	private BigDecimal accumulatedDepreciation;

	@Column(name="ACQUISITION")
	private String acquisition;

	@Column(name="ANNUAL_DEPRECIATION")
	private BigDecimal annualDepreciation;

	@Column(name="ASSET_AMOUNT")
	private BigDecimal assetAmount;

	@Column(name="ASSET_CODE")
	private String assetCode;

	@Column(name="ASSET_DESCRIPTION")
	private String assetDescription;

	@Column(name="ASSET_FEATURE")
	private String assetFeature;

	@Column(name="ASSET_MODEL")
	private String assetModel;

	@Column(name="ASSET_NOTE")
	private String assetNote;

	@Column(name="ASSET_TYPE")
	private String assetType;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_MANUFACTURE")
	private Date dateOfManufacture;

	@Column(name="DEPRECIATION_RATE")
	private BigDecimal depreciationRate;

	@Temporal(TemporalType.DATE)
	@Column(name="DOCUMENT_DATE")
	private Date documentDate;

	@Column(name="DOCUMENT_NO")
	private String documentNo;

	@Column(name="EXCISE_DEPARTMENT")
	private String exciseDepartment;

	@Column(name="EXCISE_DISTRICT")
	private String exciseDistrict;

	@Column(name="EXCISE_REGION")
	private String exciseRegion;

	@Column(name="GOVERNMENT_SECTOR")
	private String governmentSector;

	@Column(name="INSTITUTE")
	private String institute;

	@Column(name="LIFETIME_ASSET")
	private BigDecimal lifetimeAsset;

	@Column(name="NET_VALUE")
	private BigDecimal netValue;

	@Column(name="PIC_ADDRESS")
	private String picAddress;

	@Column(name="TOTLE_PRICE_ASSET")
	private BigDecimal totlePriceAsset;

	@Column(name="TYPE_COST")
	private String typeCost;

	@Column(name="UNIT_PRICE_ASSET")
	private BigDecimal unitPriceAsset;

	@Column(name="VENDORS_ADDRESS")
	private String vendorsAddress;

	@Column(name="VENDORS_NAME")
	private String vendorsName;

	@Column(name="VENDORS_PHONE")
	private String vendorsPhone;

	

	public BigDecimal getAccumulatedDepreciation() {
		return accumulatedDepreciation;
	}

	public void setAccumulatedDepreciation(BigDecimal accumulatedDepreciation) {
		this.accumulatedDepreciation = accumulatedDepreciation;
	}

	public String getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(String acquisition) {
		this.acquisition = acquisition;
	}

	public BigDecimal getAnnualDepreciation() {
		return annualDepreciation;
	}

	public void setAnnualDepreciation(BigDecimal annualDepreciation) {
		this.annualDepreciation = annualDepreciation;
	}

	public BigDecimal getAssetAmount() {
		return assetAmount;
	}

	public void setAssetAmount(BigDecimal assetAmount) {
		this.assetAmount = assetAmount;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public String getAssetFeature() {
		return assetFeature;
	}

	public void setAssetFeature(String assetFeature) {
		this.assetFeature = assetFeature;
	}

	public String getAssetModel() {
		return assetModel;
	}

	public void setAssetModel(String assetModel) {
		this.assetModel = assetModel;
	}

	public String getAssetNote() {
		return assetNote;
	}

	public void setAssetNote(String assetNote) {
		this.assetNote = assetNote;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public BigDecimal getDepreciationRate() {
		return depreciationRate;
	}

	public void setDepreciationRate(BigDecimal depreciationRate) {
		this.depreciationRate = depreciationRate;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getExciseDepartment() {
		return exciseDepartment;
	}

	public void setExciseDepartment(String exciseDepartment) {
		this.exciseDepartment = exciseDepartment;
	}

	public String getExciseDistrict() {
		return exciseDistrict;
	}

	public void setExciseDistrict(String exciseDistrict) {
		this.exciseDistrict = exciseDistrict;
	}

	public String getExciseRegion() {
		return exciseRegion;
	}

	public void setExciseRegion(String exciseRegion) {
		this.exciseRegion = exciseRegion;
	}

	public String getGovernmentSector() {
		return governmentSector;
	}

	public void setGovernmentSector(String governmentSector) {
		this.governmentSector = governmentSector;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public BigDecimal getLifetimeAsset() {
		return lifetimeAsset;
	}

	public void setLifetimeAsset(BigDecimal lifetimeAsset) {
		this.lifetimeAsset = lifetimeAsset;
	}

	public BigDecimal getNetValue() {
		return netValue;
	}

	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}

	public String getPicAddress() {
		return picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}

	public BigDecimal getTotlePriceAsset() {
		return totlePriceAsset;
	}

	public void setTotlePriceAsset(BigDecimal totlePriceAsset) {
		this.totlePriceAsset = totlePriceAsset;
	}

	public String getTypeCost() {
		return typeCost;
	}

	public void setTypeCost(String typeCost) {
		this.typeCost = typeCost;
	}

	public BigDecimal getUnitPriceAsset() {
		return unitPriceAsset;
	}

	public void setUnitPriceAsset(BigDecimal unitPriceAsset) {
		this.unitPriceAsset = unitPriceAsset;
	}

	public String getVendorsAddress() {
		return vendorsAddress;
	}

	public void setVendorsAddress(String vendorsAddress) {
		this.vendorsAddress = vendorsAddress;
	}

	public String getVendorsName() {
		return vendorsName;
	}

	public void setVendorsName(String vendorsName) {
		this.vendorsName = vendorsName;
	}

	public String getVendorsPhone() {
		return vendorsPhone;
	}

	public void setVendorsPhone(String vendorsPhone) {
		this.vendorsPhone = vendorsPhone;
	}

	public Long getAssetBalanceId() {
		return assetBalanceId;
	}

	public void setAssetBalanceId(Long assetBalanceId) {
		this.assetBalanceId = assetBalanceId;
	}

}