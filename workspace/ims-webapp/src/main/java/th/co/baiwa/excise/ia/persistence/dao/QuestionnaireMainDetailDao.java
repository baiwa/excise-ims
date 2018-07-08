package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMainDetail;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class QuestionnaireMainDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sqlTemplate = " SELECT * FROM IA_QUESTIONNAIRE_MAIN_DETAIL H WHERE 1 = 1 ";
	
	public List<QuestionnaireMainDetail> findByCriteria(QuestionnaireMainDetail questionnaireMainDetail ) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		
		if(BeanUtils.isNotEmpty(questionnaireMainDetail.getQtnMainId())) {
			sql.append("AND H.IA_QTN_MAIN_DETAIL_ID = ? ");
			paramList.add(questionnaireMainDetail.getQtnMainId());
		}
		
		if(BeanUtils.isNotEmpty(questionnaireMainDetail.getHeaderCode())) {
			sql.append("AND H.HEADER_CODE = ? ");
			paramList.add(questionnaireMainDetail.getHeaderCode());
		}
		
		if(BeanUtils.isNotEmpty(questionnaireMainDetail.getQtnMainDetail())) {
			sql.append("AND H.QTN_MAIN_DETAIL = ? ");
			paramList.add(questionnaireMainDetail.getQtnMainDetail());
		}
	
		List<QuestionnaireMainDetail> questionnaireMainDetailsList = jdbcTemplate.query(sql.toString(), paramList.toArray(),rowMapper );;
		
		return questionnaireMainDetailsList;
	}
	
	
	private RowMapper<QuestionnaireMainDetail> rowMapper = new RowMapper<QuestionnaireMainDetail>() {
		
		@Override
		public QuestionnaireMainDetail mapRow(ResultSet rs, int arg1) throws SQLException {
			QuestionnaireMainDetail vo = new QuestionnaireMainDetail();
			vo.setQtnMainId(rs.getBigDecimal("IA_QTN_MAIN_DETAIL_ID"));
			vo.setHeaderCode(rs.getString("HEADER_CODE"));
			vo.setQtnMainDetail(rs.getString("QTN_MAIN_DETAIL"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}
	};
	
	public Integer createQuestionnaireMainDetail(QuestionnaireMainDetail questionnaireMainDetail){
		List<Object> paramList = new ArrayList<Object>();
		String sql = "INSERT INTO IA_QUESTIONNAIRE_MAIN_DETAIL (IA_QTN_MAIN_DETAIL_ID,HEADER_CODE,QTN_MAIN_DETAIL,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) VALUES (IA_QTN_MAIN_DETAIL_SEQ.nextval,?,?,?,?,?,?)";
	
		paramList.add(questionnaireMainDetail.getHeaderCode());
		paramList.add(questionnaireMainDetail.getQtnMainDetail());
		paramList.add(questionnaireMainDetail.getCreatedBy());
		paramList.add(questionnaireMainDetail.getCreatedDate());
		paramList.add(questionnaireMainDetail.getUpdatedBy());
		paramList.add(questionnaireMainDetail.getUpdatedDate());
		
		return jdbcTemplate.update(sql.toString(), paramList.toArray());
		
		
	}
}
