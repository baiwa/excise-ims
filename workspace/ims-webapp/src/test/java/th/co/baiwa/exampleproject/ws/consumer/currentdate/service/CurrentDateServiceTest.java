package th.co.baiwa.exampleproject.ws.consumer.currentdate.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrentDateServiceTest {
	
	@Autowired
	private CurrentDateService currentDateService;
	
	@Test
	public void test_exciseCurrentDate() {
		String currentDate = currentDateService.exciseSystemDate();
		System.out.println("currentDate=" + currentDate);
	}
	
}
