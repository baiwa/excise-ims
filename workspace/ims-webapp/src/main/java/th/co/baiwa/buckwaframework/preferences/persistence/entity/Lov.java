
package th.co.baiwa.buckwaframework.preferences.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "SYS_LOV")
public class Lov extends BaseEntity{
	
	private static final long serialVersionUID = -3853295860150204153L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOV_GEN")
	@SequenceGenerator(name = "LOV_GEN", sequenceName = "LOV_SEQ", allocationSize = 1)
	@Column(name = "LOV_ID")
	private Long lovId;
	
	@Column(name = "LOV_ID_MASTER")
	private Long lovIdMaster;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "TYPE_DESCRIPTION")
	private String typeDescription;
	
	@Column(name = "SUB_TYPE")
	private String subType;
	
	@Column(name = "SUB_TYPE_DESCRIPTION")
	private String subTypeDescription;
	
	@Column(name = "VALUE1")
	private String value1;
	
	@Column(name = "VALUE2")
	private String value2;
	
	@Column(name = "VALUE3")
	private String value3;
	
	@Column(name = "VALUE4")
	private String value4;
	
	@Column(name = "VALUE5")
	private String value5;
	
	public Lov(String lovType) {
		this.type = lovType;
	}
	public Lov() {
		
	}
	
	public Long getLovId() {
		return lovId;
	}
	public void setLovId(Long lovId) {
		this.lovId = lovId;
	}
	public Long getLovIdMaster() {
		return lovIdMaster;
	}
	public void setLovIdMaster(Long lovIdMaster) {
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
	public void setValue2(String value2) {
		this.value2 = value2;
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
	
	
}
