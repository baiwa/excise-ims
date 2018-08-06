package th.co.baiwa.excise.ia.persistence.entity;

import java.util.List;

public class TravelCostWsIntegrate extends TravelCostWorkSheetHeader {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7313374520901078311L;
	private List<TravelCostWsDetail> Detail;

	public List<TravelCostWsDetail> getDetail() {
		return Detail;
	}

	public void setDetail(List<TravelCostWsDetail> detail) {
		Detail = detail;
	}
}
