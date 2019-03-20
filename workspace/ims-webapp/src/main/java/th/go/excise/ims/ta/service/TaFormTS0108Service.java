package th.go.excise.ims.ta.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.vo.TaFormTS0108DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0108Vo;

@Service
public class TaFormTS0108Service {
	
	public TaFormTS0108Vo getTS0108() {
		TaFormTS0108Vo ts = new TaFormTS0108Vo();
		List<TaFormTS0108DtlVo> dtl = new ArrayList<>();
		ts.setFormTsNumber("000000-2562-0001");
		for (int i = 0; i < 1; i++) {
			dtl.add(new TaFormTS0108DtlVo());
			dtl.get(i).setApprovedAck("ApprovedAck");
			dtl.get(i).setAuditComment("AuditComment");
			Date date = new Date();
			dtl.get(i).setAuditDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			dtl.get(i).setAuditDest("AuditDest");
			dtl.get(i).setAuditResultDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			dtl.get(i).setAuditResultDocNo("AuditResultDocNo");
			dtl.get(i).setAuditTime("AuditTime");
			dtl.get(i).setAuditTopic("AuditTopic");
			dtl.get(i).setOfficerAck("OfficerAck");
			dtl.get(i).setOfficerFullName("OfficerFullName");
			dtl.get(i).setOfficerPosition("OfficerPosition");
			dtl.get(i).setRecNo(1);
		}
		ts.setTaFormTS0108DtlVoList(dtl);
		return ts;
	}
	
}
