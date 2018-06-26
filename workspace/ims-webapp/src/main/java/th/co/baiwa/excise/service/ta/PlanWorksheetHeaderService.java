package th.co.baiwa.excise.service.ta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.domain.MockupVo;
import th.co.baiwa.excise.domain.ta.PlanWorksheetHeaderDetail;
import th.co.baiwa.excise.domain.ta.RequestFilterMapping;
import th.co.baiwa.excise.entity.ta.PlanWorksheetDetail;
import th.co.baiwa.excise.entity.ta.PlanWorksheetHeader;
import th.co.baiwa.excise.ia.constant.DateConstant;
import th.co.baiwa.excise.persistence.dao.ExciseRegisttionNumberDao;
import th.co.baiwa.excise.persistence.dao.ExciseTaxReceiveDao;
import th.co.baiwa.excise.persistence.dao.ta.PlanWorksheetDetailDao;
import th.co.baiwa.excise.persistence.dao.ta.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.persistence.entity.ExciseRegistartionNumber;
import th.co.baiwa.excise.persistence.entity.ExciseTaxReceive;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class PlanWorksheetHeaderService {

	private Logger logger = LoggerFactory.getLogger(PlanWorksheetHeaderService.class);

	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;

	@Autowired
	private PlanWorksheetDetailDao planWorksheetDetailDao;

	@Autowired
	private ExciseRegisttionNumberDao exciseRegisttionNumberDao;

	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;

	public String insertPlanWorksheetHeaderService(MockupVo mockupVo, Date startBackDate, int month,
			String productType) {

		logger.info("PlanWorksheetHeaderService.insertPlanWorksheetHeaderService");
		List<String> monthNameList = DateConstant.startBackDate(startBackDate, month);
		String analysNumber = DateConstant.DateToString(new Date(), DateConstant.YYYYMMDD) + "-01-"
				+ planWorksheetHeaderDao.getAnalysNumber();
		Date saveDate = new Date();
		logger.info("get analysNumber : " + analysNumber);
		PlanWorksheetHeader planWorksheetHeader = null;
		List<ExciseTaxReceive> taxReciveList = null;
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao
				.queryByExciseRegistionNumber(productType);
		for (ExciseRegistartionNumber exciseRegistartionNumber : regisNumberList) {
			planWorksheetHeader = new PlanWorksheetHeader();
			planWorksheetHeader.setAnalysNumber(analysNumber);
			planWorksheetHeader.setExciseId(exciseRegistartionNumber.getExciseId());
			planWorksheetHeader.setCompanyName(exciseRegistartionNumber.getExciseOperatorName());
			planWorksheetHeader.setFactoryName(exciseRegistartionNumber.getExciseFacName());
			planWorksheetHeader.setFactoryAddress(exciseRegistartionNumber.getIndustrialAddress());
			planWorksheetHeader.setExciseOwnerArea(exciseRegistartionNumber.getExciseArea());
			planWorksheetHeader.setProductType(exciseRegistartionNumber.getTaexciseProductType());
			planWorksheetHeader.setExciseOwnerArea1(exciseRegistartionNumber.getTaexciseSectorArea());
			taxReciveList = exciseTaxReceiveDao.queryByExciseTaxReceiveAndFilterDataSelection(exciseRegistartionNumber.getExciseId(), startBackDate, month);
			BigDecimal totalAmount = new BigDecimal(0);
			int countReciveMonth = 0;
			int firstMonth = 0;
			int lastMonth = 0;
			double firstAmount = 0;
			double lastAmount = 0;
			BigDecimal percenttage = new BigDecimal(0);
			if (taxReciveList != null && taxReciveList.size() > 0) {
				for (int i = 0; i < taxReciveList.size(); i++) {
					ExciseTaxReceive taxRecive = taxReciveList.get(i);

					String amount = taxRecive.getExciseTaxReceiveAmount() != null
							? taxRecive.getExciseTaxReceiveAmount().trim().replaceAll(",", "")
							: "0";
					try {
						totalAmount = totalAmount.add(new BigDecimal(amount));
					} catch (Exception e) {
						totalAmount = totalAmount.add(new BigDecimal(0));
					}
					if (taxRecive.getExciseTaxReceiveMonth() != null
							&& taxRecive.getExciseTaxReceiveMonth().length() > 0) {
						countReciveMonth++;
						int indexOfMonthNameList = monthNameList
								.indexOf(taxReciveList.get(i).getExciseTaxReceiveMonth());
						if (indexOfMonthNameList != -1) {
							if (indexOfMonthNameList < monthNameList.size() / 2) {
								if (!"0".equals(amount)) {
									firstMonth++;
								}
								firstAmount += Double.parseDouble(amount);
							} else {
								if (!"0".equals(amount)) {
									lastMonth++;
								}
								lastAmount += Double.parseDouble(amount);
							}
						}
					}
				}
				double percetFirstAmount = firstAmount / totalAmount.doubleValue() * 100;
				double percetLastAmount = lastAmount / totalAmount.doubleValue() * 100;

				try {
					double calAmount = percetLastAmount - percetFirstAmount;
					percenttage = new BigDecimal(calAmount);
					percenttage.setScale(2, RoundingMode.UP);
				} catch (Exception e) {
					e.printStackTrace();
					percenttage = new BigDecimal(0);
				}
			}

			planWorksheetHeader.setTotalAmount(totalAmount);
			planWorksheetHeader.setTotalMonth(new BigDecimal(countReciveMonth));
			planWorksheetHeader.setPercentage(percenttage);
			planWorksheetHeader.setFirstMonth(new BigDecimal(firstMonth));
			planWorksheetHeader.setLastMonth(new BigDecimal(lastMonth));
			planWorksheetHeader.setFlag("N");
			planWorksheetHeader.setCreateBy(UserLoginUtils.getCurrentUsername());
			planWorksheetHeader.setCreateDatetime(saveDate);
			planWorksheetHeaderDao.insertPlanWorksheetHeader(planWorksheetHeader);
			PlanWorksheetDetail planWorksheetDetail = null;
			List<PlanWorksheetDetail> planWorksheetDetailList = new ArrayList<PlanWorksheetDetail>();
			for (ExciseTaxReceive taxRecive : taxReciveList) {
				planWorksheetDetail = new PlanWorksheetDetail();
				planWorksheetDetail.setAnalysNumber(analysNumber);
				planWorksheetDetail.setMonth(taxRecive.getExciseTaxReceiveMonth());
				planWorksheetDetail.setExciseId(taxRecive.getExciseId());
				planWorksheetDetail.setCreateBy(UserLoginUtils.getCurrentUsername());
				planWorksheetDetail.setCreateDatetime(saveDate);
				String amountDetail = taxRecive.getExciseTaxReceiveAmount() != null
						? taxRecive.getExciseTaxReceiveAmount().trim().replaceAll(",", "")
						: "0";
				planWorksheetDetail.setAmount(new BigDecimal(amountDetail));
				planWorksheetDetailList.add(planWorksheetDetail);
			}
			planWorksheetDetailDao.insertPlanWorksheetDetail(planWorksheetDetailList);

		}
		return analysNumber;
	}

	public List<String> queryAnalysNumberFromHeader() {
		return planWorksheetHeaderDao.queryAnalysNumberFromHeader();
	}

	public ResponseDataTable<PlanWorksheetHeaderDetail> queryPlanWorksheetHeaderDetil(RequestFilterMapping vo) {
		List<PlanWorksheetHeader> planWorksheetHeaderList;
		if(BeanUtils.isEmpty(vo.getPaging()) || new Boolean(vo.getPaging())) {
			planWorksheetHeaderList = planWorksheetHeaderDao.queryPlanWorksheetHeader(vo);
		}else {
			planWorksheetHeaderList = planWorksheetHeaderDao.queryPlanWorksheetHeaderFullDataNoPaging(vo);
		}
		List<PlanWorksheetHeaderDetail> PlanWorksheetHeaderDetailList = new ArrayList<PlanWorksheetHeaderDetail>();
		List<PlanWorksheetDetail> planDetailList = new ArrayList<PlanWorksheetDetail>();
		PlanWorksheetHeaderDetail planShow = new PlanWorksheetHeaderDetail();
		PlanWorksheetDetail planDetail;
		for (PlanWorksheetHeader planWorksheetHeader : planWorksheetHeaderList) {
			if (planWorksheetHeader != null
					&& !planWorksheetHeader.getWorksheetHeaderId().equals(planShow.getWorksheetHeaderId())) {
				if (planShow.getWorksheetHeaderId() != null) {
					PlanWorksheetHeaderDetailList.add(planShow);
				}
				planShow = new PlanWorksheetHeaderDetail();
				planShow.setWorksheetHeaderId(planWorksheetHeader.getWorksheetHeaderId());
				planShow.setAnalysNumber(planWorksheetHeader.getAnalysNumber());
				planShow.setExciseId(planWorksheetHeader.getExciseId());
				planShow.setCompanyName(planWorksheetHeader.getCompanyName());
				planShow.setFactoryName(planWorksheetHeader.getFactoryName());
				planShow.setFactoryAddress(planWorksheetHeader.getFactoryAddress());
				planShow.setExciseOwnerArea(planWorksheetHeader.getExciseOwnerArea());
				planShow.setProductType(planWorksheetHeader.getProductType());
				planShow.setExciseOwnerArea1(planWorksheetHeader.getExciseOwnerArea1());
				planShow.setTotalAmount(planWorksheetHeader.getTotalAmount());
				planShow.setPercentage(planWorksheetHeader.getPercentage());
				planShow.setTotalMonth(planWorksheetHeader.getTotalMonth());
				planShow.setDecideType(planWorksheetHeader.getDecideType());
				planShow.setFlag(planWorksheetHeader.getFlag());
				planShow.setFirstMonth(planWorksheetHeader.getFirstMonth());
				planShow.setLastMonth(planWorksheetHeader.getLastMonth());
			}
			planDetail = new PlanWorksheetDetail();
			planDetail.setAnalysNumber(planWorksheetHeader.getAnalysNumber());
			planDetail.setExciseId(planWorksheetHeader.getExciseId());
			planDetailList = planWorksheetDetailDao.queryPlanWorksheetDetailCriteria(planDetail);
			List<String> monthList = new ArrayList<String>();
			for (PlanWorksheetDetail planWorksheetDetail : planDetailList) {
				monthList.add(planWorksheetDetail.getMonth());
			}
			monthList = DateConstant.sortMonthShotList(monthList);
			for (int i = 0; i < planDetailList.size(); i++) {
				planDetail = planDetailList.get(i);
				DecimalFormat formatter = new DecimalFormat("#,###.00");
				String amountValue = formatter.format(planDetail.getAmount().doubleValue());
				if (".00".equals(amountValue)) {
					amountValue = "";
				}
				int index = monthList.indexOf(planDetail.getMonth());
				if (index + 1 == 1) {
					planShow.setAmount1(amountValue);
				} else if (index + 1 == 2) {
					planShow.setAmount2(amountValue);
				} else if (index + 1 == 3) {
					planShow.setAmount3(amountValue);
				} else if (index + 1 == 4) {
					planShow.setAmount4(amountValue);
				} else if (index + 1 == 5) {
					planShow.setAmount5(amountValue);
				} else if (index + 1 == 6) {
					planShow.setAmount6(amountValue);
				} else if (index + 1 == 7) {
					planShow.setAmount7(amountValue);
				} else if (index + 1 == 8) {
					planShow.setAmount8(amountValue);
				} else if (index + 1 == 9) {
					planShow.setAmount9(amountValue);
				} else if (index + 1 == 10) {
					planShow.setAmount10(amountValue);
				} else if (index + 1 == 11) {
					planShow.setAmount11(amountValue);
				} else if (index + 1 == 12) {
					planShow.setAmount12(amountValue);
				} else if (index + 1 == 13) {
					planShow.setAmount13(amountValue);
				} else if (index + 1 == 14) {
					planShow.setAmount14(amountValue);
				} else if (index + 1 == 15) {
					planShow.setAmount15(amountValue);
				} else if (index + 1 == 16) {
					planShow.setAmount16(amountValue);
				} else if (index + 1 == 17) {
					planShow.setAmount17(amountValue);
				} else if (index + 1 == 18) {
					planShow.setAmount18(amountValue);
				} else if (index + 1 == 19) {
					planShow.setAmount19(amountValue);
				} else if (index + 1 == 20) {
					planShow.setAmount20(amountValue);
				} else if (index + 1 == 21) {
					planShow.setAmount21(amountValue);
				} else if (index + 1 == 22) {
					planShow.setAmount22(amountValue);
				} else if (index + 1 == 23) {
					planShow.setAmount23(amountValue);
				} else if (index + 1 == 24) {
					planShow.setAmount24(amountValue);
				}
			}
		}
		PlanWorksheetHeaderDetailList.add(planShow);
		ResponseDataTable<PlanWorksheetHeaderDetail> responseDataTable = new ResponseDataTable<PlanWorksheetHeaderDetail>();
		long count = planWorksheetHeaderDao.queryCountByPlanWorksheetHeader(vo);
		responseDataTable.setDraw(vo.getDraw().intValue() + 1);
		responseDataTable.setData(PlanWorksheetHeaderDetailList);
		responseDataTable.setRecordsTotal((int) count);
		responseDataTable.setRecordsFiltered((int) count);
		return responseDataTable;

	}

	public List<String> getStartDateAndEndDateFromAnalysNumber(String analysNumber) {
		List<String> valueList = planWorksheetHeaderDao.getStartDateAndEndDateFromAnalysNumber(analysNumber);
		String countMonth = valueList != null ? valueList.size()+"" : "0";
		valueList = DateConstant.sortMonthShotList(valueList);
        String startMonthDate = valueList.get(0);
        String endMonthDate = valueList.get(valueList.size()-1);
        String splitStart[] = startMonthDate.split(" ");
        String splitEnd[] = endMonthDate.split(" ");
        startMonthDate = DateConstant.monthName()[Arrays.asList(DateConstant.monthShotName()).indexOf(splitStart[0])]+" 25"+splitStart[1];
        endMonthDate = DateConstant.monthName()[Arrays.asList(DateConstant.monthShotName()).indexOf(splitEnd[0])]+" 25"+splitEnd[1];
        valueList = new ArrayList<String>();
        valueList.add(startMonthDate);
        valueList.add(endMonthDate);
        String fromMonth = (Arrays.asList(DateConstant.monthShotName()).indexOf(splitEnd[0])+1)+"";
        if(fromMonth.length() == 1) {
        	fromMonth = "0"+fromMonth;
        }
        fromMonth+="/25"+splitEnd[1];
        valueList.add(fromMonth);
        valueList.add(countMonth);
        
		return valueList;
	}
	
	public String getWorkSheetNumber(String analysNumber) {
		return planWorksheetHeaderDao.queryWorkSheetNumber(analysNumber);
	}
	
	
	public void updateStatusFlg(RequestFilterMapping vo) {
		String workSheetNumber = DateConstant.DateToString(new Date(), DateConstant.YYYYMMDD) + "-02-"
				+ planWorksheetHeaderDao.getWorksheetNumber();
		vo.setWorkShheetNumber(workSheetNumber);
		planWorksheetHeaderDao.updateStatusFlg(vo);
	}
	
}
