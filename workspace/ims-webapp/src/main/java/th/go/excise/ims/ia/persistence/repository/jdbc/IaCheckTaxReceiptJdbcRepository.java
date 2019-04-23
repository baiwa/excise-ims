package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int060501FormVo;
import th.go.excise.ims.ia.vo.Int060501Vo;

@Repository
public class IaCheckTaxReceiptJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int060501Vo> fillterDate(Int060501FormVo res) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT * FROM IA_CHECK_TAX_RECEIPT WHERE IS_DELETED='N' ");
		if (StringUtils.isNoneEmpty(res.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE = ? ");
			params.add(res.getOfficeCode());
		}
//		if (StringUtils.isNoneEmpty(res.getStartDate())) {
//			sql.append(" AND RECEIPT_DATE >= TO_DATE(?, 'dd/mm/yyyy') ");
//			Date startDate = ConvertDateUtils.parseStringToDate(res.getStartDate(), ConvertDateUtils.DD_MM_YYYY,
//					ConvertDateUtils.LOCAL_TH);
//
//			params.add(ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.DD_MM_YYYY,
//					ConvertDateUtils.LOCAL_EN));
//		}
//		if (StringUtils.isNoneEmpty(res.getEndDate())) {
//			sql.append(" AND RECEIPT_DATE <=  TO_DATE(?, 'dd/mm/yyyy') ");
//			Date endDate = ConvertDateUtils.parseStringToDate(res.getEndDate(), ConvertDateUtils.DD_MM_YYYY,
//					ConvertDateUtils.LOCAL_TH);
//			params.add(ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.DD_MM_YYYY,
//					ConvertDateUtils.LOCAL_EN));
//		}
		if (StringUtils.isNoneEmpty(res.getStartDate())) {
			sql.append(" AND RECEIPT_DATE >= ? ");
			Date startDate = ConvertDateUtils.parseStringToDate(res.getStartDate(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH);
			params.add(startDate);
		}
		if (StringUtils.isNoneEmpty(res.getEndDate())) {
			sql.append(" AND RECEIPT_DATE <= ? ");
			Date endDate = ConvertDateUtils.parseStringToDate(res.getEndDate(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH);
			params.add(endDate);
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int060501Vo> dataRes = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int060501Vo.class));
		return dataRes;
	}
}
