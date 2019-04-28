package th.go.excise.ims.scheduler.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.incfri8000.model.Income;
import th.go.excise.ims.ws.client.pcc.incfri8000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.incfri8000.service.IncFri8000Service;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8000Repository;

@Service
public class SyncWsIncfri8000Service {

	private static final Logger logger = LoggerFactory.getLogger(SyncWsIncfri8000Service.class);

	private final int WS_DATA_SIZE = 500;
	
	@Autowired
	private IncFri8000Service incFri8000Service;
	
	@Autowired
	private WsIncfri8000Repository wsIncfri8000Repository;
	
	public void syncData(RequestData requestData) throws PccRestfulException {
		requestData.setDateType("Income");
		requestData.setDataPerPage(String.valueOf(WS_DATA_SIZE));
		int indexPage = 0;
		
		List<Income> incomeList = null;
		do {
			incomeList = new ArrayList<>();
			indexPage++;
			requestData.setPageNo(String.valueOf(indexPage));
			incomeList = incFri8000Service.execute(requestData).getIncomeList();
			WsIncfri8000 incfri8000;
			List<WsIncfri8000> taWsInc8000List = new ArrayList<>();
			if (incomeList != null && incomeList.size() > 0) {
				for (Income income : incomeList) {
					incfri8000 = new WsIncfri8000();
					incfri8000.setRegId(income.getRegId());
					incfri8000.setNewRegId(income.getNewregId());
					incfri8000.setReceiptNo(income.getReceiptNo());
					incfri8000.setReceiptDate(income.getReceiptDate());
					incfri8000.setTaxAmount(income.getTaxAmount() != null && income.getTaxAmount().length() > 0 ? new BigDecimal(income.getTaxAmount()) : BigDecimal.ZERO);
					incfri8000.setPenAmount(income.getPenAmount() != null && income.getPenAmount().length() > 0 ? new BigDecimal(income.getPenAmount()) : BigDecimal.ZERO);
					incfri8000.setAddAmount(income.getAddAmount() != null && income.getAddAmount().length() > 0 ? new BigDecimal(income.getAddAmount()) : BigDecimal.ZERO);
					incfri8000.setReduceAmount(income.getReduceAmount() != null && income.getReduceAmount().length() > 0 ? new BigDecimal(income.getReduceAmount()) : BigDecimal.ZERO);
					incfri8000.setCreditAmount(income.getCreditAmount() != null && income.getCreditAmount().length() > 0 ? new BigDecimal(income.getCreditAmount()) : BigDecimal.ZERO);
					incfri8000.setOfficeReceiveCode(income.getOfficeReceiveCode());
					incfri8000.setTrnDate(income.getTrnDate());
					incfri8000.setDepositDate(income.getDepositDate());
					incfri8000.setSendDate(income.getSendDate());
					incfri8000.setIncomeCode(income.getIncomeCode());
					incfri8000.setIncomeType(income.getIncomeType());
					incfri8000.setCreatedBy(SecurityConstants.SYSTEM_USER.SYSTEM);
					taWsInc8000List.add(incfri8000);
				}
				wsIncfri8000Repository.batchInsert(taWsInc8000List);
				logger.info("Add to taWsInc8000 : {completed}");
			}
		} while (incomeList.size() == WS_DATA_SIZE);
	}

}
