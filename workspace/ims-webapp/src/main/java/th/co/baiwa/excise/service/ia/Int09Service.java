package th.co.baiwa.excise.service.ia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.ia.TravelCostWsIntegrate;
import th.co.baiwa.excise.persistence.dao.ia.TravelCostWorkSheetHeaderDao;
import th.co.baiwa.excise.persistence.dao.ia.TravelCostWsDetailDao;

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
