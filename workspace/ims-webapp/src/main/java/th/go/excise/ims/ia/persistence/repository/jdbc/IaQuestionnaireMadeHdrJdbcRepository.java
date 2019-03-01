package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int0202FormVo;
import th.go.excise.ims.ia.vo.Int0202Vo;

@Repository
public class IaQuestionnaireMadeHdrJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int0202Vo> getDataFilter(Int0202FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_MADE_HDR WHERE 1=1 AND IS_DELETED='N' ");
		sql.append(" AND OFFICE_CODE LIKE ? ");
		params.add(request.getOfficeCode().substring(0, 2) + "%");
		
		if(StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}
		
		if(StringUtils.isNotBlank(request.getCreatedBy())) {
			sql.append(" AND CREATED_BY LIKE ?");
			params.add(request.getCreatedBy() + "%");
		}
		
		if(StringUtils.isNotBlank(request.getStartDate())) {
			sql.append(" AND TRUNC(START_DATE) >= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			
		}
		if(StringUtils.isNotBlank(request.getEndDate())) {
			sql.append(" AND TRUNC(END_DATE) <= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}
 		sql.append(" ORDER BY CREATED_DATE ASC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int0202Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(), new BeanPropertyRowMapper(Int0202Vo.class));

		return datas; 
	}
	
	/* count datatable */
	public Integer countDatafilter(Int0202FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_MADE_HDR WHERE IS_DELETED='N' ");
		sql.append(" AND OFFICE_CODE LIKE ? ");
		params.add(request.getOfficeCode().substring(0, 2) + "%");
		
		if(StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}
		
		if(StringUtils.isNotBlank(request.getCreatedBy())) {
			sql.append(" AND CREATED_BY LIKE ? ");
			params.add(request.getCreatedBy() + "%");
		}
		
		if(StringUtils.isNotBlank(request.getStartDate()) && StringUtils.isNotBlank(request.getEndDate())) {
			sql.append(" AND TRUNC(CREATED_DATE) >= ? ");
			sql.append(" AND TRUNC(CREATED_DATE) <= ? ");
			/* convert string to date */
			params.add(ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			params.add(ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}
		sql.append(" ORDER BY CREATED_DATE ASC");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count; 
	}

}
