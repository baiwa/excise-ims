package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.repository.IaChartOfAccRepository;

@Service
public class Int12040101Service {

	@Autowired
	private IaChartOfAccRepository iaChartOfAccRepository;

	public List<IaChartOfAcc> findAll() {
		List<IaChartOfAcc> data = new ArrayList<>();
		data = iaChartOfAccRepository.findAll();
		for (IaChartOfAcc iaChartOfAccData : data) {
			iaChartOfAccData.setCreatedBy(null);
			iaChartOfAccData.setCreatedDate(null);
			iaChartOfAccData.setUpdatedBy(null);
			iaChartOfAccData.setUpdatedDate(null);
			iaChartOfAccData.setVersion(null);
		}
		return data;
	}
}
