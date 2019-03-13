package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenDetailRepository;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa02030101JdbcRepository;
import th.go.excise.ims.oa.vo.Oa02030101FormVo;

@Service
public class Oa02030101Service {

	@Autowired
	OaCustomerLicenRepository oaCustomerLicenRep;

	@Autowired
	OaCustomerLicenDetailRepository oaCustomerLicenDetailRep;
	
	@Autowired
	Oa02030101JdbcRepository oa02030101JdbcRep;
	
	public List<Oa02030101FormVo> findCustomerLicenList(String customerIdStr, String licenseType) {
		List<Oa02030101FormVo> response = new ArrayList<Oa02030101FormVo>();
		BigDecimal customerId = new BigDecimal(customerIdStr);
		response = oa02030101JdbcRep.findByCustomerIdAndLicenseType(customerId, licenseType);
		return response;
	}
	
	public Oa02030101FormVo findCustomerLicenAll(String idStr) {
		Oa02030101FormVo response = new Oa02030101FormVo();
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaCustomerLicen> licenOpt = oaCustomerLicenRep.findById(id);
		if (licenOpt.isPresent()) {
			OaCustomerLicen licen = licenOpt.get();
			response.setApprove(licen.getApprove());
			response.setApproveName(licen.getApproveName());
			response.setBankGuarantee(licen.getBankGuarantee());
			response.setBankGuaranteeDate(licen.getBankGuaranteeDate());
			response.setBankGuaranteeNo(licen.getBankGuaranteeNo());
			response.setEndDate(licen.getEndDate());
			response.setLicenseDate(licen.getLicenseDate());
			response.setLicenseNo(licen.getLicenseNo());
			response.setLicenseType(licen.getLicenseType());
			response.setOaCuslicenseId(licen.getOaCuslicenseId());
			response.setOaCustomerId(licen.getOaCustomerId());
			response.setOffCode(licen.getOffCode());
			response.setOldLicenseYear(licen.getOldLicenseYear());
			response.setOperateName(licen.getOperateName());
			response.setOperateRemark(licen.getOperateRemark());
			response.setReceiveDate(licen.getReceiveDate());
			response.setReceiveNo(licen.getReceiveNo());
			response.setStartDate(licen.getStartDate());
			List<OaCustomerLicenDetail> details = oa02030101JdbcRep.findByLicenseId(response.getOaCuslicenseId());
			response.setDetails(details);
		}
		return response;
	}

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
			for (OaCustomerLicenDetail detail : details) {
				detail.setLicenseNo(request.getLicenseNo());
				detail.setOaCuslicenseId(licen.getOaCuslicenseId());
				detail.setSeq(new BigDecimal(seq++));
			}
			details = (List<OaCustomerLicenDetail>) oaCustomerLicenDetailRep.saveAll(details);
		}
		request.setOaCuslicenseId(licen.getOaCuslicenseId());
		request.setDetails(details);
		return request;
	}

	@Transactional
	public Oa02030101FormVo updateCustomerLicenAll(Oa02030101FormVo request) {
		Optional<OaCustomerLicen> licenOpt = oaCustomerLicenRep.findById(request.getOaCuslicenseId());
		if (licenOpt.isPresent()) {
			OaCustomerLicen licen = licenOpt.get();
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
			List<OaCustomerLicenDetail> detailsOld = new ArrayList<>();
			if (details != null) {
				int seq = 0;
				for (OaCustomerLicenDetail detail : details) {
					if (detail.getOaCuslicenseDtlId() != null) {
						Optional<OaCustomerLicenDetail> detailOpt = oaCustomerLicenDetailRep.findById(detail.getOaCuslicenseDtlId());
						if (detailOpt.isPresent()) {
							OaCustomerLicenDetail dtl = detailOpt.get();
							dtl.setName(detail.getName());
							dtl.setType(detail.getType());
							dtl.setAmount(detail.getAmount());
							dtl.setLicenseNo(detail.getLicenseNo());
							dtl.setOaCuslicenseId(detail.getOaCuslicenseId());
							dtl.setSeq(new BigDecimal(seq++));
							detailsOld.add(dtl);
						}
					} else {
						detail.setLicenseNo(request.getLicenseNo());
						detail.setOaCuslicenseId(licen.getOaCuslicenseId());
						detail.setSeq(new BigDecimal(seq++));
						detailsOld.add(detail);
					}
				}
				details = (List<OaCustomerLicenDetail>) oaCustomerLicenDetailRep.saveAll(detailsOld);
				if (request.getDeletes() != null && request.getDeletes().size() > 0) {
					for(OaCustomerLicenDetail delete: request.getDeletes()) {
						oaCustomerLicenDetailRep.deleteById(delete.getOaCuslicenseDtlId());
					}
				}
				
			}
			request.setDeletes(new ArrayList<>());
			request.setDetails(details);
		}
		return request;
	}

}
