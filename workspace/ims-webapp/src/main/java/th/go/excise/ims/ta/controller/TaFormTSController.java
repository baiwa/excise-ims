package th.go.excise.ims.ta.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JRException;
import th.go.excise.ims.ta.service.TaFormTS0110Service;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;

@Controller
@RequestMapping("/api/ta/report")
public class TaFormTSController {
	
    @Autowired
    private TaFormTS0110Service formTS0110Service;
    
    //TODO TaFormTS0110
    @PostMapping("/pdf/ta-form-ts0110")
	public void pdfTs(@RequestBody TaFormTS0110Vo reportJsonBean, HttpServletResponse response) throws IOException, JRException {
		byte[] reportFile = formTS0110Service.objectToPDF(reportJsonBean);
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=TaFormTS01_10.pdf");
		
//		IOUtils.write(reportFile, new FileOutputStream(new File("D:/tmp/" + "TaFormTS01_10.pdf")));
		IOUtils.write(reportFile, response.getOutputStream());
	}
}
