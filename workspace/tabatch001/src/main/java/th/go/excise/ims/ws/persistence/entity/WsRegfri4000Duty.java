package th.go.excise.ims.ws.persistence.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "WS_REGFRI4000_DUTY")
public class WsRegfri4000Duty extends BaseEntity {

	private static final long serialVersionUID = -261160718952572071L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_REGFRI4000_DUTY_GEN")
	@SequenceGenerator(name = "WS_REGFRI4000_DUTY_GEN", sequenceName = "WS_REGFRI4000_DUTY_SEQ", allocationSize = 1)
	@Column(name = "WS_REGFRI4000_DUTY_ID")
	private Long wsRegfri4000DutyId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "GROUP_ID")
	private String groupId;
	@Column(name = "GROUP_NAME")
	private String groupName;
	@Column(name = "REG_DATE")
	private LocalDate regDate;
	@Column(name = "PROD_DATE")
	private LocalDate prodDate;

	public Long getWsRegfri4000DutyId() {
		return wsRegfri4000DutyId;
	}

	public void setWsRegfri4000DutyId(Long wsRegfri4000DutyId) {
		this.wsRegfri4000DutyId = wsRegfri4000DutyId;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public LocalDate getProdDate() {
		return prodDate;
	}

	public void setProdDate(LocalDate prodDate) {
		this.prodDate = prodDate;
	}

}
