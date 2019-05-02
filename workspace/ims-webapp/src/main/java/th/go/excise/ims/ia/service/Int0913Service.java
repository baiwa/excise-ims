package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaUtilityBill;
import th.go.excise.ims.ia.persistence.repository.IaUtilityBillRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0913JdbcRepository;
import th.go.excise.ims.ia.vo.Int091301ResultSearchVo;
import th.go.excise.ims.ia.vo.Int091301SearchVo;

@Service
public class Int0913Service {
	
	@Autowired
	private Int0913JdbcRepository int0913JdbcRepository;
	
	@Autowired
	private IaUtilityBillRepository iaUtilityBillRepository;
	
	public List<Int091301ResultSearchVo> findIaUtilityBill(Int091301SearchVo int091301SearchVo){
		
		return int0913JdbcRepository.findIaUtilityBillByCriteria(int091301SearchVo);
	}
	
	public IaUtilityBill saveIaUtilityBill(IaUtilityBill iaUtilityBill) {
		return iaUtilityBillRepository.save(iaUtilityBill);
	}
	
	public void deleteIaUtilityBillById(Long id) {
		 iaUtilityBillRepository.deleteById(id);
	}

}
