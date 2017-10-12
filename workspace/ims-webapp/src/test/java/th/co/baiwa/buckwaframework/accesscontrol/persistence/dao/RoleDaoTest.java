package th.co.baiwa.buckwaframework.accesscontrol.persistence.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class RoleDaoTest {
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<Role> roleList = roleDao.findAll();
		System.out.println(roleList);
		Assert.assertNotEquals(0, roleList.size());
	}
	
	@Test
	public void test_findById_Found() {
		System.out.println("- - - - - findById_Found - - - - -");
		Role role = roleDao.findById(1L);
		System.out.println(role);
		Assert.assertNotNull(role);
	}
	
	@Test
	public void test_findById_NotFound() {
		System.out.println("- - - - - findById_NotFound - - - - -");
		Role role = roleDao.findById(99L);
		System.out.println(role);
		Assert.assertNull(role);
	}
	
	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		int count = roleDao.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}
	
	//@Test
	public void test_insert() {
		System.out.println("- - - - - insert - - - - -");
		Role role = new Role();
		role.setRoleCode("unit role code");
		role.setRoleDesc("unit role desc");
		roleDao.insert(role);
	}
	
	//@Test
	public void test_update() {
		System.out.println("- - - - - update - - - - -");
		Role role = roleDao.findById(3L);
		role.setRoleDesc("edit");
		int updateRow = roleDao.update(role);
		Assert.assertEquals(1, updateRow);
	}
	
	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		int updateRow = roleDao.delete(3L);
		Assert.assertEquals(1, updateRow);
	}
	
}
