package th.co.baiwa.excise.ia.persistence.repository.tax.receipt;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceipt;

public interface IaTaxReceiptRepository extends CommonJpaCrudRepository<IaTaxReceipt, Long>, IaTaxReceiptRepositoryCustom{
	
}
