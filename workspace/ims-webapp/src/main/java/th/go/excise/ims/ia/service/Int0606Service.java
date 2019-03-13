package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaCheckLicense;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0606JdbcRepository;
import th.go.excise.ims.ia.vo.Int0606FormVo;

@Service
public class Int0606Service {
	
	@Autowired
	Int0606JdbcRepository int0606JdbcRepository;
	
	public List<IaCheckLicense> list(Int0606FormVo form) {
		List<IaCheckLicense> iaCheckLicenseList = new ArrayList<IaCheckLicense>();
		iaCheckLicenseList = int0606JdbcRepository.list(form);
		return iaCheckLicenseList;
	}

}
