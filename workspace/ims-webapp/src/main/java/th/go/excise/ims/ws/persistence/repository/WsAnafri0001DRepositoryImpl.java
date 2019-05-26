package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001D;

public class WsAnafri0001DRepositoryImpl implements WsAnafri0001DRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsAnafri0001DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsAnafri0001D> anafri0001DList) {
		logger.info("batchInsert anafri0001DList.size()={}", anafri0001DList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"ANAFRI0001_D_SEQ",
			"NEW_REG_ID",
			"FORM_CODE",
			"REG_IN_NO",
			"GOODS_SEQ",
			"PRODUCT_CODE",
			"PRODUCT_NAME",
			"BRAND_MAIN_CODE",
			"BRAND_MAIN_NAME",
			"BRAND_SECOND_CODE",
			"BRAND_SECOND_NAME",
			"MODEL_CODE",
			"MODEL_NAME",
			"SIZE_CODE",
			"SIZE_NAME",
			"UNIT_CODE",
			"UNIT_NAME",
			"PRODUCT_QTY",
			"PRODUCT_PRICE",
			"VALUE_RATE",
			"QTY_RATE",
			"TAX_VALUE_AMT",
			"TAX_QUANTITY_AMT",
			"TAX_AMT",
			"LOC_AMT",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_ANAFRI0001_D", insertColumnNames, "WS_ANAFRI0001_D_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), anafri0001DList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsAnafri0001D>() {
			public void setValues(PreparedStatement ps, WsAnafri0001D anafri0001D) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(anafri0001D.getNewRegId());
				paramList.add(anafri0001D.getFormCode());
				paramList.add(anafri0001D.getRegInNo());
				paramList.add(anafri0001D.getGoodsSeq());
				paramList.add(anafri0001D.getProductCode());
				paramList.add(anafri0001D.getProductName());
				paramList.add(anafri0001D.getBrandMainCode());
				paramList.add(anafri0001D.getBrandMainName());
				paramList.add(anafri0001D.getBrandSecondCode());
				paramList.add(anafri0001D.getBrandSecondName());
				paramList.add(anafri0001D.getModelCode());
				paramList.add(anafri0001D.getModelName());
				paramList.add(anafri0001D.getSizeCode());
				paramList.add(anafri0001D.getSizeName());
				paramList.add(anafri0001D.getUnitCode());
				paramList.add(anafri0001D.getUnitName());
				paramList.add(anafri0001D.getProductQty());
				paramList.add(anafri0001D.getProductPrice());
				paramList.add(anafri0001D.getValueRate());
				paramList.add(anafri0001D.getQtyRate());
				paramList.add(anafri0001D.getTaxValueAmt());
				paramList.add(anafri0001D.getTaxQuantityAmt());
				paramList.add(anafri0001D.getTaxAmt());
				paramList.add(anafri0001D.getLocAmt());
				paramList.add(anafri0001D.getCreatedBy());
				paramList.add(anafri0001D.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
