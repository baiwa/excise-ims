package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.vo.Int0161FormVo;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicFri6020;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicenseList6020;
import th.co.baiwa.excise.ws.entity.response.licfri6020.ResponseData6020;

@Service
public class Int0161Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	public List<LicenseList6020> licFri6020(Int0161FormVo formVo){
		LicFri6020 dataLicFri6020 = new LicFri6020();
		List<LicenseList6020> licenseList6020List = new ArrayList<LicenseList6020>();
		List<LicenseList6020> licenseList6020ListYear = new ArrayList<LicenseList6020>();
		
		dataLicFri6020 = webServiceExciseService.licFri6020(formVo.getNid(),formVo.getNewregId(),formVo.getPageNo(),formVo.getDataPerPage());
		
		ResponseData6020 responseData6020 = dataLicFri6020.getResponseData();
		licenseList6020List = responseData6020.getLicenseList();
		
		for(int iForm = formVo.getYearForm(); iForm <= formVo.getYearTo(); iForm++) {
			for (LicenseList6020 loop1 : licenseList6020List) {
				int yyyy = (StringUtils.isNotBlank(loop1.getExpDate()))?Integer.parseInt(loop1.getExpDate().substring(0,4))+543:0;
				
				if(iForm==yyyy) {
					List<String> year = new ArrayList<String>();
					String add = "Y";
					
					for (LicenseList6020 loop2 : licenseList6020ListYear) {
						if(loop1.getLicName().equals(loop2.getLicName())) {
							add="N";
						}
					}
	
					if("Y".equals(add)) {
						licenseList6020ListYear.add(loop1);
					}
				}
			
			}
		}
		
		for(int iForm = formVo.getYearForm(); iForm <= formVo.getYearTo(); iForm++) {
			int index = 0;
			for (LicenseList6020 loop1 : licenseList6020ListYear) {
				List<String> year = new ArrayList<String>();
				String add = "Y";
					for (LicenseList6020 loop2 : licenseList6020List) {
						int yyyy = (StringUtils.isNotBlank(loop2.getExpDate()))?Integer.parseInt(loop2.getExpDate().substring(0,4))+543:0;
						if(loop1.getLicName().equals(loop2.getLicName())&&iForm==yyyy) {
							
							if(loop1.getYear() != null&&!loop1.getYear().isEmpty()) {
								year=loop1.getYear();
							}
							year.add(setDateString(loop2.getSendDate()));
							year.add(setDateString(loop2.getExpDate()));
							loop2.setYear(year);
							licenseList6020ListYear.set(index,loop2);
							add="N";
						}
					}
					if("Y".equals(add)) {
						
						if(loop1.getYear() != null&&!loop1.getYear().isEmpty()) {
							year=loop1.getYear();
						}
						year.add("");
						year.add("");
						loop1.setYear(year);
						licenseList6020ListYear.set(index,loop1);
					}
					index++;
			}
			
		}
		
		
		
		return licenseList6020ListYear;
		}
	
	public String setDateString(String dateIn) {
		String dateOut = "";
		if(StringUtils.isNotBlank(dateIn)) {
			int yyyy = Integer.parseInt(dateIn.substring(0,4))+543;
		
		dateOut = dateIn.substring(6,8)+"/"+dateIn.substring(4,6)+"/"+String.valueOf(yyyy);
		}
		
		return dateOut;
	} 
}
