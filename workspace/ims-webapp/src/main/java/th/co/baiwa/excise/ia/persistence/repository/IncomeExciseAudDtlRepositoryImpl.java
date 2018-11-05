package th.co.baiwa.excise.ia.persistence.repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.common.persistence.dao.BatchSetter;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAudDtl;

public class IncomeExciseAudDtlRepositoryImpl implements IncomeExciseAudDtlRepositoryCustom{
	private Logger logger = LoggerFactory.getLogger(IncomeExciseAudDtlRepositoryImpl.class);
	
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;

	@Override
	public int[][] insertIncomeExciseAudDtlBatch(List<IncomeExciseAudDtl> incList) throws SQLException {
		logger.info("insertIncomeExciseAudDtlBatch : {}", incList.size());
		
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO IA_INCOME_EXCISE_AUD_DTL (IA_INCOME_EXCISE_AUD_DTL_ID,IA_INCOME_EXCISE_AUD_ID,RECEIPT_NO,RECEIPT_DATE,RECEIPT_STATUS,INCOME_NAME,INCOME_CODE,OFFICE_CODE,PIN_NID_ID,IS_DELETED,VERSION,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) ");
		sql.append(" values(IA_INCOME_EXCISE_AUD_DTL_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		
		return commonJdbcDao.executeBatch(sql.toString(), new BatchSetter<IncomeExciseAudDtl>() {
			@Override
			public List<IncomeExciseAudDtl> getBatchObjectList() {
				return incList;
			}
			
			@Override
			public Object[] toObjects(IncomeExciseAudDtl obj) {
				
				
				return new Object[] {
					obj.getIaIncomeExciseAudId(),
					obj.getReceiptNo(),
					obj.getReceiptDate(),
					obj.getReceiptStatus(),
					obj.getIncomeName(),
					obj.getIncomeCode(),
					obj.getOfficeCode(),
					obj.getPinNidId(),
					"N",
					1,
					user,
					date,
					null,
					null
				};
			}
			
			@Override
			public int getExecuteSize() {
				return 1000;
			}
		});
		
	}

}
