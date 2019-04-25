
package th.go.excise.ims.ws.client.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.Int0601Vo;
import th.go.excise.ims.ws.client.persistence.entity.WsIncfri8020Inc;

public interface WsIncfri8020IncRepositoryCustom {
	public void batchInsert( List<WsIncfri8020Inc> wsIncfri8020IncList );
	public List<WsIncfri8020Inc> findByCriteria(Int0601Vo wsIncfri8020Inc);
}
