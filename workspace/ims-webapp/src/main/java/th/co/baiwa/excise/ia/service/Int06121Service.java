package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.ExpensesDao;
import th.co.baiwa.excise.ia.persistence.repository.ExpensesRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int06121FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06121Vo;

import java.util.List;

@Service
public class Int06121Service {

    @Autowired
    private ExpensesDao expensesDao;

    @Autowired
    private ExpensesRepository expensesRepository;

    public DataTableAjax<Int06121Vo> findAll(Int06121FormVo formVo){

        DataTableAjax<Int06121Vo> dataTableAjax = new DataTableAjax<>();
        if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())){
            List<Int06121Vo> datas = expensesDao.findAll(formVo);
            Long count = expensesDao.count(formVo);
            dataTableAjax.setDraw(formVo.getDraw()+1);
            dataTableAjax.setRecordsFiltered(count);
            dataTableAjax.setRecordsTotal(count);
            dataTableAjax.setData(datas);
        }
        return dataTableAjax;
    }

    public void delete(String id){
        expensesRepository.delete(Long.valueOf(id));
    }

}

