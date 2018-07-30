package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.mapper.MessageRowMapper;
import th.co.baiwa.excise.utils.OracleUtils;

public class MessageRepositoryImpl implements MessageRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryImpl.class);
	
	private final CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	public MessageRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}
	
	@Override
	public List<Message> findByCriteria(Message message, Integer start, Integer length) {
		logger.debug("findByCriteria message={}, start={}, length={}",
			ToStringBuilder.reflectionToString(message, ToStringStyle.JSON_STYLE),
			start, length);
		
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT MESSAGE_ID, MESSAGE_CODE, MESSAGE_EN, MESSAGE_TH, MESSAGE_TYPE ");
		builder.append(" FROM SYS_MESSAGE ");
		builder.append(" WHERE IS_DELETED = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(FLAG.N_FLAG);
		
		if (StringUtils.isNotBlank(message.getMessageCode())) {
			builder.append(" AND MESSAGE_CODE = ? ");
			params.add(message.getMessageCode());
		}
		if (StringUtils.isNotBlank(message.getMessageEn())) {
			builder.append(" AND MESSAGE_EN = ? ");
			params.add(message.getMessageEn());
		}
		if (StringUtils.isNotBlank(message.getMessageTh())) {
			builder.append(" AND MESSAGE_TH = ? ");
			params.add(message.getMessageTh());
		}
		if (StringUtils.isNotBlank(message.getMessageType())) {
			builder.append(" AND MESSAGE_TYPE = ? ");
			params.add(message.getMessageType());
		}
		builder.append(" ORDER BY MESSAGE_CODE ");
		
		return commonJdbcTemplate.executeQuery(
			OracleUtils.limitForDataTable(builder, start, length),
			params.toArray(),
			MessageRowMapper.getInstance()
		);
	}
	
}
