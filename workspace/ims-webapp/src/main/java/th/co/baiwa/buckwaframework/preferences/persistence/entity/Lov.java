package th.co.baiwa.buckwaframework.preferences.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Lov extends BaseEntity{
	private BigDecimal lovId;
	private BigDecimal lovIdMaster;
	private String type;
	private String typeDescription;
	private String subType;
	private String subTypeDescription;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String value5;
	
	
	public Lov() {
		
	}
	
	public Lov(String lovType) {
		this.type = lovType;
	}
	
	public Lov(String lovType ,String lovSubType) {
		this.type = lovType;
		this.subType = lovSubType;
	}
	
	public BigDecimal getLovId() {
		return lovId;
	}
	public void setLovId(BigDecimal lovId) {
		this.lovId = lovId;
	}
	public BigDecimal getLovIdMaster() {
		return lovIdMaster;
	}
	public void setLovIdMaster(BigDecimal lovIdMaster) {
		this.lovIdMaster = lovIdMaster;
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
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getSubTypeDescription() {
		return subTypeDescription;
	}
	public void setSubTypeDescription(String subTypeDescription) {
		this.subTypeDescription = subTypeDescription;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
}
