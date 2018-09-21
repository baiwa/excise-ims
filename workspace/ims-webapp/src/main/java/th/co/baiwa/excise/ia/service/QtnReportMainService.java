package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonManageReq;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.dao.QtnReportMainDao;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportDetail;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportMain;
import th.co.baiwa.excise.ia.persistence.repository.qtn.rep.QtnReportDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.qtn.rep.QtnReportMainRepository;
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

	public ResponseDataTable<Int023Vo<QtnReportDetail>> findQtnReport(QtnReportMain qtn, DataTableRequest data) {
		logger.info("findQtnReport");
		List<Int023Vo<QtnReportDetail>> int023 = qtnReportMainDao.findQtnReport(qtn, data.getStart().intValue(),
				data.getLength().intValue());
		List<QtnReportDetail> detail;
		for (Int023Vo<QtnReportDetail> vo : int023) {
			detail = new ArrayList<QtnReportDetail>();
			detail = qtnReportDetailRepository.findByQtnReportManId(vo.getQtnReportManId());
			vo.setDetail(detail);
		}
		ResponseDataTable<Int023Vo<QtnReportDetail>> response = new ResponseDataTable<Int023Vo<QtnReportDetail>>();
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
		Long mainId = 0L;
		Message msg = ApplicationCache.getMessage("MSG_00003");
		String user = UserLoginUtils.getCurrentUsername();
		if (vo.getSave().size() != 0) {
			logger.info("saveQtnReport saved... !!~ ");
			QtnReportMain m;
			QtnReportDetail d;
			for (Int023FormVo v : vo.getSave()) {
				logger.info("For : {}", v.getQtnFor());
				if ("M".equals(v.getQtnFor())) { // For IA_QTN_REPORT_MAIN
					if (BeanUtils.isEmpty(v.getQtnReportManId())) {
						m = new QtnReportMain();
						m.setCreatedBy(user);
						m.setQtnMainDetail(v.getQtnMainDetail());
						m.setQtnReportHdrId(Long.parseLong(v.getQtnReportHdrId()));
					} else {
						m = qtnReportMainRepository.findOne(Long.parseLong(v.getQtnReportManId()));
					}
					m = qtnReportMainRepository.save(m);
					mainId = m.getQtnReportManId();
					msg = ApplicationCache.getMessage("MSG_00002");
				}
				if ("D".equals(v.getQtnFor())) { // For IA_QTN_REPORT_DETAIL
					if (BeanUtils.isEmpty(v.getQtnReportDtlId())) {
						d = new QtnReportDetail();
						d.setCreatedBy(user);
						d.setQtnMainDetail(v.getQtnMainDetail());
						d.setQtnReportManId(mainId);
					} else {
						d = qtnReportDetailRepository.findOne(Long.parseLong(v.getQtnReportDtlId()));
					}
					d = qtnReportDetailRepository.save(d);
					msg = ApplicationCache.getMessage("MSG_00002");
				}
			}
		}
		if (vo.getDelete().size() != 0) {
			logger.info("saveQtnReport deleted... !!~ ");
			boolean[] has = { false, false };
			List<Long> lim = new ArrayList<>();
			List<Long> lid = new ArrayList<>();
			for (Int023FormVo v : vo.getDelete()) {
				if ("M".equals(v.getQtnFor())) { // For IA_QTN_REPORT_MAIN
					lim.add(Long.parseLong(v.getQtnReportManId()));
					has[0] = true;
				}
				if ("D".equals(v.getQtnFor())) { // For IA_QTN_REPORT_DETAIL
					lid.add(Long.parseLong(v.getQtnReportDtlId()));
					has[1] = true;
				}
			}
			if (has[0]) {
				qtnReportMainRepository.delete(lim);
				msg = ApplicationCache.getMessage("MSG_00002");
			}
			if (has[1]) {
				qtnReportDetailRepository.delete(lid);
				msg = ApplicationCache.getMessage("MSG_00002");
			}
		}
		return msg;
	}

}
