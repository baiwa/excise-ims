	package th.go.excise.ims.oa.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;
import th.go.excise.ims.oa.persistence.entity.OaCustomer;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenseRepository;
import th.go.excise.ims.oa.persistence.repository.OaCustomerRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa020301JdbcRepository;
import th.go.excise.ims.oa.vo.Oa02030101FormVo;
import th.go.excise.ims.oa.vo.Oa020301FormVo;
import th.go.excise.ims.oa.vo.Oa020301Vo;
 
@Service
public class Oa020301Service {
	
	@Autowired 
	OaCustomerRepository oaCustomerRepository;  
	@Autowired 
	OaCustomerLicenseRepository oaCustomerLicenRepository;  
	
	@Autowired
	Oa020301JdbcRepository oa020301JdbcRep;
 
	public Oa020301FormVo saveCustomer(Oa020301FormVo form) {
		OaCustomer  oaCustomer =  new OaCustomer();
		oaCustomer.setName(form.getName());
		oaCustomer.setCompanyName(form.getCompanyName());
		oaCustomer.setIdentifyNo(form.getIdentifyNo());
		oaCustomer.setIdentifyType(form.getIdentifyType());
		oaCustomer.setCompanyName(form.getCompanyName());
		oaCustomer.setMobile(form.getMobile());
		oaCustomer.setOldCustomer(form.getOldCustomer()); 
		oaCustomer.setAddress(form.getAddress()); 
		oaCustomer.setIsDeleted("N");
		oaCustomer.setCreatedBy(UserLoginUtils.getCurrentUsername());
		oaCustomer.setCreatedDate(LocalDateTime.now());
		oaCustomer.setWarehouseAddress(form.getWarehouseAddress());
		oaCustomerRepository.save(oaCustomer);
		 
		return new Oa020301FormVo(); 
	}  
	 
	
	public Oa02030101FormVo saveCustomerLicen(Oa02030101FormVo form) {
		OaCustomerLicen  oaCustomerLicen =  new OaCustomerLicen();
		   
		oaCustomerLicen.setOaCustomerId(form.getOaCustomerId());
		oaCustomerLicen.setLicenType(form.getLicenType());
		oaCustomerLicen.setLicenNo(form.getLicenNo());
		oaCustomerLicen.setLicenDate(form.getLicenDate()); 
		oaCustomerLicen.setOldLicenYear(form.getOldLicenYear()); 
		oaCustomerLicen.setBankGuarantee(form.getBankGuarantee());
		oaCustomerLicen.setBankGuaranteeNo(form.getBankGuaranteeNo());
		oaCustomerLicen.setBankGuaranteeDate(form.getBankGuaranteeDate());
		oaCustomerLicen.setOperrateName(form.getOperrateName());
		oaCustomerLicen.setOperrateRemark(form.getOperrateRemark());
		oaCustomerLicen.setApproveName(form.getApproveName());
		oaCustomerLicen.setStartDate(form.getStartDate());
		oaCustomerLicen.setEndDate(form.getEndDate());
		oaCustomerLicen.setOffCode(form.getOffCode());
		oaCustomerLicen.setReciveDate(form.getReciveDate());
		oaCustomerLicen.setReciveNo(form.getReciveNo());
	    oaCustomerLicen.setApprove(form.getApprove());
		
		oaCustomerLicenRepository.save(oaCustomerLicen);
		  
		return new Oa02030101FormVo(); 
	}
	
	public DataTableAjax<OaCustomer> filterByCriteria(Oa020301Vo request) {

		List<OaCustomer> data = oa020301JdbcRep.getDataFilter(request);

		DataTableAjax<OaCustomer> dataTableAjax = new DataTableAjax<OaCustomer>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(oa020301JdbcRep.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(oa020301JdbcRep.countDatafilter(request));

		return dataTableAjax;
	}
}
