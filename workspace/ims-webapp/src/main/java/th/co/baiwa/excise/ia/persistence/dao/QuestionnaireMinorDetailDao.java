package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMinorDetail;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class QuestionnaireMinorDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sqlTemplate = " SELECT * FROM IA_QUESTIONNAIRE_MINOR_DETAIL H WHERE 1 = 1 ";

	public List<QuestionnaireMinorDetail> findByCriteria(QuestionnaireMinorDetail questionnaireMinorDetail) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate);

		if (BeanUtils.isNotEmpty(questionnaireMinorDetail.getQtnMinorId())) {
			sql.append("AND H.QTN_MINOR_ID = ? ");
			paramList.add(questionnaireMinorDetail.getQtnMinorId());
		}

		if (BeanUtils.isNotEmpty(questionnaireMinorDetail.getHeaderCode())) {
			sql.append("AND H.HEADER_CODE = ? ");
			paramList.add(questionnaireMinorDetail.getHeaderCode());
		}

		if (BeanUtils.isNotEmpty(questionnaireMinorDetail.getMainId())) {
			sql.append("AND H.MAIN_ID = ? ");
			paramList.add(questionnaireMinorDetail.getMainId());
		}

		if (BeanUtils.isNotEmpty(questionnaireMinorDetail.getQtnMinorDetail())) {
			sql.append("AND H.QTN_MINOR_DETAIL = ? ");
			paramList.add(questionnaireMinorDetail.getQtnMinorDetail());
		}

		List<QuestionnaireMinorDetail> questionnaireMinorDetailList = jdbcTemplate.query(sql.toString(),
				paramList.toArray(), rowMapper);
		;

		return questionnaireMinorDetailList;

	}

	private RowMapper<QuestionnaireMinorDetail> rowMapper = new RowMapper<QuestionnaireMinorDetail>() {

		@Override
		public QuestionnaireMinorDetail mapRow(ResultSet rs, int arg1) throws SQLException {
			QuestionnaireMinorDetail vo = new QuestionnaireMinorDetail();

			vo.setQtnMinorId(rs.getBigDecimal("QTN_MINOR_ID"));
			vo.setHeaderCode(rs.getString("HEADER_CODE"));
			vo.setMainId(rs.getBigDecimal("MAIN_ID"));
			vo.setQtnMinorDetail(rs.getString("QTN_MINOR_DETAIL"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}
	};

	public Integer createQuestionnaireMinorDetail(List<QuestionnaireMinorDetail> questionnaireMinorDetailList) {
		List<Object> paramList = null;
		String sql = "INSERT INTO IA_QUESTIONNAIRE_MINOR_DETAIL (QTN_MINOR_ID,HEADER_CODE,MAIN_ID,QTN_MINOR_DETAIL,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) VALUES (IA_QTN_MINOR_DETAIL_SEQ.nextval,?,?,?,?,?,?,?)";
		List<Object[]> objListOfArray = new ArrayList<Object[]>();
		for (QuestionnaireMinorDetail questionnaireMinorDetail : questionnaireMinorDetailList) {
			paramList = new ArrayList<Object>();
			paramList.add(questionnaireMinorDetail.getHeaderCode());
			paramList.add(questionnaireMinorDetail.getMainId());
			paramList.add(questionnaireMinorDetail.getQtnMinorDetail());
			paramList.add(questionnaireMinorDetail.getCreatedBy());
			paramList.add(questionnaireMinorDetail.getCreatedDate());
			paramList.add(questionnaireMinorDetail.getUpdatedBy());
			paramList.add(questionnaireMinorDetail.getUpdatedDate());
			objListOfArray.add(paramList.toArray());
		}
		int[] insertRow = jdbcTemplate.batchUpdate(sql.toString(), objListOfArray);
		int countInsert = 0;
		for (int i : insertRow) {
			countInsert+= i;
		}

		return countInsert;

	}

}
