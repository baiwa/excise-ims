package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.oa.persistence.entity.OaCustomer;
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicenDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydCustomerLicenDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaHydCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0107JdbcRepository;
import th.go.excise.ims.oa.vo.Oa010106FormVo;
import th.go.excise.ims.oa.vo.Oa0107Vo;

@Service
public class Oa0107Service {

	@Autowired
	private Oa0107JdbcRepository oa0107JdbcRep;

	@Autowired
	OaHydCustomerLicenRepository oaHydCustomerLicenRep;

	@Autowired
	OaHydCustomerLicenDtlRepository oaHydCustomerLicenDtlRep;

	public DataTableAjax<OaCustomer> filterByCriteria(Oa0107Vo request) {
		List<OaCustomer> data = oa0107JdbcRep.getDataFilter(request);
		DataTableAjax<OaCustomer> dataTableAjax = new DataTableAjax<OaCustomer>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(oa0107JdbcRep.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(oa0107JdbcRep.countDatafilter(request));
		return dataTableAjax;
	}

	public List<Oa010106FormVo> findCustomerLicenList(String customerIdStr, String licenseType) {
		List<Oa010106FormVo> response = new ArrayList<Oa010106FormVo>();
		BigDecimal customerId = new BigDecimal(customerIdStr);
		response = oa0107JdbcRep.findByCustomerIdAndLicenseType(customerId, licenseType);
		return response;
	}

	public Oa010106FormVo findCustomerLicenAll(String idStr) {
		Oa010106FormVo response = new Oa010106FormVo();
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydCustomerLicen> licenOpt = oaHydCustomerLicenRep.findById(id);
		if (licenOpt.isPresent()) {
			OaHydCustomerLicen licen = licenOpt.get();
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
			List<OaHydCustomerLicenDtl> details = oa0107JdbcRep.findByLicenseId(response.getOaCuslicenseId());
			response.setDetails(details);
		}
		return response;
	}

	@Transactional
	public Oa010106FormVo saveCustomerLicenAll(Oa010106FormVo request) {
		OaHydCustomerLicen licen = new OaHydCustomerLicen();
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
		licen = oaHydCustomerLicenRep.save(licen);
		List<OaHydCustomerLicenDtl> details = request.getDetails();
		if (details != null) {
			int seq = 0;
			for (OaHydCustomerLicenDtl detail : details) {
				detail.setLicenseNo(request.getLicenseNo());
				detail.setOaCuslicenseId(licen.getOaCuslicenseId());
				detail.setSeq(new BigDecimal(seq++));
			}
			details = (List<OaHydCustomerLicenDtl>) oaHydCustomerLicenDtlRep.saveAll(details);
		}
		request.setOaCuslicenseId(licen.getOaCuslicenseId());
		request.setDetails(details);
		return request;
	}

	@Transactional
	public Oa010106FormVo updateCustomerLicenAll(Oa010106FormVo request) {
		Optional<OaHydCustomerLicen> licenOpt = oaHydCustomerLicenRep.findById(request.getOaCuslicenseId());
		if (licenOpt.isPresent()) {
			OaHydCustomerLicen licen = licenOpt.get();
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
			licen = oaHydCustomerLicenRep.save(licen);
			List<OaHydCustomerLicenDtl> details = request.getDetails();
			List<OaHydCustomerLicenDtl> detailsOld = new ArrayList<>();
			if (details != null) {
				int seq = 0;
				for (OaHydCustomerLicenDtl detail : details) {
					if (detail.getOaCuslicenseDtlId() != null) {
						Optional<OaHydCustomerLicenDtl> detailOpt = oaHydCustomerLicenDtlRep
								.findById(detail.getOaCuslicenseDtlId());
						if (detailOpt.isPresent()) {
							OaHydCustomerLicenDtl dtl = detailOpt.get();
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
				details = (List<OaHydCustomerLicenDtl>) oaHydCustomerLicenDtlRep.saveAll(detailsOld);
				if (request.getDeletes() != null && request.getDeletes().size() > 0) {
					for (OaHydCustomerLicenDtl delete : request.getDeletes()) {
						oaHydCustomerLicenDtlRep.deleteById(delete.getOaCuslicenseDtlId());
					}
				}

			}
			request.setDeletes(new ArrayList<>());
			request.setDetails(details);
		}
		return request;
	}

}
