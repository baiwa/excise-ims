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
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssRiskWsDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssRiskWsHdrRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class RiskAssRiskWsService {
	private static final Logger logger = LoggerFactory.getLogger(RiskAssRiskWsService.class);
	private final RiskAssRiskWsHdrRepository riskAssRiskWsHdrRepository;
	private final String BUGGET_YEAR = "BUGGET_YEAR";
	private final String RISK_CONFIG = "RISK_CONFIG";
	private final String INT08_1 = "INT08-1";
	
	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	private LovRepository lovRepository;
	
	@Autowired
	private RiskAssRiskWsDtlRepository riskAssRiskWsDtlRepository;

	@Autowired
	public RiskAssRiskWsService(RiskAssRiskWsHdrRepository riskAssRiskWsHdrRepository) {
		this.riskAssRiskWsHdrRepository = riskAssRiskWsHdrRepository;
	}

	public Message createRiskAssRiskWsHdrRepository(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Risk Name : " + riskAssRiskWsHdr.getRiskHdrName());
		Message message = null;
		List<RiskAssRiskWsHdr> riskWsHdrList = riskAssRiskWsHdrRepository.findByCriteria(riskAssRiskWsHdr);
		if (BeanUtils.isEmpty(riskWsHdrList) && riskWsHdrList.size() == 0) {
			RiskAssRiskWsHdr riskWsHdr = riskAssRiskWsHdrRepository.save(riskAssRiskWsHdr);
			if (BeanUtils.isNotEmpty(riskWsHdr.getRiskHrdId())) {
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
	}

	public ResponseDataTable<RiskAssRiskWsHdr> findByCriteriaForDatatable(RiskAssRiskWsHdr riskAssRiskWsHdr, DataTableRequest dataTableRequest) {

		ResponseDataTable<RiskAssRiskWsHdr> responseDataTable = new ResponseDataTable<RiskAssRiskWsHdr>();
		List<RiskAssRiskWsHdr> riskAssRiskWsHdrList = riskAssRiskWsHdrRepository.findByCriteria(riskAssRiskWsHdr);
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(riskAssRiskWsHdrList);
		responseDataTable.setRecordsTotal((int) riskAssRiskWsHdrList.size());
		responseDataTable.setRecordsFiltered((int) riskAssRiskWsHdrList.size());
		return responseDataTable;

	}
	
	
	public List<RiskAssRiskWsDtl> findRiskAssRiskDtlByWebService() {
		return webServiceExciseService.getRiskAssRiskWsDtlList(new RiskAssRiskWsDtl());

	}

	public Message deleteRiskAssRiskWsHdrRepository(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Risk Name : " + riskAssRiskWsHdr.getRiskHdrName());
		riskAssRiskWsHdrRepository.delete(riskAssRiskWsHdr.getRiskHrdId());
		Message message = ApplicationCache.getMessage("MSG_00005");
		return message;

	}
	
	public Message createBuggetYear(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		Message message = null;
		Lov lov = new Lov(BUGGET_YEAR);
		lov.setValue1(riskAssRiskWsHdr.getBudgetYear());
		List<Lov> lovList = lovRepository.queryLovByCriteria(lov, null);
		if(lovList == null || lovList.size() == 0) {
			lovRepository.save(lov);
			Lov dataInit = new Lov(RISK_CONFIG);
			dataInit.setSubType(INT08_1);
			RiskAssRiskWsHdr insertConfigData = null;
			List<Lov> lovInitList = lovRepository.queryLovByCriteria(dataInit, null);
			for (Lov lov2 : lovInitList) {
				insertConfigData = new RiskAssRiskWsHdr();
				insertConfigData.setRiskHdrName(lov2.getValue1());
				insertConfigData.setBudgetYear(riskAssRiskWsHdr.getBudgetYear());
				riskAssRiskWsHdrRepository.save(insertConfigData);
				
			}
			message = ApplicationCache.getMessage("MSG_00002");
		}else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
		
	}
	
	public List<RiskAssRiskWsDtl> findByGroupRiskHrdId(Long riskHrdId) {
		return riskAssRiskWsDtlRepository.findByGroupRiskHrdId(riskHrdId);
	}
	
	public void updateRiskAssRiskWsDtl(List<RiskAssRiskWsDtl> riskAssRiskWsDtls) {
		riskAssRiskWsDtlRepository.save(riskAssRiskWsDtls);
	}
	
	public RiskAssRiskWsHdr findById(Long id) {
		return riskAssRiskWsHdrRepository.findOne(id);
	}
	public void updateRiskAssRiskWsHdr(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		riskAssRiskWsHdrRepository.save(riskAssRiskWsHdr);
	}

	public List<RiskAssRiskWsHdr> findByCriteria(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		return riskAssRiskWsHdrRepository.findByCriteria(riskAssRiskWsHdr);
	}
	
	
}
