package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.License;

public interface LicenseRepositoryCustom {
	public List<License> searchLicenseByLicDateOffCode(String startDate , String endDate , String offCode);
}
