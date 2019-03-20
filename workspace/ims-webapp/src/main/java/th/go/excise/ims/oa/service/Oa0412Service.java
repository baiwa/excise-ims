package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomer;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicenDtl;
import th.go.excise.ims.oa.persistence.repository.OaAchCustomerLicenDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaAchCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0412JdbcRepository;
import th.go.excise.ims.oa.vo.Oa040106FormVo;
import th.go.excise.ims.oa.vo.Oa0412Vo;

@Service
public class Oa0412Service {

	@Autowired
	private Oa0412JdbcRepository oa0412JdbcRep;

	@Autowired
	OaAchCustomerLicenRepository oaAchCustomerLicenRep;

	@Autowired
	OaAchCustomerLicenDtlRepository oaAchCustomerLicenDtlRep;

	public DataTableAjax<OaAchCustomer> filterByCriteria(Oa0412Vo request) {
		List<OaAchCustomer> data = oa0412JdbcRep.getDataFilter(request);
		DataTableAjax<OaAchCustomer> dataTableAjax = new DataTableAjax<OaAchCustomer>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(oa0412JdbcRep.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(oa0412JdbcRep.countDatafilter(request));
		return dataTableAjax;
	}

	public List<Oa040106FormVo> findCustomerLicenList(String customerIdStr, String licenseType) {
		List<Oa040106FormVo> response = new ArrayList<Oa040106FormVo>();
		BigDecimal customerId = new BigDecimal(customerIdStr);
		response = oa0412JdbcRep.findByCustomerIdAndLicenseType(customerId, licenseType);
		return response;
	}

	public Oa040106FormVo findCustomerLicenAll(String idStr) {
		Oa040106FormVo response = new Oa040106FormVo();
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaAchCustomerLicen> licenOpt = oaAchCustomerLicenRep.findById(id);
		if (licenOpt.isPresent()) {
			OaAchCustomerLicen licen = licenOpt.get();
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
			List<OaAchCustomerLicenDtl> details = oa0412JdbcRep.findByLicenseId(response.getOaCuslicenseId());
			response.setDetails(details);
		}
		return response;
	}

	@Transactional
	public Oa040106FormVo saveCustomerLicenAll(Oa040106FormVo request) {
		OaAchCustomerLicen licen = new OaAchCustomerLicen();
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
		licen = oaAchCustomerLicenRep.save(licen);
		List<OaAchCustomerLicenDtl> details = request.getDetails();
		if (details != null) {
			int seq = 0;
			for (OaAchCustomerLicenDtl detail : details) {
				detail.setLicenseNo(request.getLicenseNo());
				detail.setOaCuslicenseId(licen.getOaCuslicenseId());
				detail.setSeq(new BigDecimal(seq++));
			}
			details = (List<OaAchCustomerLicenDtl>) oaAchCustomerLicenDtlRep.saveAll(details);
		}
		request.setOaCuslicenseId(licen.getOaCuslicenseId());
		request.setDetails(details);
		return request;
	}

	@Transactional
	public Oa040106FormVo updateCustomerLicenAll(Oa040106FormVo request) {
		Optional<OaAchCustomerLicen> licenOpt = oaAchCustomerLicenRep.findById(request.getOaCuslicenseId());
		if (licenOpt.isPresent()) {
			OaAchCustomerLicen licen = licenOpt.get();
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
			licen = oaAchCustomerLicenRep.save(licen);
			List<OaAchCustomerLicenDtl> details = request.getDetails();
			List<OaAchCustomerLicenDtl> detailsOld = new ArrayList<>();
			if (details != null) {
				int seq = 0;
				for (OaAchCustomerLicenDtl detail : details) {
					if (detail.getOaCuslicenseDtlId() != null) {
						Optional<OaAchCustomerLicenDtl> detailOpt = oaAchCustomerLicenDtlRep
								.findById(detail.getOaCuslicenseDtlId());
						if (detailOpt.isPresent()) {
							OaAchCustomerLicenDtl dtl = detailOpt.get();
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
				details = (List<OaAchCustomerLicenDtl>) oaAchCustomerLicenDtlRep.saveAll(detailsOld);
				if (request.getDeletes() != null && request.getDeletes().size() > 0) {
					for (OaAchCustomerLicenDtl delete : request.getDeletes()) {
						oaAchCustomerLicenDtlRep.deleteById(delete.getOaCuslicenseDtlId());
					}
				}

			}
			request.setDeletes(new ArrayList<>());
			request.setDetails(details);
		}
		return request;
	}

}
