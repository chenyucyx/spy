package src.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenyu on 2017/6/26.
 */
@Controller
public class ScanController {

    @RequestMapping(value = "/scan")
    public String scan(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
/*//使用Apache文件上传组件处理文件上传步骤：
//1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
//2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
//解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
//3、判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
//按照传统方式获取数据
            return;
        }*/
        return "index";
    }
}
