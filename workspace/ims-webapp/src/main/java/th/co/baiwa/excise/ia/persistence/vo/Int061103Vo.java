package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFeeChild;

public class Int061103Vo extends TuitionFee {

	private static final long serialVersionUID = 5147027989259232799L;
	List<TuitionFeeChild> items;

	public List<TuitionFeeChild> getItems() {
		return items;
	}

	public void setItems(List<TuitionFeeChild> items) {
		this.items = items;
	}

}
