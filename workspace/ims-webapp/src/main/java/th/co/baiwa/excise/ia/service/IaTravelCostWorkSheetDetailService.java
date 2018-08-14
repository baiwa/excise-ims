package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostDetailDao;
import th.co.baiwa.excise.ia.persistence.entity.DataTravelCostWsDetail;
import th.co.baiwa.excise.ia.persistence.repository.DataTravelCostWsDetailRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09213Vo;

@Service
public class IaTravelCostWorkSheetDetailService {

	@Autowired
	private TravelCostDetailDao travelCostDetailDao;

	@Autowired
    private DataTravelCostWsDetailRepository dataTravelCostWsDetailRepository;
	public DataTableAjax<Int09213Vo> findAll(List<Int09213Vo> dataTableSession) {

		Long count = Long.valueOf(dataTableSession.size());

		DataTableAjax<Int09213Vo> dataTableAjax = new DataTableAjax<>();

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(dataTableSession);

		return dataTableAjax;
	}
	
	public void addData(List<Int09213Vo> dataTableSession ,Int09213FormVo formVo) {
		
		//id
		Integer id = (int) (Math.random() * 50 + 1);
		
		Int09213Vo vo = new Int09213Vo();
		vo.setWorkSheetDetailId(id.toString());
		vo.setPrefix(formVo.getPrefix());
		vo.setName(formVo.getName());
		vo.setLastName(formVo.getLast());
		vo.setPosition(formVo.getPosition()); 		
		
		dataTableSession.add(vo);
	}
	
	public List<LabelValueBean> dropdownListType(Int09213FormVo formVo){
		return 	travelCostDetailDao.drodownList(formVo.getLovIdMaster());
	}

	public void addData(Int09213FormVo formVo, List<Int09213Vo> dataTableSession){

        Int09213Vo voTable = new Int09213Vo();
        dataTableSession.add(voTable);

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
