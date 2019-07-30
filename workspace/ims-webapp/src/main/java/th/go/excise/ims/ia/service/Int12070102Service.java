package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.common.util.ThaiNumberUtils;
import th.go.excise.ims.ia.persistence.entity.IaMedicalReceipt;
import th.go.excise.ims.ia.persistence.entity.IaMedicalWelfare;
import th.go.excise.ims.ia.persistence.repository.IaMedicalReceiptRepository;
import th.go.excise.ims.ia.persistence.repository.IaMedicalWelfareRepository;
import th.go.excise.ims.ia.vo.HospitalVo;
import th.go.excise.ims.ia.vo.Int1200702HdrVo;
import th.go.excise.ims.ia.vo.Int12070101SaveFormVo;
import th.go.excise.ims.ia.vo.Int120702DtlVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseHospital;
import th.go.excise.ims.preferences.persistence.repository.ExciseHospitalRepository;

@Service
public class Int12070102Service {
	private static final Logger logger = LoggerFactory.getLogger(Int12070102Service.class);

	@Autowired
	private ExciseHospitalRepository exciseHospitalRepository;

	@Autowired
	private IaMedicalWelfareRepository iaMedicalWelfareRepository;

	@Autowired
	private IaMedicalReceiptRepository iaMedicalReceiptRepository;

	public List<HospitalVo> getHospital() {
		List<HospitalVo> res = new ArrayList<HospitalVo>();
		List<ExciseHospital> data = exciseHospitalRepository.findAll();
		HospitalVo dataSet = null;
		for (ExciseHospital hospital : data) {
			dataSet = new HospitalVo();
			dataSet.setHospCode(hospital.getHospCode());
			dataSet.setHospName(hospital.getHospName());
			res.add(dataSet);
		}
		return res;
	}

