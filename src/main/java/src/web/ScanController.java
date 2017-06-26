package src.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenyu on 2017/6/26.
 */
@Controller
public class ScanController {

    @RequestMapping(value = "/scan")
    public String scan(ModelMap modelMap) {
        modelMap.addAttribute("message", "hello,world!");
        return "test";
    }
}
