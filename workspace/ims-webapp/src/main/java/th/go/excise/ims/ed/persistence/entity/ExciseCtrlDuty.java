
package th.go.excise.ims.ed.persistence.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;
import th.go.excise.ims.ed.persistence.entity.ExciseCtrlDutyPK;

@Entity
@Table(name = "EXCISE_CTRL_DUTY")
public class ExciseCtrlDuty extends BaseEntity {

	private static final long serialVersionUID = 7015514453033674519L;
	@EmbeddedId
	private ExciseCtrlDutyPK id;

	public ExciseCtrlDutyPK getId() {
		return id;
	}

	public void setId(ExciseCtrlDutyPK id) {
		this.id = id;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
