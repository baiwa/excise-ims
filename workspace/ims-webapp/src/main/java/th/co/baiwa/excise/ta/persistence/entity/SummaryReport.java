package th.co.baiwa.excise.ta.persistence.entity;

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
@Table(name = "TA_SUMMARY_REPORT")
public class SummaryReport extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7900898407789873855L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_SUMMARY_REPORT_GEN")
	@SequenceGenerator(name = "TA_SUMMARY_REPORT_GEN", sequenceName = "TA_SUMMARY_REPORT_SEQ", allocationSize = 1)

	@Column(name = "ID")
	private Long id;

	@Column(name = "SEND_DATE")
	private Date sendDate;

	@Column(name = "RECEIVE_DATE")
	private Date receiveDate;

	@Column(name = "SECTOR")
	private String sector;

	@Column(name = "ANALYSNUMBER")
	private String analysnumber;

	@Column(name = "CENTER")
	private String center;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getAnalysnumber() {
		return analysnumber;
	}

	public void setAnalysnumber(String analysnumber) {
		this.analysnumber = analysnumber;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

}
