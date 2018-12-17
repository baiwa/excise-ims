package th.co.baiwa.excise.sys.dao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.sys.domain.Notification;

@Repository
public class NotificationDao {
	
	private Logger logger = LoggerFactory.getLogger(NotificationDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public long createNotification(Notification notification) {
		logger.info("createNotification");
		StringBuilder sql = new StringBuilder("INSERT INTO SYS_NOTIFICATION (ID, TYPE, SUBJECT, DETAIL_MESSAGE, STATUS, IS_DELETED, CREATED_BY, CREATED_DATE, VERSION)" ); 
		sql.append("VALUES (SYS_NOTIFICATION_SEQ.nectval, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		
		return jdbcTemplate.update(sql.toString(), new Object[] {notification.getType() , notification.getSubject() ,notification.getDetailMessage() , notification.getStatus() , FLAG.N_FLAG , UserLoginUtils.getCurrentUsername() , new Date(), 1}, Long.class );
	}
}
