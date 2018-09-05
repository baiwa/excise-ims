package th.co.baiwa.excise.ia.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name="IA_FOLLOW_UP_PROJECT")
public class IaFollowUpProject extends BaseEntity {

	private static final long serialVersionUID = -6083591812384737632L;
	
	@Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_FOLLOW_UP_PROJECT_GEN" )
	@SequenceGenerator(name = "IA_FOLLOW_UP_PROJECT_GEN", sequenceName = "IA_FOLLOW_UP_PROJECT_SEQ", allocationSize = 1)
	@Column(name="FOLLOW_UP_PROJECT_ID")
	private Long followUpProjectId;
	
	@Column(name="PROJECT_NAME")
	private String projectName;
	
	@Column(name="INFORM_RECTOR_BNUM")
	private String informRectorBnum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INFORM_RECTOR_DATE")
	private Date informRectorDate;
	
	@Column(name="FOLLOW_UP1_BNUM")
	private String followUp1Bnum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FOLLOW_UP1_DATE")
	private Date followUp1Date;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MATURITY1_45")
	private Date maturity145;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MATURITY1_60")
	private Date maturity160;
	
	@Column(name="PERFORMANCE1_BNUM")
	private String performance1Bnum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PERFORMANCE1_DATE")
	private Date performance1Date;
	
	@Column(name="TRACK_RESULT1_BNUM")
	private String trackResult1Bnum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRACK_RESULT1_DATE")
	private Date trackResult1Date;
	
	@Column(name="FOLLOW_UP2_BNUM")
	private String followUp2Bnum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FOLLOW_UP2_DATE")
	private Date followUp2Date;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MATURITY2_60")
	private Date maturity260;
	
	@Column(name="PERFORMANCE2_BNUM")
	private String performance2Bnum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PERFORMANCE2_DATE")
	private Date performance2Date;
	
	@Column(name="TRACK_RESULT2_BNUM")
	private String trackResult2Bnum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRACK_RESULT2_DATE")
	private Date trackResult2Date;
	
	@Column(name="STATUS")
	private String status;

	public Long getFollowUpProjectId() {
		return followUpProjectId;
	}

	public void setFollowUpProjectId(Long followUpProjectId) {
		this.followUpProjectId = followUpProjectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getInformRectorBnum() {
		return informRectorBnum;
	}

	public void setInformRectorBnum(String informRectorBnum) {
		this.informRectorBnum = informRectorBnum;
	}

	public Date getInformRectorDate() {
		return informRectorDate;
	}

	public void setInformRectorDate(Date informRectorDate) {
		this.informRectorDate = informRectorDate;
	}

	public String getFollowUp1Bnum() {
		return followUp1Bnum;
	}

	public void setFollowUp1Bnum(String followUp1Bnum) {
		this.followUp1Bnum = followUp1Bnum;
	}

	public Date getFollowUp1Date() {
		return followUp1Date;
	}

	public void setFollowUp1Date(Date followUp1Date) {
		this.followUp1Date = followUp1Date;
	}

	public Date getMaturity145() {
		return maturity145;
	}

	public void setMaturity145(Date maturity145) {
		this.maturity145 = maturity145;
	}

	public Date getMaturity160() {
		return maturity160;
	}

	public void setMaturity160(Date maturity160) {
		this.maturity160 = maturity160;
	}

	public String getPerformance1Bnum() {
		return performance1Bnum;
	}

	public void setPerformance1Bnum(String performance1Bnum) {
		this.performance1Bnum = performance1Bnum;
	}

	public Date getPerformance1Date() {
		return performance1Date;
	}

	public void setPerformance1Date(Date performance1Date) {
		this.performance1Date = performance1Date;
	}

	public String getTrackResult1Bnum() {
		return trackResult1Bnum;
	}

	public void setTrackResult1Bnum(String trackResult1Bnum) {
		this.trackResult1Bnum = trackResult1Bnum;
	}

	public Date getTrackResult1Date() {
		return trackResult1Date;
	}

	public void setTrackResult1Date(Date trackResult1Date) {
		this.trackResult1Date = trackResult1Date;
	}

	public String getFollowUp2Bnum() {
		return followUp2Bnum;
	}

	public void setFollowUp2Bnum(String followUp2Bnum) {
		this.followUp2Bnum = followUp2Bnum;
	}

	public Date getFollowUp2Date() {
		return followUp2Date;
	}

	public void setFollowUp2Date(Date followUp2Date) {
		this.followUp2Date = followUp2Date;
	}

	public Date getMaturity260() {
		return maturity260;
	}

	public void setMaturity260(Date maturity260) {
		this.maturity260 = maturity260;
	}

	public String getPerformance2Bnum() {
		return performance2Bnum;
	}

	public void setPerformance2Bnum(String performance2Bnum) {
		this.performance2Bnum = performance2Bnum;
	}

	public Date getPerformance2Date() {
		return performance2Date;
	}

	public void setPerformance2Date(Date performance2Date) {
		this.performance2Date = performance2Date;
	}

	public String getTrackResult2Bnum() {
		return trackResult2Bnum;
	}

	public void setTrackResult2Bnum(String trackResult2Bnum) {
		this.trackResult2Bnum = trackResult2Bnum;
	}

	public Date getTrackResult2Date() {
		return trackResult2Date;
	}

	public void setTrackResult2Date(Date trackResult2Date) {
		this.trackResult2Date = trackResult2Date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
