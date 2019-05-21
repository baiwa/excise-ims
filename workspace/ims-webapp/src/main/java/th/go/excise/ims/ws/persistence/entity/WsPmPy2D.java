
package th.go.excise.ims.ws.persistence.entity;

import java.util.Date;
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
@Table(name = "WS_PM_PY2_D")
public class WsPmPy2D extends BaseEntity {

	private static final long serialVersionUID = 6135243224394816006L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_PM_PY2_D_GEN")
	@SequenceGenerator(name = "WS_PM_PY2_D_GEN", sequenceName = "WS_PM_PY2_D_SEQ", allocationSize = 1)
	@Column(name = "PM_PY2_D_SEQ")
	private Long pmPy2DSeq;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "DIV_SEQ")
	private String divSeq;
	@Column(name = "DIV_NAME")
	private String divName;
	@Column(name = "JOB_NAME")
	private String jobName;
	@Column(name = "PROCESS_BY")
	private String processBy;
	@Column(name = "PROCESS_POSITION")
	private String processPosition;
	@Column(name = "PROCESS_DATE")
	private Date processDate;
	@Column(name = "PY2_TOPIC_SEQ")
	private String py2TopicSeq;
	@Column(name = "PY2_TOPIC_NAME")
	private String py2TopicName;
	@Column(name = "PY2_TOPIC1_MAIN")
	private String py2Topic1Main;
	@Column(name = "PY2_TOPIC2_CTL")
	private String py2Topic2Ctl;
	@Column(name = "PY2_TOPIC3_ASSESS")
	private String py2Topic3Assess;
	@Column(name = "PY2_TOPIC4_RISK")
	private String py2Topic4Risk;
	@Column(name = "PY2_TOPIC5_IMPROVE")
	private String py2Topic5Improve;
	@Column(name = "PY2_TOPIC6_OWNER")
	private String py2Topic6Owner;
	@Column(name = "PY2_TOPIC7_REMARK")
	private String py2Topic7Remark;

	public Long getPmPy2DSeq() {
		return pmPy2DSeq;
	}

	public void setPmPy2DSeq(Long pmPy2DSeq) {
		this.pmPy2DSeq = pmPy2DSeq;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getDivSeq() {
		return divSeq;
	}

	public void setDivSeq(String divSeq) {
		this.divSeq = divSeq;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getProcessBy() {
		return processBy;
	}

	public void setProcessBy(String processBy) {
		this.processBy = processBy;
	}

	public String getProcessPosition() {
		return processPosition;
	}

	public void setProcessPosition(String processPosition) {
		this.processPosition = processPosition;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getPy2TopicSeq() {
		return py2TopicSeq;
	}

	public void setPy2TopicSeq(String py2TopicSeq) {
		this.py2TopicSeq = py2TopicSeq;
	}

	public String getPy2TopicName() {
		return py2TopicName;
	}

	public void setPy2TopicName(String py2TopicName) {
		this.py2TopicName = py2TopicName;
	}

	public String getPy2Topic1Main() {
		return py2Topic1Main;
	}

	public void setPy2Topic1Main(String py2Topic1Main) {
		this.py2Topic1Main = py2Topic1Main;
	}

	public String getPy2Topic2Ctl() {
		return py2Topic2Ctl;
	}

	public void setPy2Topic2Ctl(String py2Topic2Ctl) {
		this.py2Topic2Ctl = py2Topic2Ctl;
	}

	public String getPy2Topic3Assess() {
		return py2Topic3Assess;
	}

	public void setPy2Topic3Assess(String py2Topic3Assess) {
		this.py2Topic3Assess = py2Topic3Assess;
	}

	public String getPy2Topic4Risk() {
		return py2Topic4Risk;
	}

	public void setPy2Topic4Risk(String py2Topic4Risk) {
		this.py2Topic4Risk = py2Topic4Risk;
	}

	public String getPy2Topic5Improve() {
		return py2Topic5Improve;
	}

	public void setPy2Topic5Improve(String py2Topic5Improve) {
		this.py2Topic5Improve = py2Topic5Improve;
	}

	public String getPy2Topic6Owner() {
		return py2Topic6Owner;
	}

	public void setPy2Topic6Owner(String py2Topic6Owner) {
		this.py2Topic6Owner = py2Topic6Owner;
	}

	public String getPy2Topic7Remark() {
		return py2Topic7Remark;
	}

	public void setPy2Topic7Remark(String py2Topic7Remark) {
		this.py2Topic7Remark = py2Topic7Remark;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
