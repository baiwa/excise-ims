package th.co.baiwa.excise.ta.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.ta.persistence.entity.MaterialsWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.MaterialsWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.repository.MaterialsWsDetailRepository;
import th.co.baiwa.excise.ta.persistence.repository.MaterialsWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.vo.Ope041Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope043DataTable;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 
import th.co.baiwa.buckwaframework.common.util.NumberUtils;

@Service
public class MaterialsWsService {
	
private Logger logger = LoggerFactory.getLogger(DisbRmatWsService.class);
	
	@Autowired
	private MaterialsWsHeaderRepository materialsWsHeaderRepository;
	
	@Autowired
	private MaterialsWsDetailRepository materialsWsDetailRepository;
	
	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;
	
	public List<Ope043DataTable> queryExciseIdFromAccDTL(String exciseId, String type, String start, String end) {
		String[] startData = start.split("/");
		String[] endData = end.split("/");

		Calendar startCal = Calendar.getInstance();
		startCal.set(Integer.parseInt(startData[1]), Integer.parseInt(startData[0]), 1);
		Calendar endCalforUse = Calendar.getInstance();
		endCalforUse.set(Integer.parseInt(endData[1]), Integer.parseInt(endData[0]), 1);

		Calendar endCal = Calendar.getInstance();
		endCal.set(Integer.parseInt(endData[1]), Integer.parseInt(endData[0]), 1);

		int backMonth = 1;
		while (!isEqualsDate(startCal.getTime(), endCal.getTime())) {
			endCal.add(Calendar.MONTH, -1);
			backMonth++;
		}

		List<Ope041Vo> listData = planWorksheetHeaderDao.queryExciseIdFromAccDTL(exciseId, type,
				endCalforUse.getTime(), backMonth);
		
		List<Ope043DataTable> opeDataTableList = new ArrayList<Ope043DataTable>();
		Ope043DataTable opeDataTable = new Ope043DataTable();
		
		if(BeanUtils.isNotEmpty(listData)) {
		Ope041Vo database = listData.get(0);
		
		opeDataTable.setNo(Long.valueOf("1"));
		opeDataTable.setOrder(database.getProduct1());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve1()));
		opeDataTableList.add(opeDataTable);

