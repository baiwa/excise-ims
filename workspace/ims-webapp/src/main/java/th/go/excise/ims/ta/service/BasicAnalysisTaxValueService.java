package th.go.excise.ims.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.TaPaperBaD3Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxValueVo;

@Service
public class BasicAnalysisTaxValueService extends AbstractBasicAnalysisService<BasicAnalysisTaxValueVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxValueService.class);

	@Autowired
	private TaPaperBaD3Repository taPaperBaD3Repository;

	@Override
	protected List<BasicAnalysisTaxValueVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BasicAnalysisTaxValueVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub

	}

}
