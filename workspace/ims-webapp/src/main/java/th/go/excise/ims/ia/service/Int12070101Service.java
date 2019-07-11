package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
			detialSave.setDocumentSeq(new BigDecimal(seq));
			detialSave.setDocReceiptNo(detail.getReceiptNo());
			detialSave.setDocReceiptDate(ConvertDateUtils.parseStringToDate(detail.getReceiptDate(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			detialSave.setDocReceiptAmount(new BigDecimal(detail.getReceiptAmount()));
			iaRentHouse1Repository.save(detialSave);
			seq++;
		}
		return dataSave;
	}

	public Int12070101SaveFormVo findById(long id) {
		IaRentHouse headerData = new IaRentHouse();
		headerData = iaRentHouseRepository.findById(id).get();
		List<IaRentHouse1> detailData = iaRentHouse1Repository.findByRentHouseId(id);
		
		Int12070101SaveFormVo dataRes = new Int12070101SaveFormVo();
		dataRes.setAffiliation(headerData.getAffiliation());
		dataRes.setBillAmount(headerData.getBillAmount());
		dataRes.setName(headerData.getName());
		dataRes.setNotOver(headerData.getNotOver());
		dataRes.setPaymentCost(headerData.getPaymentCost());
		dataRes.setPaymentFor(headerData.getPaymentFor());
		dataRes.setPeriod(headerData.getPeriod());
		dataRes.setPeriodWithdraw(headerData.getPeriodWithdraw());
		dataRes.setPosition(headerData.getPosition());
		dataRes.setReceipts(headerData.getReceipts());
		dataRes.setRefReceipts(headerData.getRefReceipts());
		dataRes.setRequestNo(headerData.getRequestNo());
		dataRes.setSalary(headerData.getSalary());
		dataRes.setTotalMonth(headerData.getTotalMonth());
		dataRes.setTotalWithdraw(headerData.getTotalWithdraw());
		dataRes.setStatus(headerData.getStatus());
		dataRes.setPeriodWithdrawTo(headerData.getPeriodWithdrawTo());
		
		List<Int12070101D1Vo> detailsSet = new ArrayList<Int12070101D1Vo>();
		Int12070101D1Vo detailSet = null;
		for (IaRentHouse1 detail : detailData) {
			detailSet = new Int12070101D1Vo();
			detailSet.setRentHouse1Id(detail.getRentHouseId());
			detailSet.setDocumentSeq(detail.getDocumentSeq().toString());
			detailSet.setReceiptNo(detail.getDocReceiptNo());
			detailSet.setReceiptDate(ConvertDateUtils.formatDateToString(detail.getDocReceiptDate(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
			detailSet.setReceiptAmount(detail.getDocReceiptAmount().toString());
			detailsSet.add(detailSet);
		}
		dataRes.setReceiptsRH(detailsSet);
		return dataRes;
	}
}