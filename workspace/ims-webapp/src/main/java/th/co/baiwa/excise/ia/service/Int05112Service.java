package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaStampDetailDao;
import th.co.baiwa.excise.ia.persistence.entity.IaStamGenre;
import th.co.baiwa.excise.ia.persistence.entity.IaStamType;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetailSummary;
import th.co.baiwa.excise.ia.persistence.repository.IaStamGenreRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStamTypeRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStampDetailSummaryRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int05112DetailVo;
import th.co.baiwa.excise.ia.persistence.vo.Int05112Vo;

@Service
public class Int05112Service {

	@Autowired
	private IaStamTypeRepository iaStamTypeRepository;

	@Autowired
	private IaStamGenreRepository genreRepository;

	@Autowired
	private IaStampDetailDao iaStampDetailDao;

	@Autowired
	private IaStampDetailSummaryRepository iaStampDetailSummaryRepository;

	private final String PAY = "จ่าย";

	public DataTableAjax<Int05112Vo> findAll(Int05112Vo req) {

		List<Int05112Vo> list = new ArrayList<>();
		addHeader(list);
		addColum(list);
		saveSummary(list);
		DataTableAjax<Int05112Vo> dataTableAjax = new DataTableAjax<>();

		dataTableAjax.setRecordsTotal(Long.valueOf(list.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(list.size()));
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

	public List<Int05112Vo> addHeader(List<Int05112Vo> list) {

		List<IaStamType> stampType = iaStamTypeRepository.findAll();
		if (!stampType.isEmpty()) {
			for (IaStamType type : stampType) {

				Int05112Vo voType = new Int05112Vo();
				voType.setOrder(String.valueOf(type.getStampTypeId()));
				voType.setStampType(type.getStampTypeName());
				list.add(voType);

				List<IaStamGenre> stampGenre = genreRepository.findByStampTypeId(String.valueOf(type.getStampTypeId()));
				if (!stampGenre.isEmpty()) {
					int count = 1;
					for (IaStamGenre genre : stampGenre) {
						Int05112Vo vo = new Int05112Vo();

						vo.setColumnId(String.valueOf(genre.getStampGenreId()));
						vo.setOrder(String.valueOf(type.getStampTypeId()) + "." + count++);
						vo.setStampType(genre.getStampGenreName());
						list.add(vo);
					}
				}

			}
		}
		return list;
	}

	public void addColum(List<Int05112Vo> list) {

		/* calculate Date */
		Date nextYearDate = DateUtils.addYears(new Date(), 1);
		Date previousYearDate = DateUtils.addYears(new Date(), -1);

		String dateNow = DateConstant.convertDateToStr(new Date(), ExciseConstants.FORMAT_DATE.YYYYMMDD,ExciseConstants.LOCALE.EN);
		String yearNow = DateConstant.convertDateToStr(new Date(), ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);

		String nextYear = DateConstant.convertDateToStr(nextYearDate, ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);
		String previousYearAdd = DateConstant.convertDateToStr(previousYearDate, ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);

		String dateFrom = "";
		String dateTo = "";
		if (Integer.parseInt(yearNow + "1001") <= Integer.parseInt(dateNow)) {
			dateFrom = yearNow + "1001";
			dateTo = nextYear + "0930";
		} else {
			dateFrom = previousYearAdd + "1001";
			dateTo = yearNow + "0930";
		}

		List<Int05112DetailVo> detailList = iaStampDetailDao.findAll(dateFrom, dateTo);

		if (!detailList.isEmpty()) {
			for (Int05112DetailVo detail : detailList) {
				int index = 0;
				for (Int05112Vo vo : list) {
					if (vo.getColumnId() != null) {
						if (detail.getStampBrandId().equals(Long.valueOf(vo.getColumnId()))) {
							break;
						}
					}
					index++;
				}
				/* set data */
				Int05112Vo result = list.get(index);
				String payOfDate = DateConstant.convertDateToStr(detail.getDateOfPay(), "MM");
				String dateOfPay = calulateYear(detail.getDateOfPay());
				
				Date yyyy = DateConstant.convertStrToDate(dateOfPay, ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.EN);
				dateOfPay = DateConstant.convertDateToStr(yyyy, ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.TH);
				result.setYear(dateOfPay);
				
				checkMonth(result, detail.getStatus(),detail.getNumberOfStamp(), payOfDate);

				/* receive & pay of year */
				if (PAY.equals(detail.getStatus())) {
					result.setSummaryYearPay(result.getSummaryYearPay() + detail.getNumberOfStamp());
					result.setSummaryYearMoneyPay(result.getSummaryYearMoneyPay().add(detail.getSumOfValue()));
				} else {
					result.setSummaryYearRecieve(result.getSummaryYearRecieve() + detail.getNumberOfStamp());
					result.setSummaryYearMoneyRecieve(result.getSummaryYearMoneyRecieve().add(detail.getSumOfValue()));
				}
			}
		}

		/* summay of year */
		for (Int05112Vo result : list) {

			/* column 3 -4 */
			String previousYear = DateConstant.convertStrToStrPreviousYear(result.getYear());
			if (StringUtils.isNoneBlank(result.getColumnId()) && StringUtils.isNotBlank(previousYear)) {
				IaStampDetailSummary summary = iaStampDetailSummaryRepository.findByStampGenreIdAndYear(Long.valueOf(result.getColumnId()), previousYear);
				if (summary != null) {
					result.setBranchLastYeatNumberOfStamp(summary.getNumberOfStamp().intValue());
					result.setBranchLastYeatMoneyOfStamp(summary.getSumOfValue());
				}
			}

			result.setSummaryTotalRecieve(result.getSummaryYearRecieve() + result.getBranchLastYeatNumberOfStamp());
			result.setSummaryTotalMoneyRecieve(result.getSummaryYearMoneyRecieve().add(result.getBranchLastYeatMoneyOfStamp()));
			result.setSummaryTotalPay(result.getSummaryYearPay());
			result.setSummaryTotalMoneyPay(result.getSummaryYearMoneyPay());

			result.setBranchUpToDateNumberOfStamp(result.getSummaryTotalRecieve() - result.getSummaryTotalPay());
			result.setBranchUpToDateMoneyOfStamp(result.getSummaryTotalMoneyRecieve().subtract(result.getSummaryTotalMoneyPay()));
		}

	}

	public boolean isMonthEqual(String monthId, String month) {
		return monthId.equals(month);
	}

	public void checkMonth(Int05112Vo vo, String status, Integer numberOfStamp, String month) {
		if (isMonthEqual("01", month)) {
			if (PAY.equals(status)) {						
				vo.setJanuaryPay(vo.getJanuaryPay() + numberOfStamp);
				return;
			} else {			
				vo.setJanuaryRecieve(vo.getJanuaryRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("02", month)) {
			if (PAY.equals(status)) {
				vo.setFebruaryPay(vo.getFebruaryPay() + numberOfStamp);
				return;
			} else {
				vo.setFebruaryRecieve(vo.getFebruaryRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("03", month)) {
			if (PAY.equals(status)) {
				vo.setMarchPay(vo.getMarchPay() + numberOfStamp);
				return;
			} else {
				vo.setMarchRecieve(vo.getMarchRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("04", month)) {
			if (PAY.equals(status)) {
				vo.setAprilPay(vo.getAprilPay() + numberOfStamp);
				return;
			} else {
				vo.setAprilRecieve(vo.getAprilRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("05", month)) {
			if (PAY.equals(status)) {
				vo.setMayPay(vo.getMayPay() + numberOfStamp);
				return;
			} else {
				vo.setMayRecieve(vo.getMayRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("06", month)) {
			if (PAY.equals(status)) {
				vo.setJunePay(vo.getJunePay() + numberOfStamp);
				return;
			} else {
				vo.setJuneRecieve(vo.getJuneRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("07", month)) {
			if (PAY.equals(status)) {
				vo.setJulyPay(vo.getJulyPay() + numberOfStamp);
				return;
			} else {
				vo.setJulyRecieve(vo.getJulyRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("08", month)) {
			if (PAY.equals(status)) {
				vo.setAugustPay(vo.getAugustPay() + numberOfStamp);
				return;
			} else {
				vo.setAugustRecieve(vo.getAugustRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("09", month)) {
			if (PAY.equals(status)) {
				vo.setSeptemberPay(vo.getSeptemberPay() + numberOfStamp);
				return;
			} else {
				vo.setSeptemberRecieve(vo.getSeptemberRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("10", month)) {
			if (PAY.equals(status)) {
				vo.setOctoberPay(vo.getOctoberPay() + numberOfStamp);
				return;
			} else {
				vo.setOctoberRecieve(vo.getOctoberRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("11", month)) {
			if (PAY.equals(status)) {
				vo.setNovemberPay(vo.getNovemberPay() + numberOfStamp);
				return;
			} else {
				vo.setNovemberRecieve(vo.getNovemberRecieve() + numberOfStamp);
				return;
			}
		}
		if (isMonthEqual("12", month)) {
			if (PAY.equals(status)) {
				vo.setDecemberPay(vo.getDecemberPay() + numberOfStamp);
				return;
			} else {
				vo.setDecemberRecieve(vo.getDecemberRecieve() + numberOfStamp);
				return;
			}
		}
	}

	public void saveSummary(List<Int05112Vo> list) {
		
		/* save summary of year */
		if (!list.isEmpty()) {

			for (Int05112Vo item : list) {
				if (StringUtils.isNoneBlank(item.getColumnId()) && StringUtils.isNotBlank(item.getYear())) {
					IaStampDetailSummary summary = iaStampDetailSummaryRepository.findByStampGenreIdAndYear(Long.valueOf(item.getColumnId()), item.getYear());
					if (summary != null) {
						summary.setNumberOfStamp(new BigDecimal(item.getBranchUpToDateNumberOfStamp()));
						summary.setSumOfValue(item.getBranchUpToDateMoneyOfStamp());
						summary.setStampGenreId(Long.valueOf(item.getColumnId()));
						
						
						summary.setYear(item.getYear());

						iaStampDetailSummaryRepository.save(summary);
					} else {
						/* create row */
						IaStampDetailSummary vo = new IaStampDetailSummary();
						vo.setNumberOfStamp(new BigDecimal(item.getBranchUpToDateNumberOfStamp()));
						vo.setSumOfValue(item.getBranchUpToDateMoneyOfStamp());
						vo.setStampGenreId(Long.valueOf(item.getColumnId()));
						vo.setYear(item.getYear());

						iaStampDetailSummaryRepository.save(vo);
					}
				}
			}
		}
	}

	public String calulateYear(Date date) {
		/* calculate Date */
		//Date date = new Date();
//		Date date = DateConstant.convertStrToDate(ddMMyyy, "yyyyMMdd",ExciseConstants.LOCALE.EN);
		Date nextYearDate = DateUtils.addYears(date, 1);

		String monthNow = DateConstant.convertDateToStr(date, ExciseConstants.FORMAT_DATE.MM,ExciseConstants.LOCALE.EN);
		String yearNow = DateConstant.convertDateToStr(date, ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);

		String nextYear = DateConstant.convertDateToStr(nextYearDate, ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);

		String year = "";
		int month = 9;
		if (Integer.parseInt(monthNow) > month) {
			year = nextYear;
		} else {
			year = yearNow;
		}

		System.out.println(year);
		return year;
	}
/*	public static void main(String[] args) {
		 calculate Date 
		
		Date d = DateConstant.convertStrToDate(ddMMyyy, "yyyyMMdd",ExciseConstants.LOCALE.EN);
		Date nextYearDate = DateUtils.addYears(d, 1);
		Date previousYearDate = DateUtils.addYears(d, -1);

		String dateNow = DateConstant.convertDateToStr(d, ExciseConstants.FORMAT_DATE.YYYYMMDD,ExciseConstants.LOCALE.EN);
		String yearNow = DateConstant.convertDateToStr(d, ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);

		String nextYear = DateConstant.convertDateToStr(nextYearDate, ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);
		String previousYearAdd = DateConstant.convertDateToStr(previousYearDate, ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);

		String dateFrom = "";
		String dateTo = "";
		if (Integer.parseInt(yearNow + "1001") <= Integer.parseInt(dateNow)) {
			dateFrom = yearNow + "1001";
			dateTo = nextYear + "0930";
		} else {
			dateFrom = previousYearAdd + "1001";
			dateTo = yearNow + "0930";
		}
		System.out.println(dateFrom+" : "+dateTo);
	}*/
}

