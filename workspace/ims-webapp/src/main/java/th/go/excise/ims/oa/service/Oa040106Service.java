package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaAchCustomer;
import th.go.excise.ims.oa.persistence.repository.OaAchCustomerRepository;

@Service
public class Oa040106Service {
	
//	@Autowired
//	private Oa040106JdbcRepository oa040106JdbcRep;
	
	@Autowired
	private OaAchCustomerRepository oaAchCustomerRep;
	
//	@Autowired
//	private OaAchCustomerLicenRepository oaAchCustomerLicenRep;
	
//	public Oa020106ButtonVo findButtonById(String idStr) {
//		BigDecimal id = new BigDecimal(idStr);
//		return oa020106JdbcRep.findButtonIdById(id);
//	}
	
	public OaAchCustomer findById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaAchCustomer> optOaCustomer = oaAchCustomerRep.findById(id);
		OaAchCustomer  oaCustomer =  new OaAchCustomer();
		if (optOaCustomer.isPresent()) {
			oaCustomer = optOaCustomer.get();
		}
		return oaCustomer;
	}
	
//	public Oa020106FormVo findCustomerLicenAll(String idStr) {
//		Oa020106FormVo response = new Oa020106FormVo();
//		BigDecimal id = new BigDecimal(idStr);
//		Optional<OaCustomerLicen> licenOpt = oaCustomerLicenRep.findById(id);
//		if (licenOpt.isPresent()) {
//			OaCustomerLicen licen = licenOpt.get();
//			response.setApprove(licen.getApprove());
//			response.setApproveName(licen.getApproveName());
//			response.setBankGuarantee(licen.getBankGuarantee());
//			response.setBankGuaranteeDate(licen.getBankGuaranteeDate());
//			response.setBankGuaranteeNo(licen.getBankGuaranteeNo());
//			response.setEndDate(licen.getEndDate());
//			response.setLicenseDate(licen.getLicenseDate());
//			response.setLicenseNo(licen.getLicenseNo());
//			response.setLicenseType(licen.getLicenseType());
//			response.setOaCuslicenseId(licen.getOaCuslicenseId());
//			response.setOaCustomerId(licen.getOaCustomerId());
//			response.setOffCode(licen.getOffCode());
//			response.setOldLicenseYear(licen.getOldLicenseYear());
//			response.setOperateName(licen.getOperateName());
//			response.setOperateRemark(licen.getOperateRemark());
//			response.setReceiveDate(licen.getReceiveDate());
//			response.setReceiveNo(licen.getReceiveNo());
//			response.setStartDate(licen.getStartDate());
//			List<OaCustomerLicenDetail> details = oa020106JdbcRep.findByLicenseId(response.getOaCuslicenseId());
//			response.setDetails(details);
//		}
//		return response;
//	}

}
