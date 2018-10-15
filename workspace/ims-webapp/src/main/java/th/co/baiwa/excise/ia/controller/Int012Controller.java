package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.Int0121Vo;
import th.co.baiwa.excise.ia.persistence.entity.License;
import th.co.baiwa.excise.ia.service.LicenseService;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicFri6010;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicenseList6010;

@Controller
@RequestMapping("api/ia/int012")
public class Int012Controller {
	private Logger logger = LoggerFactory.getLogger(Int012Controller.class);
	private final String RESPONSE_CODE_SUCCESS = "OK";
	private final String MAX_SIZE_WEB_SERVICE = "1000";
	
	

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	@Autowired
	private LicenseService licenseService;

	@PostMapping("/searchLicense")
	@ResponseBody
	public List<License> searchLicense(@RequestBody Int0121Vo int0121Vo) {
		logger.info("searchLicense");
		List<License> licenseList = new ArrayList<License>();
		List<LicenseList6010> wsData = new ArrayList<LicenseList6010>();
		
		
		License license = new License();
		int i = 1;
		do {

			wsData = new ArrayList<LicenseList6010>();
			LicFri6010 licFri6010 = webServiceExciseService.licFri6010(int0121Vo.getOffCode(), int0121Vo.getStartDate(), int0121Vo.getEndDate(), i++ + "", MAX_SIZE_WEB_SERVICE);
			if (RESPONSE_CODE_SUCCESS.equals(licFri6010.getResponseCode())) {
				if (BeanUtils.isNotEmpty(licFri6010.getResponseData()) && BeanUtils.isNotEmpty(licFri6010.getResponseData().getLicenseList())) {
					wsData = licFri6010.getResponseData().getLicenseList();
					for (LicenseList6010 licenseList6010 : wsData) {
						try {
							license = new License();
							BeanUtils.copyProperties(license, licenseList6010);
							license.setOffCode(int0121Vo.getOffCode());
							List<License> lic = licenseService.findBylicNo(license.getLicNo());
							if (BeanUtils.isNotEmpty(lic)) {
								license.setPrintCode(lic.get(0).getPrintCode());
								license.setLicAmount(lic.get(0).getLicAmount());
							}
							licenseList.add(license);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error(e.getMessage());
						}

					}
				}
			}
		} while (MAX_SIZE_WEB_SERVICE.equals(wsData.size() + ""));

		return licenseList;
	}

	@PostMapping("/save")
	@ResponseBody
	public Message save(@RequestBody Int0121Vo int0121Vo) {
		logger.info("saveLicenseList");
		Message message;
		try {
			licenseService.saveLicenseList(int0121Vo.getLicenseList());
			message = ApplicationCache.getMessage("MSG_00002");

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			message = ApplicationCache.getMessage("MSG_00003");
		}
		logger.info(message.getMessageEn());
		return message;
	}

}
