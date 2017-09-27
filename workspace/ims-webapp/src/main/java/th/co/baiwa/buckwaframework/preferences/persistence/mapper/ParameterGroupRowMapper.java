package th.co.baiwa.buckwaframework.preferences.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup;

public class ParameterGroupRowMapper implements RowMapper<ParameterGroup> {
	
	private static class SingletonHolder {
		private static final ParameterGroupRowMapper instance = new ParameterGroupRowMapper();
	}
	
	public static ParameterGroupRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public ParameterGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		ParameterGroup paramGroup = new ParameterGroup();
		paramGroup.setParamGroupId(rs.getLong("param_group_id"));
		paramGroup.setParamGroupCode(rs.getString("param_group_code"));
		paramGroup.setParamGroupDesc(rs.getString("param_group_desc"));
		return paramGroup;
	}
	
}
