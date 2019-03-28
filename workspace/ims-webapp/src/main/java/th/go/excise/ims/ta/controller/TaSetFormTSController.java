package th.go.excise.ims.ta.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.buckwaframework.common.bean.ReportJsonBean;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.rest.adapter.BigDecimalTypeAdapter;
import th.co.baiwa.buckwaframework.common.rest.adapter.DateThaiTypeAdapter;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.service.*;
import th.go.excise.ims.ta.vo.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
	public ResponseData<TaFormTS0109Vo> getformTs09List(@RequestBody TaFormTS0109Vo formVo) {
		logger.info("getformTsList");

		ResponseData<TaFormTS0109Vo> response = new ResponseData<>();
		try {
			response.setData(taFormTS0109Service.getFormTS(formVo.getFormTsNumber()));
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
	public ResponseData<TaFormTS0114Vo> getformTs14List(@RequestBody TaFormTS0114Vo formVo) {
		logger.info("getformTsList");

		ResponseData<TaFormTS0114Vo> response = new ResponseData<>();
		try {
			response.setData(taFormTS0114Service.getFormTS(formVo.getFormTsNumber()));
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
