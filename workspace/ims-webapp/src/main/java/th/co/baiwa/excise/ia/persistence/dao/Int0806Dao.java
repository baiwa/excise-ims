package th.co.baiwa.excise.ia.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.MoneyCheck;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.vo.Int061105FormSearchVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0806FormSearchVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Repository
public class Int0806Dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Lov> getValue1(Lov en) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM SYS_LOV L ");
		sql.append(" WHERE L.TYPE = ? ");
		valueList.add(en.getType());
		sql.append(" AND L.LOV_ID_MASTER is not null ");
		sql.append(" AND L.VALUE1 is not null ");

		List<Lov> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingGetValue1);
		return dataList;
	}

	private RowMapper<Lov> fieldMappingGetValue1 = new RowMapper<Lov>() {
		@Override
		public Lov mapRow(ResultSet rs, int arg1) throws SQLException {
			Lov en = new Lov();
			en.setLovId(rs.getLong("LOV_ID"));
			en.setLovIdMaster(rs.getLong("LOV_ID_MASTER"));
			en.setType(rs.getString("TYPE"));
			en.setValue1(rs.getString("VALUE1"));
			return en;
		}
	};
	
	public List<Lov> filerByIncomeCode(String type, BigDecimal incomeCode) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM SYS_LOV L ");
		sql.append(" WHERE L.TYPE = ? ");
		valueList.add(type);
		sql.append(" AND L.VALUE2 = ? ");
		valueList.add(incomeCode);

		List<Lov> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingfilerByIncomeCode);
//		List<Lov> dataReturn;
//		if(dataList.size() < 1) {
//			dataReturn = new Lov();
//		}else {
//			dataReturn = dataList.get(0);
//		}
		return dataList;
	}
	
	private RowMapper<Lov> fieldMappingfilerByIncomeCode = new RowMapper<Lov>() {
		@Override
		public Lov mapRow(ResultSet rs, int arg1) throws SQLException {
			Lov en = new Lov();
			en.setLovId(rs.getLong("LOV_ID"));
			en.setType(rs.getString("TYPE"));
			en.setValue1(rs.getString("VALUE1"));
			en.setValue2(rs.getString("VALUE2"));
			return en;
		}
	};
	
	public String getSubtype(String id) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT L.SUB_TYPE FROM SYS_LOV L ");
		sql.append(" WHERE L.LOV_ID = ? ");
		valueList.add(id);

		String data = jdbcTemplate.queryForObject(sql.toString(), valueList.toArray(), String.class);
		return data;
	}
	
	public List<MoneyCheck> queryByfilter(Int0806FormSearchVo en) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_MONEY_CHECK M ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND M.IS_DELETED = 'N' ");
		if(BeanUtils.isNotEmpty(en.getOfficeCode())) {
			sql.append("AND M.OFFICE_RECEIVE = ? ");
			valueList.add(en.getOfficeCode());
		}
		if(BeanUtils.isNotEmpty(en.getAccount())) {
			sql.append("AND M.INCOME_CODE = ? ");
			valueList.add(en.getAccount());
		}
		if(BeanUtils.isNotEmpty(en.getStartDate()) && BeanUtils.isNotEmpty(en.getEndDate())) {
			sql.append("AND M.TRN_DATE >= ? ");
			valueList.add(DateConstant.convertStrDDMMYYYYToDate(en.getStartDate()));
			sql.append("AND M.TRN_DATE <= ? ");
			valueList.add(DateConstant.convertStrDDMMYYYYToDate(en.getEndDate()));
		}

		List<MoneyCheck> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingByfilter);
		return dataList;
	}

	private RowMapper<MoneyCheck> fieldMappingByfilter = new RowMapper<MoneyCheck>() {
		@Override
		public MoneyCheck mapRow(ResultSet rs, int arg1) throws SQLException {
			MoneyCheck en = new MoneyCheck();
			en.setId(rs.getBigDecimal("ID"));
			en.setReceiptNo(rs.getString("RECEIPT_NO"));
			en.setDepositDate(rs.getDate("DEPOSIT_DATE"));
			en.setTrnDate(rs.getDate("TRN_DATE"));
			en.setNettaxAmount(rs.getBigDecimal("NETTAX_AMOUNT"));
			en.setNetlocAmount(rs.getBigDecimal("NETLOC_AMOUNT"));
			en.setCreatedDate(rs.getDate("CREATED_DATE"));
			en.setIncomeCode(rs.getBigDecimal("INCOME_CODE"));
			return en;
		}
	};

}
