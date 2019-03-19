package th.go.excise.ims.ta.controller;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.ims.ta.service.WorksheetExportService;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@Controller
@RequestMapping("/api/ta/report")
public class TaxAuditReportController {

    private static final Logger logger = LoggerFactory.getLogger(TaxAuditReportController.class);

    @Autowired
    private WorksheetExportService exportService;

 // TODO preview worksheet
    @GetMapping("/ta-rpt0001")
    @ResponseBody
    public void exportPreviewWorksheet(@ModelAttribute TaxOperatorFormVo formVo, HttpServletRequest httpServletRequest, HttpServletResponse response)
            throws Exception {

        logger.info("listRawMaterialReceive export!!");

        /* set fileName */
        String fileName = URLEncoder.encode("Worksheet", "UTF-8");
        /* write it as an excel attachment */
        byte[] outArray = exportService.exportPreviewWorksheet(formVo);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);

    }
    
    // TODO Draft
    @GetMapping("/ta-rpt0002")
    @ResponseBody
    public void exportDraftWorksheet(@ModelAttribute TaxOperatorFormVo formVo, HttpServletRequest httpServletRequest, HttpServletResponse response)
            throws Exception {

        logger.info("listRawMaterialReceive export!!");

        /* set fileName */
        String fileName = URLEncoder.encode("DraftWorksheet", "UTF-8");
        /* write it as an excel attachment */
        byte[] outArray = exportService.exportDraftWorksheet(formVo);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);

    }
    
 // TODO Worksheet
    @GetMapping("/ta-rpt0003")
    @ResponseBody
    public void exportWorksheet(@ModelAttribute TaxOperatorFormVo formVo, HttpServletRequest httpServletRequest, HttpServletResponse response)
            throws Exception {

        logger.info("listRawMaterialReceive export!!");

        /* set fileName */
        String fileName = URLEncoder.encode("Worksheet", "UTF-8");
        /* write it as an excel attachment */
        byte[] outArray = exportService.exportWorksheet(formVo);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);

    }

}
