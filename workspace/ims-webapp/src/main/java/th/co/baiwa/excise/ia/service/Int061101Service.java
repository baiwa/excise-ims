package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.repository.RentHouseRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int061101FormVo;

@Service
public class Int061101Service {
	
	@Autowired
	private RentHouseRepository rentHouseRepository;

	public void save(Int061101FormVo formVo) {
		RentHouse data = new RentHouse();
		data.setAffiliation(formVo.getAffiliation());
		data.setBillAmount(formVo.getBillAmount());
		data.setName(formVo.getName());
		data.setNotOver(formVo.getNotOver());
		data.setPaymentCost(formVo.getPaymentCost());
		data.setPaymentFor(formVo.getPaymentFor());
		data.setPeriod(formVo.getPeriod());
		data.setPeriodWithdraw(formVo.getPeriodWithdraw());
		data.setPosition(formVo.getPosition());
		data.setReceipts(formVo.getReceipts());
		data.setRefReceipts(formVo.getRefReceipts());
		data.setRequestNo(formVo.getRequestNo());
		data.setSalary(formVo.getSalary());
		data.setTotalMonth(formVo.getTotalMonth());
		data.setTotalWithdraw(formVo.getTotalWithdraw());
		
		rentHouseRepository.save(data);
	}

}
