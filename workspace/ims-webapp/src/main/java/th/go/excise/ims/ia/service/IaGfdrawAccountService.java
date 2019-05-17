package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfdrawAccount;
import th.go.excise.ims.ia.persistence.repository.IaGfdrawAccountRepository;

@Service
public class IaGfdrawAccountService {

	@Autowired
	private IaGfdrawAccountRepository iaGfdrawAccountRepository;

	private final String KEY_FILTER[] = { "รหัสหน่วยเบิกจ่าย", "วันที่บันทึก", "วันที่รายงาน", "กรณีจ่ายตรงผู้ขาย", "กรณีจ่ายผ่านส่วนราชการ", "ผลรวม" };

	private Logger logger = LoggerFactory.getLogger(IaGfdrawAccountService.class);
	
//	public void addDataByExcel(File file) {
//		try {
//			List<List<String>> ex = ExcelUtils.readExcel(file);
//			for (List<String> list : ex) {
//				for (int i = 0; i < list.size(); i++) {
//					System.out.print(i + " : " + list.get(i) + "||");
//				}
//				System.out.println();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void addDataByExcel(MultipartFile file) {
		try {
			String departmentCode = "";
			String periodFrom = "";
			String periodTo = "";
			String repDate = "";
			String repType = "";
			List<IaGfdrawAccount> iaGfDrawAccountList = new ArrayList<>();
			IaGfdrawAccount iaGfDrawAccount = new IaGfdrawAccount();
			List<List<String>> allLine = ExcelUtils.readExcel(file);
			for (List<String> line : allLine) {
				if (line != null && line.size() == 2 && line.get(0) != null && KEY_FILTER[0].equals(line.get(0).trim())) {
					departmentCode = line.get(1);
				} else if (line != null && line.size() == 2 && line.get(0) != null && KEY_FILTER[1].equals(line.get(0).trim())) {
					String[] periodData = line.get(1).trim().split(" ถึง ");
					periodFrom = periodData[0];
					periodTo = periodData[1];
				} else if (line != null && line.size() == 2 && line.get(0) != null && KEY_FILTER[2].equals(line.get(0).trim())) {
					repDate = line.get(1).trim();
				} else if (line != null && line.size() == 1 && line.get(0) != null && KEY_FILTER[3].equals(line.get(0).trim())) {
					repType = "1";
				} else if (line != null && line.size() == 1 && line.get(0) != null && KEY_FILTER[4].equals(line.get(0).trim())) {
					repType = "2";
				} else if (line != null && line.size() == 2 && line.get(0) != null && KEY_FILTER[5].equals(line.get(0).trim())) {
					departmentCode = "";
					periodFrom = "";
					periodTo = "";
					repDate = "";
					repType = "";
				} else if (line != null && line.size() == 13) {
					try {
						iaGfDrawAccount = new IaGfdrawAccount();
						iaGfDrawAccount.setDepartmentCode(departmentCode);
						iaGfDrawAccount.setPeriodFrom(ConvertDateUtils.parseStringToDate(periodFrom, ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setPeriodTo(ConvertDateUtils.parseStringToDate(periodTo, ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setRepDate(ConvertDateUtils.parseStringToDate(repDate, ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setRepType(repType);
						iaGfDrawAccount.setRecordDate(ConvertDateUtils.parseStringToDate(line.get(0), ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setRecodeApproveDate(ConvertDateUtils.parseStringToDate(line.get(1), ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setType(line.get(2));
						iaGfDrawAccount.setDocNo(line.get(3));
						iaGfDrawAccount.setSellerName(line.get(4));
						iaGfDrawAccount.setSellerBookBank(line.get(5));
						iaGfDrawAccount.setReferenceCode(line.get(6));
						iaGfDrawAccount.setBudgetCode(line.get(7));
						iaGfDrawAccount.setDisbAmt(NumberUtils.toBigDecimal(line.get(8)));
						iaGfDrawAccount.setTaxAmt(NumberUtils.toBigDecimal(line.get(9)));
						iaGfDrawAccount.setMulctAmt(NumberUtils.toBigDecimal(line.get(10)));
						iaGfDrawAccount.setFeeAmt(NumberUtils.toBigDecimal(line.get(11)));
						iaGfDrawAccount.setNetAmt(NumberUtils.toBigDecimal(line.get(12)));
						iaGfDrawAccountList.add(iaGfDrawAccount);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e.getStackTrace());
					}

				}
			}

			iaGfdrawAccountRepository.batchInsert(iaGfDrawAccountList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
