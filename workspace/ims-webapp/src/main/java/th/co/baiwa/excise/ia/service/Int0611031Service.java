package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;

@Service
public class Int0611031Service {

	private final String ACADEMY_TYPE = "ACADEMY_TYPE";
	@Autowired
	private LovRepository lovRepository;

	public List<Lov> typeEducation() {
		return lovRepository.findByTypeAndLovIdMasterIsNull(ACADEMY_TYPE);
	}

	public List<Lov> subTypeEducation(String idMaster) {
		List<Lov> res = new ArrayList<>();
		if (StringUtils.isNotBlank(idMaster)) {
			return lovRepository.findByTypeAndLovIdMaster(ACADEMY_TYPE, Long.valueOf(idMaster));
		}
		return res;
	}

	public List<Lov> levelEducation(String idMaster) {
		List<Lov> res = new ArrayList<>();
		if (StringUtils.isNotBlank(idMaster)) {
			return lovRepository.findByTypeAndLovIdMaster(ACADEMY_TYPE, Long.valueOf(idMaster));
		}
		return res;
	}

	public List<Lov> typeSubject(String idMaster) {
		List<Lov> res = new ArrayList<>();
		if (StringUtils.isNotBlank(idMaster)) {
			return lovRepository.findByTypeAndLovIdMaster(ACADEMY_TYPE, Long.valueOf(idMaster));
		}
		return res;
	}

	public List<Lov> bursary(String idMaster) {
		List<Lov> res = new ArrayList<>();
		if (StringUtils.isNotBlank(idMaster)) {
			res = lovRepository.findByTypeAndLovIdMaster(ACADEMY_TYPE, Long.valueOf(idMaster));
		}
		return res;
	}

	public Lov checkMoney(String idMaster) {
		Lov res = new Lov();
		if (StringUtils.isNotBlank(idMaster)) {
			res = lovRepository.findByTypeAndLovId(ACADEMY_TYPE, Long.valueOf(idMaster));
		}
		return res;
	}

}
