package th.co.baiwa.buckwaframework.common.bean;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -7169648802684929126L;
	
	private String errorCode;
	private String errorDesc;
	
	public BusinessException(String errorCode, String errorDesc) {
		super(errorCode + errorDesc);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
}
