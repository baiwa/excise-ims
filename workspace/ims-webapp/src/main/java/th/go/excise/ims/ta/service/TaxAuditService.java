package th.go.excise.ims.ta.service;

import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
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
import th.go.excise.ims.ta.vo.FactoryAuditDetailVo;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegDuty;
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

	public DataTableAjax<OutsidePlanVo> outsidePlan(OutsidePlanFormVo formVo) {

		formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		String whereOfficeCode = ExciseUtils.whereInLocalOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
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

	public WsRegfri4000FormVo getOperatorDetails(String newRegId) throws Exception {
		logger.info("getOperatorDetails newRegId={}", newRegId);
		
		RequestData requestData = new RequestData();
		requestData.setType("2");
		requestData.setNid("");
		requestData.setNewregId(newRegId);
		requestData.setHomeOfficeId("");
		requestData.setActive("1");
		requestData.setPageNo("1");
		requestData.setDataPerPage("1");
		
		WsRegfri4000FormVo formVo = null;
		try {
			List<RegMaster60> regMaster60List = regFri4000Service.execute(requestData).getRegMaster60List();
			formVo = new WsRegfri4000FormVo();
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
		} catch (PccRestfulException e) {
			logger.warn("Now Found in WsRegfri4000");
			formVo = taWsReg4000Repository.findByNewRegId(newRegId);
			if (formVo == null) {
				throw new PccRestfulException("NewRegId=" + newRegId + " Not Found");
			}
		}
		
		return formVo;
	}
	
	public FactoryAuditDetailVo getFactoryAuditDetailByNewRegId(String newRegId) throws Exception {
		logger.info("getFactoryAuditDetailByNewRegId newRegId={}", newRegId);
		
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		
		WsRegfri4000FormVo wsRegfri4000FormVo = getOperatorDetails(newRegId);
		FactoryAuditDetailVo formVo = new FactoryAuditDetailVo();
		BeanUtils.copyProperties(formVo, wsRegfri4000FormVo);
		String secDesc = ApplicationCache.getExciseDepartment(wsRegfri4000FormVo.getOffcode().substring(0, 2) + "0000").getDeptShortName();
		String areaDesc = ApplicationCache.getExciseDepartment(wsRegfri4000FormVo.getOffcode().substring(0, 4) + "00").getDeptShortName();
		formVo.setSecDesc(secDesc);
		formVo.setAreaDesc(areaDesc);
		for (RegDuty regDuty : formVo.getRegDutyList()) {
			formVo.setDutyCode(regDuty.getGroupId());
			formVo.setDutyDesc(regDuty.getGroupName());
			break;
		}
		
		TaPlanWorksheetDtl planDtl = taPlanWorksheetDtlRepository.findByOfficeCodeAndNewRegId(officeCode, newRegId);
		formVo.setAuditType(planDtl.getAuditType());
		formVo.setAuditStartDate(ThaiBuddhistDate.from(planDtl.getAuditStartDate()).format(DateTimeFormatter.ofPattern(ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH)));
		formVo.setAuditEndDate(ThaiBuddhistDate.from(planDtl.getAuditEndDate()).format(DateTimeFormatter.ofPattern(ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH)));
		
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
