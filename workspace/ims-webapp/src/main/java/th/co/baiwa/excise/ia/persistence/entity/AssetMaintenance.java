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
 * The persistent class for the IA_ASSET_MAINTENANCE database table.
 * 
 */
@Entity
@Table(name="IA_ASSET_MAINTENANCE")
public class AssetMaintenance extends BaseEntity {
	
	private static final long serialVersionUID = 8820047649848992045L;

	@Id
	@SequenceGenerator(name="IA_ASSET_MAINTENANCE_GEN", sequenceName="IA_ASSET_MAINTENANCE_SEQ" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_ASSET_MAINTENANCE_GEN")
	@Column(name="MAINTENANCE_ID")
	private Long maintenanceId;

	@Column(name="ASSET_BALANCE_ID")
	private Long assetBalanceId;
	
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
	
	

	public Long getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(Long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public Long getAssetBalanceId() {
		return assetBalanceId;
	}

	public void setAssetBalanceId(Long assetBalanceId) {
		this.assetBalanceId = assetBalanceId;
	}

	public BigDecimal getMaintenanceAmount() {
		return maintenanceAmount;
	}

	public void setMaintenanceAmount(BigDecimal maintenanceAmount) {
		this.maintenanceAmount = maintenanceAmount;
	}

	public Date getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getMaintenanceDescription() {
		return maintenanceDescription;
	}

	public void setMaintenanceDescription(String maintenanceDescription) {
		this.maintenanceDescription = maintenanceDescription;
	}

	public String getMaintenanceNote() {
		return maintenanceNote;
	}

	public void setMaintenanceNote(String maintenanceNote) {
		this.maintenanceNote = maintenanceNote;
	}

	public BigDecimal getMaintenancePrice() {
		return maintenancePrice;
	}

	public void setMaintenancePrice(BigDecimal maintenancePrice) {
		this.maintenancePrice = maintenancePrice;
	}

}