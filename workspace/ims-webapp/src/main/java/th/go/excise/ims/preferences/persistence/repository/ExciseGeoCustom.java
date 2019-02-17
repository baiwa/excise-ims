package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.persistence.entity.ExciseGeo;

public interface ExciseGeoCustom {
	public List<ExciseGeo> findByCriteria(ExciseGeo exciseGeo);
}
