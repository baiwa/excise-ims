package th.co.baiwa.excise.ia.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;
import th.co.baiwa.excise.ia.persistence.repository.HealthCareWelFareRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int061102FormVo;

@Service
public class Int061102Service {

	@Autowired
	private HealthCareWelFareRepository healthCareWelFareRepository;

	public void save(Int061102FormVo int061102FormVo) {
		HealthCareWelFareEntity vo = new HealthCareWelFareEntity();
		vo.setFullName(int061102FormVo.getFullName());
		vo.setGender(int061102FormVo.getGender());
//		vo.setBirthDate(int061102FormVo.getBirthDate());
		vo.setSiblingsOrder(NumberUtils.toInt(int061102FormVo.getSiblingsOrder()));
		vo.setPosition(int061102FormVo.getPosition());
		vo.setAffiliation(int061102FormVo.getAffiliation());
		vo.setPhoneNumber(int061102FormVo.getPhoneNumber());
		vo.setStatus(int061102FormVo.getStatus());
		vo.setDisease(int061102FormVo.getDisease());
		vo.setHospitalName(int061102FormVo.getHospitalName());
		vo.setHospitalOwner(int061102FormVo.getHospitalOwner());
//		vo.setTreatedDateFrom(int061102FormVo.getTreatedDateFrom());
//		vo.setTreatedDateTo(int061102FormVo.getTreatedDateTo());
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
		
		healthCareWelFareRepository.save(vo);
	}

}
