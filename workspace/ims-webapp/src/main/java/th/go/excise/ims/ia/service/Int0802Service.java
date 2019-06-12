package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
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
			BigDecimal sumDebit2 = BigDecimal.ZERO;
			BigDecimal sumCredit2 = BigDecimal.ZERO;
			BigDecimal sumDiffDebit = BigDecimal.ZERO;
			BigDecimal sumDiffCredit = BigDecimal.ZERO;
			
			response = iaGftrialBalanceRepositoryl.findDiferrenceByCondition(reqeust);
			for (Int0802Vo int0802Vo : response) {
				if(int0802Vo.getCurrAmt() != null) {
					if ("40".equals(int0802Vo.getPkCode())) {
						int0802Vo.setDebit2(int0802Vo.getCurrAmt());
						int0802Vo.setSumDebit2(sumDebit2.add(int0802Vo.getCurrAmt()));
					} else if ("50".equals(int0802Vo.getPkCode())) {
						int0802Vo.setCredit2(int0802Vo.getCurrAmt());
						int0802Vo.setSumCredit2(sumCredit2.add(int0802Vo.getCurrAmt()));
					}
				}
				
				int0802Vo.setDiffCredit(checkNullBigdecimal(int0802Vo.getCredit()).subtract(checkNullBigdecimal(int0802Vo.getCredit2())));
				int0802Vo.setDiffDebit(checkNullBigdecimal(int0802Vo.getDebit()).subtract(checkNullBigdecimal(int0802Vo.getDebit2())));
				int0802Vo.setSumDiffCredit(sumDiffCredit.add(int0802Vo.getDiffCredit()));
				int0802Vo.setSumDiffDebit(sumDiffDebit.add(int0802Vo.getDiffDebit()));
			}
		}
		return response;
	}
	
	private BigDecimal checkNullBigdecimal(BigDecimal param) {
		return param != null ? param: BigDecimal.ZERO;
	}

}
