package th.co.baiwa.ims.taxaudit.dao.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import th.co.baiwa.ims.taxaudit.dao.jpa.entity.TaxAuditEntity;

public interface TaxAuditRepository extends CrudRepository<TaxAuditEntity, Long> {

}
