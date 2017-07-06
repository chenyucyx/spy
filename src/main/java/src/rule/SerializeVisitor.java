package src.rule;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import src.model.Result;
import src.model.RuleDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by chenyu on 2017/6/27.
 */
public class SerializeVisitor extends ASTVisitor {

    private List resultList = new ArrayList<Result>();

    private String fileName;

    private String filePath;

/*    @Override
    public boolean visit(FieldDeclaration node) {
        boolean serial = false;
        for (Object obj : node.fragments()) {
            VariableDeclarationFragment v = (VariableDeclarationFragment) obj;
            if (v.getName().toString().equals("serialVersionUID")) {
                serial = true;
                break;
            }
        }
        if (!serial) {
            *//* 放入map存在的错误信息 *//*
            errorMap.put("message", node.fragments());
        }
        System.out.println("message:" + node);
        return serial;
    }*/

/*    @Override
    public boolean visit(MethodDeclaration node) {
        System.out.println("Method:\t" + node.getName());
        return true;
    }*/

    public SerializeVisitor(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public boolean visit(TypeDeclaration node) {
       // System.out.println("Class:\t" + node.getName() + "\t implment class:" + node.superInterfaceTypes());
        if (null != node.superInterfaceTypes() && node.superInterfaceTypes().size() > 0
                && node.superInterfaceTypes().stream().anyMatch(type -> type.toString().equals("Serializable"))) {
            boolean match = false;
            FieldDeclaration[] fieldDeclarations = node.getFields();
            if (Arrays.stream(fieldDeclarations).anyMatch(fieldDeclaration ->
                    fieldDeclaration.fragments().stream().map(obj -> (VariableDeclarationFragment) obj)
                            .anyMatch(v -> v.toString().equals("serialVersionUID")))) {

                match = true;
            }
            if (!match) {
                Result result = new Result();
                result.setLineNumber(node.getStartPosition());
                result.setFileDirt(filePath);
                result.setBugType(RuleDefinition.SERIALIZABLE.getCode().toString());
                result.setDescription("增加变量serialVersionUID");
                result.setFileName(fileName);
                resultList.add(result);
            }
        }
        return true;
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
