package th.co.baiwa.excise.ia.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Int02m2ServiceTest {
	
	@Autowired
	private Int02m2Service int02m2Service;
	
	//@Test
	public void test() {
		int02m2Service.findData("2561");
	}
	
}
