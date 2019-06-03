package th.go.excise.ims.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.TaPaperBaD6Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxFilingVo;

@Service
public class BasicAnalysisTaxFilingService extends AbstractBasicAnalysisService<BasicAnalysisTaxFilingVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxFilingService.class);

	@Autowired
	private TaPaperBaD6Repository taPaperBaD6Repository;

	@Override
	protected List<BasicAnalysisTaxFilingVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BasicAnalysisTaxFilingVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub

	}

}
