package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
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

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.service.Int030102Service;
import th.go.excise.ims.ia.vo.Int030102FormVo;

@Controller
@RequestMapping("/api/ia/int03/01/02")
public class Int030102Controller {

	private Logger logger = LoggerFactory.getLogger(Int030102Controller.class);

	@Autowired
	private Int030102Service int030102Service;

	@PostMapping("/list")
	@ResponseBody
	public ResponseData<List<IaRiskFactorsMaster>> list(@RequestBody Int030102FormVo form) {
		ResponseData<List<IaRiskFactorsMaster>> response = new ResponseData<List<IaRiskFactorsMaster>>();
		List<IaRiskFactorsMaster> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();

		try {
			iaRiskFactorsMasterList = int030102Service.list(form);
			response.setData(iaRiskFactorsMasterList);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller List : ", e);
			response.setMessage("ERROR");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<BigDecimal> delete(@RequestBody Int030102FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int030102Service.delete(form);
			response.setData(id);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller Delete : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/editStatus")
	@ResponseBody
	public ResponseData<BigDecimal> status(@RequestBody Int030102FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int030102Service.editStatus(form);
			response.setData(id);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller Delete : ", e);
			response.setMessage("ERROR");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<String> save(@RequestBody Int030102FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		String save = "บันทึกเรียบร้อบ";

		try {
			int030102Service.save(form);
			response.setData(save);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller Delete : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}