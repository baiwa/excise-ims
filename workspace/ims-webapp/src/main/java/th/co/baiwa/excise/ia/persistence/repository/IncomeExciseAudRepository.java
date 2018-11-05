package th.co.baiwa.excise.ia.persistence.repository;

import java.util.Date;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAud;

public interface IncomeExciseAudRepository extends CommonJpaCrudRepository<IncomeExciseAud,Long> {
	
	public IncomeExciseAud findByStartMonthAndEndMonth(String startMonth , String endMonth);
	public IncomeExciseAud findByCreatedDate(Date createDate);
	
}