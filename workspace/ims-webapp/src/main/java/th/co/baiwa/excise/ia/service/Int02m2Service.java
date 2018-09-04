package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportDetail;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportMain;
import th.co.baiwa.excise.ia.persistence.repository.QtnMasterRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportHeaderRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportMainRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int02m2Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int02m2VoDetail;

@Service
public class Int02m2Service {

	@Autowired
	private QtnMasterRepository qtnMasterRepository;

	@Autowired
	private QtnReportHeaderRepository qtnHeaderReposity;

	@Autowired
	private QtnReportMainRepository qtnMainRepository;

	@Autowired
	private QtnReportDetailRepository qtnDetailReposity;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Int02m2Vo> findByCreteria() {
		String user = UserLoginUtils.getCurrentUsername();
		String sector = "ภาคที่ 1";
		String area = user.equals("ryangek") ? "สำนักงานสรรพสามิตพื้นที่อยุธยา" : "สำนักงานสรรพสามิตพื้นที่ชัยนาท";
		String year = "2561";
		List<Int02m2Vo> int02m2 = new ArrayList<>();
		try {
			List<QtnMaster> master = qtnMasterRepository.findBySectorAndAreaAndYear(sector, area, year);
			if (master.size() > 0) {
				Long masterId = master.get(0).getQtnMasterId();
				logger.info("QTN_MASTER_ID: {}", masterId);
				List<QtnReportHeader> header = qtnHeaderReposity.findbyMasterId(masterId);
				if (header.size() > 0) {
					for (QtnReportHeader h : header) {
						Int02m2Vo vo = new Int02m2Vo();
						Long headerId = h.getQtnReportHdrId();
						logger.info("QTN_REPORT_HDR_ID: {}", headerId);
						List<QtnReportMain> main = qtnMainRepository.findByHeaderId(headerId);
						List<Int02m2VoDetail> int02m2Details = new ArrayList<>();
						int i = 1;
						for (QtnReportMain m : main) {
							logger.info("QTN_REPORT_MAN_ID: {}", m.getQtnReportManId());
							Int02m2VoDetail int02m2Detail = new Int02m2VoDetail();
							int02m2Detail.setOrder(String.valueOf(i));
							int02m2Detail.setHeaderId(m.getQtnReportManId());
							int02m2Detail.setContent(m.getQtnMainDetail());
							List<QtnReportDetail> detail = qtnDetailReposity
									.findByQtnReportManId(m.getQtnReportManId());
							int02m2Details.add(int02m2Detail);
							int j = 1;
							for (QtnReportDetail d : detail) {
								logger.info("QTN_REPORT_DTL_ID: {}", d.getQtnReportDtlId());
								int02m2Detail = new Int02m2VoDetail();
								int02m2Detail.setOrder(String.valueOf(i) + "." + String.valueOf(j));
								int02m2Detail.setDetailId(d.getQtnReportDtlId());
								int02m2Detail.setContent(d.getQtnMainDetail());
								int02m2Details.add(int02m2Detail);
								j++;
							}
							i++;
						}
						vo.setTitle(h.getQtnReportHdrName());
						vo.setContent(h.getQtnReportHdrName());
						vo.setConclusion(true);
						vo.setDetail(int02m2Details);
						int02m2.add(vo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return int02m2;
	}

	public List<Int02m2Vo> findByCreteria(Int02m2Vo find) { // Mock
		logger.info("findByCreteria");
		List<Int02m2Vo> result = new ArrayList<>();
		List<Int02m2VoDetail> details;
		Int02m2Vo main;
		Int02m2VoDetail detail;
		for (int i = 1; i <= 3; i++) {
			int k = 1, h = 1;
			main = new Int02m2Vo();
			main.setTitle("การเงิน " + i);
			main.setContent("-");
			main.setConclusion(true);
			details = new ArrayList<>();
			for (int j = 1; j < 100; j++) {
				detail = new Int02m2VoDetail();
				if (j == 1) {
					detail.setHeaderId(Long.valueOf(j));
					detail.setOrder("" + j);
					detail.setContent("หลัก " + j);
					k = 1;
					h = 1;
				} else {
					detail.setDetailId(Long.valueOf(k));
					detail.setOrder(h + "." + k);
					detail.setContent("ย่อย " + h + "." + k++);
				}
				details.add(detail);
			}
			main.setDetail(details);
			result.add(main);
		}
		return result;
	}

}
