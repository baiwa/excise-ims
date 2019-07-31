package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;

public interface WsIncfri8000RepositoryCustom {
	
	public void forceDeleteByDateType(String dateType, String dateStart, String dateEnd);
	
	public void batchInsert(List<WsIncfri8000> incfri8000List);
	
	public List<TaWsInc8000M> findFor8000M(String dateType, String dateStart, String dateEnd);
	
}