		opeDataTable = new Ope043DataTable();
		opeDataTable.setNo(Long.valueOf("2"));
		opeDataTable.setOrder(database.getProduct2());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve2()));
		opeDataTableList.add(opeDataTable);

		opeDataTable = new Ope043DataTable();
		opeDataTable.setNo(Long.valueOf("3"));
		opeDataTable.setOrder(database.getProduct3());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve3()));
		opeDataTableList.add(opeDataTable);

		opeDataTable = new Ope043DataTable();
		opeDataTable.setNo(Long.valueOf("4"));
		opeDataTable.setOrder(database.getProduct4());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve4()));
		opeDataTableList.add(opeDataTable);

		opeDataTable = new Ope043DataTable();
		opeDataTable.setNo(Long.valueOf("5"));
		opeDataTable.setOrder(database.getProduct5());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve5()));
		opeDataTableList.add(opeDataTable);
		}

		return opeDataTableList;
	}
	
	public boolean isEqualsDate(Date date1, Date date2) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMM");
		return simple.format(date1).equals(simple.format(date2));
	}

	public List<Ope043DataTable> sumData(List<Ope041Vo> dataList, Ope041Vo vo) {

		List<Ope043DataTable> opeDataTableList = new ArrayList<Ope043DataTable>();
		Ope043DataTable opeDataTable = new Ope043DataTable();
		Ope041Vo database = vo;

		opeDataTable.setNo(Long.valueOf("1"));
		opeDataTable.setOrder(database.getProduct1());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve1()));
		opeDataTableList.add(opeDataTable);

		opeDataTable = new Ope043DataTable();
		opeDataTable.setNo(Long.valueOf("2"));
		opeDataTable.setOrder(database.getProduct2());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve2()));
		opeDataTableList.add(opeDataTable);

		opeDataTable = new Ope043DataTable();
		opeDataTable.setNo(Long.valueOf("3"));
		opeDataTable.setOrder(database.getProduct3());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve3()));
		opeDataTableList.add(opeDataTable);

		opeDataTable = new Ope043DataTable();
		opeDataTable.setNo(Long.valueOf("4"));
		opeDataTable.setOrder(database.getProduct4());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve4()));
		opeDataTableList.add(opeDataTable);

		opeDataTable = new Ope043DataTable();
		opeDataTable.setNo(Long.valueOf("5"));
		opeDataTable.setOrder(database.getProduct5());
		opeDataTable.setCounting(NumberUtils.stringToLong(database.getMonthRecieve5()));
		opeDataTableList.add(opeDataTable);
		
		List<Ope043DataTable> returnDataList = new ArrayList<Ope043DataTable>();
		if (BeanUtils.isNotEmpty(dataList)) {
			for (Ope043DataTable opeData : opeDataTableList) {
				for (Ope041Vo formUpload : dataList) {
					if (opeData.getOrder().equals(formUpload.getColumn2())) {
						opeData.setEx1(NumberUtils.stringToLong(formUpload.getColumn3()));
						opeData.setEx2(NumberUtils.stringToLong(formUpload.getColumn4()));
						if (opeData.getEx1() > opeData.getEx2()) {
							opeData.setResult1(opeData.getEx1());
						} else {
							opeData.setResult1(opeData.getEx2());
						}
						if (opeData.getEx1() > opeData.getResult1()) {
							opeData.setResult1(opeData.getEx1());
						}
						
						opeData.setResult2((opeData.getEx1() - opeData.getResult1()));
						break;
					}
				}
				returnDataList.add(opeData); 
			}
		}
		Ope043DataTable opeDataTable1;
		Ope041Vo formUpload;
		for (int  i = 1 ; i < dataList.size() ; i++) {
			formUpload = new Ope041Vo();
			formUpload = dataList.get(i);
			boolean isExist = false;
			for (Ope043DataTable opeData : opeDataTableList) {
				if (opeData.getOrder().equals(formUpload.getColumn2())) {
					isExist = true;
				}
			}
			if (!isExist) {
				opeDataTable1 = new Ope043DataTable();
				opeDataTable1.setEx1(NumberUtils.stringToLong(formUpload.getColumn3()));
				opeDataTable1.setEx2(NumberUtils.stringToLong(formUpload.getColumn4()));
				returnDataList.add(opeDataTable1);
			}
		}
		if(BeanUtils.isNotEmpty(returnDataList)) {
			returnDataList.get(0).setColumnNameEx1(dataList.get(0).getColumn3());
			returnDataList.get(0).setColumnNameEx2(dataList.get(0).getColumn4());
		}
		return returnDataList;
	}
	
	
	public void insertMaterialsWsService(List<Ope043DataTable> allData) {
		logger.info("Save Ope043");
		Long hdr = 0L;
		MaterialsWorksheetHeader materialsWorksheetHeader = null;
		MaterialsWorksheetDetail dtl = new MaterialsWorksheetDetail();
		MaterialsWorksheetHeader hd = new MaterialsWorksheetHeader();
		for (Ope043DataTable value : allData) {

			if (BeanUtils.isNotEmpty(value.getExciseId())) {
				hd = new MaterialsWorksheetHeader();
				hd.setExciseId(value.getExciseId());
				hd.setTaAnalysisId(value.getAnalysNumber());
				hd.setStartDate(value.getStartDate());
				hd.setEndDate(value.getEndDate());
				materialsWorksheetHeader = materialsWsHeaderRepository.save(hd);
				hdr = materialsWorksheetHeader.getTaMaterialsWsHeaderId();
			}

			if (BeanUtils.isEmpty(value.getExciseId())) {
				dtl = new MaterialsWorksheetDetail();
				dtl.setTaMaterialsWsHeaderId(hdr);
				dtl.setMaterialsWsDtlNo(value.getNo());
				dtl.setMaterialsWsDtlOrder(value.getOrder());
				dtl.setMaterialsWsDtlBalance(value.getEx1());
				dtl.setMaterialsWsDtlStorage(value.getEx2());
				dtl.setMaterialsWsDtlCounting(value.getCounting());
				dtl.setResult(value.getResult1());
				dtl.setResult1(value.getResult2());
				materialsWsDetailRepository.save(dtl);
			}
		}
	}

}
