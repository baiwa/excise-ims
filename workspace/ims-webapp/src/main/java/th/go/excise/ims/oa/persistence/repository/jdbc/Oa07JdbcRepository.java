package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Reg4000Vo;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;

@Repository
public class Oa07JdbcRepository {

	@Autowired
	CommonJdbcTemplate commonJdbcTemplate;

	public List<Oa07Reg4000Vo> reg4000(Oa07FormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT R4000.CUS_FULLNAME ,  ");
		sql.append("   R4000.FAC_FULLNAME ,  ");
		sql.append("   R4000.FAC_ADDRESS ,  ");
		sql.append("   R4000.DUTY_CODE ,  ");
		sql.append("   R4000.OFFICE_CODE OFFICE_CODE_R4000 ,  ");
		sql.append("   ED_SECTOR.OFFICE_CODE SEC_CODE ,  ");
		sql.append("   ED_SECTOR.OFF_SHORT_NAME SEC_DESC ,  ");
		sql.append("   ED_AREA.OFFICE_CODE AREA_CODE ,  ");
		sql.append("   ED_AREA.OFF_SHORT_NAME AREA_DESC ,  ");
		sql.append("   R4000.REG_STATUS,  ");
		sql.append("   R4000.REG_DATE,  ");
		sql.append("   R4000.REG_CAPITAL  ");
		sql.append(" FROM TA_WS_REG4000 R4000 ");
		sql.append(" INNER JOIN (  ");
		sql.append("   SELECT OFF_CODE OFFICE_CODE,  ");
		sql.append("     OFF_NAME,  ");
		sql.append("     OFF_SHORT_NAME  ");
		sql.append("   FROM EXCISE_DEPARTMENT  ");
		sql.append("   WHERE IS_DELETED = 'N'  ");
		sql.append("     AND CONCAT (SUBSTR(OFF_CODE, 0, 2),'0000') = OFF_CODE  ");
		sql.append(" ) ED_SECTOR ON ED_SECTOR.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000')  ");
		sql.append(" INNER JOIN (  ");
		sql.append("   SELECT OFF_CODE OFFICE_CODE,  ");
		sql.append("     OFF_NAME,  ");
		sql.append("     OFF_SHORT_NAME  ");
		sql.append("   FROM EXCISE_DEPARTMENT  ");
		sql.append("   WHERE IS_DELETED = 'N'  ");
		sql.append("     AND CONCAT (SUBSTR(OFF_CODE, 0, 4),'00') = OFF_CODE  ");
		sql.append(" ) ED_AREA ON ED_AREA.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" WHERE R4000.IS_DELETED='N' ");

		if(StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" AND r4000.new_reg_id=?  ");
			params.add(formVo.getNewRegId());
		}
		return this.commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), reg4000Rowmapper);
	}

	private RowMapper<Oa07Reg4000Vo> reg4000Rowmapper = new RowMapper<Oa07Reg4000Vo>() {
		@Override
		public Oa07Reg4000Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa07Reg4000Vo vo = new Oa07Reg4000Vo();
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setDutyCode(rs.getString("DUTY_CODE"));
			vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
			vo.setSecCode(rs.getString("SEC_CODE"));
			vo.setSecDesc(rs.getString("SEC_DESC"));
			vo.setAreaCode(rs.getString("AREA_CODE"));
			vo.setAreaDesc(rs.getString("AREA_DESC"));
			vo.setRegStatus(rs.getString("REG_STATUS"));
			vo.setRegDate(rs.getString("REG_DATE"));
			vo.setRegCapital(rs.getString("REG_CAPITAL"));
			return vo;
		}
	};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TaWsInc8000M> reg8000M(Oa07FormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append(" SELECT  ");
		sql.append("     WS_INC8000_M_ID, ");
		sql.append("     NEW_REG_ID, ");
		sql.append("     CASE LENGTH(TAX_MONTH) ");
		sql.append("        WHEN 1 THEN CONCAT(TAX_YEAR,CONCAT('0',TAX_MONTH)) ");
		sql.append("        ELSE CONCAT(TAX_YEAR,TAX_MONTH) ");
		sql.append("     END TAX_MONTH, ");
		sql.append("     TAX_AMOUNT ");
		sql.append(" fROM  TA_WS_INC8000_M WHERE IS_DELETED='N' ");
		
		if(StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" and NEW_REG_ID=? ");
			params.add(formVo.getNewRegId());
		}
		if (StringUtils.isNotBlank(formVo.getMonthStart()) && StringUtils.isNotBlank(formVo.getMonthEnd())) {
			sql.append(" AND CASE LENGTH(TAX_MONTH) ");
			sql.append("        WHEN 1 THEN CONCAT(TAX_YEAR,COncat('0',TAX_MONTH)) ");
			sql.append("        ELSE CONCAT(TAX_YEAR,TAX_MONTH) ");
			sql.append("     END  BETWEEN ? AND ? ");
			params.add(formVo.getMonthStart());		//yyyyMM
			params.add(formVo.getMonthEnd());		//yyyyMM
		}
		
		sql.append(" ORDER BY CASE LENGTH(TAX_MONTH) ");
		sql.append("        WHEN 1 THEN CONCAT(TAX_YEAR,CONCAT('0',TAX_MONTH)) ");
		sql.append("        ELSE CONCAT(TAX_YEAR,TAX_MONTH) ");
		sql.append("     END DESC ");
		
		return this.commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), new BeanPropertyRowMapper(TaWsInc8000M.class));
	}
	
}
