package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireDetail;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class QuestionnaireDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	private String sqlTemplate = " SELECT * FROM IA_QUESTIONNAIRE_DETAIL H WHERE 1 = 1 ";

	public List<QuestionnaireDetail> findByCriteria(QuestionnaireDetail questionnaireDetail) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(sqlTemplate);

		if (BeanUtils.isNotEmpty(questionnaireDetail.getQtnDetailId())) {
			sql.append("AND H.QTN_DETAIL_ID = ? ");
			paramList.add(questionnaireDetail.getQtnDetailId());
		}
		if (BeanUtils.isNotEmpty(questionnaireDetail.getMasterId())) {
			sql.append("AND H.MASTER_ID = ?");
			paramList.add(questionnaireDetail.getMasterId());
		}
		if (BeanUtils.isNotEmpty(questionnaireDetail.getHeaderCode())) {
			sql.append("AND H.HEADER_CODE = ? ");
			paramList.add(questionnaireDetail.getHeaderCode());
		}
		if (BeanUtils.isNotEmpty(questionnaireDetail.getQtnMainDetail())) {
			sql.append("AND H.QTN_MAIN_DETAIL = ? ");
			paramList.add(questionnaireDetail.getQtnMainDetail());
		}
		List<QuestionnaireDetail> questionnaireDetailsList = jdbcTemplate.query(sql.toString(), paramList.toArray(),rowMapper);
		
		return questionnaireDetailsList;
	}

	private RowMapper<QuestionnaireDetail> rowMapper = new RowMapper<QuestionnaireDetail>() {

		@Override
		public QuestionnaireDetail mapRow(ResultSet rs, int arg1) throws SQLException {
			QuestionnaireDetail vo = new QuestionnaireDetail();

			vo.setQtnDetailId(rs.getBigDecimal("QTN_DETAIL_ID"));
			vo.setMasterId(rs.getBigDecimal("MASTER_ID"));
			vo.setHeaderCode(rs.getString("HEADER_CODE"));
			vo.setQtnMainDetail(rs.getString("QTN_MAIN_DETAIL"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));

			return vo;
		}
	};


	public Integer createQuestionnaireDetail(List<QuestionnaireDetail> questionnaireDetailList) {
		List<Object> paramList = new ArrayList<Object>();
		String sqlTemplate = " INSERT INTO IA_QUESTIONNAIRE_DETAIL (QTN_DETAIL_ID,MASTER_ID,HEADER_CODE,QTN_MAIN_DETAIL,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,IS_DELETED,VERSION) VALUES(?,?,?,?,?,?,?,?,?,?) ";
		StringBuffer sql = new StringBuffer(sqlTemplate);
		List<Object[]> objListOfArray = new ArrayList<Object[]>();
		for (QuestionnaireDetail questionnaireDetail : questionnaireDetailList) {
			paramList = new ArrayList<Object>();
			paramList.add(questionnaireDetail.getQtnDetailId());
			paramList.add(questionnaireDetail.getMasterId());
			paramList.add(questionnaireDetail.getHeaderCode());
			paramList.add(questionnaireDetail.getQtnMainDetail());
			paramList.add(questionnaireDetail.getCreatedBy());
			paramList.add(questionnaireDetail.getCreatedDate());
			paramList.add(questionnaireDetail.getUpdatedBy());
			paramList.add(questionnaireDetail.getUpdatedDate());
			paramList.add(questionnaireDetail.getIsDeleted());
			paramList.add(questionnaireDetail.getVersion());
			objListOfArray.add(paramList.toArray());
		}
		int[] insertRow = jdbcTemplate.batchUpdate(sql.toString(), objListOfArray);
		return insertRow.length;

	}
	
	
	//findByCriteriaDataTable
	public List<QuestionnaireDetail> findByCriteriaDataTable(QuestionnaireDetail questionnaireDetail,int start, int length) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		if(BeanUtils.isNotEmpty(questionnaireDetail.getQtnDetailId())) {
			sql.append("AND H.QTN_DETAIL_ID = ? ");
			paramList.add(questionnaireDetail.getQtnDetailId());
		}
		
		if(BeanUtils.isNotEmpty(questionnaireDetail.getQtnMainDetail())) {
			sql.append("AND H.QTN_MAIN_DETAIL = ? ");
			paramList.add(questionnaireDetail.getQtnMainDetail());
		}
		
		List<QuestionnaireDetail> questionnaireDetailList = jdbcTemplate.query(OracleUtils.limitForDataTable(sql, start, length), paramList.toArray(),rowMapper );
		
		return questionnaireDetailList;
	}
	
	//////
	public long countQuestionnaireDetail(QuestionnaireDetail questionnaireDetail) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		if(BeanUtils.isNotEmpty(questionnaireDetail.getQtnDetailId())) {
			sql.append("AND H.QTN_DETAIL_ID = ? ");
			paramList.add(questionnaireDetail.getQtnDetailId());
		}
		if(BeanUtils.isNotEmpty(questionnaireDetail.getQtnMainDetail())) {
			sql.append("AND H.QTN_MAIN_DETAIL = ? ");
			paramList.add(questionnaireDetail.getQtnMainDetail());
		}
		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), paramList.toArray(),Long.class);
		return count;
	}
}
