package src.rule;

import org.springframework.stereotype.Component;
import src.model.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihaixin on 2017/7/4.
 */
@Component
public class PasswordHardCode {

    private int lineNum = 0;

    public List<Result> pswordHardScan(File dir) {
        File[] files = dir.listFiles();
        List<Result> lineNubs = new ArrayList<Result>();
        Pattern pattern = Pattern.compile("(?i)password =\"[\\s\\S]*\"");

        for (int i = 0; i < files.length; i++) {
            try {
                if (files[i].getName().endsWith(".java")) {
                    BufferedReader br = null;
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(files[i])));
                    String lineContent = "";
                    while ((lineContent = br.readLine()) != null) {
                        Matcher matcher = pattern.matcher(lineContent);
                        if (matcher.find()) {
                            Result res = new Result();
                            res.setBugType("PasswordHardCode");
                            res.setFileName(files[i].getName());
                            res.setFileDirt(files[i].getAbsolutePath());
                            res.setLineNumber(lineNum + 1);
                            res.setDescription("硬编码了密码，系统存在风险.");
                            lineNubs.add(res);
                        }
                        lineNum++;
                        System.out.println("line number is :" + lineNum);
                    }

                } else {
                    continue;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return lineNubs;


    }
}
