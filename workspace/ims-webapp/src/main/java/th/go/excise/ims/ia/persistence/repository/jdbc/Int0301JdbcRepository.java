package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.vo.Int0301FormVo;

@Repository
public class Int0301JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String SQL_IA_RISK_FACTORS = "SELECT * FROM IA_RISK_FACTORS WHERE IS_DELETED = 'N' and INSPECTION_WORK = ?";

	public List<IaRiskFactors> list(Int0301FormVo form) {
		List<IaRiskFactors> iaRiskFactorsList = new ArrayList<IaRiskFactors>();

		StringBuilder sql = new StringBuilder(SQL_IA_RISK_FACTORS);
		List<Object> params = new ArrayList<Object>();
		
		
		params.add(form.getInspectionWork());
		
		
		if(StringUtils.isNotBlank(form.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(form.getBudgetYear());
		}

		
		iaRiskFactorsList = commonJdbcTemplate.query(sql.toString(), params.toArray() ,listRowmapper);

		return iaRiskFactorsList;
	}

	private RowMapper<IaRiskFactors> listRowmapper = new RowMapper<IaRiskFactors>() {
		@Override
		public IaRiskFactors mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskFactors vo = new IaRiskFactors();		
			vo.setId(rs .getBigDecimal("ID"));
			vo.setRiskFactors(rs .getString("RISK_FACTORS"));	
			vo.setBudgetYear(rs .getString("BUDGET_YEAR"));
			vo.setSide(rs .getString("SIDE"));
			vo.setFactorsLow(rs .getString("FACTORS_LOW"));	
			vo.setFactorsMedium(rs .getString("FACTORS_MEDIUM"));
			vo.setFactorsHigh(rs .getString("FACTORS_HIGH"));
			vo.setStatusScreen(rs .getString("STATUS_SCREEN"));
			vo.setDateCriteria(rs .getString("DATE_CRITERIA"));	
			vo.setInspectionWork(rs .getBigDecimal("INSPECTION_WORK"));
			vo.setCreatedBy(rs .getString("CREATED_BY"));
			
			
			LocalDateTime createdDate = LocalDateTimeConverter.convertToEntityAttribute(rs .getTimestamp("CREATED_DATE"));
			vo.setCreatedDate(createdDate);
			
			vo.setCreatedDateDesc(ConvertDateUtils.formatDateToString(rs .getDate("CREATED_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			
			vo.setUpdatedBy(rs .getString("UPDATED_BY"));
			
			LocalDateTime updatedDate = LocalDateTimeConverter.convertToEntityAttribute(rs .getTimestamp("UPDATED_DATE"));
			vo.setUpdatedDate(updatedDate);
			
			vo.setUpdateDateDesc(ConvertDateUtils.formatDateToString(rs .getDate("UPDATED_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			
			
	
			return vo;
		}
	};
}


