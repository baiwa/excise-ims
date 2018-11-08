package th.co.baiwa.excise.ia.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "IA_WITHDRAW_FILE_UPLOAD")
public class WithdrawFileUpload extends BaseEntity {

	private static final long serialVersionUID = 371463744375926625L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_WITHDRAW_FILE_UPLOAD_GEN")
	@SequenceGenerator(name = "IA_WITHDRAW_FILE_UPLOAD_GEN", sequenceName = "IA_WITHDRAW_FILE_UPLOAD_SEQ", allocationSize = 1)

	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ID_MASTER")
	private BigDecimal idMaster;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "FILENAME")
	private String filename;
	@Column(name = "PATH_FIIL")
	private String pathFiil;
	@Column(name = "VALUE_1")
	private String value1;
	@Column(name = "VALUE_2")
	private String value2;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdMaster() {
		return idMaster;
	}

	public void setIdMaster(BigDecimal idMaster) {
		this.idMaster = idMaster;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPathFiil() {
		return pathFiil;
	}

	public void setPathFiil(String pathFiil) {
		this.pathFiil = pathFiil;
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

}