package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.ExpensesDao;
import th.co.baiwa.excise.ia.persistence.vo.Int06113FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06113Vo;

import java.util.Date;
import java.util.List;

@Service
public class Int06113Service {

    @Autowired
    private LovRepository lovRepository;

    @Autowired
    private ExpensesDao expensesDao;

    public DataTableAjax<Int06113Vo> findAll(Int06113FormVo formVo){

        DataTableAjax<Int06113Vo> dataTableAjax = new DataTableAjax<>();
        if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())){

            Date date = DateConstant.convertStrToDate(formVo.getYear(), ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.TH);
            String yyyy = DateConstant.convertDateToStr(date, ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.EN);
            formVo.setYear(yyyy);

            List<Int06113Vo> datas = expensesDao.findAllCheckCost(formVo);
            Long count = expensesDao.countCheckCost(formVo);

            dataTableAjax.setDraw(formVo.getDraw()+1);
            dataTableAjax.setRecordsFiltered(count);
            dataTableAjax.setRecordsTotal(count);
            dataTableAjax.setData(datas);
        }
        return dataTableAjax;
    }

    public List<Lov> sector() {
        return lovRepository.findByTypeAndLovIdMasterIsNullOrderBySubType("SECTOR_VALUE");
    }

    public List<Lov> area(Long idMaster) {
        return lovRepository.findByLovIdMasterOrderBySubType(idMaster);
    }

    public List<LabelValueBean> year() {
		return expensesDao.year();
    }
}
