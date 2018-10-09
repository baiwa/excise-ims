package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.excise.ia.persistence.entity.IaWithdrawalList;
import th.co.baiwa.excise.ia.persistence.entity.IaWithdrawalPersons;
import th.co.baiwa.excise.ia.persistence.repository.IaWithdrawalListRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaWithdrawalPersonsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Int02m2ServiceTest {
	
	
	@Autowired
	private IaWithdrawalListRepository iaWithdrawalListRepository;
	
	@Autowired
	private IaWithdrawalPersonsRepository iaWithdrawalPersonsRepository;
	
	@Test
	public void test() {
		IaWithdrawalList idList = new IaWithdrawalList();
		
//		idList.setBudgetType("เงินงบประมาณ");
//		idList.setItemDesc("ค่าตอบแทนพนักงาน");		
//		iaWithdrawalListRepository.save(idList);
		
		IaWithdrawalPersons iaPerson = new IaWithdrawalPersons();
		
		iaPerson.setPaymentDate(new Date());		
		iaPerson.setWithdrawalId(1l);
		iaPerson.setAmount(new BigDecimal(2500));
		iaPerson.setRefPayment("10287725");
		iaPerson.setPayee("นายสุรวิทย์  ใจมั่น");
		iaWithdrawalPersonsRepository.save(iaPerson);
	}
	
}
