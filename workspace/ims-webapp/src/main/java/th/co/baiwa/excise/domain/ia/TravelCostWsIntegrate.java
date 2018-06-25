package th.co.baiwa.excise.domain.ia;

public class TravelCostWsIntegrate extends TravelCostWorkSheetHeader {
	private TravelCostWsDetail[] Detail;

	public TravelCostWsDetail[] getDetail() {
		return Detail;
	}

	public void setDetail(TravelCostWsDetail[] detail) {
		Detail = detail;
	}
}
