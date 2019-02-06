package co.th.ims.excise.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.ims.excise.dao.ExciseBranchDao;
import co.th.ims.excise.domain.ExciseBranch;

@Service
public class ExciseBranchService {

	@Autowired
	private ExciseBranchDao exciseBranchDao;
	
	public List<ExciseBranch> findBySectorId(BigDecimal bigDecimal){
		return exciseBranchDao.findBySectorId(bigDecimal);
	}
}
