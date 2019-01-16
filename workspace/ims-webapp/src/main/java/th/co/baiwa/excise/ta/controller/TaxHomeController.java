package th.co.baiwa.excise.ta.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.TaxHomeFormVo;
import th.co.baiwa.excise.ta.persistence.vo.TaxHomeVo;
import th.co.baiwa.excise.ta.service.TaxHomeService;

@Controller	
@RequestMapping("/api/taxHome")
public class TaxHomeController {
	
	@Autowired
	private TaxHomeService taxHomeService;

	private Logger logger = LoggerFactory.getLogger(TaxHomeController.class);

	@PostMapping("/selectType")
	@ResponseBody
	public DataTableAjax<TaxHomeVo> selectType(@RequestBody TaxHomeFormVo formVo){
		DataTableAjax<TaxHomeVo> list = null;
		logger.debug("TaxHomeController selectType");
		try {
			list = taxHomeService.selectType(formVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
