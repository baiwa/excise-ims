package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class QuestionnaireHeaderDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String sqlTemplate = " SELECT * FROM IA_QUESTIONNAIRE_HEADER H WHERE 1 = 1 ";
	
	public List<QuestionnaireHeader> findByCriteria(QuestionnaireHeader questionnaireHeader) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		if(BeanUtils.isNotEmpty(questionnaireHeader.getQtnHeaderId())) {
			sql.append("AND H.QTN_HEADER_ID = ? ");
			paramList.add(questionnaireHeader.getQtnHeaderId());
		}
		
		if(BeanUtils.isNotEmpty(questionnaireHeader.getQtnHeaderCode())) {
			sql.append("AND H.QTN_HEADER_CODE = ? ");
			paramList.add(questionnaireHeader.getQtnHeaderCode());
		}
		
		if(BeanUtils.isNotEmpty(questionnaireHeader.getQtnHeaderName())) {
			sql.append("AND H.QTN_HEADER_NAME = ? ");
			paramList.add(questionnaireHeader.getQtnHeaderName());
		}
		
		List<QuestionnaireHeader> questionnaireHeaderDaoList = jdbcTemplate.query(sql.toString(), paramList.toArray(), rowMapper );
		
		return questionnaireHeaderDaoList;
	}
	
	private RowMapper<QuestionnaireHeader> rowMapper = new RowMapper<QuestionnaireHeader>() {

		@Override
		public QuestionnaireHeader mapRow(ResultSet rs, int arg1) throws SQLException {
			QuestionnaireHeader vo = new QuestionnaireHeader();
			vo.setQtnHeaderId(rs.getBigDecimal("QTN_HEADER_ID"));
			vo.setQtnHeaderCode(rs.getString("QTN_HEADER_CODE"));
			vo.setQtnHeaderName(rs.getString("QTN_HEADER_NAME"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			
			return vo;

		}

	};
	
	
}
