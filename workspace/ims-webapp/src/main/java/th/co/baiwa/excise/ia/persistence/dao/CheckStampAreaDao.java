package th.co.baiwa.excise.ia.persistence.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
import th.co.baiwa.excise.utils.OracleUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CheckStampAreaDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = "SELECT * FROM IA_STAMP_DETAIL WHERE IS_DELETED='N' ";

	public Long count(Int0511FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

        if(StringUtils.isNotBlank(formVo.getSector())){
            sql.append(" AND EXCISE_DEPARTMENT=? ");
            params.add(StringUtils.trim(formVo.getSector()));
        }
        if (StringUtils.isNotBlank(formVo.getArea())){
            sql.append(" AND EXCISE_REGION=? ");
            params.add(StringUtils.trim(formVo.getArea()));
        }
        if (StringUtils.isNotBlank(formVo.getBranch())){
            sql.append(" AND EXCISE_DISTRICT=? ");
            params.add(StringUtils.trim(formVo.getBranch()));
        }
        if (StringUtils.isNotBlank(formVo.getDateForm()) && StringUtils.isNotBlank(formVo.getDateTo())){
            sql.append(" AND TO_CHAR(DATE_OF_PAY,'YYYYMMDD') BETWEEN ? AND ?");
            params.add(formVo.getDateForm());
            params.add(formVo.getDateTo());
        }

		sql.append(" ORDER BY CREATED_DATE DESC ");
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Int0511Vo> findAll(Int0511FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
        if(StringUtils.isNotBlank(formVo.getSector())){
            sql.append(" AND EXCISE_DEPARTMENT=? ");
            params.add(StringUtils.trim(formVo.getSector()));
        }
        if (StringUtils.isNotBlank(formVo.getArea())){
            sql.append(" AND EXCISE_REGION=? ");
            params.add(StringUtils.trim(formVo.getArea()));
        }
        if (StringUtils.isNotBlank(formVo.getBranch())){
            sql.append(" AND EXCISE_DISTRICT=? ");
            params.add(StringUtils.trim(formVo.getBranch()));
        }
        if (StringUtils.isNotBlank(formVo.getDateForm()) && StringUtils.isNotBlank(formVo.getDateTo())){
            sql.append(" AND TO_CHAR(DATE_OF_PAY,'YYYYMMDD') BETWEEN ? AND ?");
            params.add(formVo.getDateForm());
            params.add(formVo.getDateTo());
        }
        sql.append(" ORDER BY CREATED_DATE DESC ");
		List<Int0511Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), stamRowmapper);
		return list;

	}
	public List<Int0511Vo> exportFile(Int0511FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
        if(StringUtils.isNotBlank(formVo.getSector())){
            sql.append(" AND EXCISE_DEPARTMENT=? ");
            params.add(StringUtils.trim(formVo.getSector()));
        }
        if (StringUtils.isNotBlank(formVo.getArea())){
            sql.append(" AND EXCISE_REGION=? ");
            params.add(StringUtils.trim(formVo.getArea()));
        }
        if (StringUtils.isNotBlank(formVo.getBranch())){
            sql.append(" AND EXCISE_DISTRICT=? ");
            params.add(StringUtils.trim(formVo.getBranch()));
        }
        if (StringUtils.isNotBlank(formVo.getDateForm()) && StringUtils.isNotBlank(formVo.getDateTo())){
            sql.append(" AND TO_CHAR(DATE_OF_PAY,'YYYYMMDD') BETWEEN ? AND ?");
            params.add(formVo.getDateForm());
            params.add(formVo.getDateTo());
        }
        sql.append(" ORDER BY CREATED_DATE DESC ");
		List<Int0511Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), stamRowmapper);
		return list;

	}
	
	
	

	private RowMapper<Int0511Vo> stamRowmapper = new RowMapper<Int0511Vo>() {
		@Override
		public Int0511Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0511Vo vo = new Int0511Vo();

			vo.setDateOfPay(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("DATE_OF_PAY")));
			vo.setStatus(rs.getString("STATUS"));			
			vo.setBookNumberWithdrawStamp(rs.getString("BOOK_NUMBER_WITHDRAW_STAMP"));
			vo.setDateWithdrawStamp(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("DATE_WITHDRAW_STAMP")));
			vo.setBookNumberDeliverStamp(rs.getString("BOOK_NUMBER_DELIVER_STAMP"));
			vo.setDateDeliverStamp(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("DATE_DELIVER_STAMP")));
			vo.setFivePartNumber(rs.getString("FIVE_PART_NUMBER"));
			vo.setFivePartDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("FIVE_PART_DATE")));
			vo.setStampCheckDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("STAMP_CHECK_DATE")));
			vo.setStampChecker(rs.getString("STAMP_CHECKER"));
			vo.setStampBrand(rs.getString("STAMP_BRAND"));
			vo.setNumberOfBook(rs.getBigDecimal("NUMBER_OF_BOOK"));
			vo.setNumberOfStamp(rs.getInt("NUMBER_OF_STAMP"));
			vo.setValueOfStampPrinted(rs.getBigDecimal("VALUE_OF_STAMP_PRINTED"));
			vo.setSumOfValue(rs.getBigDecimal("SUM_OF_VALUE"));
			vo.setSerialNumber(rs.getString("SERIAL_NUMBER"));
			vo.setNote(rs.getString("NOTE"));			
			vo.setWorkSheetDetailId(rs.getString("WORK_SHEET_DETAIL_ID"));			
			vo.setStampType(rs.getString("STAMP_TYPE"));
			vo.setTaxStamp(rs.getBigDecimal("TAX_STAMP"));
			vo.setStampCodeStart(rs.getString("STAMP_CODE_START"));
			vo.setStampCodeEnd(rs.getString("STAMP_CODE_END"));
			vo.setFileName(rs.getString("FILE_NAME"));
			
			return vo;
		}
	};

	public List<LabelValueBean> sector() {
		String SQL = "SELECT * FROM Sys_Lov where TYPE='SECTOR_VALUE' and LOV_ID_MASTER is null ORDER BY SUB_TYPE ASC";
		return jdbcTemplate.query(SQL, SoctorRowmapper);
	}

	private RowMapper<LabelValueBean> SoctorRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("VALUE1"), rs.getString("LOV_ID"));
		}
	};

	public List<LabelValueBean> area(String id) {
		String SQL = "SELECT * FROM SYS_LOV WHERE LOV_ID_MASTER=? AND TYPE='SECTOR_VALUE' ORDER BY SUB_TYPE ASC";
		return jdbcTemplate.query(SQL, new Object[] { id }, areaRowmapper);
	}

	private RowMapper<LabelValueBean> areaRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("SUB_TYPE_DESCRIPTION"), rs.getString("LOV_ID"));
		}
	};
}
