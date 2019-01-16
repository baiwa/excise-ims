package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnMaster;
import th.co.baiwa.excise.ia.persistence.vo.Int021Vo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

@Repository
public class QtnReportMasterDao {

	private static String SELECT = " SELECT M.* FROM IA_QTN_MASTER M WHERE 1=1 ";

	@Autowired
	private JdbcTemplate jdbcTemp;

	public Long count(Int021Vo vo) {
		StringBuilder query = new StringBuilder(SELECT);
		List<Object> params = new ArrayList<>();
		query.append(" AND M.IS_DELETED = ? ");
		params.add(FLAG.N_FLAG);

		if (BeanUtils.isNotEmpty(vo.getQtnMasterId())) {
			query.append(" AND M.QTN_MASTER_ID = ? ");
			params.add(vo.getQtnMasterId());
		}

		if (BeanUtils.isNotEmpty(vo.getQtnSector())) {
			query.append(" AND M.QTN_SECTOR = ? ");
			params.add(vo.getQtnSector());
		}

		if (BeanUtils.isNotEmpty(vo.getQtnYear())) {
			query.append(" AND M.QTN_YEAR = ? ");
			params.add(vo.getQtnYear());
		}

		if (BeanUtils.isNotEmpty(vo.getQtnFrom()) && BeanUtils.isNotEmpty(vo.getQtnTo())) {
			Date dateFrom = DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getQtnFrom().concat(" 00:00"));
			Date dateTo = DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getQtnTo().concat(" 23:59"));
			query.append(" AND M.CREATED_DATE BETWEEN ? AND ? ");
			params.add(dateFrom);
			params.add(dateTo);
		}

		Long result = jdbcTemp.queryForObject(OracleUtils.countForDatatable(query.toString()), params.toArray(),
				Long.class);

		return result;
	}

	public List<QtnMaster> findByCreteria(Int021Vo vo) {
		StringBuilder query = new StringBuilder(SELECT);
		List<Object> params = new ArrayList<>();
		query.append(" AND M.IS_DELETED = ? ");
		params.add(FLAG.N_FLAG);

		if (BeanUtils.isNotEmpty(vo.getQtnMasterId())) {
			query.append(" AND M.QTN_MASTER_ID = ? ");
			params.add(vo.getQtnMasterId());
		}

		if (BeanUtils.isNotEmpty(vo.getQtnSector())) {
			query.append(" AND M.QTN_SECTOR = ? ");
			params.add(vo.getQtnSector());
		}

		if (BeanUtils.isNotEmpty(vo.getQtnYear())) {
			query.append(" AND M.QTN_YEAR = ? ");
			params.add(vo.getQtnYear());
		}

		if (BeanUtils.isNotEmpty(vo.getQtnFrom()) && BeanUtils.isNotEmpty(vo.getQtnTo())) {
			Date dateFrom = DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getQtnFrom().concat(" 00:00"));
			Date dateTo = DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getQtnTo().concat(" 23:59"));
			query.append(" AND M.CREATED_DATE BETWEEN ? AND ? ");
			params.add(dateFrom);
			params.add(dateTo);
		}

		List<QtnMaster> result = jdbcTemp.query(
				OracleUtils.limitForDataTable(query.toString(), vo.getStart(), vo.getLength()), params.toArray(),
				rowMapper);

		return result;
	}

	private RowMapper<QtnMaster> rowMapper = new RowMapper<QtnMaster>() {

		@Override
		public QtnMaster mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnMaster vo = new QtnMaster();
			vo.setQtnMasterId(rs.getLong("QTN_MASTER_ID"));
			vo.setQtnName(rs.getString("QTN_NAME"));
			vo.setQtnSector(rs.getString("QTN_SECTOR"));
			vo.setQtnYear(rs.getString("QTN_YEAR"));
			vo.setQtnFinished(rs.getString("QTN_FINISHED"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setVersion(rs.getInt("VERSION"));
			vo.setQtnStart(rs.getTimestamp("QTN_START"));
			vo.setQtnEnd(rs.getTimestamp("QTN_END"));
			return vo;
		}

	};

}
