package th.go.excise.ims.ia.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRentHouse;
import th.go.excise.ims.ia.persistence.entity.IaRentHouse1;
import th.go.excise.ims.ia.persistence.repository.IaRentHouse1Repository;
import th.go.excise.ims.ia.persistence.repository.IaRentHouseRepository;
import th.go.excise.ims.ia.vo.Int12070101D1Vo;
import th.go.excise.ims.ia.vo.Int12070101SaveFormVo;

@Service
public class Int12070101Service {

	@Autowired
	private IaRentHouseRepository iaRentHouseRepository;

	@Autowired
	private IaRentHouse1Repository iaRentHouse1Repository;

	@Transactional
	public IaRentHouse save(Int12070101SaveFormVo en) {
		IaRentHouse data = new IaRentHouse();
		data.setAffiliation(en.getAffiliation());
		data.setBillAmount(en.getBillAmount());
		data.setName(en.getName());
		data.setNotOver(en.getNotOver());
		data.setPaymentCost(en.getPaymentCost());
		data.setPaymentFor(en.getPaymentFor());
		data.setPeriod(en.getPeriod());
		data.setPeriodWithdraw(en.getPeriodWithdraw());
		data.setPosition(en.getPosition());
		data.setReceipts(en.getReceipts());
		data.setRefReceipts(en.getRefReceipts());
		data.setRequestNo(en.getRequestNo());
		data.setSalary(en.getSalary());
		data.setTotalMonth(en.getTotalMonth());
		data.setTotalWithdraw(en.getTotalWithdraw());
		data.setStatus(IaConstants.STATUS.PROCESS);
		data.setPeriodWithdrawTo(en.getPeriodWithdrawTo());
		IaRentHouse dataSave = iaRentHouseRepository.save(data);
		
		IaRentHouse1 detialSave = null;
		int seq = 0;
		for (Int12070101D1Vo detail : en.getReceiptsRH()) {
			detialSave = new IaRentHouse1();
			detialSave.setRentHouseId(dataSave.getRentHouseId());
			detialSave.setDocumentSeq( new BigDecimal(seq));
			detialSave.setDocReceiptNo(detail.getReceiptNo());
			detialSave.setDocReceiptDate(ConvertDateUtils.parseStringToDate(detail.getReceiptDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			detialSave.setDocReceiptAmount(new BigDecimal(detail.getReceiptAmount()));
			iaRentHouse1Repository.save(detialSave);
			seq++;
		}
		return dataSave;
	}
}
