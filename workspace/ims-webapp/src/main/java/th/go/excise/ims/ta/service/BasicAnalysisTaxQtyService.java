package th.go.excise.ims.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.TaPaperBaD1Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxQtyVo;

@Service
public class BasicAnalysisTaxQtyService extends AbstractBasicAnalysisService<BasicAnalysisTaxQtyVo> {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxQtyService.class);
	
	@Autowired
	private TaPaperBaD1Repository taPaperBaD1Repository;
	
	@Override
	protected List<BasicAnalysisTaxQtyVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BasicAnalysisTaxQtyVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		
	}

}
