	package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.oa.persistence.entity.OaCustomer;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenRepository;
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
	OaCustomerLicenRepository oaCustomerLicenRep;  
	
	@Autowired
	Oa020301JdbcRepository oa020301JdbcRep;
	
	public OaCustomer findById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaCustomer> optOaCustomer = oaCustomerRepository.findById(id);
		OaCustomer  oaCustomer =  new OaCustomer();
		if (optOaCustomer.isPresent()) {
			oaCustomer = optOaCustomer.get();
		}
		return oaCustomer;
	}
 
	public OaCustomer saveCustomer(Oa020301FormVo form) {
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
		oaCustomer.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		oaCustomer = oaCustomerRepository.save(oaCustomer);
		return oaCustomer; 
	} 
	
	public OaCustomer updateCustomer(Oa020301FormVo form, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaCustomer> optOaCustomer = oaCustomerRepository.findById(id);
		OaCustomer oaCustomer = new OaCustomer();
		if (optOaCustomer.isPresent()) {
			oaCustomer = optOaCustomer.get();
			oaCustomer.setName(form.getName());
			oaCustomer.setCompanyName(form.getCompanyName());
			oaCustomer.setIdentifyNo(form.getIdentifyNo());
			oaCustomer.setIdentifyType(form.getIdentifyType());
			oaCustomer.setCompanyName(form.getCompanyName());
			oaCustomer.setMobile(form.getMobile());
			oaCustomer.setOldCustomer(form.getOldCustomer()); 
			oaCustomer.setAddress(form.getAddress());
			oaCustomer.setWarehouseAddress(form.getWarehouseAddress());
			oaCustomerRepository.save(oaCustomer);
		}
		return oaCustomer; 
	}
	 
	
	public Oa02030101FormVo saveCustomerLicen(Oa02030101FormVo form) {
		OaCustomerLicen  oaCustomerLicen =  new OaCustomerLicen();

		oaCustomerLicen.setLicenseType(form.getLicenseType());
		oaCustomerLicen.setLicenseNo(form.getLicenseNo());
		oaCustomerLicen.setLicenseDate(form.getLicenseDate()); 
		oaCustomerLicen.setOldLicenseYear(form.getOldLicenseYear()); 
		oaCustomerLicen.setBankGuarantee(form.getBankGuarantee());
		oaCustomerLicen.setBankGuaranteeNo(form.getBankGuaranteeNo());
		oaCustomerLicen.setBankGuaranteeDate(form.getBankGuaranteeDate());
		oaCustomerLicen.setOperateName(form.getOperateName());
		oaCustomerLicen.setOperateRemark(form.getOperateRemark());
		oaCustomerLicen.setApproveName(form.getApproveName());
		oaCustomerLicen.setStartDate(form.getStartDate());
		oaCustomerLicen.setEndDate(form.getEndDate());
		oaCustomerLicen.setOffCode(form.getOffCode());
		oaCustomerLicen.setReceiveDate(form.getReceiveDate());
		oaCustomerLicen.setReceiveNo(form.getReceiveNo());
	    oaCustomerLicen.setApprove(form.getApprove());
		
	    oaCustomerLicenRep.save(oaCustomerLicen);
		  
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
