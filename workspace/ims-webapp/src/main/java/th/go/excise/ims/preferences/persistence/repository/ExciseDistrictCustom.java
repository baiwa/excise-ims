package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.persistence.entity.ExciseDistrict;

public interface ExciseDistrictCustom {
	public List<ExciseDistrict> findByCriteria(ExciseDistrict exiseDistrict);
}
