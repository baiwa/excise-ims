package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.repository.IaChartOfAccRepository;

@Service
public class Int1502Service {
	
	@Autowired
	private  IaChartOfAccRepository iaChartOfAccRepository;
	
	public List<IaChartOfAcc> getDropdownCoaCode() {
		return iaChartOfAccRepository.getCoaCodeList();
	}

}
