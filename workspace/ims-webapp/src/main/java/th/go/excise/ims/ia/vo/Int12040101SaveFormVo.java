package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaExpenses;

public class Int12040101SaveFormVo extends IaExpenses {
	private static final long serialVersionUID = 2721635924220256250L;
	private String expenseDateStr;
	private String area;

	public String getExpenseDateStr() {
		return expenseDateStr;
	}

	public void setExpenseDateStr(String expenseDateStr) {
		this.expenseDateStr = expenseDateStr;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
