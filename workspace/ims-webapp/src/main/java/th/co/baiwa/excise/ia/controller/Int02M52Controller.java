package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.domain.datatable.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.IaIceReportHdr;
import th.co.baiwa.excise.ia.service.Int02m052Service;

@Controller
@RequestMapping("api/ia/02m052")
public class Int02M52Controller {
	
	@Autowired
	private Int02m052Service int02m052Service;
	
	@PostMapping("/searchIaIceReportHdr")
	@ResponseBody
	public ResponseDataTable<IaIceReportHdr> searchIaIceHdr(DataTableRequest datatableRequest, IaIceReportHdr iaIceReeportHdr){
		return int02m052Service.findByCriteriaForDatatable(datatableRequest, iaIceReeportHdr);
	}

}
