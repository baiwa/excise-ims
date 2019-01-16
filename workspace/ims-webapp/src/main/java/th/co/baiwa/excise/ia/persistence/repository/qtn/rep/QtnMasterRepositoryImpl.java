package th.co.baiwa.excise.ia.persistence.repository.qtn.rep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.excise.domain.QtnHdrConditionVo;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnMaster;
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

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
		sql.append("and 1 = 1 and l.IS_DELETED = ? ");
		param.add(FLAG.N_FLAG);
		if (!"00".equals(area)) {
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
	
	
	@Override
	public List<QtnHdrConditionVo> findRiskNameAndPoint(QtnMaster qtnMaster) {
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT MAS.QTN_MASTER_ID , MAS.QTN_NAME ,HDR.QTN_REPORT_HDR_ID , COUNT(1) RISK_POINT FROM IA_QTN_MASTER MAS  ");
		sql.append(" INNER JOIN IA_QTN_REPORT_HEADER HDR ");
		sql.append(" ON HDR.QTN_MASTER_ID = MAS.QTN_MASTER_ID ");
		sql.append(" INNER JOIN IA_QTN_FINAL_REP_HEADER FI_HRD ");
		sql.append(" ON FI_HRD.QTN_REPORT_HDR_ID = HDR.QTN_REPORT_HDR_ID ");
		sql.append(" INNER JOIN IA_QTN_FINAL_REP_MAIN MAIN ");
		sql.append(" ON MAIN.QTN_FINAL_REP_HDR_ID = FI_HRD.QTN_FINAL_REP_HDR_ID ");
		sql.append(" INNER JOIN IA_QTN_FINAL_REP_DETAIL DTL ");
		sql.append(" ON DTL.QTN_FINAL_REP_MAN_ID = MAIN.QTN_FINAL_REP_MAN_ID ");
		sql.append(" WHERE  MAS.IS_DELETED = 'N' ");
		sql.append(" AND  HDR.IS_DELETED = 'N' ");
		sql.append(" AND  FI_HRD.IS_DELETED = 'N' ");
		sql.append(" AND  MAIN.IS_DELETED = 'N' ");
		sql.append(" AND  DTL.IS_DELETED = 'N' ");
		sql.append(" AND DTL.QTN_POINT = 'N' ");
		sql.append(" AND MAS.QTN_YEAR = ? ");
		sql.append(" GROUP BY MAS.QTN_MASTER_ID , MAS.QTN_NAME ,HDR.QTN_REPORT_HDR_ID ");
		param.add(qtnMaster.getQtnYear());
		return jdbcTemplate.query(sql.toString(),param.toArray(), riskMapping);
	}
	
	private RowMapper<QtnHdrConditionVo> riskMapping = new RowMapper<QtnHdrConditionVo>() {

		@Override
		public QtnHdrConditionVo mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnHdrConditionVo int0803Vo = new QtnHdrConditionVo();
			int0803Vo.setRiskPoint(rs.getInt("RISK_POINT"));
			int0803Vo.setQtnName(rs.getString("QTN_NAME"));
			int0803Vo.setQtnMasterId(rs.getLong("QTN_MASTER_ID"));
			int0803Vo.setQtnHrdId(rs.getLong("QTN_REPORT_HDR_ID"));
			return int0803Vo;
		}
		
	};
}
