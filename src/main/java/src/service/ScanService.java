package src.service;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.stereotype.Service;
import src.model.Result;
import src.rule.RuleDefinition;
import src.utils.JdtAstUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by chenyu on 2017/6/26.
 */
@Service
public class ScanService implements Serializable {

    private String serialVersionUI;
    private String name2;
    // private List<Result> errorList = new ArrayList<>();

    /* 指定文件路径，采用ast方式访问*/
    public List<Result> visitorFile(String path, List<Result> errorList, List<String> ruleList) {
        File file = new File(path);
        if (file.isFile()) {
            //System.out.println(file.getName());
            CompilationUnit comp = JdtAstUtil.getCompilationUnit(path);
            if (ruleList.contains(RuleDefinition.SERIALIZABLE.getCode().toString())) {
                SpyVisitor visitor = new SpyVisitor(file.getName(), file.getPath());
                comp.accept(visitor);
                errorList.addAll(visitor.getResultList());
            }
            file.delete();
        } else {
            Arrays.stream(file.listFiles()).forEach(childfile -> visitorFile(childfile.getPath(), errorList, ruleList));
        }
        return errorList;
    }

/*    public static void main(String[] args) {
        List<Result> info = visitorFile("D:\\workspace\\spy\\src");
        info.stream().forEach(result -> {
            System.out.println("illeage error: line=" + result.getLineNumber() +
                    "\n filePath=" + result.getFileDirt() +
                    "\n fileName=" + result.getFileName() +
                    "\n bugType=" + result.getBugType() +
                    "\n desc=" + result.getDescription());
        });

    }*/

}
