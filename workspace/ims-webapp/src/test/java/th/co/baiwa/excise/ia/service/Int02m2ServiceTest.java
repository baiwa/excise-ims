package th.co.baiwa.excise.ia.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.excise.ia.persistence.repository.IaWithdrawalPersonsRepository;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetHeaderDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Int02m2ServiceTest {
	
	
	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;
	
	@Autowired
	private IaWithdrawalPersonsRepository iaWithdrawalPersonsRepository;
	
	@Test
	public void test() {
		planWorksheetHeaderDao.createNewPlanWorkSheetHeaderByAnalysNumber("1234");
		planWorksheetHeaderDao.createNewPlanWorkSheetDetailByAnalysNumber("1234");
	}
	
}
