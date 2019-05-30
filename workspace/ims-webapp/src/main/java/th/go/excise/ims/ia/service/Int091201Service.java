package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingH;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.ia.persistence.repository.IaAuditWorkingD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditWorkingHRepository;
import th.go.excise.ims.ia.persistence.repository.IaEmpWorkingDtlRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaEmpWorkingDtlJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int091201JdbcRepository;
import th.go.excise.ims.ia.vo.Int091201DayDetailVo;
import th.go.excise.ims.ia.vo.Int091201FormSaveVo;
import th.go.excise.ims.ia.vo.Int091201FormSearchVo;
import th.go.excise.ims.ia.vo.Int091201HdrDtlVo;
import th.go.excise.ims.ia.vo.Int091201LineDetail;
import th.go.excise.ims.ia.vo.Int091201ViewFullDetailRequstVo;
import th.go.excise.ims.ia.vo.Int091201ViewFullDetailVo;
import th.go.excise.ims.ia.vo.Int091201Vo;

@Service
public class Int091201Service {

	@Autowired
	private IaEmpWorkingDtlJdbcRepository iaEmpWorkingDtlJdbcRepository;

	@Autowired
	private IaAuditWorkingHRepository iaAuditWorkingHRepository;

	@Autowired
	private IaAuditWorkingD1Repository iaAuditWorkingD1Repository;

	@Autowired
	private IaEmpWorkingDtlRepository iaEmpWorkingDtlRepository;

	@Autowired
	private Int091201JdbcRepository int091201JdbcRepository;

	public List<Int091201Vo> getList(Int091201FormSearchVo res) {
		List<Int091201Vo> dataRes = iaEmpWorkingDtlJdbcRepository.getList(res);
		return dataRes;
	}

	public List<IaAuditWorkingH> findHeaderAll() {
		return iaAuditWorkingHRepository.findAll();
	}

	public Int091201HdrDtlVo findDtl(String auditWorkingNo) {
		Int091201HdrDtlVo dataRes = new Int091201HdrDtlVo();
		IaAuditWorkingH iaAuditWorkingH = iaAuditWorkingHRepository.findByAuditWorkingNo(auditWorkingNo);
		List<IaAuditWorkingD1> iaAuditWorkingD1List = iaAuditWorkingD1Repository.findByAuditWorkingNo(auditWorkingNo);
		dataRes.setIaAuditWorkingH(iaAuditWorkingH);
		dataRes.setIaAuditWorkingD1List(iaAuditWorkingD1List);
		return dataRes;
	}

	@Transactional
	public void saveAuditWorking(Int091201FormSaveVo res) {
		String offCode = null;
		if (StringUtils.isNotEmpty(res.getSector())) {
			if (StringUtils.isEmpty(res.getArea()) || "0".equals(res.getArea())) {
				offCode = res.getSector();
			} else if (StringUtils.isEmpty(res.getBranch()) || "0".equals(res.getBranch())) {
				offCode = res.getArea();
			} else if (StringUtils.isNotEmpty(res.getBranch()) && !"0".equals(res.getBranch())) {
				offCode = res.getBranch();
			}
		}

		IaAuditWorkingH dataSave = new IaAuditWorkingH();

		Date date = ConvertDateUtils.parseStringToDate(res.getAuWorkingMonth(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String dateStr = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

		String auditIncNo = iaEmpWorkingDtlJdbcRepository.generateAuditIncNo();
		auditIncNo = offCode + "/" + auditIncNo;
		dataSave.setAuditWorkingNo(auditIncNo);
		dataSave.setAuWorkingMonth(dateStr);
		dataSave.setAuPetitionNo(res.getAuPetitionNo());
		dataSave.setAuOfficeCode(offCode);
		dataSave.setWorkingConditionText(res.getWorkingConditionText());
		dataSave.setWorkingCriteriaText(res.getWorkingCriteriaText());
		iaAuditWorkingHRepository.save(dataSave);

		List<IaAuditWorkingD1> iaAuditWorkingD1List = new ArrayList<IaAuditWorkingD1>();
		iaAuditWorkingD1List = res.getIaAuditWorkingD1List();
		IaAuditWorkingD1 iaAuditWorkingD1Save = null;

		for (IaAuditWorkingD1 iaAuditWorkingD1 : iaAuditWorkingD1List) {
			iaAuditWorkingD1Save = new IaAuditWorkingD1();
			iaAuditWorkingD1Save = iaAuditWorkingD1;
			iaAuditWorkingD1Save.setAuditWorkingNo(auditIncNo);
			iaAuditWorkingD1Save.setAuWorkingMonth(dateStr);
			iaAuditWorkingD1Save.setAuOfficeCode(offCode);
			iaAuditWorkingD1Repository.save(iaAuditWorkingD1Save);
		}
	}

	public Int091201ViewFullDetailVo viewFulldetail(Int091201ViewFullDetailRequstVo int091201ViewFullDetailRequstVo) {
		Int091201ViewFullDetailVo response = new Int091201ViewFullDetailVo();
		List<Int091201DayDetailVo> dayDetailList = new ArrayList<>();
		List<Int091201LineDetail> lineDetailList = new ArrayList<>();
		Int091201DayDetailVo day = null;
		Int091201LineDetail lineData = new Int091201LineDetail();
		Calendar startMonth = Calendar.getInstance();
		Calendar endMonth = Calendar.getInstance();
		int year = Integer.parseInt(int091201ViewFullDetailRequstVo.getYearMonth().substring(0, 4));
		int month = Integer.parseInt(int091201ViewFullDetailRequstVo.getYearMonth().substring(4, 6));
		startMonth.set(year, month, 1);
		endMonth.set(year, month + 1, 1);
		endMonth.add(Calendar.DATE, -1);
		String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday" };
		while (startMonth.compareTo(endMonth) == -1) {
			day = new Int091201DayDetailVo();
			day.setDayOfMonth(startMonth.get(Calendar.DAY_OF_MONTH));
			day.setDayOfweek(strDays[startMonth.get(Calendar.DAY_OF_WEEK)]);
			dayDetailList.add(day);
		}
		List<IaEmpWorkingDtl> empList = int091201JdbcRepository.findUsernameWorkByOfficeCodeAndWorkMonth(int091201ViewFullDetailRequstVo);
		Int091201ViewFullDetailRequstVo val;
		for (IaEmpWorkingDtl iaEmpWorkingDtl : empList) {
			lineData = new Int091201LineDetail();
			lineData.setUserLogin(iaEmpWorkingDtl.getUserLogin());
			lineData.setUsername(iaEmpWorkingDtl.getUserName());

			val = new Int091201ViewFullDetailRequstVo();
			val.setOfficeCode(int091201ViewFullDetailRequstVo.getOfficeCode());
			val.setYearMonth(int091201ViewFullDetailRequstVo.getYearMonth());
			val.setUserLogin(iaEmpWorkingDtl.getUserLogin());
			List<IaEmpWorkingDtl> empWorkingDtlList = int091201JdbcRepository.findIaEmpWorkingDtlBy(val);
			for (Int091201DayDetailVo inLineData : dayDetailList) {
				for (IaEmpWorkingDtl fieldData : empWorkingDtlList) {
					Calendar date = Calendar.getInstance();
					date.setTime(fieldData.getWorkingDate());
					if (inLineData.getDayOfMonth().equals(date.get(Calendar.DAY_OF_MONTH))) {

					}
				}
			}
		}

		return response;
	}
}
