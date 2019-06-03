package th.go.excise.ims.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.TaPaperBaD5Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxAmtVo;

@Service
public class BasicAnalysisTaxAmtService extends AbstractBasicAnalysisService<BasicAnalysisTaxAmtVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxAmtService.class);

	@Autowired
	private TaPaperBaD5Repository taPaperBaD5Repository;

	@Override
	protected List<BasicAnalysisTaxAmtVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BasicAnalysisTaxAmtVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub

	}

}
