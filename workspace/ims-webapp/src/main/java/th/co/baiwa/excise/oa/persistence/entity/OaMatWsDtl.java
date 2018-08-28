package th.co.baiwa.excise.oa.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name="OA_MAT_WS_DTL")
public class OaMatWsDtl extends BaseEntity {

	private static final long serialVersionUID = -7145030173738930156L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_MAT_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_MAT_WS_DTL_GEN", sequenceName = "OA_MAT_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="MAT_WS_DTL_ID")
	private Long matWsDtlId;

	@Column(name="MAT_WS_HDR_ID")
	private Long matWsHdrId;
	
	@Column(name="MAT_WS_DTL_NO")
	private Long matWsDtlNo;
	
	@Column(name="MAT_WS_DTL_ORDER")
	private String matWsDtlOrder;
	
	@Column(name="MAT_WS_DTL_BALANCE")
	private BigDecimal  matWsDtlBalance;
	
	@Column(name="MAT_WS_DTL_COUNTING")
	private BigDecimal matWsDtlCounting;
	
	public Long getMatWsDtlId() {
		return matWsDtlId;
	}

	public void setMatWsDtlId(Long matWsDtlId) {
		this.matWsDtlId = matWsDtlId;
	}

	

	public BigDecimal getMatWsDtlBalance() {
		return matWsDtlBalance;
	}

	public void setMatWsDtlBalance(BigDecimal matWsDtlBalance) {
		this.matWsDtlBalance = matWsDtlBalance;
	}

	public BigDecimal getMatWsDtlCounting() {
		return matWsDtlCounting;
	}

	public void setMatWsDtlCounting(BigDecimal matWsDtlCounting) {
		this.matWsDtlCounting = matWsDtlCounting;
	}

	public Long getMatWsDtlNo() {
		return matWsDtlNo;
	}

	public void setMatWsDtlNo(Long matWsDtlNo) {
		this.matWsDtlNo = matWsDtlNo;
	}

	public String getMatWsDtlOrder() {
		return matWsDtlOrder;
	}

	public void setMatWsDtlOrder(String matWsDtlOrder) {
		this.matWsDtlOrder = matWsDtlOrder;
	}

	public Long getMatWsHdrId() {
		return matWsHdrId;
	}

	public void setMatWsHdrId(Long matWsHdrId) {
		this.matWsHdrId = matWsHdrId;
	}

}