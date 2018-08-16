package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfDtl;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfHdrRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class RiskAssInfService {

	private static final Logger logger = LoggerFactory.getLogger(RiskAssInfService.class);

	private final RiskAssInfHdrRepository riskAssInfHdrRepository;

	
	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
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
	
	public List<RiskAssInfDtl> findRiskAssInfDtlByWebService() {
		return webServiceExciseService.getRiskAssInfDtlList(new RiskAssInfDtl());
	}
}
