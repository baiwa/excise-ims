package th.co.baiwa.exampleproject.examplemodule.service;

import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.exampleproject.examplemodule.persistence.entity.BuckwaTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = PROFILE.UNITTEST)
public class BuckwaTestServiceTest {
	
	private final static Logger logger = LoggerFactory.getLogger(BuckwaTestServiceTest.class);
	
	@Autowired
	private BuckwaTestService buckwaTestService;
	
	@Test
	public void testExecuteQuery() {
		List<BuckwaTest> resultList = buckwaTestService.getAll();
		logger.info(String.valueOf(resultList.size()));
		for (BuckwaTest buckWaTest : resultList) {
			logger.info(buckWaTest.toString());
		}
		assertNotEquals(0, resultList.size());
	}
	
	//@Test
	public void testExecuteQueryWithBeanProperty() {
		List<BuckwaTest> resultList = buckwaTestService.getAllWithBeanProperty();
		logger.info(String.valueOf(resultList.size()));
		for (BuckwaTest buckWaTest : resultList) {
			logger.info(buckWaTest.toString());
		}
		assertNotEquals(0, resultList.size());
	}
	
	//@Test
	public void testExecuteQueryForObject() {
		int result = buckwaTestService.count();
		logger.info("executeQueryForObject: " + result);
		logger.debug("D E B U G");
		logger.info("I N F O");
		logger.warn("W A R N");
		//for (int i = 0; i < 100000; i++) {
		//	logger.error("E R R O R");
		//}
		assertNotEquals(0, result);
	}
	
	//@Test
	public void testExecuteInsert() {
		BuckwaTest buckwaTest = new BuckwaTest();
		buckwaTest.setColVarchar("TEST_STRING");
		buckwaTest.setColInt(1);
		buckwaTest.setColDouble(20D);
		buckwaTest.setColDecimal(BigDecimal.TEN);
		buckwaTest.setColTimestamp(new Date());
		buckwaTest.setColDate(new Date());
		buckwaTest.setColTime(new Date());
		
		buckwaTestService.insert(buckwaTest);
	}
	
	//@Test
	public void testExecuteInsertTransactional() throws Exception {
		BuckwaTest buckwaTest = new BuckwaTest();
		buckwaTest.setColVarchar("TEST_STRING");
		buckwaTest.setColInt(1);
		buckwaTest.setColDouble(20D);
		buckwaTest.setColDecimal(BigDecimal.TEN);
		buckwaTest.setColTimestamp(new Date());
		buckwaTest.setColDate(new Date());
		buckwaTest.setColTime(new Date());
		
		buckwaTestService.insertTransactional(buckwaTest);
	}
	
	//@Test
	public void testExecuteUpdate() {
		BuckwaTest buckwaTest = new BuckwaTest();
		buckwaTest.setColVarchar("TEST_UPDATE");
		buckwaTest.setColPk(1L);
		
		buckwaTestService.update(buckwaTest);
	}
	
	//@Test
	public void testExecuteBatch() throws SQLException {
		List<BuckwaTest> buckwaTestList = new ArrayList<BuckwaTest>();
		BuckwaTest buckwaTest = null;
		for (int i = 0; i < 20; i++) {
			buckwaTest = new BuckwaTest();
			buckwaTest.setColVarchar("Batch" + (i + 1));
			buckwaTestList.add(buckwaTest);
		}
		
		int[][] result = buckwaTestService.insertList(buckwaTestList, 4);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.println("[" + i + "][" + j + "]: " + result[i][j]);
			}
		}
	}
	
}
