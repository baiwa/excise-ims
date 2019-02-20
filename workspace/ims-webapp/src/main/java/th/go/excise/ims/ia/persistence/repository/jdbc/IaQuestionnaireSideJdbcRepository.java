package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.vo.Int020101Vo;

@Repository
public class IaQuestionnaireSideJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int020101Vo> findAll() {
		String sql = " SELECT * FROM  IA_QUESTIONNAIRE_SIDE WHERE IS_DELETED = 'N' ";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int020101Vo> datas = commonJdbcTemplate.query(sql, new BeanPropertyRowMapper(Int020101Vo.class));
		return datas;
	}
	
	public List<Int020101Vo> findByIdHead(BigDecimal idHead) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM  IA_QUESTIONNAIRE_SIDE WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID_HEAD = ? ");
		params.add(idHead);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int020101Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),new BeanPropertyRowMapper(Int020101Vo.class));
		return datas;
	}
	
	public IaQuestionnaireSide findOne(BigDecimal id) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM  IA_QUESTIONNAIRE_SIDE WHERE IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID = ? ");
		params.add(id);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		IaQuestionnaireSide data = (IaQuestionnaireSide) commonJdbcTemplate.queryForObject(sqlBuilder.toString(), params.toArray(), new BeanPropertyRowMapper(IaQuestionnaireSide.class));
		return data;
	}
	
}