package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the IA_ASSET_MAINTENANCE database table.
 * 
 */
@Entity
@Table(name="IA_ASSET_MAINTENANCE")
@NamedQuery(name="IaAssetMaintenance.findAll", query="SELECT i FROM IaAssetMaintenance i")
public class IaAssetMaintenance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IA_ASSET_MAINTENANCE_MAINTENANCEID_GENERATOR", sequenceName="IA_ASSET_MAINTENANCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_ASSET_MAINTENANCE_MAINTENANCEID_GENERATOR")
	@Column(name="MAINTENANCE_ID")
	private long maintenanceId;

	@Column(name="ASSET_BALANCE_ID")
	private BigDecimal assetBalanceId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="IS_DELETED")
	private String isDeleted;

	@Column(name="MAINTENANCE_AMOUNT")
	private BigDecimal maintenanceAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="MAINTENANCE_DATE")
	private Date maintenanceDate;

	@Column(name="MAINTENANCE_DESCRIPTION")
	private String maintenanceDescription;

	@Column(name="MAINTENANCE_NOTE")
	private String maintenanceNote;

	@Column(name="MAINTENANCE_PRICE")
	private BigDecimal maintenancePrice;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	public IaAssetMaintenance() {
	}

	public long getMaintenanceId() {
		return this.maintenanceId;
	}

	public void setMaintenanceId(long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public BigDecimal getAssetBalanceId() {
		return this.assetBalanceId;
	}

	public void setAssetBalanceId(BigDecimal assetBalanceId) {
		this.assetBalanceId = assetBalanceId;
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

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public BigDecimal getMaintenanceAmount() {
		return this.maintenanceAmount;
	}

	public void setMaintenanceAmount(BigDecimal maintenanceAmount) {
		this.maintenanceAmount = maintenanceAmount;
	}

	public Date getMaintenanceDate() {
		return this.maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getMaintenanceDescription() {
		return this.maintenanceDescription;
	}

	public void setMaintenanceDescription(String maintenanceDescription) {
		this.maintenanceDescription = maintenanceDescription;
	}

	public String getMaintenanceNote() {
		return this.maintenanceNote;
	}

	public void setMaintenanceNote(String maintenanceNote) {
		this.maintenanceNote = maintenanceNote;
	}

	public BigDecimal getMaintenancePrice() {
		return this.maintenancePrice;
	}

	public void setMaintenancePrice(BigDecimal maintenancePrice) {
		this.maintenancePrice = maintenancePrice;
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

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}