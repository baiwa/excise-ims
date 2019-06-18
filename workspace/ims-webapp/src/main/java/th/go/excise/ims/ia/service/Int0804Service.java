package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.repository.IaGfmovementAccountRepository;
import th.go.excise.ims.ia.vo.Int0804DateVo;
import th.go.excise.ims.ia.vo.Int0804HeaderTable;
import th.go.excise.ims.ia.vo.Int0804SearchVo;
import th.go.excise.ims.ia.vo.Int0804SummaryVo;
import th.go.excise.ims.ia.vo.Int0804Vo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepaccMasRepository;

@Service
public class Int0804Service {
	@Autowired
	private ExciseDepaccMasRepository exciseDepaccMasRepository;

	@Autowired
	private IaGfmovementAccountRepository iaGfmovementAccountRepository;

	public List<ExciseDepaccMas> getDepaccMasDropdown() {
		return exciseDepaccMasRepository.getDepaccMasDropdown();
	}

	public Int0804Vo findByCondition(Int0804SearchVo request) {
		Int0804Vo response = new Int0804Vo();
		/* _________ set request _________ */
		request.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(request.getOfficeCode()));
		request.setDateFrom(ConvertDateUtils.formatDateToString(
				ConvertDateUtils.parseStringToDate(request.getDateFrom(), ConvertDateUtils.MM_YYYY),
				ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN));
		request.setDateTo(ConvertDateUtils.formatDateToString(
				ConvertDateUtils.parseStringToDate(request.getDateTo(), ConvertDateUtils.MM_YYYY),
				ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN));

		List<Int0804SummaryVo> summaryList = iaGfmovementAccountRepository.getResultByconditon(request);

		/* _________ set unique header table _________ */
		Set<String> keyDate = new TreeSet<String>();
		Set<String> keyHeadTable = new TreeSet<String>();

		// HashSet<Int0804SummaryVo> uniqueHeaderTable = new HashSet<>(summaryList);
		for (Int0804SummaryVo header : summaryList) {
			keyDate.add(header.getDateDefault());
			if (header.getGfExciseCode() != null) {
				keyHeadTable.add(header.getGfExciseCode());
			}
		}

		// Map<String, String> mapValue = new HashMap<>();
		// for (Int0804SummaryVo vo : summaryList) {
		// mapValue.put(vo.getDateDefault() + vo.getGfExciseCode(),
		// vo.getSumCarryForward());
		// }

		List<Int0804HeaderTable> headerTable = new ArrayList<Int0804HeaderTable>();
		Int0804HeaderTable th = null;
		/* _________ loop set header table _________ */
		for (String officeCode : keyHeadTable) {
			th = new Int0804HeaderTable();
			th.setArea(ApplicationCache.getExciseDepartment(officeCode).getDeptName());
			th.setGfDepositCode(officeCode);
			headerTable.add(th);
		}

		/* _________ loop set data _________ */
		List<Int0804DateVo> daysList = new ArrayList<Int0804DateVo>();
		Int0804DateVo days = null;
		List<Int0804SummaryVo> daysFilter = null;
		for (String key : keyDate) {
			days = new Int0804DateVo();
			daysFilter = new ArrayList<Int0804SummaryVo>();

			daysFilter = summaryList.stream().filter(obj -> obj.getDateDefault().equals(key))
					.collect(Collectors.toList());
			for (Int0804SummaryVo f : daysFilter) {
				f.setDateDefault(
						ConvertDateUtils.formatDateToString(
								ConvertDateUtils.parseStringToDate(f.getDateDefault(), ConvertDateUtils.YYYYMMDD,
										ConvertDateUtils.LOCAL_EN),
								ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			}
			days.setDay(daysFilter);
			daysList.add(days);
		}
		response.setTh(headerTable);
		response.setDateList(daysList);

		return response;
	}
}
