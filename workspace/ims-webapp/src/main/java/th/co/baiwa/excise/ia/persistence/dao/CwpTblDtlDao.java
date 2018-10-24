package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;
import th.co.baiwa.excise.ia.persistence.entity.CwpTblDtl;

@Repository
public class CwpTblDtlDao {
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<CwpTblDtl> queryFindT(CwpTblDtl en) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT CWP_TBL_DTL_ID, D.LEDGER_ACCOUNT_NAME, D.CARRY_FORWARD ");
		sql.append(" FROM IA_CWP_TBL_DTL D ");
		sql.append(" WHERE D.CWP_TBL_HDR_ID = ? ");
		sql.append(" AND D.LEDGER_ACCOUNT_NAME LIKE 'T%' ");
		valueList.add(en.getCwpTblHdrId());

		List<CwpTblDtl> dropdownT = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingFindDropdownT);
		return dropdownT;
	}

	private RowMapper<CwpTblDtl> fieldMappingFindDropdownT = new RowMapper<CwpTblDtl>() {
		@Override
		public CwpTblDtl mapRow(ResultSet rs, int arg1) throws SQLException {
			CwpTblDtl dtlVo = new CwpTblDtl();
			dtlVo.setCwpTblDtlId(rs.getLong("CWP_TBL_DTL_ID"));
			dtlVo.setLedgerAccountName(rs.getString("LEDGER_ACCOUNT_NAME"));
			dtlVo.setCarryForward(rs.getBigDecimal("CARRY_FORWARD"));
			return dtlVo;
		}
	};
}
