package th.go.excise.ims.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.TaPaperBaD4Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxRateVo;

@Service
public class BasicAnalysisTaxRateService extends AbstractBasicAnalysisService<BasicAnalysisTaxRateVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxRateService.class);

	@Autowired
	private TaPaperBaD4Repository taPaperBaD4Repository;

	@Override
	protected List<BasicAnalysisTaxRateVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BasicAnalysisTaxRateVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub

	}

}
