package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.vo.Int02Vo;

@Repository
public class IaQuestionnaireHdrJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<IaQuestionnaireHdr> getDataFilter(Int02Vo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_HDR WHERE IS_DELETED='N' ");
		
		if(StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}
		
		if(StringUtils.isNotBlank(request.getCreatedBy())) {
			sql.append(" AND CREATED_BY = ? ");
			params.add(request.getCreatedBy());
		}
		
		if(StringUtils.isNotBlank(request.getStartDate()) && StringUtils.isNotBlank(request.getEndDate())) {
			sql.append(" AND CREATED_DATE >= ? ");
			sql.append(" AND CREATED_DATE <= ? ");
			/* convert string to date */
			params.add(ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			params.add(ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaQuestionnaireHdr> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaQuestionnaireHdr.class));

		return datas;
	}
}
