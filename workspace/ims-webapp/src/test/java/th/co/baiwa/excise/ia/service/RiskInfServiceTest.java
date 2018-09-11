package th.co.baiwa.excise.ia.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfOtherDtl;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfOtherDtlRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskInfServiceTest {
	
	@Autowired
	private RiskAssInfOtherDtlRepository riskAssInfOtherDtlRepository;
	
	@Test
	public void findAll() {
		List<RiskAssInfOtherDtl> riskAssRiskInfDtls = riskAssInfOtherDtlRepository.findAll();
		Gson gson = new Gson();
		riskAssRiskInfDtls.forEach(r -> System.out.println(gson.toJson(r)));
		
	}
	
}
