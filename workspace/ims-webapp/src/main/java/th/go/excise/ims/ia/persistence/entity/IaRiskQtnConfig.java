package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_RISK_QTN_CONFIG")
public class IaRiskQtnConfig extends BaseEntity {

	private static final long serialVersionUID = -1185997833610340235L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_QTN_CONFIG_GEN")
	@SequenceGenerator(name = "IA_RISK_QTN_CONFIG_GEN", sequenceName = "IA_RISK_QTN_CONFIG_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ID_QTN_HDR")
	private BigDecimal idQtnHdr;
	@Column(name = "LOW")
	private String low;
	@Column(name = "LOW_START")
	private String lowStart;
	@Column(name = "LOW_END")
	private String lowEnd;
	@Column(name = "LOW_RATING")
	private BigDecimal lowRating;
	@Column(name = "LOW_COLOR")
	private String lowColor;
	@Column(name = "LOW_CONDITION")
	private String lowCondition;
	@Column(name = "MEDIUM")
	private String medium;
	@Column(name = "MEDIUM_START")
	private String mediumStart;
	@Column(name = "MEDIUM_END")
	private String mediumEnd;
	@Column(name = "MEDIUM_RATING")
	private BigDecimal mediumRating;
	@Column(name = "MEDIUM_COLOR")
	private String mediumColor;
	@Column(name = "MEDIUM_CONDITION")
	private String mediumCondition;
	@Column(name = "HIGH")
	private String high;
	@Column(name = "HIGH_START")
	private String highStart;
	@Column(name = "HIGH_END")
	private String highEnd;
	@Column(name = "HIGH_RATING")
	private BigDecimal highRating;
	@Column(name = "HIGH_COLOR")
	private String highColor;
	@Column(name = "HIGH_CONDITION")
	private String highCondition;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdQtnHdr() {
		return idQtnHdr;
	}

	public void setIdQtnHdr(BigDecimal idQtnHdr) {
		this.idQtnHdr = idQtnHdr;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getLowStart() {
		return lowStart;
	}

	public void setLowStart(String lowStart) {
		this.lowStart = lowStart;
	}

	public String getLowEnd() {
		return lowEnd;
	}

	public void setLowEnd(String lowEnd) {
		this.lowEnd = lowEnd;
	}

	public BigDecimal getLowRating() {
		return lowRating;
	}

	public void setLowRating(BigDecimal lowRating) {
		this.lowRating = lowRating;
	}

	public String getLowColor() {
		return lowColor;
	}

	public void setLowColor(String lowColor) {
		this.lowColor = lowColor;
	}

	public String getLowCondition() {
		return lowCondition;
	}

	public void setLowCondition(String lowCondition) {
		this.lowCondition = lowCondition;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getMediumStart() {
		return mediumStart;
	}

	public void setMediumStart(String mediumStart) {
		this.mediumStart = mediumStart;
	}

	public String getMediumEnd() {
		return mediumEnd;
	}

	public void setMediumEnd(String mediumEnd) {
		this.mediumEnd = mediumEnd;
	}

	public BigDecimal getMediumRating() {
		return mediumRating;
	}

	public void setMediumRating(BigDecimal mediumRating) {
		this.mediumRating = mediumRating;
	}

	public String getMediumColor() {
		return mediumColor;
	}

	public void setMediumColor(String mediumColor) {
		this.mediumColor = mediumColor;
	}

	public String getMediumCondition() {
		return mediumCondition;
	}

	public void setMediumCondition(String mediumCondition) {
		this.mediumCondition = mediumCondition;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getHighStart() {
		return highStart;
	}

	public void setHighStart(String highStart) {
		this.highStart = highStart;
	}

	public String getHighEnd() {
		return highEnd;
	}

	public void setHighEnd(String highEnd) {
		this.highEnd = highEnd;
	}

	public BigDecimal getHighRating() {
		return highRating;
	}

	public void setHighRating(BigDecimal highRating) {
		this.highRating = highRating;
	}

	public String getHighColor() {
		return highColor;
	}

	public void setHighColor(String highColor) {
		this.highColor = highColor;
	}

	public String getHighCondition() {
		return highCondition;
	}

	public void setHighCondition(String highCondition) {
		this.highCondition = highCondition;
	}
}
