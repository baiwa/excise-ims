package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_AMPHUR")
public class ExciseAmphur extends BaseEntity {

	private static final long serialVersionUID = 6767121432761797009L;

	public static class Field {
		public static final String AMPHUR_ID = "AMPHUR_ID";
		public static final String GEO_ID = "GEO_ID";
		public static final String PROVINCE_ID = "PROVINCE_ID";
		public static final String AMPHUR_CODE = "AMPHUR_CODE";
		public static final String AMPHUR_NAME = "AMPHUR_NAME";
	}

	@Id
	@Column(name = "AMPHUR_ID")
	private Long amphurId;
	@Column(name = "GEO_ID")
	private Long geoId;
	@Column(name = "PROVINCE_ID")
	private Long provinceId;
	@Column(name = "AMPHUR_CODE")
	private String amphurCode;
	@Column(name = "AMPHUR_NAME")
	private String amphurName;

	public Long getAmphurId() {
		return amphurId;
	}

	public void setAmphurId(Long amphurId) {
		this.amphurId = amphurId;
	}

	public Long getGeoId() {
		return geoId;
	}

	public void setGeoId(Long geoId) {
		this.geoId = geoId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getAmphurCode() {
		return amphurCode;
	}

	public void setAmphurCode(String amphurCode) {
		this.amphurCode = amphurCode;
	}

	public String getAmphurName() {
		return amphurName;
	}

	public void setAmphurName(String amphurName) {
		this.amphurName = amphurName;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
