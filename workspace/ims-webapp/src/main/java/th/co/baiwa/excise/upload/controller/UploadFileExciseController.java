package th.co.baiwa.excise.upload.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.domain.form.AccMonth0407DTL;
import th.co.baiwa.excise.domain.form.FormUpload;
import th.co.baiwa.excise.domain.form.OPEDataTable;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/upload")
public class UploadFileExciseController {
	private Logger logger = LoggerFactory.getLogger(UploadFileExciseController.class);

	@Autowired
	UploadFileExciseService uploadFileExciseService;
	
	@Autowired
	PlanWorksheetHeaderService planWorksheetHeaderService;

	@PostMapping("excel")
	@ResponseBody
	public DataTableAjax<OPEDataTable> excel(@ModelAttribute FormUpload mainForm, AccMonth0407DTL vo) throws EncryptedDocumentException, InvalidFormatException, IOException{
		List<OPEDataTable> result = new ArrayList<OPEDataTable>();
		if((BeanUtils.isNotEmpty(mainForm.getStartDate())) && (BeanUtils.isNotEmpty(mainForm.getEndDate()))) {
			result = planWorksheetHeaderService.queryExciseIdFromAccDTL(vo.getExciseId(), vo.getType(),vo.getStartDate(), vo.getEndDate());
		}
		
		if(mainForm.getFileExel() != null) {
			List<FormUpload> fuList = new ArrayList<>();
			FormUpload fu = new FormUpload();
			List<String[]> ListfileEx = uploadFileExciseService.readFileExcel(mainForm);
				for (String[] stringArr : ListfileEx) {
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
			result = planWorksheetHeaderService.sumData(fuList, mainForm);
		}
		
		DataTableAjax<OPEDataTable> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(result.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(result.size()));
		dataTableAjax.setData(result);
		return dataTableAjax;
	}

}
