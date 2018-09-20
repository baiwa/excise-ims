package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_PROCUREMENT_UL_FILE database table.
 * 
 */
@Entity
@Table(name="IA_PROCUREMENT_UL_FILE")
public class IaProcurementUlFile extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3792535422672479873L;

	@Id
	@SequenceGenerator(name="IA_PROCUREMENT_UL_FILE_GEN", sequenceName="IA_PROCUREMENT_UL_FILE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_PROCUREMENT_UL_FILE_GEN")
	@Column(name="ID_FILE")
	private long idFile;

	@Column(name="NAME_FILE")
	private String nameFile;

	@Column(name="PROCUREMENT_ID")
	private long procurementId;

	@Column(name="SIZE_FILE")
	private Long sizeFile;

	public IaProcurementUlFile() {
	}

	public long getIdFile() {
		return idFile;
	}

	public void setIdFile(long idFile) {
		this.idFile = idFile;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public long getProcurementId() {
		return procurementId;
	}

	public void setProcurementId(long procurementId) {
		this.procurementId = procurementId;
	}

	public Long getSizeFile() {
		return sizeFile;
	}

	public void setSizeFile(Long sizeFile) {
		this.sizeFile = sizeFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}