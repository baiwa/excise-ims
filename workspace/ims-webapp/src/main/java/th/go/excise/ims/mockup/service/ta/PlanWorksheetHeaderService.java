package th.go.excise.ims.mockup.service.ta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
			
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.constant.DateConstant;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.persistence.dao.ExciseRegisttionNumberDao;
import th.go.excise.ims.mockup.persistence.dao.ExciseTaxReceiveDao;
import th.go.excise.ims.mockup.persistence.dao.ta.PlanWorksheetDetailDao;
import th.go.excise.ims.mockup.persistence.dao.ta.PlanWorksheetHeaderDao;
import th.go.excise.ims.mockup.persistence.entity.ExciseRegistartionNumber;
import th.go.excise.ims.mockup.persistence.entity.ExciseTaxReceive;
import th.go.excise.ims.mockup.persistence.entity.ta.PlanWorksheetDetail;
import th.go.excise.ims.mockup.persistence.entity.ta.PlanWorksheetHeader;

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
	
	public String insertPlanWorksheetHeaderService(MockupVo mockupVo,Date startBackDate, int month , String productType) {

		logger.info("PlanWorksheetHeaderService.insertPlanWorksheetHeaderService");
		String analysNumber = DateConstant.DateToString(new Date(), DateConstant.YYYYMMDD)+"-01-"+planWorksheetHeaderDao.getAnalysNumber();
		Date saveDate = new Date();
		logger.info("get analysNumber : "+analysNumber);
		PlanWorksheetHeader planWorksheetHeader = null;
		List<ExciseTaxReceive> taxReciveList = null;
		List<ExciseRegistartionNumber> regisNumberList = exciseRegisttionNumberDao.queryByExciseRegistionNumber(productType);
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
			double firstAmount = 0;
			double lastAmount = 0;
			BigDecimal percenttage = new BigDecimal(0);
			if(taxReciveList != null && taxReciveList.size()  > 0) {
				for (int i = 0 ; i < taxReciveList.size() ; i++) {
					ExciseTaxReceive taxRecive = taxReciveList.get(i);
					
					String amount = taxRecive.getExciseTaxReceiveAmount() != null ? taxRecive.getExciseTaxReceiveAmount().trim().replaceAll(",","") : "0";
					try {
						totalAmount = totalAmount.add(new BigDecimal(amount));
					} catch (Exception e) {
						totalAmount = totalAmount.add(new BigDecimal(0));
					}
					if(taxRecive.getExciseTaxReceiveMonth() != null && taxRecive.getExciseTaxReceiveMonth().length() > 0) {
						countReciveMonth++;
						if( (i+1) < taxReciveList.size()/2 ) {
							firstMonth++;
							firstAmount += Double.parseDouble(amount);
						}else {
							lastMonth++;
							lastAmount += Double.parseDouble(amount);
						}
					}
				}
				double percetFirstAmount = firstAmount / totalAmount.doubleValue() * 100 ;
				double percetLastAmount = lastAmount / totalAmount.doubleValue() * 100 ;
				
				try {
					double calAmount = percetLastAmount - percetFirstAmount;
//					String strPercen =  (calAmount)+"".split(".")[0]+"."+(calAmount)+"".split(".")[1].substring(0, 2);
					percenttage = new BigDecimal(calAmount);
					percenttage.setScale(2, RoundingMode.UP);
				} catch (Exception e) {
					e.printStackTrace();
					percenttage = new BigDecimal(0);
				}
			}
			
			
			System.out.println(percenttage);
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
				String amountDetail = taxRecive.getExciseTaxReceiveAmount()  != null? taxRecive.getExciseTaxReceiveAmount().trim().replaceAll(",","") :"0" ;
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
