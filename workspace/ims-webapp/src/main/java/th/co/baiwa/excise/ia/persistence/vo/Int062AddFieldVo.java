package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;

public class Int062AddFieldVo extends CwpScwdDtl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8655011815477694741L;
	private List<Int062paymentInfoVo> paymentInfoVoList;
	private Long withdrawalId;
	private String refNum;
	private BigDecimal withdrawalAmount;
	private BigDecimal receivedAmount;
	private String withdrawalDocNum;
	private String listName;
	private BigDecimal diffReceived;
	private BigDecimal diffWithdraw;
	private BigDecimal totalDiffReceived;
	private BigDecimal totalDiffWithdraw;
	
	public Int062AddFieldVo() {
		withdrawalAmount = new BigDecimal(0);
		receivedAmount = new BigDecimal(0);
		diffReceived = new BigDecimal(0);
		diffWithdraw = new BigDecimal(0);
		totalDiffReceived = new BigDecimal(0);
		totalDiffWithdraw = new BigDecimal(0);
	}
	
	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}
	public BigDecimal getWithdrawalAmount() {
		return withdrawalAmount;
	}
	public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}
	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	public String getWithdrawalDocNum() {
		return withdrawalDocNum;
	}
	public void setWithdrawalDocNum(String withdrawalDocNum) {
		this.withdrawalDocNum = withdrawalDocNum;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public Long getWithdrawalId() {
		return withdrawalId;
	}
	public void setWithdrawalId(Long withdrawalId) {
		this.withdrawalId = withdrawalId;
	}
	public List<Int062paymentInfoVo> getPaymentInfoVoList() {
		return paymentInfoVoList;
	}
	public void setPaymentInfoVoList(List<Int062paymentInfoVo> paymentInfoVoList) {
		this.paymentInfoVoList = paymentInfoVoList;
	}

	public BigDecimal getDiffReceived() {
		return diffReceived;
	}

	public void setDiffReceived(BigDecimal diffReceived) {
		this.diffReceived = diffReceived;
	}

	public BigDecimal getDiffWithdraw() {
		return diffWithdraw;
	}

	public void setDiffWithdraw(BigDecimal diffWithdraw) {
		this.diffWithdraw = diffWithdraw;
	}

	public BigDecimal getTotalDiffReceived() {
		return totalDiffReceived;
	}

	public void setTotalDiffReceived(BigDecimal totalDiffReceived) {
		this.totalDiffReceived = totalDiffReceived;
	}

	public BigDecimal getTotalDiffWithdraw() {
		return totalDiffWithdraw;
	}

	public void setTotalDiffWithdraw(BigDecimal totalDiffWithdraw) {
		this.totalDiffWithdraw = totalDiffWithdraw;
	}

}
