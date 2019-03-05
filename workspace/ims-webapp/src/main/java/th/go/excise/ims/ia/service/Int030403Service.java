package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.Int030403JdbcRepository;
import th.go.excise.ims.ia.vo.Int030403FormVo;
import th.go.excise.ims.ia.vo.Int030403Vo;

@Service
public class Int030403Service {

	@Autowired
	Int030403JdbcRepository int030403JdbcRepository;

	public List<Int030403Vo> list(Int030403FormVo form) {
		List<Int030403Vo> iaRiskBudgetProject = new ArrayList<Int030403Vo>();
		iaRiskBudgetProject = int030403JdbcRepository.list(form);
		int index = 0;
		for (Int030403Vo int030403Vo : iaRiskBudgetProject) {
			BigDecimal expenseBudgetAmountAll = new BigDecimal(0);
			Float ex1 = StringToFloat(int030403Vo.getIaRiskBudgetProject().getExpensebudgetamounta());
			
			Float ex2 = StringToFloat(int030403Vo.getIaRiskBudgetProject().getExpensebudgetamountm());
			
			Float ex3 = StringToFloat(int030403Vo.getIaRiskBudgetProject().getExpensebudgetamountx());
				    
			expenseBudgetAmountAll = new BigDecimal(ex1+ex2+ex3);
		
			iaRiskBudgetProject.get(index).setExpenseBudgetAmountAll(expenseBudgetAmountAll);
			index++;
		}
		return iaRiskBudgetProject;
	}
	public Float StringToFloat(String amount) {
		Float f = 0f; 
		if(amount!="" &&amount!=null) {
			
			f = Float.valueOf(amount.replaceAll(",", ""));
			
		}
			    
		return f;
		
	}
}
