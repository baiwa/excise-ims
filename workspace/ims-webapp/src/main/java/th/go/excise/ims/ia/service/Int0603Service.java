package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0602JdbcRepository;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Service
public class Int0603Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Int0603Service.class);

	@Autowired
	private Int0602JdbcRepository int0602JdbcRepository;
	
	public List<Int0602ResultTab1Vo> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriterai");
		List<WsLicfri6010> wsLicfri6010List = int0602JdbcRepository.findByCriteria(int0602FormVo, "PRINT_COUNT");
		List<Int0602ResultTab1Vo> int0602ResultTab1Vo = new ArrayList<>();
		Int0602ResultTab1Vo intiData = null;
		if (wsLicfri6010List != null && wsLicfri6010List.size() > 0) {
			for (WsLicfri6010 wsLicfri6010 : wsLicfri6010List) {
				intiData = new Int0602ResultTab1Vo();
				try {
					BeanUtils.copyProperties(intiData, wsLicfri6010);
					if (StringUtils.isNoneBlank(wsLicfri6010.getSendDate())) {
						Date date = ConvertDateUtils.parseStringToDate(wsLicfri6010.getSendDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN);
						intiData.setSendDate(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (StringUtils.isNoneBlank(wsLicfri6010.getExpDate())) {
						Date date = ConvertDateUtils.parseStringToDate(wsLicfri6010.getExpDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN);
						intiData.setExpDate(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (StringUtils.isNoneBlank(wsLicfri6010.getStartDate())) {
						Date date = ConvertDateUtils.parseStringToDate(wsLicfri6010.getStartDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN);
						intiData.setStartDate(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					if (StringUtils.isNoneBlank(wsLicfri6010.getLicDate())) {
						Date date = ConvertDateUtils.parseStringToDate(wsLicfri6010.getLicDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN);
						intiData.setLicDate(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					int0602ResultTab1Vo.add(intiData);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		return int0602ResultTab1Vo;
	}
}
