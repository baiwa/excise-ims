package th.go.excise.ims.mockup.service.ta;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.dao.RoleDao;
import th.co.baiwa.excise.ia.constant.DateConstant;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.persistence.dao.ExciseRegisttionNumberDao;
import th.go.excise.ims.mockup.persistence.dao.ExciseTaxReceiveDao;
import th.go.excise.ims.mockup.persistence.dao.ta.PlanWorksheetHeaderDao;
import th.go.excise.ims.mockup.persistence.entity.ExciseRegistartionNumber;
import th.go.excise.ims.mockup.persistence.entity.ExciseTaxReceive;
import th.go.excise.ims.mockup.persistence.entity.ta.PlanWorksheetHeader;

@Service
public class PlanWorksheetHeaderService {

	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;
	
	@Autowired
	private ExciseRegisttionNumberDao exciseRegisttionNumberDao;

	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;
	
	private Logger logger = LoggerFactory.getLogger(PlanWorksheetHeaderService.class);
	
	public void insertPlanWorksheetHeaderService(MockupVo mockupVo,Date startBackDate, int month) {
		logger.info("PlanWorksheetHeaderService.insertPlanWorksheetHeaderService");
		String analysNumber = DateConstant.DateToString(new Date(), DateConstant.YYYYMMDD)+"-01-"+planWorksheetHeaderDao.getAnalysNumber();
		
		logger.info("get analysNumber : "+analysNumber);
		System.out.println(analysNumber);
		PlanWorksheetHeader planWorksheetHeader = null;
		List<ExciseTaxReceive> taxReciveList = null;
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseRegistionNumber();
		for (ExciseRegistartionNumber exciseRegistartionNumber : regisNumberList) {
			planWorksheetHeader = new PlanWorksheetHeader();
			planWorksheetHeader.setAnalysNumber(analysNumber);
			planWorksheetHeader.setExciseId(exciseRegistartionNumber.getExciseId());
			planWorksheetHeader.setCompanyName(exciseRegistartionNumber.getExciseOperatorName());
			planWorksheetHeader.setFactoryName(exciseRegistartionNumber.getExciseFacName());
			planWorksheetHeader.setFactoryAddress(exciseRegistartionNumber.getExciseFacAddress());
			planWorksheetHeader.setExciseOwnerArea(exciseRegistartionNumber.getExciseArea());
			planWorksheetHeader.setProductType(exciseRegistartionNumber.getTaexciseProductType());
			planWorksheetHeader.setExciseOwnerArea1(exciseRegistartionNumber.getTaexciseSectorArea());
			taxReciveList = exciseTaxReceiveDao.queryByExciseId(exciseRegistartionNumber.getExciseId());
			BigDecimal totalAmount = new BigDecimal(0);
			int countReciveMonth = 0;
			int firstMonth = 0;
			int lastMonth = 0;
			if(taxReciveList != null) {
				for (int i = 0 ; i < taxReciveList.size() ; i++) {
					ExciseTaxReceive taxRecive = taxReciveList.get(i);
					
					String amount = taxRecive.getExciseTaxReceiveAmount() != null ? taxRecive.getExciseTaxReceiveAmount() : "0";
					totalAmount.add(new BigDecimal(amount));
					
					if(taxRecive.getExciseTaxReceiveMonth() != null && taxRecive.getExciseTaxReceiveMonth().length() > 0) {
						countReciveMonth++;
						if( (i+1) < taxReciveList.size()/2 ) {
							firstMonth++;
						}else {
							lastMonth++;
						}
					}
					
					
					
				}
				
			}
			
			planWorksheetHeader.setTotalAmount(totalAmount);
			planWorksheetHeader.setTotalMonth(new BigDecimal(countReciveMonth));
//			planWorksheetHeader.setPercentage(percentage);
			planWorksheetHeader.setFirstMonth(new BigDecimal(firstMonth));
			planWorksheetHeader.setLastMonth(new BigDecimal(lastMonth));
			
			
			
		}
	}
	
	

	public PlanWorksheetHeaderDao getPlanWorksheetHeaderDao() {
		return planWorksheetHeaderDao;
	}

	public void setPlanWorksheetHeaderDao(PlanWorksheetHeaderDao planWorksheetHeaderDao) {
		this.planWorksheetHeaderDao = planWorksheetHeaderDao;
	}



	public ExciseRegisttionNumberDao getExciseRegisttionNumberDao() {
		return exciseRegisttionNumberDao;
	}



	public void setExciseRegisttionNumberDao(ExciseRegisttionNumberDao exciseRegisttionNumberDao) {
		this.exciseRegisttionNumberDao = exciseRegisttionNumberDao;
	}



	public ExciseTaxReceiveDao getExciseTaxReceiveDao() {
		return exciseTaxReceiveDao;
	}



	public void setExciseTaxReceiveDao(ExciseTaxReceiveDao exciseTaxReceiveDao) {
		this.exciseTaxReceiveDao = exciseTaxReceiveDao;
	}
	
	
}
