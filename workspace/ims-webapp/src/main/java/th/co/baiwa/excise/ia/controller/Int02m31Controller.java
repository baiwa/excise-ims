package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMinor;
import th.co.baiwa.excise.ia.persistence.vo.Int023Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int02m31FormVo;
import th.co.baiwa.excise.ia.service.QuestionnaireMainDetailService;

@Controller
@RequestMapping("api/ia/int02m31")
public class Int02m31Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	QuestionnaireMainDetailService questionnaireMainDetailService;

	@PostMapping("/getQtnDetailList")
	@ResponseBody
	public ResponseDataTable<Int023Vo<QuestionnaireMinor>> questionnaireDetail(@RequestBody DataTableRequest req) {
		logger.info("Query Detail");
		return questionnaireMainDetailService.findByCriteria(req);
	}
	
	@PostMapping("/saveDetailList")
	@ResponseBody
	public Message saveQtnDetail(@RequestBody List<Int02m31FormVo> listDetail) {
		logger.info("Saved to saveDetail");
		return questionnaireMainDetailService.saveQtnDetail(listDetail);
	}
	
	@PostMapping("/updateDetail/{id}")
	@ResponseBody
	public Message updateQtnDtl(@PathVariable("id") String id, @RequestBody Int02m31FormVo vo) {
		logger.info("Saved to updateQtnDtl {}", id);
		return questionnaireMainDetailService.updateQtnDtl(id, vo);
	}
	
	@DeleteMapping("/deleteMainDtl/{id}")
	@ResponseBody
	public Message deleteMainDtl(@PathVariable("id") String idList) {
		return questionnaireMainDetailService.deleteMainDtl(idList);
	}
	
	@DeleteMapping("/deleteMinorDtl/{id}")
	@ResponseBody
	public Message deleteMinorDtl(@PathVariable("id") String idList) {
		return questionnaireMainDetailService.deleteMinorDtl(idList);
	}
	
}
