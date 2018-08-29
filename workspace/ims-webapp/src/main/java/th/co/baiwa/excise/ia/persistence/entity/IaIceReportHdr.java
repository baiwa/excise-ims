package th.co.baiwa.excise.ia.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name="IA_ICE_REPORT_HDR")
public class IaIceReportHdr extends BaseEntity {
	
	private static final long serialVersionUID = 3537128309980862396L;
	
	@Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_ICE_REPORT_HDR_GEN" )
	@SequenceGenerator(name = "IA_ICE_REPORT_HDR_GEN", sequenceName = "IA_ICE_REPORT_HDR_SEQ", allocationSize = 1)	
	@Column(name="ICE_REPORT_HDR_ID")
	private Long iceReportHdrId;
	
	@Column(name="SUBSECTION_NAME")
	private String subSectionName;
	
	@Column(name="END_DATE")
	private Date endDate;

	public Long getIceReportHdrId() {
		return iceReportHdrId;
	}
	public void setIceReportHdrId(Long iceReportHdrId) {
		this.iceReportHdrId = iceReportHdrId;
	}
	public String getSubSectionName() {
		return subSectionName;
	}
	public void setSubSectionName(String subSectionName) {
		this.subSectionName = subSectionName;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
