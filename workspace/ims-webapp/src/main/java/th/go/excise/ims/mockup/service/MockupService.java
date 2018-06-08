package th.go.excise.ims.mockup.service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.ia.constant.DateConstant;
import th.go.excise.ims.mockup.domain.Excise;
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

	public ResponseDataTable<MockupVo> findAll(String register, MockupVo mockupVo, Date startBackDate, int month) {

		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseId(register,
				mockupVo.getStart(), mockupVo.getLength());

		List<ExciseTaxReceive> taxReciveList = null;
		MockupVo vo = null;
		List<MockupVo> mockupVoList = new ArrayList<>();
		List<String> monthNameList = DateConstant.startBackDate(startBackDate, month);
		for (ExciseRegistartionNumber registartionNumber : regisNumberList) {
			

			taxReciveList = exciseTaxReceiveDao.queryByExciseId(registartionNumber.getExciseId());
			int count = 0;
			int count2 = 0;

			double sumFirstMonth = 0.0;
			double sumLastMonth = 0.0;

			

			for (int i = 0; i < monthNameList.size(); i++) {
				for (ExciseTaxReceive taxReceive : taxReciveList) {
					
					String monthName = monthNameList.get(i);
					System.out.println(taxReceive.getExciseId() + " : "+taxReceive.getExciseTaxReceiveMonth()+ " : "+taxReceive.getExciseTaxReceiveAmount());
					
					if (monthName.equals(taxReceive.getExciseTaxReceiveMonth()) && taxReceive.getExciseId().equals(registartionNumber.getExciseId())) {
						try {
							vo = new MockupVo();
							vo.setExciseRegisttionNumberId(registartionNumber.getExciseRegisttionNumberId());
							vo.setExciseId(registartionNumber.getExciseId());
							vo.setExciseOperatorName(registartionNumber.getExciseOperatorName());
							vo.setExciseFacName(registartionNumber.getExciseFacName());
							vo.setExciseArea(registartionNumber.getExciseArea());
							vo.setIndustrialAddress(registartionNumber.getIndustrialAddress());
							vo.setSector(registartionNumber.getTaexciseProductType());
							vo.setCoordinates(registartionNumber.getTaexciseSectorArea());
							vo.setExciseFacAddress(String.valueOf(count));
							vo.setExciseRegisCapital(count2);
							switch (i) {
							case 1:
								System.out.println(taxReceive.getExciseId() + " : "+taxReceive.getExciseTaxReceiveMonth()+ " : "+taxReceive.getExciseTaxReceiveAmount());
								System.out.println(taxReceive.getExciseId() + " : "+taxReceive.getExciseTaxReceiveMonth()+ " : "+taxReceive.getExciseTaxReceiveAmount());
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
			}
			mockupVoList.add(vo);
		}

		long count = exciseRegisttionNumberDao.queryCountByExciseId();

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
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseId("",
				mockupVo.getStart(), mockupVo.getLength());

	}

}
