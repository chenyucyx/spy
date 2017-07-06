package src.rule;

import org.osgi.service.component.annotations.Component;
import src.model.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by lihaixin on 2017/7/3.
 */
@Component
public class FilePathCanonical {
    private int lineNum = 0;

    public  List<Result> filePathScan(File dir){
        Charset charset = Charset.forName("GB18030");
        File[] files = dir.listFiles();
        List<Result> lineNubs= new ArrayList<Result>();
        Pattern pattern = Pattern.compile(".getAbsolutePath\\(\\)");

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                filePathScan(files[i]);
            } else {
                try {
                    if (files[i].getName().endsWith(".java")) {
                        BufferedReader br = null;
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(files[i])));
                        String lineContent="";
                        while ((lineContent=br.readLine()) != null) {

                            System.out.println(lineContent);

                            Matcher matcher = pattern.matcher(lineContent);
                            if(matcher.find()) {
                                Result res= new Result();
                                System.out.println("match"+lineContent);
                                res.setBugType("FilePathCanonical");
                                res.setFileName(files[i].getName());
                                res.setFileDirt(files[i].getAbsolutePath());
                                res.setLineNumber(lineNum+1);
                                res.setDescription("The File operation have secure risk.we suggest you use the getCanonicalPath method ");
                                lineNubs.add(res);
                            }
                            lineNum++;
                            System.out.println("line number is :"+lineNum);
                        }

                    } else {continue;}
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return lineNubs;


    }

}
