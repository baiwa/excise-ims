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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ParameterInfoDaoTest {
	
	@Autowired
	private ParameterInfoDao parameterInfoDao;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<ParameterInfo> paramInfoList = parameterInfoDao.findAll();
		System.out.println(paramInfoList);
		Assert.assertNotEquals(0, paramInfoList.size());
	}
	
	@Test
	public void test_findById_Found() {
		System.out.println("- - - - - findById_Found - - - - -");
		ParameterInfo paramInfo = parameterInfoDao.findById(1L);
		System.out.println(paramInfo);
		Assert.assertNotNull(paramInfo);
	}
	
	@Test
	public void test_findById_NotFound() {
		System.out.println("- - - - - findById_NotFound - - - - -");
		ParameterInfo paramInfo = parameterInfoDao.findById(99L);
		System.out.println(paramInfo);
		Assert.assertNull(paramInfo);
	}
	
	@Test
	public void test_findByParamGroupId() {
		System.out.println("- - - - - findByParamGroupId - - - - -");
		List<ParameterInfo> paramInfoList = parameterInfoDao.findByParamGroupId(2L);
		System.out.println(paramInfoList);
		Assert.assertNotEquals(0, paramInfoList.size());
	}
	
	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		int count = parameterInfoDao.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}
	
	@Test
	public void test_countByParamGroupId() {
		System.out.println("- - - - - countByParamGroupId - - - - -");
		int count = parameterInfoDao.countByParamGroupId(2L);
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}
	
	//@Test
	public void test_insert() {
		System.out.println("- - - - - insert - - - - -");
		ParameterInfo paramInfo = new ParameterInfo();
		paramInfo.setParamGroupId(2L);
		paramInfo.setParamCode("TEST2");
		paramInfo.setValue1("v1");
		paramInfo.setValue2("v2");
		paramInfo.setValue3("v3");
		paramInfo.setValue4("v4");
		paramInfo.setValue5("v5");
		paramInfo.setValue6("v6");
		paramInfo.setIsDefault(FLAG.N_FLAG);
		paramInfo.setSortingOrder(1);
		Long pk = parameterInfoDao.insert(paramInfo);
		Assert.assertNotNull(pk);
	}
	
	//@Test
	public void test_update() {
		System.out.println("- - - - - update - - - - -");
		ParameterInfo paramInfo = parameterInfoDao.findById(2L);
		paramInfo.setValue2("edit");
		int updateRow = parameterInfoDao.update(paramInfo);
		Assert.assertEquals(1, updateRow);
	}
	
	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		int updateRow = parameterInfoDao.delete(2L);
		Assert.assertEquals(1, updateRow);
	}
	
}
