package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaConcludeFollowHdr;

public class Int1102Vo {

	private IaConcludeFollowHdr IaConcludeFollowHdr;
	private String issues;
	private String whatShouldBe;

	public IaConcludeFollowHdr getIaConcludeFollowHdr() {
		return IaConcludeFollowHdr;
	}

	public void setIaConcludeFollowHdr(IaConcludeFollowHdr iaConcludeFollowHdr) {
		IaConcludeFollowHdr = iaConcludeFollowHdr;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

	public String getWhatShouldBe() {
		return whatShouldBe;
	}

	public void setWhatShouldBe(String whatShouldBe) {
		this.whatShouldBe = whatShouldBe;
	}

}
