
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaMedicalReceipt;

public interface IaMedicalReceiptRepository
    extends CommonJpaCrudRepository<IaMedicalReceipt, Long>
{
	@Query(value = "SELECT E.* FROM IA_MEDICAL_RECEIPT E WHERE E.ID = ?1 AND E.IS_DELETED = 'N' ", nativeQuery = true)
	public List<IaMedicalReceipt> findByIdKey(String id);
	
}
