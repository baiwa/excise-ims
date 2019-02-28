package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int020201SidesVo {
	private BigDecimal idMadeHdr;
	private BigDecimal idSide;
	private String sideName;
	private BigDecimal idHead;
	private String status;
	List<Int020201SidesVo> request = null;
	
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
	public BigDecimal getIdHead() {
		return idHead;
	}
	public void setIdHead(BigDecimal idHead) {
		this.idHead = idHead;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Int020201SidesVo> getRequest() {
		return request;
	}
	public void setRequest(List<Int020201SidesVo> request) {
		this.request = request;
	}
	public BigDecimal getIdMadeHdr() {
		return idMadeHdr;
	}
	public void setIdMadeHdr(BigDecimal idMadeHdr) {
		this.idMadeHdr = idMadeHdr;
	}
	
}
