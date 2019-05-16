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
@Table(name = "WS_OASFRI0100_DATA")
public class WsOasfri0100D1 extends BaseEntity {

	private static final long serialVersionUID = -442553327564073690L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_OASFRI0100_DATA_GEN")
	@SequenceGenerator(name = "WS_OASFRI0100_DATA_GEN", sequenceName = "WS_OASFRI0100_DATA_SEQ", allocationSize = 1)
	@Column(name = "OASFRI0100_D1_SEQ")
	private Long oasfri0100D1Seq;
	@Column(name = "DATA_TYPE")
	private String dataType;
	@Column(name = "FORMDOC_REC0142_NO")
	private String formdocRec0142No;
	@Column(name = "DATA_SEQ")
	private String dataSeq;
	@Column(name = "DATA_ID")
	private String dataId;
	@Column(name = "DATA_NAME")
	private String dataName;
	@Column(name = "BAL_BF_QTY")
	private String balBfQty;

	public Long getOasfri0100D1Seq() {
		return oasfri0100D1Seq;
	}

	public void setOasfri0100D1Seq(Long oasfri0100d1Seq) {
		oasfri0100D1Seq = oasfri0100d1Seq;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFormdocRec0142No() {
		return formdocRec0142No;
	}

	public void setFormdocRec0142No(String formdocRec0142No) {
		this.formdocRec0142No = formdocRec0142No;
	}

	public String getDataSeq() {
		return dataSeq;
	}

	public void setDataSeq(String dataSeq) {
		this.dataSeq = dataSeq;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getBalBfQty() {
		return balBfQty;
	}

	public void setBalBfQty(String balBfQty) {
		this.balBfQty = balBfQty;
	}

}
