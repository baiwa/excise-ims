package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.IaQuestionnaireSideVo;

@Repository
public class IaQuestionnaireSideJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<IaQuestionnaireSideVo> findAll() {
		String sql = " SELECT * FROM  IA_QUESTIONNAIRE_SIDE ";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<IaQuestionnaireSideVo> datas = this.commonJdbcTemplate.query(sql, new BeanPropertyRowMapper(IaQuestionnaireSideVo.class));
		return datas;
	}
	
}
