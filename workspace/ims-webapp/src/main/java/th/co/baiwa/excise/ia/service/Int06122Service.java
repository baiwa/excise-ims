package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.co.baiwa.excise.constant.MessageConstant;
import th.co.baiwa.excise.ia.persistence.entity.Expenses;
import th.co.baiwa.excise.ia.persistence.repository.ExpensesRepository;

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
}

