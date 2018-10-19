package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.Int018Vo;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;

@Controller
@RequestMapping("api/ia/int018")
public class Int018Controller {
	private Logger logger = LoggerFactory.getLogger(Int018Controller.class);

	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	private final String RESPONSE_CODE_SUCCESS = "200";
 
	@PostMapping("/exciseWebService8020")
	@ResponseBody
	public List<IncomeList> exciseWebService8020(@RequestBody Int018Vo int018Vo) {
		logger.info("exciseWebService8020");
		List<IncomeList> wsData = new ArrayList<IncomeList>();
			IncFri8020 incFri8020  = webServiceExciseService.IncFri8020(int018Vo.getOfficeCode(), int018Vo.getYearMonthFrom().substring(0, 6), int018Vo.getYearMonthTo().substring(0, 6), int018Vo.getDateType(), "1", "1000");
			if (RESPONSE_CODE_SUCCESS.equals(incFri8020.getResponseCode())) {
				if (BeanUtils.isNotEmpty(incFri8020.getResponseData()) && BeanUtils.isNotEmpty(incFri8020.getResponseData().getIncomeList())) {
					wsData = incFri8020.getResponseData().getIncomeList();
				}
			}
		
		return wsData;
	}

}
