package th.co.baiwa.buckwaframework.preferences.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.SysAuditHistoryActionDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysAuditHistoryAction;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Service
public class SysAuditHistoryActionService {
	
	@Autowired
	private SysAuditHistoryActionDao sysAuditHistoryActionDao;
	
	
	public int insertSysAuditHistoryAction(SysAuditHistoryAction value) {
		value.setActionBy(UserLoginUtils.getCurrentUsername());
		value.setCreatedBy(UserLoginUtils.getCurrentUsername());
		value.setCreatedDate(new Date());
		return sysAuditHistoryActionDao.insertSysAuditHistoryAction(value);
	}
	
	
	
	
	
}
