package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicD2;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicH;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicD2Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0602JdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.AuditLicD1Vo;
import th.go.excise.ims.ia.vo.AuditLicD2Vo;
import th.go.excise.ims.ia.vo.AuditLicHVo;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
import th.go.excise.ims.ia.vo.Int0602ResultTab2Vo;
import th.go.excise.ims.ia.vo.Int0602SaveVo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Service
public class Int0602Service {
	private static final Logger logger = LoggerFactory.getLogger(Int0602Service.class);

	@Autowired
	private Int0602JdbcRepository int0602JdbcRepository;

	@Autowired
	private IaAuditLicHRepository iaAuditLicHRepository;

	@Autowired
	private IaAuditLicD1Repository iaAuditLicD1Repository;

	@Autowired
	private IaAuditLicD2Repository iaAuditLicD2Repository;

	@Autowired
	private IaCommonService iaCommonService;

	public List<Int0602ResultTab1Vo> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriterai");
		List<WsLicfri6010> wsLicfri6010List = int0602JdbcRepository.findByCriteria(int0602FormVo, "LIC_NO");
		List<Int0602ResultTab1Vo> int0602ResultTab1Vo = new ArrayList<>();
		Int0602ResultTab1Vo intiData = null;
		if (wsLicfri6010List != null && wsLicfri6010List.size() > 0) {
			for (WsLicfri6010 wsLicfri6010 : wsLicfri6010List) {
				intiData = new Int0602ResultTab1Vo();
				try {
					BeanUtils.copyProperties(intiData, wsLicfri6010);
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
					int0602ResultTab1Vo.add(intiData);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		return int0602ResultTab1Vo;
	}

	public List<Int0602ResultTab2Vo> findTab2Criteria(Int0602FormVo int0602FormVo) {
		return int0602JdbcRepository.findTab2Criteria(int0602FormVo);
	}

	public AuditLicHVo saveLicListService(Int0602SaveVo vo) {
		logger.info("saveLicListService : {} ", vo.getAuditLicH().getAuditLicNo());

		// Header
		IaAuditLicH licH = null;
		if (StringUtils.isNotBlank(vo.getAuditLicH().getAuditLicNo())) {
			licH = iaAuditLicHRepository.findByAuditLicNo(vo.getAuditLicH().getAuditLicNo());
		} else {
			licH = new IaAuditLicH();
			licH.setOfficeCode(vo.getAuditLicH().getOfficeCode());
			licH.setLicDateFrom(ConvertDateUtils.parseStringToDate(vo.getAuditLicH().getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			licH.setLicDateTo(ConvertDateUtils.parseStringToDate(vo.getAuditLicH().getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			licH.setAuditLicNo(iaCommonService.autoGetRunAuditNoBySeqName("AL", vo.getAuditLicH().getOfficeCode(), "AUDIT_LIC_NO_SEQ", 8));
		}

		licH.setD1AuditFlag(vo.getAuditLicH().getD1AuditFlag());
		licH.setD1ConditionText(vo.getAuditLicH().getD1ConditionText());
		licH.setD1CriteriaText(vo.getAuditLicH().getD1CriteriaText());
		licH.setD2AuditFlag(vo.getAuditLicH().getD2AuditFlag());
		licH.setD2ConditionText(vo.getAuditLicH().getD2ConditionText());
		licH.setD2CriteriaText(vo.getAuditLicH().getD2CriteriaText());

		licH = iaAuditLicHRepository.save(licH);
		vo.getAuditLicH().setAuditLicSeq(licH.getAuditLicSeq());
		vo.getAuditLicH().setAuditLicNo(licH.getAuditLicNo());

		// D1
		if (vo.getAuditLicD1List() != null && vo.getAuditLicD1List().size() > 0) {
			IaAuditLicD1 val1 = null;
			List<IaAuditLicD1> iaAuditLicD1List = new ArrayList<>();
			for (AuditLicD1Vo auditLicD1Vo : vo.getAuditLicD1List()) {
				val1 = new IaAuditLicD1();
				if (auditLicD1Vo.getAuditLicD1Seq() != null) {
					val1 = iaAuditLicD1Repository.findById(auditLicD1Vo.getAuditLicD1Seq()).get();
					val1.setSeqNo(auditLicD1Vo.getSeqNo());
					val1.setRunCheck(auditLicD1Vo.getRunCheck());
					val1.setLicRemark(auditLicD1Vo.getLicRemark());
					val1.setActionFlag(auditLicD1Vo.getActionFlag());
					val1 = iaAuditLicD1Repository.save(val1);
				} else {
					val1.setSeqNo(auditLicD1Vo.getSeqNo());
					val1.setAuditLicNo(licH.getAuditLicNo());
					val1.setLicType(auditLicD1Vo.getLicType());
					val1.setLicNo(auditLicD1Vo.getLicNo());
					val1.setRunCheck(auditLicD1Vo.getRunCheck());
					val1.setLicDate(ConvertDateUtils.parseStringToDate(auditLicD1Vo.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val1.setSendDate(ConvertDateUtils.parseStringToDate(auditLicD1Vo.getSendDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val1.setLicName(auditLicD1Vo.getLicName());
					val1.setIncCode(auditLicD1Vo.getIncCode());
					val1.setLicPrice(auditLicD1Vo.getLicPrice());
					val1.setLicFee(auditLicD1Vo.getLicFee());
					val1.setLicInterior(auditLicD1Vo.getLicInterior());
					val1.setLicRemark(auditLicD1Vo.getLicRemark());
					val1.setActionFlag(auditLicD1Vo.getActionFlag());
					iaAuditLicD1List.add(val1);
				}
			}
			iaAuditLicD1Repository.saveAll(iaAuditLicD1List);
		}
		// D2
		if (vo.getAuditLicD2List() != null && vo.getAuditLicD2List().size() > 0) {
			IaAuditLicD2 val2 = null;
			List<IaAuditLicD2> iaAuditLicD2List = new ArrayList<>();
			for (AuditLicD2Vo auditLicD2Vo : vo.getAuditLicD2List()) {
				val2 = new IaAuditLicD2();
				if (auditLicD2Vo.getAuditLicD2Seq() != null) {
					val2 = iaAuditLicD2Repository.findById(auditLicD2Vo.getAuditLicD2Seq()).get();
					val2.setAuditCheck(auditLicD2Vo.getAuditCheck());
					val2.setLicT2Remark(auditLicD2Vo.getLicT2Remark());
					val2 = iaAuditLicD2Repository.save(val2);
				} else {
					val2.setAuditLicNo(licH.getAuditLicNo());
					val2.setTaxCode(auditLicD2Vo.getTaxCode());
					val2.setLicName(auditLicD2Vo.getLicName());
					val2.setLicPrice(auditLicD2Vo.getLicPrice());
					val2.setLicCount(auditLicD2Vo.getLicCount());
					val2.setAuditCheck(auditLicD2Vo.getAuditCheck());
					val2.setLicT2Remark(auditLicD2Vo.getLicT2Remark());
					iaAuditLicD2List.add(val2);
				}

			}
			iaAuditLicD2Repository.saveAll(iaAuditLicD2List);
		}
		return vo.getAuditLicH();
	}

	public List<AuditLicHVo> findAuditLicHVoList() {
		List<IaAuditLicH> iaAuditLicHList = iaAuditLicHRepository.findIaAuditLicHAllDataActive();
		AuditLicHVo licH = null;
		List<AuditLicHVo> auditLicHVoList = new ArrayList<>();

		for (IaAuditLicH data : iaAuditLicHList) {
			licH = new AuditLicHVo();
			try {
				licH.setAuditLicSeq(data.getAuditLicSeq());
				licH.setOfficeCode(data.getOfficeCode());
				licH.setLicDateFrom(data.getLicDateFrom() != null ? ConvertDateUtils.formatDateToString(data.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				licH.setLicDateTo(data.getLicDateTo() != null ? ConvertDateUtils.formatDateToString(data.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				licH.setAuditLicNo(data.getAuditLicNo());
				licH.setD1AuditFlag(data.getD1AuditFlag());
				licH.setD1ConditionText(data.getD1ConditionText());
				licH.setD1CriteriaText(data.getD1CriteriaText());
				licH.setD2AuditFlag(data.getD2AuditFlag());
				licH.setD2ConditionText(data.getD2ConditionText());
				licH.setD2CriteriaText(data.getD2CriteriaText());
				auditLicHVoList.add(licH);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		}
		return auditLicHVoList;
	}

	public List<AuditLicD1Vo> findAuditLicD1ByAuditLicNo(String auditLicNo) throws Exception {
		List<AuditLicD1Vo> auditLicD1VoList = new ArrayList<>();
		AuditLicD1Vo val1 = new AuditLicD1Vo();
		List<IaAuditLicD1> iaAuditLicD1List = iaAuditLicD1Repository.findByAuditLicNoOrderBySeqNo(auditLicNo);
		for (IaAuditLicD1 data : iaAuditLicD1List) {
			val1 = new AuditLicD1Vo();
			val1.setAuditLicD1Seq(data.getAuditLicD1Seq());
			val1.setSeqNo(data.getSeqNo());
			val1.setAuditLicNo(data.getAuditLicNo());
			val1.setLicType(data.getLicType());
			val1.setLicNo(data.getLicNo());
			val1.setRunCheck(data.getRunCheck());
			val1.setLicDate(ConvertDateUtils.formatDateToString(data.getLicDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			val1.setSendDate(ConvertDateUtils.formatDateToString(data.getSendDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			val1.setLicName(data.getLicName());
			val1.setIncCode(data.getIncCode());
			val1.setLicPrice(data.getLicPrice());
			val1.setLicFee(data.getLicFee());
			val1.setLicInterior(data.getLicInterior());
			val1.setLicRemark(data.getLicRemark());
			val1.setActionFlag(data.getActionFlag());
			auditLicD1VoList.add(val1);
		}
		return auditLicD1VoList;
	}

	public List<AuditLicD2Vo> findAuditLicD2ByAuditLicNo(String auditLicNo) throws Exception {
		List<AuditLicD2Vo> auditLicD2VoList = new ArrayList<>();
		AuditLicD2Vo val2 = new AuditLicD2Vo();
		List<IaAuditLicD2> iaAuditLicD2List = iaAuditLicD2Repository.findByAuditLicNo(auditLicNo);
		for (IaAuditLicD2 data : iaAuditLicD2List) {
			val2 = new AuditLicD2Vo();
			val2.setAuditLicD2Seq(data.getAuditLicD2Seq());
			val2.setAuditLicNo(data.getAuditLicNo());
			val2.setTaxCode(data.getTaxCode());
			val2.setLicName(data.getLicName());
			val2.setLicPrice(data.getLicPrice());
			val2.setLicCount(data.getLicCount());
			val2.setAuditCheck(data.getAuditCheck());
			val2.setLicT2Remark(data.getLicT2Remark());

			auditLicD2VoList.add(val2);
		}
		return auditLicD2VoList;
	}

	public AuditLicHVo findIaAuditLicHByAuditLicNo(String auditLicNo) {
		logger.info("findIaAuditLicHByAuditLicNo auditLicNo={}", auditLicNo);

		AuditLicHVo LicHVo = null;
		IaAuditLicH data = null;
		ExciseDepartmentVo excise = null;
		data = iaAuditLicHRepository.findByAuditLicNo(auditLicNo);
		try {
			LicHVo = new AuditLicHVo();
			LicHVo.setOfficeCode(data.getOfficeCode());
			LicHVo.setLicDateFrom(data.getLicDateFrom() != null ? ConvertDateUtils.formatDateToString(data.getLicDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
			LicHVo.setLicDateTo(data.getLicDateTo() != null ? ConvertDateUtils.formatDateToString(data.getLicDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
			LicHVo.setAuditLicNo(data.getAuditLicNo());
			LicHVo.setD1AuditFlag(data.getD1AuditFlag());
			LicHVo.setD1ConditionText(data.getD1ConditionText());
			LicHVo.setD1CriteriaText(data.getD1CriteriaText());
			LicHVo.setD2AuditFlag(data.getD2AuditFlag());
			LicHVo.setD2ConditionText(data.getD2ConditionText());
			LicHVo.setD2CriteriaText(data.getD2CriteriaText());

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(data.getOfficeCode());
			LicHVo.setArea(excise.getArea());
			LicHVo.setSector(excise.getSector());
			LicHVo.setBranch(excise.getBranch());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return LicHVo;
	}

}
