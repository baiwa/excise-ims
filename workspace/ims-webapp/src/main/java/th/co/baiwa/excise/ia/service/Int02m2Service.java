package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.QtnFinalRepDetail;
import th.co.baiwa.excise.ia.persistence.entity.QtnFinalRepHeader;
import th.co.baiwa.excise.ia.persistence.entity.QtnFinalRepMain;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;
import th.co.baiwa.excise.ia.persistence.repository.QtnFinalRepDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnFinalRepHeaderRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnFinalRepMainRepository;
import th.co.baiwa.excise.ia.persistence.repository.QtnMasterRepositoryImpl;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportDetailRepositoryImpl;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportHeaderRepositoryImpl;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportMainRepositoryImpl;
import th.co.baiwa.excise.ia.persistence.vo.Int02m2Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int02m2VoDetail;
import th.co.baiwa.excise.ia.persistence.vo.QtnReportDetailVo;
import th.co.baiwa.excise.ia.persistence.vo.QtnReportHeaderVo;
import th.co.baiwa.excise.ia.persistence.vo.QtnReportMainVo;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class Int02m2Service {

//	@Autowired
//	private QtnMasterRepository qtnMasterRepo; // Questionnaire Master

	@Autowired
	private QtnMasterRepositoryImpl qtnMasterRepoImpl; // Questionnaire Master Implement

//	@Autowired
//	private QtnReportHeaderRepository qtnHeaderRepo; // Questionnaire Report Header

	@Autowired
	private QtnReportHeaderRepositoryImpl qtnHeaderRepoImpl; // Questionnaire Report Header Implemnet

//	@Autowired
//	private QtnReportMainRepository qtnMainRepo; // Questionnaire Report Main

	@Autowired
	private QtnReportMainRepositoryImpl qtnMainRepoImpl; // Questionnaire Report Main Implement

//	@Autowired
//	private QtnReportDetailRepository qtnDetailRepo; // Questionnaire Report Detail

	@Autowired
	private QtnReportDetailRepositoryImpl qtnDetailRepoImpl; // Questionnaire Report Detail Implement

	@Autowired
	private QtnFinalRepHeaderRepository qtnFinalRepHdrRepo; // Questionnaire Final Report Header

	@Autowired
	private QtnFinalRepMainRepository qtnFinalRepManRepo; // Questionnaire Final Report Main

	@Autowired
	private QtnFinalRepDetailRepository qtnFinalRepDtlRepo; // Questionnaire Final Report Detail

	@Autowired
	private WebServiceExciseService webService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static String TYPE_HDR = "header";
	private static String TYPE_DTL = "detail";

	public Message saveData(List<Int02m2Vo> data, String save) {
		Message msg = ApplicationCache.getMessage("MSG_00003");
		try {
			QtnFinalRepHeader header;
			String user = UserLoginUtils.getCurrentUsername();
			String stat = "";
			if ("save".equals(save)) {
				stat = FLAG.N_FLAG;
			} else {
				stat = FLAG.Y_FLAG;
			}
			for (Int02m2Vo da : data) {
				if (BeanUtils.isNotEmpty(da.getId()) && da.getId() != 0) {
					// logger.info("HeaderId : {}", da.getId());
					header = qtnFinalRepHdrRepo.findOne(da.getId());
					header.setQtnCreator(user);
					header.setQtnFinished(stat);
					header.setQtnReportHdrId(da.getHeaderId());
					header.setQtnConclusion(da.getConclusion());
					header = qtnFinalRepHdrRepo.save(header);
				} else {
					header = new QtnFinalRepHeader();
					header.setQtnCreator(user);
					header.setQtnFinished(stat);
					header.setQtnReportHdrId(da.getHeaderId());
					header.setQtnConclusion(da.getConclusion());
					header = qtnFinalRepHdrRepo.save(header);
				}
				if (da.getDetail().size() > 0) {
					QtnFinalRepMain main = new QtnFinalRepMain();
					List<QtnFinalRepDetail> details = new ArrayList<>();
					QtnFinalRepDetail detail;
					for (Int02m2VoDetail de : da.getDetail()) {
						if (BeanUtils.isNotEmpty(de.getId()) && de.getId() != 0) {
							if (BeanUtils.isNotEmpty(de.getHeaderId())) {
								// logger.info(" HeaderID: {}", de.getId());
								main = qtnFinalRepManRepo.findOne(de.getId());
								main.setQtnCreator(user);
								main.setQtnPoint(de.getPoint());
								main.setQtnReportManId(de.getHeaderId());
								main.setQtnFinalRepHdrId(header.getQtnFinalRepHdrId());
								main = qtnFinalRepManRepo.save(main);
								// logger.info(" Point: {}", de.getPoint());
							} else {
								// logger.info(" DetailID: {}", de.getId());
								detail = qtnFinalRepDtlRepo.findOne(de.getId());
								detail.setQtnCreator(user);
								detail.setQtnPoint(de.getPoint());
								detail.setQtnReportDtlId(de.getDetailId());
								detail.setQtnFinalRepManId(main.getQtnFinalRepManId());
								details.add(detail);
								// logger.info(" Point: {}", de.getPoint());
							}
						} else {
							if (BeanUtils.isNotEmpty(de.getHeaderId())) {
								main = new QtnFinalRepMain();
								main.setQtnCreator(user);
								main.setQtnPoint(de.getPoint());
								main.setQtnReportManId(de.getHeaderId());
								main.setQtnFinalRepHdrId(header.getQtnFinalRepHdrId());
								main = qtnFinalRepManRepo.save(main);
								// logger.info(" Point: {}", de.getPoint());
							} else {
								detail = new QtnFinalRepDetail();
								detail.setQtnCreator(user);
								detail.setQtnPoint(de.getPoint());
								detail.setQtnReportDtlId(de.getDetailId());
								detail.setQtnFinalRepManId(main.getQtnFinalRepManId());
								details.add(detail);
								// logger.info(" Point: {}", de.getPoint());
							}
						}
					}
					if (details.size() > 0) {
						qtnFinalRepDtlRepo.save(details);
						details = new ArrayList<>();
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

	public List<Int02m2Vo> findData(String year) {
		String code = UserLoginUtils.getCurrentUserBean().getOfficeCode(); // 010100
		String user = UserLoginUtils.getCurrentUserBean().getUsername();
		if (BeanUtils.isEmpty(code)) { // Pull UserBean from WebServiceLDAP
			code = webService.webServiceLdap(user, "password").getOffice();
		}
		List<Lov> lov = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", code);
		logger.info("{} {} {}", lov.get(0).getSubTypeDescription(), lov.get(0).getLovId(), code);
		// String sector = lov.get(0).getSubTypeDescription();
		// String.valueOf(lov.get(0).getLovId());
		// String year = "2561";
		String sector = code.substring(0, 2);
		String area = code.substring(2, 4);
		String finished = "Y";
		logger.info("Sector: {} , Area: {}", sector, area);
		List<Int02m2Vo> int02m2 = new ArrayList<>();
		try {
			List<QtnMaster> master = new ArrayList<>();
			master = qtnMasterRepoImpl.findData(sector, area, year, finished);
			// qtnMasterRepo.findBySectorAndAreaAndYear(sector, year, "Y");
			if (master.size() > 0) {
				Long masterId = master.get(0).getQtnMasterId();
				// logger.info("QTN_MASTER_ID: {}", masterId);
				List<QtnReportHeaderVo> header = qtnHeaderRepoImpl.findJoinFinal(masterId, user);
				if (header.size() == 0) {
					user = null;
					header = qtnHeaderRepoImpl.findJoinFinal(masterId, user);
				}
				if (header.size() > 0) {
					for (QtnReportHeaderVo h : header) {
						Int02m2Vo vo = new Int02m2Vo();
						Long headerId = h.getQtnReportHdrId();
						// logger.info("QTN_REPORT_HDR_ID: {}", headerId);
						List<QtnReportMainVo> main = qtnMainRepoImpl.findJoinFinal(headerId, user);
						if (main.size() == 0) {
							user = null;
							main = qtnMainRepoImpl.findJoinFinal(headerId, user);
						}
						List<Int02m2VoDetail> int02m2Details = new ArrayList<>();
						int i = 1;
						for (QtnReportMainVo m : main) {
							// logger.info("QTN_REPORT_MAN_ID: {}", m.getQtnReportManId());
							Int02m2VoDetail int02m2Detail = new Int02m2VoDetail();
							int02m2Detail.setOrder(String.valueOf(i));
							int02m2Detail.setId(m.getMainId());
							int02m2Detail.setPoint(m.getPoint());
							int02m2Detail.setHeaderId(m.getQtnReportManId());
							int02m2Detail.setContent(m.getQtnMainDetail());
							int02m2Details.add(int02m2Detail);
							List<QtnReportDetailVo> detail = qtnDetailRepoImpl.findJoinFinal(m.getQtnReportManId(),
									user);
							if (detail.size() == 0) {
								user = null;
								detail = qtnDetailRepoImpl.findJoinFinal(m.getQtnReportManId(), user);
							}
							int j = 1;
							for (QtnReportDetailVo d : detail) {
								// logger.info("QTN_REPORT_DTL_ID: {}", d.getQtnReportDtlId());
								int02m2Detail = new Int02m2VoDetail();
								int02m2Detail.setOrder(String.valueOf(i) + "." + String.valueOf(j));
								int02m2Detail.setId(d.getDetailId());
								int02m2Detail.setPoint(d.getPoint());
								int02m2Detail.setDetailId(d.getQtnReportDtlId());
								int02m2Detail.setContent(d.getQtnMainDetail());
								int02m2Detail.setType(TYPE_DTL);
								int02m2Details.add(int02m2Detail);
								// logger.info("DETAIL_ID: {}", d.getQtnReportDtlId());
								j++;
							}
							if (j == 1) {
								int02m2Detail = int02m2Details.get(int02m2Details.size() - 1);
								int02m2Detail.setType(TYPE_HDR);
								int02m2Details.set(int02m2Details.size() - 1, int02m2Detail);
								// logger.info("ID: {}", int02m2Detail.getHeaderId());
								// get to set
							}
							i++;
						}
						vo.setId(h.getHeaderId());
						vo.setHeaderId(headerId);
						vo.setTitle(h.getQtnReportHdrName());
						vo.setContent(h.getQtnReportHdrName());
						vo.setConclusion(h.getConclusion());
						vo.setFinished(h.getQtnFinished());
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
