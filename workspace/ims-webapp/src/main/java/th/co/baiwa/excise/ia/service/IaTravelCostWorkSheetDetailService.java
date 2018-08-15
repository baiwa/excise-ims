package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostDetailDao;
import th.co.baiwa.excise.ia.persistence.entity.DataTravelCostWsDetail;
import th.co.baiwa.excise.ia.persistence.repository.DataTravelCostWsDetailRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09213Vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class IaTravelCostWorkSheetDetailService {

	@Autowired
	private TravelCostDetailDao travelCostDetailDao;

	@Autowired
    private LovRepository lovRepository;

	@Autowired
    private DataTravelCostWsDetailRepository dataTravelCostWsDetailRepository;

	public DataTableAjax<Int09213Vo> findAll(List<Int09213Vo> dataTableSession) {

        DataTableAjax<Int09213Vo> dataTableAjax = new DataTableAjax<>();
        List<Int09213Vo> list = new ArrayList<>();
		Long count = Long.valueOf(dataTableSession.size());

		if (count>0){
            list = dataTableSession;
        }

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}
	
	public void addData(List<Int09213Vo> dataTableSession ,Int09213FormVo formVo) {


        Lov lov = lovRepository.findByTypeAndLovId("ACC_FEE",Long.valueOf(formVo.getLevel()));
        Int09213Vo vo = new Int09213Vo();

        vo.setPrefix(formVo.getPrefix());
        vo.setName(formVo.getName());
        vo.setLastName(formVo.getLast());
        vo.setPosition(formVo.getPosition());
        vo.setAllowanceDate(new BigDecimal(formVo.getDays()));

        BigDecimal day = new BigDecimal(formVo.getDays());
        BigDecimal allowanceCost = new BigDecimal(lov.getValue5());
        vo.setAllowanceCost(day.multiply(allowanceCost));

        vo.setRentDate(new BigDecimal(formVo.getNumberLive()));
        //check type room
        if("1".equals(formVo.getTypeRoom())){
            BigDecimal money = new BigDecimal(lov.getValue2());
            vo.setRentCost(vo.getRentDate().multiply(money));
        }
        if ("2".equals(formVo.getTypeRoom())){
            BigDecimal money = new BigDecimal(lov.getValue4());
            vo.setRentCost(vo.getRentDate().multiply(money));
        }

		dataTableSession.add(vo);
	}
	
	public List<LabelValueBean> dropdownListType(Int09213FormVo formVo){
		return 	travelCostDetailDao.drodownList(formVo.getLovIdMaster());
	}

    public void save(Int09213FormVo formVo, List<Int09213Vo> dataTableSession){
        for (Int09213Vo table:dataTableSession) {
            DataTravelCostWsDetail entity = new DataTravelCostWsDetail();
            entity.setHeaderId(table.getHeaderId());
            entity.setName(table.getName());
            entity.setLastName(table.getLastName());
            entity.setPosition(table.getPosition());
            entity.setCategory(table.getCategory());
            entity.setDegree(table.getDegree());
            entity.setAllowanceDate(table.getAllowanceDate());
            entity.setAllowanceCost(table.getAllowanceCost());
            entity.setRentDate(table.getRentDate());
            entity.setRentCost(table.getRentCost());
            entity.setTravelCost(table.getTravelCost());
            entity.setOtherCost(table.getOtherCost());
            entity.setSumCost(table.getSumCost());
            entity.setNote(table.getNote());

            dataTravelCostWsDetailRepository.save(entity);
        }
    }

}
