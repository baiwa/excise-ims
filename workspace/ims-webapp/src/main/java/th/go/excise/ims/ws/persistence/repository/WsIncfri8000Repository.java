package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.scheduler.vo.WsIncfri8000MVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;

public interface WsIncfri8000Repository extends CommonJpaCrudRepository<WsIncfri8000, Long>, WsIncfri8000RepositoryCustom {
	
}
