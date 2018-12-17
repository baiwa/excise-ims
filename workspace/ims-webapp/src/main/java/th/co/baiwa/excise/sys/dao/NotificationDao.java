package th.co.baiwa.excise.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.sys.domain.Notification;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class NotificationDao {
	
	private Logger logger = LoggerFactory.getLogger(NotificationDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public int createNotification(Notification notification) {
		
		logger.info("createNotification");
		StringBuilder sql = new StringBuilder("INSERT INTO SYS_NOTIFICATION (ID, TYPE, SUBJECT, DETAIL_MESSAGE, STATUS, IS_DELETED, CREATED_BY, CREATED_DATE)" ); 
		sql.append("VALUES (SYS_NOTIFICATION_SEQ.nextval, ?, ?, ?, ?, ?, ?, sysdate) ");
		
		return jdbcTemplate.update(sql.toString(), new Object[] {notification.getType() , notification.getSubject() ,notification.getDetailMessage() , notification.getStatus() , FLAG.N_FLAG , UserLoginUtils.getCurrentUsername() });
	}
	
	
	public List<Notification> findNotificationByType(String type) {
		
		logger.info("findExciseIdOrderByPercenTax");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT ID, TYPE, SUBJECT, DETAIL_MESSAGE, STATUS,VIEW_DATE, NVL2(VIEW_DATE , 'N' , 'Y') VIEW_STATUS, IS_DELETED, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, VERSION FROM SYS_NOTIFICATION N WHERE N.IS_DELETED = 'N' ");
		if(BeanUtils.isNotEmpty(type)) {
			sql.append(" AND TYPE = ? ");
			params.add(type);
		}
		sql.append(" ORDER BY ID DESC ");
		return commonJdbcDao.executeQuery(sql.toString(), params.toArray(), mappingSelectStarNotification);
	}
	
	
	private RowMapper<Notification> mappingSelectStarNotification = new RowMapper<Notification>() {
		@Override
		public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
			Notification Notification = new Notification();
			Notification.setId(rs.getLong("ID"));
			Notification.setType(rs.getString("TYPE"));
			Notification.setSubject(rs.getString("SUBJECT"));
			Notification.setDetailMessage(rs.getString("DETAIL_MESSAGE"));
			Notification.setStatus(rs.getString("STATUS"));
			Notification.setViewDate(rs.getDate("VIEW_DATE"));
			Notification.setViewStatus(rs.getString("VIEW_STATUS"));
			Notification.setIsDeleted(rs.getString("IS_DELETED"));
			Notification.setVersion(rs.getInt("VERSION"));
			Notification.setCreatedBy(rs.getString("CREATED_BY"));
			Notification.setCreatedDate(rs.getDate("CREATED_DATE"));
			Notification.setUpdatedBy(rs.getString("UPDATED_BY"));
			Notification.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return Notification;
		}
	};
	
	
	
	
}
