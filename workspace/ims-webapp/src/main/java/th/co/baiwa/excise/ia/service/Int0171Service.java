package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0171FormVo;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicFri6010;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicenseList6010;

@Service
public class Int0171Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	public DataTableAjax<LicenseList6010> licFri6010(Int0171FormVo formVo){
		
		LicFri6010 dataLicFri6010 = new LicFri6010();
		List<LicenseList6010> licenseList6010List = new ArrayList<LicenseList6010>();
		DataTableAjax<LicenseList6010> dataTableAjax= new DataTableAjax<>();
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
		dataLicFri6010 = webServiceExciseService.licFri6010(formVo.getOffcode(),formVo.getYearMonthFrom(),formVo.getYearMonthTo(),formVo.getPageNo(),formVo.getDataPerPage());
		
		licenseList6010List = dataLicFri6010.getResponseData().getLicenseList();
		
			dataTableAjax.setData(licenseList6010List);
		}
		return dataTableAjax;
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
