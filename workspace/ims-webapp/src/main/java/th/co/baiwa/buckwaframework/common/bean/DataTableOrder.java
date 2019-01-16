package th.co.baiwa.buckwaframework.common.bean;

import java.io.Serializable;

public class DataTableOrder implements Serializable {

    private static final long serialVersionUID = 3911757104629654810L;
    private int column;
	private String dir;
	
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
}
