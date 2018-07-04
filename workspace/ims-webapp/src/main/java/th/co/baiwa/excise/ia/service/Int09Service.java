package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWorkSheetHeader;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsDetail;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostWorkSheetHeaderDao;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostWsDetailDao;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsIntegrate;

@Service
public class Int09Service {

	@Autowired
	private TravelCostWorkSheetHeaderDao travelCostWorkSheetHeaderDao;

	@Autowired
	private TravelCostWsDetailDao travelCostWsDetailDao;

	public void createTravelCostService(TravelCostWsIntegrate travelCostWsIntegrate) {
		// Creator
		String creator = UserLoginUtils.getCurrentUsername();

		// New Date
		Date date = new Date();

		// Set data to HeaderCost
		travelCostWsIntegrate.setCreatedBy(creator);
		travelCostWsIntegrate.setUpdateBy(creator);
		travelCostWsIntegrate.setCreatedDatetime(date);
		travelCostWsIntegrate.setUpdateDatetime(date);

		// Insert data to HeaderCost
		if (travelCostWorkSheetHeaderDao.insertTravelCostWorksheetHeader(travelCostWsIntegrate) > 0) {
			// Get ID HeaderCost
			List<TravelCostWorkSheetHeader> header = travelCostWorkSheetHeaderDao.queryTravelCostWorksheetHeader("WORK_SHEET_HEADER_ID DESC");

			if (header.toArray().length > 0) {
				// New ArrayList
				List<TravelCostWsDetail> travel = new ArrayList<TravelCostWsDetail>();

				for (TravelCostWsDetail t : travelCostWsIntegrate.getDetail()) {
					// Set data to DetailCost
					t.setHeaderId(header.get(0).getWorkSheetHeaderId().toString());
					t.setCreatedBy(creator);
					t.setUpdateBy(creator);
					t.setCreatedDatetime(date);
					t.setUpdateDatetime(date);

					// add `TravelCostWsDetail t` to ArrayList
					travel.add(t);
				}

				// Insert data to DetailsCost
				travelCostWsDetailDao.insertTravelCostWsDetail(travel);
			}

		}
	}

}
