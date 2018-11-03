package th.co.baiwa.excise.ia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.CommonResponse;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.COMMON_STATUS;
import th.co.baiwa.excise.ia.persistence.vo.Int0142Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int014Vo;
import th.co.baiwa.excise.ia.persistence.vo.TrialBalanceCalcRow;
import th.co.baiwa.excise.ia.persistence.vo.TrialBalanceVo;
import th.co.baiwa.excise.ia.service.Int014Service;

@Controller
@RequestMapping("api/ia/int014")
public class Int014Controller {
	private Logger logger = LoggerFactory.getLogger(Int014Controller.class);
	private final String INT014_UPLOADKEY = "INT014_UPLOADKEY";
	
	@Autowired
	private Int014Service int014Service;
	
	@PostMapping("uploadFileExcel")
	@ResponseBody
	public CommonResponse<Int014Vo>  uploadFileExcel(@ModelAttribute Int014Vo int014Vo, HttpServletRequest httpServletRequest) throws Exception {
		
		CommonResponse<Int014Vo> resp = new CommonResponse<Int014Vo>();
		try {
			HttpSession session = httpServletRequest.getSession();
			List<TrialBalanceVo> readLine = int014Service.processData(int014Vo);
			Int014Vo vo = new  Int014Vo();			
			vo.setLines(readLine);
			resp.setData(vo);
			session.setAttribute(INT014_UPLOADKEY, vo);
			resp.setStatus(COMMON_STATUS.SUCCESS);
		}catch (Exception e) {
			resp.setStatus(COMMON_STATUS.ERROR);
			resp.setDesc(COMMON_STATUS.ERROR_DESC);
			logger.error("uploadFileExcel",e);
		}

		return resp;
		
	}
	
	
	@PostMapping("compair")
	@ResponseBody
	public CommonResponse<Int0142Vo>  compair(@RequestBody Int0142Vo int014Vo, HttpServletRequest httpServletRequest) throws Exception {
		
		CommonResponse<Int0142Vo> resp = new CommonResponse<Int0142Vo>();
		try {

			HttpSession session = httpServletRequest.getSession();
			Int014Vo sessionUpload  = (Int014Vo) session.getAttribute(INT014_UPLOADKEY);
			
			List<TrialBalanceCalcRow> res = int014Service.compairData(int014Vo, sessionUpload);
			
			Int0142Vo vo = new  Int0142Vo();	
			vo.setDatas(res);
			resp.setData(vo);
			resp.setStatus(COMMON_STATUS.SUCCESS);
		}catch (Exception e) {
			resp.setStatus(COMMON_STATUS.ERROR);
			resp.setDesc(COMMON_STATUS.ERROR_DESC);
			logger.error("uploadFileExcel",e);
		}

		return resp;
		
	}
}
