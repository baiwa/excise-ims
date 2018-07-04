package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
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
		if (travelCostWorkSheetHeaderDao.insertTravelCostWorksheetHeader(travelCostWsIntegrate) > 0) {
			List<TravelCostWorkSheetHeader> header= travelCostWorkSheetHeaderDao.queryTravelCostWorksheetHeader();
			if (header.toArray().length > 0) {
				List<TravelCostWsDetail> travel = new ArrayList<TravelCostWsDetail>();
				for(TravelCostWsDetail t: travelCostWsIntegrate.getDetail()) {
					t.setHeaderId(header.get(0).getWorkSheetHeaderId().toString());
					travel.add(t);
				}
				travelCostWsDetailDao.insertTravelCostWsDetail(travel);
			}
		}
	}
	
}
