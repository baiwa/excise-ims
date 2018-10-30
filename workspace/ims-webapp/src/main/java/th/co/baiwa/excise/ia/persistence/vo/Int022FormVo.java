package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnMaster;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnTimeAlert;

public class Int022FormVo extends QtnMaster {
	List<QtnTimeAlert> alerts;

	public List<QtnTimeAlert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<QtnTimeAlert> alerts) {
		this.alerts = alerts;
	}

}
