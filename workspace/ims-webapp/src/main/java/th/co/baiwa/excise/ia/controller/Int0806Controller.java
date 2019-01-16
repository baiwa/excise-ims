package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.Int0806Vo;
import th.co.baiwa.excise.ia.persistence.entity.MoneyCheck;
import th.co.baiwa.excise.ia.persistence.vo.Int0806FormSearchVo;
import th.co.baiwa.excise.ia.service.Int0806Service;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Controller
@RequestMapping("api/ia/int0806")

public class Int0806Controller {
	
	private Logger log = LoggerFactory.getLogger(Int084Controller.class);
	
	private static final String sessionDataInt086 = "sessionDataInt086";
	
	@Autowired
	private Int0806Service int0806Service;
	
	@PostMapping("/getValue1")
	@ResponseBody
	public List<Lov> getValue1(@RequestBody Lov en) {
		return int0806Service.getValue1(en);

	}
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Int0806Vo> search(Int0806FormSearchVo en) {
		return int0806Service.search(en);
	}
	
   @SuppressWarnings("unchecked")
@PostMapping("/save")
    @ResponseBody
    public CommonMessage<Long> save(@RequestBody List<Int0806Vo> int0806VoList,HttpServletRequest request){
		Long id = 0L;
		log.error("Save Int086Vo");
		CommonMessage<Long> message = new CommonMessage<Long>();
		List<Int0806Vo> list2 = new ArrayList<Int0806Vo>();
		try {
			HttpSession session = request.getSession();
			session.setAttribute(sessionDataInt086,int0806VoList);
			
			list2 = (List<Int0806Vo>)session.getAttribute(sessionDataInt086);
			
			log.error("save Int0806 list2.size() {} ",list2.size());
			message.setData(id);
		} catch (Exception e) {
			log.error("Error ! add ",e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}
   
	@SuppressWarnings("unchecked")
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int0806Vo> list(@ModelAttribute Int0806FormSearchVo formVo,HttpServletRequest request){
		DataTableAjax<Int0806Vo> list = new DataTableAjax<Int0806Vo>();
		List<Int0806Vo> list2 = new ArrayList<Int0806Vo>();
		try {
			HttpSession session = request.getSession();
			list2 = (List<Int0806Vo>)session.getAttribute(sessionDataInt086);
			list.setData((BeanUtils.isEmpty(list2))? new ArrayList<Int0806Vo>():list2);
			if (!BeanUtils.isEmpty(list2)) {
				list.setData(list2);
			}			
//				 list = int085Service.findAll(formVo);
		} catch (Exception e) {
			log.error("Error ! findAll",e);	
		}
		
		return list;
	}

}
