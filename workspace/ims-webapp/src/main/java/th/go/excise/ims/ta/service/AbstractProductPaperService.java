package th.go.excise.ims.ta.service;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;

public abstract class AbstractProductPaperService<VO> {
	
	protected static final String EXPORT_TYPE_CREATE = "001";
	protected static final String EXPORT_TYPE_PR_NUM = "002";
	protected static final String NO_VALUE = "-";
	
	protected Gson gson;
	protected TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;
	protected PaperSequenceService paperSequenceService;
	
	@Autowired
	public final void setGson(Gson gson) {
		this.gson = gson;
	}

	@Autowired
	public final void setTaPlanWorksheetDtlRepository(TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository) {
		this.taPlanWorksheetDtlRepository = taPlanWorksheetDtlRepository;
	}

	@Autowired
	public final void setPaperSequenceService(PaperSequenceService paperSequenceService) {
		this.paperSequenceService = paperSequenceService;
	}
	
	protected abstract Logger getLogger();

	public List<VO> inquiry(ProductPaperFormVo formVo) {
		if (StringUtils.isEmpty(formVo.getPaperPrNumber())) {
			return inquiryByWs(formVo);
		} else {
			return inquiryByPaperPrNumber(formVo);
		}
	};
	
	protected abstract List<VO> inquiryByWs(ProductPaperFormVo formVo);
	
	protected abstract List<VO> inquiryByPaperPrNumber(ProductPaperFormVo formVo);
	
	public byte[] export(ProductPaperFormVo formVo) {
		List<VO> voList = null;
		String exportType = null;
		if (StringUtils.isEmpty(formVo.getPaperPrNumber())) {
			voList = inquiryByWs(formVo);
			exportType = EXPORT_TYPE_CREATE;
		} else {
			voList = inquiryByPaperPrNumber(formVo);
			exportType = EXPORT_TYPE_PR_NUM;
		}
		return exportData(voList, exportType);
	}
	
	protected abstract byte[] exportData(List<VO> voList, String exportType);
	
	public abstract List<VO> upload(ProductPaperFormVo formVo);
	
	public abstract void save(ProductPaperFormVo formVo);
	
	public abstract List<String> getPaperPrNumberList(ProductPaperFormVo formVo);
	
	protected Type getListVoType() {
		Type voType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return TypeToken.getParameterized(List.class, voType).getType();
	}
	
	protected LocalDate toLocalDate(String inputDate) {
		return LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(inputDate.split("/")[1]), Integer.parseInt(inputDate.split("/")[0]), 1));
	}
	
	protected void prepareEntityH(ProductPaperFormVo formVo, Object entityObj, Class<?> entityClass) {
		PlanWorksheetDtlVo planDtlVo = taPlanWorksheetDtlRepository.findPlanDetailByAuditPlanCode(formVo.getAuditPlanCode());
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = planDtlVo.getBudgetYear();
		String paperPrNumber = paperSequenceService.getPaperProductNumber(officeCode, budgetYear);
		
		try {
			Method methodSetOfficeCode = entityClass.getDeclaredMethod("setOfficeCode", String.class);
			methodSetOfficeCode.invoke(entityObj, officeCode);
			
			Method methodSetBudgetYear = entityClass.getDeclaredMethod("setBudgetYear", String.class);
			methodSetBudgetYear.invoke(entityObj, budgetYear);
			
			Method methodSetPlanNumber = entityClass.getDeclaredMethod("setPlanNumber", String.class);
			methodSetPlanNumber.invoke(entityObj, planDtlVo.getPlanNumber());
			
			Method methodSetAuditPlanCode = entityClass.getDeclaredMethod("setAuditPlanCode", String.class);
			methodSetAuditPlanCode.invoke(entityObj, formVo.getAuditPlanCode());
			
			Method methodSetPaperPrNumber = entityClass.getDeclaredMethod("setPaperPrNumber", String.class);
			methodSetPaperPrNumber.invoke(entityObj, paperPrNumber);
			
			Method methodSetNewRegId = entityClass.getDeclaredMethod("setNewRegId", String.class);
			methodSetNewRegId.invoke(entityObj, formVo.getNewRegId());
			
			Method methodSetDutyGroupId = entityClass.getDeclaredMethod("setDutyGroupId", String.class);
			methodSetDutyGroupId.invoke(entityObj, formVo.getDutyGroupId());
			
			Method methodSetStartDate = entityClass.getDeclaredMethod("setStartDate", LocalDate.class);
			methodSetStartDate.invoke(entityObj, toLocalDate(formVo.getStartDate()));
			
			Method methodSetEndDate = entityClass.getDeclaredMethod("setEndDate", LocalDate.class);
			methodSetEndDate.invoke(entityObj, toLocalDate(formVo.getEndDate()));
		} catch (Exception e) {
			getLogger().error(e.getMessage(), e);
		}
	}
	
}
