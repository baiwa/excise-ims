package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.persistence.entity.ExciseAmphur;

public interface ExciseAmphurCustom {
	public List<ExciseAmphur> findByCriteria(ExciseAmphur exciseAmphur);
}
