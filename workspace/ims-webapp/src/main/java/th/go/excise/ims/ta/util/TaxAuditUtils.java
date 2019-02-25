package th.go.excise.ims.ta.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@Component
public class TaxAuditUtils {
	
	public static List<TaxOperatorDatatableVo> prepareTaxOperatorDatatable(List<TaxOperatorDetailVo> taxOperatorDetailVoList, TaxOperatorFormVo formVo) {
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = new ArrayList<>();
		TaxOperatorDatatableVo taxOperatorDatatableVo = null;
		List<String> taxAmtList = null;
		for (TaxOperatorDetailVo taxOperatorDetailVo : taxOperatorDetailVoList) {
			taxOperatorDatatableVo = new TaxOperatorDatatableVo();
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
	
}
