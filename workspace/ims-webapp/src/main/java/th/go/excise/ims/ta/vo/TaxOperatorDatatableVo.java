package th.go.excise.ims.ta.vo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaxOperatorDatatableVo {

	private String cusFullname;
	private String facFullname;
	private String facAddress;
	private String officeCode;
	private String secCode;
	private String secDesc;
	private String areaCode;
	private String areaDesc;
	private String worksheetHdrId;
	private String draftNumber;
	private String newRegId;
	private String sumTaxAmtG1;
	private String sumTaxAmtG2;
	private String taxAmtChnPnt;
	private String taxMonthNo;
	private List<String> taxAmtList;

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

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecDesc() {
		return secDesc;
	}

	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getWorksheetHdrId() {
		return worksheetHdrId;
	}

	public void setWorksheetHdrId(String worksheetHdrId) {
		this.worksheetHdrId = worksheetHdrId;
	}

	public String getDraftNumber() {
		return draftNumber;
	}

	public void setDraftNumber(String draftNumber) {
		this.draftNumber = draftNumber;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getSumTaxAmtG1() {
		return sumTaxAmtG1;
	}

	public void setSumTaxAmtG1(String sumTaxAmtG1) {
		this.sumTaxAmtG1 = sumTaxAmtG1;
	}

	public String getSumTaxAmtG2() {
		return sumTaxAmtG2;
	}

	public void setSumTaxAmtG2(String sumTaxAmtG2) {
		this.sumTaxAmtG2 = sumTaxAmtG2;
	}

	public String getTaxAmtChnPnt() {
		return taxAmtChnPnt;
	}

	public void setTaxAmtChnPnt(String taxAmtChnPnt) {
		this.taxAmtChnPnt = taxAmtChnPnt;
	}

	public String getTaxMonthNo() {
		return taxMonthNo;
	}

	public void setTaxMonthNo(String taxMonthNo) {
		this.taxMonthNo = taxMonthNo;
	}

	public List<String> getTaxAmtList() {
		return taxAmtList;
	}

	public void setTaxAmtList(List<String> taxAmtList) {
		this.taxAmtList = taxAmtList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
