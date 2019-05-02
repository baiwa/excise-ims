package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.ia.persistence.repository.IaEmpWorkingDtlRepository;
import th.go.excise.ims.ia.vo.IaEmpWorkingDtlSaveVo;

@Service
public class Int090102Service {

	@Autowired
	private IaEmpWorkingDtlRepository empWorkingDtlRepository;
	
	public void save(IaEmpWorkingDtlSaveVo formVo) {
		IaEmpWorkingDtl emp = new IaEmpWorkingDtl();
		emp.setUserLogin(UserLoginUtils.getCurrentUserBean().getUserThaiId());
		String userName = UserLoginUtils.getCurrentUserBean().getUserThaiName() + " " + UserLoginUtils.getCurrentUserBean().getUserThaiSurname();
		emp.setUserName(userName);
		emp.setUserOffcode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		emp.setWorkingDate(ConvertDateUtils.parseStringToDate(formVo.getWorkingDate(), ConvertDateUtils.DD_MM_YYYY));
		emp.setWorkingFlag(formVo.getWorkingFlag());
		emp.setWorkingDesc(formVo.getWorkingDesc());
		emp.setWorkingRemark(formVo.getWorkingRemark());
		emp.setReimburseExpFlag(formVo.getReimburseExpFlag());
		
		empWorkingDtlRepository.save(emp);
	}
	
	public List<IaEmpWorkingDtl> getByMonth(IaEmpWorkingDtlSaveVo formVo) {
		List<IaEmpWorkingDtl> emp = empWorkingDtlRepository.findByMonth(formVo);
		return emp;
	}
}
