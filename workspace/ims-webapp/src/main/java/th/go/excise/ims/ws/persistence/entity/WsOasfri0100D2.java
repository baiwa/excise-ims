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
@Table(name = "WS_OASFRI0100_DATA_ENTRY")
public class WsOasfri0100D2 extends BaseEntity {

	private static final long serialVersionUID = -6249382434275690475L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_OASFRI0100_DATA_ENTRY_GEN")
	@SequenceGenerator(name = "WS_OASFRI0100_DATA_ENTRY_GEN", sequenceName = "WS_OASFRI0100_DATA_ENTRY_SEQ", allocationSize = 1)
	@Column(name = "OASFRI0100_D2_SEQ")
	private Long oasfri0100D2Seq;
	@Column(name = "DATA_TYPE")
	private String dataType;
	@Column(name = "FORMDOC_REC0142_NO")
	private String formdocRec0142No;
	@Column(name = "DATA_ID")
	private String dataId;
	@Column(name = "SEQ_NO")
	private String seqNo;
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	@Column(name = "IN_QTY")
	private String inQty;

	public Long getOasfri0100D2Seq() {
		return oasfri0100D2Seq;
	}

	public void setOasfri0100D2Seq(Long oasfri0100d2Seq) {
		oasfri0100D2Seq = oasfri0100d2Seq;
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

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getInQty() {
		return inQty;
	}

	public void setInQty(String inQty) {
		this.inQty = inQty;
	}

}
