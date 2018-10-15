package th.co.baiwa.excise.ia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.License;
import th.co.baiwa.excise.ia.persistence.repository.LicenseRepository;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class LicenseService {

	private Logger logger = LoggerFactory.getLogger(LicenseService.class);
	@Autowired
	private LicenseRepository licenseRepository;

	@Transactional
	public void saveLicenseList(List<License> licenseList) {
		logger.info("saveLicenseList");
		for (License license : licenseList) {
			List<License> newLicense = licenseRepository.findBylicNo(license.getLicNo());
			if (BeanUtils.isNotEmpty(newLicense)) {
				for (License lic : newLicense) {

					lic.setPrintCode(license.getPrintCode());
					lic.setLicAmount(license.getLicAmount());
					licenseRepository.save(lic);
				}
			} else {
				licenseRepository.save(license);
			}

		}
	}

	
	
	public List<License> searchLicenseByLicDateOffCode(String startDate , String endDate , String offCode) {
		logger.info("searchLicenseByLicDateOffCode {} ,{}, {}" , startDate ,endDate , offCode);
		return licenseRepository.searchLicenseByLicDateOffCode(startDate, endDate, offCode);
	}
	public License searchLicenseById(Long id) {
		return licenseRepository.findOne(id);
	}

	public List<License> findBylicNo(String licNo) {
		return licenseRepository.findBylicNo(licNo);
	}
}
