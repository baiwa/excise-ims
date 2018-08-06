package th.co.baiwa.excise.domain.datatable;

import java.util.ArrayList;
import java.util.List;

public class DataTableAjax<T> {
	private Long draw = 0l;
	private Long recordsTotal = 0l;
	private Long recordsFiltered = 0l;
	private List<T> data = new ArrayList<>();

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
