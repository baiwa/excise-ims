package th.co.baiwa.buckwaframework.preferences.persistence.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ParameterGroupDaoTest {
	
	@Autowired
	private ParameterGroupDao parameterGroupDao;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<ParameterGroup> paramGroupList = parameterGroupDao.findAll();
		System.out.println(paramGroupList);
		Assert.assertNotEquals(0, paramGroupList.size());
	}
	
	@Test
	public void test_findById_Found() {
		System.out.println("- - - - - findById_Found - - - - -");
		ParameterGroup paramGroup = parameterGroupDao.findById(1L);
		System.out.println(paramGroup);
		Assert.assertNotNull(paramGroup);
	}
	
	@Test
	public void test_findById_NotFound() {
		System.out.println("- - - - - findById_NotFound - - - - -");
		ParameterGroup paramGroup = parameterGroupDao.findById(99L);
		System.out.println(paramGroup);
		Assert.assertNull(paramGroup);
	}
	
	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		int count = parameterGroupDao.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}
	
	//@Test
	public void test_insert() {
		System.out.println("- - - - - insert - - - - -");
		ParameterGroup paramGroup = new ParameterGroup();
		paramGroup.setParamGroupCode("UNIT_TEST");
		paramGroup.setParamGroupDesc("For Unit Testing");
		Long pk = parameterGroupDao.insert(paramGroup);
		Assert.assertNotNull(pk);
	}
	
	//@Test
	public void test_update() {
		System.out.println("- - - - - update - - - - -");
		ParameterGroup paramGroup = parameterGroupDao.findById(2L);
		paramGroup.setParamGroupDesc("For Unit Testing edit");
		int updateRow = parameterGroupDao.update(paramGroup);
		Assert.assertEquals(1, updateRow);
	}
	
	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		int updateRow = parameterGroupDao.delete(2L);
		Assert.assertEquals(1, updateRow);
	}
	
}
