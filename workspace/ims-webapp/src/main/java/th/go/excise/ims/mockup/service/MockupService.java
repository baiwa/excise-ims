package th.go.excise.ims.mockup.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.ia.constant.DateConstant;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.persistence.dao.ExciseDao;
import th.go.excise.ims.mockup.persistence.dao.ExciseRegisttionNumberDao;
import th.go.excise.ims.mockup.persistence.dao.ExciseTaxReceiveDao;
import th.go.excise.ims.mockup.persistence.entity.ExciseEntity;
import th.go.excise.ims.mockup.persistence.entity.ExciseRegistartionNumber;
import th.go.excise.ims.mockup.persistence.entity.ExciseTax;
import th.go.excise.ims.mockup.persistence.entity.ExciseTaxReceive;

@Service
public class MockupService {

	@Autowired
	private ExciseDao exciseDao;

	@Autowired
	private ExciseRegisttionNumberDao exciseRegisttionNumberDao;

	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;

	public List<ExciseEntity> findById(String id, int limit) {
		List<ExciseEntity> li = exciseDao.queryByExciseId(id, limit);
		Collection<ExciseEntity> list = li;
		List<ExciseEntity> listed = list.stream().filter(distinctByKey(p -> p.getExciseId()))
				.collect(Collectors.toList());
		for (ExciseEntity l : li) {
			for (ExciseEntity led : listed) {
				if (led.getExciseId().equals(l.getExciseId())) {
					ArrayList<ExciseTax> result = led.getExciseTax();
					if (l.getExciseTax().get(0).getExciseTaxReceiveId().equals(result.get(0).getExciseTaxReceiveId())) {
						continue;
					}
					result.add(l.getExciseTax().get(0));
					led.setExciseTax(result);
				}
			}
		}
		return listed;
	}

