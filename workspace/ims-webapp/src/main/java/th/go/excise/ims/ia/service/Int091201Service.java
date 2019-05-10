package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingH;
import th.go.excise.ims.ia.persistence.repository.IaAuditWorkingD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditWorkingHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaEmpWorkingDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int091201FormSaveVo;
import th.go.excise.ims.ia.vo.Int091201FormSearchVo;
import th.go.excise.ims.ia.vo.Int091201HdrDtlVo;
import th.go.excise.ims.ia.vo.Int091201Vo;

@Service
public class Int091201Service {
	@Autowired
	private IaEmpWorkingDtlJdbcRepository iaEmpWorkingDtlJdbcRepository;

	@Autowired
	private IaAuditWorkingHRepository iaAuditWorkingHRepository;
	
	@Autowired
	private IaAuditWorkingD1Repository iaAuditWorkingD1Repository;

	public List<Int091201Vo> getList(Int091201FormSearchVo res) {
		List<Int091201Vo> dataRes = iaEmpWorkingDtlJdbcRepository.getList(res);
		return dataRes;
	}
	
	public List<IaAuditWorkingH> findHeaderAll(){
		return iaAuditWorkingHRepository.findAll();
	}
	
	public Int091201HdrDtlVo findDtl(String auditWorkingNo){
		Int091201HdrDtlVo dataRes = new Int091201HdrDtlVo();
		IaAuditWorkingH iaAuditWorkingH = iaAuditWorkingHRepository.findByAuditWorkingNo(auditWorkingNo);
		List<IaAuditWorkingD1> iaAuditWorkingD1List = iaAuditWorkingD1Repository.findByAuditWorkingNo(auditWorkingNo);
		dataRes.setIaAuditWorkingH(iaAuditWorkingH);
		dataRes.setIaAuditWorkingD1List(iaAuditWorkingD1List);
		return dataRes;
	}
	
	@Transactional
	public void saveAuditWorking(Int091201FormSaveVo res) {
		String offCode = null;
		if (StringUtils.isNotEmpty(res.getSector())) {
			if (StringUtils.isEmpty(res.getArea()) || "0".equals(res.getArea())) {
				offCode = res.getSector();
			} else if (StringUtils.isEmpty(res.getBranch()) || "0".equals(res.getBranch())) {
				offCode = res.getArea();
			} else if (StringUtils.isNotEmpty(res.getBranch()) && !"0".equals(res.getBranch())) {
				offCode = res.getBranch();
			}
		}
		
		IaAuditWorkingH dataSave = new IaAuditWorkingH();

		Date date = ConvertDateUtils.parseStringToDate(res.getAuWorkingMonth(), ConvertDateUtils.MM_YYYY,
				ConvertDateUtils.LOCAL_TH);
		String dateStr = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

		String auditIncNo = iaEmpWorkingDtlJdbcRepository.generateAuditIncNo();
		auditIncNo = offCode + "/" + auditIncNo;
		dataSave.setAuditWorkingNo(auditIncNo);
		dataSave.setAuWorkingMonth(dateStr);
		dataSave.setAuPetitionNo(res.getAuPetitionNo());
		dataSave.setAuOfficeCode(offCode);
		dataSave.setWorkingConditionText(res.getWorkingConditionText());
		dataSave.setWorkingCriteriaText(res.getWorkingCriteriaText());
		iaAuditWorkingHRepository.save(dataSave);
		
		List<IaAuditWorkingD1> iaAuditWorkingD1List = new ArrayList<IaAuditWorkingD1>();
		iaAuditWorkingD1List = res.getIaAuditWorkingD1List();
		IaAuditWorkingD1 iaAuditWorkingD1Save = null;
		
		for (IaAuditWorkingD1 iaAuditWorkingD1 : iaAuditWorkingD1List) {
			iaAuditWorkingD1Save = new IaAuditWorkingD1();
			iaAuditWorkingD1Save = iaAuditWorkingD1;
			iaAuditWorkingD1Save.setAuditWorkingNo(auditIncNo);
			iaAuditWorkingD1Save.setAuWorkingMonth(dateStr);
			iaAuditWorkingD1Save.setAuOfficeCode(offCode);
			iaAuditWorkingD1Repository.save(iaAuditWorkingD1Save);
		}
	}
}
