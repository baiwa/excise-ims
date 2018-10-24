package th.co.baiwa.excise.ia.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_CWP_SCWD_HDR database table.
 * 
 */
@Entity
@Table(name="IA_CWP_SCWD_HDR")
public class CwpScwdHdr extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1462448060908091159L;

	@Id
	@SequenceGenerator(name="IA_CWP_SCWD_HDR_GEN", sequenceName="IA_CWP_SCWD_HDR_SEQ", allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_CWP_SCWD_HDR_GEN")
	@Column(name="CWP_SCWD_HDR_ID")
	private long cwpScwdHdrId;

	@Column(name="DATE_DOCUMENT_END")
	private Date dateDocumentEnd;

	@Column(name="DATE_DOCUMENT_START")
	private Date dateDocumentStart;

	@Column(name="DATE_REPORT")
	private Date dateReport;

	private String department;

	@Column(name="DISBURSEMENT_CODE")
	private String disbursementCode;

	@Column(name="DISBURSEMENT_NAME")
	private String disbursementName;
	
	@Column(name="FILE_UPLOAD_ID")
	private Long fileUploadID;

	public long getCwpScwdHdrId() {
		return cwpScwdHdrId;
	}

	public void setCwpScwdHdrId(long cwpScwdHdrId) {
		this.cwpScwdHdrId = cwpScwdHdrId;
	}

	public Date getDateDocumentEnd() {
		return dateDocumentEnd;
	}

	public void setDateDocumentEnd(Date dateDocumentEnd) {
		this.dateDocumentEnd = dateDocumentEnd;
	}

	public Date getDateDocumentStart() {
		return dateDocumentStart;
	}

	public void setDateDocumentStart(Date dateDocumentStart) {
		this.dateDocumentStart = dateDocumentStart;
	}

	public Date getDateReport() {
		return dateReport;
	}

	public void setDateReport(Date dateReport) {
		this.dateReport = dateReport;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDisbursementCode() {
		return disbursementCode;
	}

	public void setDisbursementCode(String disbursementCode) {
		this.disbursementCode = disbursementCode;
	}

	public String getDisbursementName() {
		return disbursementName;
	}

	public void setDisbursementName(String disbursementName) {
		this.disbursementName = disbursementName;
	}

	public Long getFileUploadID() {
		return fileUploadID;
	}

	public void setFileUploadID(Long fileUploadID) {
		this.fileUploadID = fileUploadID;
	}
	

}