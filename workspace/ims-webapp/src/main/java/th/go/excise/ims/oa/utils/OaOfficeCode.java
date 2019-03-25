package th.go.excise.ims.oa.utils;

public class OaOfficeCode {
	
	public static String officeCodeLike(String offCode) {
		String strSeg = offCode.substring(0, 2);
		String strAra = offCode.substring(2, 4);
		String officeCon = "";
		if ("00".equals(strAra) && "00".equals(strSeg)) {
			// Main
			officeCon = "";
		} else {
			if ("00".equals(strAra) && !"00".equals(strSeg)) {
				// Sector
				officeCon = strSeg;
			} else if (!"00".equals(strAra) && !"00".equals(strSeg)) {
				// Area
				officeCon = strSeg + strAra;
			} else {
				// Self
				officeCon = strSeg;
			}
		}
		return officeCon;
	}
	
}
