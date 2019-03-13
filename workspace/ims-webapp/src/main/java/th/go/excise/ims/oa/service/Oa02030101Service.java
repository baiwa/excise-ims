package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenDetailRepository;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenRepository;
import th.go.excise.ims.oa.vo.Oa02030101FormVo;

@Service
public class Oa02030101Service {
	
	@Autowired 
	OaCustomerLicenRepository oaCustomerLicenRep;
	
	@Autowired
	OaCustomerLicenDetailRepository oaCustomerLicenDetailRep;
	
	@Transactional
	public Oa02030101FormVo saveCustomerLicenAll(Oa02030101FormVo request) {
		OaCustomerLicen licen = new OaCustomerLicen();
		licen.setApprove(request.getApprove());
		licen.setApproveName(request.getApproveName());
		licen.setBankGuarantee(request.getBankGuarantee());
		licen.setBankGuaranteeDate(request.getBankGuaranteeDate());
		licen.setBankGuaranteeNo(request.getBankGuaranteeNo());
		licen.setEndDate(request.getEndDate());
		licen.setLicenseDate(request.getLicenseDate());
		licen.setLicenseNo(request.getLicenseNo());
		licen.setLicenseType(request.getLicenseType());
		licen.setOaCustomerId(request.getOaCustomerId());
		licen.setOffCode(request.getOffCode());
		licen.setOldLicenseYear(request.getOldLicenseYear());
		licen.setOperateName(request.getOperateName());
		licen.setOperateRemark(request.getOperateRemark());
		licen.setReceiveDate(request.getReceiveDate());
		licen.setReceiveNo(request.getReceiveNo());
		licen.setStartDate(request.getStartDate());
		licen = oaCustomerLicenRep.save(licen);
		List<OaCustomerLicenDetail> details = request.getDetails();
		if (details != null) {
			int seq = 0;
			for(OaCustomerLicenDetail detail: details) {
				if (request.getLicenseNo() != null) {
					detail.setLicenseNo(request.getLicenseNo());
				} else {
					detail.setLicenseNo("RANDOM_NUMBER");
				}
				detail.setOaCuslicenseId(licen.getOaCuslicenseId());
				detail.setSeq(new BigDecimal(seq++));
			}
			details = (List<OaCustomerLicenDetail>) oaCustomerLicenDetailRep.saveAll(details);
		}
		request.setOaCuslicenseId(licen.getOaCuslicenseId());
		request.setDetails(details);
		return request;
	}
	
}
