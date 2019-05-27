package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessD;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessH;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtD;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtH;
import th.go.excise.ims.ia.persistence.repository.IaAuditPmqtDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditPmqtHRepository;
import th.go.excise.ims.ia.vo.Int1301SaveVo;
import th.go.excise.ims.ia.vo.Int1304FormVo;
import th.go.excise.ims.ia.vo.Int1304SaveVo;
import th.go.excise.ims.ia.vo.Int1304Vo;
import th.go.excise.ims.ia.vo.WsPmAssessDVo;
import th.go.excise.ims.ia.vo.WsPmAssessHVo;
import th.go.excise.ims.ia.vo.WsPmQtDVo;
import th.go.excise.ims.ia.vo.WsPmQtHVo;
import th.go.excise.ims.ws.persistence.repository.WsPmQtDRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmQtHRepository;

@Service
public class Int1304Service {
	
	@Autowired
	private WsPmQtHRepository wsPmQtHRepository;
	
	@Autowired
	private WsPmQtDRepository wsPmQtDRepository;
	
	@Autowired
	private IaCommonService iaCommonService;
	
	@Autowired
	private IaAuditPmqtHRepository iaAuditPmqtHRepository ;
	
	@Autowired
	private IaAuditPmqtDRepository iaAuditPmqtDRepository ;
	
	
	public Int1304Vo getWsQt(Int1304FormVo request) {
		Int1304Vo response = new Int1304Vo();
		
		/* find header */
		List<WsPmQtHVo> resHeader = wsPmQtHRepository.filterWsPmQt(request);
		for (WsPmQtHVo wsPmQtHVo : resHeader) {
			/* find and set data detail */
			wsPmQtHVo.setDetail(wsPmQtDRepository.filterWsPmQtD(wsPmQtHVo.getOffCode(),wsPmQtHVo.getFormCode()));
			wsPmQtHVo.setProcessDateStr(ConvertDateUtils.formatLocalDateToString(wsPmQtHVo.getProcessDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		}
		response.setHeader(resHeader);
		return response;
	}
	
	public void saveWsQt(Int1304SaveVo request) throws Exception {
		IaAuditPmqtH header = null;
		IaAuditPmqtD detail = null;
		String auditQtNo = iaCommonService.autoGetRunAuditNoBySeqName("p", request.getHeader().get(0).getOffCode(), "AUDIT_PMQT_NO_SEQ", 8);
		for (WsPmQtHVo requestHdr :  request.getHeader()) {
			header = new IaAuditPmqtH();
			requestHdr.setProcessDate(null);
			BeanUtils.copyProperties(header, requestHdr);
			header.setProcessDate(ConvertDateUtils.parseStringToDate(requestHdr.getProcessDateStr(), ConvertDateUtils.DD_MM_YYYY));
			header.setAuditPmqtNo(auditQtNo);
			iaAuditPmqtHRepository.save(header);
			
			for (WsPmQtDVo requestDtl : requestHdr.getDetail()) {
				detail = new IaAuditPmqtD();
				BeanUtils.copyProperties(detail, requestDtl);
				detail.setPmQtDSeq(new BigDecimal(requestDtl.getPmQtDSeq()));
				detail.setAuditPmqtNo(auditQtNo);
				iaAuditPmqtDRepository.save(detail);
			}
		}
	}
	
	
}
