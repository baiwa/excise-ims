package th.go.excise.ims.ia.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingH;
import th.go.excise.ims.ia.persistence.repository.IaAuditWorkingHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaEmpWorkingDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int091201FormSaveVo;
import th.go.excise.ims.ia.vo.Int091201FormSearchVo;
import th.go.excise.ims.ia.vo.Int091201Vo;

@Service
public class Int091201Service {
	@Autowired
	private IaEmpWorkingDtlJdbcRepository iaEmpWorkingDtlJdbcRepository;

	@Autowired
	private IaAuditWorkingHRepository iaAuditWorkingHRepository;

	public List<Int091201Vo> getList(Int091201FormSearchVo res) {
		List<Int091201Vo> dataRes = iaEmpWorkingDtlJdbcRepository.getList(res);
		return dataRes;
	}

	public void saveAuditWorking(Int091201FormSaveVo res) {
		IaAuditWorkingH dataSave = new IaAuditWorkingH();

		Date date = ConvertDateUtils.parseStringToDate(res.getAuWorkingMonth(), ConvertDateUtils.MM_YYYY,
				ConvertDateUtils.LOCAL_TH);
		String dateStr = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

		String auditIncNo = iaEmpWorkingDtlJdbcRepository.generateAuditIncNo();
		auditIncNo = res.getBranch() + "/" + auditIncNo;
		dataSave.setAuditWorkingNo(auditIncNo);
		dataSave.setAuWorkingMonth(dateStr);
		dataSave.setAuPetitionNo(res.getAuPetitionNo());
		dataSave.setAuOfficeCode(res.getBranch());
		dataSave.setWorkingConditionText(res.getWorkingConditionText());
		dataSave.setWorkingCriteriaText(res.getWorkingCriteriaText());
//		iaAuditWorkingHRepository.save(dataSave);
	}
}
