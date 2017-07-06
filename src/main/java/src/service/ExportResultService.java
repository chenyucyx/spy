package src.service;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import src.model.Result;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lihaixin on 2017/6/27.
 */
@Service
public class ExportResultService {

    public static final Logger logger= LoggerFactory.getLogger(ExportResultService.class);

    private Result result;
    //Excel构造方法(初始化信息) 
    @SuppressWarnings("unchecked")
    public void createExcel(List<Result> list){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("扫描结果");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setFillBackgroundColor(HSSFColor.LIGHT_YELLOW.index);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("文件名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("文件路径");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("BUG类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("所在行");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("详细描述");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
       // List list = List()<Result>;

        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            Result res = (Result) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue((String) res.getFileName());
            row.createCell((short) 1).setCellValue(res.getFileDirt());
            row.createCell((short) 2).setCellValue((String) res.getBugType());
            row.createCell((short) 2).setCellValue((int) res.getLineNumber());
            row.createCell((short) 2).setCellValue((String) res.getDescription());

        }
        // 第六步，将文件存到指定位置

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String resultName ="/result_output/"+dateFormat.format(date) + ".xls";
        try
        {
            FileOutputStream fout = new FileOutputStream(resultName);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}




