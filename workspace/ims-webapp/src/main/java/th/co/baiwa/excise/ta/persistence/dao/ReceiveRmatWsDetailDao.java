package th.co.baiwa.excise.ta.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.form.OPEDataTable;

@Repository
public class ReceiveRmatWsDetailDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;	

	private Logger logger = LoggerFactory.getLogger(ReceiveRmatWsDetailDao.class);
	
	public void insertTableReceiveRmatWsDetail(OPEDataTable value ) {
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
		params.add(value.getNo());
		params.add(value.getProduct());
		params.add(value.getTaxInvoice());
		params.add(value.getDayRecieve());
		params.add(value.getMonthRecieve());
		params.add(value.getExd1());
		
		logger.info("SQL : {}",sql.toString());
		jdbcTemplate.update(sql.toString(),params.toArray());
	}	

}
