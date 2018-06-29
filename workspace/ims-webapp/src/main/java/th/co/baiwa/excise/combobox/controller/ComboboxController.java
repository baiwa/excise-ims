package th.co.baiwa.excise.combobox.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.combobox.entity.Combobox;
import th.co.baiwa.excise.combobox.service.ComboboxService;

@Controller
@RequestMapping("combobox/controller")
public class ComboboxController {
	
	private Logger logger = LoggerFactory.getLogger(ComboboxController.class);
	
	@Autowired
	private ComboboxService comboboxService;
	
	@PostMapping("/comboboxHeaderQuestionnaire")
	@ResponseBody
	public List<Combobox> comboboxHeaderQuestionnaire() {
		logger.info("get comboboxHeaderQuestionnaire");
		List<Combobox> conboboxList = comboboxService.findQuestionnaireHeader();
		return conboboxList;
	}

}
