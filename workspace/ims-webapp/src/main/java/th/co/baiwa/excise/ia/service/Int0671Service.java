package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.TimeSet;
import th.co.baiwa.excise.ia.persistence.repository.TimeSetRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0671FormVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Service
public class Int0671Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TimeSetRepository timeSetRepository;

	public CommonMessage<List<Int0671FormVo>> queryInit() {
		CommonMessage<List<Int0671FormVo>> response = new CommonMessage<>();
		Date date = new Date();

		// return response data
		List<TimeSet> dataAll = new ArrayList<TimeSet>();
		List<Int0671FormVo> result = new ArrayList<Int0671FormVo>();
		Int0671FormVo t;
		dataAll = timeSetRepository.oderById();
		for (TimeSet obj : dataAll) {
			t = new Int0671FormVo();
			t.setTimeSetId(obj.getTimeSetId());
			t.setCreatedBy(obj.getCreatedBy());
			t.setCreatedDate(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getCreatedDate()));
			t.setStatus(obj.getStatus());
			t.setStartDateTime(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getStartDateTime()));
			t.setEndDateTime(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getEndDateTime()));

			result.add(t);
		}

		response.setData(result);
		return response;
	}

	public CommonMessage<List<Int0671FormVo>> saveTime(Int0671FormVo form) {
		logger.info("stratTime : {}, endTime : {}", form.getStartDateTime(), form.getEndDateTime());
		Message msg = null;
		CommonMessage<List<Int0671FormVo>> response = new CommonMessage<>();
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		TimeSet d;

		// check count status = '1'(open)

		if (BeanUtils.isNotEmpty(form.getStartDateTime()) && BeanUtils.isNotEmpty(form.getEndDateTime())) {

			Date start = DateConstant.convertStringDDMMYYYYHHmmToDate(form.getStartDateTime());
			Date end = DateConstant.convertStringDDMMYYYYHHmmToDate(form.getEndDateTime());
			int numOpen = timeSetRepository.countOverlap(start, end);
			if (numOpen == 0) {
				d = new TimeSet();
				d.setStartDateTime(start);
				d.setEndDateTime(end);
				timeSetRepository.save(d);
				msg = ApplicationCache.getMessage("MSG_00002");
			} else {
				msg = ApplicationCache.getMessage("MSG_00003");
			}

		} else {
			msg = ApplicationCache.getMessage("MSG_00003");
		}

		List<TimeSet> dataAll = new ArrayList<TimeSet>();
		List<Int0671FormVo> result = new ArrayList<Int0671FormVo>();
		Int0671FormVo t;
		dataAll = timeSetRepository.oderById();
		for (TimeSet obj : dataAll) {
			t = new Int0671FormVo();
			t.setTimeSetId(obj.getTimeSetId());
			t.setCreatedBy(obj.getCreatedBy());
			t.setCreatedDate(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getCreatedDate()));
			t.setStatus(obj.getStatus());
			t.setStartDateTime(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getStartDateTime()));
			t.setEndDateTime(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getEndDateTime()));

			result.add(t);
		}

		// return message

		response.setMsg(msg);
		response.setData(result);
		return response;
	}

	public CommonMessage<List<Int0671FormVo>> updateTime(Int0671FormVo vo) {
		Message msg = null;
		CommonMessage<List<Int0671FormVo>> response = new CommonMessage<>();
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		TimeSet findData = new TimeSet();

		if (BeanUtils.isNotEmpty(vo)) {
			// covert string to date
			Date start = DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getStartDateTime());
			Date end = DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getEndDateTime());

			int numOpen = timeSetRepository.countOverlapNotCurrentID(start, end, vo.getTimeSetId());
			if (numOpen == 0) {
				findData = timeSetRepository.findOne(vo.getTimeSetId());
				findData.setStartDateTime(start);
				findData.setEndDateTime(end);
				findData.setUpdatedBy(user);
				findData.setUpdatedDate(date);
				timeSetRepository.save(findData);
				msg = ApplicationCache.getMessage("MSG_00002");
			}else {
				msg = ApplicationCache.getMessage("MSG_00003");
			}
		}

		// return response data
		List<TimeSet> dataAll = new ArrayList<TimeSet>();
		List<Int0671FormVo> result = new ArrayList<Int0671FormVo>();
		Int0671FormVo t;
		dataAll = timeSetRepository.oderById();
		for (TimeSet obj : dataAll) {
			t = new Int0671FormVo();
			t.setTimeSetId(obj.getTimeSetId());
			t.setCreatedBy(obj.getCreatedBy());
			t.setCreatedDate(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getCreatedDate()));
			t.setStatus(obj.getStatus());
			t.setStartDateTime(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getStartDateTime()));
			t.setEndDateTime(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getEndDateTime()));

			result.add(t);
		}

		response.setMsg(msg);
		response.setData(result);
		return response;
	}

	public CommonMessage<List<Int0671FormVo>> deleteTime(Int0671FormVo vo) {
		Message msg = ApplicationCache.getMessage("MSG_00006");
		;
		CommonMessage<List<Int0671FormVo>> response = new CommonMessage<>();

		if (BeanUtils.isNotEmpty(vo.getTimeSetId())) {
			timeSetRepository.delete(vo.getTimeSetId());
			msg = ApplicationCache.getMessage("MSG_00005");
		}

		// return response data
		List<TimeSet> dataAll = new ArrayList<TimeSet>();
		List<Int0671FormVo> result = new ArrayList<Int0671FormVo>();
		Int0671FormVo t;
		dataAll = timeSetRepository.oderById();
		for (TimeSet obj : dataAll) {
			t = new Int0671FormVo();
			t.setTimeSetId(obj.getTimeSetId());
			t.setCreatedBy(obj.getCreatedBy());
			t.setCreatedDate(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getCreatedDate()));
			t.setStatus(obj.getStatus());
			t.setStartDateTime(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getStartDateTime()));
			t.setEndDateTime(DateConstant.convertDateToStrDDMMYYYYHHmm(obj.getEndDateTime()));

			result.add(t);
		}

		response.setMsg(msg);
		response.setData(result);
		return response;
	}

}
