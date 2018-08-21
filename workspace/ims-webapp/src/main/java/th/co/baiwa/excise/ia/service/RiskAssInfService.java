package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfDtl;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfHdrRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfDtlRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class RiskAssInfService {

	private static final Logger logger = LoggerFactory.getLogger(RiskAssInfService.class);

	private final RiskAssInfHdrRepository riskAssInfHdrRepository;
	private final String BUDGET_YEAR = "BUDGET_YEAR";
	private final String RISK_CONFIG = "RISK_CONFIG";
	private final String INT08_2 = "INT08-2";
	
	@Autowired
	private LovRepository lovRepository;
	
	@Autowired
	private RiskAssInfDtlRepository riskAssInfDtlRepository;
	
//	@Autowired
//	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	public RiskAssInfService(RiskAssInfHdrRepository riskAssInfHdrRepository) {
		this.riskAssInfHdrRepository = riskAssInfHdrRepository;
	}

	public Message createRiskAssInfHdrRepository(RiskAssInfHdr riskAssInfHdr) {
		logger.info("Risk Name : " + riskAssInfHdr.getRiskAssInfHdrName());
		Message message = null;
		List<RiskAssInfHdr> riskAssInfHdrList = riskAssInfHdrRepository.findByCriteria(riskAssInfHdr);

		if (BeanUtils.isEmpty(riskAssInfHdrList) && riskAssInfHdrList.size() == 0) {

			RiskAssInfHdr riskInfHdr = riskAssInfHdrRepository.save(riskAssInfHdr);

			if (BeanUtils.isNotEmpty(riskInfHdr.getRiskAssInfHdrId())) {
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
	}
	
	
	public ResponseDataTable<RiskAssInfHdr> findByCriteriaForDatatable(RiskAssInfHdr riskAssInfHdr, DataTableRequest dataTableRequest) {
		
		ResponseDataTable<RiskAssInfHdr> responseDataTable = new ResponseDataTable<RiskAssInfHdr>();
		List<RiskAssInfHdr> riskAssInfHdrList = riskAssInfHdrRepository.findByCriteria(riskAssInfHdr);
		
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(riskAssInfHdrList);
		responseDataTable.setRecordsTotal((int) riskAssInfHdrList.size());
		responseDataTable.setRecordsFiltered((int) riskAssInfHdrList.size());
		return responseDataTable;

	}
	
	public Message deleteRiskAssInfHdrRepository(RiskAssInfHdr riskAssInfHdr) {
		logger.info("id : "+riskAssInfHdr.getRiskAssInfHdrId());
		riskAssInfHdrRepository.delete(riskAssInfHdr.getRiskAssInfHdrId());
		Message message = ApplicationCache.getMessage("MSG_00005");
		return message;

	}
	
	
	public Message createBudgetYear(RiskAssInfHdr riskAssInfHdr) {
		Message message = null;
		Lov lov = new Lov(BUDGET_YEAR);
		lov.setValue1(riskAssInfHdr.getBudgetYear());
		lov.setSubType(INT08_2);
		
		List<Lov> lovList = lovRepository.queryLovByCriteria(lov, null);
		if(lovList == null || lovList.size() == 0) {
			lovRepository.save(lov);
			Lov dataInit = new Lov(RISK_CONFIG);
			dataInit.setSubType(INT08_2);

			RiskAssInfHdr insertConfigData = null;
			List<Lov> lovInitList = lovRepository.queryLovByCriteria(dataInit, null);
			for (Lov lov2 : lovInitList) {
				insertConfigData = new RiskAssInfHdr();
				insertConfigData.setRiskAssInfHdrName(lov2.getValue1());
				insertConfigData.setBudgetYear(riskAssInfHdr.getBudgetYear());
				riskAssInfHdrRepository.save(insertConfigData);	
			}
			message = ApplicationCache.getMessage("MSG_00002");
		}else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
		
	}
	
	
	
	public List<RiskAssInfDtl> findRiskAssInfDtlByWebService() {
//		return webServiceExciseService.getRiskAssInfDtlList(new RiskAssInfDtl());
		return null;
	}
	
	
	public RiskAssInfHdr findById(Long id) {
		return  riskAssInfHdrRepository.findOne(id);
	}
	
	public void updateRiskAssInfHdr(RiskAssInfHdr riskAssInfHdr) {
		riskAssInfHdrRepository.save(riskAssInfHdr);
	}
	public void updateRiskAssInfDtl(List<RiskAssInfDtl> riskAssInfDtls) {
		riskAssInfDtlRepository.save(riskAssInfDtls);
	}
}
