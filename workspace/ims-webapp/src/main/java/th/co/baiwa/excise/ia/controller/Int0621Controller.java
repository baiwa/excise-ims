package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;
import th.co.baiwa.excise.ia.persistence.entity.CwpTblDtl;
import th.co.baiwa.excise.ia.persistence.vo.Int0621CompareFormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0621Vo;
import th.co.baiwa.excise.ia.service.Int0621Service;

@Controller
@RequestMapping("api/ia/int0621")
public class Int0621Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0621Service int0621Service;
	
	@PostMapping("/budgetCodeList")
	@ResponseBody
	public List<CwpScwdDtl> getbudgetCodeList(@RequestBody CwpScwdDtl en) {
		logger.info("Query budgetCode: {}",en.getCwpScwdHdrId());
		return int0621Service.getbudgetCodeList(en);
	}
	
	@PostMapping("/dropdownT")
	@ResponseBody
	public List<CwpTblDtl> dropdownT(@RequestBody CwpTblDtl en) {
		logger.info("Query Find TR/TD: {}",en.getCwpTblHdrId());
		return int0621Service.getdropdownT(en);
	}
	
	@PostMapping("/compareExcel")
	@ResponseBody
	public List<Int0621Vo> compareExcel(@RequestBody List<Int0621CompareFormVo> formVo) {
		logger.info("Query compareExcel");
		return int0621Service.compareExcel(formVo);
	}

}
