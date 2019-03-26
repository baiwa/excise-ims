package th.go.excise.ims.ta.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import th.co.baiwa.buckwaframework.common.bean.ReportJsonBean;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.rest.adapter.BigDecimalTypeAdapter;
import th.co.baiwa.buckwaframework.common.rest.adapter.DateThaiTypeAdapter;
import th.go.excise.ims.ta.service.TaFormTS0101Service;
import th.go.excise.ims.ta.service.TaFormTS0107Service;
import th.go.excise.ims.ta.service.TaFormTS0108Service;
import th.go.excise.ims.ta.service.TaFormTS01101Service;
import th.go.excise.ims.ta.service.TaFormTS0110Service;
import th.go.excise.ims.ta.service.TaFormTS0111Service;
import th.go.excise.ims.ta.service.TaFormTS0112Service;
import th.go.excise.ims.ta.service.TaFormTS0113Service;
import th.go.excise.ims.ta.service.TaFormTS01142Service;
import th.go.excise.ims.ta.service.TaFormTS0114Service;
import th.go.excise.ims.ta.service.TaFormTS01171Service;
import th.go.excise.ims.ta.service.TaFormTS0118Service;
import th.go.excise.ims.ta.service.TaFormTS0119Service;
import th.go.excise.ims.ta.service.TaFormTS0120Service;
import th.go.excise.ims.ta.service.TaFormTS0121Service;
import th.go.excise.ims.ta.vo.TaFormTS0101Vo;
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;
import th.go.excise.ims.ta.vo.TaFormTS0108Vo;
import th.go.excise.ims.ta.vo.TaFormTS01101Vo;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;
import th.go.excise.ims.ta.vo.TaFormTS0111Vo;
import th.go.excise.ims.ta.vo.TaFormTS0112Vo;
import th.go.excise.ims.ta.vo.TaFormTS0113Vo;
import th.go.excise.ims.ta.vo.TaFormTS01142Vo;
import th.go.excise.ims.ta.vo.TaFormTS0114Vo;
import th.go.excise.ims.ta.vo.TaFormTS01171Vo;
import th.go.excise.ims.ta.vo.TaFormTS0118Vo;
import th.go.excise.ims.ta.vo.TaFormTS0119Vo;
import th.go.excise.ims.ta.vo.TaFormTS0120Vo;
import th.go.excise.ims.ta.vo.TaFormTS0121Vo;

@Controller
@RequestMapping("/api/ta/report")
public class TaFormTSController {

    private static final Logger logger = LoggerFactory.getLogger(TaFormTSController.class);

    private Gson gson = new GsonBuilder()
        .serializeNulls()
        .registerTypeAdapter(Date.class, DateThaiTypeAdapter.getInstance())
        .registerTypeAdapter(BigDecimal.class, BigDecimalTypeAdapter.getInstance())
        .create();
    
    private TaFormTS0101Service taFormTS0101Service;
    private TaFormTS0107Service taFormTS0107Service;
    private TaFormTS0108Service taFormTS0108Service;
    private TaFormTS0110Service taFormTS0110Service;
    private TaFormTS01101Service taFormTS01101Service;
    private TaFormTS0111Service taFormTS0111Service;
    private TaFormTS0112Service taFormTS0112Service;
    private TaFormTS0113Service taFormTS0113Service;
    private TaFormTS01171Service taFormTS01171Service;
    private TaFormTS0119Service taFormTS0119Service;
    private TaFormTS01142Service taFormTS01142Service;
    private TaFormTS0114Service taFormTS0114Service;
    private TaFormTS0118Service taFormTS0118Service;
    private TaFormTS0120Service taFormTS0120Service;
    private TaFormTS0121Service taFormTS0121Service;
    
    @Autowired
    public TaFormTSController(TaFormTS0101Service taFormTS0101Service, TaFormTS0107Service taFormTS0107Service, TaFormTS0108Service taFormTS0108Service, TaFormTS0110Service taFormTS0110Service, TaFormTS01101Service taFormTS01101Service, TaFormTS0112Service taFormTS0112Service, TaFormTS0113Service taFormTS0113Service, TaFormTS0114Service taFormTS0114Service,
                              TaFormTS0111Service taFormTS0111Service, TaFormTS01171Service taFormTS01171Service,TaFormTS0118Service taFormTS0118Service, TaFormTS0119Service taFormTS0119Service , TaFormTS01142Service taFormTS01142Service, TaFormTS0120Service taFormTS0120Service, TaFormTS0121Service taFormTS0121Service) {

        this.taFormTS0101Service = taFormTS0101Service;
        this.taFormTS0107Service = taFormTS0107Service;
        this.taFormTS0108Service = taFormTS0108Service;
        this.taFormTS0110Service = taFormTS0110Service;
        this.taFormTS01101Service = taFormTS01101Service;
        this.taFormTS0111Service = taFormTS0111Service;
        this.taFormTS0112Service = taFormTS0112Service;
        this.taFormTS0113Service = taFormTS0113Service;
        this.taFormTS01171Service = taFormTS01171Service;
        this.taFormTS0118Service = taFormTS0118Service;
        this.taFormTS0119Service = taFormTS0119Service;
        this.taFormTS0114Service = taFormTS0114Service;
        this.taFormTS01142Service = taFormTS01142Service; 
        this.taFormTS0120Service = taFormTS0120Service;
        this.taFormTS0121Service = taFormTS0121Service;
    }

