package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0112;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0112Repository;
import th.go.excise.ims.ta.vo.TaFormTS0112Vo;

@Service
public class TaFormTS0112Service extends AbstractTaFormTSService<TaFormTS0112Vo, TaFormTs0112> {

    private static final Logger logger = LoggerFactory.getLogger(TaFormTS0112Service.class);

    @Autowired
    private TaFormTs0112Repository taFormTs0112Repository;

    @Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected CommonTaFormTsRepository<?, Long> getRepository() {
		return taFormTs0112Repository;
	}

	@Override
    public String getReportName() {
        return REPORT_NAME.TA_FORM_TS01_12;
    }

    @Override
    public byte[] processFormTS(TaFormTS0112Vo formTS0112Vo) throws Exception {
        logger.info("processFormTS");

        saveFormTS(formTS0112Vo);
        byte[] reportFile = generateReport(formTS0112Vo);

        return reportFile;
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public void saveFormTS(TaFormTS0112Vo formTS0112Vo) {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        String budgetYear = ExciseUtils.getCurrentBudgetYear();
        logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0112Vo.getFormTsNumber());

        TaFormTs0112 formTs0112 = null;
        String formTsNumber = null;
        if (StringUtils.isNotBlank(formTS0112Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0112Vo.getFormTsNumber())) {
        	// Case Update FormTS
        	formTsNumber = formTS0112Vo.getFormTsNumber();
        	
            formTs0112 = taFormTs0112Repository.findByFormTsNumber(formTS0112Vo.getFormTsNumber());
            toEntity(formTs0112, formTS0112Vo);
        } else {
        	// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
        				
            formTs0112 = new TaFormTs0112();
            toEntity(formTs0112, formTS0112Vo);
            formTs0112.setOfficeCode(officeCode);
            formTs0112.setBudgetYear(budgetYear);
            formTs0112.setFormTsNumber(formTsNumber);
        }
        taFormTs0112Repository.save(formTs0112);
        
        saveAuditStep(formTS0112Vo, TaFormTS0112Vo.class, TA_FORM_TS_CODE.TS0112, formTsNumber);
    }

    @Override
    public byte[] generateReport(TaFormTS0112Vo formTS0112Vo) throws Exception, IOException {
        logger.info("generateReport");

        // get data to report
        Map<String, Object> params = new HashMap<>();
        params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
        params.put("formTsNumber", formTS0112Vo.getFormTsNumber());
        params.put("docPlace", formTS0112Vo.getDocPlace());
        params.put("docDate", formTS0112Vo.getDocDate());
        params.put("headOfficerFullName", formTS0112Vo.getHeadOfficerFullName());
        params.put("headOfficerPosition", formTS0112Vo.getHeadOfficerPosition());
        params.put("headOfficerOfficeName", formTS0112Vo.getHeadOfficerOfficeName());
        params.put("factoryName", formTS0112Vo.getFactoryName());
        params.put("newRegId", formTS0112Vo.getNewRegId());
        params.put("facAddrNo", formTS0112Vo.getFacAddrNo());
        params.put("facSoiName", formTS0112Vo.getFacSoiName());
        params.put("facThnName", formTS0112Vo.getFacThnName());
        params.put("facTambolName", formTS0112Vo.getFacTambolName());
        params.put("facAmphurName", formTS0112Vo.getFacAmphurName());
        params.put("facProvinceName", formTS0112Vo.getFacProvinceName());
        params.put("facZipCode", formTS0112Vo.getFacZipCode());
        params.put("ownerFullName1", formTS0112Vo.getOwnerFullName1());
        params.put("ownerPosition", formTS0112Vo.getOwnerPosition());
        params.put("ownerOther", formTS0112Vo.getOwnerOther());
        params.put("lawGroup", formTS0112Vo.getLawGroup());
        params.put("seizeDesc", formTS0112Vo.getSeizeDesc());
        params.put("contactDesc", formTS0112Vo.getContactDesc());
        params.put("ownerFullName2", formTS0112Vo.getOwnerFullName2());
        params.put("ownerPosition2", formTS0112Vo.getOwnerPosition2());
        params.put("ownerOther2", formTS0112Vo.getOwnerOther2());
        params.put("signAuthFullName", formTS0112Vo.getSignAuthFullName());
        params.put("signInspectorFullName", formTS0112Vo.getSignInspectorFullName());
        params.put("signWitnessFullName1", formTS0112Vo.getSignWitnessFullName1());
        params.put("signWitnessFullName2", formTS0112Vo.getSignWitnessFullName2());

        // set output
        JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_12 + "." + FILE_EXTENSION.JASPER, params);
        byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
        ReportUtils.closeResourceFileInputStream(params);

        return content;
    }

    @Override
    public TaFormTS0112Vo getFormTS(String formTsNumber) {
    	logger.info("getFormTS formTsNumber={}", formTsNumber);
    	
        TaFormTs0112 formTs0112 = taFormTs0112Repository.findByFormTsNumber(formTsNumber);
        
        // Set Data
        TaFormTS0112Vo formTS0112Vo = new TaFormTS0112Vo();
        toVo(formTS0112Vo, formTs0112);

        return formTS0112Vo;
    }
}
