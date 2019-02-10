package th.co.baiwa.buckwaframework.accesscontrol.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "ADM_EXCISE_AUTHEN")
public class ExciseAuthen extends BaseEntity{

	
	private static final long serialVersionUID = 613907346579518080L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_EXCISE_AUTHEN_GEN")
	@SequenceGenerator(name = "ADM_EXCISE_AUTHEN_GEN", sequenceName = "ADM_EXCISE_AUTHEN_SEQ", allocationSize = 1)
	@Column(name = "AUTHEN_ID")
	private BigDecimal authenId;
	
	@Column(name = "GROUP_AUTHEN_NAME")
	private String groupAuthenName;
	
	@Column(name = "ROLE_ID")
	private BigDecimal roleId;
	
	@Column(name = "PAGE")
	private String page;
	
	@Column(name = "URL_PAGE")
	private String urlPage;

	public BigDecimal getAuthenId() {
		return authenId;
	}

	public void setAuthenId(BigDecimal authenId) {
		this.authenId = authenId;
	}

	public String getGroupAuthenName() {
		return groupAuthenName;
	}

	public void setGroupAuthenName(String groupAuthenName) {
		this.groupAuthenName = groupAuthenName;
	}

	public BigDecimal getRoleId() {
		return roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getUrlPage() {
		return urlPage;
	}

	public void setUrlPage(String urlPage) {
		this.urlPage = urlPage;
	}
}
