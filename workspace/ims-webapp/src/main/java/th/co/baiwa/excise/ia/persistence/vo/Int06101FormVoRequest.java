package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

public class Int06101FormVoRequest {
	Int06101FormVo data;
	List<Long> delete;
	public Int06101FormVo getData() {
		return data;
	}
	public void setData(Int06101FormVo data) {
		this.data = data;
	}
	public List<Long> getDelete() {
		return delete;
	}
	public void setDelete(List<Long> delete) {
		this.delete = delete;
	}
}
