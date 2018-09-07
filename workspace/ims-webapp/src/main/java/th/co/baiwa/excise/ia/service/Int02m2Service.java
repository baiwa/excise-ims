package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.QtnFinalRepHeader;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportDetail;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportMain;
import th.co.baiwa.excise.ia.persistence.repository.QtnFinalRepHeaderRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnMasterRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnMasterRepositoryImpl;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportHeaderRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportMainRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int02m2Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int02m2VoDetail;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class Int02m2Service {

	
	@Autowired
	private QtnMasterRepository qtnMasterRepo; // Questionnaire Master 
	
	@Autowired
	private QtnMasterRepositoryImpl qtnMasterRepoImpl; // Questionnaire Master Implement

	@Autowired
	private QtnReportHeaderRepository qtnHeaderRepo; // Questionnaire Report Header

	@Autowired
	private QtnReportMainRepository qtnMainRepo; // Questionnaire Report Main

	@Autowired
	private QtnReportDetailRepository qtnDetailRepo; // Questionnaire Report Detail
	
	@Autowired
	private QtnFinalRepHeaderRepository qtnFinalRepHdrRepo; // Questionnaire Final Report Header
	
	@Autowired
	private WebServiceExciseService webService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static String TYPE_HDR = "header";
	private static String TYPE_DTL = "detail";

	public Message saveData(List<Int02m2Vo> data) {
		Message msg = ApplicationCache.getMessage("MSG_00003");
		try {
			QtnFinalRepHeader header;
			String user = UserLoginUtils.getCurrentUsername();
			for (Int02m2Vo da : data) {
				if (da.getDetail().size() > 0) {
					header = new QtnFinalRepHeader();
					header.setQtnCreator(user);
					header.setQtnReportHdrId(da.getId());
					header.setQtnConclusion(da.getConclusion());
					header = qtnFinalRepHdrRepo.save(header);
					for (Int02m2VoDetail de : da.getDetail()) {
						if (BeanUtils.isEmpty(de.getHeaderId())) {
							logger.info(" Point: {} ", de.getPoint());
						}
					}
				}
			}
			msg = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		return msg;
	}

	public List<Int02m2Vo> findData() {
		String code = UserLoginUtils.getCurrentUserBean().getOfficeCode(); // 010100
		String user = UserLoginUtils.getCurrentUserBean().getUsername();
		if (BeanUtils.isEmpty(code)) { // Pull UserBean from WebServiceLDAP
			code = webService.webServiceLdap(user, "password").getOffice();
		}
		List<Lov> lov = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", code);
		logger.info("{} {} {}", lov.get(0).getSubTypeDescription(), lov.get(0).getLovId(), code);
		// String sector = lov.get(0).getSubTypeDescription(); // String.valueOf(lov.get(0).getLovId());
		String year = "2561";
		String sector = code.substring(0, 2);
		String area = code.substring(2, 4);
		String finished = "Y";
		logger.info("Sector: {} , Area: {}", sector, area);
		List<Int02m2Vo> int02m2 = new ArrayList<>();
		try {
			List<QtnMaster> master = new ArrayList<>();
			master = qtnMasterRepoImpl.findData(sector, area, year, finished); // qtnMasterRepo.findBySectorAndAreaAndYear(sector, year, "Y");
			if (master.size() > 0) {
				Long masterId = master.get(0).getQtnMasterId();
				// logger.info("QTN_MASTER_ID: {}", masterId);
				List<QtnReportHeader> header = qtnHeaderRepo.findbyMasterId(masterId);
				if (header.size() > 0) {
					for (QtnReportHeader h : header) {
						Int02m2Vo vo = new Int02m2Vo();
						Long headerId = h.getQtnReportHdrId();
						// logger.info("QTN_REPORT_HDR_ID: {}", headerId);
						List<QtnReportMain> main = qtnMainRepo.findByHeaderId(headerId);
						List<Int02m2VoDetail> int02m2Details = new ArrayList<>();
						int i = 1;
						for (QtnReportMain m : main) {
							// logger.info("QTN_REPORT_MAN_ID: {}", m.getQtnReportManId());
							Int02m2VoDetail int02m2Detail = new Int02m2VoDetail();
							int02m2Detail.setOrder(String.valueOf(i));
							int02m2Detail.setHeaderId(m.getQtnReportManId());
							int02m2Detail.setContent(m.getQtnMainDetail());
							List<QtnReportDetail> detail = qtnDetailRepo
									.findByQtnReportManId(m.getQtnReportManId());
							int02m2Details.add(int02m2Detail);
							int j = 1;
							for (QtnReportDetail d : detail) {
								// logger.info("QTN_REPORT_DTL_ID: {}", d.getQtnReportDtlId());
								int02m2Detail = new Int02m2VoDetail();
								int02m2Detail.setOrder(String.valueOf(i) + "." + String.valueOf(j));
								int02m2Detail.setDetailId(d.getQtnReportDtlId());
								int02m2Detail.setContent(d.getQtnMainDetail());
								int02m2Detail.setType(TYPE_DTL);
								int02m2Details.add(int02m2Detail);
								j++;
							}
							if (j == 1) {
								int02m2Detail = int02m2Details.get(i-1);
								int02m2Detail.setType(TYPE_HDR);
								int02m2Details.get(int02m2Details.size());
								// get to set
							}
							i++;
						}
						vo.setId(headerId);
						vo.setTitle(h.getQtnReportHdrName());
						vo.setContent(h.getQtnReportHdrName());
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

	public List<Int02m2Vo> findMock(Int02m2Vo find) { // Mock
		logger.info("findMock");
		List<Int02m2Vo> result = new ArrayList<>();
		List<Int02m2VoDetail> details;
		Int02m2Vo main;
		Int02m2VoDetail detail;
		for (int i = 1; i <= 3; i++) {
			int k = 1, h = 1;
			main = new Int02m2Vo();
			main.setTitle("การเงิน " + i);
			main.setContent("-");
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