	public ResponseDataTable<MockupVo> findAll(String register, MockupVo mockupVo, Date startBackDate, int month , String exciseProductType) {

		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseId(register,exciseProductType,
				mockupVo.getStart(), mockupVo.getLength());

		List<ExciseTaxReceive> taxReciveList = null;
		MockupVo vo = null;
		List<MockupVo> mockupVoList = new ArrayList<>();
		List<String> monthNameList = DateConstant.startBackDate(startBackDate, month);
		for (ExciseRegistartionNumber registartionNumber : regisNumberList) {

			taxReciveList = exciseTaxReceiveDao.queryByExciseTaxReceiveAndFilterDataSelection(registartionNumber.getExciseId(), monthNameList);
			int count = 0;
			int count2 = 0;

			double sumFirstMonth = 0.0;
			double sumLastMonth = 0.0;
			vo = new MockupVo();
			vo.setExciseRegisttionNumberId(registartionNumber.getExciseRegisttionNumberId());
			vo.setExciseId(registartionNumber.getExciseId());
			vo.setExciseOperatorName(registartionNumber.getExciseOperatorName());
			vo.setExciseFacName(registartionNumber.getExciseFacName());
			vo.setExciseArea(registartionNumber.getExciseArea());
			vo.setIndustrialAddress(registartionNumber.getIndustrialAddress());
			vo.setSector(registartionNumber.getTaexciseProductType());
			vo.setCoordinates(registartionNumber.getTaexciseSectorArea());

			for (ExciseTaxReceive taxReceive : taxReciveList) {
				int i = monthNameList.indexOf(taxReceive.getExciseTaxReceiveMonth());
				if (i != -1) {

					String monthName = monthNameList.get(i);

					try {
						if (i < (month / 2)) {
							String amount = taxReceive.getExciseTaxReceiveAmount()!= null ? taxReceive.getExciseTaxReceiveAmount().trim().replace(",", "") : "0";
							sumFirstMonth += Double.parseDouble(amount);
							switch (i + 1) {
							case 1:
								vo.setExciseFirstTaxReceiveMonth1(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount1(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount1().isEmpty()) {
									count++;
								}
								break;
							case 2:
								vo.setExciseFirstTaxReceiveMonth2(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount2(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount2().isEmpty()) {
									count++;
								}
								break;
							case 3:
								vo.setExciseFirstTaxReceiveMonth3(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount3(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount3().isEmpty()) {
									count++;
								}
								break;
							case 4:
								vo.setExciseFirstTaxReceiveMonth4(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount4(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount4().isEmpty()) {
									count++;
								}
								break;
							case 5:
								vo.setExciseFirstTaxReceiveMonth5(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount5(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount5().isEmpty()) {
									count++;
								}
								break;
							case 6:
								vo.setExciseFirstTaxReceiveMonth6(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount6(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount6().isEmpty()) {
									count++;
								}
								break;
							case 7:
								vo.setExciseFirstTaxReceiveMonth7(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount7(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount7().isEmpty()) {
									count++;
								}
								break;
							case 8:
								vo.setExciseFirstTaxReceiveMonth8(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount8(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount8().isEmpty()) {
									count++;
								}
								break;
							case 9:
								vo.setExciseFirstTaxReceiveMonth9(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount9(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount9().isEmpty()) {
									count++;
								}
								break;
							case 10:
								vo.setExciseFirstTaxReceiveMonth10(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount10(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount10().isEmpty()) {
									count++;
								}
								break;
							case 11:
								vo.setExciseFirstTaxReceiveMonth11(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount11(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount1().isEmpty()) {
									count++;
								}
								break;
							case 12:
								vo.setExciseFirstTaxReceiveMonth12(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount12(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseFirstTaxReceiveAmount12().isEmpty()) {
									count++;
								}
								break;

							default:

								break;
							}

						}else {

							String amount = taxReceive.getExciseTaxReceiveAmount()!= null ? taxReceive.getExciseTaxReceiveAmount().trim().replace(",", "") : "0";
							sumLastMonth += Double.parseDouble(amount);
							switch ((i + 1)-(month / 2)) {
							case 1:
								vo.setExciseLatestTaxReceiveMonth1(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount1(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount1().isEmpty()) {
									count2++;
								}
								break;
							case 2:
								vo.setExciseLatestTaxReceiveMonth2(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount2(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount2().isEmpty()) {
									count2++;
								}
								break;
							case 3:
								vo.setExciseLatestTaxReceiveMonth3(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount3(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount3().isEmpty()) {
									count2++;
								}
								break;
							case 4:
								vo.setExciseLatestTaxReceiveMonth4(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount4(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount4().isEmpty()) {
									count2++;
								}
								break;
							case 5:
								vo.setExciseLatestTaxReceiveMonth5(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount5(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount5().isEmpty()) {
									count2++;
								}
								break;
							case 6:
								vo.setExciseLatestTaxReceiveMonth6(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount6(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount6().isEmpty()) {
									count2++;
								}
								break;
							case 7:
								vo.setExciseLatestTaxReceiveMonth7(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount7(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount7().isEmpty()) {
									count2++;
								}
								break;
							case 8:
								vo.setExciseLatestTaxReceiveMonth8(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount8(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount8().isEmpty()) {
									count2++;
								}
								break;
							case 9:
								vo.setExciseLatestTaxReceiveMonth9(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount9(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount9().isEmpty()) {
									count2++;
								}
								break;
							case 10:
								vo.setExciseLatestTaxReceiveMonth10(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount10(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount10().isEmpty()) {
									count2++;
								}
								break;
							case 11:
								vo.setExciseLatestTaxReceiveMonth11(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount11(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount1().isEmpty()) {
									count2++;
								}
								break;
							case 12:
								vo.setExciseLatestTaxReceiveMonth12(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount12(taxReceive.getExciseTaxReceiveAmount());
								if (!vo.getExciseLatestTaxReceiveAmount12().isEmpty()) {
									count2++;
								}
								break;

							default:

								break;
							}

						
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount())
							? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "")
							: "0");
					double total24Month = (sumFirstMonth + sumLastMonth);
					double changOfPercent1 = ((sumFirstMonth / total24Month) * 100.00);
					double changOfPercent2 = ((sumLastMonth / total24Month) * 100.00);
					double totalResult = (changOfPercent1 - changOfPercent2);
					vo.setExciseRegisCapital(count2);
					vo.setPayingtax(String.valueOf(count2 + count));
					vo.setChange(String.format("%,.2f", totalResult));
				}
			}
			vo.setExciseFacAddress(String.valueOf(count));
			vo.setExciseRegisCapital(count2);
			mockupVoList.add(vo);
		}

		long count = exciseRegisttionNumberDao.queryCountByExciseId(exciseProductType);

		ResponseDataTable<MockupVo> responseDataTable = new ResponseDataTable<>();
		responseDataTable.setDraw(mockupVo.getDraw().intValue() + 1);
		responseDataTable.setData(mockupVoList);
		responseDataTable.setRecordsTotal((int) count);
		responseDataTable.setRecordsFiltered((int) count);

		return responseDataTable;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> key) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(key.apply(t), Boolean.TRUE) == null;
	}

	public void createWorkSheetService(MockupVo mockupVo, Date startBackDate, int month) {
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseId("",null,mockupVo.getStart(), mockupVo.getLength());

	}

}
