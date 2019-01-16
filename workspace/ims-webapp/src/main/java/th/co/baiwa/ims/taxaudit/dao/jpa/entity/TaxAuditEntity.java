package th.co.baiwa.ims.taxaudit.dao.jpa.entity;

import javax.persistence.*;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "table_tax")
public class TaxAuditEntity extends BaseEntity {

	private static final long serialVersionUID = 3568823112650647838L;

	@Id
	@Column(name = "tax_id")
	@SequenceGenerator(name = "table_tax_generator", sequenceName = "table_tax_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_tax_generator")
	private Long taxId;

	@Column(name = "tax_name")
	private String taxName;

	public Long getTaxId() {
		return taxId;
	}

	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
}
