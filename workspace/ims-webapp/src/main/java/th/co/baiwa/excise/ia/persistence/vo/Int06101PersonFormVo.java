package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.IaWithdrawalList;
import th.co.baiwa.excise.ia.persistence.entity.IaWithdrawalPersons;

public class Int06101PersonFormVo extends IaWithdrawalList {

	private static final long serialVersionUID = 4411794133700725607L;
	
	private List<IaWithdrawalPersons> persons;

	public List<IaWithdrawalPersons> getPersons() {
		return persons;
	}

	public void setPersons(List<IaWithdrawalPersons> persons) {
		this.persons = persons;
	}
	
}
