package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;
import th.co.baiwa.excise.ia.persistence.vo.Int061102FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int061105FormSearchVo;

@Repository
public class Int061105Dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<RentHouse> findInRentHouse(Int061105FormSearchVo ids) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_RENT_HOUSE R ");
		sql.append(" where R.STATUS = ? ");
		valueList.add(ids.getStatus());
		sql.append(" and R.IS_DELETED = 'N' ");

		List<RentHouse> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingRentHouse);
		return dataList;
	}

	private RowMapper<RentHouse> fieldMappingRentHouse = new RowMapper<RentHouse>() {
		@Override
		public RentHouse mapRow(ResultSet rs, int arg1) throws SQLException {
			RentHouse en = new RentHouse();
			en.setRentHouseId(rs.getBigDecimal("RENT_HOUSE_ID"));
			en.setCreatedBy(rs.getString("CREATED_BY"));
			en.setPosition(rs.getString("POSITION"));
			en.setAffiliation(rs.getString("AFFILIATION"));
			en.setCreatedDate(rs.getDate("CREATED_DATE"));
			en.setTotalWithdraw(rs.getBigDecimal("TOTAL_WITHDRAW"));		
			return en;
		}
	};
	
	public List<TuitionFee> findInTuitionFee(Int061105FormSearchVo ids) {
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_TUITION_FEE T ");
		sql.append(" where T.STATUS = ? ");
		valueList.add(ids.getStatus());
		sql.append(" and T.IS_DELETED = 'N' ");
		
		List<TuitionFee> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingTuitionFee);
		return dataList;
	}
	
	private RowMapper<TuitionFee> fieldMappingTuitionFee = new RowMapper<TuitionFee>() {
		@Override
		public TuitionFee mapRow(ResultSet rs, int arg1) throws SQLException {
			TuitionFee en = new TuitionFee();
			en.setId(rs.getLong("ID"));
			en.setCreatedBy(rs.getString("CREATED_BY"));
			en.setPition(rs.getString("PITION"));
			en.setBelong(rs.getString("BELONG"));
			en.setCreatedDate(rs.getDate("CREATED_DATE"));
			en.setSumAmount(rs.getBigDecimal("SUM_AMOUNT"));			
			return en;
		}
	};
	
	public List<HealthCareWelFareEntity> findInMedicalWelfare(Int061105FormSearchVo ids) {
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_MEDICAL_WELFARE M ");
		sql.append(" where M.STATUS = ? ");
		valueList.add(ids.getStatus());
		sql.append(" and M.IS_DELETED = 'N' ");
		
		List<HealthCareWelFareEntity> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingMedicalWelfare);
		return dataList;
	}
	
	private RowMapper<HealthCareWelFareEntity> fieldMappingMedicalWelfare = new RowMapper<HealthCareWelFareEntity>() {
		@Override
		public HealthCareWelFareEntity mapRow(ResultSet rs, int arg1) throws SQLException {
			HealthCareWelFareEntity en = new HealthCareWelFareEntity();
			en.setId(rs.getBigDecimal("ID"));
			en.setCreatedBy(rs.getString("CREATED_BY"));
			en.setPosition(rs.getString("PITION"));
			en.setAffiliation(rs.getString("BELONG"));
			en.setCreatedDate(rs.getDate("CREATED_DATE"));
			en.setTotalMoney(rs.getString("SUM_AMOUNT"));			
			return en;
		}
	};

}
