package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaxOperatorVo {

	private String newRegId;
	private String condGroup;
	private String cusFullname;
	private String facFullname;
	private String facAddress;
	private String officeCode;
	private Integer countTaxPayOfMonth = 0;
	private List<String> taxYear = new ArrayList<>();
	private List<String> taxMonth = new ArrayList<>();
	private BigDecimal taxAmountBefore = BigDecimal.ZERO;
	private BigDecimal taxAmountAfter = BigDecimal.ZERO;
	private BigDecimal diffTaxAmount = BigDecimal.ZERO;
	private List<BigDecimal> taxAmount = new ArrayList<>();
	private String sectorName;
	private String areaShotName;

	public String getCondGroup() {
		return condGroup;
	}

	public void setCondGroup(String condGroup) {
		this.condGroup = condGroup;
	}

	public BigDecimal getDiffTaxAmount() {
		return diffTaxAmount;
	}

	public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
		this.diffTaxAmount = diffTaxAmount;
	}

	public Integer getCountTaxPayOfMonth() {
		return countTaxPayOfMonth;
	}

	public void setCountTaxPayOfMonth(Integer countTaxPayOfMonth) {
		this.countTaxPayOfMonth = countTaxPayOfMonth;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getFacAddress() {
		return facAddress;
	}

	public void setFacAddress(String facAddress) {
		this.facAddress = facAddress;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public List<String> getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(List<String> taxYear) {
		this.taxYear = taxYear;
	}

	public List<String> getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(List<String> taxMonth) {
		this.taxMonth = taxMonth;
	}

	public BigDecimal getTaxAmountBefore() {
		return taxAmountBefore;
	}

	public void setTaxAmountBefore(BigDecimal taxAmountBefore) {
		this.taxAmountBefore = taxAmountBefore;
	}

	public BigDecimal getTaxAmountAfter() {
		return taxAmountAfter;
	}

	public void setTaxAmountAfter(BigDecimal taxAmountAfter) {
		this.taxAmountAfter = taxAmountAfter;
	}

	public List<BigDecimal> getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(List<BigDecimal> taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getAreaShotName() {
		return areaShotName;
	}

	public void setAreaShotName(String areaShotName) {
		this.areaShotName = areaShotName;
	}

}
