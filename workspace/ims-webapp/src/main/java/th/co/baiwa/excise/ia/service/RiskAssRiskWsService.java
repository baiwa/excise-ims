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
import th.co.baiwa.excise.ia.persistence.repository.RiskAssRiskWsHdrRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class RiskAssRiskWsService {
	private static final Logger logger = LoggerFactory.getLogger(RiskAssRiskWsService.class);
	private final RiskAssRiskWsHdrRepository riskAssRiskWsHdrRepository;
	private final String LOV_BUGGET_YEAR = "BUGGET_YEAR";
	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	private LovRepository lovRepository;

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
	
	
	public ResponseDataTable<RiskAssRiskWsDtl> findRiskAssRiskDtlByWebService() {

		ResponseDataTable<RiskAssRiskWsDtl> responseDataTable = new ResponseDataTable<RiskAssRiskWsDtl>();
		List<RiskAssRiskWsDtl> riskAssRiskWsHdrList = webServiceExciseService.getRiskAssRiskWsDtlList(new RiskAssRiskWsDtl());
//		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(riskAssRiskWsHdrList);
		responseDataTable.setRecordsTotal((int) riskAssRiskWsHdrList.size());
		responseDataTable.setRecordsFiltered((int) riskAssRiskWsHdrList.size());
		return responseDataTable;

	}

	public Message deleteRiskAssRiskWsHdrRepository(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Risk Name : " + riskAssRiskWsHdr.getRiskHdrName());
		riskAssRiskWsHdrRepository.delete(riskAssRiskWsHdr.getRiskHrdId());
		Message message = ApplicationCache.getMessage("MSG_00005");
		return message;

	}
	
	public Message createBuggetYear(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		Message message = null;
		Lov lov = new Lov(LOV_BUGGET_YEAR);
		lov.setValue1(riskAssRiskWsHdr.getBuggetYear());
		List<Lov> lovList = lovRepository.queryLovByCriteria(lov, null);
		if(lovList == null || lovList.size() == 0) {
			lovRepository.save(lov);
			message = ApplicationCache.getMessage("MSG_00002");
		}else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
		
	}
	
	

	public List<RiskAssRiskWsHdr> findByCriteria(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		return riskAssRiskWsHdrRepository.findByCriteria(riskAssRiskWsHdr);
	}
}
