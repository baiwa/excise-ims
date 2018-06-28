package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.dao.TravelCostWorkSheetHeaderDao;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostWsDetailDao;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsIntegrate;

@Service
public class Int09Service {
     
	@Autowired
	private TravelCostWorkSheetHeaderDao  travelCostWorkSheetHeaderDao;

	@Autowired
	private TravelCostWsDetailDao travelCostWsDetailDao;	
	
	public void createTravelCostService(TravelCostWsIntegrate travelCostWsIntegrate ){
		travelCostWorkSheetHeaderDao.insertTravelCostWorksheetHeader(travelCostWsIntegrate);
		
	}
	
}
