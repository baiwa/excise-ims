package th.go.excise.ims.ta.vo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaxOperatorVo {

	private List<String> condGroups;
	private List<TaxOperatorDatatableVo> datas;

	public List<String> getCondGroups() {
		return condGroups;
	}

	public void setCondGroups(List<String> condGroups) {
		this.condGroups = condGroups;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public List<TaxOperatorDatatableVo> getDatas() {
		return datas;
	}

	public void setDatas(List<TaxOperatorDatatableVo> datas) {
		this.datas = datas;
	}

}
