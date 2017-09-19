package th.co.baiwa.buckwaframework.security.persistence.entity;

import java.util.Date;

public class UserAttempt {

	private Long userAttemptId;
	private String username;
	private int attempts;
	private Date lastModified;

	public Long getUserAttemptId() {
		return userAttemptId;
	}

	public void setUserAttemptId(Long userAttemptId) {
		this.userAttemptId = userAttemptId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
