package co.th.ims.user.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.baiwa.buckwaframework.security.util.UserLoginUtils;
import co.th.ims.user.dao.UserDao;
import co.th.ims.user.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	public User findOne(String id) {
		BigDecimal idBigDecimal = new BigDecimal(id);
		return userDao.findOne(idBigDecimal);
	}
	
	public void save(User user) throws Exception {
		// Who is creating ?
		user.setCreatedBy(UserLoginUtils.getCurrentUsername());
		// Saved already ?
		if (userDao.save(user) != 1) {
			throw new Exception();
		}
	}
	
	public void update(User user, String id) throws Exception {
		BigDecimal idBigDecimal = new BigDecimal(id);
		// Who is creating ?
		user.setUpdatedBy(UserLoginUtils.getCurrentUsername());
		// Updated already ?
		if (userDao.update(user, idBigDecimal) != 1) {
			throw new Exception();
		}
	}
	
	public void delete(String id) throws Exception {
		BigDecimal idBigDecimal = new BigDecimal(id);
		// Who is creating ?
		String updatedBy = UserLoginUtils.getCurrentUsername();
		// Updated already ?
		if (userDao.delete(updatedBy, idBigDecimal) != 1) {
			throw new Exception();
		}
	}
}
