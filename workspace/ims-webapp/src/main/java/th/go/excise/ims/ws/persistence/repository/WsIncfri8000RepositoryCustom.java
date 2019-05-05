package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.scheduler.vo.WsIncfri8000MVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;

public interface WsIncfri8000RepositoryCustom {
	
	public void forceDeleteByDateType(String dateType, String dateStart, String dateEnd);
	
	public void batchInsert(List<WsIncfri8000> incfri8000List);
	
	public List<WsIncfri8000MVo> findByDateType(String dateType, String dateStart, String dateEnd);
	
}
