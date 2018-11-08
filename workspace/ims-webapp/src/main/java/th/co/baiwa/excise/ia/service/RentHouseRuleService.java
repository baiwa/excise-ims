package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.dao.RentHouseRuleDao;
import th.co.baiwa.excise.ia.persistence.entity.RentHouseRule;
import th.co.baiwa.excise.ia.persistence.repository.RentHouseRuleRepository;

@Service
public class RentHouseRuleService {

	@Autowired
	private RentHouseRuleRepository rentHouseRuleRepository;

	@Autowired
	private RentHouseRuleDao rentHouseRuleDao;

	public List<RentHouseRule> findAll() {
		return rentHouseRuleRepository.findAll();
	}

	public List<RentHouseRule> findAllDao() {
		return rentHouseRuleDao.findAll();
	}

	public List<RentHouseRule> findAdministrativePosition() {
		return rentHouseRuleDao.findAdministrativePosition();
	}

	public List<RentHouseRule> findDirectorPosition() {
		return rentHouseRuleDao.findDirectorPosition();
	}

	public List<RentHouseRule> findAcademicPosition() {
		return rentHouseRuleDao.findAcademicPosition();
	}

	public List<RentHouseRule> findGeneralPosition() {
		return rentHouseRuleDao.findGeneralPosition();
	}

	public ResponseDataTable<RentHouseRule> administrativeForDatatable(DataTableRequest dataTableRequest) {
		ResponseDataTable<RentHouseRule> responseDataTable = new ResponseDataTable<RentHouseRule>();
		List<RentHouseRule> rentHouseRuleList = rentHouseRuleDao.findAdministrativePosition();

		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(rentHouseRuleList);
		responseDataTable.setRecordsTotal((int) rentHouseRuleList.size());
		responseDataTable.setRecordsFiltered((int) rentHouseRuleList.size());
		return responseDataTable;

	}

	public ResponseDataTable<RentHouseRule> directorForDatatable(DataTableRequest dataTableRequest) {
		ResponseDataTable<RentHouseRule> responseDataTable = new ResponseDataTable<RentHouseRule>();
		List<RentHouseRule> rentHouseRuleList = rentHouseRuleDao.findDirectorPosition();

		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(rentHouseRuleList);
		responseDataTable.setRecordsTotal((int) rentHouseRuleList.size());
		responseDataTable.setRecordsFiltered((int) rentHouseRuleList.size());
		return responseDataTable;

	}

	public ResponseDataTable<RentHouseRule> academicForDatatable(DataTableRequest dataTableRequest) {
		ResponseDataTable<RentHouseRule> responseDataTable = new ResponseDataTable<RentHouseRule>();
		List<RentHouseRule> rentHouseRuleList = rentHouseRuleDao.findAcademicPosition();

		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(rentHouseRuleList);
		responseDataTable.setRecordsTotal((int) rentHouseRuleList.size());
		responseDataTable.setRecordsFiltered((int) rentHouseRuleList.size());
		return responseDataTable;

	}

	public ResponseDataTable<RentHouseRule> generalForDatatable(DataTableRequest dataTableRequest) {
		ResponseDataTable<RentHouseRule> responseDataTable = new ResponseDataTable<RentHouseRule>();
		List<RentHouseRule> rentHouseRuleList = rentHouseRuleDao.findGeneralPosition();

		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(rentHouseRuleList);
		responseDataTable.setRecordsTotal((int) rentHouseRuleList.size());
		responseDataTable.setRecordsFiltered((int) rentHouseRuleList.size());
		return responseDataTable;

	}

	public void update(RentHouseRule rentHouseRule) {
		RentHouseRule rentHouse = rentHouseRuleRepository.findOne(rentHouseRule.getRuleId());
		rentHouse.setSalaryFrom(rentHouseRule.getSalaryFrom());
		rentHouse.setSalaryTo(rentHouseRule.getSalaryTo());
		rentHouse.setRentAmount(rentHouseRule.getRentAmount());
		rentHouseRuleRepository.save(rentHouse);

	}

}
