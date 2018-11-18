package th.co.baiwa.excise.ia.persistence.dao;

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
import th.co.baiwa.excise.ia.persistence.vo.Int0615FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0615Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class CheckMoneyWithdrawaDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String SQL = "SELECT T1.* FROM ("
			+ "  SELECT IME.ID,IME.FULL_NAME NAME,IME.POSITION,IME.TOTAL_MONEY MONEY,TO_CHAR(IME.CREATED_DATE,'YYYYMM') CREATED_DATE, "
			+ "  CASE  " + "     WHEN IME.IS_DELETED='N' THEN '1' " + "     ELSE '1' " + "     END FLAG "
			+ "  FROM IA_MEDICAL_WELFARE IME WHERE AFFILIATION = ? AND TO_CHAR(IME.CREATED_DATE,'YYYYMM') BETWEEN ? AND ? "
			+ "  UNION " + "   "
			+ "  SELECT IRH.RENT_HOUSE_ID ID, IRH.NAME , IRH.POSITION, IRH.TOTAL_WITHDRAW MONEY,TO_CHAR(IRH.CREATED_DATE,'YYYYMM') CREATED_DATE,  "
			+ "  CASE  " + "     WHEN IRH.IS_DELETED='N' THEN '2' " + "     ELSE '2' " + "     END FLAG "
			+ "  FROM IA_RENT_HOUSE IRH WHERE AFFILIATION = ? AND TO_CHAR(IRH.CREATED_DATE,'YYYYMM') BETWEEN ? AND ? "
			+ "   " + "  UNION " + "   "
			+ "  SELECT ITF.ID, ITF.NAME ,ITF.PITION POSITION, ITF.SUM_AMOUNT MONEY,TO_CHAR(ITF.CREATED_DATE,'YYYYMM') CREATED_DATE, "
			+ "  CASE  " + "     WHEN ITF.IS_DELETED='N' THEN '3' " + "     ELSE '3' " + "     END FLAG "
			+ "     FROM IA_TUITION_FEE ITF WHERE  BELONG = ? AND TO_CHAR(ITF.CREATED_DATE,'YYYYMM') BETWEEN ? AND ? "
			+ "  )T1 WHERE 1=1 ";

	public Long count(Int0615FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

		params.add(formVo.getSector());
		params.add(formVo.getStartDate());
		params.add(formVo.getEndDate());

		params.add(formVo.getSector());
		params.add(formVo.getStartDate());
		params.add(formVo.getEndDate());

		params.add(formVo.getSector());
		params.add(formVo.getStartDate());
		params.add(formVo.getEndDate());

		if (StringUtils.isNotBlank(formVo.getMoney())) {
			sql.append(" AND MONEY >= ?");
			params.add(formVo.getMoney());
		}

		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Int0615Vo> findAll(Int0615FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

		params.add(formVo.getSector());
		params.add(formVo.getStartDate());
		params.add(formVo.getEndDate());

		params.add(formVo.getSector());
		params.add(formVo.getStartDate());
		params.add(formVo.getEndDate());

		params.add(formVo.getSector());
		params.add(formVo.getStartDate());
		params.add(formVo.getEndDate());
		
		if (StringUtils.isNotBlank(formVo.getMoney())) {
			sql.append(" AND MONEY >= ?");
			params.add(formVo.getMoney());
		}
		sql.append(" ORDER BY T1.NAME ASC");

		List<Int0615Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), rowRowmapper);
		return list;

	}

	private RowMapper<Int0615Vo> rowRowmapper = new RowMapper<Int0615Vo>() {
		@Override
		public Int0615Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0615Vo vo = new Int0615Vo();

			// ==> convert date
			Date date = DateConstant.convertStrToDate(rs.getString("CREATED_DATE"), "yyyyMM", DateConstant.LOCAL_EN);
			String strdate = DateConstant.convertDateToStr(date, "MM/yyyy", DateConstant.LOCAL_TH);

			vo.setId(rs.getString("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setPosition(rs.getString("POSITION"));
			vo.setMoney(rs.getString("MONEY"));
			vo.setCreatedDate(strdate);
			vo.setFlag(rs.getString("FLAG"));

			return vo;
		}
	};
}
