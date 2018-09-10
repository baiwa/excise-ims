package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the IA_ASSET_BALANCE database table.
 * 
 */
@Entity
@Table(name="IA_ASSET_BALANCE")
@NamedQuery(name="IaAssetBalance.findAll", query="SELECT i FROM IaAssetBalance i")
public class IaAssetBalance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IA_ASSET_BALANCE_ASSETBALANCEID_GENERATOR", sequenceName="IA_ASSET_BALANCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_ASSET_BALANCE_ASSETBALANCEID_GENERATOR")
	@Column(name="ASSET_BALANCE_ID")
	private long assetBalanceId;

	@Column(name="ACCUMULATED_DEPRECIATION")
	private BigDecimal accumulatedDepreciation;

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

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

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

	private String institute;

	@Column(name="IS_DELETED")
	private String isDeleted;

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

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="VENDORS_ADDRESS")
	private String vendorsAddress;

	@Column(name="VENDORS_NAME")
	private String vendorsName;

	@Column(name="VENDORS_PHONE")
	private String vendorsPhone;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	public IaAssetBalance() {
	}

	public long getAssetBalanceId() {
		return this.assetBalanceId;
	}

	public void setAssetBalanceId(long assetBalanceId) {
		this.assetBalanceId = assetBalanceId;
	}

	public BigDecimal getAccumulatedDepreciation() {
		return this.accumulatedDepreciation;
	}

	public void setAccumulatedDepreciation(BigDecimal accumulatedDepreciation) {
		this.accumulatedDepreciation = accumulatedDepreciation;
	}

	public String getAcquisition() {
		return this.acquisition;
	}

	public void setAcquisition(String acquisition) {
		this.acquisition = acquisition;
	}

	public BigDecimal getAnnualDepreciation() {
		return this.annualDepreciation;
	}

	public void setAnnualDepreciation(BigDecimal annualDepreciation) {
		this.annualDepreciation = annualDepreciation;
	}

	public BigDecimal getAssetAmount() {
		return this.assetAmount;
	}

	public void setAssetAmount(BigDecimal assetAmount) {
		this.assetAmount = assetAmount;
	}

	public String getAssetCode() {
		return this.assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetDescription() {
		return this.assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public String getAssetFeature() {
		return this.assetFeature;
	}

	public void setAssetFeature(String assetFeature) {
		this.assetFeature = assetFeature;
	}

	public String getAssetModel() {
		return this.assetModel;
	}

	public void setAssetModel(String assetModel) {
		this.assetModel = assetModel;
	}

	public String getAssetNote() {
		return this.assetNote;
	}

	public void setAssetNote(String assetNote) {
		this.assetNote = assetNote;
	}

	public String getAssetType() {
		return this.assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDateOfManufacture() {
		return this.dateOfManufacture;
	}

	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public BigDecimal getDepreciationRate() {
		return this.depreciationRate;
	}

	public void setDepreciationRate(BigDecimal depreciationRate) {
		this.depreciationRate = depreciationRate;
	}

	public Date getDocumentDate() {
		return this.documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentNo() {
		return this.documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getExciseDepartment() {
		return this.exciseDepartment;
	}

	public void setExciseDepartment(String exciseDepartment) {
		this.exciseDepartment = exciseDepartment;
	}

	public String getExciseDistrict() {
		return this.exciseDistrict;
	}

	public void setExciseDistrict(String exciseDistrict) {
		this.exciseDistrict = exciseDistrict;
	}

	public String getExciseRegion() {
		return this.exciseRegion;
	}

	public void setExciseRegion(String exciseRegion) {
		this.exciseRegion = exciseRegion;
	}

	public String getGovernmentSector() {
		return this.governmentSector;
	}

	public void setGovernmentSector(String governmentSector) {
		this.governmentSector = governmentSector;
	}

	public String getInstitute() {
		return this.institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public BigDecimal getLifetimeAsset() {
		return this.lifetimeAsset;
	}

	public void setLifetimeAsset(BigDecimal lifetimeAsset) {
		this.lifetimeAsset = lifetimeAsset;
	}

	public BigDecimal getNetValue() {
		return this.netValue;
	}

	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}

	public String getPicAddress() {
		return this.picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}

	public BigDecimal getTotlePriceAsset() {
		return this.totlePriceAsset;
	}

	public void setTotlePriceAsset(BigDecimal totlePriceAsset) {
		this.totlePriceAsset = totlePriceAsset;
	}

	public String getTypeCost() {
		return this.typeCost;
	}

	public void setTypeCost(String typeCost) {
		this.typeCost = typeCost;
	}

	public BigDecimal getUnitPriceAsset() {
		return this.unitPriceAsset;
	}

	public void setUnitPriceAsset(BigDecimal unitPriceAsset) {
		this.unitPriceAsset = unitPriceAsset;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getVendorsAddress() {
		return this.vendorsAddress;
	}

	public void setVendorsAddress(String vendorsAddress) {
		this.vendorsAddress = vendorsAddress;
	}

	public String getVendorsName() {
		return this.vendorsName;
	}

	public void setVendorsName(String vendorsName) {
		this.vendorsName = vendorsName;
	}

	public String getVendorsPhone() {
		return this.vendorsPhone;
	}

	public void setVendorsPhone(String vendorsPhone) {
		this.vendorsPhone = vendorsPhone;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}