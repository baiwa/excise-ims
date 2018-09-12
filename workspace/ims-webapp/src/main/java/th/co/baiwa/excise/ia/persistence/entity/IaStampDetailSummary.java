package th.co.baiwa.excise.ia.persistence.entity;

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
@Table(name = "IA_STAMP_DETAIL_SUMMAEY")
public class IaStampDetailSummary extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3513900916791123095L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_DETAIL_SUMMAEY_GEN")
	@SequenceGenerator(name = "IA_STAMP_DETAIL_SUMMAEY_GEN", sequenceName = "IA_STAMP_DETAIL_SUMMAEY_SEQ", allocationSize = 1)

	@Column(name = "ID")
	private Long id;

	@Column(name = "NUMBER_OF_STAMP")
	private Integer numberOfStamp;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "SUM_OF_VALUE")
	private BigDecimal sumOfValue;

	@Column(name = "STAMP_GENRE_ID")
	private Long stamGenreId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumberOfStamp() {
		return numberOfStamp;
	}

	public void setNumberOfStamp(Integer numberOfStamp) {
		this.numberOfStamp = numberOfStamp;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal getSumOfValue() {
		return sumOfValue;
	}

	public void setSumOfValue(BigDecimal sumOfValue) {
		this.sumOfValue = sumOfValue;
	}

	public Long getStamGenreId() {
		return stamGenreId;
	}

	public void setStamGenreId(Long stamGenreId) {
		this.stamGenreId = stamGenreId;
	}

}
