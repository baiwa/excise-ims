package th.go.excise.ims.ta.vo;

import java.util.List;

public class TaFormTS0303Vo {
	private String formTsNumber;
	private List<TaFormTS0303DtlVo> taFormTS0303DtlVoList;

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public List<TaFormTS0303DtlVo> getTaFormTS0303DtlVoList() {
		return taFormTS0303DtlVoList;
	}

	public void setTaFormTS0303DtlVoList(List<TaFormTS0303DtlVo> taFormTS0303DtlVoList) {
		this.taFormTS0303DtlVoList = taFormTS0303DtlVoList;
	}

}
