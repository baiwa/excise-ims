package th.go.excise.ims.mockup.domain.ia;

public class TravelCostWsIntegrate extends TravelCostWorkSheetHeader {
	private TravelCostWsDetail[] Detail;

	public TravelCostWsDetail[] getDetail() {
		return Detail;
	}

	public void setDetail(TravelCostWsDetail[] detail) {
		Detail = detail;
	}
}
