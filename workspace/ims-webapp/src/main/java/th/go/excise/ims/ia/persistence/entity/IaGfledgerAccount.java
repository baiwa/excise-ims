
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_GFLEDGER_ACCOUNT")
public class IaGfledgerAccount extends BaseEntity {
	
	private static final long serialVersionUID = 8981228250303210712L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_GFLEDGER_ACCOUNT_GEN")
	@SequenceGenerator(name = "IA_GFLEDGER_ACCOUNT_GEN", sequenceName = "IA_GFLEDGER_ACCOUNT_SEQ", allocationSize = 1)
	@Column(name = "IA_GFLEDGER_ACCOUNT_SEQ")
	private Long iaGfledgerAccountSeq;
	@Column(name = "ST_CODE")
	private String stCode;
	@Column(name = "DETERMINATON")
	private String determinaton;
	@Column(name = "DOC_NO")
	private String docNo;
	@Column(name = "CODE")
	private String code;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "DOC_DATE")
	private String docDate;
	@Column(name = "PK_CODE")
	private String pkCode;
	@Column(name = "CURR_AMT")
	private BigDecimal currAmt;
	@Column(name = "SOURCE_MONEY")
	private String sourceMoney;
	@Column(name = "KEY_REF_3")
	private String keyRef3;
	@Column(name = "DEP_CODE")
	private String depCode;
	@Column(name = "POSTING_DATE")
	private Date postingDate;
	@Column(name = "YEAR_MONTH")
	private String yearMonth;
	@Column(name = "TAX_AMT")
	private BigDecimal taxAmt;
	@Column(name = "TAX_EXRMPT_AMT")
	private BigDecimal taxExrmptAmt;
	@Column(name = "REF_CODE")
	private String refCode;
	@Column(name = "GL_ACC")
	private String glAcc;
	@Column(name = "FORWARD_CLEARING_LIST")
	private String forwardClearingList;
	@Column(name = "CLG_I")
	private BigDecimal clgI;
	@Column(name = "BUDGET_CODE")
	private String budgetCode;
	@Column(name = "KEY_REF_1")
	private String keyRef1;
	@Column(name = "KEY_REF_2")
	private String keyRef2;
	@Column(name = "DEPOSIT_ACC")
	private String depositAcc;
	@Column(name = "SUB_ACC")
	private String subAcc;
	@Column(name = "DEPOSIT_NAME")
	private String depositName;
	@Column(name = "ACC_OWN")
	private String accOwn;
	@Column(name = "DOC_HEADER_MSG")
	private String docHeaderMsg;
	@Column(name = "TX_CODE")
	private String txCode;
	@Column(name = "CLRNG_DOC")
	private String clrngDoc;

	public Long getIaGfledgerAccountSeq() {
		return iaGfledgerAccountSeq;
	}

	public void setIaGfledgerAccountSeq(Long iaGfledgerAccountSeq) {
		this.iaGfledgerAccountSeq = iaGfledgerAccountSeq;
	}

	public String getStCode() {
		return stCode;
	}

	public void setStCode(String stCode) {
		this.stCode = stCode;
	}

	public String getDeterminaton() {
		return determinaton;
	}

	public void setDeterminaton(String determinaton) {
		this.determinaton = determinaton;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	public String getPkCode() {
		return pkCode;
	}

	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}

	public BigDecimal getCurrAmt() {
		return currAmt;
	}

	public void setCurrAmt(BigDecimal currAmt) {
		this.currAmt = currAmt;
	}

	public String getSourceMoney() {
		return sourceMoney;
	}

	public void setSourceMoney(String sourceMoney) {
		this.sourceMoney = sourceMoney;
	}

	public String getKeyRef3() {
		return keyRef3;
	}

	public void setKeyRef3(String keyRef3) {
		this.keyRef3 = keyRef3;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getTaxExrmptAmt() {
		return taxExrmptAmt;
	}

	public void setTaxExrmptAmt(BigDecimal taxExrmptAmt) {
		this.taxExrmptAmt = taxExrmptAmt;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getGlAcc() {
		return glAcc;
	}

	public void setGlAcc(String glAcc) {
		this.glAcc = glAcc;
	}

	public String getForwardClearingList() {
		return forwardClearingList;
	}

	public void setForwardClearingList(String forwardClearingList) {
		this.forwardClearingList = forwardClearingList;
	}

	public BigDecimal getClgI() {
		return clgI;
	}

	public void setClgI(BigDecimal clgI) {
		this.clgI = clgI;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public String getKeyRef1() {
		return keyRef1;
	}

	public void setKeyRef1(String keyRef1) {
		this.keyRef1 = keyRef1;
	}

	public String getKeyRef2() {
		return keyRef2;
	}

	public void setKeyRef2(String keyRef2) {
		this.keyRef2 = keyRef2;
	}

	public String getDepositAcc() {
		return depositAcc;
	}

	public void setDepositAcc(String depositAcc) {
		this.depositAcc = depositAcc;
	}

	public String getSubAcc() {
		return subAcc;
	}

	public void setSubAcc(String subAcc) {
		this.subAcc = subAcc;
	}

	public String getDepositName() {
		return depositName;
	}

	public void setDepositName(String depositName) {
		this.depositName = depositName;
	}

	public String getAccOwn() {
		return accOwn;
	}

	public void setAccOwn(String accOwn) {
		this.accOwn = accOwn;
	}

	public String getDocHeaderMsg() {
		return docHeaderMsg;
	}

	public void setDocHeaderMsg(String docHeaderMsg) {
		this.docHeaderMsg = docHeaderMsg;
	}

	public String getTxCode() {
		return txCode;
	}

	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}

	public String getClrngDoc() {
		return clrngDoc;
	}

	public void setClrngDoc(String clrngDoc) {
		this.clrngDoc = clrngDoc;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
