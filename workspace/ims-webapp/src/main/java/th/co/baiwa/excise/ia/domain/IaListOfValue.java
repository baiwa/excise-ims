package th.co.baiwa.excise.ia.domain;

import java.math.BigDecimal;
import java.util.Date;

public class IaListOfValue {
	
	private BigDecimal lovId;
	private String rype;
	private String rypeDescription;
	private String value;
	private String valueDescription;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	
	
	public BigDecimal getLovId() {
		return lovId;
	}
	public void setLovId(BigDecimal lovId) {
		this.lovId = lovId;
	}
	public String getRype() {
		return rype;
	}
	public void setRype(String rype) {
		this.rype = rype;
	}
	public String getRypeDescription() {
		return rypeDescription;
	}
	public void setRypeDescription(String rypeDescription) {
		this.rypeDescription = rypeDescription;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValueDescription() {
		return valueDescription;
	}
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
