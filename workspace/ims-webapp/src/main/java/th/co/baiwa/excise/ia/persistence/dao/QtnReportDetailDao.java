package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportDetail;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

@Repository
public class QtnReportDetailDao {
	
	private static String _SQL = " SELECT D.* FROM IA_QTN_REPORT_DETAIL D WHERE 1=1 ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<QtnReportDetail> findQtnReportDetail(QtnReportDetail qtn, int start, int length) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(_SQL);
		sql.append(" AND D.IS_DELETED = 'N' ");

		if (BeanUtils.isNotEmpty(qtn.getQtnReportManId())) {
			sql.append("AND D.QTN_REPORT_MAN_ID = ? ");
			paramList.add(qtn.getQtnReportManId());
		}

		List<QtnReportDetail> qtnReportDetail = jdbcTemplate.query(OracleUtils.limitForDataTable(sql, start, length),
				paramList.toArray(), rowMapper);

		return qtnReportDetail;
	}

	public long countQtnReportDetail(QtnReportDetail qtn) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(_SQL);
		sql.append(" AND D.IS_DELETED = 'N' ");
		if (BeanUtils.isNotEmpty(qtn.getQtnReportManId())) {
			sql.append("AND D.QTN_REPORT_MAN_ID = ? ");
			paramList.add(qtn.getQtnReportManId());
		}
		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), paramList.toArray(),
				Long.class);
		return count;
	}

	private RowMapper<QtnReportDetail> rowMapper = new RowMapper<QtnReportDetail>() {

		@Override
		public QtnReportDetail mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnReportDetail vo = new QtnReportDetail();
			vo.setQtnReportDtlId(rs.getLong("QTN_REPORT_DTL_ID"));
			vo.setQtnReportManId(rs.getLong("QTN_REPORT_MAN_ID"));
			vo.setQtnMainDetail(rs.getString("QTN_MAIN_DETAIL"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setVersion(rs.getInt("VERSION"));
			return vo;
		}

	};

}
