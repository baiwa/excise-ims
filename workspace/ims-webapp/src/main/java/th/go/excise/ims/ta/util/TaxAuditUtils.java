package th.go.excise.ims.ta.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@Component
public class TaxAuditUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(TaxAuditUtils.class);
	
	public static List<TaxOperatorDatatableVo> prepareTaxOperatorDatatable(List<TaxOperatorDetailVo> taxOperatorDetailVoList, TaxOperatorFormVo formVo) {
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = new ArrayList<>();
		TaxOperatorDatatableVo taxOperatorDatatableVo = null;
		List<String> taxAmtList = null;
		for (TaxOperatorDetailVo taxOperatorDetailVo : taxOperatorDetailVoList) {
			taxOperatorDatatableVo = new TaxOperatorDatatableVo();
			try {
				BeanUtils.copyProperties(taxOperatorDatatableVo, taxOperatorDetailVo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.warn(e.getMessage());
				taxOperatorDatatableVo.setCusFullname(taxOperatorDetailVo.getCusFullname());
				taxOperatorDatatableVo.setFacFullname(taxOperatorDetailVo.getFacFullname());
				taxOperatorDatatableVo.setFacAddress(taxOperatorDetailVo.getFacAddress());
				taxOperatorDatatableVo.setOfficeCode(taxOperatorDetailVo.getOfficeCode());
				taxOperatorDatatableVo.setSecCode(taxOperatorDetailVo.getSecCode());
				taxOperatorDatatableVo.setSecDesc(taxOperatorDetailVo.getSecDesc());
				taxOperatorDatatableVo.setAreaCode(taxOperatorDetailVo.getAreaCode());
				taxOperatorDatatableVo.setAreaDesc(taxOperatorDetailVo.getAreaDesc());
				taxOperatorDatatableVo.setWorksheetHdrId(taxOperatorDetailVo.getWorksheetHdrId());
				taxOperatorDatatableVo.setDraftNumber(taxOperatorDetailVo.getDraftNumber());
				taxOperatorDatatableVo.setNewRegId(taxOperatorDetailVo.getNewRegId());
				taxOperatorDatatableVo.setSumTaxAmtG1(taxOperatorDetailVo.getSumTaxAmtG1());
				taxOperatorDatatableVo.setSumTaxAmtG2(taxOperatorDetailVo.getSumTaxAmtG2());
				taxOperatorDatatableVo.setTaxAmtChnPnt(taxOperatorDetailVo.getTaxAmtChnPnt());
				taxOperatorDatatableVo.setTaxMonthNo(taxOperatorDetailVo.getTaxMonthNo());
			}
			
			taxAmtList = new ArrayList<>();
			for (int i = 0; i < formVo.getDateRange(); i++) {
				taxAmtList.add(getTaxAmtByField(taxOperatorDetailVo, i, formVo.getDateRange()));
			}
			taxOperatorDatatableVo.setTaxAmtList(taxAmtList);

			taxOperatorDatatableVoList.add(taxOperatorDatatableVo);
		}

		return taxOperatorDatatableVoList;
	}

	private static String getTaxAmtByField(TaxOperatorDetailVo taxOperatorDetailVo, int i, int dataRange) {
		String taxAmt = "0.00";
		if (i < dataRange / 2) {
			if (i + 1 == 1) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M1();
			} else if (i + 1 == 2) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M2();
			} else if (i + 1 == 3) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M3();
			} else if (i + 1 == 4) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M4();
			} else if (i + 1 == 5) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M5();
			} else if (i + 1 == 6) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M6();
			} else if (i + 1 == 7) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M7();
			} else if (i + 1 == 8) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M8();
			} else if (i + 1 == 9) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M9();
			} else if (i + 1 == 10) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M10();
			} else if (i + 1 == 11) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M11();
			} else if (i + 1 == 12) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M12();
			}
		} else {
			if (i + 1 - (dataRange / 2) == 1) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M1();
			} else if (i + 1 - (dataRange / 2) == 2) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M2();
			} else if (i + 1 - (dataRange / 2) == 3) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M3();
			} else if (i + 1 - (dataRange / 2) == 4) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M4();
			} else if (i + 1 - (dataRange / 2) == 5) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M5();
			} else if (i + 1 - (dataRange / 2) == 6) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M6();
			} else if (i + 1 - (dataRange / 2) == 7) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M7();
			} else if (i + 1 - (dataRange / 2) == 8) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M8();
			} else if (i + 1 - (dataRange / 2) == 9) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M9();
			} else if (i + 1 - (dataRange / 2) == 10) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M10();
			} else if (i + 1 - (dataRange / 2) == 11) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M11();
			} else if (i + 1 - (dataRange / 2) == 12) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG2M12();
			}
		}

		return taxAmt;
	}
	
	public static void commonSelectionWorksheetRowMapper(TaxOperatorDetailVo vo, ResultSet rs) throws SQLException {
		vo.setNewRegId(rs.getString("NEW_REG_ID"));
		vo.setCusFullname(rs.getString("CUS_FULLNAME"));
		vo.setFacFullname(rs.getString("FAC_FULLNAME"));
		vo.setFacAddress(rs.getString("FAC_ADDRESS"));
		vo.setOfficeCode(rs.getString("OFFICE_CODE_R4000"));
		vo.setSecCode(rs.getString("SEC_CODE"));
		vo.setSecDesc(rs.getString("SEC_DESC"));
		vo.setAreaCode(rs.getString("AREA_CODE"));
		vo.setAreaDesc(rs.getString("AREA_DESC"));
		vo.setSumTaxAmtG1(rs.getString("SUM_TAX_AMT_G1"));
		vo.setSumTaxAmtG2(rs.getString("SUM_TAX_AMT_G2"));
		vo.setTaxAmtChnPnt(rs.getString("TAX_AMT_CHN_PNT"));
		vo.setTaxMonthNo(rs.getString("TAX_MONTH_NO"));
		vo.setTaxAmtG1M1(rs.getString("TAX_AMT_G1_M1"));
		vo.setTaxAmtG1M2(rs.getString("TAX_AMT_G1_M2"));
		vo.setTaxAmtG1M3(rs.getString("TAX_AMT_G1_M3"));
		vo.setTaxAmtG1M4(rs.getString("TAX_AMT_G1_M4"));
		vo.setTaxAmtG1M5(rs.getString("TAX_AMT_G1_M5"));
		vo.setTaxAmtG1M6(rs.getString("TAX_AMT_G1_M6"));
		vo.setTaxAmtG1M7(rs.getString("TAX_AMT_G1_M7"));
		vo.setTaxAmtG1M8(rs.getString("TAX_AMT_G1_M8"));
		vo.setTaxAmtG1M9(rs.getString("TAX_AMT_G1_M9"));
		vo.setTaxAmtG1M10(rs.getString("TAX_AMT_G1_M10"));
		vo.setTaxAmtG1M11(rs.getString("TAX_AMT_G1_M11"));
		vo.setTaxAmtG1M12(rs.getString("TAX_AMT_G1_M12"));
		vo.setTaxAmtG2M1(rs.getString("TAX_AMT_G2_M1"));
		vo.setTaxAmtG2M2(rs.getString("TAX_AMT_G2_M2"));
		vo.setTaxAmtG2M3(rs.getString("TAX_AMT_G2_M3"));
		vo.setTaxAmtG2M4(rs.getString("TAX_AMT_G2_M4"));
		vo.setTaxAmtG2M5(rs.getString("TAX_AMT_G2_M5"));
		vo.setTaxAmtG2M6(rs.getString("TAX_AMT_G2_M6"));
		vo.setTaxAmtG2M7(rs.getString("TAX_AMT_G2_M7"));
		vo.setTaxAmtG2M8(rs.getString("TAX_AMT_G2_M8"));
		vo.setTaxAmtG2M9(rs.getString("TAX_AMT_G2_M9"));
		vo.setTaxAmtG2M10(rs.getString("TAX_AMT_G2_M10"));
		vo.setTaxAmtG2M11(rs.getString("TAX_AMT_G2_M11"));
		vo.setTaxAmtG2M12(rs.getString("TAX_AMT_G2_M12"));
		vo.setTaxAmtSd(rs.getString("TAX_AMT_SD"));
		vo.setTaxAmtMean(rs.getString("TAX_AMT_MEAN"));
		vo.setTaxAmtMaxPnt(rs.getString("TAX_AMT_MAX_PNT"));
		vo.setTaxAmtMinPnt(rs.getString("TAX_AMT_MIN_PNT"));
		vo.setDutyName(ExciseUtils.getDutyDesc(rs.getString("DUTY_CODE")));
	}
	
}
