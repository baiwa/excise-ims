package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;

public class Int0201Vo {
	
	List<IaQuestionnaireSideDtl> detail = null;
	private String sideName;
	private BigDecimal id;
	
	public List<IaQuestionnaireSideDtl> getDetail() {
		return detail;
	}
	public void setDetail(List<IaQuestionnaireSideDtl> detail) {
		this.detail = detail;
	}
	public String getSideName() {
		return sideName;
	}
	public void setSideName(String sideName) {
		this.sideName = sideName;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	

	
}
