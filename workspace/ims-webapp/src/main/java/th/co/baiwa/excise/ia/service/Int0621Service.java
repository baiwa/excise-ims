package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.dao.CwpScwdDtlDao;
import th.co.baiwa.excise.ia.persistence.dao.CwpTblDtlDao;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;
import th.co.baiwa.excise.ia.persistence.entity.CwpTblDtl;
import th.co.baiwa.excise.ia.persistence.repository.CwpScwdDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.CwpTblDtlRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0621CompareFormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0621Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int0621Service {
	@Autowired
	private CwpScwdDtlDao cwpScwdDtlDao;

	@Autowired
	private CwpTblDtlDao cwpTblDtlDao;

	@Autowired
	private CwpScwdDtlRepository cwpScwdDtlRepository;

	@Autowired
	private CwpTblDtlRepository cwpTblDtlRepository;

	public List<CwpScwdDtl> getbudgetCodeList(CwpScwdDtl en) {
		List<CwpScwdDtl> budgetList = new ArrayList<CwpScwdDtl>();
		if (BeanUtils.isNotEmpty(en.getCwpScwdHdrId())) {
			budgetList = cwpScwdDtlDao.queryBudget(en);
		}
		return budgetList;
	}

	public List<CwpTblDtl> getdropdownT(CwpTblDtl en) {
		List<CwpTblDtl> dropdownT = new ArrayList<CwpTblDtl>();
		if (BeanUtils.isNotEmpty(en.getCwpTblHdrId())) {
			dropdownT = cwpTblDtlDao.queryFindT(en);
		}
		return dropdownT;
	}

	public List<Int0621Vo> compareExcel(List<Int0621CompareFormVo> formVo) {
		List<Int0621Vo> dataList = new ArrayList<Int0621Vo>();
		Int0621Vo int0621Vo;
		List<CwpTblDtl> TRList;
		CwpTblDtl TRData;
		try {
			/* assign variable to count old condition */
//			int countNumberThree = 0;
//			int countNumberNine = 0;
			/* --------------- */
			for (int i = 0; i < formVo.size(); i++) {
				int0621Vo = new Int0621Vo();
				TRList = new ArrayList<CwpTblDtl>();
				BigDecimal totalNetAmount = new BigDecimal(0);
				totalNetAmount = cwpScwdDtlDao.sumNetAmount(formVo.get(i).getCwpScwdHdrId(), formVo.get(i).getBudgetCode());
				// set data budget code and net amount
				int0621Vo.setBudgetCode(formVo.get(i).getBudgetCode());
				int0621Vo.setNetAmount(totalNetAmount);

				//check budget code condition
				if (BeanUtils.isNotEmpty(int0621Vo.getBudgetCode())) {
					char checkStr = int0621Vo.getBudgetCode().charAt(0);
					if (int0621Vo.getBudgetCode().length() > 4) {
						//index 0 == 3
						if ("3".equals(String.valueOf(checkStr))) {
							int0621Vo.setBudgetName("เงินงบประมาณ");
						}else {
						//index 0 == 9
							int0621Vo.setBudgetName("เงินงบประมาณกลาง");
						}
					}else {
						int0621Vo.setBudgetName("เงินนอกงบประมาณ");
					}
				}
				BigDecimal totalCarryForward = new BigDecimal(0);
				for (int j = 0; j < formVo.get(i).getCwpTblDtlId().size(); j++) {
					TRData = new CwpTblDtl();
					TRData = cwpTblDtlRepository.findOne(Long.valueOf(formVo.get(i).getCwpTblDtlId().get(j)));
					//check duplicate 'CarryForward' value
					if(formVo.get(i).getCwpTblDtlId().size() > 1) {
						totalCarryForward = totalCarryForward.add(TRData.getCarryForward());
						int lastIndex = formVo.get(i).getCwpTblDtlId().size() - 1;
						if(j == lastIndex) {
							// set total data difference
							TRData.setDiff(int0621Vo.getNetAmount().subtract(totalCarryForward));
						}
					}else {
						// set data difference
						TRData.setDiff(int0621Vo.getNetAmount().subtract(TRData.getCarryForward()));
					}
					TRList.add(TRData);
				}
				int0621Vo.setCwpTblDtlList(TRList);
				dataList.add(int0621Vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
}
