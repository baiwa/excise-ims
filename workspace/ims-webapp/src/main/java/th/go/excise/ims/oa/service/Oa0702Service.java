package th.go.excise.ims.oa.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0702JdbcRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa07JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0702Reg8000Vo;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Reg4000Vo;
import th.go.excise.ims.oa.vo.Oa07Vo;
import th.go.excise.ims.oa.vo.ResponseOa07Vo;

@Service
public class Oa0702Service {

    private static final Logger logger = LoggerFactory.getLogger(Oa0702Service.class);

    @Autowired
    private Oa0702JdbcRepository oa0702JdbcRepository;

    @Autowired
    private Oa07JdbcRepository oa07JdbcRepository;

	public ResponseOa07Vo reg4000(Oa07FormVo formVo) {

        List<Oa07Reg4000Vo> reg4000List = oa07JdbcRepository.reg4000(formVo);

        // ==> convert date
        Date dateS = ConvertDateUtils.parseStringToDate(formVo.getMonthStart(), ConvertDateUtils.MM_YYYY);

        //==> Add list year months
        List<String> yearsMonths = new ArrayList<>();
        // ==> for previous year
        for(int i=0; i< NumberUtils.toInt(formVo.getPreviousYear());i++){
            dateS = DateUtils.addYears(dateS, -i);
            // ==> add month
            for (int j = 0; j < NumberUtils.toInt(formVo.getMonthNum()); j++) {
                Date monthAdd = DateUtils.addMonths(dateS, j);
                String yearMonth = ConvertDateUtils.formatDateToString(monthAdd, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
                yearsMonths.add(yearMonth);
            }
        }

        // ==> Add object
        List<Oa07Vo> voList = new ArrayList<>();
        Oa07Vo vo = null;
        for (Oa07Reg4000Vo reg4000 : reg4000List) {
            vo = new Oa07Vo();
            copyPropertiesReg4000(vo, reg4000);
            String dutyDesc = ExciseUtils.getDutyDesc(vo.getDutyCode());
            vo.setDutyDesc(dutyDesc);

            List<String> taxListDtl = new ArrayList<>();
            List<String> percenDiffList = new ArrayList<>();

            // ==> query tax pay
            List<Oa0702Reg8000Vo> reg8000MList = oa0702JdbcRepository.reg8000M(reg4000.getNewRegId(), yearsMonths);

            // ==> 8000M
            Map<String, Oa0702Reg8000Vo> reg8000MMap = new HashMap<>();
            for (Oa0702Reg8000Vo voMap : reg8000MList) {
                reg8000MMap.put(voMap.getYearMonth(), voMap);
            }

            for (int idx = 0; idx < yearsMonths.size(); idx++) {
                Oa0702Reg8000Vo reg8000 = reg8000MMap.get(yearsMonths.get(idx));

                if (reg8000 != null) {
                    // check sum null
                    if (reg8000.getTaxAmount() == null) {
                        reg8000.setTaxAmount(BigDecimal.ZERO);
                    }

                    taxListDtl.add(reg8000.getTaxAmount().toString());
                    /*if (idx > 0) {
                        String taxAmBeforArr = taxListDtl.get(idx - 1);
                        BigDecimal taxAmBefor = new BigDecimal(taxAmBeforArr);

                        BigDecimal sub = reg8000.getTaxAmount().subtract(taxAmBefor); // b-a
                        BigDecimal multi = sub.multiply(new BigDecimal(100)); // b-a*100
                        BigDecimal avg = multi.divide(reg8000.getTaxAmount(), 2, RoundingMode.HALF_UP); // b-a*100/b

                        percenDiffList.add(avg.toString() + " %");
                    } else {
                        percenDiffList.add("");
                    }*/
                } else {
                    taxListDtl.add(BigDecimal.ZERO.toString());
                }
            }
            vo.setTaxPayList(taxListDtl);
            vo.setPerceneDiff(percenDiffList);
            
            
            // Group year and taxAmount
            List<String> groupTaxPay = new ArrayList<>();
            List<String> groupYearMonth = new ArrayList<>();
			for (int i = 0; i < NumberUtils.toInt(formVo.getPreviousYear()); i++) {
				Date date = ConvertDateUtils.parseStringToDate(formVo.getMonthStart(), ConvertDateUtils.MM_YYYY);
				Date subYear = DateUtils.addYears(date, -i);
				for (int j=0; j<NumberUtils.toInt(formVo.getMonthNum()); i++){
                    Date addMonth = DateUtils.addMonths(subYear, j);
                    groupYearMonth.add(ConvertDateUtils.formatDateToString(addMonth, ConvertDateUtils.MMM_YYYY_SPAC));
                }
			}

            vo.setGroupTaxPay(groupTaxPay);
            vo.setGroupYearMonth(groupYearMonth);
            voList.add(vo);
        }

        ResponseOa07Vo response07 = new ResponseOa07Vo();
        response07.setDataList(voList);
        response07.setCount(oa07JdbcRepository.reg4000Count(formVo));
        return response07;
    }

    private void copyPropertiesReg4000(Oa07Vo vo1, Oa07Reg4000Vo vo2) {
        try {
            BeanUtils.copyProperties(vo1, vo2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.warn(e.getMessage(), e);
        }
    }

}
