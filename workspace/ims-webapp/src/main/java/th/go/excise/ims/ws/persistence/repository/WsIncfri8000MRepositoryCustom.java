package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsIncfri8000M;

public interface WsIncfri8000MRepositoryCustom {
	
	public void forceDeleteByDateType(String dateType, String taxYear, String taxMonth);
	
	public void batchInsert(List<WsIncfri8000M> incfri8000MList);
	
}
