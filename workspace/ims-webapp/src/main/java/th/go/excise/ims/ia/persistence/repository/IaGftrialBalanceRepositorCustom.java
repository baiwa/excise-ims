package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.vo.Int0802SearchVo;
import th.go.excise.ims.ia.vo.Int0802Vo;

public interface IaGftrialBalanceRepositorCustom {
	public void batchInsert(List<IaGftrialBalance> iaGftrialBalances);

	public List<IaGftrialBalance> findByGfDisburseUnit(String gfDisburseUnit);

	public List<Int0802Vo> findDiferrenceByCondition(Int0802SearchVo reqeust);
}
