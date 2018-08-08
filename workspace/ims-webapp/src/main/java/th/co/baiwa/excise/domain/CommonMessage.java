package th.co.baiwa.excise.domain;

import java.util.List;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

public class CommonMessage<T> {
	T data;
	Message msg;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Message getMsg() {
		return msg;
	}
	public void setMsg(Message msg) {
		this.msg = msg;
	}
}
