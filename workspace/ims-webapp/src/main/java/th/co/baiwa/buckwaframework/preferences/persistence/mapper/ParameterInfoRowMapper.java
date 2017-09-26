package th.co.baiwa.buckwaframework.preferences.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysParameterInfo;

public class ParameterInfoRowMapper implements RowMapper<SysParameterInfo> {
	
	private static class SingletonHolder {
		private static final ParameterInfoRowMapper instance = new ParameterInfoRowMapper();
	}
	
	public static ParameterInfoRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public SysParameterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysParameterInfo paramInfo = new SysParameterInfo();
		paramInfo.setParamInfoId(rs.getLong("param_info_id"));
		paramInfo.setParamGroupId(rs.getLong("param_group_id"));
		paramInfo.setParamCode(rs.getString("param_code"));
		paramInfo.setValue1(rs.getString("value_1"));
		paramInfo.setValue2(rs.getString("value_2"));
		paramInfo.setValue3(rs.getString("value_3"));
		paramInfo.setValue4(rs.getString("value_4"));
		paramInfo.setValue5(rs.getString("value_5"));
		paramInfo.setValue6(rs.getString("value_6"));
		paramInfo.setSortingOrder(rs.getInt("sorting_order"));
		paramInfo.setIsDefault(rs.getString("is_default"));
		return paramInfo;
	}
	
}
