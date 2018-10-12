package th.co.baiwa.excise.domain.datatable;

import java.io.Serializable;
import java.util.List;

public class DataTableSortRequest implements Serializable {

    private static final long serialVersionUID = -3920942461824199924L;
    private Integer start;
	private Integer length;
	private Long draw;
	private List<DataTableColumn> columns;
	private List<DataTableOrder> order;

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

	public List<DataTableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DataTableColumn> columns) {
		this.columns = columns;
	}

	public List<DataTableOrder> getOrder() {
		return order;
	}

	public void setOrder(List<DataTableOrder> order) {
		this.order = order;
	}

}
