package th.go.excise.ims.mockup.persistence.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.Application;
import th.go.excise.ims.mockup.persistence.entity.ExciseRegistartionNumber;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ExciseRegisttionNumberDaoTest {
	
	@Autowired
	private ExciseRegisttionNumberDao exciseRegisttionNumberDao;
	
//	@Test
//	public void test_queryByExciseId() {
//		System.out.println("hello");
//		String exciseId = "2533-00254-8";
//		List<ExciseRegistartionNumber> resultList = exciseRegisttionNumberDao.queryByExciseId(exciseId);
//		resultList.forEach(s -> System.out.println(s));
//		System.out.println("hello-end");
//	}
//	
}
