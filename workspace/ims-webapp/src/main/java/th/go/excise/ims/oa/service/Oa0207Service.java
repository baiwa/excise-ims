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
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenDetailRepository;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0207JdbcRepository;
import th.go.excise.ims.oa.vo.Oa020106FormVo;
import th.go.excise.ims.oa.vo.Oa0207Vo;

@Service
public class Oa0207Service {

	@Autowired
	private Oa0207JdbcRepository oa0207JdbcRep;

	@Autowired
	OaCustomerLicenRepository oaCustomerLicenRep;

	@Autowired
	OaCustomerLicenDetailRepository oaCustomerLicenDetailRep;

	public DataTableAjax<OaCustomer> filterByCriteria(Oa0207Vo request) {
		List<OaCustomer> data = oa0207JdbcRep.getDataFilter(request);
		DataTableAjax<OaCustomer> dataTableAjax = new DataTableAjax<OaCustomer>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(oa0207JdbcRep.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(oa0207JdbcRep.countDatafilter(request));
		return dataTableAjax;
	}

	public List<Oa020106FormVo> findCustomerLicenList(String customerIdStr, String licenseType) {
		List<Oa020106FormVo> response = new ArrayList<Oa020106FormVo>();
		BigDecimal customerId = new BigDecimal(customerIdStr);
		response = oa0207JdbcRep.findByCustomerIdAndLicenseType(customerId, licenseType);
		return response;
	}

	public Oa020106FormVo findCustomerLicenAll(String idStr) {
		Oa020106FormVo response = new Oa020106FormVo();
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
			List<OaCustomerLicenDetail> details = oa0207JdbcRep.findByLicenseId(response.getOaCuslicenseId());
			response.setDetails(details);
		}
		return response;
	}

	@Transactional
	public Oa020106FormVo saveCustomerLicenAll(Oa020106FormVo request) {
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
	public Oa020106FormVo updateCustomerLicenAll(Oa020106FormVo request) {
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
						Optional<OaCustomerLicenDetail> detailOpt = oaCustomerLicenDetailRep
								.findById(detail.getOaCuslicenseDtlId());
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
					for (OaCustomerLicenDetail delete : request.getDeletes()) {
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
