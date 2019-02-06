package co.th.ims.excise.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.ims.excise.dao.ExciseAreaDao;
import co.th.ims.excise.domain.ExciseArea;

@Service
public class ExciseGeoService {

	@Autowired
	private ExciseAreaDao exciseAreaDao;
	
	public List<ExciseArea> findAllExciseSector(BigDecimal sectorId){
		return exciseAreaDao.findBySectorId(sectorId);
	}
}
