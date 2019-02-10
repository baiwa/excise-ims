package th.go.excise.ims.common.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.dao.ExciseAreaDao;
import th.go.excise.ims.common.domain.ExciseArea;

@Service
public class ExciseAreaService {

	@Autowired
	private ExciseAreaDao exciseAreaDao;
	
	public List<ExciseArea> findAllExciseSector(BigDecimal sectorId){
		return exciseAreaDao.findBySectorId(sectorId);
	}
}
