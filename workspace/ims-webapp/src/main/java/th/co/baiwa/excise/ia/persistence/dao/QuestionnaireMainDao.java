package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMain;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class QuestionnaireMainDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sqlTemplate = " SELECT * FROM IA_QUESTIONNAIRE_MAIN_DETAIL H WHERE 1 = 1 ";

	public List<QuestionnaireMain> findByCriteria(QuestionnaireMain questionnaireMain, long start, long length) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(sqlTemplate);

		if (BeanUtils.isNotEmpty(questionnaireMain.getQtnMainDetailId())) {
			sql.append("AND H.IA_QTN_MAIN_DETAIL_ID = ? ");
			paramList.add(questionnaireMain.getQtnMainDetailId());
		}
		if (BeanUtils.isNotEmpty(questionnaireMain.getHeaderCode())) {
			sql.append("AND H.HEADER_CODE = ? ");
			paramList.add(questionnaireMain.getHeaderCode());
		}
		if (BeanUtils.isNotEmpty(questionnaireMain.getQtnMainDetail())) {
			sql.append("AND H.QTN_MAIN_DETAIL = ? ");
			paramList.add(questionnaireMain.getQtnMainDetail());
		}
		
		String query = "";
		if (BeanUtils.isNotEmpty(start)&&BeanUtils.isNotEmpty(length)&&length!=0) {
			query = OracleUtils.limitForDataTable(sql.toString(), start, length);
		} else {
			query = sql.toString();
		}
		
		List<QuestionnaireMain> mainList = jdbcTemplate.query(query, paramList.toArray(),
				mapper);
		return mainList;
	}
	
	public List<QuestionnaireMain> findForInt023(long start, long length) {
		List<Object> paramList = new ArrayList<Object>();
		String template = " SELECT H.* FROM IA_QUESTIONNAIRE_MAIN_DETAIL H LEFT JOIN IA_QUESTIONNAIRE_MINOR_DETAIL M ";
		StringBuffer sql = new StringBuffer(template);
		sql.append("ON M.MAIN_ID = H.IA_QTN_MAIN_DETAIL_ID WHERE 1=1 ORDER BY H.IA_QTN_MAIN_DETAIL_ID ASC ");
		
		String query = "";
		if (BeanUtils.isNotEmpty(start)&&BeanUtils.isNotEmpty(length)&&length!=0) {
			query = OracleUtils.limitForDataTable(sql.toString(), start, length);
		} else {
			query = sql.toString();
		}
		
		List<QuestionnaireMain> mainList = jdbcTemplate.query(query, paramList.toArray(),
				mapper);
		return mainList;
	}
	
	public List<QuestionnaireMain> findForInt02m31(DataTableRequest req) {
		List<Object> paramList = new ArrayList<Object>();
		String template = " SELECT H.* FROM IA_QUESTIONNAIRE_MAIN_DETAIL H LEFT JOIN IA_QUESTIONNAIRE_MINOR_DETAIL M ";
		StringBuffer sql = new StringBuffer(template);
		sql.append("ON M.MAIN_ID = H.IA_QTN_MAIN_DETAIL_ID WHERE 1=1 ");
		
		if (BeanUtils.isNotEmpty(req.getHeaderCode())) {
			sql.append(" and H.HEADER_CODE = ? ");
			paramList.add(req.getHeaderCode());

		}
		sql.append(" ORDER BY H.IA_QTN_MAIN_DETAIL_ID ASC ");
		
		String query = "";
		if (BeanUtils.isNotEmpty(req.getStart())&&BeanUtils.isNotEmpty( req.getLength() ) && (req.getLength()!=0) ) {
			query = OracleUtils.limitForDataTable(sql.toString(), req.getStart(), req.getLength());
		} else {
			query = sql.toString();
		}
		
		List<QuestionnaireMain> mainList = jdbcTemplate.query(query, paramList.toArray(),
				mapper);
		return mainList;
	}

	private RowMapper<QuestionnaireMain> mapper = new RowMapper<QuestionnaireMain>() {

		@Override
		public QuestionnaireMain mapRow(ResultSet rs, int arg1) throws SQLException {
			QuestionnaireMain vo = new QuestionnaireMain();
			vo.setQtnMainDetailId(rs.getLong("IA_QTN_MAIN_DETAIL_ID"));
			vo.setHeaderCode(rs.getString("HEADER_CODE"));
			vo.setQtnMainDetail(rs.getString("QTN_MAIN_DETAIL"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}
	};
	
}
