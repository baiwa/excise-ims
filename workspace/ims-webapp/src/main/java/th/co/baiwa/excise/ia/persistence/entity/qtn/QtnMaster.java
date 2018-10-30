package th.co.baiwa.excise.ia.persistence.entity.qtn;

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
@Table(name = "IA_QTN_MASTER")
public class QtnMaster extends BaseEntity {
	
	private static final long serialVersionUID = 6671240606864438530L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_MASTER_ID")
	@SequenceGenerator(name = "IA_QTN_MASTER_ID", sequenceName = "IA_QTN_MASTER_SEQ", allocationSize = 1)
	@Column(name = "QTN_MASTER_ID")
	private Long qtnMasterId;
	
	@Column(name = "QTN_NAME")
	private String qtnName;
	
	@Column(name = "QTN_YEAR")
	private String qtnYear;
	
	@Column(name = "QTN_FINISHED")
	private String qtnFinished;
	
	@Column(name = "QTN_SECTOR")
	private String qtnSector;
	
	@Column(name = "QTN_START")
	private Date qtnStart;
	
	@Column(name = "QTN_END")
	private Date qtnEnd;
	
	public Date getQtnStart() {
		return qtnStart;
	}
	public void setQtnStart(Date qtnStart) {
		this.qtnStart = qtnStart;
	}
	public Date getQtnEnd() {
		return qtnEnd;
	}
	public void setQtnEnd(Date qtnEnd) {
		this.qtnEnd = qtnEnd;
	}
	public String getQtnSector() {
		return qtnSector;
	}
	public void setQtnSector(String qtnSector) {
		this.qtnSector = qtnSector;
	}
	public String getQtnFinished() {
		return qtnFinished;
	}
	public void setQtnFinished(String qtnFinished) {
		this.qtnFinished = qtnFinished;
	}
	public Long getQtnMasterId() {
		return qtnMasterId;
	}
	public void setQtnMasterId(Long qtnMasterId) {
		this.qtnMasterId = qtnMasterId;
	}
	public String getQtnName() {
		return qtnName;
	}
	public void setQtnName(String qtnName) {
		this.qtnName = qtnName;
	}
	public String getQtnYear() {
		return qtnYear;
	}
	public void setQtnYear(String qtnYear) {
		this.qtnYear = qtnYear;
	}
}