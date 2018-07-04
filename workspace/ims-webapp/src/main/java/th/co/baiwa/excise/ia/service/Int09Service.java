package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWorkSheetHeader;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsDetail;
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
		// query success
		if (travelCostWorkSheetHeaderDao.insertTravelCostWorksheetHeader(travelCostWsIntegrate) > 0) {
			// travelCostWsDetailDao.insertTravelCostWsDetails(travelCostWsIntegrate.getDetail());
			List<TravelCostWorkSheetHeader> header= travelCostWorkSheetHeaderDao.queryTravelCostWorksheetHeader();
			if (header.toArray().length > 0) {
				for(TravelCostWsDetail travel: travelCostWsIntegrate.getDetail()) {
					travel.setHeaderId(header.get(0).getWorkSheetHeaderId().toString());
					travelCostWsDetailDao.insertTravelCostWsDetail(travel);
				}
			}
		}
	}
	
}
