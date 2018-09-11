package th.co.baiwa.excise.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.excise.ia.persistence.vo.QtnReportMainVo;
import th.co.baiwa.excise.utils.BeanUtils;

public class QtnReportMainRepositoryImpl implements QtnReportMainRepositoryCustom {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<QtnReportMainVo> findJoinFinal(Long qtnReportHdrId) {
		List<Object> param = new ArrayList<Object>();
		String SQL = " select rp.*, fn.QTN_POINT POINT, fn.QTN_FINAL_REP_MAN_ID MAIN_ID from IA_QTN_REPORT_MAIN rp left join IA_QTN_FINAL_REP_MAIN fn on rp.QTN_REPORT_MAN_ID = fn.QTN_REPORT_MAN_ID where 1=1 ";
		StringBuilder sql = new StringBuilder(SQL);
		sql.append(" and rp.IS_DELETED = '" + FLAG.N_FLAG + "' ");
		if (BeanUtils.isNotEmpty(qtnReportHdrId)) {
			sql.append(" and rp.QTN_REPORT_HDR_ID = ? ");
			param.add(qtnReportHdrId);
		}
		sql.append(" order by rp.QTN_REPORT_MAN_ID ASC ");
		List<QtnReportMainVo> main = jdbcTemplate.query(sql.toString(),
				param.toArray(), row);
		return main;
	}
	
	@Override
	public List<QtnReportMainVo> findJoinFinal(Long qtnReportHdrId, String qtnCreator) {
		List<Object> param = new ArrayList<Object>();
		String SQL = " select rp.*, fn.QTN_POINT POINT, fn.QTN_FINAL_REP_MAN_ID MAIN_ID from IA_QTN_REPORT_MAIN rp left join IA_QTN_FINAL_REP_MAIN fn on rp.QTN_REPORT_MAN_ID = fn.QTN_REPORT_MAN_ID where 1=1 ";
		StringBuilder sql = new StringBuilder(SQL);
		sql.append(" and rp.IS_DELETED = '" + FLAG.N_FLAG + "' ");
		if (BeanUtils.isNotEmpty(qtnReportHdrId)) {
			sql.append(" and rp.QTN_REPORT_HDR_ID = ? ");
			param.add(qtnReportHdrId);
		}
		if (BeanUtils.isNotEmpty(qtnCreator)) {
			sql.append(" and fn.CREATED_BY = ? ");
			param.add(qtnCreator);
		}
		sql.append(" order by rp.QTN_REPORT_MAN_ID ASC ");
		List<QtnReportMainVo> main = jdbcTemplate.query(sql.toString(),
				param.toArray(), row);
		return main;
	}
	
	private RowMapper<QtnReportMainVo> row = new RowMapper<QtnReportMainVo>() {

		@Override
		public QtnReportMainVo mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnReportMainVo main = new QtnReportMainVo();
			main.setQtnReportHdrId(rs.getLong("QTN_REPORT_HDR_ID"));
			main.setQtnReportManId(rs.getLong("QTN_REPORT_MAN_ID"));
			main.setQtnMainDetail(rs.getString("QTN_MAIN_DETAIL"));
			main.setCreatedBy(rs.getString("CREATED_BY"));
			main.setUpdatedBy(rs.getString("UPDATED_BY"));
			main.setCreatedDate(rs.getDate("CREATED_DATE"));
			main.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			main.setPoint(rs.getString("POINT"));
			main.setMainId(rs.getLong("MAIN_ID"));
			return main;
		}
		
	};

}
