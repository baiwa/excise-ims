package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicdupH;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicdupHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0602JdbcRepository;
import th.go.excise.ims.ia.vo.AuditLicdupHVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Service
public class Int0603Service {

	private static final Logger logger = LoggerFactory.getLogger(Int0603Service.class);

	@Autowired
	private Int0602JdbcRepository int0602JdbcRepository;

	@Autowired
	private IaAuditLicdupHRepository iaAuditLicdupHRepository;

	public List<Int0602ResultTab1Vo> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriterai");
		List<WsLicfri6010> wsLicfri6010List = int0602JdbcRepository.findByCriteria(int0602FormVo, "PRINT_COUNT");
		List<Int0602ResultTab1Vo> int0602ResultTab1Vo = new ArrayList<>();
		Int0602ResultTab1Vo intiData = null;
		if (wsLicfri6010List != null && wsLicfri6010List.size() > 0) {
			for (WsLicfri6010 wsLicfri6010 : wsLicfri6010List) {
				intiData = new Int0602ResultTab1Vo();
				try {
					if (StringUtils.isNoneBlank(wsLicfri6010.getSendDate())) {
						Date date = ConvertDateUtils.parseStringToDate(wsLicfri6010.getSendDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN);
						intiData.setSendDate(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (StringUtils.isNoneBlank(wsLicfri6010.getExpDate())) {
						Date date = ConvertDateUtils.parseStringToDate(wsLicfri6010.getExpDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN);
						intiData.setExpDate(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (StringUtils.isNoneBlank(wsLicfri6010.getStartDate())) {
						Date date = ConvertDateUtils.parseStringToDate(wsLicfri6010.getStartDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN);
						intiData.setStartDate(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (StringUtils.isNoneBlank(wsLicfri6010.getLicDate())) {
						Date date = ConvertDateUtils.parseStringToDate(wsLicfri6010.getLicDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN);
						intiData.setLicDate(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
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
					logger.error(e.getMessage());
				}
			}
		}
		return int0602ResultTab1Vo;
	}

	public List<AuditLicdupHVo> findAuditLicdupHList() {
		List<IaAuditLicdupH> iaAuditLicHList = iaAuditLicdupHRepository.findIaAuditLicdupHAllDataActive();
		AuditLicdupHVo auditLicdupHVo = null;
		List<AuditLicdupHVo> auditLicdupHVosList = new ArrayList<>();
		for (IaAuditLicdupH iaAuditLicdupH : iaAuditLicHList) {
			auditLicdupHVo = new AuditLicdupHVo();
			try {
				auditLicdupHVo.setAuditLicdupSeq(iaAuditLicdupH.getAuditLicdupSeq());
				auditLicdupHVo.setOfficeCode(iaAuditLicdupH.getOfficeCode());
				auditLicdupHVo.setLicDateFrom(iaAuditLicdupH.getLicDateFrom() != null ? ConvertDateUtils.formatDateToString(iaAuditLicdupH.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				auditLicdupHVo.setLicDateTo(iaAuditLicdupH.getLicDateTo() != null ? ConvertDateUtils.formatDateToString(iaAuditLicdupH.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				auditLicdupHVo.setAuditLicdupNo(iaAuditLicdupH.getAuditLicdupNo());
				auditLicdupHVo.setAuditFlag(iaAuditLicdupH.getAuditFlag());
				auditLicdupHVo.setConditionText(iaAuditLicdupH.getConditionText());
				auditLicdupHVo.setCriteriaText(iaAuditLicdupH.getCriteriaText());
				auditLicdupHVosList.add(auditLicdupHVo);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		return auditLicdupHVosList;
	}

}
