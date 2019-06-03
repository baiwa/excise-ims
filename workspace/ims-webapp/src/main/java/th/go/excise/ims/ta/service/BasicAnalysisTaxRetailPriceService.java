package th.go.excise.ims.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.TaPaperBaD2Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxRetailPriceVo;

@Service
public class BasicAnalysisTaxRetailPriceService extends AbstractBasicAnalysisService<BasicAnalysisTaxRetailPriceVo> {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxRetailPriceService.class);
	
	@Autowired
	private TaPaperBaD2Repository taPaperBaD2Repository;

	@Override
	protected List<BasicAnalysisTaxRetailPriceVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BasicAnalysisTaxRetailPriceVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		
	}
	
}
