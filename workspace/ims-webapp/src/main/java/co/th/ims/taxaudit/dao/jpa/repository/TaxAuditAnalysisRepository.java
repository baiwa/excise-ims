package co.th.ims.taxaudit.dao.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import co.th.ims.taxaudit.dao.jpa.entity.TaxAuditAnalysisEntity;

public interface TaxAuditAnalysisRepository extends CrudRepository<TaxAuditAnalysisEntity, Long> {

}
