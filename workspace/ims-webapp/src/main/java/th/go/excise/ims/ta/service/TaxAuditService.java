package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.util.ExciseUtils;
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

	public RegMaster60 getOperatorDetail(WsRegfri4000FormVo wsRegfri4000FormVo) {
		//regFri4000Service.execute(requestData);
		return null;
	}
}
