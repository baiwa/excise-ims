package th.co.baiwa.excise.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;
import th.co.baiwa.excise.utils.OracleUtils;

public class QtnMasterRepositoryImpl implements QtnMasterRepositoryCustom {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<QtnMaster> findData(String sector, String area, String year, String finished) {
		logger.info("Sector: {}, Area: {}, Year: {}, Finished: {}", sector, area, year, finished);
		List<Object> param = new ArrayList<Object>();
		String SQL = " select M.* from sys_lov l inner join sys_lov v on l.lov_id = v.lov_id_master inner join IA_QTN_MASTER m on m.QTN_SECTOR = v.LOV_ID where l.type = 'SECTOR_VALUE' and v.type = 'SECTOR_VALUE' ";
		StringBuilder sql = new StringBuilder(SQL);
		if (area.equals("00")) {
			sql.append("and v.SUB_TYPE = ? ");
			param.add(area);
		} else {
			sql.append("and l.lov_id_master is null and l.SUB_TYPE = ? ");
			param.add(sector);
		}
		sql.append(" and M.qtn_year = ? and M.qtn_finished = ? ");
		param.add(year);
		param.add(finished);
		
		List<QtnMaster> master = jdbcTemplate.query(OracleUtils.limitForDataTable(sql, 0, 1),
				param.toArray(), row);
		return master;
	}
	
	private RowMapper<QtnMaster> row = new RowMapper<QtnMaster>() {

		@Override
		public QtnMaster mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnMaster master = new QtnMaster();
			master.setQtnMasterId(rs.getLong("QTN_MASTER_ID"));
			master.setQtnName(rs.getString("QTN_NAME"));
			master.setQtnYear(rs.getString("QTN_YEAR"));
			master.setQtnSector(rs.getString("QTN_SECTOR"));
			master.setQtnFinished(rs.getString("QTN_FINISHED"));
			return master;
		}
		
	};
}
