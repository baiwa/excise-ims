package th.co.baiwa.buckwaframework.preferences.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysMessage;

public class MessageRowMapper implements RowMapper<SysMessage> {
	
	private static class SingletonHolder {
		private static final MessageRowMapper instance = new MessageRowMapper();
	}
	
	public static MessageRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public SysMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysMessage message = new SysMessage();
		message.setMessageId(rs.getLong("message_id"));
		message.setMessageCode(rs.getString("message_code"));
		message.setMessageEn(rs.getString("message_en"));
		message.setMessageTh(rs.getString("message_th"));
		message.setMessageType(rs.getString("message_type"));
		return message;
	}
	
}
