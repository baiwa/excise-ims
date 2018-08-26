package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMain;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMinor;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class QuestionnaireMinorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sqlTemplate = " SELECT * FROM IA_QUESTIONNAIRE_MINOR_DETAIL H WHERE 1 = 1 ";

	public List<QuestionnaireMinor> findByCriteria(QuestionnaireMinor questionnaireMinor, long start, long length) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(sqlTemplate);

		if (BeanUtils.isNotEmpty(questionnaireMinor.getQtnMinorId())) {
			sql.append("AND H.QTN_MINOR_ID = ? ");
			paramList.add(questionnaireMinor.getQtnMinorId());
		}
		if (BeanUtils.isNotEmpty(questionnaireMinor.getHeaderCode())) {
			sql.append("AND H.HEADER_CODE = ? ");
			paramList.add(questionnaireMinor.getHeaderCode());
		}
		if (BeanUtils.isNotEmpty(questionnaireMinor.getMainId())) {
			sql.append("AND H.MAIN_ID = ? ");
			paramList.add(questionnaireMinor.getMainId());
		}
		
		String query = "";
		if (BeanUtils.isNotEmpty(start)&&BeanUtils.isNotEmpty(length)&&length!=0) {
			query = OracleUtils.limitForDataTable(sql.toString(), start, length);
		} else {
			query = sql.toString();
		}
		
		List<QuestionnaireMinor> minorList = jdbcTemplate.query(query, paramList.toArray(),
				mapper);

		return minorList;
	}

	private RowMapper<QuestionnaireMinor> mapper = new RowMapper<QuestionnaireMinor>() {
		@Override
		public QuestionnaireMinor mapRow(ResultSet rs, int arg1) throws SQLException {
			QuestionnaireMinor vo = new QuestionnaireMinor();
			vo.setQtnMinorId(rs.getLong("QTN_MINOR_ID"));
			vo.setHeaderCode(rs.getString("HEADER_CODE"));
			vo.setMainId(rs.getLong("MAIN_ID"));
			vo.setQtnMinorDetail(rs.getString("QTN_MINOR_DETAIL"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}
	};
	
}
