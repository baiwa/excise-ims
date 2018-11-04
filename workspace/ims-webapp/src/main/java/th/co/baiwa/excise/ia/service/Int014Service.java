package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.util.excel.ExcelReaderUtils;
import th.co.baiwa.buckwaframework.common.util.excel.ExcelResultSet;
import th.co.baiwa.buckwaframework.common.util.excel.ExcelRowMapper;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.LovConstant;
import th.co.baiwa.excise.ia.persistence.vo.Int0142Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int014Vo;
import th.co.baiwa.excise.ia.persistence.vo.OfficeTrailVo;
import th.co.baiwa.excise.ia.persistence.vo.TrialBalanceCalcRow;
import th.co.baiwa.excise.ia.persistence.vo.TrialBalanceMapping;
import th.co.baiwa.excise.ia.persistence.vo.TrialBalanceVo;
import th.co.baiwa.excise.ia.persistence.vo.TypeTaxVo;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;

@Service
public class Int014Service {

	private static final Logger logger = LoggerFactory.getLogger(WebServiceExciseService.class);

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	public List<TrialBalanceVo> processData(Int014Vo int014Vo) throws Exception {

		MultipartFile file = int014Vo.getFileExel();

		if(!file.isEmpty()) {
//			file.
			File f = File.createTempFile("TBFile", null);
			file.transferTo(f);
			
			return ExcelReaderUtils.read(f, new ExcelRowMapper<TrialBalanceVo>() {

				@Override
				public TrialBalanceVo mapRow(ExcelResultSet rs, int rowNum) throws Exception {
					String acc = rs.getString(1);
					if(StringUtils.isNotEmpty(acc) && acc.startsWith("4")) {
						TrialBalanceVo vo = new TrialBalanceVo();
						vo.setAccountNo(acc);
						vo.setAccountDes(rs.getString(3));
						vo.setBringForwardAmount(rs.getNumber(5));
						vo.setDebit(rs.getNumber(8));
						vo.setCredit(rs.getNumber(9));
						vo.setCarryForwardAmount(rs.getNumber(10));
						return vo;
					}							
					return null;
				}
			});
		}
		
		return null;
	}
	
	public List<TrialBalanceCalcRow> compairData(Int0142Vo int014Vo, Int014Vo sessionUpload) {

		String officeCode = null;
		String yearMonthFrom = null;
		String yearMonthTo = null;
		String dateType = null;
		
		Assert.notNull(sessionUpload, "File upload in session is null");
		
		IncFri8020 resp = webServiceExciseService.IncFri8020(officeCode, yearMonthFrom, yearMonthTo, dateType, "0", "0");
		
		logger.info("IncFri8020 : {}", resp.getResponseCode());
		
		List<TrialBalanceMapping> mappers = this.loadMapping();
		
		List<TrialBalanceVo> lineAccount = sessionUpload.getLines();
		
		List<TrialBalanceCalcRow> resultCalc = new ArrayList<>();
		
		for (TrialBalanceVo item : lineAccount) {
//			logger.info("Account : {}" , item.getAccountNo() );
			TrialBalanceMapping myMap = this.findMapper(item.getAccountNo(), mappers);
			
			List<IncomeList> wsIncome = resp.getResponseData().getIncomeList();
			List<TypeTaxVo> typeTaxVoList = new ArrayList<>();
			if(myMap != null) {
				for (String incomeCode : myMap.getIncomeCodes()) {
					
					TypeTaxVo vo = new TypeTaxVo();
					vo.setIncomeCode(incomeCode);
					
					List<IncomeList> wsmap = new ArrayList<>();
					for (IncomeList incomeList : wsIncome) {
						if(incomeCode.equalsIgnoreCase(incomeList.getIncomeCode())) {
							wsmap.add(incomeList);
						}
					}
					vo.setWsmap(wsmap);
					typeTaxVoList.add(vo);				
				}
			}
			
//			logger.info("wsmap size : {}", typeTaxVoList.size());
			TrialBalanceCalcRow res = this.calc(item, typeTaxVoList);
			resultCalc.add(res);
		}
		
		return resultCalc;
	}
	
