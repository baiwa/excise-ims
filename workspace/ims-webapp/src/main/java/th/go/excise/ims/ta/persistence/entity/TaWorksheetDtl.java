
package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_WORKSHEET_DTL")
public class TaWorksheetDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6358831230496999116L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_DTL_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_DTL_GEN", sequenceName = "TA_WORKSHEET_DTL_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_DTL_ID")
	private Long worksheetDtlId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "COND_MAIN_GRP")
	private String condMainGrp;
	@Column(name = "CENTER_SEL_FLAG")
	private String centerSelFlag;
	@Column(name = "SECTOR_SEL_FLAG")
	private String sectorSelFlag;
	@Column(name = "AREA_SEL_FLAG")
	private String areaSelFlag;

	
	public Long getWorksheetDtlId() {
		return worksheetDtlId;
	}
	
	public void setWorksheetDtlId(Long worksheetDtlId) {
		this.worksheetDtlId = worksheetDtlId;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCondMainGrp() {
		return condMainGrp;
	}

	public void setCondMainGrp(String condMainGrp) {
		this.condMainGrp = condMainGrp;
	}

	public String getCenterSelFlag() {
		return centerSelFlag;
	}

	public void setCenterSelFlag(String centerSelFlag) {
		this.centerSelFlag = centerSelFlag;
	}

	public String getSectorSelFlag() {
		return sectorSelFlag;
	}

	public void setSectorSelFlag(String sectorSelFlag) {
		this.sectorSelFlag = sectorSelFlag;
	}

	public String getAreaSelFlag() {
		return areaSelFlag;
	}

	public void setAreaSelFlag(String areaSelFlag) {
		this.areaSelFlag = areaSelFlag;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
