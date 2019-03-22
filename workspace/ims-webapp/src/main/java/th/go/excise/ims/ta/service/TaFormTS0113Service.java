package th.go.excise.ims.ta.service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0113Vo;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaFormTS0113Service {
    private static final Logger logger = LoggerFactory.getLogger(TaFormTS0113Service.class);

    public byte[] exportTaFormTS0113(TaFormTS0113Vo ts0113Vo ) throws Exception, IOException {

        logger.info("exportTaFormTS0113");
        Map<String, Object> params = new HashMap<>();

        // get data to report
        params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
        params.put("docPlace", ts0113Vo.getDocPlace());

        Date docDate = ConvertDateUtils.parseStringToDate(ts0113Vo.getDocDate(), ConvertDateUtils.DD_MM_YYYY);
        params.put("day", ConvertDateUtils.formatDateToString(docDate, ConvertDateUtils.DD));
        params.put("month", ConvertDateUtils.formatDateToString(docDate, ConvertDateUtils.MMMM));
        params.put("year", ConvertDateUtils.formatDateToString(docDate, ConvertDateUtils.YYYY));

        params.put("docTime", ts0113Vo.getDocTime());
        params.put("headOfficerFullName", ts0113Vo.getHeadOfficerFullName());
        params.put("headOfficerPosition", ts0113Vo.getHeadOfficerPosition());
        params.put("refBookNumber1", ts0113Vo.getRefBookNumber1());

        Date refDate = ConvertDateUtils.parseStringToDate(ts0113Vo.getRefBookDate(), ConvertDateUtils.DD_MM_YYYY);
        String refBookDay = ConvertDateUtils.formatDateToString(refDate, ConvertDateUtils.DD);
        String refBookMonth = ConvertDateUtils.formatDateToString(refDate, ConvertDateUtils.MMMM);
        String refBookYear = ConvertDateUtils.formatDateToString(refDate, ConvertDateUtils.YYYY);
        String refBookDate = StringUtils.toString(Integer.parseInt(refBookDay)) + " " + refBookMonth + " " + refBookYear;
        params.put("refBookDate", refBookDate);

        params.put("factoryType", ts0113Vo.getFactoryType());
        params.put("factoryName", ts0113Vo.getFactoryName());
        params.put("newRegId", ts0113Vo.getNewRegId());
        params.put("facAddrNo", ts0113Vo.getFacAddrNo());
        params.put("facSoiName", ts0113Vo.getFacSoiName());
        params.put("facThnName", ts0113Vo.getFacThnName());
        params.put("facTambolName", ts0113Vo.getFacTambolName());
        params.put("facAmphurName", ts0113Vo.getFacAmphurName());
        params.put("facProvinceName", ts0113Vo.getFacProvinceName());
        params.put("facZipCode", ts0113Vo.getFacZipCode());

        Date audDate = ConvertDateUtils.parseStringToDate(ts0113Vo.getAuditDate(), ConvertDateUtils.DD_MM_YYYY);
        String audDay = ConvertDateUtils.formatDateToString(audDate, ConvertDateUtils.DD);
        String audMonth = ConvertDateUtils.formatDateToString(audDate, ConvertDateUtils.MMMM);
        String audYear = ConvertDateUtils.formatDateToString(audDate, ConvertDateUtils.YYYY);
        String auditDate = audDay + " " + audMonth + " " + audYear;
        params.put("auditDate", auditDate);

        params.put("ownerFullName", ts0113Vo.getOwnerFullName());
        params.put("ownerPosition", ts0113Vo.getOwnerPosition());
        params.put("factoryName2", ts0113Vo.getFactoryName2());
        params.put("auditFinishTime", ts0113Vo.getAuditFinishTime());
        params.put("signOwnerFullName", ts0113Vo.getSignOwnerFullName());
        params.put("signOfficerFullName", ts0113Vo.getSignOfficerFullName());
        params.put("signWitnessFullName1", ts0113Vo.getSignWitnessFullName1());
        params.put("signWitnessFullName2", ts0113Vo.getSignWitnessFullName2());



        // set output
        JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_13 + "." + FILE_EXTENSION.JASPER, params);
        byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
        ReportUtils.closeResourceFileInputStream(params);

        return content;
    }
}
