package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.dao.BatchSetter;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetDetail;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class PlanWorksheetDetailDao{
	
	private Logger logger = LoggerFactory.getLogger(PlanWorksheetDetailDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;

	
	public List<PlanWorksheetDetail> queryPlanWorksheetDetailCriteria(PlanWorksheetDetail criteria) {
		logger.info("PlanWorksheetDetailDao.queryPlanWorksheetDetailCriteria");
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" select * from TA_PLAN_WORK_SHEET_DETAIL ");
		sql.append(" where 1 = 1 ");

		if (BeanUtils.isNotEmpty(criteria.getAnalysNumber())) {
			sql.append(" and ANALYS_NUMBER = ? ");
			valueList.add(criteria.getAnalysNumber());

		}
		if (BeanUtils.isNotEmpty(criteria.getExciseId())) {
			sql.append(" and EXCISE_ID = ? ");
			valueList.add(criteria.getExciseId());
			
		}

		List<PlanWorksheetDetail> PlanWorksheetDetailList = jdbcTemplate.query(sql.toString(), valueList.toArray(),
				fieldMapping);
		return PlanWorksheetDetailList;

	}

	public void insertPlanWorksheetDetail(List<PlanWorksheetDetail> valueList) {
		logger.info("PlanWorksheetDetailDao.insertPlanWorksheetDetail");
		//inti SQL for insert to database
		StringBuilder sql = new StringBuilder(" Insert into EXCISEADM.TA_PLAN_WORK_SHEET_DETAIL (WORK_SHEET_DETAIL_ID,EXCISE_ID,ANALYS_NUMBER,MONTH,YEAR,AMOUNT,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) "); 
		sql.append(" values (TA_PLAN_WORK_SHEET_DETAIL_SEQ.nextval,?,?,?,?,?,?,?,?,?) ");
		
		//for to set Object
		List<Object[]> objArrayList = new ArrayList<Object[]>();
		for (PlanWorksheetDetail planWorksheetDetail : valueList) {
			objArrayList.add(objArrayList(planWorksheetDetail));
		}
		jdbcTemplate.batchUpdate(sql.toString(), objArrayList);
	}
	
	
	public void insertPlanWorksheetDetail(final List<PlanWorksheetDetail> detailList, int executeSize) throws SQLException {
		StringBuilder sql = new StringBuilder(" Insert into EXCISEADM.TA_PLAN_WORK_SHEET_DETAIL (WORK_SHEET_DETAIL_ID,EXCISE_ID,ANALYS_NUMBER,MONTH,YEAR,AMOUNT,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) "); 
		sql.append(" values (TA_PLAN_WORK_SHEET_DETAIL_SEQ.nextval,?,?,?,?,?,?,?,?,?) ");
		
		 commonJdbcDao.executeBatch(sql.toString(), new BatchSetter<PlanWorksheetDetail>() {
			@Override
			public List<PlanWorksheetDetail> getBatchObjectList() {
				return detailList;
			}
			
			@Override
			public Object[] toObjects(PlanWorksheetDetail obj) {
				String username = UserLoginUtils.getCurrentUsername();
				Date date = new Date();
				return new Object[] {
						obj.getExciseId(),
						obj.getAnalysNumber(),
						obj.getMonth(),
						obj.getYear(),
						obj.getAmount(),
						username,
						date,
						obj.getUpdatedBy(),
						obj.getUpdatedDate()
				};
			}
			
			@Override
			public int getExecuteSize() {
				return executeSize;
			}
		});
	}
	
	private Object[] objArrayList(PlanWorksheetDetail value) {

		List<Object> valueList = new ArrayList<Object>();
		if (value != null) {
			valueList.add(value.getExciseId());
			valueList.add(value.getAnalysNumber());
			valueList.add(value.getMonth());
			valueList.add(value.getYear());
			valueList.add(value.getAmount());
			valueList.add(value.getCreatedBy());
			valueList.add(value.getCreatedDate());
			valueList.add(value.getUpdatedBy());
			valueList.add(value.getUpdatedDate());
			
		}
		return valueList.toArray();
	}

	private RowMapper<PlanWorksheetDetail> fieldMapping = new RowMapper<PlanWorksheetDetail>() {
		@Override
		public PlanWorksheetDetail mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanWorksheetDetail header = new PlanWorksheetDetail();
			header.setWorksheetDetailId(rs.getBigDecimal("WORK_SHEET_DETAIL_ID"));
			header.setExciseId(rs.getString("EXCISE_ID"));
			header.setAnalysNumber(rs.getString("ANALYS_NUMBER"));
			header.setMonth(rs.getString("MONTH"));
			header.setYear(rs.getString("YEAR"));
			header.setAmount(rs.getBigDecimal("AMOUNT"));
			header.setCreatedBy(rs.getString("CREATED_BY"));
			header.setCreatedDate(rs.getDate("CREATED_DATE"));
			header.setUpdatedBy(rs.getString("UPDATED_BY"));
			header.setUpdatedDate(rs.getTime("UPDATED_DATE"));
			return header;
		}
	};
	
	
	public List<PlanWorksheetDetail> findDetailFromTemp(List<String> monthLIst, String analysNumber) {
		String userLogin = UserLoginUtils.getCurrentUsername();
		
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT ");
		sql.append(" D.EXCISE_ID ");
		sql.append(" ,'"+analysNumber+"' ANALYS_NUMBER ");
		sql.append(" ,D.MONTH ");
		sql.append(" ,D.YEAR ");
		sql.append(" ,D.AMOUNT ");
		sql.append(" ,'"+userLogin+"' CREATED_BY ");
		sql.append(" ,D.CREATED_DATE ");
		sql.append(" ,null UPDATED_BY");
		sql.append(" ,null UPDATED_DATE");
		sql.append(" ,'N' IS_DELETED");
		sql.append(" ,1 VERSION");
		sql.append(" ,D.MAX_VALUES ");
		sql.append(" ,D.RESULT ");
		sql.append(" FROM TA_PLAN_FROM_WS_DETAIL D ");
		if(BeanUtils.isNotEmpty(monthLIst)) {
			sql.append(" WHERE D.MONTH IN (" );
			for (int i = 0; i < monthLIst.size(); i++) {
				sql.append("?");
				if (i < monthLIst.size()-1) {
					sql.append(",");
				}
				params.add(monthLIst.get(i));
			}
			sql.append(") " ) ;
		}
		return commonJdbcDao.executeQuery(sql.toString(), params.toArray(), fieldMappingDtl);
	}
	
	private RowMapper<PlanWorksheetDetail> fieldMappingDtl = new RowMapper<PlanWorksheetDetail>() {
		@Override
		public PlanWorksheetDetail mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanWorksheetDetail dtl = new PlanWorksheetDetail();
			//dtl.setWorksheetDetailId(rs.getBigDecimal("WORK_SHEET_DETAIL_ID"));
			dtl.setExciseId(rs.getString("EXCISE_ID"));
			dtl.setAnalysNumber(rs.getString("ANALYS_NUMBER"));
			dtl.setMonth(rs.getString("MONTH"));
			dtl.setYear(rs.getString("YEAR"));
			dtl.setAmount(rs.getBigDecimal("AMOUNT"));
			dtl.setCreatedBy(rs.getString("CREATED_BY"));
			dtl.setCreatedDate(rs.getDate("CREATED_DATE"));
			dtl.setUpdatedBy(rs.getString("UPDATED_BY"));
			dtl.setUpdatedDate(rs.getTime("UPDATED_DATE"));
			return dtl;
		}
	};

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
