package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
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
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;

@Service
public class TaxAuditFactorySelectionService {

	private static final Logger logger = LoggerFactory.getLogger(TaxAuditFactorySelectionService.class);

	private static final String NO_TAX_AMOUNT = "-";
	private static final String EXCISE_PRODUCT_TYPE = "EXCISE_PRODUCT_TYPE";
	private static final String EXCISE_SERVICE_TYPE = "EXCISE_SERVICE_TYPE";

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

	public TaxOperatorVo getPreviewData(TaxOperatorFormVo formVo) {
		TaxOperatorVo vo = new TaxOperatorVo();
		try {
			List<TaxOperatorDetailVo> taxOperatorDetailVoList = prepareTaxOperatorDetailVoList(formVo);
			vo.setDatas(summaryDatatable(taxOperatorDetailVoList, formVo));
			vo.setCount(taWsReg4000Repository.countAll());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return vo;
	}
	
	public List<TaxOperatorDatatableVo> summaryDatatable(List<TaxOperatorDetailVo> taxOperatorDetailVoList ,TaxOperatorFormVo formVo) {
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = new ArrayList<>();
		TaxOperatorDatatableVo taxOperatorDatatableVo = null;
		List<String> taxAmtList = null;
		for (TaxOperatorDetailVo taxOperatorDetailVo : taxOperatorDetailVoList) {
			taxAmtList = new ArrayList<>();
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
			for (int i = 0; i < formVo.getDateRange(); i++) {
				taxAmtList.add(getTaxAmtByField(taxOperatorDetailVo, i, formVo.getDateRange()));
			}
			taxOperatorDatatableVo.setTaxAmtList(taxAmtList);
			taxOperatorDatatableVoList.add(taxOperatorDatatableVo);

		}
		return taxOperatorDatatableVoList;
	}

	private String getTaxAmtByField(TaxOperatorDetailVo taxOperatorDetailVo, int i, int dataRange) {
		String taxAmt = "0.00";
		if (i < dataRange / 2) {
			if (i + 1 == 1) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M1();
			} else if (i + 1 == 2) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M2();
			} else if (i + 1 == 3) {
				taxAmt = taxOperatorDetailVo.getTaxAmtG1M3();
			} else if(i + 1 == 4) {
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

	public List<TaxOperatorDetailVo> prepareTaxOperatorDetailVoList(TaxOperatorFormVo formVo) throws SQLException {
		logger.info("prepareTaxOperatorDetailVoList startDate={}, endDate={}, dateRange={}", formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange());
		Date ymStart = ConvertDateUtils.parseStringToDate(formVo.getDateStart(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		Date ymEnd = ConvertDateUtils.parseStringToDate(formVo.getDateEnd(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String ymStartStr = ConvertDateUtils.formatDateToString(ymStart, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String ymEndStr = ConvertDateUtils.formatDateToString(ymEnd, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		List<TaWsReg4000> wsReg4000List = null;
		if(formVo.getStart() == null && formVo.getLength() == null ) {
			wsReg4000List = taWsReg4000Repository.findAll();
		}else {
			wsReg4000List = taWsReg4000Repository.findAllPagination(formVo.getStart(), formVo.getLength());
		}
		Map<String, List<TaWsInc8000M>> wsInc8000MMap = taWsInc8000MRepository.findByMonthRange(ymStartStr, ymEndStr);
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		List<TaWsInc8000M> wsInc8000MList = null;
		BigDecimal sumTaxAmtG1 = null;
		BigDecimal sumTaxAmtG2 = null;
		BigDecimal taxAmtChnPnt = null;
		ExciseDept exciseDeptSector = null;
		ExciseDept exciseDeptArea = null;
		String taxAmount = null;

		TaxOperatorDetailVo detailVo = null;
		List<TaxOperatorDetailVo> detailVoList = new ArrayList<>();
		for (TaWsReg4000 wsReg4000 : wsReg4000List) {
			logger.debug("wsReg4000.newRegId={}", wsReg4000.getNewRegId());

			int countTaxMonthNo = 0;
			int countG1 = 0;
			int countG2 = 0;
			sumTaxAmtG1 = BigDecimal.ZERO;
			sumTaxAmtG2 = BigDecimal.ZERO;
			
			detailVo = new TaxOperatorDetailVo();
			
			detailVo.setDutyCode(wsReg4000.getDutyCode());
			detailVo.setNewRegId(wsReg4000.getNewRegId());
			detailVo.setCusFullname(wsReg4000.getCusFullname());
			detailVo.setFacFullname(wsReg4000.getFacFullname());
			detailVo.setFacAddress(wsReg4000.getFacAddress());
			detailVo.setOfficeCode(wsReg4000.getOfficeCode());
			exciseDeptSector = ApplicationCache.getExciseDept(wsReg4000.getOfficeCode().substring(0, 2) + "0000");

			if (exciseDeptSector != null) {
				detailVo.setSecCode(exciseDeptSector.getOfficeCode());
				detailVo.setSecDesc(exciseDeptSector.getDeptShortName());
			}
			exciseDeptArea = ApplicationCache.getExciseDept(wsReg4000.getOfficeCode().substring(0, 4) + "00");

			if (exciseDeptArea != null) {
				detailVo.setAreaCode(exciseDeptArea.getOfficeCode());
				detailVo.setAreaDesc(exciseDeptArea.getDeptShortName());
			}

			wsInc8000MList = wsInc8000MMap.get(wsReg4000.getNewRegId());
			if (CollectionUtils.isEmpty(wsInc8000MList)) {
				// Set Default Value
				taxAmount = NO_TAX_AMOUNT;
				detailVo.setTaxAmtG1M1(taxAmount);
				detailVo.setTaxAmtG1M2(taxAmount);
				detailVo.setTaxAmtG1M3(taxAmount);
				detailVo.setTaxAmtG1M4(taxAmount);
				detailVo.setTaxAmtG1M5(taxAmount);
				detailVo.setTaxAmtG1M6(taxAmount);
				detailVo.setTaxAmtG1M7(taxAmount);
				detailVo.setTaxAmtG1M8(taxAmount);
				detailVo.setTaxAmtG1M9(taxAmount);
				detailVo.setTaxAmtG1M10(taxAmount);
				detailVo.setTaxAmtG1M11(taxAmount);
				detailVo.setTaxAmtG1M12(taxAmount);
				detailVo.setTaxAmtG2M1(taxAmount);
				detailVo.setTaxAmtG2M2(taxAmount);
				detailVo.setTaxAmtG2M3(taxAmount);
				detailVo.setTaxAmtG2M4(taxAmount);
				detailVo.setTaxAmtG2M5(taxAmount);
				detailVo.setTaxAmtG2M6(taxAmount);
				detailVo.setTaxAmtG2M7(taxAmount);
				detailVo.setTaxAmtG2M8(taxAmount);
				detailVo.setTaxAmtG2M9(taxAmount);
				detailVo.setTaxAmtG2M10(taxAmount);
				detailVo.setTaxAmtG2M11(taxAmount);
				detailVo.setTaxAmtG2M12(taxAmount);
				detailVo.setSumTaxAmtG1(taxAmount);
				detailVo.setSumTaxAmtG2(taxAmount);
				detailVo.setTaxMonthNo(String.valueOf(BigDecimal.ZERO));
				detailVo.setTaxAmtChnPnt(taxAmount);
				detailVoList.add(detailVo);
				continue;
			}

			for (TaWsInc8000M wsInc8000M : wsInc8000MList) {
				countTaxMonthNo++;
				if (countG1 < formVo.getDateRange() / 2) {
					// Group 1
					if (wsInc8000M.getTaxAmount() != null) {
						taxAmount = decimalFormat.format(wsInc8000M.getTaxAmount());
						sumTaxAmtG1 = sumTaxAmtG1.add(wsInc8000M.getTaxAmount());
					} else {
						taxAmount = NO_TAX_AMOUNT;
					}
					countG1++;
					if (countG1 == 1) {
						detailVo.setTaxAmtG1M1(taxAmount);
					} else if (countG1 == 2) {
						detailVo.setTaxAmtG1M2(taxAmount);
					} else if (countG1 == 3) {
						detailVo.setTaxAmtG1M3(taxAmount);
					} else if (countG1 == 4) {
						detailVo.setTaxAmtG1M4(taxAmount);
					} else if (countG1 == 5) {
						detailVo.setTaxAmtG1M5(taxAmount);
					} else if (countG1 == 6) {
						detailVo.setTaxAmtG1M6(taxAmount);
					} else if (countG1 == 7) {
						detailVo.setTaxAmtG1M7(taxAmount);
					} else if (countG1 == 8) {
						detailVo.setTaxAmtG1M8(taxAmount);
					} else if (countG1 == 9) {
						detailVo.setTaxAmtG1M9(taxAmount);
					} else if (countG1 == 10) {
						detailVo.setTaxAmtG1M10(taxAmount);
					} else if (countG1 == 11) {
						detailVo.setTaxAmtG1M11(taxAmount);
					} else if (countG1 == 12) {
						detailVo.setTaxAmtG1M12(taxAmount);
					}
				} else {
					// Group 2
					if (wsInc8000M.getTaxAmount() != null) {
						taxAmount = decimalFormat.format(wsInc8000M.getTaxAmount());
						sumTaxAmtG2 = sumTaxAmtG2.add(wsInc8000M.getTaxAmount());
					} else {
						taxAmount = NO_TAX_AMOUNT;
					}
					countG2++;
					if (countG2 == 1) {
						detailVo.setTaxAmtG2M1(taxAmount);
					} else if (countG2 == 2) {
						detailVo.setTaxAmtG2M2(taxAmount);
					} else if (countG2 == 3) {
						detailVo.setTaxAmtG2M3(taxAmount);
					} else if (countG2 == 4) {
						detailVo.setTaxAmtG2M4(taxAmount);
					} else if (countG2 == 5) {
						detailVo.setTaxAmtG2M5(taxAmount);
					} else if (countG2 == 6) {
						detailVo.setTaxAmtG2M6(taxAmount);
					} else if (countG2 == 7) {
						detailVo.setTaxAmtG2M7(taxAmount);
					} else if (countG2 == 8) {
						detailVo.setTaxAmtG2M8(taxAmount);
					} else if (countG2 == 9) {
						detailVo.setTaxAmtG2M9(taxAmount);
					} else if (countG2 == 10) {
						detailVo.setTaxAmtG2M10(taxAmount);
					} else if (countG2 == 11) {
						detailVo.setTaxAmtG2M11(taxAmount);
					} else if (countG2 == 12) {
						detailVo.setTaxAmtG2M12(taxAmount);
					}
				}
			}

			detailVo.setSumTaxAmtG1(decimalFormat.format(sumTaxAmtG1.setScale(2, BigDecimal.ROUND_HALF_UP)));
			detailVo.setSumTaxAmtG2(decimalFormat.format(sumTaxAmtG2.setScale(2, BigDecimal.ROUND_HALF_UP)));
			detailVo.setTaxMonthNo(String.valueOf(countTaxMonthNo));

			// Calculate Percentage
			if ((sumTaxAmtG2.compareTo(BigDecimal.ZERO) == 0) && (sumTaxAmtG1.compareTo(BigDecimal.ZERO) == 0)) {
				taxAmtChnPnt = BigDecimal.ZERO;
			} else {
				if (sumTaxAmtG1.compareTo(BigDecimal.ZERO) == 0) {
					sumTaxAmtG1 = BigDecimal.ONE;
				}
				taxAmtChnPnt = (sumTaxAmtG2.subtract(sumTaxAmtG1)).multiply(new BigDecimal("100")).divide(sumTaxAmtG1, 2, BigDecimal.ROUND_HALF_UP);
			}
			detailVo.setTaxAmtChnPnt(decimalFormat.format(taxAmtChnPnt));
			try {
				if("1".equals(wsReg4000.getFacType()) || "3".equals(wsReg4000.getFacType())) {
					detailVo.setDutyName(ApplicationCache.getParamInfoByCode(EXCISE_PRODUCT_TYPE, wsReg4000.getDutyCode()).getValue1());
				}else {
					detailVo.setDutyName(ApplicationCache.getParamInfoByCode(EXCISE_SERVICE_TYPE, wsReg4000.getDutyCode()).getValue1());
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			
			detailVoList.add(detailVo);
		}

		return detailVoList;
	}

	public List<TaWorksheetHdr> selectFactoryProcess(String analysisNumber) throws SQLException {
		logger.info("selectFactoryProcess analysisNumber={}");

		TaWorksheetCondHdr taWorksheetCondHdr = taWorksheetCondHdrRepository.findByAnalysisNumber(analysisNumber);
		List<TaWsReg4000> taWsReg4000List = taWsReg4000Repository.findAll();
		List<TaWorksheetCondDtlTax> taWorksheetCondDtlTaxList = taWorksheetCondDtlTaxRepository.findByAnalysisNumber(analysisNumber);
		Map<String, List<TaWsInc8000M>> data8000 = taWsInc8000MRepository.findByMonthRange(taWorksheetCondHdr.getYearMonthStart(), taWorksheetCondHdr.getYearMonthEnd());
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
			if (taWsInc8000MList != null && taWsInc8000MList.size() > 0) {
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
