package src.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenyu on 2017/6/26.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String home(ModelMap modelMap) {
        return "index";
    }


}
