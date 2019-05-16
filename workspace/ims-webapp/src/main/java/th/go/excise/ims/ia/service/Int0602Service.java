package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicH;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditLicHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0602JdbcRepository;
import th.go.excise.ims.ia.vo.AuditLicD1Vo;
import th.go.excise.ims.ia.vo.AuditLicHVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
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

	public List<Int0602ResultTab1Vo> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriterai");
		List<WsLicfri6010> wsLicfri6010List = int0602JdbcRepository.findByCriteria(int0602FormVo);
		List<Int0602ResultTab1Vo> int0602ResultTab1Vo = new ArrayList<>();
		Int0602ResultTab1Vo intiData = null;
		if (wsLicfri6010List != null && wsLicfri6010List.size() > 0) {
			for (WsLicfri6010 wsLicfri6010 : wsLicfri6010List) {
				intiData = new Int0602ResultTab1Vo();
				try {
					BeanUtils.copyProperties(intiData, wsLicfri6010);
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
					int0602ResultTab1Vo.add(intiData);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		return int0602ResultTab1Vo;
	}

	public AuditLicHVo saveLicListService(Int0602SaveVo vo) {
		logger.info("saveLicListService : {} " , vo.getAuditLicH().getAuditLicNo());
		IaAuditLicH licH = null;
		if (StringUtils.isNotBlank(vo.getAuditLicH().getAuditLicNo())) {
			licH = iaAuditLicHRepository.findByAuditLicNoAndIsDeletedOrderByAuditLicSeqDesc(vo.getAuditLicH().getAuditLicNo(), FLAG.N_FLAG);
		} else {
			try {
				licH = new IaAuditLicH();
				BeanUtils.copyProperties(licH, vo.getAuditLicH());
				licH.setAuditLicNo(vo.getAuditLicH().getOfficeCode()+"/"+iaAuditLicHRepository.generateAuditLicNo());
				licH.setLicDateTo(ConvertDateUtils.parseStringToDate( vo.getAuditLicH().getLicDateToStr(), ConvertDateUtils.YYYYMMDD , ConvertDateUtils.LOCAL_TH));
				licH.setLicDateFrom(ConvertDateUtils.parseStringToDate( vo.getAuditLicH().getLicDateFromStr() , ConvertDateUtils.YYYYMMDD , ConvertDateUtils.LOCAL_TH));
				licH = iaAuditLicHRepository.save(licH);
				vo.getAuditLicH().setAuditLicSeq(licH.getAuditLicSeq());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (vo.getAuditLicD1List() != null && vo.getAuditLicD1List().size()>0) {
			IaAuditLicD1 val = null;
			List<IaAuditLicD1> iaAuditLicD1List = new ArrayList<>();
			for (AuditLicD1Vo auditLicD1Vo : vo.getAuditLicD1List()) {
				val = new IaAuditLicD1();
				try {
					BeanUtils.copyProperties(val, auditLicD1Vo);
					val.setLicDate(ConvertDateUtils.parseStringToDate(auditLicD1Vo.getLicDateStr() , ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
					val.setSendDate(ConvertDateUtils.parseStringToDate( auditLicD1Vo.getSendDateStr()  , ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
				} catch (Exception e) {
					e.printStackTrace();
				}
				val.setAuditLicNo(licH.getAuditLicNo());
				iaAuditLicD1List.add(val);
			}
			iaAuditLicD1Repository.saveAll(iaAuditLicD1List);
		}
		
		return vo.getAuditLicH();
	}
	
	
	public List<AuditLicHVo> findAuditLicHVoList() {
		List<IaAuditLicH> iaAuditLicHList = iaAuditLicHRepository.findIaAuditLicHAllDataActive();
		AuditLicHVo auditLicHVo = null;
		List<AuditLicHVo> auditLicHVoList = new ArrayList<>();
		for (IaAuditLicH iaAuditLicH : iaAuditLicHList) {
			auditLicHVo = new AuditLicHVo();
			try {
				
				BeanUtils.copyProperties(auditLicHVo, iaAuditLicH);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			auditLicHVoList.add(auditLicHVo);
		}
		return auditLicHVoList;
	}
	
	
	public List<AuditLicD1Vo> findAuditLicD1ByAuditLicNo( String auditLicNo) throws Exception{
		List<AuditLicD1Vo> auditLicD1VoList = new ArrayList<>();
		AuditLicD1Vo auditLicD1Vo = new AuditLicD1Vo();
		List<IaAuditLicD1> iaAuditLicD1List = iaAuditLicD1Repository.findByAuditLicNo(auditLicNo );
		for (IaAuditLicD1 iaAuditLicD1 : iaAuditLicD1List) {
			auditLicD1Vo = new AuditLicD1Vo();
			BeanUtils.copyProperties(auditLicD1Vo, iaAuditLicD1);
			auditLicD1Vo.setLicDateStr(ConvertDateUtils.formatDateToString(iaAuditLicD1.getLicDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH));
			auditLicD1Vo.setSendDateStr(ConvertDateUtils.formatDateToString(iaAuditLicD1.getSendDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH));
			auditLicD1VoList.add(auditLicD1Vo);
		}
		return auditLicD1VoList;
	}

}
