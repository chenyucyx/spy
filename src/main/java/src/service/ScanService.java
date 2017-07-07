package src.service;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.stereotype.Service;
import src.model.Result;
import src.model.RuleDefinition;
import src.rule.CommandInject;
import src.rule.FilePathCanonical;
import src.rule.MethodInvocationVisitor;
import src.rule.MethodVisitor;
import src.rule.PasswordHardCode;
import src.rule.SerializeVisitor;
import src.rule.SpringBootSpELInject;
import src.rule.SqlInject;
import src.rule.StatementVisitor;
import src.rule.XXEInject;
import src.utils.JdtAstUtil;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by chenyu on 2017/6/26.
 */
@Service
public class ScanService {

    @Resource
    private CommandInject commandInject;

    @Resource
    private SqlInject sqlInject;

    @Resource
    private XXEInject xXEInject;

    @Resource
    private FilePathCanonical filePathCanonical;

    @Resource
    private PasswordHardCode passwordHardCode;

    @Resource
    private SpringBootSpELInject springBootSpELInject;

    /* 指定文件路径，采用ast方式访问*/
    public List<Result> visitorFile(String path, List<Result> errorList, List<String> ruleList) {
        File file = new File(path);
        if (file.isFile()) {
            //System.out.println(file.getName());
            CompilationUnit comp = JdtAstUtil.getCompilationUnit(path);
            if (ruleList.contains(RuleDefinition.SERIALIZABLE.getCode().toString())) {
                SerializeVisitor visitor = new SerializeVisitor(file.getName(), file.getPath());
                comp.accept(visitor);
                errorList.addAll(visitor.getResultList());
            } else if (ruleList.contains(RuleDefinition.COMMANDINJECT.getCode().toString())) {
                errorList.addAll(commandInject.commandScan(file));
            } else if (ruleList.contains(RuleDefinition.SQLINJECT.getCode().toString())) {
                errorList.addAll(sqlInject.sqlScan(file));
            } else if (ruleList.contains(RuleDefinition.XXEINJECT.getCode().toString())) {
                errorList.addAll(xXEInject.XXEInjectScan(file));
            } else if (ruleList.contains(RuleDefinition.FILENOTSECURE.getCode().toString())) {
                errorList.addAll(filePathCanonical.filePathScan(file));
            } else if (ruleList.contains(RuleDefinition.PASSWORDHARDCODE.getCode().toString())) {
                errorList.addAll(passwordHardCode.pswordHardScan(file));
            } else if (ruleList.contains(RuleDefinition.SPRINGBOOTSPELINJECT.getCode().toString())) {
                errorList.addAll(springBootSpELInject.springSpELScan(file));
            } else if (ruleList.contains(RuleDefinition.REGULAINJECT.getCode().toString())) {
                MethodInvocationVisitor visitor = new MethodInvocationVisitor(file.getName(), file.getPath());
                comp.accept(visitor);
                errorList.addAll(visitor.getResultList());
            }
            file.delete();
        } else {
            Arrays.stream(file.listFiles()).forEach(childfile -> visitorFile(childfile.getPath(), errorList, ruleList));
        }
        return errorList;
    }

    public static void main(String[] args) {
        CompilationUnit comp = JdtAstUtil.getCompilationUnit("D:\\workspace\\spy\\src\\main\\java\\src\\service\\ScanService.java");
        MethodInvocationVisitor methodInvocationVisitor = new MethodInvocationVisitor();
        String a = "", b = "";
        a = b + "fsfa";
        comp.accept(methodInvocationVisitor);
        //System.out.println(methodInvocationVisitor.getResultList().get(0));
       List<Result> list = methodInvocationVisitor.getResultList();
        list.stream().forEach(t->System.out.println(t.getDescription()+"|"+t.getBugType()));

    }

    public void testRegular(String regstr) {
       //String tmp = "sdf" + regstr;
        Pattern.matches(regstr, "sdf");
    }

}
