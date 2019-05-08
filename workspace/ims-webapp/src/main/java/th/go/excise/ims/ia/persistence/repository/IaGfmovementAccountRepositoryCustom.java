package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;

public interface IaGfmovementAccountRepositoryCustom {
	public void batchInsert(List<IaGfmovementAccount> iaGfmovementAccounts);
}
