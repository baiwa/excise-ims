package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.co.baiwa.excise.constant.MessageConstant;
import th.co.baiwa.excise.ia.persistence.entity.Expenses;
import th.co.baiwa.excise.ia.persistence.repository.ExpensesRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int06121Vo;
import th.co.baiwa.excise.utils.NumberUtils;

@Service
public class Int06122Service {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Transactional
    public String save(Expenses expenses) {
        String msg = "";
        try {
            expensesRepository.save(expenses);
            msg = MessageConstant.MSG.STATUS.SAVE.SUCCESS;
        } catch (Exception e) {
            msg = MessageConstant.MSG.STATUS.SAVE.FAIL;
        }
        return msg;
    }

    @Transactional
    public String edit(Int06121Vo expenses) {
        String msg = "";
        try {
            Expenses data = expensesRepository.findOne(Long.valueOf(expenses.getId()));
            data.setAccountId(expenses.getAccountId());
            data.setAccountName(expenses.getAccountName());

            data.setServiceReceive(NumberUtils.toBigDecimal(expenses.getServiceReceive()));
            data.setSuppressReceive(NumberUtils.toBigDecimal(expenses.getSuppressReceive()));
            data.setBudgetReceive(NumberUtils.toBigDecimal(expenses.getBudgetReceive()));
            data.setSumReceive(NumberUtils.toBigDecimal(expenses.getSumReceive()));

            data.setServiceWithdraw(NumberUtils.toBigDecimal(expenses.getServiceWithdraw()));
            data.setSuppressWithdraw(NumberUtils.toBigDecimal(expenses.getSuppressWithdraw()));
            data.setBudgetWithdraw(NumberUtils.toBigDecimal(expenses.getBudgetWithdraw()));
            data.setSumWithdraw(NumberUtils.toBigDecimal(expenses.getSumWithdraw()));
            data.setServiceBalance(NumberUtils.toBigDecimal(expenses.getServiceBalance()));
            data.setSuppressBalance(NumberUtils.toBigDecimal(expenses.getSuppressBalance()));
            data.setBudgetBalance(NumberUtils.toBigDecimal(expenses.getBudgetBalance()));
            data.setSumBalance(NumberUtils.toBigDecimal(expenses.getSumBalance()));
            data.setMoneyBudget(NumberUtils.toBigDecimal(expenses.getMoneyBudget()));
            data.setMoneyOut(NumberUtils.toBigDecimal(expenses.getMoneyOut()));
            data.setAverageCost(NumberUtils.toBigDecimal(expenses.getAverageCost()));
            data.setAverageGive(expenses.getAverageGive());
            data.setAverageFrom(NumberUtils.toBigDecimal(expenses.getAverageFrom()));
            data.setAverageComeCost(expenses.getAverageComeCost());

            data.setNote(expenses.getNote());

            expensesRepository.save(data);
            msg = MessageConstant.MSG.STATUS.SAVE.SUCCESS;
        } catch (Exception e) {
            msg = MessageConstant.MSG.STATUS.SAVE.FAIL;
        }
        return msg;
    }
}

