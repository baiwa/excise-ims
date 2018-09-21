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

import th.co.baiwa.excise.ia.persistence.vo.Int076Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope041Vo;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;

@Controller
@RequestMapping("api/ia/int076")
public class Int076Controller {

	private Logger logger = LoggerFactory.getLogger(Int076Controller.class);

	@Autowired
	UploadFileExciseService uploadFileExciseService;

	@PostMapping("excelINT076")
	@ResponseBody
	public List<Int076Vo> excelINT081(@ModelAttribute Ope041Vo mainForm) throws Exception {
		List<Int076Vo> excelData = new ArrayList<Int076Vo>();
		if (mainForm.getFileExel() != null) {
			Int076Vo row = new Int076Vo();
			List<String[]> ListfileEx = uploadFileExciseService.readFileExcel(mainForm);
			for (int j = 1; j < ListfileEx.size(); j++) {
				String[] stringArr = ListfileEx.get(j);

				row = new Int076Vo();
				row.setId(new Long(j));

				for (int i = 0; i < stringArr.length; i++) {
					if (i == 0) {
						if (stringArr[i].indexOf(".01.") != -1) {
							row.setColor("1");
						}else if(stringArr[i].indexOf(".02.") != -1) {
							row.setColor("2");
						}else if(stringArr[i].indexOf(".03.") != -1) {
							row.setColor("3");
						}else if(stringArr[i].indexOf(".04.") != -1) {
							row.setColor("4");
						}else if(stringArr[i].indexOf(".05.") != -1) {
							row.setColor("5");
						}else if(stringArr[i].indexOf(".06.") != -1) {
							row.setColor("6");
						}else if(stringArr[i].indexOf(".07.") != -1) {
							row.setColor("7");
						}else if(stringArr[i].indexOf(".08.") != -1) {
							row.setColor("8");
						}else if(stringArr[i].indexOf(".09.") != -1) {
							row.setColor("9");
						}else if(stringArr[i].indexOf(".10.") != -1) {
							row.setColor("10");
						}else if(stringArr[i].indexOf(".11.") != -1) {
							row.setColor("11");
						}else if(stringArr[i].indexOf(".12.") != -1) {
							row.setColor("12");
						}
						
						row.setDatePosted(stringArr[i]);
					} else if (i == 1) {
						row.setDocNumber(stringArr[i]);
					} else if (i == 2) {

						row.setDocType(stringArr[i]);
					} else if (i == 3) {
						row.setDocRefer(stringArr[i]);
					} else if (i == 4) {
						row.setActor(stringArr[i]);
					} else if (i == 5) {
						row.setDetermination(stringArr[i]);
					} else if (i == 6) {
						row.setPayUnit(stringArr[i]);
					} else if (i == 7) {
						row.setDebit(stringArr[i]);
					} else if (i == 8) {
						row.setCredit(stringArr[i]);
					} else if (i == 9) {
						row.setLiftUp(stringArr[i]);
					}
				}
				excelData.add(row);
			}
		}
		return excelData;
	}

}