	@Transactional
	public void save(Int1200702HdrVo form) {
		IaMedicalWelfare dataHdrSave = new IaMedicalWelfare();

		if (form.isSelf()) {
			dataHdrSave.setSelfCheck("Y");
		}
		if (form.isCouple()) {
			dataHdrSave.setMateName(form.getMateName());
			dataHdrSave.setMateCitizenId(form.getMateCitizenId());
			dataHdrSave.setCoupleCheck("Y");
		}
		if (form.isFather()) {
			dataHdrSave.setFatherName(form.getFatherName());
			dataHdrSave.setFatherCitizenId(form.getFatherCitizenId());
			dataHdrSave.setFatherCheck("Y");
		}
		if (form.isMother()) {
			dataHdrSave.setMotherName(form.getMotherName());
			dataHdrSave.setMotherCitizenId(form.getMotherCitizenId());
			dataHdrSave.setFatherCheck("Y");
		}
		if (form.isChild1()) {
			dataHdrSave.setBirthdate(ConvertDateUtils.parseStringToDate(form.getBirthdate(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName(form.getChildName());
			dataHdrSave.setChildCitizenId(form.getChildCitizenId());
			dataHdrSave.setStatus(form.getStatus());
			dataHdrSave.setChildCheck("Y");
			dataHdrSave.setSiblingsOrder(new BigDecimal(form.getSiblingsOrder()));
		}
		if (form.isChild2()) {
			dataHdrSave.setBirthdate2(ConvertDateUtils.parseStringToDate(form.getBirthdate2(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName2(form.getChildName2());
			dataHdrSave.setChildCitizenId2(form.getChildCitizenId2());
			dataHdrSave.setStatus2(form.getStatus2());
			dataHdrSave.setChild2Check("Y");
			dataHdrSave.setSiblingsOrder2(new BigDecimal(form.getSiblingsOrder2()));
		}
		if (form.isChild3()) {
			dataHdrSave.setBirthdate3(ConvertDateUtils.parseStringToDate(form.getBirthdate3(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName3(form.getChildName3());
			dataHdrSave.setChildCitizenId3(form.getChildCitizenId3());
			dataHdrSave.setStatus3(form.getStatus3());
			dataHdrSave.setChild3Check("Y");
			dataHdrSave.setSiblingsOrder3(new BigDecimal(form.getSiblingsOrder3()));
		}

		dataHdrSave.setFullName(form.getFullName());
		dataHdrSave.setGender(form.getGender());
		dataHdrSave.setPhoneNo(form.getPhoneNumber());

		dataHdrSave.setPosition(form.getPosition());
		dataHdrSave.setAffiliation(form.getAffiliation());
		dataHdrSave.setPhoneNo(form.getPhoneNo());
		dataHdrSave.setDisease(form.getDisease());
		dataHdrSave.setHospitalName(form.getHospitalName());
		dataHdrSave.setHospitalOwner(form.getHospitalOwner());
		dataHdrSave.setTreatedDateFrom(ConvertDateUtils.parseStringToDate(form.getTreatedDateFrom(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		dataHdrSave.setTreatedDateTo(ConvertDateUtils.parseStringToDate(form.getTreatedDateTo(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		dataHdrSave.setTotalMoney(new BigDecimal(form.getTotalMoney()));
		dataHdrSave.setReceiptQt(new BigDecimal(form.getReceiptQt()));
		dataHdrSave.setClaimStatus(form.getClaimStatus());
		dataHdrSave.setClaimMoney(new BigDecimal(form.getClaimMoney()));

//		dataHdrSave.setFileId(new BigDecimal(form.getFileId()));
		dataHdrSave.setStatusCheck(form.getStatusCheck());
//		dataHdrSave.setIaDisReqId(new BigDecimal(form.getIaDisReqId()));


		dataHdrSave.setOwnerClaim1(form.getOwnerClaim1());
		dataHdrSave.setOwnerClaim2(form.getOwnerClaim2());
		dataHdrSave.setOwnerClaim3(form.getOwnerClaim3());
		dataHdrSave.setOwnerClaim4(form.getOwnerClaim4());
		dataHdrSave.setOtherClaim1(form.getOtherClaim1());
		dataHdrSave.setOtherClaim2(form.getOtherClaim2());
		dataHdrSave.setOtherClaim3(form.getOtherClaim3());
		dataHdrSave.setOtherClaim4(form.getOtherClaim4());

		iaMedicalWelfareRepository.save(dataHdrSave);
		IaMedicalReceipt dataDtlSave = null;
		for (Int120702DtlVo dataDtl : form.getReceipts()) {
			dataDtlSave = new IaMedicalReceipt();
			dataDtlSave.setId(dataHdrSave.getId());
			dataDtlSave.setReceiptNo(dataDtl.getReceiptNo());
			dataDtlSave.setReceiptAmount(new BigDecimal(dataDtl.getReceiptAmount()));
			dataDtlSave.setReceiptDate(ConvertDateUtils.parseStringToDate(dataDtl.getReceiptDate(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataDtlSave.setReceiptType(dataDtl.getReceiptType());
			iaMedicalReceiptRepository.save(dataDtlSave);
		}
	}

	public Int1200702HdrVo findById(Long id) {
		Int1200702HdrVo dataRes = new Int1200702HdrVo();
		IaMedicalWelfare dataHdr = iaMedicalWelfareRepository.findById(id).get();
		dataRes.setFullName(dataHdr.getFullName());
		dataRes.setGender(dataHdr.getGender());
		dataRes.setPhoneNumber(dataHdr.getPhoneNo());

		if ("Y".equals(dataHdr.getSelfCheck())) {
			dataRes.setFather(true);
		}
		if ("Y".equals(dataHdr.getCoupleCheck())) {
			dataRes.setCouple(true);
			dataRes.setMateName(dataHdr.getMateName());
			dataRes.setMateCitizenId(dataHdr.getMateCitizenId());
		}
		if ("Y".equals(dataHdr.getFatherCheck())) {
			dataRes.setFather(true);
			dataRes.setFatherName(dataHdr.getFatherName());
			dataRes.setFatherCitizenId(dataHdr.getFatherCitizenId());
		}
		if ("Y".equals(dataHdr.getMotherCheck())) {
			dataRes.setMother(true);
			dataRes.setMotherName(dataHdr.getMotherName());
			dataRes.setMotherCitizenId(dataHdr.getMotherCitizenId());
		}
		if ("Y".equals(dataHdr.getChildCheck())) {
			dataRes.setChild1(true);
			dataRes.setChildName(dataHdr.getChildName());
			dataRes.setChildCitizenId(dataHdr.getChildCitizenId());
			dataRes.setStatusCheck(dataHdr.getStatusCheck());
			dataRes.setSiblingsOrder(dataHdr.getSiblingsOrder().toString());
			dataRes.setBirthdate(ConvertDateUtils.formatDateToString(dataHdr.getBirthdate(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
		}
		if ("Y".equals(dataHdr.getChild2Check())) {
			dataRes.setChild2(true);
			dataRes.setChildName2(dataHdr.getChildName2());
			dataRes.setChildCitizenId2(dataHdr.getChildCitizenId2());
			dataRes.setStatus2(dataHdr.getStatus2());
			dataRes.setSiblingsOrder2(dataHdr.getSiblingsOrder2().toString());
			dataRes.setBirthdate2(ConvertDateUtils.formatDateToString(dataHdr.getBirthdate2(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
		}
		if ("Y".equals(dataHdr.getChild3Check())) {
			dataRes.setChild3(true);
			dataRes.setChildName3(dataHdr.getChildName3());
			dataRes.setChildCitizenId3(dataHdr.getChildCitizenId3());
			dataRes.setStatus3(dataHdr.getStatus3());
			dataRes.setSiblingsOrder3(dataHdr.getSiblingsOrder3().toString());
			dataRes.setBirthdate3(ConvertDateUtils.formatDateToString(dataHdr.getBirthdate3(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
		}

//		dataRes.setBirthdate(dataHdr.getBirthdate().toString());
//		dataRes.setSiblingsOrder(dataHdr.getSiblingsOrder().toString());
//		dataRes.setPosition(dataHdr.getPosition());
//		dataRes.setAffiliation(dataHdr.getAffiliation());
//		dataRes.setPhoneNo(dataHdr.getPhoneNo());
//		dataRes.setStatus(dataHdr.getStatus());
		dataRes.setDisease(dataHdr.getDisease());
		dataRes.setHospitalName(dataHdr.getHospitalName());
		dataRes.setHospitalOwner(dataHdr.getHospitalOwner());
		dataRes.setTreatedDateFrom(ConvertDateUtils.formatDateToString(dataHdr.getTreatedDateFrom(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		dataRes.setTreatedDateTo(ConvertDateUtils.formatDateToString(dataHdr.getTreatedDateTo(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		dataRes.setTotalMoney(dataHdr.getTotalMoney().toString());
//		dataRes.setReceiptQt(dataHdr.getReceiptQt().toString());
		dataRes.setClaimStatus(dataHdr.getClaimStatus());
		dataRes.setClaimMoney(dataHdr.getClaimMoney().toString());

		dataRes.setOwnerClaim1(dataHdr.getOwnerClaim1());
		dataRes.setOwnerClaim2(dataHdr.getOwnerClaim2());
		dataRes.setOwnerClaim3(dataHdr.getOwnerClaim3());
		dataRes.setOwnerClaim4(dataHdr.getOwnerClaim4());
		dataRes.setOtherClaim1(dataHdr.getOtherClaim1());
		dataRes.setOtherClaim2(dataHdr.getOtherClaim2());
		dataRes.setOtherClaim3(dataHdr.getOtherClaim3());
		dataRes.setOtherClaim4(dataHdr.getOtherClaim4());

		return dataRes;
	}
	
	public byte[] exportReport(long id) throws Exception {
		Int1200702HdrVo dataFind = findById(id);
		
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		Map<String, Object> params = new HashMap<>();
//		params.put("name", dataFind.getName());
//		params.put("position", dataFind.getPosition());
//		params.put("affiliation", dataFind.getAffiliation());
//		params.put("paymentCost", dataFind.getPaymentCost());
//		params.put("paymentFor", dataFind.getPaymentFor());
//		params.put("period", dataFind.getPeriod());
//		params.put("requestNo", dataFind.getRequestNo());
//		params.put("periodWithdraw", dataFind.getPeriodWithdraw());
//		params.put("receipts", dataFind.getReceipts());
//		params.put("status", dataFind.getStatus());
//		params.put("periodWithdrawTo", dataFind.getPeriodWithdrawTo());
//		params.put("form6005No", dataFind.getForm6005No());
//		params.put("refReceipts", dataFind.getRefReceipts());
//		
//		params.put("billAmount", decimalFormat.format(dataFind.getBillAmount()));
//		params.put("salary",  decimalFormat.format(dataFind.getSalary()));
//		params.put("notOver",  decimalFormat.format(dataFind.getNotOver()));
//		params.put("totalMonth", dataFind.getTotalMonth());
//		params.put("totalWithdraw", dataFind.getTotalWithdraw());
//		
//		params.put("billAmountText", ThaiNumberUtils.toThaiBaht(dataFind.getBillAmount().toString()));
//		params.put("salaryText",ThaiNumberUtils.toThaiBaht(dataFind.getSalary().toString()));
//		params.put("notOverText", ThaiNumberUtils.toThaiBaht(dataFind.getNotOver().toString()));
//		params.put("totalWithdrawText", ThaiNumberUtils.toThaiBaht(dataFind.getTotalWithdraw().toString()));
//		params.put("totalMonthThaiNumber", ThaiNumberUtils.toThaiNumber(dataFind.getTotalMonth().toString()));
//		params.put("receiptsNumber", ThaiNumberUtils.toThaiNumber(dataFind.getReceipts().toString()));
		
		
		System.out.println("dataFind--------->"+ params);
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.IA_FORM_7131_NO + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);
		return content;
	}
	
}
