package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;

public class Int0201FormVo {
	
	private BigDecimal id;
	private String sideName;
	List<IaQuestionnaireMade> qtnMadeList = null;
	private String startDateSend;
	private String endDateSend;
	private BigDecimal idSide;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getSideName() {
		return sideName;
	}
	public void setSideName(String sideName) {
		this.sideName = sideName;
	}
	public List<IaQuestionnaireMade> getQtnMadeList() {
		return qtnMadeList;
	}
	public void setQtnMadeList(List<IaQuestionnaireMade> qtnMadeList) {
		this.qtnMadeList = qtnMadeList;
	}
	public String getStartDateSend() {
		return startDateSend;
	}
	public void setStartDateSend(String startDateSend) {
		this.startDateSend = startDateSend;
	}
	public String getEndDateSend() {
		return endDateSend;
	}
	public void setEndDateSend(String endDateSend) {
		this.endDateSend = endDateSend;
	}
	public BigDecimal getIdSide() {
		return idSide;
	}
	public void setIdSide(BigDecimal idSide) {
		this.idSide = idSide;
	}
	
}
