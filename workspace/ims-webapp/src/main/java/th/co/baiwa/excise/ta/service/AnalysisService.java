package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ta.persistence.dao.AnalysisTaxDao;

@Service
public class AnalysisService {
	
	@Autowired
	private AnalysisTaxDao analysisTaxDao;

	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = analysisTaxDao.findAllExciseIdNotN();
		return dataList;
	}

}
