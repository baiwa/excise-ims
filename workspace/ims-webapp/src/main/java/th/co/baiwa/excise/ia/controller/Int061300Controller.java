package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.RentHouseRule;
import th.co.baiwa.excise.ia.service.RentHouseRuleService;

@Controller
@RequestMapping("api/ia/int061300")
public class Int061300Controller {

	@Autowired
	private RentHouseRuleService rentHouseRuleService;

	@GetMapping("/findAll")
	@ResponseBody
	public List<RentHouseRule> findAll() {
		return rentHouseRuleService.findAllDao();
	}

	@PostMapping("/findAdministrativePosition")
	@ResponseBody
	public ResponseDataTable<RentHouseRule> findAdministrativePosition(DataTableRequest dataTableRequest) {
		return rentHouseRuleService.administrativeForDatatable(dataTableRequest);
	}

	@PostMapping("/findDirectorPosition")
	@ResponseBody
	public ResponseDataTable<RentHouseRule> findDirectorPosition(DataTableRequest dataTableRequest) {
		return rentHouseRuleService.directorForDatatable(dataTableRequest);
	}

	@PostMapping("/findAcademicPosition")
	@ResponseBody
	public ResponseDataTable<RentHouseRule> findAcademicPosition(DataTableRequest dataTableRequest) {
		return rentHouseRuleService.academicForDatatable(dataTableRequest);
	}

	@PostMapping("/findGeneralPosition")
	@ResponseBody
	public ResponseDataTable<RentHouseRule> findGeneralPosition(DataTableRequest dataTableRequest) {
		return rentHouseRuleService.generalForDatatable(dataTableRequest);
	}

	@PostMapping("/update")
	@ResponseBody
	public void update(@RequestBody RentHouseRule rentHouseRule) {
		rentHouseRuleService.update(rentHouseRule);
	}

}
