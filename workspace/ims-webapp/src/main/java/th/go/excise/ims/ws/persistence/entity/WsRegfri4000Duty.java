package th.go.excise.ims.ws.persistence.entity;

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

	private static final long serialVersionUID = -9117319061433370887L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_REGFRI4000_DUTY_GEN")
	@SequenceGenerator(name = "WS_REGFRI4000_DUTY_GEN", sequenceName = "WS_REGFRI4000_DUTY_SEQ", allocationSize = 1)
	@Column(name = "REGFRI4000_DUTY_ID")
	private Long regfri4000DutyId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "GROUP_ID")
	private String groupId;
	@Column(name = "GROUP_NAME")
	private String groupName;

	public Long getRegfri4000DutyId() {
		return regfri4000DutyId;
	}

	public void setRegfri4000DutyId(Long regfri4000DutyId) {
		this.regfri4000DutyId = regfri4000DutyId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
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

}
