package th.go.excise.ims.mockup.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ListOfValue {
	private BigDecimal lovId;
	private String type;
	private String typeDescription;
	private String value;
	private String typeDrescription;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	public BigDecimal getLovId() {
		return lovId;
	}
	public void setLovId(BigDecimal lovId) {
		this.lovId = lovId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTypeDrescription() {
		return typeDrescription;
	}
	public void setTypeDrescription(String typeDrescription) {
		this.typeDrescription = typeDrescription;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}
