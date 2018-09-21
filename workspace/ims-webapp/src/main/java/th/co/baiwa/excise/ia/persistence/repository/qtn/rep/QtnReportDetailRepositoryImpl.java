package th.co.baiwa.excise.ia.persistence.repository.qtn.rep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.excise.ia.persistence.vo.QtnReportDetailVo;
import th.co.baiwa.excise.utils.BeanUtils;

public class QtnReportDetailRepositoryImpl implements QtnReportDetailRepositoryCustom {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<QtnReportDetailVo> findJoinFinal(Long qtnReportManId, String qtnCreator) {
		List<Object> param = new ArrayList<Object>();
		String SQL = " select rp.*, fn.QTN_POINT POINT, fn.QTN_FINAL_REP_DTL_ID DETAIL_ID from IA_QTN_REPORT_DETAIL rp left join IA_QTN_FINAL_REP_DETAIL fn on rp.QTN_REPORT_DTL_ID = fn.QTN_REPORT_DTL_ID where 1=1 ";
		StringBuilder sql = new StringBuilder(SQL);
		sql.append(" and rp.IS_DELETED = '" + FLAG.N_FLAG + "' ");
		if (BeanUtils.isNotEmpty(qtnReportManId)) {
			sql.append(" and rp.QTN_REPORT_MAN_ID = ? ");
			param.add(qtnReportManId);
		}
		if (BeanUtils.isNotEmpty(qtnCreator)) {
			sql.append(" and fn.CREATED_BY = ? ");
			param.add(qtnCreator);
		}
		sql.append(" order by rp.QTN_REPORT_DTL_ID ASC ");
		List<QtnReportDetailVo> detail = jdbcTemplate.query(sql.toString(),
				param.toArray(), row);
		return detail;
	}
	
	private RowMapper<QtnReportDetailVo> row = new RowMapper<QtnReportDetailVo>() {

		@Override
		public QtnReportDetailVo mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnReportDetailVo main = new QtnReportDetailVo();
			main.setQtnReportDtlId(rs.getLong("QTN_REPORT_DTL_ID"));
			main.setQtnReportManId(rs.getLong("QTN_REPORT_MAN_ID"));
			main.setQtnMainDetail(rs.getString("QTN_MAIN_DETAIL"));
			main.setCreatedBy(rs.getString("CREATED_BY"));
			main.setUpdatedBy(rs.getString("UPDATED_BY"));
			main.setCreatedDate(rs.getDate("CREATED_DATE"));
			main.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			main.setPoint(rs.getString("POINT"));
			main.setDetailId(rs.getLong("DETAIL_ID"));
			return main;
		}
		
	};

}
