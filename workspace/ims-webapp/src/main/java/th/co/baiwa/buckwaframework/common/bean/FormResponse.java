package th.co.baiwa.buckwaframework.common.bean;

public class FormResponse<T> {
	private int status = 1; // error = 1, success = 0
	private String message;
	private T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
