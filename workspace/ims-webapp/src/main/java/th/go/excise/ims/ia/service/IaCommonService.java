package th.go.excise.ims.ia.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaJdbcRepository;

@Service
public class IaCommonService {
	
	@Autowired
	private IaJdbcRepository iaJdbcRepository;
	
	public long findSeqBySeqName(String seqName) {
		return iaJdbcRepository.findOracleSeqBySeqName(seqName);
	}
	
	
	public String autoGetRunAuditNoBySeqName(String seqName , int length) {
		return StringUtils.leftPad(String.valueOf(iaJdbcRepository.findOracleSeqBySeqName(seqName)), length, "0");
	}
}
