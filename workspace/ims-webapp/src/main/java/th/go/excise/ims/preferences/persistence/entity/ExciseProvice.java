
package th.go.excise.ims.preferences.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_PROVICE")
public class ExciseProvice extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3875429400246075553L;
	
	public static class Field {

		public static final String PROVINCE_ID = "PROVINCE_ID";
		public static final String GEO_ID = "GEO_ID";
		public static final String PROVINCE_CODE = "PROVINCE_CODE";
		public static final String PROVINCE_NAME = "PROVINCE_NAME";
	}
	@Id
	@Column(name = "PROVINCE_ID")
	private BigDecimal provinceId;
	@Column(name = "GEO_ID")
	private BigDecimal geoId;
	@Column(name = "PROVINCE_CODE")
	private String provinceCode;
	@Column(name = "PROVINCE_NAME")
	private String provinceName;

	public BigDecimal getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(BigDecimal provinceId) {
		this.provinceId = provinceId;
	}

	public BigDecimal getGeoId() {
		return geoId;
	}

	public void setGeoId(BigDecimal geoId) {
		this.geoId = geoId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
