package src.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import src.model.ScanFile;
import src.service.ExportResultService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 2017/7/3.
 */
@Controller
public class FileController {

    @Resource
    private ExportResultService exportResultService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public List<ScanFile> scan(HttpServletRequest request, ModelMap modelMap) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("file_upload");
        List<ScanFile> resultList = new ArrayList<>();
        fileList.forEach(multipartFile -> {
            String filename = multipartFile.getOriginalFilename();
            String tmppath = System.getProperty("user.home") + "/upload/" + filename;
            File tmpFile = new File(tmppath);
            try {
                multipartFile.transferTo(tmpFile);
                resultList.add(new ScanFile(tmpFile.getPath(), tmpFile.getName()));
                modelMap.put("scanResult",resultList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return resultList;
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletRequest request, ModelMap modelMap) {

        System.out.println("aaa");
        //exportResultService.createExcel(list);

    }
}
