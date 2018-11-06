package th.co.baiwa.excise.ia.persistence.dao;

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

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;

@Repository
public class ConfigRentHouseDao {

	private Logger logger = LoggerFactory.getLogger(ConfigRentHouseDao.class);

	private final String SQL = "SELECT t1.TYPE_DESCRIPTION AS LEVELS," 
			+ "  t2.LOV_ID," 
			+ "  t2.LOV_ID_MASTER,"
			+ "  t2.TYPE," 
			+ "  t2.TYPE_DESCRIPTION AS DESCRIPTION ," 
			+ "  t2.VALUE1" 
			+ " FROM sYS_LOV t1" 
			+ " JOIN"
			+ "  (SELECT LOV_ID,"
			+ "    LOV_ID_MASTER," 
			+ "    TYPE," 
			+ "    TYPE_DESCRIPTION," 
			+ "    VALUE1"
			+ "  FROM sYS_LOV" 
			+ "  WHERE TYPE ='WITHDRAW_TYPE'" 
			+ "  AND LOV_ID_MASTER = ? "
			+ "  OR LOV_ID_MASTER = ? " 
			+ "  )t2" 
			+ " ON t1.lov_Id = t2.lov_id_master";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Lov> findManagementType() {
		logger.info("findRentHouse");

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQL);
		valueList.add("1986");
		valueList.add("1987");
		List<Lov> findManagementType = jdbcTemplate.query(sql.toString(), valueList.toArray(), RentHouseMapping);
		return findManagementType;
	}

	private RowMapper<Lov> RentHouseMapping = new RowMapper<Lov>() {

		@Override
		public Lov mapRow(ResultSet rs, int arg1) throws SQLException {
			Lov vo = new Lov();
			vo.setLovId(rs.getLong("LOV_ID"));
			vo.setLovIdMaster(rs.getLong("LOV_ID_MASTER"));
			vo.setType(rs.getString("TYPE"));
			vo.setSubType(rs.getString("LEVELS"));
			vo.setTypeDescription(rs.getString("DESCRIPTION"));
			vo.setValue1(rs.getString("VALUE1"));
			return vo;
		}

	};

}
