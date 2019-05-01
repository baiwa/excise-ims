
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

	private static final long serialVersionUID = -7273038421473858670L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_GFLEDGER_ACCOUNT_GEN")
	@SequenceGenerator(name = "IA_GFLEDGER_ACCOUNT_GEN", sequenceName = "IA_GFLEDGER_ACCOUNT_SEQ", allocationSize = 1)
	@Column(name = "IA_GFLEDGER_ACCOUNT_SEQ")
	private Long iaGfledgerAccountSeq;
	@Column(name = "DEPARTMENT_CODE")
	private String departmentCode;
	@Column(name = "GL_ACCOUNT_NO")
	private String glAccountNo;
	@Column(name = "GL_ACCOUNT_NAME")
	private String glAccountName;
	@Column(name = "ST_CODE")
	private String stCode;
	@Column(name = "DISB_DEP_CODE")
	private String disbDepCode;
	@Column(name = "DOC_NO")
	private String docNo;
	@Column(name = "GL_AREA_CODE")
	private String glAreaCode;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "PK_CODE")
	private String pkCode;
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	@Column(name = "LOCAL_CURR")
	private String localCurr;
	@Column(name = "TX_CODE")
	private String txCode;
	@Column(name = "CLRNG_DOC")
	private String clrngDoc;
	@Column(name = "REMARK_GFLEDGER_ACCOUNT")
	private String remarkGfledgerAccount;
	@Column(name = "IS_DELETED")
	private String isDeleted;
	@Column(name = "VERSION")
	private BigDecimal version;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public Long getIaGfledgerAccountSeq() {
		return iaGfledgerAccountSeq;
	}

	public void setIaGfledgerAccountSeq(Long iaGfledgerAccountSeq) {
		this.iaGfledgerAccountSeq = iaGfledgerAccountSeq;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getGlAccountNo() {
		return glAccountNo;
	}

	public void setGlAccountNo(String glAccountNo) {
		this.glAccountNo = glAccountNo;
	}

	public String getGlAccountName() {
		return glAccountName;
	}

	public void setGlAccountName(String glAccountName) {
		this.glAccountName = glAccountName;
	}

	public String getStCode() {
		return stCode;
	}

	public void setStCode(String stCode) {
		this.stCode = stCode;
	}

	public String getDisbDepCode() {
		return disbDepCode;
	}

	public void setDisbDepCode(String disbDepCode) {
		this.disbDepCode = disbDepCode;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getGlAreaCode() {
		return glAreaCode;
	}

	public void setGlAreaCode(String glAreaCode) {
		this.glAreaCode = glAreaCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getPkCode() {
		return pkCode;
	}

	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getLocalCurr() {
		return localCurr;
	}

	public void setLocalCurr(String localCurr) {
		this.localCurr = localCurr;
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

	public String getRemarkGfledgerAccount() {
		return remarkGfledgerAccount;
	}

	public void setRemarkGfledgerAccount(String remarkGfledgerAccount) {
		this.remarkGfledgerAccount = remarkGfledgerAccount;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
