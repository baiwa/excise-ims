package th.go.excise.ims.preferences.controller;

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

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.vo.ExciseOrgGfmisVo;
import th.go.excise.ims.ia.vo.Int1504FormVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseTitle;
import th.go.excise.ims.preferences.service.Ed04Service;
import th.go.excise.ims.preferences.vo.Ed04FormSave;
import th.go.excise.ims.preferences.vo.ExcisePersonInfoVo;

@Controller
@RequestMapping("/api/ed/ed04")
public class Ed04Controller {
	
	private Logger logger = LoggerFactory.getLogger(Ed04Controller.class);
	
	@Autowired
	private Ed04Service ed04Service;
	
	@PostMapping("/listPersonThTitle")
	@ResponseBody
	public DataTableAjax<ExciseTitle> listPersonThTitle() {
		DataTableAjax<ExciseTitle> response = new DataTableAjax<ExciseTitle>();
		List<ExciseTitle> dataList = new ArrayList<ExciseTitle>();
		try {
			dataList = ed04Service.listPersonThTitle();
			response.setData(dataList);
		} catch (Exception e) {
			logger.error("Int1503Controller : ", e);
		}
		return response;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<ExcisePersonInfoVo> save(@RequestBody Ed04FormSave request) {
		ResponseData<ExcisePersonInfoVo> response = new ResponseData<ExcisePersonInfoVo>();
		try {
			response.setData(ed04Service.savePerson(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
