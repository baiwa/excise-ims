package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000Repository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.IncFri8000Request;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.IncomeList;
import th.go.excise.ims.ws.client.pcc.incfri8000.service.IncFri8000RequestService;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegFri4000Request;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegMaster60List;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000RequestService;

@Service
public class SyncWebServiceTaxAuditSelectFactoryService {

	private static final Logger logger = LoggerFactory.getLogger(SyncWebServiceTaxAuditSelectFactoryService.class);
	@Autowired
	private IncFri8000RequestService incFri8000RequestService;

	@Autowired
	private RegFri4000RequestService regFri4000RequestService;

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	@Autowired
	private TaWsInc8000Repository taWsInc8000Repository;
	
	private String wsDataSizeWs = "1000";

	public void suncData() throws IOException {
		logger.info("SyncData : {}", LocalDateTime.now());
		RegFri4000Request regFri4000Request = new RegFri4000Request();
		regFri4000Request.setType("1");
		regFri4000Request.setDataPerPage(wsDataSizeWs);
		regFri4000Request.setActive("1");
		regFri4000Request.setNewregId("");
		regFri4000Request.setNid("");
		int indexPage = 0;
		List<RegMaster60List> regMaster60List;
		List<TaWsReg4000> taWsReg4000List = new ArrayList<>();
		TaWsReg4000 taWsReg4000 = new TaWsReg4000();
		try {
			taWsReg4000Repository.truncateTaWsReg4000();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		do {
			
			regMaster60List = new ArrayList<>();
			indexPage++;
			regFri4000Request.setPageNo(String.valueOf(indexPage));
			regMaster60List = regFri4000RequestService.postRestFul(regFri4000Request);
			if (regMaster60List != null && regMaster60List.size() > 0) {
				logger.info("Resule Post to ws 4000 Response size : {}", regMaster60List.size());
				taWsReg4000List = new ArrayList<>();
				for (RegMaster60List regMaster60 : regMaster60List) {
					taWsReg4000 = new TaWsReg4000();
					taWsReg4000.setNewRegId(regMaster60.getNewregId());
					taWsReg4000.setCusId(regMaster60.getCusId());
					taWsReg4000.setCusFullname(regMaster60.getCusFullname());
					taWsReg4000.setCusAddress(buildCusAddress(regMaster60));
					taWsReg4000.setCusTelno(regMaster60.getCusTelno());
					taWsReg4000.setCusEmail(regMaster60.getCusEmail());
					taWsReg4000.setCusUrl(regMaster60.getCusUrl());
					taWsReg4000.setFacId(regMaster60.getFacId());
					taWsReg4000.setFacFullname(regMaster60.getFacFullname());
					taWsReg4000.setFacAddress(buildFacAddress(regMaster60));
					taWsReg4000.setFacTelno(regMaster60.getFacTelno());
					taWsReg4000.setFacEmail(regMaster60.getFacEmail());
					taWsReg4000.setFacUrl(regMaster60.getFacUrl());
					taWsReg4000.setOfficeCode(regMaster60.getOffcode());
					taWsReg4000.setActiveFlag(regMaster60.getActiveFlag());
					if (regMaster60.getRegDutyList() != null && regMaster60.getRegDutyList().size() > 0) {
						taWsReg4000.setDutyCode(regMaster60.getRegDutyList().get(0).getGroupId());
					}
					taWsReg4000List.add(taWsReg4000);
				}
				try {
					taWsReg4000Repository.insertBatch(taWsReg4000List);
					logger.info("Add to taWsReg4000 : {completed}");
				} catch (SQLException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			} else {
				logger.info("ws 4000 no response data", regMaster60List.size());
			}
		} while (regMaster60List.size() == Integer.parseInt(wsDataSizeWs));

		LocalDate localDateFrom = LocalDate.of(2016, 2, 14);
		String dateFrom = localDateFrom.format(DateTimeFormatter.ofPattern("yyyyMM"));
		LocalDate localDateTo = LocalDate.of(2019, 2, 14);
		String dateTo = localDateTo.format(DateTimeFormatter.ofPattern("yyyyMM"));
		IncFri8000Request incFri8000Request = new IncFri8000Request();
		incFri8000Request.setYearMonthFrom(dateFrom);
		incFri8000Request.setYearMonthTo(dateTo);
		incFri8000Request.setDateType("Income");
		incFri8000Request.setDataPerPage(wsDataSizeWs);
		indexPage = 0;
		List<IncomeList> incomeList = new ArrayList<>();
		do {
			incomeList = new ArrayList<>();
			indexPage++;
			incFri8000Request.setPageNo(String.valueOf(indexPage));
			incomeList = incFri8000RequestService.postRestFul(incFri8000Request);
			TaWsInc8000 taWsInc8000;
			List<TaWsInc8000> taWsInc8000List = new ArrayList<>();
			if (incomeList != null && incomeList.size() > 0) {
				for (IncomeList income : incomeList) {
					taWsInc8000 = new TaWsInc8000();
					taWsInc8000.setRegId(income.getRegId());
					taWsInc8000.setNewRegId(income.getNewregId());
					taWsInc8000.setReceiptNo(income.getReceiptNo());
					taWsInc8000.setReceiptDate(income.getReceiptDate());
					taWsInc8000.setTaxAmount(income.getTaxAmount() != null && income.getTaxAmount().length() > 0 ? new BigDecimal(income.getTaxAmount()) : BigDecimal.ZERO);
					taWsInc8000.setPenAmount(income.getPenAmount() != null && income.getPenAmount().length() > 0 ? new BigDecimal(income.getPenAmount()) : BigDecimal.ZERO);
					taWsInc8000.setAddAmount(income.getAddAmount() != null && income.getAddAmount().length() > 0 ? new BigDecimal(income.getAddAmount()) : BigDecimal.ZERO);
					taWsInc8000.setReduceAmount(income.getReduceAmount() != null && income.getReduceAmount().length() > 0 ? new BigDecimal(income.getReduceAmount()) : BigDecimal.ZERO);
					taWsInc8000.setCreditAmount(income.getCreditAmount() != null && income.getCreditAmount().length() > 0 ? new BigDecimal(income.getCreditAmount()) : BigDecimal.ZERO);
					taWsInc8000.setOfficeReceiveCode(income.getOfficeReceiveCode());
					taWsInc8000.setTrnDate(income.getTrnDate());
					taWsInc8000.setDepositDate(income.getDepositDate());
					taWsInc8000.setSendDate(income.getSendDate());
					taWsInc8000.setIncomeCode(income.getIncomeCode());
					taWsInc8000.setIncomeType(income.getIncomeType());
					taWsInc8000.setCreatedBy(SecurityConstants.SYSTEM_USER.SYSTEM);
					taWsInc8000List.add(taWsInc8000);
				}
				try {
					taWsInc8000Repository.insertBatchList(taWsInc8000List);
					logger.info("Add to taWsInc8000 : {completed}");
				} catch (SQLException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			}
		} while (incomeList.size() == Integer.parseInt(wsDataSizeWs));

	}

	private String buildCusAddress(RegMaster60List regMaster60List) {
		StringBuilder address = new StringBuilder(regMaster60List.getCusAddrno());
		if (regMaster60List.getCusBuildname() != null) {
			address.append(" ").append(regMaster60List.getCusBuildname());
		}
		if (regMaster60List.getCusFloorno() != null) {
			address.append(" ").append(regMaster60List.getCusFloorno());
		}
		if (regMaster60List.getCusRoomno() != null) {
			address.append(" ").append(regMaster60List.getCusRoomno());
		}
		if (regMaster60List.getCusMoono() != null) {
			address.append(" ").append(regMaster60List.getCusMoono());
		}
		if (regMaster60List.getCusVillage() != null) {
			address.append(" ").append(regMaster60List.getCusVillage());
		}
		if (regMaster60List.getCusSoiname() != null) {
			address.append(" ").append(regMaster60List.getCusSoiname());
		}
		if (regMaster60List.getCusThnname() != null) {
			address.append(" ").append(regMaster60List.getCusThnname());
		}
		if (regMaster60List.getCusTambolname() != null) {
			address.append(" ").append(regMaster60List.getCusTambolname());
		}
		if (regMaster60List.getCusAmphurname() != null) {
			address.append(" ").append(regMaster60List.getCusAmphurname());
		}
		if (regMaster60List.getCusProvincename() != null) {
			address.append(" ").append(regMaster60List.getCusProvincename());
		}
		if (regMaster60List.getCusZipcode() != null) {
			address.append(" ").append(regMaster60List.getCusZipcode());
		}
		return address.toString();

	}

	private String buildFacAddress(RegMaster60List regMaster60List) {
		StringBuilder address = new StringBuilder(regMaster60List.getFacAddrno());
		if (regMaster60List.getFacBuildname() != null) {
			address.append(" ").append(regMaster60List.getFacBuildname());
		}
		if (regMaster60List.getFacFloorno() != null) {
			address.append(" ").append(regMaster60List.getFacFloorno());
		}
		if (regMaster60List.getFacRoomno() != null) {
			address.append(" ").append(regMaster60List.getFacRoomno());
		}
		if (regMaster60List.getFacMoono() != null) {
			address.append(" ").append(regMaster60List.getFacMoono());
		}
		if (regMaster60List.getFacVillage() != null) {
			address.append(" ").append(regMaster60List.getFacVillage());
		}
		if (regMaster60List.getFacSoiname() != null) {
			address.append(" ").append(regMaster60List.getFacSoiname());
		}
		if (regMaster60List.getFacThnname() != null) {
			address.append(" ").append(regMaster60List.getFacThnname());
		}
		if (regMaster60List.getFacTambolname() != null) {
			address.append(" ").append(regMaster60List.getFacTambolname());
		}
		if (regMaster60List.getFacAmphurname() != null) {
			address.append(" ").append(regMaster60List.getFacAmphurname());
		}
		if (regMaster60List.getFacProvincename() != null) {
			address.append(" ").append(regMaster60List.getFacProvincename());
		}
		if (regMaster60List.getFacZipcode() != null) {
			address.append(" ").append(regMaster60List.getFacZipcode());
		}
		return address.toString();

	}

}
