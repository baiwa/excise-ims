package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;

public interface IaGftrialBalanceRepositorCustom {
	public void batchInsert(List<IaGftrialBalance> iaGftrialBalances) ;
}
