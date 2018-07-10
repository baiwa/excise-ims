package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostWorkSheetHeaderDao;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostWsDetailDao;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWorkSheetHeader;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsDetail;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsIntegrate;

@Service
public class Int09Service {

	@Autowired
	private TravelCostWorkSheetHeaderDao travelCostWorkSheetHeaderDao;

	@Autowired
	private TravelCostWsDetailDao travelCostWsDetailDao;

	public Integer deleteTravelCostHeader(String id) {
		if (travelCostWorkSheetHeaderDao.deleteTravelCostWorksheetHeader(new BigDecimal(id)) > 0) {
			return travelCostWsDetailDao.deleteTravelCostWorksheetDetail(new BigDecimal(id));
		} else {
			return 0;
		}
	}

	public List<TravelCostWorkSheetHeader> listTravelCostHeaderService(TravelCostWorkSheetHeader travel) {

		// Query Data
		List<TravelCostWorkSheetHeader> result = travelCostWorkSheetHeaderDao.queryTravelCostWorksheetHeader(travel);

		// Return Result
		return result;
	}

	public List<TravelCostWsIntegrate> listTravelCostService(TravelCostWorkSheetHeader travel) {
		// Initial Objects
		List<TravelCostWsIntegrate> result = new ArrayList<TravelCostWsIntegrate>();
		TravelCostWsDetail detail = new TravelCostWsDetail();
		detail.setHeaderId(travel.getWorkSheetHeaderId().toString());
		TravelCostWsIntegrate ti = new TravelCostWsIntegrate();

		// Query Data
		List<TravelCostWorkSheetHeader> header = travelCostWorkSheetHeaderDao.queryTravelCostWorksheetHeader(travel);
		List<TravelCostWsDetail> details = travelCostWsDetailDao.queryTravelCostWsDetail(detail);

		// Mapping Data
		ti.setWorkSheetHeaderId(header.get(0).getWorkSheetHeaderId());
		ti.setWorkSheetHeaderName(header.get(0).getWorkSheetHeaderName());
		ti.setDepartmentName(header.get(0).getDepartmentName());
		ti.setStartDate(header.get(0).getStartDate());
		ti.setEndDate(header.get(0).getEndDate());
		ti.setDescription(header.get(0).getDescription());
		ti.setCreatedBy(header.get(0).getCreatedBy());
		ti.setCreatedDate(header.get(0).getCreatedDate());
		ti.setUpdatedBy(header.get(0).getUpdatedBy());
		ti.setUpdatedDate(header.get(0).getUpdatedDate());
		ti.setDetail(details);

		// Add to ArrayList
		result.add(ti);

		// Return Result
		return result;
	}

	public void createTravelCostService(TravelCostWsIntegrate travelCostWsIntegrate) {
		// Creator
		String creator = UserLoginUtils.getCurrentUsername();

		// New Date
		Date date = new Date();

		// Set data to HeaderCost
		travelCostWsIntegrate.setCreatedBy(creator);
		travelCostWsIntegrate.setUpdatedBy(creator);
		travelCostWsIntegrate.setCreatedDate(date);
		travelCostWsIntegrate.setUpdatedDate(date);

		// Insert data to HeaderCost
		if (travelCostWorkSheetHeaderDao.insertTravelCostWorksheetHeader(travelCostWsIntegrate) > 0) {
			// Get ID HeaderCost
			List<TravelCostWorkSheetHeader> header = travelCostWorkSheetHeaderDao
					.queryTravelCostWorksheetHeader("WORK_SHEET_HEADER_ID DESC");

			if (header.toArray().length > 0) {
				// New ArrayList
				List<TravelCostWsDetail> travel = new ArrayList<TravelCostWsDetail>();

				for (TravelCostWsDetail t : travelCostWsIntegrate.getDetail()) {
					// Set data to DetailCost
					t.setHeaderId(header.get(0).getWorkSheetHeaderId().toString());
					t.setCreatedBy(creator);
					t.setUpdatedBy(creator);
					t.setCreatedDate(date);
					t.setUpdatedDate(date);

					// add `TravelCostWsDetail t` to ArrayList
					travel.add(t);
				}

				// Insert data to DetailsCost
				travelCostWsDetailDao.insertTravelCostWsDetail(travel);
			}

		}
	}

}
