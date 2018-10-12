package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.vo.Int0151FormVo;
import th.co.baiwa.excise.upload.service.ExcalService;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;
import th.co.baiwa.excise.ws.entity.response.incfri8020.ResponseData;

@Service
public class Int0151Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	private ExcalService excalService;
	
	public List<IncomeList> licFri8020(Int0151FormVo formVo){
		IncFri8020 dataLicFri8020 = new IncFri8020();
		List<IncomeList> licenseList8020List = new ArrayList<IncomeList>();
		ResponseData responseData8020 = new ResponseData();
	
		dataLicFri8020 = webServiceExciseService.IncFri8020(formVo.getOfficeCode(),formVo.getYearMonthFrom(),formVo.getYearMonthTo(),formVo.getDateType(),formVo.getPageNo(),formVo.getDataPerPage());
		
		responseData8020 = dataLicFri8020.getResponseData();
		
		licenseList8020List = responseData8020.getIncomeList();
		
		return licenseList8020List;
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
