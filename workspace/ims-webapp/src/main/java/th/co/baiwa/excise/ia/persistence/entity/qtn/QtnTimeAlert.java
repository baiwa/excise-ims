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
@Table(name = "IA_QTN_TIME_ALERT")
public class QtnTimeAlert extends BaseEntity {

	private static final long serialVersionUID = -8551966463309080530L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_ALERT_GEN")
	@SequenceGenerator(name = "IA_QTN_ALERT_GEN", sequenceName = "IA_QTN_ALERT_SEQ", allocationSize = 1)
	@Column(name = "QTN_ALERT_ID")
	private Long qtnAlertId;

	@Column(name = "QTN_ALERT_TIME")
	private Date qtnAlertTime;

	@Column(name = "QTN_TIMES")
	private Integer qtnTimes;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "QTN_MASTER_ID")
	private Long qtnMasterId;

	public Long getQtnAlertId() {
		return qtnAlertId;
	}

	public void setQtnAlertId(Long qtnAlertId) {
		this.qtnAlertId = qtnAlertId;
	}

	public Date getQtnAlertTime() {
		return qtnAlertTime;
	}

	public void setQtnAlertTime(Date qtnAlertTime) {
		this.qtnAlertTime = qtnAlertTime;
	}

	public Integer getQtnTimes() {
		return qtnTimes;
	}

	public void setQtnTimes(Integer qtnTimes) {
		this.qtnTimes = qtnTimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getQtnMasterId() {
		return qtnMasterId;
	}

	public void setQtnMasterId(Long qtnMasterId) {
		this.qtnMasterId = qtnMasterId;
	}

}
