package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicexpD;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicexpH;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicexpDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicexpHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0602JdbcRepository;
import th.go.excise.ims.ia.vo.AuditLicexpDVo;
import th.go.excise.ims.ia.vo.AuditLicexpHVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
import th.go.excise.ims.ia.vo.Int0604SaveVo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Service
public class Int0604Service {

	private static final Logger logger = LoggerFactory.getLogger(Int0604Service.class);

	@Autowired
	private Int0602JdbcRepository int0602JdbcRepository;

	@Autowired
	private IaAuditLicexpHRepository auditLicexpHRepository;

	@Autowired
	private IaAuditLicexpDRepository auditLicexpDRepository;

	public List<Int0602ResultTab1Vo> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriterai");
		List<WsLicfri6010> wsLicfri6010List = int0602JdbcRepository.findByCriteria(int0602FormVo, "EXP_DATE");
		List<Int0602ResultTab1Vo> int0602ResultTab1Vo = new ArrayList<>();
		Int0602ResultTab1Vo intiData = null;
		if (wsLicfri6010List != null && wsLicfri6010List.size() > 0) {
			for (WsLicfri6010 wsLicfri6010 : wsLicfri6010List) {
				intiData = new Int0602ResultTab1Vo();
				try {
					if (wsLicfri6010.getSendDate() != null) {
						intiData.setSendDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getSendDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (wsLicfri6010.getExpDate() != null) {
						intiData.setExpDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getExpDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (wsLicfri6010.getStartDate() != null) {
						intiData.setStartDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (wsLicfri6010.getLicDate() != null) {
						intiData.setLicDate(ConvertDateUtils.formatLocalDateToString(wsLicfri6010.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					intiData.setOffcode(wsLicfri6010.getOffcode());
					intiData.setLicType(wsLicfri6010.getLicType());
					intiData.setLicNo(wsLicfri6010.getLicNo());
					intiData.setLicName(wsLicfri6010.getLicName());
					intiData.setLicFee(wsLicfri6010.getLicFee());
					intiData.setLicInterior(wsLicfri6010.getLicInterior());
					intiData.setLicPrice(wsLicfri6010.getLicPrice());
					intiData.setPrintCount(wsLicfri6010.getPrintCount());
					intiData.setNid(wsLicfri6010.getNid());
					intiData.setNewRegId(wsLicfri6010.getNewRegId());
					intiData.setCusFullname(wsLicfri6010.getCusFullname());
					intiData.setFacFullname(wsLicfri6010.getFacFullname());
					intiData.setIncCode(wsLicfri6010.getIncCode());

					int0602ResultTab1Vo.add(intiData);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
		return int0602ResultTab1Vo;
	}

	public List<AuditLicexpHVo> findAuditLicdupHList() {
		List<IaAuditLicexpH> iaAuditLicexpHList = auditLicexpHRepository.findIaAuditLicexpHAllDataActive();
		AuditLicexpHVo auditLicexpHVo = null;
		List<AuditLicexpHVo> auditLicexpHVoList = new ArrayList<>();
		for (IaAuditLicexpH iaAuditLicexpH : iaAuditLicexpHList) {
			auditLicexpHVo = new AuditLicexpHVo();
			try {
				auditLicexpHVo.setAuditLicexpSeq(iaAuditLicexpH.getAuditLicexpSeq());
				auditLicexpHVo.setOfficeCode(iaAuditLicexpH.getOfficeCode());
				auditLicexpHVo.setLicexpDateFrom(iaAuditLicexpH.getLicexpDateFrom() != null ? ConvertDateUtils.formatDateToString(iaAuditLicexpH.getLicexpDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				auditLicexpHVo.setLicexpDateTo(iaAuditLicexpH.getLicexpDateTo() != null ? ConvertDateUtils.formatDateToString(iaAuditLicexpH.getLicexpDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				auditLicexpHVo.setAuditLicexpNo(iaAuditLicexpH.getAuditLicexpNo());
				auditLicexpHVo.setAuditFlag(iaAuditLicexpH.getAuditFlag());
				auditLicexpHVo.setConditionText(iaAuditLicexpH.getConditionText());
				auditLicexpHVo.setCriteriaText(iaAuditLicexpH.getCriteriaText());
				auditLicexpHVoList.add(auditLicexpHVo);

			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}

		}
		return auditLicexpHVoList;
	}

	public AuditLicexpHVo save(Int0604SaveVo vo) {
		logger.info("saveExpListService : {} ", vo.getAuditLicexpH().getAuditLicexpNo());
		IaAuditLicexpH licexpH = null;
		try {
			if (StringUtils.isNotBlank(vo.getAuditLicexpH().getAuditLicexpNo())) {
				licexpH = new IaAuditLicexpH();
				licexpH = auditLicexpHRepository.findByAuditLicexpNo(vo.getAuditLicexpH().getAuditLicexpNo());
				licexpH.setAuditFlag(vo.getAuditLicexpH().getAuditFlag());
				licexpH.setConditionText(vo.getAuditLicexpH().getConditionText());
				licexpH.setCriteriaText(vo.getAuditLicexpH().getCriteriaText());
				licexpH = auditLicexpHRepository.save(licexpH);
				vo.getAuditLicexpH().setAuditLicexpSeq(licexpH.getAuditLicexpSeq());
				vo.getAuditLicexpH().setAuditLicexpNo(licexpH.getAuditLicexpNo());
			} else {
				licexpH = new IaAuditLicexpH();
				licexpH.setOfficeCode(vo.getAuditLicexpH().getOfficeCode());
				licexpH.setLicexpDateFrom(ConvertDateUtils.parseStringToDate(vo.getAuditLicexpH().getLicexpDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				licexpH.setLicexpDateTo(ConvertDateUtils.parseStringToDate(vo.getAuditLicexpH().getLicexpDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				licexpH.setAuditLicexpNo(vo.getAuditLicexpH().getOfficeCode() + "/" + auditLicexpHRepository.generateAuditLicexpNo());
				licexpH.setAuditFlag(vo.getAuditLicexpH().getAuditFlag());
				licexpH.setConditionText(vo.getAuditLicexpH().getConditionText());
				licexpH.setCriteriaText(vo.getAuditLicexpH().getCriteriaText());
				licexpH = auditLicexpHRepository.save(licexpH);
				vo.getAuditLicexpH().setAuditLicexpSeq(licexpH.getAuditLicexpSeq());
				vo.getAuditLicexpH().setAuditLicexpNo(licexpH.getAuditLicexpNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if (vo.getAuditLicexpDList() != null && vo.getAuditLicexpDList().size() > 0) {
			IaAuditLicexpD val = null;
			List<IaAuditLicexpD> auditLicexpDList = new ArrayList<>();
			for (AuditLicexpDVo data : vo.getAuditLicexpDList()) {
				val = new IaAuditLicexpD();
				if (data.getAuditLicexpSeq() != null) {
					val = auditLicexpDRepository.findById(data.getAuditLicexpSeq()).get();
					try {
						val = auditLicexpDRepository.save(val);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				} else {
					try {
						val.setOfficeCode(vo.getAuditLicexpH().getOfficeCode());
						val.setAuditLicexpNo(vo.getAuditLicexpH().getAuditLicexpNo());
						val.setNewRegId(data.getNewRegId());
						val.setCusFullName(data.getCusFullName());
						val.setFacFullName(data.getFacFullName());
						val.setLicType(data.getLicType());
						val.setLicNo(data.getLicNo());
						val.setLicDate(ConvertDateUtils.parseStringToDate(data.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						val.setExpDate(ConvertDateUtils.parseStringToDate(data.getExpDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						val.setLicDateNew(ConvertDateUtils.parseStringToDate(data.getLicDateNew(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						val.setLicNoNew(data.getLicNoNew());
					} catch (Exception e) {
						e.printStackTrace();
					}
					auditLicexpDList.add(val);
				}

			}
			auditLicexpDRepository.saveAll(auditLicexpDList);
		}

		return vo.getAuditLicexpH();
	}

	public List<AuditLicexpDVo> findAuditLicexpDByAuditLicdupNo(String auditLicexpNo) throws Exception {
		List<AuditLicexpDVo> auditLicexpDVoList = new ArrayList<>();
		AuditLicexpDVo auditLicexpDVo = null;
		List<IaAuditLicexpD> auditLicdupDList = auditLicexpDRepository.findByAuditLicexpNoOrderByExpDate(auditLicexpNo);
		for (IaAuditLicexpD data : auditLicdupDList) {
			auditLicexpDVo = new AuditLicexpDVo();
			try {
				auditLicexpDVo.setAuditLicexpSeq(data.getAuditLicexpSeq());
				auditLicexpDVo.setAuditLicexpNo(data.getAuditLicexpNo());
				auditLicexpDVo.setOfficeCode(data.getOfficeCode());
				auditLicexpDVo.setNewRegId(data.getNewRegId());
				auditLicexpDVo.setCusFullName(data.getCusFullName());
				auditLicexpDVo.setFacFullName(data.getFacFullName());
				auditLicexpDVo.setLicType(data.getLicType());
				auditLicexpDVo.setLicNo(data.getLicNo());
				auditLicexpDVo.setLicDate(ConvertDateUtils.formatDateToString(data.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				auditLicexpDVo.setExpDate(ConvertDateUtils.formatDateToString(data.getExpDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				auditLicexpDVo.setLicNoNew(data.getLicNoNew());
				auditLicexpDVo.setLicDateNew(ConvertDateUtils.formatDateToString(data.getLicDateNew(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				auditLicexpDVoList.add(auditLicexpDVo);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		return auditLicexpDVoList;
	}

	public AuditLicexpHVo findByAuditLicexpNo(String auditLicexpNo) {
		AuditLicexpHVo auditLicexpHVo = null;
		IaAuditLicexpH data = null;
		data = auditLicexpHRepository.findByAuditLicexpNo(auditLicexpNo);
		try {
			auditLicexpHVo = new AuditLicexpHVo();
			auditLicexpHVo.setAuditLicexpSeq(data.getAuditLicexpSeq());
			auditLicexpHVo.setAuditFlag(data.getAuditFlag());
			auditLicexpHVo.setAuditLicexpNo(data.getAuditLicexpNo());
			auditLicexpHVo.setConditionText(data.getConditionText());
			auditLicexpHVo.setCriteriaText(data.getCriteriaText());
			auditLicexpHVo.setLicexpDateFrom(ConvertDateUtils.formatDateToString(data.getLicexpDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditLicexpHVo.setLicexpDateTo(ConvertDateUtils.formatDateToString(data.getLicexpDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditLicexpHVo.setOfficeCode(data.getOfficeCode());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return auditLicexpHVo;
	}

}
