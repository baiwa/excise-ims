package th.co.baiwa.excise.ia.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAud;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAudDtl;
import th.co.baiwa.excise.ia.persistence.repository.IncomeExciseAudDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.IncomeExciseAudRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;

@Service
public class IncomeExciseAudService {

	private Logger logger = LoggerFactory.getLogger(IncomeExciseAudService.class);
	
	@Autowired
	private IncomeExciseAudRepository incomeExciseAudRepository;
	
	@Autowired
	private IncomeExciseAudDtlRepository incomeExciseAudDtlRepository;
	
	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	
	public IncomeExciseAud createIncomeExciseAud(IncomeExciseAud incomeExciseAud) {
		logger.info("createIncomeExciseAud startMonth = {} || endMonth = {} "+ incomeExciseAud.getStartMonth() , incomeExciseAud.getEndMonth());
		List<IncomeList> incomeLists = new ArrayList<IncomeList>();
		List<IncomeExciseAudDtl> incDetailList = new ArrayList<IncomeExciseAudDtl>();
		IncomeExciseAudDtl incomeExciseAudDtl = new IncomeExciseAudDtl();
		int indexPage = 0;
		int pageSize = 100;
		do {
			
			incomeLists = new ArrayList<IncomeList>();
			incomeExciseAud = incomeExciseAudRepository.save(incomeExciseAud);
			IncFri8020 incFri8020 = webServiceExciseService.IncFri8020("", incomeExciseAud.getStartMonth(), incomeExciseAud.getEndMonth(), "Income", indexPage,++pageSize );
			if(BeanUtils.isNotEmpty(incFri8020) && BeanUtils.isNotEmpty(incFri8020.getResponseData())) {
				incomeLists = incFri8020.getResponseData().getIncomeList();
				if(BeanUtils.isNotEmpty(incomeLists)) {
					
					for (IncomeList incomeList : incomeLists) {
						incomeExciseAudDtl = new IncomeExciseAudDtl();
						incomeExciseAudDtl.setIaIncomeExciseAudId(incomeExciseAud.getIaIncomeExciseAudId());
						incomeExciseAudDtl.setReceiptNo(incomeList.getReceiptNo());
						incomeExciseAudDtl.setReceiptDate(incomeList.getReceiptDate());
						incomeExciseAudDtl.setReceiptStatus(incomeList.getReceiptStatus());
						incomeExciseAudDtl.setIncomeName(incomeList.getIncomeName());
						incomeExciseAudDtl.setIncomeCode(incomeList.getIncomeCode());
						incomeExciseAudDtl.setOfficeCode(incomeList.getOfficeReceive());
						incomeExciseAudDtl.setPinNidId(incomeList.getPinNidId());
						incDetailList.add(incomeExciseAudDtl);
					}
					try {
						incomeExciseAudDtlRepository.insertIncomeExciseAudDtlBatch(incDetailList);
					} catch (SQLException e) {
						logger.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
			
		}while(BeanUtils.isNotEmpty(incomeLists) && incomeLists.size() == pageSize);
		
		return incomeExciseAud;
	}
	
	
}
