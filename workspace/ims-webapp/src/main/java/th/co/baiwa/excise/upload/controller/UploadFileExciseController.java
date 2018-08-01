package th.co.baiwa.excise.upload.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.form.FormUpload;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;

@Controller
@RequestMapping("api/upload")
public class UploadFileExciseController {
	private Logger logger = LoggerFactory.getLogger(UploadFileExciseController.class);

	@Autowired
	UploadFileExciseService uploadFileExciseService;

	@PostMapping("excel")
	@ResponseBody
	public List<FormUpload> excel(@ModelAttribute FormUpload mainForm) throws EncryptedDocumentException, InvalidFormatException, IOException{
		logger.debug("mainForm : " + mainForm);
		List<String> headerList = new ArrayList<>();
//		List<Lov> lovHeaderColumnList = ApplicationCache.getListOfValueByValueType("COLUMN_NAME", "OPE04-1");
		List<FormUpload> fuList = new ArrayList<>();
		FormUpload fu = new FormUpload();
//		for (Lov lov : lovHeaderColumnList) {
//			headerList.add(lov.getValue1());
//		}
		List<String[]> xxx = uploadFileExciseService.readFileExcel(mainForm);
		for (String[] stringArr : xxx) {
			fu = new FormUpload();
			for(int i = 0 ; i < stringArr.length ; i++) {
				if(i == 0) {
					fu.setColumn1(stringArr[i]);
				}else if(i == 1) {
					fu.setColumn2(stringArr[i]);
				}else if(i == 2) {
					fu.setColumn3(stringArr[i]);
				}else if(i == 3) {
					fu.setColumn4(stringArr[i]);
				}else if(i == 4) {
					fu.setColumn5(stringArr[i]);
				}else if(i == 5) {
					fu.setColumn6(stringArr[i]);
				}
			}
			fuList.add(fu);
		}
		
		
		return fuList;
	}

}
