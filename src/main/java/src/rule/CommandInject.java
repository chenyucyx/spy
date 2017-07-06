package src.rule;

import org.springframework.stereotype.Component;
import src.model.Result;
import src.model.RuleDefinition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihaixin on 2017/6/29.
 */
@Component
public class CommandInject {
    private int lineNum = 0;
    public List<Result> commandScan(File dir){
        Charset charset = Charset.forName("GB18030");
        CharsetDecoder decoder = charset.newDecoder();
        File[] files = dir.listFiles();
        List<Result> lineNubs= new ArrayList<Result>();
        Pattern pattern = Pattern.compile(".exec\\(\"[\\s\\S]*\"\\)");

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                commandScan(files[i]);
            } else {
                // System.out.println(fs[i].getAbsolutePath());
                try {
                    if (files[i].getName().endsWith(".java")) {
                        BufferedReader br = null;
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(files[i])));
                        //BufferedReader br = new BufferedReader(new FileReader(files[i]));
                        String lineContent="";
                        while ((lineContent=br.readLine()) != null) {

                            System.out.println(lineContent);

                            Matcher matcher = pattern.matcher(lineContent);
                            if(matcher.find()) {
                                Result res= new Result();
                                System.out.println("match"+lineContent);
                                res.setBugType(RuleDefinition.COMMANDINJECT.getCode().toString());
                                res.setFileName(files[i].getName());
                                res.setFileDirt(files[i].getAbsolutePath());
                                res.setLineNumber(lineNum+1);
                                res.setDescription("This line have command inject risk.");
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
    public static void main(String []args) throws Exception{


       //String dir= "D:\\tmp\\command.java";
        File dir = new File("D:\\tmp");
        //commandScan(dir);
    }
}
