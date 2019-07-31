package th.go.excise.ims.ws.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "WS_REGFRI4000")
public class WsRegfri4000 extends BaseEntity {

	private static final long serialVersionUID = -7874741775897826430L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_REGFRI4000_GEN")
	@SequenceGenerator(name = "WS_REGFRI4000_GEN", sequenceName = "WS_REGFRI4000_SEQ", allocationSize = 1)
	@Column(name = "WS_REGFRI4000_ID")
	private Long wsRegfri4000Id;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "REG_STATUS")
	private String regStatus;
	@Column(name = "REG_STATUS_DESC")
	private String regStatusDesc;
	@Column(name = "STATUS_DATE")
	private LocalDate statusDate;
	@Column(name = "CUS_ID")
	private String cusId;
	@Column(name = "CUS_SEQ")
	private Integer cusSeq;
	@Column(name = "CUS_ADDR_SEQ")
	private Integer cusAddrSeq;
	@Column(name = "CUS_FULLNAME")
	private String cusFullname;
	@Column(name = "CUS_HOUSE_NO")
	private String cusHouseNo;
	@Column(name = "CUS_ADDR_NO")
	private String cusAddrNo;
	@Column(name = "CUS_BUILD_NAME")
	private String cusBuildName;
	@Column(name = "CUS_FLOOR_NO")
	private String cusFloorNo;
	@Column(name = "CUS_ROOM_NO")
	private String cusRoomNo;
	@Column(name = "CUS_MOO_NO")
	private String cusMooNo;
	@Column(name = "CUS_VILLAGE")
	private String cusVillage;
	@Column(name = "CUS_SOI_NAME")
	private String cusSoiName;
	@Column(name = "CUS_THN_NAME")
	private String cusThnName;
	@Column(name = "CUS_TAMBOL_CODE")
	private String cusTambolCode;
	@Column(name = "CUS_TAMBOL_NAME")
	private String cusTambolName;
	@Column(name = "CUS_AMPHUR_CODE")
	private String cusAmphurCode;
	@Column(name = "CUS_AMPHUR_NAME")
	private String cusAmphurName;
	@Column(name = "CUS_PROVINCE_CODE")
	private String cusProvinceCode;
	@Column(name = "CUS_PROVINCE_NAME")
	private String cusProvinceName;
	@Column(name = "CUS_ZIP_CODE")
	private String cusZipCode;
	@Column(name = "CUS_TEL_NO")
	private String cusTelNo;
	@Column(name = "CUS_EMAIL")
	private String cusEmail;
	@Column(name = "CUS_URL")
	private String cusUrl;
	@Column(name = "FAC_ID")
	private String facId;
	@Column(name = "FAC_SEQ")
	private Integer facSeq;
	@Column(name = "FAC_ADDR_SEQ")
	private Integer facAddrSeq;
	@Column(name = "FAC_FULLNAME")
	private String facFullname;
	@Column(name = "FAC_HOUSE_NO")
	private String facHouseNo;
	@Column(name = "FAC_ADDR_NO")
	private String facAddrNo;
	@Column(name = "FAC_BUILD_NAME")
	private String facBuildName;
	@Column(name = "FAC_FLOOR_NO")
	private String facFloorNo;
	@Column(name = "FAC_ROOM_NO")
	private String facRoomNo;
	@Column(name = "FAC_MOO_NO")
	private String facMooNo;
	@Column(name = "FAC_VILLAGE")
	private String facVillage;
	@Column(name = "FAC_SOI_NAME")
	private String facSoiName;
	@Column(name = "FAC_THN_NAME")
	private String facThnName;
	@Column(name = "FAC_TAMBOL_CODE")
	private String facTambolCode;
	@Column(name = "FAC_TAMBOL_NAME")
	private String facTambolName;
	@Column(name = "FAC_AMPHUR_CODE")
	private String facAmphurCode;
	@Column(name = "FAC_AMPHUR_NAME")
	private String facAmphurName;
	@Column(name = "FAC_PROVINCE_CODE")
	private String facProvinceCode;
	@Column(name = "FAC_PROVINCE_NAME")
	private String facProvinceName;
	@Column(name = "FAC_ZIP_CODE")
	private String facZipCode;
	@Column(name = "FAC_TEL_NO")
	private String facTelNo;
	@Column(name = "FAC_EMAIL")
	private String facEmail;
	@Column(name = "FAC_URL")
	private String facUrl;
	@Column(name = "CAPITAL")
	private BigDecimal capital;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "ACTIVE_FLAG")
	private String activeFlag;
	@Column(name = "SYNC_DATE")
	private LocalDateTime syncDate;

	public Long getWsRegfri4000Id() {
		return wsRegfri4000Id;
	}

	public void setWsRegfri4000Id(Long wsRegfri4000Id) {
		this.wsRegfri4000Id = wsRegfri4000Id;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getRegStatusDesc() {
		return regStatusDesc;
	}

	public void setRegStatusDesc(String regStatusDesc) {
		this.regStatusDesc = regStatusDesc;
	}

	public LocalDate getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(LocalDate statusDate) {
		this.statusDate = statusDate;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public Integer getCusSeq() {
		return cusSeq;
	}

	public void setCusSeq(Integer cusSeq) {
		this.cusSeq = cusSeq;
	}

	public Integer getCusAddrSeq() {
		return cusAddrSeq;
	}

	public void setCusAddrSeq(Integer cusAddrSeq) {
		this.cusAddrSeq = cusAddrSeq;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getCusHouseNo() {
		return cusHouseNo;
	}

	public void setCusHouseNo(String cusHouseNo) {
		this.cusHouseNo = cusHouseNo;
	}

	public String getCusAddrNo() {
		return cusAddrNo;
	}

	public void setCusAddrNo(String cusAddrNo) {
		this.cusAddrNo = cusAddrNo;
	}

	public String getCusBuildName() {
		return cusBuildName;
	}

	public void setCusBuildName(String cusBuildName) {
		this.cusBuildName = cusBuildName;
	}

	public String getCusFloorNo() {
		return cusFloorNo;
	}

	public void setCusFloorNo(String cusFloorNo) {
		this.cusFloorNo = cusFloorNo;
	}

	public String getCusRoomNo() {
		return cusRoomNo;
	}

	public void setCusRoomNo(String cusRoomNo) {
		this.cusRoomNo = cusRoomNo;
	}

	public String getCusMooNo() {
		return cusMooNo;
	}

	public void setCusMooNo(String cusMooNo) {
		this.cusMooNo = cusMooNo;
	}

	public String getCusVillage() {
		return cusVillage;
	}

	public void setCusVillage(String cusVillage) {
		this.cusVillage = cusVillage;
	}

	public String getCusSoiName() {
		return cusSoiName;
	}

	public void setCusSoiName(String cusSoiName) {
		this.cusSoiName = cusSoiName;
	}

	public String getCusThnName() {
		return cusThnName;
	}

	public void setCusThnName(String cusThnName) {
		this.cusThnName = cusThnName;
	}

	public String getCusTambolCode() {
		return cusTambolCode;
	}

	public void setCusTambolCode(String cusTambolCode) {
		this.cusTambolCode = cusTambolCode;
	}

	public String getCusTambolName() {
		return cusTambolName;
	}

	public void setCusTambolName(String cusTambolName) {
		this.cusTambolName = cusTambolName;
	}

	public String getCusAmphurCode() {
		return cusAmphurCode;
	}

	public void setCusAmphurCode(String cusAmphurCode) {
		this.cusAmphurCode = cusAmphurCode;
	}

	public String getCusAmphurName() {
		return cusAmphurName;
	}

	public void setCusAmphurName(String cusAmphurName) {
		this.cusAmphurName = cusAmphurName;
	}

	public String getCusProvinceCode() {
		return cusProvinceCode;
	}

	public void setCusProvinceCode(String cusProvinceCode) {
		this.cusProvinceCode = cusProvinceCode;
	}

	public String getCusProvinceName() {
		return cusProvinceName;
	}

	public void setCusProvinceName(String cusProvinceName) {
		this.cusProvinceName = cusProvinceName;
	}

	public String getCusZipCode() {
		return cusZipCode;
	}

	public void setCusZipCode(String cusZipCode) {
		this.cusZipCode = cusZipCode;
	}

	public String getCusTelNo() {
		return cusTelNo;
	}

	public void setCusTelNo(String cusTelNo) {
		this.cusTelNo = cusTelNo;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusUrl() {
		return cusUrl;
	}

	public void setCusUrl(String cusUrl) {
		this.cusUrl = cusUrl;
	}

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public Integer getFacSeq() {
		return facSeq;
	}

	public void setFacSeq(Integer facSeq) {
		this.facSeq = facSeq;
	}

	public Integer getFacAddrSeq() {
		return facAddrSeq;
	}

	public void setFacAddrSeq(Integer facAddrSeq) {
		this.facAddrSeq = facAddrSeq;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getFacHouseNo() {
		return facHouseNo;
	}

	public void setFacHouseNo(String facHouseNo) {
		this.facHouseNo = facHouseNo;
	}

	public String getFacAddrNo() {
		return facAddrNo;
	}

	public void setFacAddrNo(String facAddrNo) {
		this.facAddrNo = facAddrNo;
	}

	public String getFacBuildName() {
		return facBuildName;
	}

	public void setFacBuildName(String facBuildName) {
		this.facBuildName = facBuildName;
	}

	public String getFacFloorNo() {
		return facFloorNo;
	}

	public void setFacFloorNo(String facFloorNo) {
		this.facFloorNo = facFloorNo;
	}

	public String getFacRoomNo() {
		return facRoomNo;
	}

	public void setFacRoomNo(String facRoomNo) {
		this.facRoomNo = facRoomNo;
	}

	public String getFacMooNo() {
		return facMooNo;
	}

	public void setFacMooNo(String facMooNo) {
		this.facMooNo = facMooNo;
	}

	public String getFacVillage() {
		return facVillage;
	}

	public void setFacVillage(String facVillage) {
		this.facVillage = facVillage;
	}

	public String getFacSoiName() {
		return facSoiName;
	}

	public void setFacSoiName(String facSoiName) {
		this.facSoiName = facSoiName;
	}

	public String getFacThnName() {
		return facThnName;
	}

	public void setFacThnName(String facThnName) {
		this.facThnName = facThnName;
	}

	public String getFacTambolCode() {
		return facTambolCode;
	}

	public void setFacTambolCode(String facTambolCode) {
		this.facTambolCode = facTambolCode;
	}

	public String getFacTambolName() {
		return facTambolName;
	}

	public void setFacTambolName(String facTambolName) {
		this.facTambolName = facTambolName;
	}

	public String getFacAmphurCode() {
		return facAmphurCode;
	}

	public void setFacAmphurCode(String facAmphurCode) {
		this.facAmphurCode = facAmphurCode;
	}

	public String getFacAmphurName() {
		return facAmphurName;
	}

	public void setFacAmphurName(String facAmphurName) {
		this.facAmphurName = facAmphurName;
	}

	public String getFacProvinceCode() {
		return facProvinceCode;
	}

	public void setFacProvinceCode(String facProvinceCode) {
		this.facProvinceCode = facProvinceCode;
	}

	public String getFacProvinceName() {
		return facProvinceName;
	}

	public void setFacProvinceName(String facProvinceName) {
		this.facProvinceName = facProvinceName;
	}

	public String getFacZipCode() {
		return facZipCode;
	}

	public void setFacZipCode(String facZipCode) {
		this.facZipCode = facZipCode;
	}

	public String getFacTelNo() {
		return facTelNo;
	}

	public void setFacTelNo(String facTelNo) {
		this.facTelNo = facTelNo;
	}

	public String getFacEmail() {
		return facEmail;
	}

	public void setFacEmail(String facEmail) {
		this.facEmail = facEmail;
	}

	public String getFacUrl() {
		return facUrl;
	}

	public void setFacUrl(String facUrl) {
		this.facUrl = facUrl;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public LocalDateTime getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(LocalDateTime syncDate) {
		this.syncDate = syncDate;
	}

}
