
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
@Table(name = "EXCISE_GEO")
public class ExciseGeo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7704555372708574252L;
	public static class Field {

		public static final String GEO_ID = "GEO_ID";
		public static final String GEO_NAME = "GEO_NAME";
	}
	
	@Id
	@Column(name = "GEO_ID")
	private BigDecimal geoId;
	@Column(name = "GEO_NAME")
	private String geoName;

	public BigDecimal getGeoId() {
		return geoId;
	}

	public void setGeoId(BigDecimal geoId) {
		this.geoId = geoId;
	}

	public String getGeoName() {
		return geoName;
	}

	public void setGeoName(String geoName) {
		this.geoName = geoName;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
