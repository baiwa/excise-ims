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
import th.go.excise.ims.ia.vo.Int061402FilterVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;

@Repository
public class Int061402JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ws_Reg4000Vo> getDataFilter(Int061402FilterVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT d.DUTY_GROUP_NAME, r.* ");
		sql.append(" FROM TA_WS_REG4000 r ");
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP d on r.DUTY_CODE  = d.DUTY_GROUP_CODE ");
		sql.append(" WHERE r.IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND r.OFFICE_CODE = ? ");
			params.add(request.getOfficeCode());
		}
		
		if (StringUtils.isNotBlank(request.getRegDateStart())) {
			sql.append(" AND r.REG_DATE >= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
		}
		
		if (StringUtils.isNotBlank(request.getRegDateEnd())) {
			sql.append(" AND r.REG_DATE <= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
		}
		
//		if (StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
//			sql.append(" AND r.AUDIT_TXINSUR_NO  != ? ");
//			params.add(request.getAuditTxinsurNo());
//		}

		sql.append(" ORDER BY r.REG_DATE DESC");

//		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ws_Reg4000Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ws_Reg4000Vo.class));

		return datas;
	}
	
	public Integer countDatafilter(Int061402FilterVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT d.DUTY_GROUP_NAME, r.* ");
		sql.append(" FROM TA_WS_REG4000 r ");
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP d on r.DUTY_CODE  = d.DUTY_GROUP_CODE ");
		sql.append(" WHERE r.IS_DELETED = 'N' ");


		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND r.OFFICE_CODE = ? ");
			params.add(request.getOfficeCode());
		}
		
		if (StringUtils.isNotBlank(request.getRegDateStart())) {
			sql.append(" AND r.REG_DATE >= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
		}
		
		if (StringUtils.isNotBlank(request.getRegDateEnd())) {
			sql.append(" AND r.REG_DATE <= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
		}
		
//		if (StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
//			sql.append(" AND r.AUDIT_TXINSUR_NO  != ? ");
//			params.add(request.getAuditTxinsurNo());
//		}
		
		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}
	
	public String getSeqTxinsurNo() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_TXINSUR_NO_SEQ.nextval FROM DUAL ");
		
		String count = this.commonJdbcTemplate.queryForObject(sql.toString(), String.class);
		return count;
	}

}
