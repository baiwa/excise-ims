package th.co.baiwa.excise.ia.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssRiskWsDtlRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskAssRiskWsServiceTest {

	@Autowired
	private RiskAssRiskWsService riskAssRiskWsService;
	
	@Autowired
	private RiskAssRiskWsDtlRepository riskAssRiskWsDtlRepository;
	
//	@Test
	public void testNaja() {
		new Date();
		RiskAssRiskWsHdr riskAssRiskWsHdr = new RiskAssRiskWsHdr();
		riskAssRiskWsHdr.setBudgetYear("2561");
		List<RiskAssRiskWsHdr> riskAssRiskWsHdrList = riskAssRiskWsService.findByCriteria(riskAssRiskWsHdr);
		riskAssRiskWsHdrList.forEach(r -> System.out.println(r.getCreatedDate()));
		
	}
	@Test
	public void findAll() {
		new Date();
		RiskAssRiskWsHdr riskAssRiskWsHdr = new RiskAssRiskWsHdr();
		riskAssRiskWsHdr.setBudgetYear("2561");
		List<RiskAssRiskWsDtl> riskAssRiskWsDtls = riskAssRiskWsDtlRepository.findAll();
				riskAssRiskWsDtls.forEach(r -> System.out.println(r.getCreatedDate()));
		
	}
	
	
	
	
}
