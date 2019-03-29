package th.go.excise.ims.ta.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.rest.adapter.BigDecimalTypeAdapter;
import th.co.baiwa.buckwaframework.common.rest.adapter.DateThaiTypeAdapter;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.service.TaFormTS0101Service;
import th.go.excise.ims.ta.service.TaFormTS0107Service;
import th.go.excise.ims.ta.service.TaFormTS0108Service;
import th.go.excise.ims.ta.service.TaFormTS0109Service;
import th.go.excise.ims.ta.service.TaFormTS01101Service;
import th.go.excise.ims.ta.service.TaFormTS0110Service;
import th.go.excise.ims.ta.service.TaFormTS0111Service;
import th.go.excise.ims.ta.service.TaFormTS0112Service;
import th.go.excise.ims.ta.service.TaFormTS0113Service;
import th.go.excise.ims.ta.service.TaFormTS01142Service;
import th.go.excise.ims.ta.service.TaFormTS0114Service;
import th.go.excise.ims.ta.service.TaFormTS0115Service;
import th.go.excise.ims.ta.service.TaFormTS0116Service;
import th.go.excise.ims.ta.service.TaFormTS01171Service;
import th.go.excise.ims.ta.service.TaFormTS0118Service;
import th.go.excise.ims.ta.service.TaFormTS0119Service;
import th.go.excise.ims.ta.service.TaFormTS0120Service;
import th.go.excise.ims.ta.service.TaFormTS0121Service;
import th.go.excise.ims.ta.vo.TaFormTS0109Vo;
import th.go.excise.ims.ta.vo.TaFormTS0114Vo;

@Controller
@RequestMapping("/api/ta/set-form-ts")
public class TaSetFormTSController {

	private static final Logger logger = LoggerFactory.getLogger(TaSetFormTSController.class);

	private Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(Date.class, DateThaiTypeAdapter.getInstance()).registerTypeAdapter(BigDecimal.class, BigDecimalTypeAdapter.getInstance()).create();

	private TaFormTS0101Service taFormTS0101Service;
	private TaFormTS0107Service taFormTS0107Service;
	private TaFormTS0108Service taFormTS0108Service;
	private TaFormTS0109Service taFormTS0109Service;
	private TaFormTS0110Service taFormTS0110Service;
	private TaFormTS01101Service taFormTS01101Service;
	private TaFormTS0111Service taFormTS0111Service;
	private TaFormTS0112Service taFormTS0112Service;
	private TaFormTS0113Service taFormTS0113Service;
	private TaFormTS01142Service taFormTS01142Service;
	private TaFormTS0114Service taFormTS0114Service;
	private TaFormTS0115Service taFormTS0115Service;
	private TaFormTS0116Service taFormTS0116Service;
	private TaFormTS01171Service taFormTS01171Service;
	private TaFormTS0118Service taFormTS0118Service;
	private TaFormTS0119Service taFormTS0119Service;
	private TaFormTS0120Service taFormTS0120Service;
	private TaFormTS0121Service taFormTS0121Service;

	@Autowired
	public TaSetFormTSController(TaFormTS0101Service taFormTS0101Service, TaFormTS0107Service taFormTS0107Service, TaFormTS0108Service taFormTS0108Service, TaFormTS0109Service taFormTS0109Service, TaFormTS0110Service taFormTS0110Service, TaFormTS0111Service taFormTS0111Service,
                                 TaFormTS01101Service taFormTS01101Service, TaFormTS0112Service taFormTS0112Service, TaFormTS0113Service taFormTS0113Service, TaFormTS0114Service taFormTS0114Service, TaFormTS01142Service taFormTS01142Service, TaFormTS0115Service taFormTS0115Service,
                                 TaFormTS01171Service taFormTS01171Service, TaFormTS0118Service taFormTS0118Service, TaFormTS0116Service taFormTS0116Service, TaFormTS0119Service taFormTS0119Service, TaFormTS0120Service taFormTS0120Service, TaFormTS0121Service taFormTS0121Service) {

		this.taFormTS0101Service = taFormTS0101Service;
		this.taFormTS0107Service = taFormTS0107Service;
		this.taFormTS0108Service = taFormTS0108Service;
		this.taFormTS0109Service = taFormTS0109Service;
		this.taFormTS0110Service = taFormTS0110Service;
		this.taFormTS01101Service = taFormTS01101Service;
		this.taFormTS0111Service = taFormTS0111Service;
		this.taFormTS0112Service = taFormTS0112Service;
		this.taFormTS0113Service = taFormTS0113Service;
		this.taFormTS0114Service = taFormTS0114Service;
		this.taFormTS01142Service = taFormTS01142Service;
		this.taFormTS0115Service = taFormTS0115Service;
		this.taFormTS01171Service = taFormTS01171Service;
		this.taFormTS0118Service = taFormTS0118Service;
		this.taFormTS0119Service = taFormTS0119Service;
		this.taFormTS0120Service = taFormTS0120Service;
		this.taFormTS0121Service = taFormTS0121Service;
		this.taFormTS0116Service= taFormTS0116Service;
	}



	// TODO =================>  Get Form TS number
	// TODO Form TS number ts 09
	@PostMapping("/ta-form-ts0109")
	@ResponseBody
	public ResponseData<String> getformTs09List(@RequestBody TaFormTS0109Vo formVo) {
		logger.info("getformTsList");

		ResponseData<String> response = new ResponseData<>();
		try {
			response.setData(gson.toJson(taFormTS0109Service.getFormTS(formVo.getFormTsNumber())));

			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	// TODO Form TS number ts 14
	@PostMapping("/ta-form-ts0114")
	@ResponseBody
	public ResponseData<String> getformTs14List(@RequestBody TaFormTS0114Vo formVo) {
		logger.info("getformTsList");

		ResponseData<String> response = new ResponseData<>();
		try {
			response.setData(gson.toJson(taFormTS0114Service.getFormTS(formVo.getFormTsNumber())));
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	// TODO Form TS number ts 20
	@PostMapping("/ta-form-ts0120")
	@ResponseBody
	public ResponseData<TaFormTS0120Vo> getformTs20List(@RequestBody TaFormTS0120Vo formVo) {
		logger.info("getformTs20List");

		ResponseData<TaFormTS0120Vo> response = new ResponseData<>();
		try {
			response.setData(taFormTS0120Service.getFormTS(formVo.getFormTsNumber()));
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	// TODO Form TS number ts 21
	@PostMapping("/ta-form-ts0121")
	@ResponseBody
	public ResponseData<TaFormTS0121Vo> getformTs21List(@RequestBody TaFormTS0121Vo formVo) {
		logger.info("getformTs21List");
		
		ResponseData<TaFormTS0121Vo> response = new ResponseData<>();
		try {
			response.setData(taFormTS0121Service.getFormTS(formVo.getFormTsNumber()));
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
