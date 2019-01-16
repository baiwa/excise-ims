	package th.co.baiwa.excise.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.excise.oa.persistence.entity.OaOperPlan;
import th.co.baiwa.excise.oa.service.COP013Service;

@Controller
@RequestMapping("api/oa/cop013")
public class COP013Controller {
	
	@Autowired
	private COP013Service cop013Service;
	
//	@PostMapping("/oa_oper_plan/datatable")
//	@ResponseBody
//	public ResponseDataTable<OaOperPlan> oaOperPlan(DataTableRequest dataTableRequest){
//		return cop013Service.findAllOaOperPlan();
//	}
	
	@PostMapping("/searchOaOperPlan")
	@ResponseBody
	public ResponseDataTable<OaOperPlan> searchOaOperPlan(DataTableRequest dataTableRequest, OaOperPlan oaOperPlan){
		return cop013Service.findByCriteriaForDatatable(oaOperPlan, dataTableRequest);
	}
	
}
