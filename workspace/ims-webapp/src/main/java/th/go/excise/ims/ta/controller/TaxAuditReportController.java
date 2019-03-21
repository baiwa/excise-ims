package th.go.excise.ims.ta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.service.WorksheetExportService;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WsReg4000FormVo;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegMaster60List;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/api/ta/report")
public class TaxAuditReportController {

    private static final Logger logger = LoggerFactory.getLogger(TaxAuditReportController.class);

    @Autowired
    private WorksheetExportService exportService;

    @Autowired
    private RegFri4000Service regFri4000Service;

    //TODO Get details operator
    @PostMapping("/get-details-operator")
    @ResponseBody
    public ResponseData<List<RegMaster60List>> getDetailsOperator(@RequestBody WsReg4000FormVo wsReg4000FormVo) {
        ResponseData<List<RegMaster60List>> response = new ResponseData<>();
        try {
            List<RegMaster60List> regFri4000Response = regFri4000Service.getDetailsOperator(wsReg4000FormVo);
            response.setData(regFri4000Response);
            response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
        } catch (IOException e) {
            response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            e.printStackTrace();
        }
        return response;
    }

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
