package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgGfmis;
import th.go.excise.ims.preferences.persistence.repository.ExciseOrgGfmisRepository;

@Service
public class Int1504Service {
	
	@Autowired
	private ExciseOrgGfmisRepository exciseOrgGfmisRepository;
	
	
	public List<ExciseOrgGfmis> listData() {
		List<ExciseOrgGfmis> dataList = new ArrayList<ExciseOrgGfmis>();
		dataList = exciseOrgGfmisRepository.listData();
		return dataList;
	}

}
