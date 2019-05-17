package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Repository
public class Int0602JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<WsLicfri6010> findByCriteria(Int0602FormVo vo, String strOrder) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ");
		sql.append(" FROM WS_LICFRI6010 WS ");
		sql.append(" WHERE WS.IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND WS.OFFCODE LIKE ? ");
			paramList.add(vo.getOfficeCode());
		}
		if (StringUtils.isNotBlank(vo.getLicDateFrom())) {
			sql.append(" AND WS.LIC_DATE > ? ");
			Date date = ConvertDateUtils.parseStringToDate(vo.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN));
		}
		if (StringUtils.isNotBlank(vo.getLicDateTo())) {
			sql.append(" AND WS.LIC_DATE < ? ");
			Date date = ConvertDateUtils.parseStringToDate(vo.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			paramList.add(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN));
		}
		sql.append(" ORDER BY ").append(strOrder);
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab1RowMapper);

	}

	private RowMapper<WsLicfri6010> tab1RowMapper = new RowMapper<WsLicfri6010>() {
		@Override
		public WsLicfri6010 mapRow(ResultSet rs, int rowNum) throws SQLException {
			WsLicfri6010 vo = new WsLicfri6010();
			vo.setWsLicfri6010Id(rs.getLong("WS_LICFRI6010_ID"));
			vo.setOffcode(rs.getString("OFFCODE"));
			vo.setLicType(rs.getString("LIC_TYPE"));
			vo.setLicNo(rs.getString("LIC_NO"));
			vo.setLicName(rs.getString("LIC_NAME"));
			vo.setLicFee(rs.getBigDecimal("LIC_FEE"));
			vo.setLicInterior(rs.getBigDecimal("LIC_INTERIOR"));
			vo.setLicPrice(rs.getBigDecimal("LIC_PRICE"));
			vo.setLicDate(rs.getString("LIC_DATE"));
			vo.setStartDate(rs.getString("START_DATE"));
			vo.setExpDate(rs.getString("EXP_DATE"));
			vo.setSendDate(rs.getString("SEND_DATE"));
			vo.setPrintCount(NumberUtils.toBigDecimal(rs.getString("PRINT_COUNT")));
			vo.setNid(rs.getString("NID"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setIncCode(rs.getString("INC_CODE"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setVersion(rs.getInt("VERSION"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE")));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("UPDATED_DATE")));
			return vo;
		}
	};

}
