package th.co.baiwa.excise.domain;

import java.util.List;

public class CommonManageReq<T> {
	
	private List<T> save;
	private List<T> delete;
	
	public List<T> getSave() {
		return save;
	}
	public void setSave(List<T> save) {
		this.save = save;
	}
	public List<T> getDelete() {
		return delete;
	}
	public void setDelete(List<T> delete) {
		this.delete = delete;
	}
	
}
