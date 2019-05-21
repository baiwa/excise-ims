
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
@Table(name = "WS_PM_QT_H")
public class WsPmQtH extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -210300396110657178L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_PM_QT_H_GEN")
	@SequenceGenerator(name = "WS_PM_QT_H_GEN", sequenceName = "WS_PM_QT_H_SEQ", allocationSize = 1)
	@Column(name = "PM_QT_H_SEQ")
	private Long pmQtHSeq;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "OFF_NAME")
	private String offName;
	@Column(name = "FORM_YEAR")
	private String formYear;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "FORM_NAME")
	private String formName;
	@Column(name = "FORM_ROUND")
	private String formRound;
	@Column(name = "FORM_STATUS")
	private String formStatus;
	@Column(name = "FORM_STATUS_DESC")
	private String formStatusDesc;
	@Column(name = "SUMMARY")
	private String summary;
	@Column(name = "PROCESS_BY")
	private String processBy;
	@Column(name = "PROCESS_POSITION")
	private String processPosition;
	@Column(name = "PROCESS_DATE")
	private Date processDate;

	public Long getPmQtHSeq() {
		return pmQtHSeq;
	}

	public void setPmQtHSeq(Long pmQtHSeq) {
		this.pmQtHSeq = pmQtHSeq;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public String getFormYear() {
		return formYear;
	}

	public void setFormYear(String formYear) {
		this.formYear = formYear;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormRound() {
		return formRound;
	}

	public void setFormRound(String formRound) {
		this.formRound = formRound;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getFormStatusDesc() {
		return formStatusDesc;
	}

	public void setFormStatusDesc(String formStatusDesc) {
		this.formStatusDesc = formStatusDesc;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
