package src.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import src.rule.RuleDefinition;
import src.service.RuleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by chenyu on 2017/6/26.
 */
@Controller
public class IndexController {

    @Resource
    private RuleService ruleService;

    @RequestMapping(value = "/")
    public String home(ModelMap modelMap) {
        List<RuleDefinition> ruleDefinitionList = ruleService.queryAllRules();
        modelMap.put("ruleList", ruleDefinitionList);
        return "index";
    }


}
