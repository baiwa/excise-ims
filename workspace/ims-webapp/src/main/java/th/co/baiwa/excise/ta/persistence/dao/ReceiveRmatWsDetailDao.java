package th.co.baiwa.excise.ta.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.form.AccMonth0407DTLVo;

@Repository
public class ReceiveRmatWsDetailDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;	

	private Logger logger = LoggerFactory.getLogger(ReceiveRmatWsDetailDao.class);
	
	
	public void insertTableReceiveRmatWsDetail(AccMonth0407DTLVo data ) {
		// inti SQL for insert to database
		StringBuilder sql = new StringBuilder(" INSERT INTO TA_RECEIVE_RMAT_WS_DETAIL "
				+ "(TA_RECEIVE_RMAT_WS_DETAIL_ID,"
				+ "RECEIVE_RMAT_DETAIL_NO,"
				+ "RECEIVE_RMAT_DETAIL_ORDER,"
				+ "PURCHASE_TAX_INV,"
				+ "DAY_BOOK,"
				+ "MONTH_BOOK,"
				+ "EXTERNAL_DATA)");
		sql.append(" values(TA_PLAN_WS_HEADER_SEQ.nextval,?,?,?,?,?,?) ");
		// for to set Object
		
		List<Object> params = new ArrayList<>();
		params.add(data.getNo());
		params.add(data.getProduct());
		params.add(data.getTaxInvoice());
		params.add(data.getDayRecieve());
		params.add(data.getMonthRecieve());
		params.add(data.getExd1());
//		List<Object[]> objArrayOfList = new ArrayList<Object[]>();
//		for (AccMonth0407DTLVo value : dataSesion) {
//			objArrayOfList.add(receiveRmatWsDetailToArrayObject(value));
//		}
		logger.info("SQL : {}",sql.toString());
		jdbcTemplate.update(sql.toString(),params.toArray());
	}
	
	private Object[] receiveRmatWsDetailToArrayObject(AccMonth0407DTLVo value) {

		List<Object> valueList = new ArrayList<Object>();
		if (value != null) {
			valueList.add(value.getNo());
			valueList.add(value.getProduct());
			valueList.add(value.getTaxInvoice());
			valueList.add(value.getDayRecieve());
			valueList.add(value.getMonthRecieve());
			valueList.add(value.getExd1());
		}
		return valueList.toArray();
	}

}
