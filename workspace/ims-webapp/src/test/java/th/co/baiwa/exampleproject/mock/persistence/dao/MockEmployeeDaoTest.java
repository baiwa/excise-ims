package th.co.baiwa.exampleproject.mock.persistence.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.exampleproject.mock.persistence.entity.MockEmployee;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = PROFILE.UNITTEST_MOCK)
public class MockEmployeeDaoTest {
	
	@Autowired
	private MockEmployeeDao mockEmployeeDao;
	
	@Test
	public void test_findById() {
		System.out.println("- - - - - findById - - - - -");
		MockEmployee mockEmployee = mockEmployeeDao.findById(1L);
		System.out.println(mockEmployee);
	}
	
	@Test
	public void test_findById_resultMap() {
		System.out.println("- - - - - findById_resultMap - - - - -");
		Map<String, Object> mockEmployee = mockEmployeeDao.findById_Map(99L);
		System.out.println(mockEmployee);
	}
	
	//@Test
	public void test_findByIdWithBeanProperty() {
		System.out.println("- - - - - findByIdWithBeanProperty - - - - -");
		MockEmployee mockEmployee = mockEmployeeDao.findByIdWithBeanProperty(1L);
		System.out.println(mockEmployee);
	}
	
	//@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<MockEmployee> mockEmployeeList = mockEmployeeDao.findAll();
		System.out.println(StringUtils.collectionToCommaDelimitedString(mockEmployeeList));
	}
	
	//@Test
	public void test_insert() {
		System.out.println("- - - - - insert - - - - -");
		System.out.println("before insert count: " + mockEmployeeDao.count());
		
		MockEmployee mockEmployee = new MockEmployee();
		mockEmployee.setFirstName("Test FirstName");
		mockEmployee.setLastName("My LastName");
		mockEmployee.setSalary(new BigDecimal("32145"));
		mockEmployee.setWorkingDate(new Date());
		mockEmployeeDao.insert(mockEmployee);
		
		System.out.println("after insert count: " + mockEmployeeDao.count());
		List<MockEmployee> mockEmployeeList = mockEmployeeDao.findAll();
		System.out.println(StringUtils.collectionToCommaDelimitedString(mockEmployeeList));
	}
	
	//@Test
	public void test_insertNew() {
		System.out.println("- - - - - insertNew - - - - -");
		System.out.println("before insert count: " + mockEmployeeDao.count());
		
		MockEmployee mockEmployee = new MockEmployee();
		mockEmployee.setFirstName("Test FirstName");
		mockEmployee.setLastName("My LastName");
		mockEmployee.setSalary(new BigDecimal("32145"));
		mockEmployee.setWorkingDate(new Date());
		mockEmployeeDao.insertNew(mockEmployee);
		
		System.out.println("after insert count: " + mockEmployeeDao.count());
		List<MockEmployee> mockEmployeeList = mockEmployeeDao.findAll();
		System.out.println(StringUtils.collectionToCommaDelimitedString(mockEmployeeList));
	}
	
	//@Test
	public void test_insertBySimpleJdbcInsert() {
		System.out.println("- - - - - insertBySimpleJdbcInsert - - - - -");
		System.out.println("before insert count: " + mockEmployeeDao.count());
		
		MockEmployee mockEmployee = new MockEmployee();
		mockEmployee.setFirstName("Test FirstName");
		mockEmployee.setLastName("My LastName");
		mockEmployee.setSalary(new BigDecimal("32145"));
		mockEmployee.setWorkingDate(new Date());
		mockEmployee.setIsDeleted("N");
		mockEmployee.setCreatedBy("SU");
		mockEmployee.setCreatedDate(new Date());
		mockEmployeeDao.insertBySimpleJdbcInsert(mockEmployee);
		
		System.out.println("after insert count: " + mockEmployeeDao.count());
		List<MockEmployee> mockEmployeeList = mockEmployeeDao.findAll();
		System.out.println(StringUtils.collectionToCommaDelimitedString(mockEmployeeList));
	}
	
	//@Test
	public void test_update() {
		System.out.println("- - - - - update - - - - -");
		System.out.println("before update: " + mockEmployeeDao.findById(1L));
		
		MockEmployee mockEmployee = new MockEmployee();
		mockEmployee.setFirstName("UPDATE NaJa");
		mockEmployee.setSalary(new BigDecimal("5555"));
		mockEmployee.setEmpId(1L);
		mockEmployeeDao.update(mockEmployee);
		
		System.out.println("after update: " + mockEmployeeDao.findById(1L));
	}
	
	//@Test
	public void test_updateNew() {
		System.out.println("- - - - - updateNew - - - - -");
		System.out.println("before update: " + mockEmployeeDao.findById(2L));
		
		MockEmployee mockEmployee = new MockEmployee();
		mockEmployee.setFirstName("UPDATE NaJa Ha ha");
		mockEmployee.setSalary(new BigDecimal("9999"));
		mockEmployee.setEmpId(2L);
		mockEmployeeDao.update(mockEmployee);
		
		System.out.println("after update: " + mockEmployeeDao.findById(2L));
	}
	
	//@Test
	public void test_deleteById() {
		System.out.println("- - - - - deleteById - - - - -");
		System.out.println("before delete count: " + mockEmployeeDao.count());
		mockEmployeeDao.deleteById(2L);
		System.out.println("after delete count: " + mockEmployeeDao.count());
	}
	
	//@Test
	public void test_batchInsert() throws SQLException {
		System.out.println("- - - - - batchInsert - - - - -");
		System.out.println("before insert count: " + mockEmployeeDao.count());
		
		List<MockEmployee> mockEmployeeList = new ArrayList<MockEmployee>();
		MockEmployee mockEmployee = null;
		for (int i = 0; i < 10000; i++) {
			mockEmployee = new MockEmployee();
			mockEmployee.setFirstName("Name" + (i + 1));
			mockEmployee.setLastName("LastName" + (i + 1));
			mockEmployee.setSalary(new BigDecimal((i + 1)));
			mockEmployee.setWorkingDate(new Date());
			mockEmployeeList.add(mockEmployee);
		}
		
		int[][] result = mockEmployeeDao.batchInsert(mockEmployeeList, 1000);
		System.out.println(result.length);
		
		System.out.println("after insert count: " + mockEmployeeDao.count());
		//System.out.println(StringUtils.collectionToCommaDelimitedString(mockEmployeeDao.findAll()));
	}
	
	//@Test
	public void test_batchUpdate() throws SQLException {
		System.out.println("- - - - - batchUpdate - - - - -");
		
		MockEmployee mockEmployee1 = new MockEmployee();
		mockEmployee1.setFirstName("Name1");
		mockEmployee1.setLastName("LastName1");
		mockEmployee1.setEmpId(1L);
		
		MockEmployee mockEmployee2 = new MockEmployee();
		mockEmployee2.setFirstName("Name2");
		mockEmployee2.setLastName("LastName2");
		mockEmployee2.setEmpId(2L);
		
		MockEmployee mockEmployee3 = new MockEmployee();
		mockEmployee3.setFirstName("Name3");
		mockEmployee3.setLastName("LastName3");
		mockEmployee3.setEmpId(3L);
		
		List<MockEmployee> mockEmployeeList = Arrays.asList(
			mockEmployee1,
			mockEmployee2,
			mockEmployee3
		);
		
		int[][] result = mockEmployeeDao.batchUpdate(mockEmployeeList, 2);
		System.out.println(result.length);
		
		System.out.println(StringUtils.collectionToCommaDelimitedString(mockEmployeeDao.findAll()));
	}
	
}
