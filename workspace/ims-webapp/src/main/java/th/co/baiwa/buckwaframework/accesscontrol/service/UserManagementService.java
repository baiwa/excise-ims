package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.dao.UserManagementDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.UserManagement;

@Service
public class UserManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	@Autowired
	private UserManagementDao userManagementDao;
	
	public List<UserManagement> getUserManagementList(Integer start, Integer length, UserManagement userManagement) {
		logger.info("getUserManagementAll");
		
		return userManagementDao.findAll(start, length, userManagement);
	}
	
	public UserManagement getUserManagementByUserId(String userId) {
		logger.info("getUserManagement userId=?", userId);
		
		return userManagementDao.findByUserId(userId);
	}
	
	public UserManagement getUserManagementByUsername(String username) {
		logger.info("getUserManagement username=?", username);
		
		return userManagementDao.findByUsername(username);
	}
	
	public int countUserManagement() {
		logger.info("countUserManagement");
		
		return userManagementDao.count();
	}
	
	public UserManagement insertUserManagement(UserManagement userManagement) {
		logger.info("insertUserManagement");
		
		Long userId = userManagementDao.insert(userManagement);
		userManagement.setUserId(userId);
		
		return userManagement;
	}
	
	public void updateUserManagement(UserManagement userManagement) {
		logger.info("updateUserManagement");
		
		userManagementDao.update(userManagement);
	}
	
	public void deleteUserManagement(List<Long> userId) {
		logger.info("deleteUserManagement");
		for (Long user : userId) {
			userManagementDao.delete(user);
		}
	}
	
}
