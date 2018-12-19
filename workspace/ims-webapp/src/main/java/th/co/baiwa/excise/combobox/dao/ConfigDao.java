package th.co.baiwa.excise.combobox.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.domain.LabelValueBean;

@Repository
public class ConfigDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Lov getConfigCreteria() {
		String SQL = "SELECT * FROM SYS_LOV WHERE TYPE = 'CONFIG_CRITERIA'";
		return jdbcTemplate.queryForObject(SQL, rowMapper);
	}
	
	private RowMapper<Lov> rowMapper = new RowMapper<Lov>() {
    	@Override
    	public Lov mapRow(ResultSet rs, int arg1) throws SQLException {
    		Lov  lov = new Lov();
    		lov.setValue1(rs.getString("VALUE1"));
    		lov.setValue2(rs.getString("VALUE2"));
    		lov.setValue3(rs.getString("VALUE3"));
    		lov.setValue4(rs.getString("VALUE4"));
    		lov.setValue5(rs.getString("VALUE5"));
    		return lov;
    	}

};
	
}
