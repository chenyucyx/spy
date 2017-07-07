package src.model;

import org.eclipse.jdt.core.dom.ASTVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 2017/7/7.
 */
public class Visitor extends ASTVisitor {

    private List resultList = new ArrayList<Result>();

    private String fileName;

    private String filePath;

    public Visitor(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Visitor() {
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
