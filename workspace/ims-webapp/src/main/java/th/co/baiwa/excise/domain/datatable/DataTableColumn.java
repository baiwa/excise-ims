package th.co.baiwa.excise.domain.datatable;

import java.io.Serializable;

public class DataTableColumn implements Serializable{

    private static final long serialVersionUID = 2929396157253113231L;
    private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public boolean isOrderable() {
		return orderable;
	}
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}
	
}