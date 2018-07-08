package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.SEQDao;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.domain.ia.Int023MappingVO;
import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireMainDetailDao;
import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireMinorDetailDao;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMainDetail;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMinorDetail;

@Service
public class QuestionnaireMainDetailService {

	@Autowired
	private QuestionnaireMainDetailDao questionnaireMainDetailDao;

	@Autowired
	private QuestionnaireMinorDetailDao questionnaireMinorDetailDao;

	@Autowired
	private SEQDao seqDao;

	public List<QuestionnaireMainDetail> findByCriteria(QuestionnaireMainDetail QuestionnaireMainDetail) {
		return questionnaireMainDetailDao.findByCriteria(QuestionnaireMainDetail);
	}

	public Integer createQuestionnaireMainDetail(Int023MappingVO int023MappingVO) {
		/* questionnaireMainDetail.setQtnMainDetail(); */
		int countInsert = 0;
		String userLogin = UserLoginUtils.getCurrentUsername();
		Date processDate = new Date();
		QuestionnaireMainDetail questionnaireMainDetail = new QuestionnaireMainDetail();
		BigDecimal mainId = seqDao.autoNumberRunningBySeqName("IA_QTN_MAIN_DETAIL_SEQ");
		questionnaireMainDetail.setQtnMainId(mainId);
		questionnaireMainDetail.setHeaderCode("H001");
		questionnaireMainDetail.setQtnMainDetail(int023MappingVO.getMainDetail());
		questionnaireMainDetail.setCreatedBy(userLogin);
		questionnaireMainDetail.setCreatedDate(processDate);
		int countInsertMain = questionnaireMainDetailDao.createQuestionnaireMainDetail(questionnaireMainDetail);
		if (countInsertMain > 0) {
			List<QuestionnaireMinorDetail> questionnaireMinorDetailList = new ArrayList<QuestionnaireMinorDetail>();
			QuestionnaireMinorDetail questionnaireMinorDetail = null;
			for (String minorDetail : int023MappingVO.getMinorDetail()) {
				questionnaireMinorDetail = new QuestionnaireMinorDetail();
				questionnaireMinorDetail.setQtnMinorId(seqDao.autoNumberRunningBySeqName("IA_QTN_MINOR_DETAIL_SEQ"));
				questionnaireMinorDetail.setMainId(mainId);
				questionnaireMinorDetail.setHeaderCode("H001");
				questionnaireMinorDetail.setQtnMinorDetail(minorDetail);
				questionnaireMinorDetail.setCreatedBy(userLogin);
				questionnaireMinorDetail.setCreatedDate(processDate);
				questionnaireMinorDetailList.add(questionnaireMinorDetail);
			}
			countInsert = questionnaireMinorDetailDao.createQuestionnaireMinorDetail(questionnaireMinorDetailList);

		}
		return countInsert;
	}

}
