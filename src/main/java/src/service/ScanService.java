package src.service;

import org.eclipse.jdt.core.dom.CompilationUnit;
import src.utils.JdtAstUtil;

import java.io.Serializable;


/**
 * Created by chenyu on 2017/6/26.
 */
public class ScanService implements Serializable {

    private String serialVersionUID;
    private String name2;

    /* 指定文件路径，采用ast方式访问*/
    public static void visitorFile(String path) {
        CompilationUnit comp = JdtAstUtil.getCompilationUnit(path);
        SpyVisitor visitor = new SpyVisitor();
        comp.accept(visitor);
    }

    public static void main(String[] args) {
        visitorFile("D:\\workspace\\spy\\src\\main\\java\\src\\service\\ScanService.java");
    }

}
