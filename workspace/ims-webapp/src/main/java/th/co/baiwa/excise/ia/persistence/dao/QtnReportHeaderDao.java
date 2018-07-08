package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class QtnReportHeaderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sqlTemplate = " SELECT * FROM IA_QTN_REPORT_HEADER H WHERE 1 = 1 ";
	
	public List<QtnReportHeader> findByCriteria(QtnReportHeader qtnReportHeader) {
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
		
		List<QtnReportHeader> qtnReportHeaderList = jdbcTemplate.query(sql.toString(), paramList.toArray(),rowMapper );
		
		return qtnReportHeaderList;
	}
	
	public List<QtnReportHeader> findByCriteriaDataTable(QtnReportHeader qtnReportHeader,int start, int length) {
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
		
		List<QtnReportHeader> qtnReportHeaderList = jdbcTemplate.query(OracleUtils.limitForDataTable(sql, start, length), paramList.toArray(),rowMapper );
		
		return qtnReportHeaderList;
	}
	public long countQtnReportHeader(QtnReportHeader qtnReportHeader) {
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
		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), paramList.toArray(),Long.class);
		return count;
	}
	
	
	private RowMapper<QtnReportHeader> rowMapper = new RowMapper<QtnReportHeader>() {

		@Override
		public QtnReportHeader mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnReportHeader vo = new QtnReportHeader();
			vo.setQtnReportHdrId(rs.getBigDecimal("QTN_REPORT_HDR_ID"));
			vo.setQtnReportHdrName(rs.getString("QTN_REPORT_HDR_NAME"));
			vo.setCreator(rs.getString("CREATOR"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			
			return vo;

		}

	};
	
	public Integer createQtnReportHeader(QtnReportHeader qtnReportHeader){
		List<Object> paramList = new ArrayList<Object>();
		String sql = "INSERT INTO IA_QTN_REPORT_HEADER (QTN_REPORT_HDR_ID,QTN_REPORT_HDR_NAME,CREATOR,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) VALUES (IA_QTN_REPORT_HEADER_SEQ.nextval,?,?,?,?,?,?)";
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
