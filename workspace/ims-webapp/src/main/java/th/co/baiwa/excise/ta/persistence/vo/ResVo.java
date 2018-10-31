package th.co.baiwa.excise.ta.persistence.vo;

import java.util.List;

public class ResVo {

	private int sendAll;
	private int sector;
	private int central;
	
	private List<SectorMapValue> detailData;
	
	public int getSendAll() {
		return sendAll;
	}
	public void setSendAll(int sendAll) {
		this.sendAll = sendAll;
	}
	public int getSector() {
		return sector;
	}
	public void setSector(int sector) {
		this.sector = sector;
	}
	public int getCentral() {
		return central;
	}
	public void setCentral(int central) {
		this.central = central;
	}
	
	public List<SectorMapValue> getDetailData() {
		return detailData;
	}
	public void setDetailData(List<SectorMapValue> detailData) {
		this.detailData = detailData;
	}
	@Override
	public String toString() {
		return "ResVo [sendAll=" + sendAll + ", sector=" + sector + ", central=" + central + ", detailData=" + detailData + "]";
	}
	
	
	
	
}
