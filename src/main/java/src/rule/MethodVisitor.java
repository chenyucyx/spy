package src.rule;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import src.model.Result;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenyu on 2017/6/27.
 */
public class MethodVisitor extends ASTVisitor {

    private List resultList = new ArrayList<Result>();

    private String fileName;

    private String filePath;


    @Override
    public boolean visit(MethodDeclaration node) {
        System.out.println("Method:\t" + node.getName());
        SingleVariableDeclaration variableDeclaration = (SingleVariableDeclaration) node.parameters().get(0);
        Block block = node.getBody();
        List statements = block.statements();
        //System.out.println("statements:" + statements);
        for (int i = 0; i < statements.size(); i++) {

            Statement statement = (Statement) statements.get(i);
            //System.out.println("=======TYPE="+statement.getNodeType());
            if (ASTNode.IF_STATEMENT == statement.getNodeType()) {
                IfStatement ifStatement = (IfStatement) statement;

                //System.out.println("IF="+ifStatement.getThenStatement());
            }

            if (ASTNode.VARIABLE_DECLARATION_STATEMENT == statement.getNodeType()) {
                //statement=(ExpressionStatement)statement;
               // System.out.println("VARIABLE_DECLARATION_STATEMENT:" + statement);
            }

            if (ASTNode.EXPRESSION_STATEMENT == statement.getNodeType()) {
                //System.out.println("EXPRESSION_STATEMENT="+statement.toString());
                ((ASTNode) statement).accept(new StatementVisitor());

            }

        }
        return true;
    }


    public MethodVisitor(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public MethodVisitor() {
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
