package jblog.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private static final Log LOG = LogFactory.getLog(BlogController.class);

    @RequestMapping("/main")
    public String index() {
//        LOG.debug("main debug-level");
        return "main/index";
    }
}
