package th.go.excise.ims.mockup.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.Application;
import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.persistence.dao.ExciseRegisttionNumberDao;

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
