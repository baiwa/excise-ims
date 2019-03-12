package th.go.excise.ims.ta.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import th.go.excise.ims.ta.service.WorksheetExportService;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@Controller
@RequestMapping("/api/ta/report")
public class TaxAuditReportController {

    private static final Logger logger = LoggerFactory.getLogger(TaxAuditReportController.class);

    @Autowired
    private WorksheetExportService exportService;

    // TODO Draft
    @GetMapping("/export-draft-worksheet")
    @ResponseBody
    public void exportRawMaterialReceive(@ModelAttribute TaxOperatorFormVo formVo, HttpServletRequest httpServletRequest, HttpServletResponse response)
            throws Exception {

        logger.info("listRawMaterialReceive export!!");

        /* set fileName */
        String fileName = URLEncoder.encode("Tax", "UTF-8");
        /* write it as an excel attachment */
        byte[] outArray = exportService.exportDraftWorksheet(formVo);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);

    }

}
