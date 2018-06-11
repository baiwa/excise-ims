package th.go.excise.ims.mockup.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.ims.mockup.persistence.entity.ExciseTaxReceive;

@Repository
public class ExciseTaxReceiveDao {
	
	private Logger logger = LoggerFactory.getLogger(ExciseTaxReceiveDao.class);
	
	@Autowired
	private JdbcTemplate JdbcTemplate;

	private final String sqlTaExciseId = " select * from ta_excise_tax_receive where TA_EXCISE_ID =  ?  ";

	public List<ExciseTaxReceive> queryByExciseId(String register) {

		List<ExciseTaxReceive> list = JdbcTemplate.query(sqlTaExciseId, new Object[] { register },
				exciseRegisttionRowmapper);

		return list;
	}
	
	
	public List<ExciseTaxReceive> queryByExciseTaxReceiveAndFilterDataSelection(String register,List<String> monthList) {
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" select * from ta_excise_tax_receive where TA_EXCISE_ID =  ? ");
		objList.add(register);
		if(monthList != null && monthList.size()>0) {
			boolean firstFlg = true;
			sql.append("and TA_EXCISE_TAX_RECEIVE_MONTH in ( ");
			for (String month : monthList) {
				objList.add(month);
				if(firstFlg) {
					sql.append(" ?");
					firstFlg = false;
				}else {
					sql.append(", ?");
				}
			}
			sql.append(" ) ");
		}
		logger.info("queryByExciseTaxReceiveAndFilterDataSelection : "+sql.toString());
		List<ExciseTaxReceive> list = JdbcTemplate.query(sql.toString(), objList.toArray(),exciseRegisttionRowmapper);

		return list;
	}

	private RowMapper<ExciseTaxReceive> exciseRegisttionRowmapper = new RowMapper<ExciseTaxReceive>() {

		@Override
		public ExciseTaxReceive mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseTaxReceive vo = new ExciseTaxReceive();
			vo.setExciseTaxReceiveId(rs.getInt("TA_EXCISE_TAX_RECEIVE_ID"));
			vo.setExciseId(rs.getString("TA_EXCISE_ID"));
			vo.setExciseTaxReceiveMonth(rs.getString("TA_EXCISE_TAX_RECEIVE_MONTH"));
			vo.setExciseTaxReceiveAmount(rs.getString("TA_EXCISE_TAX_RECEIVE_AMOUNT"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME") != null ? rs.getTimestamp("CREATED_DATETIME").toLocalDateTime() : null);
			vo.setUpdateBy(rs.getString("UPDATE_BY"));
			vo.setUpdateDatetime(rs.getTimestamp("UPDATE_DATETIME") != null ? rs.getTimestamp("UPDATE_DATETIME").toLocalDateTime() : null);
			

			return vo;

		}

	};

}
