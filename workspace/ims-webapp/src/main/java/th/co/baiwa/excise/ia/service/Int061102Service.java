package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;
import th.co.baiwa.excise.ia.persistence.entity.MedicalReceipt;
import th.co.baiwa.excise.ia.persistence.entity.WithdrawFileUpload;
import th.co.baiwa.excise.ia.persistence.repository.HealthCareWelFareRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaMedicalReceiptRepository;
import th.co.baiwa.excise.ia.persistence.repository.WithdrawFileUploadRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int061102FormVo;

@Service
public class Int061102Service {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${app.datasource.path.upload}")
	private String pathed;

	@Autowired
	private HealthCareWelFareRepository healthCareWelFareRepository;

	@Autowired
	private WithdrawFileUploadRepository withdrawFileUploadRepository;

	@Autowired
	private LovRepository lovRepository;

	@Autowired
	private IaMedicalReceiptRepository medicalReceiptRep;

	public HealthCareWelFareEntity save(Int061102FormVo int061102FormVo) {
		HealthCareWelFareEntity vo = new HealthCareWelFareEntity();
		vo.setFullName(int061102FormVo.getFullName());
		vo.setGender(int061102FormVo.getGender());
		vo.setBirthDate(DateConstant.convertStrToDate(int061102FormVo.getBirthDate(), DateConstant.DD_MM_YYYY,
				DateConstant.LOCAL_TH));
		vo.setBirthDate2(DateConstant.convertStrToDate(int061102FormVo.getBirthDate2(), DateConstant.DD_MM_YYYY,
				DateConstant.LOCAL_TH));
		vo.setBirthDate3(DateConstant.convertStrToDate(int061102FormVo.getBirthDate3(), DateConstant.DD_MM_YYYY,
				DateConstant.LOCAL_TH));
		vo.setSiblingsOrder(NumberUtils.toInt(int061102FormVo.getSiblingsOrder()));
		vo.setSiblingsOrder2(NumberUtils.toInt(int061102FormVo.getSiblingsOrder2()));
		vo.setSiblingsOrder3(NumberUtils.toInt(int061102FormVo.getSiblingsOrder3()));
		vo.setPosition(int061102FormVo.getPosition());
		vo.setAffiliation(int061102FormVo.getAffiliation());
		vo.setPhoneNumber(int061102FormVo.getPhoneNumber());
		vo.setStatus(int061102FormVo.getStatus());
		vo.setStatus2(int061102FormVo.getStatus2());
		vo.setStatus3(int061102FormVo.getStatus3());
		vo.setDisease(int061102FormVo.getDisease());
		vo.setHospitalName(int061102FormVo.getHospitalName());
		vo.setHospitalOwner(int061102FormVo.getHospitalOwner());
		vo.setTreatedDateFrom(DateConstant.convertStrToDate(int061102FormVo.getTreatedDateFrom(),
				DateConstant.DD_MM_YYYY, DateConstant.LOCAL_TH));
		vo.setTreatedDateTo(DateConstant.convertStrToDate(int061102FormVo.getTreatedDateTo(), DateConstant.DD_MM_YYYY,
				DateConstant.LOCAL_TH));
		vo.setTotalMoney(int061102FormVo.getTotalMoney());
		vo.setReceiptQt(int061102FormVo.getReceiptQt());
		vo.setClaimStatus(int061102FormVo.getClaimStatus());
		vo.setClaimMoney(int061102FormVo.getClaimMoney());
		vo.setOwnerClaim(int061102FormVo.getOwnerClaim());
		vo.setOtherClaim(int061102FormVo.getOtherClaim());
		vo.setMateName(int061102FormVo.getMateName());
		vo.setMateCitizenId(int061102FormVo.getMateCitizenId());
		vo.setFatherName(int061102FormVo.getFatherName());
		vo.setFatherCitizenId(int061102FormVo.getFatherCitizenId());
		vo.setMotherName(int061102FormVo.getMotherName());
		vo.setMotherCitizenId(int061102FormVo.getMotherCitizenId());
		vo.setChildName(int061102FormVo.getChildName());
		vo.setChildCitizenId(int061102FormVo.getChildCitizenId());
		vo.setChildName2(int061102FormVo.getChildName());
		vo.setChildCitizenId2(int061102FormVo.getChildCitizenId());
		vo.setChildName3(int061102FormVo.getChildName());
		vo.setChildCitizenId3(int061102FormVo.getChildCitizenId());
		vo.setStatusCheck(ExciseConstants.IA.STATUS.PROCESS);

		return healthCareWelFareRepository.save(vo);
	}

	public void upload(Int061102FormVo int061102FormVo) {
		if (int061102FormVo.getFiles() != null) {

			MultipartFile[] files = int061102FormVo.getFiles();
			for (MultipartFile file : files) {
				WithdrawFileUpload w = new WithdrawFileUpload();
				File f = new File(pathed + "Document_IaHealthCareWelFare/" + "id_" + int061102FormVo.getId() + "/");

				if (!f.exists()) {
					if (f.mkdirs()) {
						log.info("Directory is created!");
					} else {
						log.info("Failed to create directory!");
					}
				}

				try {

					byte[] data = file.getBytes();
					String path = pathed + "Document_IaHealthCareWelFare/" + "id_" + int061102FormVo.getId() + "/"
							+ file.getOriginalFilename();

					OutputStream stream = new FileOutputStream(path);
					stream.write(data);
					stream.close();
					log.info("Created file at path: " + path);

					w.setFilename(file.getOriginalFilename());
					w.setType(int061102FormVo.getType());
					w.setIdMaster(int061102FormVo.getId());
					w.setPathFiil(path);
					withdrawFileUploadRepository.save(w);

				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}

		}
	}

	public void saveReceipt(List<MedicalReceipt> req) {
		medicalReceiptRep.save(req);
	}

	public List<MedicalReceipt> findReceiptAll() {
		return medicalReceiptRep.findAll();
	}

	public MedicalReceipt findReceipt(BigDecimal id) {
		return medicalReceiptRep.findOne(id.longValue());
	}

	public List<Lov> hospital() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("MEDICAL");
	}

}
