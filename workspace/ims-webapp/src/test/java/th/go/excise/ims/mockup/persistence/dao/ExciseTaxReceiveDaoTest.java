package th.go.excise.ims.mockup.persistence.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.Application;
import th.go.excise.ims.mockup.persistence.entity.ExciseTaxReceive;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ExciseTaxReceiveDaoTest {
	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;
	
	
	@Test
	public void test(){
		
		String exciseId = "2533-00254-8";
		List<ExciseTaxReceive> resultList = exciseTaxReceiveDao.queryByExciseId(exciseId);
		resultList.forEach(s -> System.out.println(s));
		System.out.println("Hollo");
		
	}
}