    // TODO TaFormTS0101
    @PostMapping("/pdf/ta-form-ts0101")
    public void processFormTS0101(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0101");

        TaFormTS0101Vo formTS0101Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0101Vo.class);
        byte[] reportFile = taFormTS0101Service.processFormTS(formTS0101Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_01 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }

    // TODO TaFormTS0107
    @PostMapping("/pdf/ta-form-ts0107")
    public void processFormTS0107(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0107");

        TaFormTS0107Vo formTS0107Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0107Vo.class);
        byte[] reportFile = taFormTS0107Service.processFormTS(formTS0107Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_07 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }

    @PostMapping("/pdf/ta-form-ts0108")
    public void processFormTS0108(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0108");

        TaFormTS0108Vo formTS0108Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0108Vo.class);
        byte[] reportFile = taFormTS0108Service.generateReport(formTS0108Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_07 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }

    // TODO TaFormTS0110
    @PostMapping("/pdf/ta-form-ts0110")
    public void processFormTS0110(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0110");

        TaFormTS0110Vo formTS0110Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0110Vo.class);
        byte[] reportFile = taFormTS0110Service.processFormTS(formTS0110Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_10 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }

    // TODO TaFormTS01101
    @PostMapping("/pdf/ta-form-ts01101")
    public void processFormTS01101(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS01101");

        TaFormTS01101Vo formTS01101Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS01101Vo.class);
        byte[] reportFile = taFormTS01101Service.processFormTS(formTS01101Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_10 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }

    // TODO TaFormTS0111
    @PostMapping("/pdf/ta-form-ts0111")
    public void processFormTS0111(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0111");

        TaFormTS0111Vo formTS0111Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0111Vo.class);
        byte[] reportFile = taFormTS0111Service.processFormTS(formTS0111Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_11 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }
    
	// TODO TaFormTS0112
	@PostMapping("/pdf/ta-form-ts0112")
	public void processFormTS0112(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
		logger.info("processFormTS0112");

		TaFormTS0112Vo formTS0112Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0112Vo.class);
		byte[] reportFile = taFormTS0112Service.processFormTS(formTS0112Vo);

		String filename = String.format(REPORT_NAME.TA_FORM_TS01_12 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(reportFile, response.getOutputStream());
	}

    // TODO TaFormTS0113
    @PostMapping("/pdf/ta-form-ts0113")
    public void processFormTS0113(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0113");

        TaFormTS0113Vo formTS0113Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0113Vo.class);
        byte[] reportFile = taFormTS0113Service.processFormTS(formTS0113Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_13 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }

    // TODO TaFormTS0114
    @PostMapping("/pdf/ta-form-ts0114")
    public void processFormTS0114(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0114");

        TaFormTS0114Vo formTS0114Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0114Vo.class);
        byte[] reportFile = taFormTS0114Service.processFormTS(formTS0114Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_14 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }
    // TODO TaFormTS01142
    @PostMapping("/pdf/ta-form-ts01142")
    public void processFormTS01142(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0114");

        TaFormTS01142Vo formTS01142Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS01142Vo.class);
        byte[] reportFile = taFormTS01142Service.processFormTS(formTS01142Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_14 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }

    // TODO TaFormTS01171
    @PostMapping("/pdf/ta-form-ts01171")
    public void processFormTS01171(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS01171");

        TaFormTS01171Vo formTS01171Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS01171Vo.class);
        byte[] reportFile = taFormTS01171Service.processFormTS(formTS01171Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_17_1 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }
    
 // TODO TaFormTS0118
    @PostMapping("/pdf/ta-form-ts0118")
    public void processFormTS0118(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0118");

        TaFormTS0118Vo formTS0118Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0118Vo.class);
        byte[] reportFile = taFormTS0118Service.processFormTS(formTS0118Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_18 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }

    // TODO TaFormTS0119
    @PostMapping("/pdf/ta-form-ts0119")
    public void processFormTS0119(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0119");

        TaFormTS0119Vo formTS0119Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0119Vo.class);
        byte[] reportFile = taFormTS0119Service.processFormTS(formTS0119Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_19 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }
    
    // TODO TaFormTS0120
    @PostMapping("/pdf/ta-form-ts0120")
    public void processFormTS0120(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0120");

        TaFormTS0120Vo formTS0120Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0120Vo.class);
        byte[] reportFile = taFormTS0120Service.processFormTS(formTS0120Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_20 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }
    
    // TODO TaFormTS0121
    @PostMapping("/pdf/ta-form-ts0121")
    public void processFormTS0121(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
        logger.info("processFormTS0121");

        TaFormTS0121Vo formTS0121Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0121Vo.class);
        byte[] reportFile = taFormTS0121Service.processFormTS(formTS0121Vo);

        String filename = String.format(REPORT_NAME.TA_FORM_TS01_21 + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
        response.setContentType("application/octet-stream");

        FileCopyUtils.copy(reportFile, response.getOutputStream());
    }


}
