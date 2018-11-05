package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.dao.BatchSetter;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;

@Repository
public class IncomeExciseAudDao {

	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public int[][] cwpScwdDtlInsert(final List<CwpScwdDtl> detailList, int executeSize) throws SQLException {
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO IA_CWP_SCWD_DTL (CWP_SCWD_DTL_ID, CWP_SCWD_HDR_ID, RECORD_DATE,POST_DATE, TYPE_CODE, DUCUMENT_NUMBER, SELLER, BANK_ACCOUNT, REFERENCE_NO, BUDGET_CODE, WITHDRAW_AMOUNT, WITHHOLDING_TAX, FINES, FEE, NET_AMOUNT, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_DELETED, VERSION) ");
		sql.append(" values(IA_CWP_SCWD_DTL_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		
		return commonJdbcDao.executeBatch(sql.toString(), new BatchSetter<CwpScwdDtl>() {
			@Override
			public List<CwpScwdDtl> getBatchObjectList() {
				return detailList;
			}
			
			@Override
			public Object[] toObjects(CwpScwdDtl obj) {
				return new Object[] {
					obj.getCwpScwdHdrId(),
					obj.getRecordDate(),
					obj.getPostDate(),
					obj.getTypeCode(),
					obj.getDucumentNumber(),
					obj.getSeller(),
					obj.getBankAccount(),
					obj.getReferenceNo(),
					obj.getBudgetCode(),
					obj.getWithdrawAmount(),
					obj.getWithholdingTax(),
					obj.getFines(),
					obj.getFee(),
					obj.getNetAmount(),
					user,
					date,
					obj.getUpdatedBy(),
					obj.getUpdatedDate(),
					"N",
					1
				};
			}
			
			@Override
			public int getExecuteSize() {
				return executeSize;
			}
		});
	}
}
