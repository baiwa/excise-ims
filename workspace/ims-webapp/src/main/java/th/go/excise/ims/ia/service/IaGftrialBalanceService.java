package th.go.excise.ims.ia.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;

@Service
public class IaGftrialBalanceService {

	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepository;
	
	private final String KEY_FILTER  = "User name     :";

//	public void addDataByExcel(File file) {
//		try {
//			List<List<String>> ex = ExcelUtils.readExcel(file);
//			for (List<String> list : ex) {
//				for (int i = 0; i < list.size(); i++) {
//					System.out.print(i + " : " + list.get(i) + "\t");
//				}
//				System.out.println();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void addDataByExcel(File file) {
		try {
			String departmentCode = "";
			String periodFrom = "";
			String periodTo = "";
			String periodYear = "";
			List<IaGftrialBalance> iaRepDisbPerMonthList = new ArrayList<>();
			IaGftrialBalance iaRepDisbPerMonth = new IaGftrialBalance();
			List<List<String>> allLine = ExcelUtils.readExcel(file);
			for (List<String> line : allLine) {
				if (line != null && line.size() == 5 && line.get(0) != null && KEY_FILTER.equals(line.get(0).trim())) {
					departmentCode = line.get(2).trim().split(" ")[1];
				}else if (line != null && line.size() == 1 && line.get(0).trim().split(" ").length == 6 ) {
					String[] periodData = line.get(0).trim().split(" ");
					periodFrom = periodData[1];
					periodTo = periodData[3];
					periodYear = periodData[5];
				}else if(line != null && line.size() == 6) {
					iaRepDisbPerMonth = new IaGftrialBalance();
					iaRepDisbPerMonth.setDepartmentCode(departmentCode);
					iaRepDisbPerMonth.setPeriodFrom(periodFrom);
					iaRepDisbPerMonth.setPeriodTo(periodTo);
					iaRepDisbPerMonth.setPeriodYear(periodYear);
					iaRepDisbPerMonth.setAccNo(line.get(0));
					iaRepDisbPerMonth.setAccName(line.get(1));
					iaRepDisbPerMonth.setBringForward(NumberUtils.toBigDecimal(line.get(2)));
					iaRepDisbPerMonth.setDebit(NumberUtils.toBigDecimal(line.get(3)));
					iaRepDisbPerMonth.setCredit(NumberUtils.toBigDecimal(line.get(4)));
					iaRepDisbPerMonth.setCarryForward(NumberUtils.toBigDecimal(line.get(5)));
					iaRepDisbPerMonthList.add(iaRepDisbPerMonth);
				}
			}
			iaGftrialBalanceRepository.batchInsert(iaRepDisbPerMonthList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
