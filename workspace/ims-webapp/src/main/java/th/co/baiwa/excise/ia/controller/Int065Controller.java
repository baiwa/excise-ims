package th.co.baiwa.excise.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.CheckPaymentExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
import th.co.baiwa.excise.ia.service.CheckPaymentExcelService;
import th.co.baiwa.excise.ia.service.Int065Service;


@Controller
@RequestMapping("api/ia/int065")
public class Int065Controller {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Int065Service int065Service;
	

	@Autowired
	private CheckPaymentExcelService checkPaymentExcelService;
	
	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int065Vo> findAll(@RequestBody Int065FormVo formVo) {
		return int065Service.findAll(formVo);
	}

	@GetMapping("/sector")
	@ResponseBody
	public List<Lov> sector() {
		return int065Service.sector();
	}

	@PostMapping("/area")
    @ResponseBody
    public List<Lov> sector(@RequestBody Long idMaster){
        List<Lov> arae = int065Service.area(idMaster);
        return arae;
    }
	
	@PostMapping("/branch")
    @ResponseBody
    public List<Lov> branch(@RequestBody Long idMaster){
        List<Lov> branch = int065Service.branch(idMaster);
        return branch;
    }

    @GetMapping("/budgetType")
	@ResponseBody
	public List<Lov> budgetType(){
		return int065Service.budgetType();
	}
    
//    @GetMapping("/exportFile")
//	@ResponseBody
//	public  void exportFile(@ModelAttribute Int065FormVo formVo, HttpServletResponse response) throws Exception {
//		try {
//			int065Service.exportFile(formVo, response);
//		} catch (Exception e) {
//			log.error("Error ! ==> exportFile method exportFile",e);
//		}
//		
//	}
    
    @PostMapping("/export")
	public void export(@RequestParam String dataJson, HttpServletResponse response) throws Exception {

		Gson gson = new Gson();
		CheckPaymentExcelVo result = gson.fromJson(dataJson, CheckPaymentExcelVo.class);
		List<Int065Vo> dataList = result.getInt065ExcelList();

		/* set fileName */
		String fileName = URLEncoder.encode("รายการคุมเช็ค", "UTF-8");

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = checkPaymentExcelService.exportInt065(dataList);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

}