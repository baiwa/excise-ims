package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnMaster;

public class Int021Vo extends QtnMaster {

	private static final long serialVersionUID = 2550374911108257886L;

	private Integer start;
	private Integer length;
	private Long draw;
	private String isInit;
	private String qtnFrom;
	private String qtnTo;
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Long getDraw() {
		return draw;
	}
	public void setDraw(Long draw) {
		this.draw = draw;
	}
	public String getIsInit() {
		return isInit;
	}
	public void setIsInit(String isInit) {
		this.isInit = isInit;
	}
	public String getQtnFrom() {
		return qtnFrom;
	}
	public void setQtnFrom(String qtnFrom) {
		this.qtnFrom = qtnFrom;
	}
	public String getQtnTo() {
		return qtnTo;
	}
	public void setQtnTo(String qtnTo) {
		this.qtnTo = qtnTo;
	}
}
