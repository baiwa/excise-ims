package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.TransferList;
import th.co.baiwa.excise.ia.persistence.vo.Int069FormVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Repository
public class TransferListDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
public List<TransferList> queryByFilter(Int069FormVo vo) {
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_TRANSFER_LIST ");
		sql.append(" where 1 = 1 ");
		sql.append(" and IS_DELETED = 'N' ");
		
		if (BeanUtils.isNotEmpty(vo.getTransferList())) {
			sql.append(" and TRANSFER_LIST = ? ");
			valueList.add(vo.getTransferList());
		}
		if (BeanUtils.isNotEmpty(vo.getBudgetType())) {
			sql.append(" and BUDGET_TYPE = ? ");
			valueList.add(vo.getBudgetType());
		}
		if (BeanUtils.isNotEmpty(vo.getActivities())) {
			sql.append(" and ACTIVITIES = ? ");
			valueList.add(vo.getActivities());
		}
		
		if (BeanUtils.isNotEmpty(vo.getStart()) && BeanUtils.isNotEmpty(vo.getEnd())) {
			Date start = DateConstant.convertStrDDMMYYYYToDate(vo.getStart());
			Date end = DateConstant.convertStrDDMMYYYYToDate(vo.getEnd());
			
//			sql.append(" and to_char(CREATED_DATE , 'yyyy') = ? ");
			sql.append(" and (? <= REF_DATE and REF_DATE <= ? )");
			valueList.add(start);
			valueList.add(end);
		}

		List<TransferList> transferList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingByFilter);
		return transferList;
	}
	
	private RowMapper<TransferList> fieldMappingByFilter = new RowMapper<TransferList>() {
		@Override
		public TransferList mapRow(ResultSet rs, int arg1) throws SQLException {
			TransferList en = new TransferList();
//			
			en.setActivities(rs.getString("ACTIVITIES"));
			en.setAmount(rs.getBigDecimal("AMOUNT"));
			en.setBudget(rs.getString("BUDGET"));
			en.setBudgetCode(rs.getString("BUDGET_CODE"));
			en.setBudgetType(rs.getString("BUDGET_TYPE"));
			en.setCtgBudget(rs.getString("CTG_BUDGET"));
			en.setDescriptionList(rs.getString("DESCRIPTION_LIST"));
			en.setMofNum(rs.getString("MOF_NUM"));
			en.setNote(rs.getString("NOTE"));
			en.setOfficeCode(rs.getString("OFFICE_CODE"));
			en.setOfficeDesc(rs.getString("OFFICE_DESC"));
			en.setRefDateStr(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("REF_DATE")));
			en.setRefDate(rs.getDate("REF_DATE"));
			en.setRefNum(rs.getString("REF_NUM"));
			en.setSubCtgBudget(rs.getString("SUB_CTG_BUDGET"));
			en.setTransferId(rs.getBigDecimal("TRANSFER_ID"));
			en.setTransferList(rs.getString("TRANSFER_LIST"));
			
			return en;
		}
	};

}
