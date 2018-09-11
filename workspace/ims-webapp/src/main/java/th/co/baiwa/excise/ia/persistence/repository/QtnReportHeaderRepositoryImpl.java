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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.excise.ia.persistence.vo.QtnReportHeaderVo;
import th.co.baiwa.excise.utils.BeanUtils;

public class QtnReportHeaderRepositoryImpl implements QtnReportHeaderRepositoryCustom {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<QtnReportHeaderVo> findJoinFinal(Long masterId) {
		List<Object> param = new ArrayList<Object>();
		String SQL = " select rp.*, fn.QTN_FINAL_REP_HDR_ID HEADER_ID from IA_QTN_REPORT_HEADER rp left join IA_QTN_FINAL_REP_HEADER fn on rp.QTN_REPORT_HDR_ID = fn.QTN_REPORT_HDR_ID where 1=1 ";
		StringBuilder sql = new StringBuilder(SQL);
		sql.append(" and rp.IS_DELETED = '" + FLAG.N_FLAG + "' and fn.QTN_FINISHED = '" + FLAG.Y_FLAG + "' ");
		if (BeanUtils.isNotEmpty(masterId)) {
			sql.append(" and rp.QTN_MASTER_ID = ? ");
			logger.info("MasterID: {}", masterId);
			param.add(masterId);
		}
		sql.append(" order by rp.QTN_REPORT_HDR_ID ASC ");
		List<QtnReportHeaderVo> header = jdbcTemplate.query(sql.toString(),
				param.toArray(), _row);
		return header;
	}
	
	@Override
	public List<QtnReportHeaderVo> findJoinFinal(Long masterId, String user) {
		List<Object> param = new ArrayList<Object>();
		String SQL = " select rp.*, fn.QTN_CONCLUSION CONCLUSION, fn.QTN_FINAL_REP_HDR_ID HEADER_ID, fn.QTN_FINISHED FINISHED from IA_QTN_REPORT_HEADER rp left join IA_QTN_FINAL_REP_HEADER fn on rp.QTN_REPORT_HDR_ID = fn.QTN_REPORT_HDR_ID where 1=1 ";
		StringBuilder sql = new StringBuilder(SQL);
		sql.append(" and rp.IS_DELETED = '" + FLAG.N_FLAG + "' ");
		if (BeanUtils.isNotEmpty(masterId)) {
			sql.append(" and rp.QTN_MASTER_ID = ? ");
			logger.info("MasterID: {}", masterId);
			param.add(masterId);
		}
		if (BeanUtils.isNotEmpty(user)) {
			sql.append(" and fn.CREATED_BY = ? ");
			param.add(user);
		}
		sql.append(" order by rp.QTN_REPORT_HDR_ID ASC ");
		List<QtnReportHeaderVo> header = jdbcTemplate.query(sql.toString(),
				param.toArray(), row);
		return header;
	}
	
	private RowMapper<QtnReportHeaderVo> row = new RowMapper<QtnReportHeaderVo>() {

		@Override
		public QtnReportHeaderVo mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnReportHeaderVo header = new QtnReportHeaderVo();
			header.setQtnReportHdrId(rs.getLong("QTN_REPORT_HDR_ID"));
			header.setQtnReportHdrName(rs.getString("QTN_REPORT_HDR_NAME"));
			header.setQtnMasterId(rs.getLong("QTN_MASTER_ID"));
			header.setCreator(rs.getString("CREATOR"));
			header.setCreatedBy(rs.getString("CREATED_BY"));
			header.setUpdatedBy(rs.getString("UPDATED_BY"));
			header.setCreatedDate(rs.getDate("CREATED_DATE"));
			header.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			header.setConclusion(rs.getString("CONCLUSION"));
			header.setHeaderId(rs.getLong("HEADER_ID"));
			header.setQtnFinished(rs.getString("FINISHED"));
			return header;
		}
		
	};
	
	private RowMapper<QtnReportHeaderVo> _row = new RowMapper<QtnReportHeaderVo>() {

		@Override
		public QtnReportHeaderVo mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnReportHeaderVo header = new QtnReportHeaderVo();
			header.setQtnReportHdrId(rs.getLong("QTN_REPORT_HDR_ID"));
			header.setQtnReportHdrName(rs.getString("QTN_REPORT_HDR_NAME"));
			header.setQtnMasterId(rs.getLong("QTN_MASTER_ID"));
			header.setCreator(rs.getString("CREATOR"));
			header.setCreatedBy(rs.getString("CREATED_BY"));
			header.setUpdatedBy(rs.getString("UPDATED_BY"));
			header.setCreatedDate(rs.getDate("CREATED_DATE"));
			header.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			header.setHeaderId(rs.getLong("HEADER_ID"));
			return header;
		}
		
	};

}
