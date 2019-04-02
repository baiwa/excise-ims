package th.go.excise.ims.ta.service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0118Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0118Hdr;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0118DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0118HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0118DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0118Vo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaFormTS0118Service extends AbstractTaFormTSService<TaFormTS0118Vo, TaFormTs0118Hdr> {

    private static final Logger logger = LoggerFactory.getLogger(TaFormTS0118Service.class);

    @Autowired
    private TaFormTSSequenceService taFormTSSequenceService;
    @Autowired
    private TaFormTs0118HdrRepository taFormTs0118HdrRepository;
    @Autowired
    private TaFormTs0118DtlRepository taFormTs0118DtlRepository;

    @Override
    public String getReportName() {
        return REPORT_NAME.TA_FORM_TS01_18;
    }

    @Override
    public byte[] processFormTS(TaFormTS0118Vo formTS0118Vo) throws Exception {
        logger.info("processFormTS");

        saveFormTS(formTS0118Vo);
        byte[] reportFile = generateReport(formTS0118Vo);

        return reportFile;
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public void saveFormTS(TaFormTS0118Vo formTS0118Vo) {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        String budgetYear = ExciseUtils.getCurrentBudgetYear();

        logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0118Vo.getFormTsNumber());

        TaFormTs0118Hdr taFormTs0118Hdr = null;
        TaFormTs0118Dtl taFormTs0118Dtl = null;
        List<TaFormTs0118Dtl> dtlVoList = null;
        if (StringUtils.isNotEmpty(formTS0118Vo.getFormTsNumber())) {

            //==> Set Hdr
            taFormTs0118Hdr = taFormTs0118HdrRepository.findByFormTsNumber(formTS0118Vo.getFormTsNumber());
            toEntity(taFormTs0118Hdr, formTS0118Vo);

            //==> Set Dtl
            //taFormTs0114DtlRepository.setIsDeleteY(officeCode, budgetYear, formTS0114Vo.getFormTsNumber());
            dtlVoList = taFormTs0118DtlRepository.findByFormTsNumber(formTS0118Vo.getFormTsNumber());
            // Set flag Y
            dtlVoList.forEach(e -> {
                e.setIsDeleted(FLAG.Y_FLAG);
                e.setRecNo(null);
            });

            if (formTS0118Vo.getTaFormTS0118DtlVoList() != null) {
                int i = 1;
                for (TaFormTS0118DtlVo formDtlVo : formTS0118Vo.getTaFormTS0118DtlVoList()) {

                    taFormTs0118Dtl = getEntityById(dtlVoList, formDtlVo.getFormTs0118DtlId());
                    if (taFormTs0118Dtl != null) {
                        // Exist Page
                        toEntityDtl(taFormTs0118Dtl, formDtlVo);
                        taFormTs0118Dtl.setIsDeleted(FLAG.N_FLAG);
                        taFormTs0118Dtl.setRecNo(String.valueOf(i));
                    } else {
                        // New Page
                        taFormTs0118Dtl = new TaFormTs0118Dtl();
                        toEntityDtl(taFormTs0118Dtl, formDtlVo);
                        taFormTs0118Dtl.setFormTsNumber(formTS0118Vo.getFormTsNumber());
                        taFormTs0118Dtl.setRecNo(String.valueOf(i));
                        dtlVoList.add(taFormTs0118Dtl);
                    }
                    i++;
                }
            }
            taFormTs0118DtlRepository.saveAll(dtlVoList);
        } else {
            taFormTs0118Hdr = new TaFormTs0118Hdr();
            toEntity(taFormTs0118Hdr, formTS0118Vo);
            taFormTs0118Hdr.setOfficeCode(officeCode);
            taFormTs0118Hdr.setBudgetYear(budgetYear);
            taFormTs0118Hdr.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));

            dtlVoList = new ArrayList<>();
            int i = 1;
            for (TaFormTS0118DtlVo formDtl : formTS0118Vo.getTaFormTS0118DtlVoList()) {
                taFormTs0118Dtl = new TaFormTs0118Dtl();
                toEntityDtl(taFormTs0118Dtl, formDtl);
                taFormTs0118Dtl.setFormTsNumber(taFormTs0118Hdr.getFormTsNumber());
                taFormTs0118Dtl.setRecNo(String.valueOf(i));
                dtlVoList.add(taFormTs0118Dtl);
                i++;
            }
            taFormTs0118DtlRepository.saveAll(dtlVoList);
        }
        taFormTs0118HdrRepository.save(taFormTs0118Hdr);
    }

    @Override
    public byte[] generateReport(TaFormTS0118Vo formTs0118Vo) throws Exception, IOException {
        Map<String, Object> params = new HashMap<String, Object>();

        // get data to report
        params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
        params.put("formTsNumber", formTs0118Vo.getFormTsNumber());
        params.put("bookNumber1", formTs0118Vo.getBookNumber1());
        params.put("bookNumber2", formTs0118Vo.getBookNumber2());
        params.put("docDate", formTs0118Vo.getDocDate());
        params.put("ownerFullName", formTs0118Vo.getOwnerFullName());
        params.put("factoryType", formTs0118Vo.getFactoryType());
        params.put("factoryName", formTs0118Vo.getFactoryName());
        params.put("newRegId", formTs0118Vo.getNewRegId());
        params.put("factoryAddress", formTs0118Vo.getFactoryAddress());
        params.put("companyAddress", formTs0118Vo.getCompanyAddress());
        params.put("lawSection", formTs0118Vo.getLawSection());
        params.put("lawGroup", formTs0118Vo.getLawGroup());
        params.put("auditDateStart", formTs0118Vo.getAuditDateStart());
        params.put("auditDateEnd", formTs0118Vo.getAuditDateEnd());
        params.put("sumAllTaxAmt", formTs0118Vo.getSumAllTaxAmt());
        params.put("sumAllTaxText", formTs0118Vo.getSumAllTaxText());
        params.put("officeName", formTs0118Vo.getOfficeName());
        params.put("tableHeaderDutyType", formTs0118Vo.getTableHeaderDutyType());
        params.put("tableHeaderUnit", formTs0118Vo.getTableHeaderUnit());
        params.put("reasonText", formTs0118Vo.getReasonText());
        params.put("signOfficerFullName1", formTs0118Vo.getSignOfficerFullName1());
        params.put("signOfficerDate1", formTs0118Vo.getSignOfficerDate1());
        params.put("signOfficerFullName2", formTs0118Vo.getSignOfficerFullName2());
        params.put("signOfficerDate2", formTs0118Vo.getSignOfficerDate2());
        params.put("extraMoneyDate", formTs0118Vo.getExtraMoneyDate());

        JRDataSource dataSource = new JRBeanCollectionDataSource(formTs0118Vo.getTaFormTS0118DtlVoList());

        // set output
        JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_18 + "." + FILE_EXTENSION.JASPER, params, dataSource);
        byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
        ReportUtils.closeResourceFileInputStream(params);

        return content;
    }

    @Override
    public List<String> getFormTsNumberList() {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        return taFormTs0118HdrRepository.findFormTsNumberByOfficeCode(officeCode);
    }

    @Override
    public TaFormTS0118Vo getFormTS(String formTsNumber) {
        TaFormTS0118Vo taFormTS0118Vo = new TaFormTS0118Vo();
        TaFormTs0118Hdr hdr = taFormTs0118HdrRepository.findByFormTsNumber(formTsNumber);
        if (hdr != null) {
            toVo(taFormTS0118Vo, hdr);
        }
        List<TaFormTs0118Dtl> dtls = taFormTs0118DtlRepository.findByFormTsNumber(formTsNumber);
        List<TaFormTS0118DtlVo> dtlVos = new ArrayList<>();
        for (TaFormTs0118Dtl dtl : dtls) {
            TaFormTS0118DtlVo dtlVo = new TaFormTS0118DtlVo();
            toVoDtl(dtlVo, dtl);
            dtlVos.add(dtlVo);
        }

        taFormTS0118Vo.setTaFormTS0118DtlVoList(dtlVos);
        return taFormTS0118Vo;
    }

    private void toEntityDtl(TaFormTs0118Dtl entity, TaFormTS0118DtlVo vo) {
        try {
            BeanUtils.copyProperties(entity, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    private void toVoDtl(TaFormTS0118DtlVo vo, TaFormTs0118Dtl entity) {
        try {
            BeanUtils.copyProperties(vo, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    private TaFormTs0118Dtl getEntityById(List<TaFormTs0118Dtl> taFormTs0118Dtls, String id) {
        TaFormTs0118Dtl formTs0118Dtl = null;
        for (TaFormTs0118Dtl taFormTS0118Dtl : taFormTs0118Dtls) {
            if (id.equals(taFormTS0118Dtl.getFormTs0118DtlId().toString())) {
                formTs0118Dtl = taFormTS0118Dtl;
                break;
            }
        }
        return formTs0118Dtl;
    }

}
