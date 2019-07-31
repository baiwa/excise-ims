package th.go.excise.ims.scheduler.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.incfri8000.model.Credit;
import th.go.excise.ims.ws.client.pcc.incfri8000.model.Income;
import th.go.excise.ims.ws.client.pcc.incfri8000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.incfri8000.service.IncFri8000Service;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000Credit;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8000CreditRepository;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8000Repository;

@Service
public class SyncWsIncfri8000Service {

	private static final Logger logger = LoggerFactory.getLogger(SyncWsIncfri8000Service.class);

	private int wsDataSize;
	private IncFri8000Service incFri8000Service;
	private WsIncfri8000Repository wsIncfri8000Repository;
	private WsIncfri8000CreditRepository wsIncfri8000CreditRepository;
	
	@Autowired
	public SyncWsIncfri8000Service(
			@Value("${ws.excise.inc.incfri8000.data-size}") int wsDataSize,
			IncFri8000Service incFri8000Service,
			WsIncfri8000Repository wsIncfri8000Repository,
			WsIncfri8000CreditRepository wsIncfri8000CreditRepository) {
		this.wsDataSize = wsDataSize;
		this.incFri8000Service = incFri8000Service;
		this.wsIncfri8000Repository = wsIncfri8000Repository;
		this.wsIncfri8000CreditRepository = wsIncfri8000CreditRepository;
	}
	
	public RequestData buildRequestData(String... args) throws Exception {
		RequestData requestData = new RequestData();
		
		if (args.length == 1) {
			requestData.setDateType(WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME);
			String yyyyMM = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM", Locale.US));
			requestData.setYearMonthFrom(yyyyMM);
			requestData.setYearMonthTo(yyyyMM);
		} else if (args.length == 2) {
			String yyyyMM = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM", Locale.US));
			requestData.setDateType(args[1]);
			requestData.setYearMonthFrom(yyyyMM);
			requestData.setYearMonthTo(yyyyMM);
		} else if (args.length == 3) {
			requestData.setDateType(args[1]);
			requestData.setYearMonthFrom(args[2]);
			requestData.setYearMonthTo(args[2]);
		} else if (args.length == 4) {
			requestData.setDateType(args[1]);
			requestData.setYearMonthFrom(args[2]);
			requestData.setYearMonthTo(args[3]);
		} else {
			throw new Exception("SyncWsIncfri8000 - Wrong Parameter");
		}
		
		return requestData;
	}
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(RequestData requestData) throws PccRestfulException {
		logger.info("syncData Incfri8000 Start");
		long start = System.currentTimeMillis();
		
		requestData.setDataPerPage(String.valueOf(wsDataSize));
		int indexPage = 0;
		
		String dateTypeCode = ExciseUtils.convertIncfri8000DateType(requestData.getDateType());
		
		List<Income> incomeList = null;
		WsIncfri8000 incfri8000 = null;
		List<WsIncfri8000> incfri8000List = new ArrayList<>();
		WsIncfri8000Credit incfri8000Credit = null;
		List<WsIncfri8000Credit> incfri8000CreditList = new ArrayList<>();
		do {
			indexPage++;
			requestData.setPageNo(String.valueOf(indexPage));
			incomeList = incFri8000Service.execute(requestData).getIncomeList();
			if (incomeList != null && incomeList.size() > 0) {
				for (Income income : incomeList) {
					incfri8000 = new WsIncfri8000();
					incfri8000.setDateType(dateTypeCode);
					incfri8000.setRegId(income.getRegId());
					incfri8000.setNewRegId(income.getNewregId());
					incfri8000.setReceiptNo(income.getReceiptNo());
					incfri8000.setReceiptDate(StringUtils.isNotBlank(income.getReceiptDate()) ? ConvertDateUtils.parseStringToLocalDate(income.getReceiptDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH) : null);
					incfri8000.setTaxAmount(StringUtils.isNotEmpty(income.getTaxAmount()) ? new BigDecimal(income.getTaxAmount()) : BigDecimal.ZERO);
					incfri8000.setPenAmount(StringUtils.isNotEmpty(income.getPenAmount()) ? new BigDecimal(income.getPenAmount()) : BigDecimal.ZERO);
					incfri8000.setAddAmount(StringUtils.isNotEmpty(income.getAddAmount()) ? new BigDecimal(income.getAddAmount()) : BigDecimal.ZERO);
					incfri8000.setReduceAmount(StringUtils.isNotEmpty(income.getReduceAmount()) ? new BigDecimal(income.getReduceAmount()) : BigDecimal.ZERO);
					incfri8000.setCreditAmount(StringUtils.isNotEmpty(income.getCreditAmount()) ? new BigDecimal(income.getCreditAmount()) : BigDecimal.ZERO);
					incfri8000.setOfficeReceiveCode(income.getOfficeReceiveCode());
					incfri8000.setTrnDate(StringUtils.isNotBlank(income.getTrnDate()) ? ConvertDateUtils.parseStringToLocalDate(income.getTrnDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH) : null);
					incfri8000.setDepositDate(StringUtils.isNotBlank(income.getDepositDate()) ? ConvertDateUtils.parseStringToLocalDate(income.getDepositDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH) : null);
					incfri8000.setSendDate(StringUtils.isNotBlank(income.getSendDate()) ? ConvertDateUtils.parseStringToLocalDate(income.getSendDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH) : null);
					incfri8000.setIncomeCode(income.getIncomeCode());
					incfri8000.setIncomeType(income.getIncomeType());
					incfri8000.setIncCtlNo(income.getIncctlNo());
					incfri8000.setOfflineStatus(income.getOfflineStatus());
					incfri8000.setGroupId(income.getGroupId());
					incfri8000.setGroupName(income.getGroupName());
					
					if (income.getCreditList() != null && income.getCreditList().size() > 0) {
						for (Credit credit : income.getCreditList()) {
							incfri8000Credit = new WsIncfri8000Credit();
							incfri8000Credit.setDateType(dateTypeCode);
							incfri8000Credit.setRegId(income.getRegId());
							incfri8000Credit.setNewRegId(income.getNewregId());
							incfri8000Credit.setApproveNo(credit.getApproveNo());
							incfri8000Credit.setApproveDate(StringUtils.isNotBlank(credit.getApproveDate()) ? ConvertDateUtils.parseStringToLocalDate(credit.getApproveDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN) : null);
							incfri8000Credit.setRefDate(WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME_CODE.equals(dateTypeCode) ? incfri8000.getTrnDate() : incfri8000.getReceiptDate());
							incfri8000CreditList.add(incfri8000Credit);
						}
					}
					incfri8000List.add(incfri8000);
				}
			}
		} while (incomeList.size() == wsDataSize);
		
		// Set dateStart and dateEnd
		LocalDate localDateStart = LocalDate.parse(requestData.getYearMonthFrom() + "01", DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate localDateEnd = LocalDate.parse(requestData.getYearMonthTo() + "01", DateTimeFormatter.BASIC_ISO_DATE);
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		wsIncfri8000Repository.forceDeleteByDateType(dateTypeCode, dateStart, dateEnd);
		wsIncfri8000Repository.batchInsert(incfri8000List);
		logger.info("Batch Insert WS_INCFRI8000 Success");
		
		wsIncfri8000CreditRepository.forceDeleteByDateType(dateTypeCode, dateStart, dateEnd);
		wsIncfri8000CreditRepository.batchInsert(incfri8000CreditList);
		logger.info("Batch Insert WS_INCFRI8000_CREDIT Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData Incfri8000 Success, using {} seconds", (float) (end - start) / 1000F);
	}

}
