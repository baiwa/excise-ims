package th.co.baiwa.excise.epa.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.epa.persistence.vo.Epa011DtlVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011Vo;
import th.co.baiwa.excise.epa.persistence.vo.Epa013FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa013Vo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrFormVo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrVo;
import th.co.baiwa.excise.utils.DateUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ExportCheckingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String HDR_SQL = "select * from EA_RE_05_01_HDR where 1=1 ";
	private final String DTL_SQL = "select * from EA_RE_05_01_DTL where 1=1 ";

	public long countExportCheckingData(Epa011FormVo epa011FormVo) {
		StringBuilder sql = new StringBuilder(HDR_SQL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(epa011FormVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(StringUtils.trim(epa011FormVo.getExciseId()));
		}

		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Epa011Vo> listExportCheckingData(Epa011FormVo epa011FormVo) {
		StringBuilder sql = new StringBuilder(HDR_SQL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(epa011FormVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(StringUtils.trim(epa011FormVo.getExciseId()));
		}

		String sqlLimit = OracleUtils.limitForDataTable(sql, epa011FormVo.getStart(), epa011FormVo.getLength());
		List<Epa011Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), Epa013VoRowMapper);
		return list;
	}

	private RowMapper<Epa011Vo> Epa013VoRowMapper = new RowMapper<Epa011Vo>() {
		@Override
		public Epa011Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa011Vo vo = new Epa011Vo();

			vo.setId(rs.getBigDecimal("ID"));
			vo.setExportName(rs.getString("EXPORT_NAME"));
			vo.setCheckPointDest(rs.getString("CHECK_POINT_DEST"));
			vo.setDateOut(rs.getDate("DATE_OUT"));
			vo.setDateIn(rs.getDate("DATE_IN"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setCusName(rs.getString("CUS_NAME"));
			vo.setTin(rs.getString("TIN"));
			vo.setVatNo(rs.getString("VATNO"));
			vo.setFacname(rs.getString("FACNAME"));
			vo.setCusNewRegid(rs.getString("CUS_NEW_REGID"));
			vo.setRemark(rs.getString("REMARK"));
			vo.setRoute(rs.getString("ROUTE"));
			vo.setTransportName(rs.getString("TRANSPORT_NAME"));

			vo.setDateInDisplay(DateUtils.formatDateToDDMMYYYY(rs.getDate("DATE_IN")));
			vo.setDateOutDisplay(DateUtils.formatDateToDDMMYYYY(rs.getDate("DATE_OUT")));

			return vo;
		}
	};

	public List<Epa013Vo> searchForReport(Epa013FormVo epa013FormVo) {
		// TODO Auto-generated method stub
		return null;
	}

	public long countForReport(Epa013FormVo epa013FormVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Epa011Vo getHDR(BigDecimal viewId) {
		StringBuilder sql = new StringBuilder(HDR_SQL);
		sql.append(" AND ID=? ");
		List<Epa011Vo> list = jdbcTemplate.query(sql.toString(), Epa013VoRowMapper, viewId);
		return list.isEmpty() ? new Epa011Vo() : list.get(0);
	}

	public List<Epa011DtlVo> listDetailData(Epa011FormVo epa011FormVo) {
		StringBuilder sql = new StringBuilder(DTL_SQL);
		sql.append(" AND HDR_ID = ? ");
		List<Object> params = new ArrayList<>();
		params.add(epa011FormVo.getViewId());
		String sqlLimit = OracleUtils.limitForDataTable(sql, epa011FormVo.getStart(), epa011FormVo.getLength());
		List<Epa011DtlVo> list = jdbcTemplate.query(sqlLimit, params.toArray(), Epa011DtlVoRowMapper);
		return list;
	}

	public long countDetail(Epa011FormVo epa011FormVo) {
		StringBuilder sql = new StringBuilder(DTL_SQL);
		sql.append(" AND HDR_ID = ? ");
		List<Object> params = new ArrayList<>();
		params.add(epa011FormVo.getViewId());
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	private RowMapper<Epa011DtlVo> Epa011DtlVoRowMapper = new RowMapper<Epa011DtlVo>() {
		@Override
		public Epa011DtlVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa011DtlVo vo = new Epa011DtlVo();
			vo.setId(rs.getBigDecimal("ID"));
			vo.setHdrId(rs.getBigDecimal("HDR_ID"));
			vo.setBrandName(rs.getString("BRAND_NAME"));
			vo.setProductName(rs.getString("PRODUCT_NAME"));
			vo.setModelName(rs.getString("MODEL_NAME"));
			vo.setGoodsSize(rs.getBigDecimal("GOODS_SIZE"));
			vo.setGoodsNum(rs.getBigDecimal("GOODS_NUM"));
			vo.setRetailPrice(rs.getBigDecimal("RETAIL_PRICE"));
			vo.setTaxvalUnit(rs.getBigDecimal("TAXVAL_UNIT"));
			vo.setTaxqtyUnit(rs.getBigDecimal("TAXQTY_UNIT"));
			vo.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
			
			return vo;
		}
	};

	public Epa011DtlVo getDTL(BigDecimal dtlId) {
		StringBuilder sql = new StringBuilder(DTL_SQL);
		sql.append(" AND ID=? ");
		List<Epa011DtlVo> list = jdbcTemplate.query(sql.toString(), Epa011DtlVoRowMapper,dtlId);
		return list.isEmpty()? new Epa011DtlVo() : list.get(0) ;
	}
	
	public void clear(BigDecimal dtlId , String invType) {
		String sql = "delete from EA_RE_05_01_INV_HDR where INV_TYPE=? AND DTL_ID=? ";
		int row = jdbcTemplate.update(sql,invType,dtlId);
	}

	public void insertInvHDR(InvhdrFormVo invhdrFormVo,String invType) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT                                   ");
		sql.append(" INTO EA_RE_05_01_INV_HDR                 ");
		sql.append("   (                                      ");
		sql.append("     ID,                                  ");
		sql.append("     DTL_ID,                              ");
		sql.append("     EXPORT_NAME,                         ");
		sql.append("     EXCISE_SRC,                          ");
		sql.append("     CHECK_POINT_DEST,                    ");
		sql.append("     EXCISE_DEST,                         ");
		sql.append("     DATE_OUT,                            ");
		sql.append("     PRODUCT_NAME,                        ");
		sql.append("     GOODS_NUM,                           ");
		sql.append("     TRANSPORT_NAME,                      ");
		sql.append("     ROUTE,                               ");
		sql.append("     CHECKING_RESULT,                     ");
		sql.append("     REMARK,                              ");
		sql.append("     REF_NO,                              ");
		sql.append("     INV_TYPE                             ");
		sql.append("   )                                      ");
		sql.append("   VALUES                                 ");
		sql.append("   (                                      ");
		sql.append("     EA_RE_05_01_INV_HDR_SEQ.nextval,     ");
		sql.append("     ?  ,                                 ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?,                                   ");
		sql.append("     ?                                    ");
		sql.append("   )                                      ");
		
		InvhdrVo hdr = invhdrFormVo.getRightForm();
		InvhdrVo leftform = invhdrFormVo.getLeftFrom();
		int row = jdbcTemplate.update(sql.toString(),new Object[] {
				invhdrFormVo.getDtlId(),
				hdr.getExportName(),
				hdr.getExciseSrc(),
				hdr.getCheckPointDest(),
				hdr.getExciseDest(),
				DateUtils.parseDateToDDMMYYYY(hdr.getDateOut()) ,
				hdr.getProductName(),
				hdr.getGoodsNum(),
				hdr.getTransportName(),
				hdr.getRoute(),
				leftform.getCheckingResult(),
				leftform.getRemark(),
				leftform.getRefNo(),
				invType
		});

	}

	public InvhdrVo getINVHDR(BigDecimal bigDecimal,String invType) {

		String sql = "select * from EA_RE_05_01_INV_HDR where INV_TYPE=? AND DTL_ID=? ";
		List<InvhdrVo> list = jdbcTemplate.query(sql, InvhdrVoRowMapper,invType, bigDecimal);
		
		return list.isEmpty() ? new InvhdrVo() : list.get(0);
	}
	
	private RowMapper<InvhdrVo> InvhdrVoRowMapper = new RowMapper<InvhdrVo>() {
		@Override
		public InvhdrVo mapRow(ResultSet rs, int arg1) throws SQLException {
			InvhdrVo vo = new InvhdrVo();
			vo.setExportName(rs.getString("EXPORT_NAME"));
			vo.setExciseSrc(rs.getString("EXCISE_SRC"));
			vo.setCheckPointDest(rs.getString("CHECK_POINT_DEST"));
			vo.setExciseDest(rs.getString("EXCISE_DEST"));
			vo.setDateOut(DateUtils.formatDateToDDMMYYYY(rs.getDate("DATE_OUT")));
			vo.setProductName(rs.getString("PRODUCT_NAME"));
			vo.setGoodsNum(rs.getBigDecimal("GOODS_NUM"));
			vo.setTransportName(rs.getString("TRANSPORT_NAME"));
			vo.setRoute(rs.getString("ROUTE"));
			vo.setCheckingResult(rs.getString("CHECKING_RESULT"));
			vo.setRemark(rs.getString("REMARK"));
			vo.setRefNo(rs.getString("REF_NO"));
			vo.setInvType(rs.getString("INV_TYPE"));
			return vo;
		}
	};

	public List<Epa011DtlVo> listDetailDataFactory(Epa011FormVo epa011FormVo) {
		StringBuilder sql = new StringBuilder(" select d.* from EA_RE_05_01_DTL d " + 
				" left join EA_RE_05_01_INV_HDR ih on d.id=ih.DTL_ID " + 
				" where d.HDR_ID=? and ih.CHECKING_RESULT='Y' and ih.INV_TYPE='1' ");
		List<Object> params = new ArrayList<>();
		params.add(epa011FormVo.getViewId());
		String sqlLimit = OracleUtils.limitForDataTable(sql, epa011FormVo.getStart(), epa011FormVo.getLength());
		List<Epa011DtlVo> list = jdbcTemplate.query(sqlLimit, params.toArray(), Epa011DtlVoRowMapper);
		return list;
	
	}

	public long countDetailFactory(Epa011FormVo epa011FormVo) {
		StringBuilder sql = new StringBuilder(" select d.* from EA_RE_05_01_DTL d " + 
				" left join EA_RE_05_01_INV_HDR ih on d.id=ih.DTL_ID " + 
				" where d.HDR_ID=? and ih.CHECKING_RESULT='Y' and ih.INV_TYPE='1' ");
		List<Object> params = new ArrayList<>();
		params.add(epa011FormVo.getViewId());
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

}
