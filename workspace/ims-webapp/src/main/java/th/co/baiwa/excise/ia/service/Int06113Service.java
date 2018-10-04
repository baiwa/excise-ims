package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;

@Service
public class Int06113Service {

	@Autowired
	private LovRepository lovRepository;

	public List<Lov> sector(){
		return lovRepository.findByTypeAndLovIdMasterIsNull("SECTOR_LIST");
	}
}
