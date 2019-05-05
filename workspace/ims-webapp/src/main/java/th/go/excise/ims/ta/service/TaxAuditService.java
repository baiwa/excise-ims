package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.vo.AuditCalendarCheckboxVo;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.FactoryVo;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000Service;

@Service
public class TaxAuditService {

	private static final Logger logger = LoggerFactory.getLogger(TaxAuditService.class);

	@Autowired
	private TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	@Autowired
	private RegFri4000Service regFri4000Service;

	public DataTableAjax<PlanWorksheetDatatableVo> getPlanWorksheetDtl(PlanWorksheetVo formVo) {

		DataTableAjax<PlanWorksheetDatatableVo> dataTableAjax = new DataTableAjax<>();

		dataTableAjax.setData(taPlanWorksheetDtlRepository.findByCriteria(formVo));
		dataTableAjax.setDraw(formVo.getDraw() + 1);
		int count = taPlanWorksheetDtlRepository.countByCriteria(formVo).intValue();
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setRecordsTotal(count);

		return dataTableAjax;
	}

	public DataTableAjax<OutsidePlanVo> outsidePlan(OutsidePlanFormVo formVo) {

		formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		String whereOfficeCode = ExciseUtils
				.whereInLocalOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		formVo.setOfficeCode(whereOfficeCode);

		DataTableAjax<OutsidePlanVo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(taWsReg4000Repository.outsidePlan(formVo));
		dataTableAjax.setDraw(formVo.getDraw() + 1);
		int count = taWsReg4000Repository.countOutsidePlan(formVo).intValue();
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setRecordsTotal(count);

		return dataTableAjax;
	}

	public List<ParamInfo> getAuditType(AuditCalendarCheckboxVo form) {
		List<ParamInfo> auditType = new ArrayList<>();
		auditType = ApplicationCache.getParamInfoListByGroupCode(ParameterConstants.PARAM_GROUP.TA_AUDIT_TYPE);
		return auditType;
	}

	public List<ParamInfo> getAuditStatus(AuditCalendarCheckboxVo form) {
		List<ParamInfo> auditStatus = new ArrayList<>();
		auditStatus = ApplicationCache.getParamInfoListByGroupCode(ParameterConstants.PARAM_GROUP.TA_AUDIT_STATUS);
		return auditStatus;
	}

	public List<PlanWorksheetDtlVo> getPlanWsDtl(AuditCalendarCriteriaFormVo formVo) {
		List<PlanWorksheetDtlVo> planWsDtl = new ArrayList<>();
		planWsDtl = taPlanWorksheetDtlRepository.findByCriteria(formVo);
		return planWsDtl;
	}

	public FactoryVo getFactoryByNewRegId(String idStr) {
		logger.info("getFactoryByNewRegId");
		return taWsReg4000Repository.findByNewRegId(idStr);
	}

	public WsRegfri4000FormVo getOperatorDetails(WsRegfri4000FormVo wsRegfri4000FormVo) throws Exception {
		logger.info("getOperatorDetails newRegId={}", wsRegfri4000FormVo.getNewRegId());
		
		RequestData requestData = new RequestData();
		requestData.setType("2");
		requestData.setNid("");
		requestData.setNewregId(wsRegfri4000FormVo.getNewRegId());
		requestData.setHomeOfficeId("");
		requestData.setActive("1");
		requestData.setPageNo("1");
		requestData.setDataPerPage("1");
		
		List<RegMaster60> regMaster60List = regFri4000Service.execute(requestData).getRegMaster60List();
		WsRegfri4000FormVo formVo = new WsRegfri4000FormVo();
		RegMaster60 regMaster60 = null;
		if (regMaster60List != null && regMaster60List.size() > 0) {
			regMaster60 = regMaster60List.get(0);
			BeanUtils.copyProperties(formVo, regMaster60);
			formVo.setNewRegId(regMaster60.getNewregId());
			formVo.setCustomerAddress(ExciseUtils.buildCusAddress(regMaster60));
			formVo.setFacAddress(ExciseUtils.buildFacAddress(regMaster60));
			formVo.setFactoryType(ExciseUtils.getFactoryType(formVo.getNewRegId()));
			if (StringUtils.isNotEmpty(formVo.getFactoryType())) {
				formVo.setFactoryTypeText(ApplicationCache.getParamInfoByCode(PARAM_GROUP.EXCISE_FACTORY_TYPE, formVo.getFactoryType()).getValue2());
			}
		}
		
		return formVo;
	}

	public void savePlanWsDtl(PlanWorksheetDtlVo formVo) {
		logger.info("savePlanWsDtl: newRegId = {}", formVo.getNewRegId());
		TaPlanWorksheetDtl planWsDtl = new TaPlanWorksheetDtl();
		planWsDtl = taPlanWorksheetDtlRepository.findByOfficeCodeAndNewRegId(UserLoginUtils.getCurrentUserBean().getOfficeCode(), formVo.getNewRegId());
		planWsDtl.setAuditType(formVo.getAuditType());
		planWsDtl.setAuditStartDate(ConvertDateUtils.parseStringToLocalDate(formVo.getAuditStartDate(), ConvertDateUtils.DD_MM_YYYY));
		planWsDtl.setAuditEndDate(ConvertDateUtils.parseStringToLocalDate(formVo.getAuditEndDate(), ConvertDateUtils.DD_MM_YYYY));
		taPlanWorksheetDtlRepository.save(planWsDtl);
	}
}
