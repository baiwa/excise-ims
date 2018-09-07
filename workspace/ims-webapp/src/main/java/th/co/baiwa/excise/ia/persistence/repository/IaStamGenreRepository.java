package th.co.baiwa.excise.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IaStamGenre;

import java.util.List;

public interface IaStamGenreRepository extends CommonJpaCrudRepository<IaStamGenre,Long> {

    List<IaStamGenre> findByStampTypeId(String stamTypeId);
}
