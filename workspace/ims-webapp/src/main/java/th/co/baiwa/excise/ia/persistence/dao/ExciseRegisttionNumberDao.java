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

import th.co.baiwa.excise.ta.persistence.entity.ExciseRegistartionNumber;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ExciseRegisttionNumberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String sqlTaExciseId = " select D.*  from EXCISEADM.ta_excise_registtion_number D ";

	public List<ExciseRegistartionNumber> queryByExciseId(String register, String exciseProductType, int start, int length, List<String> conditionList) {
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		if (BeanUtils.isNotEmpty(conditionList)) {
			sql.append(" LEFT JOIN  ( SELECT D.TA_EXCISE_ID ,SUM(TO_NUMBER(REPLACE(trim(D.TA_EXCISE_TAX_RECEIVE_AMOUNT), ',',''), '99999999999999.99')) as total_amount FROM TA_EXCISE_TAX_RECEIVE D ");
			sql.append(" group by D.TA_EXCISE_ID ) JO ON JO.TA_EXCISE_ID = D.TA_EXCISE_ID");

		}
		sql.append(" where 1=1 ");
		if (exciseProductType != null && exciseProductType.length() > 0) {
			sql.append(" and  TA_EXCISE_PRODUCT_TYPE = ? ");
			objList.add(exciseProductType);
		}

		if (BeanUtils.isNotEmpty(conditionList)) {
			boolean isFirstCondition = true;
			sql.append(" AND ( ");
			for (String condition : conditionList) {
				if (!isFirstCondition) {
					sql.append(" OR ");
				} else {
					isFirstCondition = false;
				}
				if (condition.indexOf(">:") == 0) {
					BigDecimal bigDecimal = new BigDecimal(condition.split(":")[1]);
					sql.append(" JO.total_amount >=  ? ");
					objList.add(bigDecimal);
				} else if (condition.indexOf("<:") == 0) {
					BigDecimal bigDecimal = new BigDecimal(condition.split(":")[1]);
					sql.append(" JO.total_amount <=  ? ");
					objList.add(bigDecimal);
				} else {
					BigDecimal startValue = new BigDecimal(condition.split(":")[0]);
					BigDecimal endValue = new BigDecimal(condition.split(":")[1]);
					sql.append(" ( JO.total_amount >=  ?  AND JO.total_amount <=  ? ) ");
					objList.add(startValue);
					objList.add(endValue);
				}
			}
			sql.append(" ) ");
		}

		sql.append(" order By TA_EXCISE_REGISTTION_NUMBER_ID ");
		List<ExciseRegistartionNumber> list = jdbcTemplate.query(OracleUtils.limitForDataTable(sql.toString(), start, length), objList.toArray(), exciseRegisttionRowmapper);

		return list;
	}

	public long queryCountByExciseId(String exciseProductType, List<String> conditionList) {
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		if (BeanUtils.isNotEmpty(conditionList)) {
			sql.append(" LEFT JOIN  ( SELECT D.TA_EXCISE_ID ,SUM(TO_NUMBER(REPLACE(trim(D.TA_EXCISE_TAX_RECEIVE_AMOUNT), ',',''), '99999999999999.99')) as total_amount FROM TA_EXCISE_TAX_RECEIVE D ");
			sql.append(" group by D.TA_EXCISE_ID ) JO ON JO.TA_EXCISE_ID = D.TA_EXCISE_ID");

		}
		sql.append(" where 1=1 ");
		if (exciseProductType != null && exciseProductType.length() > 0) {
			sql.append(" and  TA_EXCISE_PRODUCT_TYPE = ? ");
			objList.add(exciseProductType);
		}

		if (BeanUtils.isNotEmpty(conditionList)) {
			boolean isFirstCondition = true;
			sql.append(" AND ( ");
			for (String condition : conditionList) {
				if (!isFirstCondition) {
					sql.append(" OR ");
				} else {
					isFirstCondition = false;
				}
				if (condition.indexOf(">:") == 0) {
					BigDecimal bigDecimal = new BigDecimal(condition.split(":")[1]);
					sql.append(" JO.total_amount >=  ? ");
					objList.add(bigDecimal);
				} else if (condition.indexOf("<:") == 0) {
					BigDecimal bigDecimal = new BigDecimal(condition.split(":")[1]);
					sql.append(" JO.total_amount <=  ? ");
					objList.add(bigDecimal);
				} else {
					BigDecimal startValue = new BigDecimal(condition.split(":")[0]);
					BigDecimal endValue = new BigDecimal(condition.split(":")[1]);
					sql.append(" ( JO.total_amount >=  ?  AND JO.total_amount <=  ? ) ");
					objList.add(startValue);
					objList.add(endValue);
				}
			}
			sql.append(" ) ");
		}

		sql.append(" order By TA_EXCISE_REGISTTION_NUMBER_ID ");
		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), objList.toArray(), Long.class);

		return count;
	}

	private RowMapper<ExciseRegistartionNumber> exciseRegisttionRowmapper=new RowMapper<ExciseRegistartionNumber>(){@Override public ExciseRegistartionNumber mapRow(ResultSet rs,int arg1)throws SQLException{ExciseRegistartionNumber vo=new ExciseRegistartionNumber();

	vo.setExciseRegisttionNumberId(rs.getInt("TA_EXCISE_REGISTTION_NUMBER_ID"));vo.setExciseId(rs.getString("TA_EXCISE_ID"));vo.setExciseOperatorName(rs.getString("TA_EXCISE_OPERATOR_NAME"));vo.setExciseIdenNumber(rs.getString("TA_EXCISE_IDEN_NUMBER"));vo.setExciseFacName(rs.getString("TA_EXCISE_FAC_NAME"));vo.setIndustrialAddress(rs.getString("TA_EXCISE_FAC_ADDRESS"));vo.setExciseArea(rs.getString("TA_EXCISE_AREA"));vo.setExciseRegisCapital(rs.getInt("TA_EXCISE_REGIS_CAPITAL"));vo.setExciseRemark(rs.getString("TA_EXCISE_REMARK"));vo.setCreatedBy(rs.getString("CREATED_BY"));vo.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME")!=null?rs.getTimestamp("CREATED_DATETIME").toLocalDateTime():null);vo.setUpdateBy(rs.getString("UPDATE_BY"));vo.setUpdateDatetime(rs.getTimestamp("UPDATE_DATETIME")!=null?rs.getTimestamp("UPDATE_DATETIME").toLocalDateTime():null);vo.setTaexciseProductType(rs.getString("TA_EXCISE_PRODUCT_TYPE"));vo.setTaexciseSectorArea(rs.getString("TA_EXCISE_SECTOR_AREA"));return vo;

	}

	};

	public List<ExciseRegistartionNumber> queryByExciseRegistionNumber(String exciseProductType , List<String> conditionList) {
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		if (BeanUtils.isNotEmpty(conditionList)) {
			sql.append(" LEFT JOIN  ( SELECT D.TA_EXCISE_ID ,SUM(TO_NUMBER(REPLACE(trim(D.TA_EXCISE_TAX_RECEIVE_AMOUNT), ',',''), '99999999999999.99')) as total_amount FROM TA_EXCISE_TAX_RECEIVE D ");
			sql.append(" group by D.TA_EXCISE_ID ) JO ON JO.TA_EXCISE_ID = D.TA_EXCISE_ID");

		}
		sql.append(" where 1=1 ");
		if (exciseProductType != null && exciseProductType.length() > 0) {
			sql.append(" and  TA_EXCISE_PRODUCT_TYPE = ? ");
			objList.add(exciseProductType);
		}
		List<String> dataUseCondition = new ArrayList<String>();
		if (BeanUtils.isNotEmpty(conditionList) && conditionList.size() > 0) {
			for (String condition : conditionList) {
				if(BeanUtils.isNotEmpty(condition)) {
					dataUseCondition.add(condition);
				}
			}
		}
		if (BeanUtils.isNotEmpty(dataUseCondition) && dataUseCondition.size() > 0) {
			boolean isFirstCondition = true;
			sql.append(" AND ( ");
			for (String condition : dataUseCondition) {
				if(BeanUtils.isNotEmpty(condition)) {
					
				
				if (!isFirstCondition) {
					sql.append(" OR ");
				} else {
					isFirstCondition = false;
				}
				if (condition.indexOf(">:") == 0) {
					BigDecimal bigDecimal = new BigDecimal(condition.split(":")[1]);
					sql.append(" JO.total_amount >=  ? ");
					objList.add(bigDecimal);
				} else if (condition.indexOf("<:") == 0) {
					BigDecimal bigDecimal = new BigDecimal(condition.split(":")[1]);
					sql.append(" JO.total_amount <=  ? ");
					objList.add(bigDecimal);
				} else {
					BigDecimal startValue = new BigDecimal(condition.split(":")[0]);
					BigDecimal endValue = new BigDecimal(condition.split(":")[1]);
					sql.append(" ( JO.total_amount >=  ?  AND JO.total_amount <=  ? ) ");
					objList.add(startValue);
					objList.add(endValue);
				}
				}
			}
			sql.append(" ) ");
		}

	List<ExciseRegistartionNumber> list = jdbcTemplate.query(sql.toString(),objList.toArray() ,exciseRegisttionRowmapper);

	return list;
}

}
