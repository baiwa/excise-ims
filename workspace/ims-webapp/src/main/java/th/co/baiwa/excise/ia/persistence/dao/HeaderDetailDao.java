package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.LabelValueBean;

@Repository
public class HeaderDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	    
		public List<LabelValueBean> drodownList(String type,String lovIdMaster){
			String SQL = "SELECT * FROM SYS_LOV WHERE TYPE=? AND LOV_ID_MASTER= ?";
			return jdbcTemplate.query(SQL,new Object[] {type,lovIdMaster} ,ropdownListRowmapper);
		}
		
		private RowMapper<LabelValueBean> ropdownListRowmapper = new RowMapper<LabelValueBean>() {
		    	@Override
		    	public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
		    		LabelValueBean  lv = new LabelValueBean(rs.getString("TYPE_DESCRIPTION"),rs.getString("LOV_ID"));
		    		return lv;
		    	}

		};
}
