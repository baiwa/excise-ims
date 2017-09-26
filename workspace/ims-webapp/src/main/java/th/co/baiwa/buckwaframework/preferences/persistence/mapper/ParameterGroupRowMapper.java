package th.co.baiwa.buckwaframework.preferences.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysParameterGroup;

public class ParameterGroupRowMapper implements RowMapper<SysParameterGroup> {
	
	private static class SingletonHolder {
		private static final ParameterGroupRowMapper instance = new ParameterGroupRowMapper();
	}
	
	public static ParameterGroupRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public SysParameterGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysParameterGroup paramGroup = new SysParameterGroup();
		paramGroup.setParamGroupId(rs.getLong("param_group_id"));
		paramGroup.setParamGroupCode(rs.getString("param_group_code"));
		paramGroup.setParamGroupDesc(rs.getString("param_group_desc"));
		return paramGroup;
	}
	
}
