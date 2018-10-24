package th.co.baiwa.excise.ia.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ta.persistence.entity.ExciseRegistartionNumber;
import th.co.baiwa.excise.ta.persistence.vo.AnalysisFromCountVo;
import th.co.baiwa.excise.ta.persistence.vo.MockupForm;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ExciseRegisttionNumberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String sqlTaExciseId = " select D.*  from EXCISEADM.ta_excise_registtion_number D ";

	public Long countListdataPay(AnalysisFromCountVo countVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(1) FROM (SELECT DISTINCT TA_EXCISE_ID FROM");
		sql.append(" (SELECT H.*");
		sql.append(" FROM TA_EXCISE_REGISTTION_NUMBER H");
		sql.append(" INNER JOIN TA_EXCISE_TAX_RECEIVE D");
		sql.append(" ON H.TA_EXCISE_ID = D.TA_EXCISE_ID");
		sql.append(" AND D.TA_EXCISE_TAX_RECEIVE_MONTH  IN");
		sql.append(" (SELECT REPLACE(TO_CHAR( add_MONTHS( ?, LEVEL- ? ) , 'MON yy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'), '  ', ' ' ) Month_AFTER");
		sql.append(" FROM dual CONNECT BY LEVEL <= ?) where SUBSTR(H.TA_EXCISE_ID,15,1)=? " );
		sql.append(" AND H.TA_EXCISE_PRODUCT_TYPE=?");
		List<Object> params = new ArrayList<>();
		
		Date date = DateConstant.convertStrToDate(StringUtils.trim(countVo.getDateFrom()), DateConstant.MM_YYYY, DateConstant.LOCAL_EN);
		params.add(date);
		params.add(countVo.getDateTo());
		params.add(countVo.getDateTo());
		params.add(countVo.getCoordinatesFlag());
		params.add(countVo.getProductionType());
		
		if (StringUtils.isNotBlank(countVo.getFormSearch())){
            sql.append(" AND H.TA_EXCISE_OPERATOR_NAME LIKE ?");
            sql.append(" OR H.TA_EXCISE_ID LIKE ?");
            sql.append(" OR H.TA_EXCISE_FAC_ADDRESS LIKE ?");
            sql.append(" OR H.TA_EXCISE_SECTOR_AREA LIKE ?");
            sql.append(" OR H.TA_EXCISE_AREA LIKE ?");

            params.add("%"+StringUtils.trim(countVo.getFormSearch())+"%");
            params.add("%"+StringUtils.trim(countVo.getFormSearch())+"%");
            params.add("%"+StringUtils.trim(countVo.getFormSearch())+"%");
            params.add("%"+StringUtils.trim(countVo.getFormSearch())+"%");
            params.add("%"+StringUtils.trim(countVo.getFormSearch())+"%");
        }	
		sql.append("))");
		Long count = jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Long.class);

		return count;
	}
	public List<ExciseRegistartionNumber> queryByExciseId(String register, String exciseProductType,  List<String> conditionList, String formSearch, String coordinatesFlag) {
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		if (BeanUtils.isNotEmpty(conditionList)) {
			sql.append(
					" LEFT JOIN  ( SELECT D.TA_EXCISE_ID ,SUM(TO_NUMBER(REPLACE(trim(D.TA_EXCISE_TAX_RECEIVE_AMOUNT), ',',''), '99999999999999.99')) as total_amount FROM TA_EXCISE_TAX_RECEIVE D ");
			sql.append(" group by D.TA_EXCISE_ID ) JO ON JO.TA_EXCISE_ID = D.TA_EXCISE_ID");

		}
		sql.append(" where 1=1 ");


		if (exciseProductType != null && exciseProductType.length() > 0) {
			sql.append(" and  TA_EXCISE_PRODUCT_TYPE = ? ");
			objList.add(exciseProductType);
		}
		
		if(StringUtils.isNotBlank(coordinatesFlag)) {
			sql.append("AND SUBSTR(TA_EXCISE_ID,15,1) = ? ");
			objList.add(coordinatesFlag);
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
        if (StringUtils.isNotBlank(formSearch)){
            sql.append(" AND D.TA_EXCISE_OPERATOR_NAME LIKE ?");
            sql.append(" OR D.TA_EXCISE_ID LIKE ?");
            sql.append(" OR D.TA_EXCISE_FAC_ADDRESS LIKE ?");
            sql.append(" OR D.TA_EXCISE_SECTOR_AREA LIKE ?");
            sql.append(" OR D.TA_EXCISE_AREA LIKE ?");

            objList.add("%"+StringUtils.trim(formSearch)+"%");
            objList.add("%"+StringUtils.trim(formSearch)+"%");
            objList.add("%"+StringUtils.trim(formSearch)+"%");
            objList.add("%"+StringUtils.trim(formSearch)+"%");
            objList.add("%"+StringUtils.trim(formSearch)+"%");
        }
		sql.append(" order By TA_EXCISE_REGISTTION_NUMBER_ID ");
		List<ExciseRegistartionNumber> list = jdbcTemplate.query(sql.toString(), objList.toArray(),
				exciseRegisttionRowmapper);

		return list;
	}

	public long queryCountByExciseId(String exciseProductType, List<String> conditionList, String formSearch, String coordinatesFlag) {
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		if (BeanUtils.isNotEmpty(conditionList)) {
			sql.append(
					" LEFT JOIN  ( SELECT D.TA_EXCISE_ID ,SUM(TO_NUMBER(REPLACE(trim(D.TA_EXCISE_TAX_RECEIVE_AMOUNT), ',',''), '99999999999999.99')) as total_amount FROM TA_EXCISE_TAX_RECEIVE D ");
			sql.append(" group by D.TA_EXCISE_ID ) JO ON JO.TA_EXCISE_ID = D.TA_EXCISE_ID");

		}
		sql.append(" where 1=1 ");
		if (exciseProductType != null && exciseProductType.length() > 0) {
			sql.append(" and  TA_EXCISE_PRODUCT_TYPE = ? ");
			objList.add(exciseProductType);
		}
		
		if(StringUtils.isNotBlank(coordinatesFlag)) {
			sql.append("AND SUBSTR(TA_EXCISE_ID,15,1) = ? ");
			objList.add(coordinatesFlag);
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
        if (StringUtils.isNotBlank(formSearch)){
            sql.append(" AND D.TA_EXCISE_OPERATOR_NAME LIKE ?");
            sql.append(" OR D.TA_EXCISE_ID LIKE ?");
            sql.append(" OR D.TA_EXCISE_FAC_ADDRESS LIKE ?");
            sql.append(" OR D.TA_EXCISE_SECTOR_AREA LIKE ?");
            sql.append(" OR D.TA_EXCISE_AREA LIKE ?");

            objList.add("%"+StringUtils.trim(formSearch)+"%");
            objList.add("%"+StringUtils.trim(formSearch)+"%");
            objList.add("%"+StringUtils.trim(formSearch)+"%");
            objList.add("%"+StringUtils.trim(formSearch)+"%");
            objList.add("%"+StringUtils.trim(formSearch)+"%");
        }
		sql.append(" order By TA_EXCISE_REGISTTION_NUMBER_ID ");
		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), objList.toArray(),
				Long.class);

		return count;
	}

	private RowMapper<ExciseRegistartionNumber> exciseRegisttionRowmapper = new RowMapper<ExciseRegistartionNumber>() {
		@Override
		public ExciseRegistartionNumber mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseRegistartionNumber vo = new ExciseRegistartionNumber();

			vo.setExciseRegisttionNumberId(rs.getInt("TA_EXCISE_REGISTTION_NUMBER_ID"));
			vo.setExciseId(rs.getString("TA_EXCISE_ID"));
			vo.setExciseIdOld(rs.getString("TA_EXCISE_OLD_ID"));
			vo.setExciseOperatorName(rs.getString("TA_EXCISE_OPERATOR_NAME"));
			vo.setExciseIdenNumber(rs.getString("TA_EXCISE_IDEN_NUMBER"));
			vo.setExciseFacName(rs.getString("TA_EXCISE_FAC_NAME"));
			vo.setIndustrialAddress(rs.getString("TA_EXCISE_FAC_ADDRESS"));
			vo.setExciseArea(rs.getString("TA_EXCISE_AREA"));
			vo.setExciseRegisCapital(rs.getInt("TA_EXCISE_REGIS_CAPITAL"));
			vo.setExciseRemark(rs.getString("TA_EXCISE_REMARK"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE") != null ? rs.getDate("CREATED_DATE") : null);
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getTimestamp("UPDATED_DATE") != null ? rs.getDate("UPDATED_DATE") : null);
			vo.setTaexciseProductType(rs.getString("TA_EXCISE_PRODUCT_TYPE"));
			vo.setTaexciseSectorArea(rs.getString("TA_EXCISE_SECTOR_AREA"));
			return vo;

		}


	};

	public List<ExciseRegistartionNumber> queryByExciseRegistionNumber(String exciseProductType,
			List<String> conditionList) {
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		if (BeanUtils.isNotEmpty(conditionList)) {
			sql.append(
					" LEFT JOIN  ( SELECT D.TA_EXCISE_ID ,SUM(TO_NUMBER(REPLACE(trim(D.TA_EXCISE_TAX_RECEIVE_AMOUNT), ',',''), '99999999999999.99')) as total_amount FROM TA_EXCISE_TAX_RECEIVE D ");
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
				if (BeanUtils.isNotEmpty(condition)) {
					dataUseCondition.add(condition);
				}
			}
		}
		if (BeanUtils.isNotEmpty(dataUseCondition) && dataUseCondition.size() > 0) {
			boolean isFirstCondition = true;
			sql.append(" AND ( ");
			for (String condition : dataUseCondition) {
				if (BeanUtils.isNotEmpty(condition)) {

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

		List<ExciseRegistartionNumber> list = jdbcTemplate.query(sql.toString(), objList.toArray(),
				exciseRegisttionRowmapper);

		return list;
	}
	
	public BigDecimal average(MockupForm form) {
		String sqlQuery = "SELECT * FROM (SELECT to_number(D.TA_EXCISE_TAX_RECEIVE_AMOUNT) amount , " + 
				"  H.TA_EXCISE_ID TA_EXCISE_ID, " + 
				"  H.TA_EXCISE_PRODUCT_TYPE TA_EXCISE_PRODUCT_TYPE " + 
				" FROM TA_EXCISE_REGISTTION_NUMBER H " + 
				" INNER JOIN TA_EXCISE_TAX_RECEIVE D " + 
				" ON H.TA_EXCISE_ID = D.TA_EXCISE_ID " + 
				" AND D.TA_EXCISE_TAX_RECEIVE_MONTH IN " + 
				"  (SELECT REPLACE(TO_CHAR( add_MONTHS( Sysdate, LEVEL-12 ) , 'MON yy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'), '  ', ' ' ) Month_AFTER " + 
				"  FROM dual " + 
				"    CONNECT BY LEVEL <= 12 " + 
				"  )) " + 
				"WHERE TA_EXCISE_PRODUCT_TYPE  = 'น้ำมัน' " + 
				"AND SUBSTR(TA_EXCISE_ID,15,1) = '1'";
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlQuery);
		
		
		List<ExciseRegistartionNumber> list = jdbcTemplate.query(sql.toString(), objList.toArray(),
				exciseRegisttionRowmapper);

		return new BigDecimal(0);
	}

}
