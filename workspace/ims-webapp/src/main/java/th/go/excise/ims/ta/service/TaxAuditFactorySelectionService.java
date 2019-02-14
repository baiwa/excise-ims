package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;

@Service
public class TaxAuditFactorySelectionService {

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	@Autowired
	private TaWsInc8000MRepository taWsInc8000MRepository;

	@Autowired
	private TaWorksheetCondHdrRepository taWorksheetCondHdrRepository;

	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;

	@Autowired
	private TaWorksheetCondDtlTaxRepository taWorksheetCondDtlTaxRepository;

	public List<TaWorksheetHdr> selectFactoryProcess(String analysisNumber) throws SQLException {
		TaWorksheetCondHdr taWorksheetCondHdr = taWorksheetCondHdrRepository.findByAnalysisNumber(analysisNumber);
		List<TaWsReg4000> taWsReg4000List = taWsReg4000Repository.findAll();
		List<TaWorksheetCondDtlTax> taWorksheetCondDtlTaxList = taWorksheetCondDtlTaxRepository.findByAnalysisNumber(analysisNumber);
		Map<String, List<TaWsInc8000M>> data8000 = taWsInc8000MRepository.findAllTaWsInc8000MSet(taWorksheetCondHdr.getYearMonthStart(), taWorksheetCondHdr.getYearMonthEnd());
		List<TaWorksheetHdr> taWorksheetHdrList = new ArrayList<TaWorksheetHdr>();
		TaWorksheetHdr taWorksheetHdr = new TaWorksheetHdr();
		int index = 0;
		int index2 = 1;
		int countTaxMonthNo = 0;
		double sumTaxAmtG1 = 0, sumTaxAmtG2 = 0;
		for (TaWsReg4000 taWsReg4000 : taWsReg4000List) {
			index = 0;
			countTaxMonthNo = 0;
			sumTaxAmtG1 = 0;
			sumTaxAmtG2 = 0;
			index2 = 0;
			taWorksheetHdr = new TaWorksheetHdr();
			taWorksheetHdr.setAnalysisNumber(analysisNumber);
			taWorksheetHdr.setNewRegId(taWsReg4000.getNewRegId());

			List<TaWsInc8000M> taWsInc8000MList = data8000.get(taWsReg4000.getNewRegId());
			if(taWsInc8000MList != null && taWsInc8000MList.size()>0) {
			for (TaWsInc8000M taWsInc8000M : taWsInc8000MList) {
				index++;
				if (taWsInc8000M.getTaxAmount() != null) {
					String taxAmount = taWsInc8000M.getTaxAmount().toString();
					countTaxMonthNo++;
					if (index <= taWorksheetCondHdr.getMonthNum().intValue() / 2) {
						sumTaxAmtG1 += taWsInc8000M.getTaxAmount().doubleValue();

						if (index == 1) {
							taWorksheetHdr.setTaxAmtG1M1(taxAmount);
						} else if (index == 2) {
							taWorksheetHdr.setTaxAmtG1M2(taxAmount);
						} else if (index == 3) {
							taWorksheetHdr.setTaxAmtG1M3(taxAmount);
						} else if (index == 4) {
							taWorksheetHdr.setTaxAmtG1M4(taxAmount);
						} else if (index == 5) {
							taWorksheetHdr.setTaxAmtG1M5(taxAmount);
						} else if (index == 6) {
							taWorksheetHdr.setTaxAmtG1M6(taxAmount);
						} else if (index == 7) {
							taWorksheetHdr.setTaxAmtG1M7(taxAmount);
						} else if (index == 8) {
							taWorksheetHdr.setTaxAmtG1M8(taxAmount);
						} else if (index == 9) {
							taWorksheetHdr.setTaxAmtG1M9(taxAmount);
						} else if (index == 10) {
							taWorksheetHdr.setTaxAmtG1M10(taxAmount);
						} else if (index == 11) {
							taWorksheetHdr.setTaxAmtG1M11(taxAmount);
						} else if (index == 12) {
							taWorksheetHdr.setTaxAmtG1M12(taxAmount);
						}

					} else {
						sumTaxAmtG2 += taWsInc8000M.getTaxAmount().doubleValue();
						index2++;
						if (index2 == 1) {
							taWorksheetHdr.setTaxAmtG2M1(taxAmount);
						} else if (index2 == 2) {
							taWorksheetHdr.setTaxAmtG2M2(taxAmount);
						} else if (index2 == 3) {
							taWorksheetHdr.setTaxAmtG2M3(taxAmount);
						} else if (index2 == 4) {
							taWorksheetHdr.setTaxAmtG2M4(taxAmount);
						} else if (index2 == 5) {
							taWorksheetHdr.setTaxAmtG2M5(taxAmount);
						} else if (index2 == 6) {
							taWorksheetHdr.setTaxAmtG2M6(taxAmount);
						} else if (index2 == 7) {
							taWorksheetHdr.setTaxAmtG2M7(taxAmount);
						} else if (index2 == 8) {
							taWorksheetHdr.setTaxAmtG2M8(taxAmount);
						} else if (index2 == 9) {
							taWorksheetHdr.setTaxAmtG2M9(taxAmount);
						} else if (index2 == 10) {
							taWorksheetHdr.setTaxAmtG2M10(taxAmount);
						} else if (index2 == 11) {
							taWorksheetHdr.setTaxAmtG2M11(taxAmount);
						} else if (index2 == 12) {
							taWorksheetHdr.setTaxAmtG2M12(taxAmount);
						}
					}
				}
			}
			
			taWorksheetHdr.setSumTaxAmtG1(new BigDecimal(sumTaxAmtG1).setScale(2, BigDecimal.ROUND_HALF_UP));
			taWorksheetHdr.setSumTaxAmtG2(new BigDecimal(sumTaxAmtG2).setScale(2, BigDecimal.ROUND_HALF_UP));
			if (sumTaxAmtG2 != 0 && sumTaxAmtG1 != 00) {
				taWorksheetHdr.setTaxAmtChnPnt(new BigDecimal((sumTaxAmtG2 - sumTaxAmtG1) / sumTaxAmtG1 * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
			} else if (sumTaxAmtG2 == 0 && sumTaxAmtG1 == 00) {
				taWorksheetHdr.setTaxAmtChnPnt(BigDecimal.ZERO);
			} else if (sumTaxAmtG2 == 0 && sumTaxAmtG1 != 0) {
				taWorksheetHdr.setTaxAmtChnPnt(new BigDecimal(100));
			} else {
				taWorksheetHdr.setTaxAmtChnPnt(new BigDecimal(-100));
			}
			taWorksheetHdr.setCondTaxGrp("0");
			taWorksheetHdr.setTaxMonthNo(new BigDecimal(countTaxMonthNo));

			for (TaWorksheetCondDtlTax condition : taWorksheetCondDtlTaxList) {
				if (condition.getTaxMonthStart().compareTo(taWorksheetHdr.getTaxMonthNo()) != 1 && condition.getTaxMonthEnd().compareTo(taWorksheetHdr.getTaxMonthNo()) != -1 && condition.getRangeStart().compareTo(taWorksheetHdr.getTaxAmtChnPnt()) != 1
						&& condition.getRangeEnd().compareTo(taWorksheetHdr.getTaxAmtChnPnt()) != -1) {
					taWorksheetHdr.setCondTaxGrp(condition.getCondGroup());
				}

			}
			taWorksheetHdrList.add(taWorksheetHdr);
			}
		}
		taWorksheetHdrRepository.insertBatch(taWorksheetHdrList);
		return taWorksheetHdrList;
	}

}
