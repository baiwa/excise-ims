package th.co.baiwa.excise.ia.persistence.repository.tax.receipt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.tax.TaxReceipt;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceiptVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IaTaxReceiptRepositoryImpl implements IaTaxReceiptRepositoryCustom {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate template;

	private String SQL = " SELECT L.* FROM IA_TAX_RECEIPT L WHERE 1=1 ";

	private String _SQL = " select distinct l.OFFICE_CODE CODE from IA_TAX_RECEIPT l where 1=1 ";

	@Override
	public List<TaxReceipt> findByIaTaxReceipt(IaTaxReceiptVo iaTaxReceipt) {
		logger.info("IaTaxReceiptRepositoryImpl::findByIaTaxReceipt");
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQL);
		if (BeanUtils.isNotEmpty(iaTaxReceipt.getOfficeCode())) {
			sql.append(" AND L.OFFICE_CODE = ? ");
			param.add(iaTaxReceipt.getOfficeCode());
		}
		if (BeanUtils.isNotEmpty(iaTaxReceipt.getDateType())) {
			sql.append(" AND L.DATE_TYPE = ? ");
			param.add(iaTaxReceipt.getDateType());
		}
		if (BeanUtils.isNotEmpty(iaTaxReceipt.getDateFrom()) && BeanUtils.isNotEmpty(iaTaxReceipt.getDateTo())) {
			sql.append(" AND L.RECEIPT_DATE BETWEEN ? AND ? ");
			String from = thaiDate(iaTaxReceipt.getDateFrom());
			String to = thaiDate(iaTaxReceipt.getDateTo());
			param.add(from);
			param.add(to);
		}

		List<TaxReceipt> result = template.query(sql.toString(), param.toArray(), row);
		return result;
	}

	@Override
	public int coundByDateFrom(String officeCode, String dateType, String dateFrom) {
		logger.info("IaTaxReceiptRepositoryImpl::coundByDateFrom");
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(_SQL);
		sql.append(" AND l.OFFICE_CODE = ? ");
		sql.append(" AND l.DATE_TYPE = ? ");
		sql.append(" AND l.RECEIPT_DATE LIKE ? ");
		param.add(officeCode);
		param.add(dateType);
		param.add(thaiDate(dateFrom).substring(0,6) + '%');
		List<String> result = template.query(sql.toString(), param.toArray(), count);
		return result.size();
	}

	@Override
	public int coundByDateTo(String officeCode, String dateType, String dateTo) {
		logger.info("IaTaxReceiptRepositoryImpl::coundByDateTo");
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(_SQL);
		sql.append(" AND l.OFFICE_CODE = ? ");
		sql.append(" AND l.DATE_TYPE = ? ");
		sql.append(" AND l.RECEIPT_DATE LIKE ? ");
		param.add(officeCode);
		param.add(dateType);
		param.add(thaiDate(dateTo).substring(0,6) + '%');
		List<String> result = template.query(sql.toString(), param.toArray(), count);
		return result.size();
	}

	private RowMapper<String> count = new RowMapper<String>() {

		@Override
		public String mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("CODE");
		}

	};

	private RowMapper<TaxReceipt> row = new RowMapper<TaxReceipt>() {

		@Override
		public TaxReceipt mapRow(ResultSet rs, int arg1) throws SQLException {
			TaxReceipt tax = new TaxReceipt();
			tax.setTaxReceiptId(rs.getLong("TAX_RECEIPT_ID"));
			tax.setReceiptDate(rs.getString("RECEIPT_DATE"));
			tax.setDepositDate(rs.getString("DEPOSIT_DATE"));
			tax.setSendDate(rs.getString("SEND_DATE"));
			tax.setIncomeName(rs.getString("INCOME_NAME"));
			tax.setReceiptNo(rs.getString("RECEIPT_NO"));
			tax.setNetTaxAmount(rs.getBigDecimal("NET_TAX_AMOUNT"));
			tax.setNetLocAmount(rs.getBigDecimal("NET_LOC_AMOUNT"));
			tax.setLocOthAmount(rs.getBigDecimal("LOC_OTH_AMOUNT"));
			tax.setLocExpAmount(rs.getBigDecimal("LOC_EXP_AMOUNT"));
			tax.setOlderFundAmount(rs.getBigDecimal("OLDER_FUND_AMOUNT"));
			tax.setTpbsFundAmount(rs.getBigDecimal("TPBS_FUND_AMOUNT"));
			tax.setSendAmount(rs.getBigDecimal("SEND_AMOUNT"));
			tax.setStampAmount(rs.getBigDecimal("STAMP_AMOUNT"));
			tax.setCustomAmount(rs.getBigDecimal("CUSTOM_AMOUNT"));
			tax.setCreatedBy(rs.getString("CREATED_BY"));
			tax.setCreatedDate(rs.getDate("CREATED_DATE"));
			tax.setUpdatedBy(rs.getString("UPDATED_BY"));
			tax.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			tax.setIsDeleted(rs.getString("IS_DELETED"));
			tax.setVersion(rs.getInt("VERSION"));
			tax.setOfficeCode(rs.getString("OFFICE_CODE"));
			tax.setDateType(rs.getString("DATE_TYPE"));
			tax.setCheckedAmount(rs.getBigDecimal("CHECKED_AMOUNT"));
			tax.setTaxPrintNo(rs.getString("TAX_PRINT_NO"));
			return tax;
		}

	};

	private String thaiDate(String date) {
		Date _date = DateConstant.convertStrToDate(date, "yyyyMMdd", DateConstant.LOCAL_EN);
		String result = DateConstant.convertDateToStr(_date, "yyyyMMdd", DateConstant.LOCAL_TH);
		return result;
	}
	
	
	@Override
	public List<IaTaxReceiptVo> summaryTaxReceipt(IaTaxReceiptVo iaTaxReceipt) {
		logger.info("IaTaxReceiptRepositoryImpl::findByIaTaxReceipt");
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT L.INCOME_CODE , L.INCOME_NAME ,count(1) COUNT, sum(L.NET_TAX_AMOUNT) NET_TAX_AMOUNT "); 
		sql.append(" , sum(L.NET_LOC_AMOUNT) NET_LOC_AMOUNT ");
		sql.append(" , sum(L.LOC_OTH_AMOUNT) LOC_OTH_AMOUNT ");
		sql.append(" , sum(L.LOC_EXP_AMOUNT) LOC_EXP_AMOUNT ");
		sql.append(" , sum(L.CUSTOM_AMOUNT) CUSTOM_AMOUNT ");
		sql.append(" , sum(L.STAMP_AMOUNT) STAMP_AMOUNT ");
		sql.append(" , sum(L.OLDER_FUND_AMOUNT) OLDER_FUND_AMOUNT ");
		sql.append(" , sum(L.SEND_AMOUNT) SEND_AMOUNT ");
		sql.append(" FROM IA_TAX_RECEIPT L ");
		sql.append(" WHERE L.IS_DELETED = '").append(FLAG.N_FLAG).append("' ");
		
		if (BeanUtils.isNotEmpty(iaTaxReceipt.getDateFrom()) && BeanUtils.isNotEmpty(iaTaxReceipt.getDateTo())) {
			sql.append(" AND L.RECEIPT_DATE BETWEEN ? AND ? ");
			String from = thaiDate(iaTaxReceipt.getDateFrom());
			String to = thaiDate(iaTaxReceipt.getDateTo());
			param.add(from);
			param.add(to);
		}
		if (BeanUtils.isNotEmpty(iaTaxReceipt.getOfficeCode())) {
			sql.append(" AND L.OFFICE_CODE = ? ");
			param.add(iaTaxReceipt.getOfficeCode());
		}
		if (BeanUtils.isNotEmpty(iaTaxReceipt.getDateType())) {
			sql.append(" AND L.DATE_TYPE = ? ");
			param.add(iaTaxReceipt.getDateType());
		}
		sql.append(" GROUP BY L.INCOME_CODE , L.INCOME_NAME ");
		List<IaTaxReceiptVo> result = template.query(sql.toString(), param.toArray(), rowMappingSummary);
		return result;
	}
	
	private RowMapper<IaTaxReceiptVo> rowMappingSummary = new RowMapper<IaTaxReceiptVo>() {

		@Override
		public IaTaxReceiptVo mapRow(ResultSet rs, int arg1) throws SQLException {
			IaTaxReceiptVo tax = new IaTaxReceiptVo();
			tax.setIncomeName(rs.getString("INCOME_NAME"));
			tax.setCount(rs.getLong("COUNT"));
			tax.setNetTaxAmount(rs.getBigDecimal("NET_TAX_AMOUNT"));
			tax.setNetLocAmount(rs.getBigDecimal("NET_LOC_AMOUNT"));
			tax.setLocOthAmount(rs.getBigDecimal("LOC_OTH_AMOUNT"));
			tax.setLocExpAmount(rs.getBigDecimal("LOC_EXP_AMOUNT"));
			tax.setOlderFundAmount(rs.getBigDecimal("OLDER_FUND_AMOUNT"));
			tax.setSendAmount(rs.getBigDecimal("SEND_AMOUNT"));
			tax.setStampAmount(rs.getBigDecimal("STAMP_AMOUNT"));
			tax.setCustomAmount(rs.getBigDecimal("CUSTOM_AMOUNT"));
			tax.setIncomeCode(rs.getString("INCOME_CODE"));
			return tax;
		}

	};
	
	
	

}
