package th.co.baiwa.excise.ia.persistence.repository.tax.receipt;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.tax.TaxReceipt;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceiptVo;

public interface IaTaxReceiptRepositoryCustom {

	public List<TaxReceipt> findByIaTaxReceipt(IaTaxReceiptVo iaTaxReceipt);
	
	public int coundByDateTo(String officeCode, String dateType, String dateTo);

	public int coundByDateFrom(String officeCode, String dateType, String dateFrom);

	public List<IaTaxReceiptVo> summaryTaxReceipt(IaTaxReceiptVo iaTaxReceipt);
	
}
