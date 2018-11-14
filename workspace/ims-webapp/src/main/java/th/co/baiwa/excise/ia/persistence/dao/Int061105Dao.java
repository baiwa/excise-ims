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
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;
import th.co.baiwa.excise.ia.persistence.vo.Int061102FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int061105FormSearchVo;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetDetail;

@Repository
public class Int061105Dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<RentHouse> findInRentHouse(Int061105FormSearchVo ids) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_RENT_HOUSE R ");
		sql.append(" WHERE R.IS_DELETED = 'N' ");
		sql.append(" AND R.IA_DIS_REQ_ID IS NULL ");
		

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
			en.setStatus(rs.getString("STATUS"));
			return en;
		}
	};
	
	public List<TuitionFee> findInTuitionFee(Int061105FormSearchVo ids) {
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_TUITION_FEE T ");
		sql.append(" WHERE T.IS_DELETED = 'N' ");
		sql.append(" AND T.IA_DIS_REQ_ID IS NULL ");
		
		List<TuitionFee> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingTuitionFee);
		return dataList;
	}
	
	private RowMapper<TuitionFee> fieldMappingTuitionFee = new RowMapper<TuitionFee>() {
		@Override
		public TuitionFee mapRow(ResultSet rs, int arg1) throws SQLException {
			TuitionFee en = new TuitionFee();
			en.setId(rs.getBigDecimal("ID"));
			en.setCreatedBy(rs.getString("CREATED_BY"));
			en.setPition(rs.getString("PITION"));
			en.setBelong(rs.getString("BELONG"));
			en.setCreatedDate(rs.getDate("CREATED_DATE"));
			en.setSumAmount(rs.getBigDecimal("SUM_AMOUNT"));		
			en.setStatusCheck(rs.getString("STATUS_CHECK"));
			return en;
		}
	};
	
	public List<HealthCareWelFareEntity> findInMedicalWelfare(Int061105FormSearchVo ids) {
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_MEDICAL_WELFARE M ");
		sql.append(" WHERE M.IS_DELETED = 'N' ");
		sql.append(" AND M.IA_DIS_REQ_ID IS NULL ");
		
		List<HealthCareWelFareEntity> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingMedicalWelfare);
		return dataList;
	}
	
	private RowMapper<HealthCareWelFareEntity> fieldMappingMedicalWelfare = new RowMapper<HealthCareWelFareEntity>() {
		@Override
		public HealthCareWelFareEntity mapRow(ResultSet rs, int arg1) throws SQLException {
			HealthCareWelFareEntity en = new HealthCareWelFareEntity();
			en.setId(rs.getBigDecimal("ID"));
			en.setCreatedBy(rs.getString("CREATED_BY"));
			en.setPosition(rs.getString("POSITION"));
			en.setAffiliation(rs.getString("AFFILIATION"));
			en.setCreatedDate(rs.getDate("CREATED_DATE"));
			en.setTotalMoney(rs.getString("TOTAL_MONEY"));		
			en.setStatusCheck(rs.getString("STATUS_CHECK"));
			return en;
		}
	};

	public Long getNextval() {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT IA_DISBURSEMENT_REQUEST_SEQ.NEXTVAL FROM DUAL ");
		Long idSEQ = jdbcTemplate.queryForObject(sql.toString(), valueList.toArray(),Long.class);
		return idSEQ;
	}
	
	public void insertDisbursementRequest(DisbursementRequest en) {
		StringBuilder sql = new StringBuilder(" Insert into IA_DISBURSEMENT_REQUEST (ID, BILL_LADING, POSITION , AFFILIATION, AMOUNT, STATUS, BILL_PAY, POSITION_PAY, AFFILIATION_PAY, AMOUNT_PAY, CREATED_DATE_PAY, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_DELETED, VERSION, CREATED_BY_PAY, REQUEST_TYPE) "); 
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

		jdbcTemplate.update(sql.toString(), new Object[] {
				en.getId(),
				en.getBillLading(),
				en.getPosition(),
				en.getAffiliation(),
				en.getAmount(),
				"2208",
				null,
				null,
				null,
				null,
				null,
				en.getCreatedBy(),
				DateConstant.convertStrDDMMYYYYToDate(en.getCreatedDateStr()),
				null,
				null,
				"N",
				1,
				null,
				en.getRequestType()
		});
	}



}
