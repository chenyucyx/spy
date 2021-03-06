package src.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import src.model.Result;
import src.service.ScanService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chenyu on 2017/6/26.
 */
@Controller
public class ScanController {

    @Resource
    private ScanService scanService;

    @RequestMapping(value = "/scan", method = RequestMethod.POST)
    @ResponseBody
    public List<Result> scan(HttpServletRequest request, ModelMap modelMap) {
        String rules = request.getParameter("rules");
        List<String> ruleList = Stream.of(rules.split(",")).collect(Collectors.toList());
        String tmppath = System.getProperty("user.home") + "/upload/";
        List<Result> resultList = new ArrayList<>();
        return scanService.visitorFile(tmppath, resultList, ruleList);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
    }


}
