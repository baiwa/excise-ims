package th.co.baiwa.excise.ta.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.datatable.DataTableRequest;
import th.co.baiwa.excise.sys.domain.Notification;
import th.co.baiwa.excise.sys.service.NotificationService;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.ta.persistence.entity.PlanCriteria;
import th.co.baiwa.excise.ta.persistence.entity.PlanTaxAudit;
import th.co.baiwa.excise.ta.persistence.repository.PlanCriteriaRepository;
import th.co.baiwa.excise.ta.persistence.repository.PlanTaxAuditRepository;
import th.co.baiwa.excise.ta.persistence.vo.PlanCriteriaVo;
import th.co.baiwa.excise.ta.persistence.vo.PlanTaxAuditVo;

@Service
public class PlanTaxAuditService {
	private Logger logger = LoggerFactory.getLogger(PlanTaxAuditService.class);

	@Autowired
	private PlanTaxAuditRepository planTaxAuditRepository;

	@Autowired
	private PlanCriteriaRepository planCriteriaRepository;
	
	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;
	
	@Autowired
	private NotificationService notificationService;
	

	@Transactional
	public CommonMessage<String> createPlanTaxAuditRepository(PlanTaxAudit planTaxAudit, List<PlanCriteria> planCriteriaList) {
		Message msg = null;
		CommonMessage<String> commonMessage = new CommonMessage<String>();
		try {
			logger.info("createPlanTacAuditRepository in budget_year : {}" , planTaxAudit.getBudgetYear());
			String analysNumber = DateConstant.DateToString(new Date(), DateConstant.YYYYMMDD) + "-01-"
					+ planWorksheetHeaderDao.getAnalysNumber();
			planTaxAudit.setAnalysNumber(analysNumber);
			planTaxAudit = planTaxAuditRepository.save(planTaxAudit);
			for (PlanCriteria planCriteria : planCriteriaList) {
				planCriteria.setTaPlanTaxAuditId(planTaxAudit.getTaPlanTaxAuditId());
			}
			msg = ApplicationCache.getMessage("MSG_00002");
			planCriteriaRepository.save(planCriteriaList);
			
			
			Notification notification = new Notification();
			notification.setType(NotificationService.CREATE_PLAN);
			notification.setSubject("ข้อมูลการคัดกรอง");
			notification.setDetailMessage("หมายเลขคัดกรอง : "+ analysNumber);
			notification.setStatus(FLAG.N_FLAG);
			notification.setReferenceId(planTaxAudit.getTaPlanTaxAuditId());
			msg = notificationService.createNotificationService(notification);
			
			commonMessage.setMsg(msg);
			commonMessage.setData(analysNumber);
		} catch (Exception e) {
			e.printStackTrace();
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		return commonMessage;
	}
	
	@Transactional
	public Message editCriteria(List<PlanCriteria> planCriteriaList) {
		Message msg = null;
		try {
			PlanCriteria criteria = null;
			for (PlanCriteria planCriteria : planCriteriaList) {
				criteria = new PlanCriteria();
				criteria = planCriteriaRepository.findOne(planCriteria.getTaPlanCriteriaId());
				criteria.setMonthMax(planCriteria.getMonthMax());
				criteria.setMonthMin(planCriteria.getMonthMin());
				criteria.setPercenTageFrom(planCriteria.getPercenTageFrom());
				criteria.setPercenTageTo(planCriteria.getPercenTageTo());
				planCriteriaRepository.save(criteria);
			}
			msg = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			msg = ApplicationCache.getMessage("MSG_00003");
			msg.setMessageTh(e.getMessage());
		}
		return msg;
	}
	
	public List<PlanTaxAudit> findByBudgetYearOrderByTaPlanTaxAuditId(String budgetYear){
		logger.info("findByBudgetYearOrderByTaPlanTaxAuditId in budget_year : {}" , budgetYear);
		return planTaxAuditRepository.findByBudgetYearOrderByTaPlanTaxAuditId(budgetYear);
	}
	
	
	public ResponseDataTable<PlanTaxAudit> findByBudgetYearOrderByTaPlanTaxAuditIdForDataTable(PlanTaxAudit planTaxAudit , DataTableRequest dataTableRequest){
		long count = planTaxAuditRepository.countPlanTaxAuditByCriteriaForDataTable(planTaxAudit);
		ResponseDataTable<PlanTaxAudit> responseDataTable = new ResponseDataTable<PlanTaxAudit>();
		List<PlanTaxAudit> planTaxAuditList = planTaxAuditRepository.findPlanTaxAuditByCriteriaForDataTable(planTaxAudit, dataTableRequest.getStart(), dataTableRequest.getLength());
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(planTaxAuditList);
		responseDataTable.setRecordsTotal((int) count);
		responseDataTable.setRecordsFiltered((int) count);
		return responseDataTable;
	}
	public void createNewPlanWorkSheetHeaderByAnalysNumber(String analysNumber) {
		logger.info("createNewPlanWorkSheetHeaderByAnalysNumber in analysNumber : {}" , analysNumber);
//		PlanTaxAudit planTaxAudit = planTaxAuditRepository.findByAnalysNumber(analysNumber);
//		LocalDate dateFrom = LocalDate.parse("01/"+planTaxAudit.getMonthFrom(), DateTimeFormatter.ofPattern(DateConstant.DD_MM_YYYY) );
//		LocalDate dateTo = LocalDate.parse("01/"+planTaxAudit.getMonthTo(), DateTimeFormatter.ofPattern(DateConstant.DD_MM_YYYY) );
//		long daysBetween = ChronoUnit.MONTHS.between(dateFrom, dateTo);
//		logger.info("from : {} ==> to : {} Diff :  {}" , planTaxAudit.getMonthFrom() ,planTaxAudit.getMonthTo() , daysBetween );
		
		planWorksheetHeaderDao.createNewPlanWorkSheetHeaderByAnalysNumber(analysNumber);
		planWorksheetHeaderDao.createNewPlanWorkSheetDetailByAnalysNumber(analysNumber);
		
	}
	
	public Message deltePlanTaxAuditById(PlanTaxAudit planTaxAudit) {
		Message msg = null;
		try {
			planTaxAuditRepository.delete(planTaxAudit.getTaPlanTaxAuditId());
			msg = ApplicationCache.getMessage("MSG_00005");
		} catch (Exception e) {
			msg = ApplicationCache.getMessage("MSG_00006");
			msg.setMessageTh(e.getMessage());
		}
		return msg;
	}
	
	
	public PlanTaxAuditVo findPlanTaxAuditAndPlanCriteriaListByTaPlanTaxAuditId(PlanTaxAudit planTaxAudit) {
		logger.info("findPlanTaxAuditAndPlanCriteriaListTaPlanTaxAuditId : {}" , planTaxAudit.getTaPlanTaxAuditId());
		PlanTaxAuditVo planTaxAuditVo = new PlanTaxAuditVo();
		planTaxAudit = planTaxAuditRepository.findOne(planTaxAudit.getTaPlanTaxAuditId());
		List<PlanCriteria> planCriteriaList = planCriteriaRepository.findByTaPlanTaxAuditIdOrderByTaPlanCriteriaId(planTaxAudit.getTaPlanTaxAuditId());
		planTaxAuditVo.setPlanTaxAudit(planTaxAudit);
		planTaxAuditVo.setPlanCriteriaList(planCriteriaList);
		return planTaxAuditVo;
	}
	
	
	
	public void createPlanWorkSheet(String analysNumber) {
		logger.info("createPlanWorkSheet analysNumber : {}" , analysNumber);
		try {
			Thread.sleep(1000*30);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		createNewPlanWorkSheetHeaderByAnalysNumber(analysNumber);
		logger.info("Create Data Completed analysNumber : {}" , analysNumber);
		Notification notification = new Notification();
		notification = new Notification();
		notification.setType(NotificationService.PROCESS_PLAN_COMPLETED);
		notification.setSubject("ข้อมูลการคัดกรอง");
		notification.setDetailMessage("สร้างหมายเลขคัดกรอง : "+ analysNumber+" สำเร็จ");
		notification.setStatus(FLAG.N_FLAG);
		notificationService.createNotificationService(notification);
	}
	
	
	public List<PlanCriteriaVo> findPlanCriteriaByAnalysNumber(String analysNumber) {
		Gson gson = new Gson();
		PlanTaxAudit plan = planTaxAuditRepository.findByAnalysNumber(analysNumber);
		LocalDate dateFrom = LocalDate.parse("01/"+plan.getMonthFrom(), DateTimeFormatter.ofPattern(DateConstant.DD_MM_YYYY) );
		LocalDate dateTo = LocalDate.parse("01/"+plan.getMonthTo(), DateTimeFormatter.ofPattern(DateConstant.DD_MM_YYYY) );
		long month = ChronoUnit.MONTHS.between(dateFrom, dateTo);
		logger.info("from : {} ==> to : {} Diff :  {}" , plan.getMonthFrom() ,plan.getMonthTo() , month );
		List<PlanCriteriaVo> planCriteriaVoList = new ArrayList<PlanCriteriaVo>();
		PlanTaxAudit planTaxAudit = planTaxAuditRepository.findByAnalysNumber(analysNumber);
		List<PlanCriteria> planCriteriaList = planCriteriaRepository.findByTaPlanTaxAuditIdOrderByTaPlanCriteriaId(planTaxAudit.getTaPlanTaxAuditId());
		PlanCriteriaVo planCriteriaVo = null;
		String[] spEndDate = plan.getMonthTo().split("/");
		String endDate = "01/"+spEndDate[0]+"/"+ (Integer.parseInt(spEndDate[1])-543);
		for (PlanCriteria planCriteria : planCriteriaList) {
			planCriteriaVo = new PlanCriteriaVo();
			planCriteriaVo = gson.fromJson(gson.toJson(planCriteria), PlanCriteriaVo.class);
			
			long count = planTaxAuditRepository.countDetailWorkSheetInCriteria(analysNumber, month, endDate);
			planCriteriaVo.setWorkSheetCount(count);
			planCriteriaVoList.add(planCriteriaVo);
		}
		return planCriteriaVoList;
	}
	
	
	
}
