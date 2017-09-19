package th.co.baiwa.exampleproject.examplemodule.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BuckwaTest {

	private Long colPk;
	private String colVarchar;
	private Integer colInt;
	private Double colDouble;
	private BigDecimal colDecimal;
	private Date colTimestamp;
	private Date colDate;
	private Date colTime;

	public Long getColPk() {
		return colPk;
	}

	public void setColPk(Long colPk) {
		this.colPk = colPk;
	}

	public String getColVarchar() {
		return colVarchar;
	}

	public void setColVarchar(String colVarchar) {
		this.colVarchar = colVarchar;
	}

	public Integer getColInt() {
		return colInt;
	}

	public void setColInt(Integer colInt) {
		this.colInt = colInt;
	}

	public Double getColDouble() {
		return colDouble;
	}

	public void setColDouble(Double colDouble) {
		this.colDouble = colDouble;
	}

	public BigDecimal getColDecimal() {
		return colDecimal;
	}

	public void setColDecimal(BigDecimal colDecimal) {
		this.colDecimal = colDecimal;
	}

	public Date getColTimestamp() {
		return colTimestamp;
	}

	public void setColTimestamp(Date colTimestamp) {
		this.colTimestamp = colTimestamp;
	}

	public Date getColDate() {
		return colDate;
	}

	public void setColDate(Date colDate) {
		this.colDate = colDate;
	}

	public Date getColTime() {
		return colTime;
	}

	public void setColTime(Date colTime) {
		this.colTime = colTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
