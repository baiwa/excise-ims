package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.HospitalVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseHospital;
import th.go.excise.ims.preferences.persistence.repository.ExciseHospitalRepository;

@Service
public class Int12070102Service {
	private static final Logger logger = LoggerFactory.getLogger(Int12070102Service.class);

	@Autowired
	private ExciseHospitalRepository exciseHospitalRepository;

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
}
