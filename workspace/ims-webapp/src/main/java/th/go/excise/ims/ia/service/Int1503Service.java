package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepaccMasRepository;

@Service
public class Int1503Service {
	
	@Autowired
	private ExciseDepaccMasRepository exciseDepaccMasRepository;
	
	
	public List<ExciseDepaccMas> listData() {
		List<ExciseDepaccMas> dataList = new ArrayList<ExciseDepaccMas>();
		dataList = exciseDepaccMasRepository.listData();
		return dataList;
	}

}
