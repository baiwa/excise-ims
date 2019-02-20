package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Int02010101Vo extends BaseEntity {
	private BigDecimal id;
	private BigDecimal idSide;
	private String sideName;
	private BigDecimal level;
	private BigDecimal seq;
	private BigDecimal seqDtl;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdSide() {
		return idSide;
	}

	public void setIdSide(BigDecimal idSide) {
		this.idSide = idSide;
	}

	public String getSideName() {
		return sideName;
	}

	public void setSideName(String sideName) {
		this.sideName = sideName;
	}

	public BigDecimal getLevel() {
		return level;
	}

	public void setLevel(BigDecimal level) {
		this.level = level;
	}

	public BigDecimal getSeq() {
		return seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public BigDecimal getSeqDtl() {
		return seqDtl;
	}

	public void setSeqDtl(BigDecimal seqDtl) {
		this.seqDtl = seqDtl;
	}
}
