package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonManageReq;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.dao.QtnReportMainDao;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportDetail;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportMain;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportMainRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int023FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int023Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class QtnReportMainService {

	@Autowired
	QtnReportMainDao qtnReportMainDao;

	@Autowired
	QtnReportMainRepository qtnReportMainRepository;

	@Autowired
	QtnReportDetailRepository qtnReportDetailRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ResponseDataTable<Int023Vo> findQtnReport(QtnReportMain qtn, DataTableRequest data) {
		logger.info("findQtnReport");
		List<Int023Vo> int023 = qtnReportMainDao.findQtnReport(qtn, data.getStart().intValue(),
				data.getLength().intValue());
		List<QtnReportDetail> detail;
		for (Int023Vo vo : int023) {
			detail = new ArrayList<QtnReportDetail>();
			detail = qtnReportDetailRepository.findByQtnReportManId(vo.getQtnReportManId());
			vo.setDetail(detail);
		}
		ResponseDataTable<Int023Vo> response = new ResponseDataTable<Int023Vo>();
		long count = qtnReportMainDao.countQtnReport(qtn);
		response.setDraw(data.getDraw().intValue() + 1);
		response.setStart(data.getStart().intValue());
		response.setData(int023);
		response.setLength((int) count);
		response.setRecordsTotal((int) count);
		response.setRecordsFiltered((int) count);
		return response;
	}

	public Message saveQtnReport(CommonManageReq<Int023FormVo> vo) {
		Long main = 0L;
		Message msg = ApplicationCache.getMessage("MSG_00003");
		if (vo.getSave().size() != 0) {
			for (Int023FormVo v : vo.getSave()) {
				if (v.getQtnFor() == "M") { // For IA_QTN_REPORT_MAIN
					main = null;
				}
				if (v.getQtnFor() == "D") { // For IA_QTN_REPORT_DETAIL
					
				}
			}
		}
		if (vo.getDelete().size() != 0) {
			for (Int023FormVo v: vo.getDelete()) {
				if (v.getQtnFor() == "M") { // For IA_QTN_REPORT_MAIN
					
				}
				if (v.getQtnFor() == "D") { // For IA_QTN_REPORT_DETAIL
					
				}
			}
		}
		return msg;
	}

}
