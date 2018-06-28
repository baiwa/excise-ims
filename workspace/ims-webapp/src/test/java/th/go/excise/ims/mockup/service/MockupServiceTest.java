package th.go.excise.ims.mockup.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.Application;
import th.co.baiwa.excise.ia.persistence.dao.ExciseRegisttionNumberDao;
import th.co.baiwa.excise.ta.service.MockupService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class MockupServiceTest {
	
	@Autowired
	private MockupService mockupService;
	
	@Autowired
	private ExciseRegisttionNumberDao exciseRegisttionNumberDao;
	
//	@Test
//	public void test() {
//		ResponseDataTable<MockupVo> response = mockupService.findAll("2533-00254-8");
//		response.getData().forEach(s -> System.out.println(s));
//	}
	
//	@Test
//	public void test() {
//		exciseRegisttionNumberDao.queryByExciseId("123");
//	}
//	
	
}
