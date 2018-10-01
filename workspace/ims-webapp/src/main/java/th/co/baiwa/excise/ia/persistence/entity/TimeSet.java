package th.co.baiwa.excise.ia.persistence.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_TIME_SET database table.
 * 
 */
@Entity
@Table(name="IA_TIME_SET")
public class TimeSet extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 518669613166230545L;

	@Id
	@SequenceGenerator(name="IA_TIME_SET_GEN", sequenceName="IA_TIME_SET_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_TIME_SET_GEN")
	@Column(name="TIME_SET_ID")
	private long timeSetId;

	@Column(name="END_DATE_TIME")
	private Date endDateTime;

	@Column(name="START_DATE_TIME")
	private Date startDateTime;

	private String status;

	public TimeSet() {
	}

	public long getTimeSetId() {
		return timeSetId;
	}

	public void setTimeSetId(long timeSetId) {
		this.timeSetId = timeSetId;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}