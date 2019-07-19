package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaMedicalReceipt;
import th.go.excise.ims.ia.persistence.entity.IaMedicalWelfare;
import th.go.excise.ims.ia.persistence.repository.IaMedicalReceiptRepository;
import th.go.excise.ims.ia.persistence.repository.IaMedicalWelfareRepository;
import th.go.excise.ims.ia.vo.HospitalVo;
import th.go.excise.ims.ia.vo.Int1200702HdrVo;
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
			dataHdrSave.setBirthdate(ConvertDateUtils.parseStringToDate(form.getBirthdate(), ConvertDateUtils.DD_MM_YYYY,
				ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName(form.getChildName());
			dataHdrSave.setChildCitizenId(form.getChildCitizenId());
			dataHdrSave.setStatus(form.getStatus());
			dataHdrSave.setChildCheck("Y");
		}
		if (form.isChild2()) {
			dataHdrSave.setBirthdate2(ConvertDateUtils.parseStringToDate(form.getBirthdate2(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName2(form.getChildName2());
			dataHdrSave.setChildCitizenId2(form.getChildCitizenId2());
			dataHdrSave.setStatus2(form.getStatus2());
			dataHdrSave.setChild2Check("Y");
		}
		if (form.isChild3()) {
			dataHdrSave.setBirthdate3(ConvertDateUtils.parseStringToDate(form.getBirthdate3(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataHdrSave.setChildName3(form.getChildName3());
			dataHdrSave.setChildCitizenId3(form.getChildCitizenId3());
			dataHdrSave.setStatus3(form.getStatus3());
			dataHdrSave.setChild3Check("Y");
		}

		dataHdrSave.setFullName(form.getFullName());
		dataHdrSave.setGender(form.getGender());

//		dataHdrSave.setSiblingsOrder(new BigDecimal(form.getSiblingsOrder()));
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
//		dataHdrSave.setTotalMoney(new BigDecimal(form.getTotalMoney()));
		dataHdrSave.setReceiptQt(new BigDecimal(form.getReceiptQt()));
		dataHdrSave.setClaimStatus(form.getClaimStatus());
//		dataHdrSave.setClaimMoney(new BigDecimal(form.getClaimMoney()));

//		dataHdrSave.setFileId(new BigDecimal(form.getFileId()));
		dataHdrSave.setStatusCheck(form.getStatusCheck());
//		dataHdrSave.setIaDisReqId(new BigDecimal(form.getIaDisReqId()));

//		dataHdrSave.setSiblingsOrder2(new BigDecimal(form.getSiblingsOrder2()));
//		dataHdrSave.setSiblingsOrder3(new BigDecimal(form.getSiblingsOrder3()));
		
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
//			dataDtlSave.setReceiptAmount(new BigDecimal(dataDtl.getReceiptAmount()));
			dataDtlSave.setReceiptDate(ConvertDateUtils.parseStringToDate(dataDtl.getReceiptDate(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			dataDtlSave.setReceiptType(dataDtl.getReceiptType());
			iaMedicalReceiptRepository.save(dataDtlSave);
		}
	}
}
