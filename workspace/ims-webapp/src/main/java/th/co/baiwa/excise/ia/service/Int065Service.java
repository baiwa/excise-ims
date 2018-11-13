package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaWithdrawalDao;
import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
import th.co.baiwa.excise.upload.service.ExcalService;

@Service
public class Int065Service {
	@Autowired
	private ExcalService excalService;
	
	@Autowired
	private LovRepository lovRepository;

	@Autowired
	private IaWithdrawalDao iaWithdrawalDao;
	
	public DataTableAjax<Int065Vo> findAll(Int065FormVo formVo) {

		DataTableAjax<Int065Vo> dataTableAjax = new DataTableAjax<>();
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {

			String officeCode = mappingOfficeCode(formVo);
			formVo.setOfficeCode(officeCode);
			formVo.setDateFrom(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateFrom()));
			formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
			List<Int065Vo> list = iaWithdrawalDao.findAll(formVo);
			Long count = iaWithdrawalDao.count(formVo);

			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public List<Lov> sector() {
		List<Lov> lov = lovRepository.findByTypeAndLovIdMasterIsNullOrderBySubType("SECTOR_VALUE");
		return lov;
	}

	public List<Lov> area(Long idMaster) {
		return lovRepository.findByLovIdMasterOrderBySubType(idMaster);
	}

	public List<Lov> branch(Long idMaster) {
		return lovRepository.findByLovIdMasterOrderBySubType(idMaster);
	}

	public List<Lov> budgetType() {
		return lovRepository.findByTypeAndLovIdMasterIsNullOrderBySubType("BUDGET_TYPE");
	}

	public String mappingOfficeCode(Int065FormVo formVo) {
		Lov sectors = new Lov();
		Lov areas = new Lov();
		Lov branchs = new Lov();

		if (StringUtils.isNotBlank(formVo.getSector())) {
			sectors = lovRepository.findByLovId(Long.valueOf(formVo.getSector()));

			if (StringUtils.isNotBlank(formVo.getArea())) {
				areas = lovRepository.findByLovId(Long.valueOf(formVo.getArea()));

				if (StringUtils.isNotBlank(formVo.getBranch())) {
					branchs = lovRepository.findByLovId(Long.valueOf(formVo.getBranch()));
				}
			}
		}

		StringBuilder officeCode = new StringBuilder("");

		if (sectors != null) {
			if (StringUtils.isNoneBlank(sectors.getSubType())) {
				officeCode.append(sectors.getSubType());
			}
		}
		if (areas != null) {
			if (StringUtils.isNoneBlank(areas.getSubType())) {
				officeCode.append(areas.getSubType());
			}
		}
		if (branchs != null) {
			if (StringUtils.isNoneBlank(branchs.getSubType())) {
				officeCode.append(branchs.getSubType());
			}
		}

		return officeCode.toString();
	}



}
