package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.Int014Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcOtherDtl;
import th.co.baiwa.excise.ia.persistence.vo.Int0611FormVo;
import th.co.baiwa.excise.ia.service.Int06111Service;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;

@Controller
@RequestMapping("api/ia/int014")
public class Int014Controller {
	private Logger logger = LoggerFactory.getLogger(Int014Controller.class);
	@Autowired
	private UploadFileExciseService uploadFileExciseService;
	
	@Autowired
	private Int06111Service int0611Service;
	
	@PostMapping("uploadFileExcel")
	@ResponseBody
	public List<String[]> uploadFileExcel(@ModelAttribute Int014Vo int014Vo) throws Exception {
		logger.info("uploadFileExcel");
		List<String[]> listfileEx = null;
		List<RiskAssExcOtherDtl> excelData = new ArrayList<RiskAssExcOtherDtl>();
		if (int014Vo.getFileExel() != null) {
			RiskAssExcOtherDtl row = new RiskAssExcOtherDtl();
			Int0611FormVo int06112FormVo = new Int0611FormVo();
			int06112FormVo.setFileName(int014Vo.getFileExel());
			listfileEx = uploadFileExciseService.readFileExcelEx(int014Vo.getFileExel());
			
		}
		return listfileEx;
	}
}
