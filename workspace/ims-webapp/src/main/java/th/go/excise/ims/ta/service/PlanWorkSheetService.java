package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.vo.PlanWorkSheetVo;

@Service
public class PlanWorkSheetService {

	private static final Logger logger = LoggerFactory.getLogger(PlanWorkSheetService.class);

	@Autowired
	private WorksheetSequenceService worksheetSequenceService;

	@Autowired
	private TaPlanWorksheetHdrRepository planWorksheetHdrRepository;
	@Autowired
	private TaPlanWorksheetDtlRepository planWorksheetDtlRepository;
	@Autowired
	private TaPlanWorksheetSendRepository planWorksheetSendRepository;

	public TaPlanWorksheetHdr getPlanWorksheetHdr(PlanWorkSheetVo formVo) {
		logger.info("getPlanWorksheetHdr budgetYear={}", formVo.getBudgetYear());
		return planWorksheetHdrRepository.findByBudgetYear(formVo.getBudgetYear());
	}

	public void savePlanWorkSheetHdr(PlanWorkSheetVo formVo) {
		logger.info("savePlanWorkSheetHdr formVo={}", ToStringBuilder.reflectionToString(formVo, ToStringStyle.JSON_STYLE));
		
		TaPlanWorksheetHdr plantHdr = new TaPlanWorksheetHdr();
		plantHdr.setAnalysisNumber(formVo.getAnalysisNumber());
		plantHdr.setBudgetYear(formVo.getBudgetYear());
		plantHdr.setPlanNumber(worksheetSequenceService.getPlanNumber(UserLoginUtils.getCurrentUserBean().getOfficeCode(), formVo.getBudgetYear()));
		
		planWorksheetHdrRepository.save(plantHdr);
		
		// Add more logic for support Send All and Send Hierarchy
	}

	public void savePlanWorkSheetDtl(PlanWorkSheetVo formVo) {
		logger.info("savePlanWorkSheetDtl formVo={}", ToStringBuilder.reflectionToString(formVo, ToStringStyle.JSON_STYLE));

		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();

		TaPlanWorksheetHdr hdr = planWorksheetHdrRepository.findByBudgetYear(formVo.getPlanNumber());
		// set data search all start 0 length 0
		List<TaPlanWorksheetDtl> planWorksheetDtlList = planWorksheetDtlRepository.findByAnalysisNumberAndPlanNumberAndOfficeCode(formVo.getAnalysisNumber(), formVo.getPlanNumber(), officeCode);

		List<TaPlanWorksheetDtl> planWorksheetDtls = new ArrayList<>();
		for (TaPlanWorksheetDtl planWorksheetDtl : planWorksheetDtlList) {

			TaPlanWorksheetDtl planDtl = new TaPlanWorksheetDtl();
			planDtl.setPlanNumber(hdr.getPlanNumber());
			planDtl.setAnalysisNumber(hdr.getAnalysisNumber());
			planDtl.setOfficeCode(officeCode);
			planDtl.setNewRegId(planWorksheetDtl.getNewRegId());
			planDtl.setAuditStatus(null);
			planDtl.setAuditType(null);
			planDtl.setAuditStartDate(null);
			planDtl.setAuditEndDate(null);

			planWorksheetDtls.add(planDtl);
		}
		planWorksheetDtlRepository.saveAll(planWorksheetDtls);
	}

	public List<TaPlanWorksheetDtl> findPlanWorkSheetDtl(PlanWorkSheetVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("findPlanWorkSheetDtl planNumber={}, officeCode={}", formVo.getPlanNumber(), officeCode);
		return planWorksheetDtlRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
	}

	public List<TaPlanWorksheetSend> getPlanWorkSheetSend() {
		logger.info("getPlanWorkSheetSend budgetYear={}", ExciseUtils.getCurrentBudgetYear());
		return planWorksheetSendRepository.findByBudgetYear(ExciseUtils.getCurrentBudgetYear());
	}

}
