package th.go.excise.ims.mockup.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.persistence.dao.ExciseRegisttionNumberDao;
import th.go.excise.ims.mockup.persistence.dao.ExciseTaxReceiveDao;
import th.go.excise.ims.mockup.persistence.entity.ExciseRegistartionNumber;
import th.go.excise.ims.mockup.persistence.entity.ExciseTaxReceive;

@Service
public class MockupService {
	

	@Autowired
	private ExciseRegisttionNumberDao exciseRegisttionNumberDao;

	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;


	public ResponseDataTable<MockupVo> findAll(String register, MockupVo mockupVo) {

		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseId(register, mockupVo.getStart(), mockupVo.getLength());

		List<ExciseTaxReceive> taxReciveList = null;
		MockupVo vo = null;
		List<MockupVo> mockupVoList = new ArrayList<>();
		for (ExciseRegistartionNumber registartionNumber : regisNumberList) {
			vo = new MockupVo();
			vo.setExciseRegisttionNumberId(registartionNumber.getExciseRegisttionNumberId());
			vo.setExciseId(registartionNumber.getExciseId());
			vo.setExciseOperatorName(registartionNumber.getExciseOperatorName());
			vo.setExciseFacName(registartionNumber.getExciseFacName());
			vo.setExciseArea(registartionNumber.getExciseArea());
			vo.setIndustrialAddress(registartionNumber.getIndustrialAddress());
			vo.setSector(registartionNumber.getTaexciseProductType());	
			vo.setCoordinates(registartionNumber.getTaexciseSectorArea());
			
			taxReciveList = exciseTaxReceiveDao.queryByExciseId(registartionNumber.getExciseId());
			int count = 0;
			int count2 = 0;
           
			double sumFirstMonth = 0.0;
			double sumLastMonth = 0.0;
            
			
			
			
			for (ExciseTaxReceive taxReceive : taxReciveList) {
				vo.setExciseFacAddress(String.valueOf(count));	
				vo.setExciseRegisCapital(count2);
				
				if ("พ.ค. 58".equals(taxReceive.getExciseTaxReceiveMonth())) {		
					vo.setExciseFirstTaxReceiveMonth1(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount1(taxReceive.getExciseTaxReceiveAmount());	
					
					if(!vo.getExciseFirstTaxReceiveAmount1().isEmpty()){						
						count++;
						
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");

				} else if ("มิ.ย. 58".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth2(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount2(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount2().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ก.ค. 58".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth3(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount3(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount3().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ส.ค. 58".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth4(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount4(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount4().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ก.ย. 58".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth5(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount5(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount5().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ต.ค. 58".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth6(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount6(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount6().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));	
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("พ.ย. 58".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth7(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount7(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount7().isEmpty()){						
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ธ.ค. 58".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth8(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount8(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount8().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ม.ค. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth9(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount9(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount9().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ก.พ. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth10(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount10(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount10().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("มี.ค. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth11(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount11(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount11().isEmpty()){						
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));	
					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("เม.ย. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseFirstTaxReceiveMonth12(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseFirstTaxReceiveAmount12(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseFirstTaxReceiveAmount12().isEmpty()){					
						count++;
					}
					vo.setExciseFacAddress(String.valueOf(count));		

					sumFirstMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("พ.ค. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth1(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount1(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount1().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("มิ.ย. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth2(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount2(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount2().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ก.ค. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth3(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount3(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount3().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ส.ค. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth4(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount4(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount4().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ก.ย. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth5(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount5(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount5().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ต.ค. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth6(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount6(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount6().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("พ.ย. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth7(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount7(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount7().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ธ.ค. 59".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth8(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount8(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount8().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ม.ค. 60".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth9(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount9(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount9().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("ก.พ. 60".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth10(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount10(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount10().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);		
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("มี.ค. 60".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth11(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount11(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount11().isEmpty()){
						count2++;
					}
					vo.setExciseRegisCapital(count2);	
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				} else if ("เม.ย. 60".equals(taxReceive.getExciseTaxReceiveMonth())) {
					vo.setExciseLatestTaxReceiveMonth12(taxReceive.getExciseTaxReceiveMonth());
					vo.setExciseLatestTaxReceiveAmount12(taxReceive.getExciseTaxReceiveAmount());
					if(!vo.getExciseLatestTaxReceiveAmount12().isEmpty()){
						count2++;
					}
					
					sumLastMonth += Double.parseDouble(StringUtils.isNotBlank(taxReceive.getExciseTaxReceiveAmount()) ? StringUtils.replace(taxReceive.getExciseTaxReceiveAmount(), ",", "") : "0");
				
				}
				

				double total24Month = (sumFirstMonth + sumLastMonth);					
				double changOfPercent1 = ((sumFirstMonth/total24Month)*100.00);					
				double changOfPercent2 = ((sumLastMonth/total24Month)*100.00);				
				double  totalResult = (changOfPercent1-changOfPercent2);
				
				vo.setExciseRegisCapital(count2);
				vo.setPayingtax(String.valueOf(count2+count));
				vo.setChange(String.format("%,.2f", totalResult));
				
		
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

}
