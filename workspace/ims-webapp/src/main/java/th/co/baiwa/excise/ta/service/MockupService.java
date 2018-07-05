package th.co.baiwa.excise.ta.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.domain.MockupVo;
import th.co.baiwa.excise.ia.persistence.dao.ExciseRegisttionNumberDao;
import th.co.baiwa.excise.ia.persistence.dao.ExciseTaxReceiveDao;
import th.co.baiwa.excise.ta.persistence.entity.ExciseRegistartionNumber;
import th.co.baiwa.excise.ta.persistence.entity.ExciseTaxReceive;

@Service
public class MockupService {

	@Autowired
	private ExciseRegisttionNumberDao exciseRegisttionNumberDao;

	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;

	public ResponseDataTable<MockupVo> findAll(String register, MockupVo mockupVo, Date startBackDate, int month , String exciseProductType) {

		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseId(register,exciseProductType,mockupVo.getStart(), mockupVo.getLength());
		DecimalFormat formatter = new DecimalFormat("#,###.00");

		List<ExciseTaxReceive> taxReciveList = null;
		MockupVo vo = null;
		List<MockupVo> mockupVoList = new ArrayList<MockupVo>();
		List<String> monthNameList = exciseTaxReceiveDao.queryMonthShotName(startBackDate, month);
		for (ExciseRegistartionNumber registartionNumber : regisNumberList) {

			taxReciveList = exciseTaxReceiveDao.queryByExciseTaxReceiveAndFilterDataSelection(registartionNumber.getExciseId(),startBackDate, month);
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


					try {
						String amount = taxReceive.getExciseTaxReceiveAmount()!= null ? taxReceive.getExciseTaxReceiveAmount().trim().replace(",", "") : "0";
						sumFirstMonth += Double.parseDouble(amount);
						if (i < (month / 2)) {
							if(!"0".equals(amount)) {
								count++;
								taxReceive.setExciseTaxReceiveAmount(formatter.format(Double.parseDouble(amount)));
							}
							switch (i + 1) {
							case 1:
								vo.setExciseFirstTaxReceiveMonth1(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount1(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 2:
								vo.setExciseFirstTaxReceiveMonth2(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount2(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 3:
								vo.setExciseFirstTaxReceiveMonth3(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount3(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 4:
								vo.setExciseFirstTaxReceiveMonth4(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount4(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 5:
								vo.setExciseFirstTaxReceiveMonth5(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount5(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 6:
								vo.setExciseFirstTaxReceiveMonth6(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount6(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 7:
								vo.setExciseFirstTaxReceiveMonth7(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount7(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 8:
								vo.setExciseFirstTaxReceiveMonth8(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount8(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 9:
								vo.setExciseFirstTaxReceiveMonth9(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount9(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 10:
								vo.setExciseFirstTaxReceiveMonth10(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount10(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 11:
								vo.setExciseFirstTaxReceiveMonth11(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount11(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 12:
								vo.setExciseFirstTaxReceiveMonth12(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseFirstTaxReceiveAmount12(taxReceive.getExciseTaxReceiveAmount());
								break;

							default:

								break;
							}

						}else {

							
							if(!"0".equals(amount)) {
								count2++;
							}
							switch ((i + 1)-(month / 2)) {
							case 1:
								vo.setExciseLatestTaxReceiveMonth1(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount1(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 2:
								vo.setExciseLatestTaxReceiveMonth2(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount2(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 3:
								vo.setExciseLatestTaxReceiveMonth3(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount3(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 4:
								vo.setExciseLatestTaxReceiveMonth4(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount4(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 5:
								vo.setExciseLatestTaxReceiveMonth5(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount5(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 6:
								vo.setExciseLatestTaxReceiveMonth6(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount6(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 7:
								vo.setExciseLatestTaxReceiveMonth7(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount7(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 8:
								vo.setExciseLatestTaxReceiveMonth8(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount8(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 9:
								vo.setExciseLatestTaxReceiveMonth9(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount9(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 10:
								vo.setExciseLatestTaxReceiveMonth10(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount10(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 11:
								vo.setExciseLatestTaxReceiveMonth11(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount11(taxReceive.getExciseTaxReceiveAmount());
								break;
							case 12:
								vo.setExciseLatestTaxReceiveMonth12(taxReceive.getExciseTaxReceiveMonth());
								vo.setExciseLatestTaxReceiveAmount12(taxReceive.getExciseTaxReceiveAmount());
								break;

							default:

								break;
							}

						
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					vo.setExciseFacAddress(String.valueOf(count));
//					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount())
//							? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "")
//							: "0");
					double total24Month = (sumFirstMonth + sumLastMonth);
					double changOfPercent1 = ((sumFirstMonth / total24Month) * 100.00);
					double changOfPercent2 = ((sumLastMonth / total24Month) * 100.00);
					double totalResult = (changOfPercent1 - changOfPercent2);
					vo.setExciseRegisCapital(count2);
					vo.setPayingtax(String.valueOf(count2 + count));
					vo.setChange(String.format("%,.2f", totalResult)+"%");
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

	public void createWorkSheetService(MockupVo mockupVo, Date startBackDate, int month) {
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseId("",null,mockupVo.getStart(), mockupVo.getLength());

	}

}
