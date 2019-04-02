package th.go.excise.ims.ta.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0115Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0115Hdr;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0115DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0115HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0115DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0115Vo;

@Service
public class TaFormTS0115Service extends AbstractTaFormTSService<TaFormTS0115Vo, TaFormTs0115Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0115Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;

	@Autowired
	private TaFormTs0115HdrRepository taFormTs0115HdrRepository;

	@Autowired
	private TaFormTs0115DtlRepository taFormTs0115DtlRepository;

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_15;
	}

	@Override
	public byte[] processFormTS(TaFormTS0115Vo formTS0115Vo) throws Exception {
		logger.info("processFormTS");
		saveFormTS(formTS0115Vo);
		byte[] reportFile = generateReport(formTS0115Vo);
		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0115Vo formTS0115Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0115Vo.getFormTsNumber());

		TaFormTs0115Hdr taFormTs0115Hdr = null;
		TaFormTs0115Dtl taFormTs0115Dtl = null;
		List<TaFormTs0115Dtl> dtlVoList = null;
		if (StringUtils.isNotEmpty(formTS0115Vo.getFormTsNumber())) {

			// ==> Set Hdr
			taFormTs0115Hdr = taFormTs0115HdrRepository.findByFormTsNumber(formTS0115Vo.getFormTsNumber());
			toEntity(taFormTs0115Hdr, formTS0115Vo);

			// ==> Set Dtl
			dtlVoList = taFormTs0115DtlRepository.findByFormTsNumber(formTS0115Vo.getFormTsNumber());

			// Set flag Y
			dtlVoList.forEach(e -> e.setIsDeleted(FLAG.Y_FLAG));

			if (formTS0115Vo.getTaFormTS0115DtlVoList() != null) {
				for (TaFormTS0115DtlVo formDtlVo : formTS0115Vo.getTaFormTS0115DtlVoList()) {

					taFormTs0115Dtl = getEntityById(dtlVoList, formDtlVo.getFormTs0115DtlId());
					if (taFormTs0115Dtl != null) {
						// Exist Page
						toEntityDtl(taFormTs0115Dtl, formDtlVo);
						taFormTs0115Dtl.setIsDeleted(FLAG.N_FLAG);
					} else {
						// New Page
						taFormTs0115Dtl = new TaFormTs0115Dtl();
						toEntityDtl(taFormTs0115Dtl, formDtlVo);
						taFormTs0115Dtl.setFormTsNumber(formTS0115Vo.getFormTsNumber());
						dtlVoList.add(taFormTs0115Dtl);
					}
				}
			}
			taFormTs0115DtlRepository.saveAll(dtlVoList);
		} else {
			taFormTs0115Hdr = new TaFormTs0115Hdr();
			toEntity(taFormTs0115Hdr, formTS0115Vo);
			taFormTs0115Hdr.setOfficeCode(officeCode);
			taFormTs0115Hdr.setBudgetYear(budgetYear);
			taFormTs0115Hdr.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
			dtlVoList = new ArrayList<>();
			for (TaFormTS0115DtlVo formDtl : formTS0115Vo.getTaFormTS0115DtlVoList()) {
				taFormTs0115Dtl = new TaFormTs0115Dtl();
				toEntityDtl(taFormTs0115Dtl, formDtl);
				taFormTs0115Dtl.setFormTsNumber(taFormTs0115Hdr.getFormTsNumber());
				dtlVoList.add(taFormTs0115Dtl);
			}
			taFormTs0115DtlRepository.saveAll(dtlVoList);
		}
		taFormTs0115HdrRepository.save(taFormTs0115Hdr);
	}

	@Override
	public byte[] generateReport(TaFormTS0115Vo formTs0115Vo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("officeName", formTs0115Vo.getOfficeName());
		params.put("docDate", formTs0115Vo.getDocDate());
		params.put("ownerFullName", formTs0115Vo.getOwnerFullName());
		params.put("factoryType", formTs0115Vo.getFactoryType());
		params.put("factoryName", formTs0115Vo.getFactoryName());
		params.put("newRegId", formTs0115Vo.getNewRegId());
		params.put("facAddrNo", formTs0115Vo.getFacAddrNo());
		params.put("facSoiName", formTs0115Vo.getFacSoiName());
		params.put("facThnName", formTs0115Vo.getFacThnName());
		params.put("facTambolName", formTs0115Vo.getFacTambolName());
		params.put("facAmphurName", formTs0115Vo.getFacAmphurName());
		params.put("facProvinceName", formTs0115Vo.getFacProvinceName());
		params.put("facZipCode", formTs0115Vo.getFacZipCode());
		params.put("signOwnerFullName", formTs0115Vo.getSignOwnerFullName());
		params.put("signOfficerFullName", formTs0115Vo.getSignOfficerFullName());
		params.put("signWitnessFullName1", formTs0115Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTs0115Vo.getSignWitnessFullName2());

		JRDataSource dataSource = new JRBeanCollectionDataSource(formTs0115Vo.getTaFormTS0115DtlVoList());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_15 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		return taFormTs0115HdrRepository.findFormTsNumberByOfficeCode(officeCode);
	}

	@Override
	public TaFormTS0115Vo getFormTS(String formTsNumber) {
		TaFormTS0115Vo taFormTS0115Vo = new TaFormTS0115Vo();
		TaFormTs0115Hdr hdr = taFormTs0115HdrRepository.findByFormTsNumber(formTsNumber);
		if (hdr != null) {
			toVo(taFormTS0115Vo, hdr);
		}
		List<TaFormTs0115Dtl> dtls = taFormTs0115DtlRepository.findByFormTsNumber(formTsNumber);
		List<TaFormTS0115DtlVo> dtlVos = new ArrayList<>();
		for (TaFormTs0115Dtl dtl : dtls) {
			TaFormTS0115DtlVo dtlVo = new TaFormTS0115DtlVo();
			toVoDtl(dtlVo, dtl);
			dtlVos.add(dtlVo);
		}

		taFormTS0115Vo.setTaFormTS0115DtlVoList(dtlVos);
		return taFormTS0115Vo;
	}

	private void toEntityDtl(TaFormTs0115Dtl entity, TaFormTS0115DtlVo vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private void toVoDtl(TaFormTS0115DtlVo vo, TaFormTs0115Dtl entity) {
		try {
			BeanUtils.copyProperties(vo, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private TaFormTs0115Dtl getEntityById(List<TaFormTs0115Dtl> taFormTs0115Dtls, String id) {
		TaFormTs0115Dtl formTs0115Dtl = null;
		for (TaFormTs0115Dtl taFormTs0115Dtl : taFormTs0115Dtls) {
			if (id.equals(taFormTs0115Dtl.getFormTs0115DtlId().toString())) {
				formTs0115Dtl = taFormTs0115Dtl;
				break;
			}
		}
		return formTs0115Dtl;
	}

}
