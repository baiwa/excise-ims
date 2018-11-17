package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.vo.Int0614FormSearchVo;

@Repository
public class Int0614Dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = " select * from("+
			"   select T1.NAME, COUNT(T1.NAME) COUNT, SUM(T1.MONEY) TOTAL, T1.FLAG from("+
			"     select ime.id, ime.FULL_NAME NAME, ime.AFFILIATION, ime.TOTAL_MONEY MONEY, to_char(ime.created_date, 'yyyyMM') created_date,"+
			"     CASE "+
			"    WHEN ime.IS_DELETED = 'N' THEN '1'"+
			"    ELSE '1'"+
			"    end FLAG"+
			" from IA_MEDICAL_WELFARE ime where to_char(ime.created_date, 'yyyyMM') BETWEEN ? and ? "+
			" and AFFILIATION = ? "+
			" UNION"+
			" select IRH.RENT_HOUSE_ID ID, IRH.NAME, IRH.AFFILIATION, IRH.TOTAL_WITHDRAW MONEY, to_char(irh.created_date, 'yyyyMM') created_date,"+
			"     CASE "+
			"    WHEN IRH.IS_DELETED = 'N' THEN '2'"+
			"    ELSE '2'"+
			"    end FLAG"+
			" from IA_RENT_HOUSE irh where to_char(irh.created_date, 'yyyyMM') BETWEEN  ? and ?"+
			" and AFFILIATION = ? "+
			" UNION"+
			" select itf.ID, itf.NAME, itf.PITION POSITION, itf.SUM_AMOUNT MONEY, to_char(itf.created_date, 'yyyyMM') created_date,"+
			"     CASE "+
			"    WHEN itf.IS_DELETED = 'N' THEN '3'"+
			"    ELSE '3'"+
			"    end FLAG"+
			"    from IA_TUITION_FEE itf where to_char(itf.created_date, 'yyyyMM') BETWEEN ? and ?"+
			" and BELONG = ? "+
			"   )T1 Group by T1.NAME, T1.FLAG"+
			" ) where 1 = 1 ";
	public List<Int0614FormSearchVo> findInRentHouse(String strStartdate, String strEnddate, String typeD1) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQL);
		
			valueList.add(strStartdate);
			valueList.add(strEnddate);
			valueList.add(typeD1);
			valueList.add(strStartdate);
			valueList.add(strEnddate);
			valueList.add(typeD1);
			valueList.add(strStartdate);
			valueList.add(strEnddate);
			valueList.add(typeD1);

		List<Int0614FormSearchVo> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMapping);
		return dataList;
	}

	private RowMapper<Int0614FormSearchVo> fieldMapping = new RowMapper<Int0614FormSearchVo>() {
		@Override
		public Int0614FormSearchVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0614FormSearchVo en = new Int0614FormSearchVo();
			en.setName(rs.getString("NAME"));
			en.setWithdrawAmount(rs.getString("COUNT"));
			en.setOrder(rs.getString("FLAG"));
			return en;
		}
	};
	
	public String getTypeDesc(String id) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT L.SUB_TYPE_DESCRIPTION FROM SYS_LOV L ");
		sql.append(" WHERE L.LOV_ID = ? ");
		valueList.add(id);

		String data = jdbcTemplate.queryForObject(sql.toString(), valueList.toArray(), String.class);
		return data;
	}

}
