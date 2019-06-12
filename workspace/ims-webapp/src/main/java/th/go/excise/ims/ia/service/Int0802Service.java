package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.vo.Int0802SearchVo;
import th.go.excise.ims.ia.vo.Int0802Vo;

@Service
public class Int0802Service {

	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepositoryl;

	public List<IaGftrialBalance> getRangePeriod(String gfDisburseUnit) {
		return iaGftrialBalanceRepositoryl.findByGfDisburseUnit(gfDisburseUnit);
	}

	public List<Int0802Vo> getResultByCondition(Int0802SearchVo reqeust) {
		List<Int0802Vo> response = new ArrayList<Int0802Vo>();
		if ("Y".equals(reqeust.getFlagSearch())) {
			response = iaGftrialBalanceRepositoryl.findDiferrenceByCondition(reqeust);
		}
		return response;
	}

}
