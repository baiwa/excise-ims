package th.go.excise.ims.ws.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int0609TableVo;
import th.go.excise.ims.ia.vo.SearchVo;

@Repository
public class WsGfr01051JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int0609TableVo> findByFilter(SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT G.TRN_DATE, G.GF_DATE, G.OFFCODE, G.ACTCOST_CENT, G.GF_REF_NO, SUM(G.CNT) SUM_CNT, SUM(G.TOTAL_AMT) TOTAL_AMT, ");
		sql.append(" 	SUM(I.SUM1 + I.SUM2) SUM1_2, SUM(I.SUM4 + I.SUM5) SUM4_5, SUM(I.SUM7) SUM7, SUM(G.TOTAL_SEND_AMT) TOTAL_SEND_AMT, SUM(I.SUM4) SUM4 ");
		sql.append(" FROM WS_GFR0105_1 G ");
		sql.append(" INNER JOIN WS_INCR0003 I ");
		sql.append(" 	ON G.TRN_DATE = I.TRN_DATE ");
		sql.append(" WHERE 1 = 1 ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND G.OFFCODE = ? ");
			params.add(request.getOfficeCode());
		}

		if (StringUtils.isNotBlank(request.getPeriodMonth())) {
			sql.append(" AND TO_CHAR(G.TRN_DATE, 'MM/YYYY') = ? ");
			params.add(request.getPeriodMonth());
		}

		if (StringUtils.isNotBlank(request.getIncomeCode())) {
			sql.append(" AND I.INC_CODE = ? ");
			params.add(request.getIncomeCode());
		}

		sql.append(" GROUP BY G.TRN_DATE, G.GF_DATE, G.OFFCODE, G.ACTCOST_CENT, G.GF_REF_NO ");
		sql.append(" ORDER BY G.TRN_DATE ");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Int0609TableVo>() {
			@Override
			public Int0609TableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int0609TableVo vo = new Int0609TableVo();
				vo.setTrnDate(rs.getDate("TRN_DATE"));
				vo.setGfDate(rs.getDate("GF_DATE"));
				vo.setOffcode(rs.getString("OFFCODE"));
				vo.setActcostCent(rs.getString("ACTCOST_CENT"));
				vo.setGfRefNo(rs.getString("GF_REF_NO"));
				vo.setCnt(rs.getBigDecimal("SUM_CNT"));
				vo.setTotalAmt(rs.getBigDecimal("TOTAL_AMT"));
				vo.setSum1Sum2(rs.getBigDecimal("SUM1_2"));
				vo.setSum4Sum5(rs.getBigDecimal("SUM4_5"));
				vo.setSum7(rs.getBigDecimal("SUM7"));
				vo.setTotalSendAmt(rs.getBigDecimal("TOTAL_SEND_AMT"));
				vo.setSum4(rs.getBigDecimal("SUM4"));
				return vo;
			}
		});
	}

	public List<Int0609TableVo> summaryByResult(SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT SUM(SUM_CNT) TOTAL_CNT, SUM(TOTAL_AMT) SUM_TOTAL_AMT, SUM(SUM1_2) TOTAL_SUM1_2, SUM(SUM4_5) TOTAL_SUM4_5, SUM(SUM7) TOTAL_SUM7, SUM(TOTAL_SEND_AMT) TOTAL_SEND_AMT ");
		sql.append(" FROM ");
		sql.append(" ( ");
		sql.append(" 	SELECT SUM(G.CNT) SUM_CNT ,SUM(G.TOTAL_AMT) TOTAL_AMT, SUM(I.SUM1 + I.SUM2) SUM1_2, SUM(I.SUM4 + I.SUM5) SUM4_5, SUM(I.SUM7) SUM7, SUM(G.TOTAL_SEND_AMT) TOTAL_SEND_AMT ");
		sql.append("	 FROM WS_GFR0105_1 G ");
		sql.append(" 	INNER JOIN WS_INCR0003 I ");
		sql.append(" 		ON G.TRN_DATE = I.TRN_DATE ");
		sql.append("	 WHERE 1 = 1 ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND G.OFFCODE = ? ");
			params.add(request.getOfficeCode());
		}

		if (StringUtils.isNotBlank(request.getPeriodMonth())) {
			sql.append(" AND TO_CHAR(G.TRN_DATE, 'MM/YYYY') = ? ");
			params.add(request.getPeriodMonth());
		}
		sql.append(" ) H ");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Int0609TableVo>() {
			@Override
			public Int0609TableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int0609TableVo vo = new Int0609TableVo();
				vo.setCnt(rs.getBigDecimal("TOTAL_CNT"));
				vo.setTotalAmt(rs.getBigDecimal("SUM_TOTAL_AMT"));
				vo.setSum1Sum2(rs.getBigDecimal("TOTAL_SUM1_2"));
				vo.setSum4Sum5(rs.getBigDecimal("TOTAL_SUM4_5"));
				vo.setSum7(rs.getBigDecimal("TOTAL_SUM7"));
				vo.setTotalSendAmt(rs.getBigDecimal("TOTAL_SEND_AMT"));
				return vo;
			}
		});
	}
}
