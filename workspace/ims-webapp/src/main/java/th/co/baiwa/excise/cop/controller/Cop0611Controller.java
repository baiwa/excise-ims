package th.co.baiwa.excise.cop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.util.BeanUtils;
import th.co.baiwa.excise.cop.service.Cop0611Service;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;
import th.co.baiwa.excise.ta.persistence.vo.Ope041Vo;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;

@Controller
@RequestMapping("api/cop0611")
public class Cop0611Controller {
	
	@Autowired
	private Cop0611Service cop0611Service;
	
	@Autowired
	private UploadFileExciseService uploadFileExciseService;
	
	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;
	
	
	@PostMapping("excel")
	@ResponseBody
	public DataTableAjax<Ope041DataTable> excel(@ModelAttribute Ope041Vo vo) throws EncryptedDocumentException, InvalidFormatException, IOException{
		List<Ope041DataTable> result = new ArrayList<Ope041DataTable>();
		if((BeanUtils.isNotEmpty(vo.getStartDate())) && (BeanUtils.isNotEmpty(vo.getEndDate()))) {
			result = planWorksheetHeaderService.queryExciseIdFromAccDTL(vo.getExciseId(), vo.getType(),vo.getStartDate(), vo.getEndDate());
		}
		
		if(vo.getFileExel() != null) {
			List<Ope041Vo> fuList = new ArrayList<>();
			Ope041Vo fu = new Ope041Vo();
			List<String[]> ListfileEx = uploadFileExciseService.readFileExcel(vo.getFileExel());
				for (String[] stringArr : ListfileEx) {
					fu = new Ope041Vo();
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
			result = planWorksheetHeaderService.sumData(fuList, vo);
		}
		
		DataTableAjax<Ope041DataTable> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(result.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(result.size()));
		dataTableAjax.setData(result);
		return dataTableAjax;
	}
	
	@PostMapping("/saveTable")
	@ResponseBody
	public ResponseEntity<?> listdata(@RequestBody List<Ope041DataTable> allData) {
		try {
			cop0611Service.cop0611Service(allData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
