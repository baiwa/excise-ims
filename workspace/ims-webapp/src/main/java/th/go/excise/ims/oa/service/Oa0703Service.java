package th.go.excise.ims.oa.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0703JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0703TaxpayVo;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Reg4000Vo;
import th.go.excise.ims.oa.vo.Oa07Vo;

@Service
public class Oa0703Service {

	private static final Logger logger = LoggerFactory.getLogger(Oa0703Service.class);

	@Autowired
	private Oa0703JdbcRepository oa07Jdb03cRepository;

	public List<Oa07Vo> reg4000(Oa07FormVo formVo) {

		List<Oa07Reg4000Vo> reg4000List = oa07Jdb03cRepository.reg4000(formVo);

		// ==> Add object
		List<Oa07Vo> voList = new ArrayList<>();
		Oa07Vo vo = null;
		for (Oa07Reg4000Vo reg4000 : reg4000List) {
			vo = new Oa07Vo();
			copyPropertiesReg4000(vo, reg4000);

			// ==> query tax pay
			List<Oa0703TaxpayVo> reg8000MList = oa07Jdb03cRepository.reg8000M(reg4000.getNewRegId());

			int i = 0;
			List<String> listDtl = new ArrayList<>();
			for (Oa0703TaxpayVo reg8000 : reg8000MList) {

				//check sum null
				if(reg8000.getSumTaxAmount() == null) {
					reg8000.setSumTaxAmount(BigDecimal.ZERO);
				}
				
				listDtl.add(reg8000.getSumTaxAmount().toString());
				if (i > 0) {
					Oa0703TaxpayVo taxAmBefor = reg8000MList.get(i - 1);
					if(taxAmBefor.getSumTaxAmount() == null) {
						taxAmBefor.setSumTaxAmount(BigDecimal.ZERO);
					}
					BigDecimal sub = reg8000.getSumTaxAmount().subtract(taxAmBefor.getSumTaxAmount()); // b-a
					BigDecimal multi = sub.multiply(new BigDecimal(100)); // b-a*100
					BigDecimal avg = multi.divide(reg8000.getSumTaxAmount(), 2, RoundingMode.HALF_UP); //b-a*100/b

					listDtl.add(avg.toString()+" %");
				}else {
					listDtl.add("");
				}
				i++;
			}
			vo.setTaxPayList(listDtl);
			voList.add(vo);
		}
		return voList;
	}

	private void copyPropertiesReg4000(Oa07Vo vo1, Oa07Reg4000Vo vo2) {
		try {
			BeanUtils.copyProperties(vo1, vo2);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

}
