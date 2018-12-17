package th.co.baiwa.excise.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.sys.dao.NotificationDao;
import th.co.baiwa.excise.sys.domain.Notification;
import th.co.baiwa.excise.ta.persistence.vo.NotificationVo;

@Service
public class NotificationService {
	
	private Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	public static final String CREATE_PLAN = "CREATE_PLAN";
	
	@Autowired
	private NotificationDao notificationDao;
	
	/**
	 * 
	 * notification Type 
	 * CREATE_PLAN :  create plan for select excise
	 * 
	 * 
	 */
	public Message createNotificationService(Notification notification) {
		logger.info("createNotificationService : {}" , notification.getType());
		Message msg = null;
		try {
			int count = notificationDao.createNotification(notification);
			if (count == 0) {
				msg = ApplicationCache.getMessage("MSG_00003");
			} else {
				msg = ApplicationCache.getMessage("MSG_00002");
			}
		} catch (Exception e) {
			msg = ApplicationCache.getMessage("MSG_00003");
			msg.setMessageEn(e.getMessage());
			logger.error("createNotificationService : {}" , e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	public List<Notification> findNotificationByType(Notification notification){
		logger.info("findAllNotification");
		return notificationDao.findNotificationByType(notification.getType());
	}
	
	public void updateNotification(Long id){
		logger.info("updateNotification");
		notificationDao.updateNotification(id);
	}
	
	public List<NotificationVo> countNotification(){
		logger.info("countNotification");
		List<NotificationVo> notificationVoList = new ArrayList<NotificationVo>();
		
			 notificationVoList = notificationDao.countNotification();
			 
			 return notificationVoList;
	}
	
	
}
