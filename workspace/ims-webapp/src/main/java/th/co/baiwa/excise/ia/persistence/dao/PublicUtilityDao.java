package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.PublicUtility;
import th.co.baiwa.excise.ia.persistence.vo.Int068FormVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class PublicUtilityDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Int068FormVo> queryByFilter(PublicUtility pu) {
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_PUBLIC_UTILITY ");
		sql.append(" where 1 = 1 ");

		if (BeanUtils.isNotEmpty(pu.getExciseDepartment())) {
			sql.append(" and EXCISE_DEPARTMENT = ? ");
			valueList.add(pu.getExciseDepartment());
		}
		if (BeanUtils.isNotEmpty(pu.getExciseDistrict())) {
			sql.append(" and EXCISE_DISTRICT = ? ");
			valueList.add(pu.getExciseDistrict());
		}
		if (BeanUtils.isNotEmpty(pu.getExciseRegion())) {
			sql.append(" and EXCISE_REGION = ? ");
			valueList.add(pu.getExciseRegion());
		}
		if (BeanUtils.isNotEmpty(pu.getBudgetYear())) {
			sql.append(" and to_char(CREATED_DATE , 'yyyy') = ? ");
			valueList.add(pu.getBudgetYear());
		}

		List<Int068FormVo> publicUtilityList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingByFilter);
		return publicUtilityList;
	}
	
	private RowMapper<Int068FormVo> fieldMappingByFilter = new RowMapper<Int068FormVo>() {
		@Override
		public Int068FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int068FormVo pu = new Int068FormVo();
			pu.setPublicUtilityId(rs.getInt("PUBLIC_UTILITY_ID"));
			pu.setAllocatedBudgetId(rs.getInt("ALLOCATED_BUDGET_ID"));
			pu.setAmount(rs.getLong("AMOUNT"));
			pu.setExciseDepartment(rs.getString("EXCISE_DEPARTMENT"));
			pu.setExciseDistrict(rs.getString("EXCISE_DISTRICT"));
			pu.setExciseRegion(rs.getString("EXCISE_REGION"));
			pu.setInvoiceDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("INVOICE_DATE")));
			pu.setInvoiceNumber(rs.getString("INVOICE_NUMBER"));
			pu.setMonthInvoice(rs.getString("MONTH_INVOICE"));
			pu.setPublicUtilityType(rs.getString("PUBLIC_UTILITY_TYPE"));
			pu.setWithdrawalDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("WITHDRAWAL_DATE")));
			pu.setWithdrawalNumber(rs.getString("WITHDRAWAL_NUMBER"));
			
			return pu;
		}
	};

}
