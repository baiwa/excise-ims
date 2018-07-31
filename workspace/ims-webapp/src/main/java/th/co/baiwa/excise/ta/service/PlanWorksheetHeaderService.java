package th.co.baiwa.excise.ta.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.MockupVo;
import th.co.baiwa.excise.domain.form.AccMonth0407DTL;
import th.co.baiwa.excise.ia.persistence.dao.ExciseRegisttionNumberDao;
import th.co.baiwa.excise.ia.persistence.dao.ExciseTaxReceiveDao;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetDetailDao;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.ta.persistence.entity.ExciseRegistartionNumber;
import th.co.baiwa.excise.ta.persistence.entity.ExciseTaxReceive;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetHeaderDetail;
import th.co.baiwa.excise.ta.persistence.entity.RequestFilterMapping;
import th.co.baiwa.excise.domain.CommonAddress;

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
		List<String> monthNameList = exciseTaxReceiveDao.queryMonthShotName(startBackDate, month);
		String analysNumber = DateConstant.DateToString(new Date(), DateConstant.YYYYMMDD) + "-01-"
				+ planWorksheetHeaderDao.getAnalysNumber();
		Date saveDate = new Date();
		logger.info("get analysNumber : " + analysNumber);
		PlanWorksheetHeader planWorksheetHeader = null;
		List<ExciseTaxReceive> taxReciveList = null;
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao
				.queryByExciseRegistionNumber(productType, mockupVo.getCondition());
		for (ExciseRegistartionNumber exciseRegistartionNumber : regisNumberList) {
			planWorksheetHeader = new PlanWorksheetHeader();
			planWorksheetHeader.setAnalysNumber(analysNumber);
			planWorksheetHeader.setExciseId(exciseRegistartionNumber.getExciseId());
			planWorksheetHeader.setCompanyName(exciseRegistartionNumber.getExciseOperatorName());
			planWorksheetHeader.setFactoryName(exciseRegistartionNumber.getExciseFacName());
			planWorksheetHeader.setFactoryAddress(exciseRegistartionNumber.getIndustrialAddress());
			planWorksheetHeader.setExciseOwnerArea(exciseRegistartionNumber.getExciseArea());
			planWorksheetHeader.setExciseOwnerArea(exciseRegistartionNumber.getExciseArea());
			planWorksheetHeader.setProductType(exciseRegistartionNumber.getTaexciseProductType());
			planWorksheetHeader.setExciseOwnerArea1(exciseRegistartionNumber.getTaexciseSectorArea());
			planWorksheetHeader.setMonthDate(mockupVo.getStartBackDate());
			planWorksheetHeader.setFullMonth(new BigDecimal(month));
			taxReciveList = exciseTaxReceiveDao.queryByExciseTaxReceiveAndFilterDataSelection(
					exciseRegistartionNumber.getExciseId(), startBackDate, month);
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
					logger.debug("calAmount", calAmount);
					percenttage = new BigDecimal(calAmount);
					percenttage.setScale(2, RoundingMode.UP);
				} catch (Exception e) {
					e.printStackTrace();
					percenttage = new BigDecimal(0);
				}
			}

			planWorksheetHeader.setTotalMonth(new BigDecimal(countReciveMonth));
			planWorksheetHeader.setPercentage(percenttage);
			planWorksheetHeader.setFirstMonth(new BigDecimal(firstMonth));
			planWorksheetHeader.setLastMonth(new BigDecimal(lastMonth));
			planWorksheetHeader.setFlag("N");
			planWorksheetHeader.setCreatedBy(UserLoginUtils.getCurrentUsername());
			planWorksheetHeader.setCreatedDate(saveDate);

			planWorksheetHeaderDao.insertPlanWorksheetHeader(planWorksheetHeader);
			PlanWorksheetDetail planWorksheetDetail = null;
			List<PlanWorksheetDetail> planWorksheetDetailList = new ArrayList<PlanWorksheetDetail>();
			for (ExciseTaxReceive taxRecive : taxReciveList) {
				planWorksheetDetail = new PlanWorksheetDetail();
				planWorksheetDetail.setAnalysNumber(analysNumber);
				planWorksheetDetail.setMonth(taxRecive.getExciseTaxReceiveMonth());
				planWorksheetDetail.setExciseId(taxRecive.getExciseId());
				planWorksheetDetail.setCreatedBy(UserLoginUtils.getCurrentUsername());
				planWorksheetDetail.setCreatedDate(saveDate);
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
		if (BeanUtils.isEmpty(vo.getPaging()) || new Boolean(vo.getPaging())) {
			planWorksheetHeaderList = planWorksheetHeaderDao.queryPlanWorksheetHeader(vo);
		} else {
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
		String countMonth = valueList != null ? valueList.size() + "" : "0";
		valueList = DateConstant.sortMonthShotList(valueList);
		String startMonthDate = valueList.get(0);
		String endMonthDate = valueList.get(valueList.size() - 1);
		String splitStart[] = startMonthDate.split(" ");
		String splitEnd[] = endMonthDate.split(" ");
		startMonthDate = DateConstant.monthName()[Arrays.asList(DateConstant.monthShotName()).indexOf(splitStart[0])]
				+ " 25" + splitStart[1];
		endMonthDate = DateConstant.monthName()[Arrays.asList(DateConstant.monthShotName()).indexOf(splitEnd[0])]
				+ " 25" + splitEnd[1];
		valueList = new ArrayList<String>();
		valueList.add(startMonthDate);
		valueList.add(endMonthDate);
		String fromMonth = (Arrays.asList(DateConstant.monthShotName()).indexOf(splitEnd[0]) + 1) + "";
		if (fromMonth.length() == 1) {
			fromMonth = "0" + fromMonth;
		}
		fromMonth += "/25" + splitEnd[1];
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

	public Integer updatePlanWorksheetHeaderByExciseList(RequestFilterMapping vo) {
		int count = 0;
		for (String exice : vo.getExiceList()) {
			count += planWorksheetHeaderDao.updatePlanWorksheetHeaderFlag(vo.getFlag(), vo.getAnalysNumber(), exice);
		}
		return count;
	}

	public List<String> getProductionInProcessByMonthAndBackDate(MockupVo mockupVo, Date startBackDate, int month) {
		PlanWorksheetHeader planWorksheetHeader = new PlanWorksheetHeader();
		planWorksheetHeader.setMonthDate(mockupVo.getStartBackDate());
		planWorksheetHeader.setFullMonth(new BigDecimal(month));
		return planWorksheetHeaderDao.queryProductTypeList(planWorksheetHeader);
	}

	public List<String> getExciseIdFlagSFromHeader() {
		return planWorksheetHeaderDao.queryExciseIdFlagSFromHeader();
	}

	public List<Object> queryExciseIdFlagSDataList(String exciseId) {
		List<Object> valueList = planWorksheetHeaderDao.queryExciseIdFlagSDataList(exciseId);
		return valueList;
	}

	public List<AccMonth0407DTL> queryExciseIdFromAccDTL(String exciseId, String type, String start, String end) {
		String[] startData = start.split("/");
		String[] endData = end.split("/");

		Calendar startCal = Calendar.getInstance();
		startCal.set(Integer.parseInt(startData[1]), Integer.parseInt(startData[0]), 1);
		Calendar endCalforUse = Calendar.getInstance();
		endCalforUse.set(Integer.parseInt(endData[1]), Integer.parseInt(endData[0]), 1);

		Calendar endCal = Calendar.getInstance();
		endCal.set(Integer.parseInt(endData[1]), Integer.parseInt(endData[0]), 1);

		int backMonth = 1;
		while (!isEqualsDate(startCal.getTime(), endCal.getTime())) {
			endCal.add(Calendar.MONTH, -1);
			backMonth++;
		}
		return planWorksheetHeaderDao.queryExciseIdFromAccDTL(exciseId, type, endCalforUse.getTime(), backMonth);
	}

	public boolean isEqualsDate(Date date1, Date date2) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMM");
		return simple.format(date1).equals(simple.format(date2));
	}

	public List<PlanWorksheetHeader> queryPlanWorksheetHeaderCriteria(PlanWorksheetHeader criteria) {
		return planWorksheetHeaderDao.queryPlanWorksheetHeaderCriteria(criteria);
	}

	public String queryExciseIdFindByAddress(String exciseId) {
		List<String> valueList = planWorksheetHeaderDao.queryExciseIdFindByAddress(exciseId);
		String dataAddress = "";
		if (BeanUtils.isNotEmpty(valueList)) {
			dataAddress = valueList.get(0);
		}
		return dataAddress;
	}

	public CommonAddress splitAddress(String exciseId) {
		List<String> valueList = planWorksheetHeaderDao.queryExciseIdFindByAddress(exciseId);
		CommonAddress address = new CommonAddress();

		for (String str : valueList) {
			String[] arr = str.split(" ");
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == arr[0] || arr[i].indexOf("/") != -1) {
					address.setHomeNumber(arr[0]);
				} else if (arr[i].indexOf("หมู่ที่") != -1) {
					address.setMoo(arr[i].replace("หมู่ที่", ""));
				} else if (arr[i].indexOf("อาคาร") != -1 || arr[i].indexOf("ตึก") != -1
						|| arr[i].indexOf("บ้าน") != -1) {
					address.setBuilding(
							arr[i].replace("อาคาร", "").replace("ตึก", "").replace("บ้าน", ""));
				} else if (arr[i].indexOf("ชั้น") != -1) {
					address.setLevel(arr[i].replace("ชั้น", ""));
				} else if (arr[i].indexOf("ซอย") != -1) {
					address.setByWay(arr[i].replace("ซอย", ""));
				} else if (arr[i].indexOf("ถนน") != -1) {
					address.setStreet(arr[i].replace("ถนน", ""));
				} else if (arr[i].indexOf("แขวง") != -1 || arr[i].indexOf("ตำบล") != -1) {
					address.setTambol(arr[i].replace("แขวง", "").replace("ตำบล", ""));
				} else if (arr[i].indexOf("เขต") != -1 || arr[i].indexOf("อำเภอ") != -1) {
					address.setDistrict(arr[i].replace("เขต", "").replace("อำเภอ", ""));
				} else if (arr[i].indexOf("จังหวัด") != -1) {
					address.setProvince(arr[i].replace("จังหวัด", ""));
				} else if (arr[i] == arr[arr.length - 1]) {
					address.setZipCode(arr[arr.length - 1]);
				}
			}
			for (int i = 0; i < arr.length; i++) {

				if (address.getHomeNumber() == null) {
					address.setHomeNumber("-");
				} else if (address.getMoo() == null) {
					address.setMoo("-");
				} else if (address.getBuilding() == null) {
					address.setBuilding("-");
				} else if (address.getLevel() == null) {
					address.setLevel("-");
				} else if (address.getByWay() == null) {
					address.setByWay("-");
				} else if (address.getStreet() == null) {
					address.setStreet("-");
				} else if (address.getTambol() == null) {
					address.setTambol("-");
				} else if (address.getDistrict() == null) {
					address.setDistrict("-");
				} else if (address.getProvince() == null) {
					address.setProvince("-");
				} else if (address.getZipCode() == null) {
					address.setZipCode("-");
				}

			}
			System.out.println(address.toString());

		}

		return address;
	}
}
