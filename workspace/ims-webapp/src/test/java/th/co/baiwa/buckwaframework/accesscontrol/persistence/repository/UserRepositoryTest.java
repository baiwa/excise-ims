package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

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

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<User> userList = userRepository.findAll();
		System.out.println(userList);
		Assert.assertNotEquals(0, userList.size());
	}

	@Test
	public void test_findOne_Found() {
		System.out.println("- - - - - findOne_Found - - - - -");
		User user = userRepository.findOne(1L);
		System.out.println(user);
		Assert.assertNotNull(user);
	}

	@Test
	public void test_findOne_NotFound() {
		System.out.println("- - - - - findOne_NotFound - - - - -");
		User user = userRepository.findOne(99L);
		System.out.println(user);
		Assert.assertNull(user);
	}

	@Test
	public void test_findByUsername_Found() {
		System.out.println("- - - - - findByUsername_Found - - - - -");
		User user = userRepository.findByUsername("admin");
		System.out.println(user);
		Assert.assertNotNull(user);
	}

	@Test
	public void test_findByUsername_NotFound() {
		System.out.println("- - - - - findByUsername_NotFound - - - - -");
		User user = userRepository.findByUsername("notExist");
		System.out.println(user);
		Assert.assertNull(user);
	}

	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		long count = userRepository.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}

	//@Test
	public void test_save_insert() {
		System.out.println("- - - - - save_insert - - - - -");
		User user = new User();
		user.setUsername("mock");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		user.setEnabled(FLAG.Y_FLAG);
		user.setAccountNonExpired(FLAG.Y_FLAG);
		user.setCredentialsNonExpired(FLAG.Y_FLAG);
		user.setAccountNonLocked(FLAG.Y_FLAG);
		userRepository.save(user);
	}

	//@Test
	public void test_save_update() {
		System.out.println("- - - - - save_update - - - - -");
		User user = userRepository.findOne(82L);
		user.setPassword(new BCryptPasswordEncoder().encode("password2"));
		user.setEnabled(FLAG.N_FLAG);
		userRepository.save(user);
	}

	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		userRepository.delete(82L);
	}

}
