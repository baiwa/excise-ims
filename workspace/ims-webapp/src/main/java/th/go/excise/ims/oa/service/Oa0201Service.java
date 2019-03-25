package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.entity.OaPersonAuditPlan;
import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPersonAuditPlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.vo.Oa0201001Vo;
import th.go.excise.ims.oa.vo.Oa0201FromVo;

@Service
public class Oa0201Service {
	
	@Autowired
	private OaPlanRepository oaplanRepo;
	
	@Autowired
	private OaLicensePlanRepository oaLicensePlanRepo;
 	
	@Autowired
	private OaPersonAuditPlanRepository oaPersonAuditPlanRepo;
	
//	public DataTableAjax<Oa0201001Vo> getLicenseCustomer(Oa0206FormVo request) {
//		List<Oa0201001Vo> data = oa0201jdbc.findLicenseById(request);
//		int count = oa0201jdbc.countLicenseById(request);
//		DataTableAjax<Oa0201001Vo> dataTableAjax = new DataTableAjax<Oa0201001Vo>();
//		dataTableAjax.setDraw(request.getDraw() + 1);
//		dataTableAjax.setData(data);
//		dataTableAjax.setRecordsTotal(count);
//		dataTableAjax.setRecordsFiltered(count);
//		return dataTableAjax;
//	}
	
	public Oa0201FromVo saveOAPlan(Oa0201FromVo request) {
//		BigDecimal id = new BigDecimal();
		Oa0201FromVo resp = new Oa0201FromVo();
		OaPlan plan = new OaPlan();
//		Optional<OaPlan> planOpt = oaplanRepo.findById(null);
//		if (planOpt.isPresent()) {
//			plan = planOpt.get();
//			plan.setAuditStart(request.getDateFrom());
//			plan.setAuditEnd(request.getDateTo());
//			plan.setFiscolYear(request.getFiscolYear());
//			plan.setStatus("01");
//			
//			plan = oaplanRepo.save(plan);
//		}

		plan.setAuditStart(request.getDateFrom());
		plan.setAuditEnd(request.getDateTo());
		plan.setFiscolYear(request.getFiscolYear());
		plan.setStatus("1");
		plan = oaplanRepo.save(plan);
		
		List<OaLicensePlan> licenseCompany = new ArrayList<OaLicensePlan>();
		for (int i = 0; i < request.getListCompany().size(); i++) {
			OaLicensePlan license = new OaLicensePlan();
			license.setAuditStart(request.getDateFrom());
			license.setAuditEnd(request.getDateTo());
			license.setFiscolYear(request.getFiscolYear());
			license.setStatus("1");
			license.setOaPlanId(plan.getOaPlanId());
			licenseCompany.add(license);
		}
		

		licenseCompany = (List<OaLicensePlan>) oaLicensePlanRepo.saveAll(licenseCompany);
		
		List<OaPersonAuditPlan> listAuditer = new ArrayList<OaPersonAuditPlan>();
		for (int i = 0; i < request.getListAuditer().size(); i++) {
			OaPersonAuditPlan person = new OaPersonAuditPlan();
			person.setOaPlanId(plan.getOaPlanId());
			person.setOaPersonId(request.getListAuditer().get(i).getWsUserId());
			person.setUserId(request.getListAuditer().get(i).getUserId());
			listAuditer.add(person);
		}
		listAuditer = (List<OaPersonAuditPlan>) oaPersonAuditPlanRepo.saveAll(listAuditer);
		
		
		return resp;
		
		
	}

}
