package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportHeader;
import th.co.baiwa.excise.ia.persistence.vo.Int022Vo;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class QtnReportHeaderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sqlTemplate = " SELECT * FROM IA_QTN_REPORT_HEADER H WHERE 1 = 1 ";
	
	public List<Int022Vo> findByCriteria(QtnReportHeader qtnReportHeader) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnReportHdrId())) {
			sql.append("AND H.QTN_REPORT_HDR_ID = ? ");
			paramList.add(qtnReportHeader.getQtnReportHdrId());
		}
		
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnReportHdrName())) {
			sql.append("AND H.QTN_REPORT_HDR_NAME = ? ");
			paramList.add(qtnReportHeader.getQtnReportHdrName());
		}
		
		List<Int022Vo> qtnReportHeaderList = jdbcTemplate.query(sql.toString(), paramList.toArray(),rowMapper );
		
		return qtnReportHeaderList;
	}
	
	public List<Int022Vo> findByCriteriaDataTable(QtnReportHeader qtnReportHeader,int start, int length) {
		List<Object> paramList = new ArrayList<Object>();
		String str = " SELECT DISTINCT H.*, DECODE(M.QTN_REPORT_HDR_ID, null, 'FALSE', 'TRUE') DETAIL FROM IA_QTN_REPORT_HEADER H ";
		StringBuilder sql = new StringBuilder(str);
		sql.append("LEFT JOIN IA_QTN_REPORT_MAIN M ON M.QTN_REPORT_HDR_ID = H.QTN_REPORT_HDR_ID AND M.IS_DELETED = 'N' WHERE 1=1 ");
		sql.append("AND H.IS_DELETED = 'N' ");
		
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnReportHdrId())) {
			sql.append("AND H.QTN_REPORT_HDR_ID = ? ");
			paramList.add(qtnReportHeader.getQtnReportHdrId());
		}
		
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnReportHdrName())) {
			sql.append("AND H.QTN_REPORT_HDR_NAME = ? ");
			paramList.add(qtnReportHeader.getQtnReportHdrName());
		}
		
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnMasterId())) {
			sql.append("AND H.QTN_MASTER_ID = ? ");
			paramList.add(qtnReportHeader.getQtnMasterId());
		}
		
		List<Int022Vo> qtnReportHeaderList = jdbcTemplate.query(OracleUtils.limitForDataTable(sql, start, length), paramList.toArray(), rowMapper);
		
		return qtnReportHeaderList;
	}
	
	public long countQtnReportHeader(QtnReportHeader qtnReportHeader) {
		List<Object> paramList = new ArrayList<Object>();
		String str = " SELECT DISTINCT H.*, DECODE(M.QTN_REPORT_HDR_ID, null, 'FALSE', 'TRUE') DETAIL FROM IA_QTN_REPORT_HEADER H ";
		StringBuilder sql = new StringBuilder(str);
		sql.append("LEFT JOIN IA_QTN_REPORT_MAIN M ON M.QTN_REPORT_HDR_ID = H.QTN_REPORT_HDR_ID WHERE 1=1 ");
		sql.append("AND H.IS_DELETED = 'N' ");
		
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnReportHdrId())) {
			sql.append("AND H.QTN_REPORT_HDR_ID = ? ");
			paramList.add(qtnReportHeader.getQtnReportHdrId());
		}
		
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnReportHdrName())) {
			sql.append("AND H.QTN_REPORT_HDR_NAME = ? ");
			paramList.add(qtnReportHeader.getQtnReportHdrName());
		}
		
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnMasterId())) {
			sql.append("AND H.QTN_MASTER_ID = ? ");
			paramList.add(qtnReportHeader.getQtnMasterId());
		}
		
		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), paramList.toArray(),Long.class);
		return count;
	}
	
	
	private RowMapper<Int022Vo> rowMapper = new RowMapper<Int022Vo>() {

		@Override
		public Int022Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int022Vo vo = new Int022Vo();
			vo.setQtnReportHdrId(rs.getLong("QTN_REPORT_HDR_ID"));
			vo.setQtnMasterId(rs.getLong("QTN_MASTER_ID"));
			vo.setQtnReportHdrName(rs.getString("QTN_REPORT_HDR_NAME"));
			vo.setCreator(rs.getString("CREATOR"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			vo.setHasChild(rs.getString("DETAIL"));
			return vo;
		}

	};
	
	public Integer createQtnReportHeader(QtnReportHeader qtnReportHeader){
		List<Object> paramList = new ArrayList<Object>();
		String sql = "INSERT INTO IA_QTN_REPORT (QTN_REPORT_HDR_ID,QTN_REPORT_HDR_NAME,CREATOR,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) VALUES (IA_QTN_REPORT_SEQ.nextval,?,?,?,?,?,?)";
		paramList.add(qtnReportHeader.getQtnReportHdrName());
		paramList.add(qtnReportHeader.getCreator());
		paramList.add(qtnReportHeader.getCreatedBy());
		paramList.add(qtnReportHeader.getCreatedDate());
		paramList.add(qtnReportHeader.getUpdatedBy());
		paramList.add(qtnReportHeader.getUpdatedDate());
		return jdbcTemplate.update(sql.toString(), paramList.toArray());
	}
	
	public Integer deleteQtnReportHeader(QtnReportHeader qtnReportHeader){
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("DELETE FROM IA_QUESTIONNAIRE_HEADER  WHERE 1=1");
		if(BeanUtils.isNotEmpty(qtnReportHeader.getQtnReportHdrId())) {
			sql.append(" AND QTN_HEADER_ID = ? ");
			objList.add(qtnReportHeader.getQtnReportHdrId());
		}
		return jdbcTemplate.update(sql.toString(), objList.toArray());
	}
	
}
