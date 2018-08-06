package th.co.baiwa.excise.ia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.excise.ia.persistence.repository.RiskAssRiskWsHdrRepository;

public class RiskAssRiskWsHdrService {
private static final Logger logger = LoggerFactory.getLogger(RiskAssRiskWsHdrService.class);
	
	private final RiskAssRiskWsHdrRepository riskAssRiskWsHdrRepository;
	
	@Autowired
	public RiskAssRiskWsHdrService(RiskAssRiskWsHdrRepository riskAssRiskWsHdrRepository) {
		this.riskAssRiskWsHdrRepository = riskAssRiskWsHdrRepository;
	}
	
	
}
