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
import th.co.baiwa.excise.ta.persistence.vo.NotificationVo;

@Repository
public class NotificationDao {
	
	private Logger logger = LoggerFactory.getLogger(NotificationDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public int createNotification(Notification notification) {
		
		logger.info("createNotification");
		StringBuilder sql = new StringBuilder("INSERT INTO SYS_NOTIFICATION (ID, TYPE, SUBJECT, DETAIL_MESSAGE, STATUS, IS_DELETED, CREATED_BY, CREATED_DATE , REFERENCE)" ); 
		sql.append("VALUES (SYS_NOTIFICATION_SEQ.nextval, ?, ?, ?, ?, ?, ?, sysdate ,?) ");
		
		return jdbcTemplate.update(sql.toString(), new Object[] {notification.getType() , notification.getSubject() ,notification.getDetailMessage() , notification.getStatus() , FLAG.N_FLAG , UserLoginUtils.getCurrentUsername() ,notification.getReferenceId() });
	}
	
	public void updateNotification(Long id) {
		
		logger.info("updateNotification");
		StringBuilder sql = new StringBuilder(" UPDATE SYS_NOTIFICATION SET VIEW_DATE = SYSDATE WHERE ID = ? ");
		commonJdbcDao.executeUpdate(sql.toString(),new Object[]{id});
	}
	
	
	
	public List<Notification> findNotificationByType(String type) {
		
		logger.info("findExciseIdOrderByPercenTax");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT ID, TYPE, SUBJECT, DETAIL_MESSAGE, STATUS,VIEW_DATE, NVL2(VIEW_DATE , 'Y' , 'N') VIEW_STATUS, IS_DELETED, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, VERSION,REFERENCE FROM SYS_NOTIFICATION N WHERE N.IS_DELETED = 'N' ");
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
			Notification notification = new Notification();
			notification.setId(rs.getLong("ID"));
			notification.setType(rs.getString("TYPE"));
			notification.setSubject(rs.getString("SUBJECT"));
			notification.setDetailMessage(rs.getString("DETAIL_MESSAGE"));
			notification.setStatus(rs.getString("STATUS"));
			notification.setViewDate(rs.getDate("VIEW_DATE"));
			notification.setViewStatus(rs.getString("VIEW_STATUS"));
			notification.setIsDeleted(rs.getString("IS_DELETED"));
			notification.setVersion(rs.getInt("VERSION"));
			notification.setCreatedBy(rs.getString("CREATED_BY"));
			notification.setCreatedDate(rs.getDate("CREATED_DATE"));
			notification.setUpdatedBy(rs.getString("UPDATED_BY"));
			notification.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			notification.setReferenceId(rs.getString("REFERENCE"));
			return notification;
		}
	};
	
	public List<NotificationVo> countNotification() {
		
		logger.info("countNotification");
		StringBuilder sql = new StringBuilder(" SELECT TYPE  , (SELECT COUNT(1) " + 
				"                FROM SYS_NOTIFICATION C  " + 
				"                WHERE C.IS_DELETED = 'N'  " + 
				"                AND C.TYPE = M.TYPE  " + 
				"                AND C.VIEW_DATE IS NULL " + 
				"                ) COUNTTYPE   " + 
				"FROM SYS_NOTIFICATION M " + 
				"WHERE IS_DELETED = 'N' " + 
				"GROUP BY TYPE  " + 
				"ORDER BY TYPE ");
		return commonJdbcDao.executeQuery(sql.toString(), mappingCountNotification);
	}
	
	private RowMapper<NotificationVo> mappingCountNotification= new RowMapper<NotificationVo>() {
		@Override
		public NotificationVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			NotificationVo Notification = new NotificationVo();
			Notification.setType(rs.getString("TYPE"));
			Notification.setCountType(rs.getLong("countType"));
			
			return Notification;
		}
	};
	
	
	
	
}
