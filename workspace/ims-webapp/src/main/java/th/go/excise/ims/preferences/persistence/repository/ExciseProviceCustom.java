package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.persistence.entity.ExciseGeo;
import th.go.excise.ims.preferences.persistence.entity.ExciseProvice;

public interface ExciseProviceCustom {
	public List<ExciseProvice> findByCriteria(ExciseProvice exciseProvice);
}
