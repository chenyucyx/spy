package src.rule;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import src.model.Result;
import src.model.RuleDefinition;
import src.model.Visitor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenyu on 2017/6/27.
 */
public class MethodVisitor extends Visitor {

    @Override
    public boolean visit(MethodDeclaration node) {
        //System.out.println("Method:\t" + node.getName());
        List<SingleVariableDeclaration> paramList = (List<SingleVariableDeclaration>) node.parameters();
        Block block = node.getBody();
        List statements = block.statements();
        for (int i = 0; i < statements.size(); i++) {
            Statement statement = (Statement) statements.get(i);
            //System.out.println("=======TYPE="+statement.getNodeType());
            paramList.forEach(v -> {
                if (statement.toString().contains(v.getName().toString())
                        && statement.toString().contains("Pattern.matches")) {
                    Result result = new Result();
                    result.setLineNumber(node.getStartPosition());
                    result.setFileDirt(this.getFilePath());
                    result.setBugType(RuleDefinition.REGULAINJECT.getCode().toString());
                    result.setDescription(RuleDefinition.REGULAINJECT.getDesc());
                    result.setFileName(this.getFileName());
                    this.getResultList().add(result);
                }
            });
        }
        return true;
    }

}
