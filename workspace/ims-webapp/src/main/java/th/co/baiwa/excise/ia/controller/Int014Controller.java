package th.co.baiwa.excise.ia.controller;

import java.math.BigDecimal;
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
import th.co.baiwa.excise.upload.service.UploadFileExciseService;

@Controller
@RequestMapping("api/ia/int014")
public class Int014Controller {
	private Logger logger = LoggerFactory.getLogger(Int014Controller.class);
	@Autowired
	private UploadFileExciseService uploadFileExciseService;
	
	@PostMapping("uploadFileExcel")
	@ResponseBody
	public List<RiskAssExcOtherDtl> uploadFileExcel(@ModelAttribute Int014Vo int014Vo) throws Exception {
		logger.info("uploadFileExcel");
		List<RiskAssExcOtherDtl> excelData = new ArrayList<RiskAssExcOtherDtl>();
		if (int014Vo.getFileExel() != null) {
			RiskAssExcOtherDtl row = new RiskAssExcOtherDtl();
			List<String[]> ListfileEx = uploadFileExciseService.readFileExcel(int014Vo.getFileExel());
			for (int j = 1; j < ListfileEx.size(); j++) {
				String[] stringArr = ListfileEx.get(j);

				row = new RiskAssExcOtherDtl();
				for (int i = 0; i < stringArr.length; i++) {
					if (i == 0) {
						row.setRiskOtherDtlId(new Long(i + 1));
					} else if (i == 1) {
						row.setDepartmentName(stringArr[i]);
					} else if (i == 2) {
						row.setRiskCost(new BigDecimal(stringArr[i]));
					}
				}
				excelData.add(row);
			}
		}
		return excelData;
	}
}
