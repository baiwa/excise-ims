package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.ExciseAuthen;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.ExciseAuthenRepository;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.UserRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.UserManagement;
import th.co.baiwa.buckwaframework.preferences.service.UserManagementService;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Service
public class ExciseAuthenService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseAuthenService.class);
	
	@Autowired
	private ExciseAuthenRepository exciseAuthenRepository;
	
	@Autowired
	private UserManagementService userManagementService;

	
	public String[] getAuthenPage() {
		logger.info("getAuthenPage");
		UserManagement user = userManagementService.getUserManagementByUsername(UserLoginUtils.getCurrentUsername());
		List<String> pageList = new ArrayList<String>();
		List<ExciseAuthen> exciseAuthenList = exciseAuthenRepository.findByGroupAuthenName(user.getExciseBaseControl());
		for (ExciseAuthen exciseAuthen : exciseAuthenList) {
			pageList.add(exciseAuthen.getPage());
		}
		String page[] = {};
		return pageList.toArray(page);
	}
}
