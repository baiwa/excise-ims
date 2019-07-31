package th.go.excise.ims.ta.vo;

import java.time.LocalDate;
import java.util.List;

public class Incfri8000MFormVo {

	private String dateType;
	private List<LocalDate> localDateList;

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public List<LocalDate> getLocalDateList() {
		return localDateList;
	}

	public void setLocalDateList(List<LocalDate> localDateList) {
		this.localDateList = localDateList;
	}

}
