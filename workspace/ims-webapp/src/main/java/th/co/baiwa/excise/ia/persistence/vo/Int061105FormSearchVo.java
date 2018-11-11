package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

public class Int061105FormSearchVo {
	private String status;
	private String withdrawRequest;
	private BigDecimal idSelect;
	private String comment;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWithdrawRequest() {
		return withdrawRequest;
	}
	public void setWithdrawRequest(String withdrawRequest) {
		this.withdrawRequest = withdrawRequest;
	}
	public BigDecimal getIdSelect() {
		return idSelect;
	}
	public void setIdSelect(BigDecimal idSelect) {
		this.idSelect = idSelect;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