	private TrialBalanceCalcRow calc(TrialBalanceVo item, List<TypeTaxVo> typeTaxVoList ) {
		TrialBalanceCalcRow r = new TrialBalanceCalcRow();
		r.setTrialBalance(item);
		r.setTypeTaxVoList(typeTaxVoList);
		
		if(!typeTaxVoList.isEmpty()) {
			r.setTotalCal(BigDecimal.ZERO);			
		}
		
		for (TypeTaxVo taxVo : typeTaxVoList) {
			
			List<OfficeTrailVo> inList = new ArrayList<>();
			for (IncomeList incomeList : taxVo.getWsmap()) {
				
				OfficeTrailVo officeTrailVo = new OfficeTrailVo();
				taxVo.setIncomeName(incomeList.getIncomeName());
				officeTrailVo.setOfficeCode(incomeList.getOfficeReceive());
				
				List<Lov> listlov = ApplicationCache.getListOfValueByValueType(LovConstant.SECTOR_LIST, incomeList.getOfficeReceive());
				if(!listlov.isEmpty()) {
					Lov lov = listlov.get(0);
					officeTrailVo.setOfficeName(lov.getValue1());
				}
				String loc = incomeList.getNetLocAmount();
				String tax = incomeList.getNettaxAmount();
				BigDecimal locbig = new BigDecimal(loc);
				BigDecimal taxbig = new BigDecimal(tax);
				BigDecimal total = locbig.add(taxbig);
				officeTrailVo.setTotal(total);
				
				OfficeTrailVo merg = this.mergeSectorIncomeData(inList, incomeList);
				if(merg == null) {
					inList.add(officeTrailVo);
//					r.setTotalCal(r.getTotalCal().add(officeTrailVo.getTotal()));
				}else {
					logger.info("Merge office code : {}", merg.getOfficeCode());
//					r.setTotalCal(r.getTotalCal().add( merg.getTotal()));
				}
			}	
			
			taxVo.setOfficeList(inList);
			for ( OfficeTrailVo caloffice : inList) {
				r.setTotalCal(r.getTotalCal().add( caloffice.getTotal()));
			}
			
		}
		
		if(!typeTaxVoList.isEmpty()) {
			BigDecimal diff = item.getCarryForwardAmount().subtract(r.getTotalCal());
			logger.info("diff : {}", diff);
			r.setDiff(diff);
		}
		
		return  r;
	}
	
	private OfficeTrailVo mergeSectorIncomeData(List<OfficeTrailVo> inList, IncomeList incomeList) {
		
		OfficeTrailVo officeTrailVo = null;
		
		for (OfficeTrailVo sectorIncome : inList) {
			if(sectorIncome.getOfficeCode().equalsIgnoreCase(incomeList.getOfficeReceive())) {
				officeTrailVo = sectorIncome;
				break;
			}
		}
		
		if(officeTrailVo != null) {
			BigDecimal t = officeTrailVo.getTotal();
			String loc = incomeList.getNetLocAmount();
			String tax = incomeList.getNettaxAmount();
			BigDecimal locbig = new BigDecimal(loc);
			BigDecimal taxbig = new BigDecimal(tax);
			BigDecimal total = locbig.add(taxbig);
			
			BigDecimal merg = t.add(total);
			logger.info("merg Sum : {}", merg);
			officeTrailVo.setTotal(merg);
		}
		
		return officeTrailVo;
	}
	
	private TrialBalanceMapping findMapper(String accountNo ,List<TrialBalanceMapping> mappers ) {
		
		for (TrialBalanceMapping trialBalanceMapping : mappers) {
			if(accountNo.equalsIgnoreCase(trialBalanceMapping.getAccountNo())){
				return trialBalanceMapping;
			}
		}
		
		return null;
	}
	
	private List<TrialBalanceMapping> loadMapping() {
		
		List<TrialBalanceMapping> mapping = new ArrayList<>();
		
		TrialBalanceMapping v = new TrialBalanceMapping();
		v.setAccountNo("4102020103");
		List<String> incomcodes = new ArrayList<>();
		incomcodes.add("203000");
		incomcodes.add("203010");
		incomcodes.add("203019");
		v.setIncomeCodes(incomcodes);
		mapping.add(v);

		v = new TrialBalanceMapping();
		v.setAccountNo("4102020105");
		incomcodes = new ArrayList<>();

		incomcodes.add("205000");
		incomcodes.add("205010");
		incomcodes.add("205019");
		incomcodes.add("205020");
		incomcodes.add("205029");
		incomcodes.add("205030");
		incomcodes.add("205039");
		incomcodes.add("205039");
		incomcodes.add("205040");
		incomcodes.add("205050");
		incomcodes.add("205059");
			
		v.setIncomeCodes(incomcodes);
		mapping.add(v);
		
		
		v = new TrialBalanceMapping();
		v.setAccountNo("4102020106");
		incomcodes = new ArrayList<>();

		incomcodes.add("207000");
			
		v.setIncomeCodes(incomcodes);
		mapping.add(v);
		
		v = new TrialBalanceMapping();
		v.setAccountNo("4102020111");
		incomcodes = new ArrayList<>();

		incomcodes.add("216000");
		incomcodes.add("216010");
		incomcodes.add("216019");
			
		v.setIncomeCodes(incomcodes);
		mapping.add(v);
		
		v = new TrialBalanceMapping();
		v.setAccountNo("4102020201");
		incomcodes = new ArrayList<>();
		incomcodes.add("217000");
		incomcodes.add("217030");
		incomcodes.add("217039");
		incomcodes.add("217040");
		incomcodes.add("217049");
		v.setIncomeCodes(incomcodes);
		mapping.add(v);

		v = new TrialBalanceMapping();
		v.setAccountNo("4102020204");
		incomcodes = new ArrayList<>();
		incomcodes.add("202000");
		v.setIncomeCodes(incomcodes);
		mapping.add(v);
		
		return mapping;
	}

}
