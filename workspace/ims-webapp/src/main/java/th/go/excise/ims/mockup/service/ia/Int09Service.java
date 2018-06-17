package th.go.excise.ims.mockup.service.ia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.mockup.domain.ia.TravelCostWorkSheetHeader;
import th.go.excise.ims.mockup.domain.ia.TravelCostWsDetail;
import th.go.excise.ims.mockup.persistence.dao.ia.TravelCostWorkSheetHeaderDao;
import th.go.excise.ims.mockup.persistence.dao.ia.TravelCostWsDetailDao;

@Service
public class Int09Service {
     
	@Autowired
	private TravelCostWorkSheetHeaderDao  travelCostWorkSheetHeaderDao;

	@Autowired
	private TravelCostWsDetailDao travelCostWsDetailDao;	
	
	public void createTravelCostService(TravelCostWorkSheetHeader travelCostWorkSheetHeader , TravelCostWsDetail travelCostWsDetail){
		travelCostWorkSheetHeaderDao.insertTravelCostWorksheetHeader(travelCostWorkSheetHeader);
		travelCostWsDetailDao.insertTravelCostWsDetail(travelCostWsDetail);
	}
	
}
