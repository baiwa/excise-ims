package th.co.baiwa.buckwaframework.admin.persistence.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.admin.persistence.entity.User;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<User> userList = userDao.findAll();
		System.out.println(userList);
		Assert.assertNotEquals(0, userList.size());
	}
	
	@Test
	public void test_findById_Found() {
		System.out.println("- - - - - findById_Found - - - - -");
		User user = userDao.findById(1L);
		System.out.println(user);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void test_findById_NotFound() {
		System.out.println("- - - - - findById_NotFound - - - - -");
		User user = userDao.findById(99L);
		System.out.println(user);
		Assert.assertNull(user);
	}
	
	@Test
	public void test_findByUsername_Found() {
		System.out.println("- - - - - findByUsername_Found - - - - -");
		User user = userDao.findByUsername("admin");
		System.out.println(user);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void test_findByUsername_NotFound() {
		System.out.println("- - - - - findByUsername_NotFound - - - - -");
		User user = userDao.findByUsername("notExist");
		System.out.println(user);
		Assert.assertNull(user);
	}
	
	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		int count = userDao.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}
	
	//@Test
	public void test_insert() {
		System.out.println("- - - - - insert - - - - -");
		User user = new User();
		user.setUsername("mock");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		user.setEnabled(FLAG.Y_FLAG);
		user.setAccountNonExpired(FLAG.Y_FLAG);
		user.setCredentialsNonExpired(FLAG.Y_FLAG);
		user.setAccountNonLocked(FLAG.Y_FLAG);
		userDao.insert(user);
	}
	
	//@Test
	public void test_update() {
		System.out.println("- - - - - update - - - - -");
		User user = userDao.findById(2L);
		user.setPassword(new BCryptPasswordEncoder().encode("password2"));
		int updateRow = userDao.update(user);
		Assert.assertEquals(1, updateRow);
	}
	
	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		int updateRow = userDao.delete(2L);
		Assert.assertEquals(1, updateRow);
	}
	
}
