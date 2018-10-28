package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.vo.Int062AddFieldVo;
import th.co.baiwa.excise.ia.persistence.vo.Int062paymentInfoVo;

@Repository
public class WithdrawalPersonsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Int062paymentInfoVo> paymentInfo(Long withdrawalId) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT W.WITHDRAWAL_PERSONS_ID, W.WITHDRAWAL_ID, W.AMOUNT, W.PAYEE, W.PAYMENT_METHOD, W.REF_PAYMENT ");
		sql.append(" FROM IA_WITHDRAWAL_PERSONS W ");
			sql.append(" WHERE W.WITHDRAWAL_ID = ? ");
			valueList.add(withdrawalId);
			
		List<Int062paymentInfoVo> addFieldList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingPaymentInfo);
		return addFieldList;
	}
	
	private RowMapper<Int062paymentInfoVo> fieldMappingPaymentInfo = new RowMapper<Int062paymentInfoVo>() {
		@Override
		public Int062paymentInfoVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int062paymentInfoVo vo = new Int062paymentInfoVo();
			vo.setWithdrawalPersonsId(rs.getLong("WITHDRAWAL_PERSONS_ID"));
			vo.setWithdrawalId(rs.getLong("WITHDRAWAL_ID"));
			vo.setAmount(rs.getBigDecimal("AMOUNT"));
			vo.setPayee(rs.getString("PAYEE"));
			vo.setPaymentMethod(rs.getString("PAYMENT_METHOD"));
			vo.setRefPayment(rs.getString("REF_PAYMENT"));			
			return vo;
		}
	};

}
