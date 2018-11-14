package th.co.baiwa.excise.ta.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.dao.ExciseRegisttionNumberDao;
import th.co.baiwa.excise.ia.persistence.dao.ExciseTaxReceiveDao;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetDetailDao;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.ta.persistence.entity.ExciseRegistartionNumber;
import th.co.baiwa.excise.ta.persistence.entity.ExciseTaxReceive;
import th.co.baiwa.excise.ta.persistence.entity.YearPlan;
import th.co.baiwa.excise.ta.persistence.repository.PlanFromWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.repository.PlanRiskDtlRepository;
import th.co.baiwa.excise.ta.persistence.repository.PlanWorksheetHeaderRepository;
import th.co.baiwa.excise.ta.persistence.repository.YearPlanRepository;
import th.co.baiwa.excise.ta.persistence.vo.PlanFromWsVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010200FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010200Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class PlanFromWsHeaderService {

	private static final Logger logger = LoggerFactory.getLogger(PlanFromWsHeaderService.class);
	private static final String PROCESS = "1";
	@Autowired
	private PlanFromWsHeaderRepository planFromWsHeaderRepository;

	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;

	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;

	@Autowired
	private PlanWorksheetDetailDao planWorksheetDetailDao;

	@Autowired
	private ExciseRegisttionNumberDao exciseRegisttionNumberDao;

	@Autowired
	private PlanWorksheetHeaderRepository planWorksheetHeaderRepository;

	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;

	@Autowired
	private PlanRiskDtlRepository planRiskDtlRepository;
	
	@Autowired
	private YearPlanRepository yearPlanRepository;

	private final String RISK_TYPE_NON_PAY = "NON_PAY";
	private final String RISK_TYPE_PERCENT_DIFF = "PERCENT_DIFF";

	public List<Tsl010200Vo> findCondition1(PlanFromWsVo vo) {
		logger.info("findCondition1");
		Date startDate = DateConstant.convertStrToDate(vo.getDateFrom(), DateConstant.MM_YYYY);
		Date endDate = DateConstant.convertStrToDate(vo.getDateTo(), DateConstant.MM_YYYY);
		int daysBetween = (int) ChronoUnit.MONTHS.between(DateConstant.dateToLocalDadte(startDate), DateConstant.dateToLocalDadte(endDate));
		// String analysNumber = DateConstant.DateToString(new Date(),
		// DateConstant.YYYYMMDD) + "-01-"+ planWorksheetHeaderDao.getAnalysNumber();
		List<String> monthList = exciseTaxReceiveDao.queryMonthShotName(endDate, daysBetween+1);
		List<Tsl010200Vo> tlTsl010200VoAllList = new ArrayList<Tsl010200Vo>();
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.searchAllRegistartionNumber();
		if (BeanUtils.isNotEmpty(regisNumberList)) {
			Tsl010200Vo indexDate = new Tsl010200Vo();
			for (ExciseRegistartionNumber registartionNumber : regisNumberList) {

				List<ExciseTaxReceive> taxReciveList = exciseTaxReceiveDao.findReciseTaxByExciseIdAndMonthList(registartionNumber.getExciseId(), monthList);
				List<String> receiveMonth = new ArrayList<String>();
				if (BeanUtils.isNotEmpty(taxReciveList)) {
					for (ExciseTaxReceive month : taxReciveList) {
						receiveMonth.add(month.getExciseTaxReceiveMonth());
					}

					int countNonPay = 0;
					int max = 0;
					for (String month : monthList) {
						if (receiveMonth.indexOf(month) == -1) {
							countNonPay++;
						} else {
							countNonPay = 0;
						}
						if (max < countNonPay) {
							max = countNonPay;
						}
					}
					if (max >= Integer.parseInt(vo.getMonthNonPay())) {
						indexDate = new Tsl010200Vo();
						indexDate.setExciseId(registartionNumber.getExciseId());
						indexDate.setConpanyName(registartionNumber.getExciseFacName());
						indexDate.setAddress(registartionNumber.getIndustrialAddress());
						indexDate.setSubProduct(registartionNumber.getTaexciseProductType());
						indexDate.setSector(registartionNumber.getSector());
						indexDate.setArea(registartionNumber.getExciseArea());
						for (int i = 0; i < monthList.size(); i++) {
							int index = receiveMonth.indexOf(monthList.get(i));
							logger.info("index : " + index);
							String amount = "-";
							if (index != -1) {
								amount = taxReciveList.get(index).getExciseTaxReceiveAmount();
							}
							if (i == 0) {
								indexDate.setMonth1(amount);
							} else if (i == 1) {
								indexDate.setMonth2(amount);
							} else if (i == 2) {
								indexDate.setMonth3(amount);
							} else if (i == 3) {
								indexDate.setMonth4(amount);
							} else if (i == 4) {
								indexDate.setMonth5(amount);
							} else if (i == 5) {
								indexDate.setMonth6(amount);
							} else if (i == 6) {
								indexDate.setMonth7(amount);
							} else if (i == 7) {
								indexDate.setMonth8(amount);
							} else if (i == 8) {
								indexDate.setMonth9(amount);
							} else if (i == 9) {
								indexDate.setMonth10(amount);
							} else if (i == 10) {
								indexDate.setMonth11(amount);
							} else if (i == 11) {
								indexDate.setMonth12(amount);
							}
						}
						tlTsl010200VoAllList.add(indexDate);
					}
				}
			}
		}

		return tlTsl010200VoAllList;

	}

	public List<Tsl010200Vo> findCondition2(PlanFromWsVo vo) {
		logger.info("findCondition2");
		Date startDate = DateConstant.convertStrToDate(vo.getDateFrom(), DateConstant.MM_YYYY);
		Date endDate = DateConstant.convertStrToDate(vo.getDateTo(), DateConstant.MM_YYYY);
		int daysBetween = (int) ChronoUnit.MONTHS.between(DateConstant.dateToLocalDadte(startDate), DateConstant.dateToLocalDadte(endDate));
		// String analysNumber = DateConstant.DateToString(new Date(),
		// DateConstant.YYYYMMDD) + "-01-"+ planWorksheetHeaderDao.getAnalysNumber();
		List<String> monthList = exciseTaxReceiveDao.queryMonthShotName(endDate, daysBetween+1);
		List<Tsl010200Vo> tlTsl010200VoAllList = new ArrayList<Tsl010200Vo>();
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.searchAllRegistartionNumber();
		boolean valid = true;
		if (BeanUtils.isNotEmpty(regisNumberList)) {
			Tsl010200Vo indexDate = new Tsl010200Vo();
			for (ExciseRegistartionNumber registartionNumber : regisNumberList) {

				List<ExciseTaxReceive> taxReciveList = exciseTaxReceiveDao.findReciseTaxByExciseIdAndMonthList(registartionNumber.getExciseId(), monthList);
				List<String> receiveMonth = new ArrayList<String>();
				if (BeanUtils.isNotEmpty(taxReciveList)) {
					for (ExciseTaxReceive month : taxReciveList) {
						receiveMonth.add(month.getExciseTaxReceiveMonth());
					}

					Map<Integer, String> map = new HashMap<Integer, String>();
					
					for (int i = 0; i < monthList.size(); i++) {
						double percentDiff = 0;
						Double amount1 = null;
						Double amount2 = null;
						if (i + 1 < monthList.size()) {
							String month1 = monthList.get(i);
							String month2 = monthList.get(i + 1);
							int index1 = receiveMonth.indexOf(month1);
							int index2 = receiveMonth.indexOf(month2);
							
							if(index1!= -1) {
								try {
									amount1 = Double.parseDouble(taxReciveList.get(index1).getExciseTaxReceiveAmount().trim().replaceAll(",", ""));
									amount2 = Double.parseDouble(taxReciveList.get(index2).getExciseTaxReceiveAmount().trim().replaceAll(",", ""));
								} catch (Exception e) {
									
								}
								
							}
						}
						if(amount1 != null && amount2 != null) {
							if(amount2 == 0 && amount1 == 0) {
								percentDiff = 0;
							}else if(amount2 == 0) {
								percentDiff = 100;
							}else if(amount1 == 0) {
								percentDiff = -100;
							}else {
								percentDiff = (amount2 - amount1)/amount2 * 100;
							}
							map.put(i, percentDiff+"");
							
							
							if(">".equals(vo.getSymbol1())) {
								if(percentDiff >= Double.parseDouble(vo.getPercent1())) {
									valid = false;
								}
							}else if("<".equals(vo.getSymbol1())) {
								if(percentDiff <= Double.parseDouble(vo.getPercent1())) {
									valid = false;
								}
							}else if("=".equals(vo.getSymbol1())) {
								if(percentDiff == Double.parseDouble(vo.getPercent1())) {
									valid = false;
								}
							}else  if(">".equals(vo.getSymbol2())) {
								if(percentDiff >= Double.parseDouble(vo.getPercent2())) {
									valid = false;
								}
							}else if("<".equals(vo.getSymbol2())) {
								if(percentDiff <= Double.parseDouble(vo.getPercent2())) {
									valid = false;
								}
							}else if("=".equals(vo.getSymbol2())) {
								if(percentDiff == Double.parseDouble(vo.getPercent2())) {
									valid = false;
								}
							}else if(">".equals(vo.getSymbol3())) {
								if(percentDiff >= Double.parseDouble(vo.getPercent3())) {
									valid = false;
								}
							}else if("<".equals(vo.getSymbol3())) {
								if(percentDiff <= Double.parseDouble(vo.getPercent3())) {
									valid = false;
								}
							}else if("=".equals(vo.getSymbol3())) {
								if(percentDiff == Double.parseDouble(vo.getPercent3())) {
									valid = false;
								}
							}
						}else {
							map.put(i, "-");
						}
						
					}
					
					
					if (!valid) {
						indexDate = new Tsl010200Vo();
						indexDate.setExciseId(registartionNumber.getExciseId());
						indexDate.setConpanyName(registartionNumber.getExciseFacName());
						indexDate.setAddress(registartionNumber.getIndustrialAddress());
						indexDate.setSubProduct(registartionNumber.getTaexciseProductType());
						indexDate.setSector(registartionNumber.getSector());
						indexDate.setArea(registartionNumber.getExciseArea());
						for (int i = 0; i < monthList.size(); i++) {
							int index = receiveMonth.indexOf(monthList.get(i));
							logger.info("index : " + index);
							String percent = map.get(i) == null ? "-" : map.get(i);
							String amount = "-";
							if (index != -1) {
								amount = taxReciveList.get(index).getExciseTaxReceiveAmount();
							}
							if (i == 0) {
								indexDate.setMonth1(amount);
								indexDate.setPack1(percent);
							} else if (i == 1) {
								indexDate.setMonth2(amount);
								indexDate.setPack2(percent);
							} else if (i == 2) {
								indexDate.setMonth3(amount);
								indexDate.setPack3(percent);
							} else if (i == 3) {
								indexDate.setMonth4(amount);
								indexDate.setPack4(percent);
							} else if (i == 4) {
								indexDate.setMonth5(amount);
								indexDate.setPack5(percent);
							} else if (i == 5) {
								indexDate.setMonth6(amount);
								indexDate.setPack6(percent);
							} else if (i == 6) {
								indexDate.setMonth7(amount);
								indexDate.setPack7(percent);
							} else if (i == 7) {
								indexDate.setMonth8(amount);
								indexDate.setPack8(percent);
							} else if (i == 8) {
								indexDate.setMonth9(amount);
								indexDate.setPack9(percent);
							} else if (i == 9) {
								indexDate.setMonth10(amount);
								indexDate.setPack10(percent);
							} else if (i == 10) {
								indexDate.setMonth11(amount);
								indexDate.setPack11(percent);
							} else if (i == 11) {
								indexDate.setMonth12(amount);
							}
						}
						tlTsl010200VoAllList.add(indexDate);
					}
				}
			}
		}

		return tlTsl010200VoAllList;

	}

	public void saveCondition1(Tsl010200FormVo formVo){
		List<Tsl010200Vo> dataList = formVo.getDataList();
		
		String analysNumber = DateConstant.DateToString(new Date(),DateConstant.YYYYMMDD) + "-01-"+ planWorksheetHeaderDao.getAnalysNumber();
		List<YearPlan> entitys = new ArrayList<>();
		for (Tsl010200Vo vo : dataList) {
			
			YearPlan entity = new YearPlan();
			entity.setAnalysisNumber(analysNumber);
			entity.setExciseId(vo.getExciseId());
			entity.setCompanyName(vo.getConpanyName());
			entity.setCompanyAddress(vo.getAddress());
			entity.setExciseArea(vo.getSector());
			entity.setExciseSubArea(vo.getArea());
			entity.setFlag(PROCESS);
			entity.setRiskType("1");
//			*1 = ความถี่ของเดือนที่ชำระภาษี,
//			2 = เปรียบเทียบจำนวนภาษีระหว่างเดือน,
//			3 = เปรียบเทียบความแต่งต่างการชำระภาษีระหว่างปี

			entitys.add(entity);
		}
		
		yearPlanRepository.save(entitys);
		logger.info("Save TA_YEAR_PLAN");
	}
}
