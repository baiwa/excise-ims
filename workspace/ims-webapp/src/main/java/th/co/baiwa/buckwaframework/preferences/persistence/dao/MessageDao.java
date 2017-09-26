package th.co.baiwa.buckwaframework.preferences.persistence.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.mapper.MessageRowMapper;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Repository("messageDao")
public class MessageDao {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageDao.class);
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public List<Message> findAll() {
		logger.debug("findAll");
		
		String sql =
			" SELECT message_id, message_code, message_en, message_th, message_type" +
			" FROM sys_message " +
			" WHERE is_deleted = ? ";
		
		return commonJdbcDao.executeQuery(sql,
			new Object[] {
				FLAG.N_FLAG
			},
			MessageRowMapper.getInstance()
		);
	}
	
	public Message findById(Long messageId) {
		logger.debug("findById");
		
		String sql =
			" SELECT message_id, message_code, message_en, message_th, message_type" +
			" FROM sys_message " +
			" WHERE is_deleted = ? " +
			"   AND message_id = ? ";
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				messageId
			},
			MessageRowMapper.getInstance()
		);
	}
	
	public int count() {
		logger.info("count");
		
		String sql = SqlGeneratorUtils.genSqlCount("sys_message", Arrays.asList("is_deleted"));
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG
			},
			Integer.class
		);
	}
	
	public Long insert(Message message) {
		logger.debug("insert");
		
		String sql = SqlGeneratorUtils.genSqlInsert("sys_message", Arrays.asList(
			"message_code",
			"message_en",
			"message_th",
			"message_type",
			"created_by",
			"created_date"
		));
		
		Long key = commonJdbcDao.executeInsertWithKeyHolder(sql.toString(), new Object[] {
			message.getMessageCode(),
			message.getMessageEn(),
			message.getMessageTh(),
			message.getMessageType(),
			UserLoginUtils.getCurrentUsername(),
			new Date()
		});
		
		return key;
	}
	
	public int update(Message message) {
		logger.debug("update");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("sys_message",
			Arrays.asList(
				"message_code",
				"message_en",
				"message_th",
				"message_type",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"message_id"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			message.getMessageCode(),
			message.getMessageEn(),
			message.getMessageTh(),
			message.getMessageType(),
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			message.getMessageId()
		});
		
		return updateRow;
	}
	
	public int delete(Long messageId) {
		logger.info("delete");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("sys_message",
			Arrays.asList(
				"is_deleted",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"message_id"
			)
		);
		
		int deleteRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			FLAG.Y_FLAG,
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			messageId
		});
		
		return deleteRow;
	}

}
