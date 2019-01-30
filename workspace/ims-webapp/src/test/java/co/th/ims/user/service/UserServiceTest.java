package co.th.ims.user.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.th.baiwa.buckwaframework.usermanagement.user.dao.UserDao;
import co.th.baiwa.buckwaframework.usermanagement.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void findAll() {
		List<User> users = new ArrayList<>();
		users = userDao.findAll();
		User user = new User();
		for(int i=0; i<users.toArray().length; i++) {
			user = users.get(i);
			System.out.println("USER: " + user.getUsername() + " PASS: " + user.getPassword());
		}
	}
}
