package th.co.baiwa.excise.ia.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.excise.constant.IaConstant;
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
import java.util.Random;

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
	
	public Int09213Vo addData(Int09213FormVo formVo) {
        Random rand = new Random();
        int  randomId = 0;
        if (StringUtils.isNotBlank(formVo.getEditFlag())) {
        	  randomId = Integer.parseInt(formVo.getIdEdit());
		}else {
			  randomId = rand.nextInt(50) + 1;
		}
        

        Lov lov = lovRepository.findByTypeAndLovId("ACC_FEE",Long.valueOf(formVo.getLevel()));

        Int09213Vo vo = new Int09213Vo();
        vo.setWorkSheetDetailId(String.valueOf(randomId));
        vo.setPrefix(formVo.getPrefix());
        vo.setName(formVo.getName());
        vo.setLastName(formVo.getLast());
        vo.setPosition(formVo.getPosition());
        vo.setAllowanceDate(new BigDecimal(formVo.getDays()));

        //date description
        String dateDesc =  dateDesc(formVo.getDays());
        vo.setAllowanceDateDesc(dateDesc);

        //call datys
        BigDecimal day = calculateDate(formVo.getDays());
        BigDecimal allowanceCost = new BigDecimal(lov.getValue5());
        vo.setAllowanceCost(day.multiply(allowanceCost));

        vo.setRentDate(new BigDecimal(formVo.getNumberLive()));
        //check type room
        if("1".equals(formVo.getTypeRoom())){
            BigDecimal money = new BigDecimal(StringUtils.trim(lov.getValue2()));
            vo.setRentCost(vo.getRentDate().multiply(money));
        }
        if ("2".equals(formVo.getTypeRoom())){
            BigDecimal money = new BigDecimal(StringUtils.trim(lov.getValue4()));
            vo.setRentCost(vo.getRentDate().multiply(money));
        }

        vo.setTravelCost(new BigDecimal(formVo.getTravelCost()));
        vo.setOtherCost(new BigDecimal(formVo.getOtherCost()));

        BigDecimal sum = vo.getAllowanceCost().add(vo.getRentCost()).add(vo.getTravelCost()).add(vo.getOtherCost());
        vo.setSumCost(sum);
        vo.setNote(formVo.getNote());


        //form
        vo.setIdForm(formVo.getId());
        vo.setPrefixForm( formVo.getPrefix());
        vo.setNameForm( formVo.getName());
        vo.setLastForm(formVo.getLast());
        vo.setPositionForm(formVo.getPosition());
        vo.setTypeForm(formVo.getType());
        vo.setLevelForm(formVo.getLevel());
        vo.setAppoveDateDataForm(formVo.getAppoveDateData());
        vo.setWithdrawDateDataForm(formVo.getWithdrawDateData());
        vo.setGoFromForm(formVo.getGoFrom());
        vo.setFoodForm(formVo.getFood());
        vo.setStartGoDateDataForm(formVo.getStartGoDateData());
        vo.setEndGoDateDataForm(formVo.getEndGoDateData());
        vo.setTypeWithdrawalForm( formVo.getTypeWithdrawal());
        vo.setNumberLiveForm(formVo.getNumberLive());
        vo.setTypeRoomForm(formVo.getTypeRoom());
        vo.setNoteForm(formVo.getNote());
        vo.setLovIdMasterForm(formVo.getLovIdMaster());
        vo.setDaysForm(formVo.getDays());
        vo.setOtherCostForm(formVo.getOtherCost());
        vo.setTravelCostForm(formVo.getTravelCost());

        return vo;
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

    public BigDecimal calculateDate(String hours){
        boolean breakLoop = false;
        int hour = Integer.parseInt(hours);
        int day = 0;
        while (!breakLoop) {
            if (hour >= 24) { //hours > 24
                hour -= 24;
                day++;
            } else {  //hours < 24
                if (hour>5){
                    hour=5;
                }else{
                    hour=0;
                }
                breakLoop = true;
            }
        }
        BigDecimal days = new BigDecimal(day + "." + hour);
	    return days;
    }

    public String dateDesc(String hours){
        boolean breakLoop = false;
        int hour = Integer.parseInt(hours);
        int day = 0;
        int hourDesc = 0;
        while (!breakLoop) {
            if (hour >= 24) { //hours > 24
                hour -= 24;
                day++;
            } else {  //hours < 24
                hourDesc = hour;
                if (hour>5){
                    hour=5;
                }else{
                    hour=0;
                }
                breakLoop = true;
            }
        }
        return  day+" "+IaConstant.VALUE.DAY+" "+hourDesc+" "+IaConstant.VALUE.HOUR;
    }
}
