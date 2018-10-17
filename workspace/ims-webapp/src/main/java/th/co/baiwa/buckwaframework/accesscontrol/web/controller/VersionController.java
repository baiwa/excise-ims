package th.co.baiwa.buckwaframework.accesscontrol.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/version")
public class VersionController {
	private static final Logger logger = LoggerFactory.getLogger(VersionController.class);
}