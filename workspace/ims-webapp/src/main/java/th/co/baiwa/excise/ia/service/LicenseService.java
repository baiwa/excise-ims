package th.co.baiwa.excise.ia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.License;
import th.co.baiwa.excise.ia.persistence.repository.LicenseRepository;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class LicenseService {
	@Autowired
	private LicenseRepository licenseRepository;

	@Transactional
	public void saveLicenseList(List<License> licenseList) {
		for (License license : licenseList) {
			if(BeanUtils.isNotEmpty(license.getLicId())) {
				License newLicense = licenseRepository.findOne(license.getLicId());
				newLicense.setPrintCode(license.getPrintCode());
				newLicense.setLicAmount(license.getLicAmount());
			}else {
				licenseRepository.save(license);
			}
		}
	}
	
	public License searchLicenseById(Long id) {
		return licenseRepository.findOne(id);
	}
	public List<License> findBylicNo(String licNo) {
		return licenseRepository.findBylicNo(licNo);
	}
}
