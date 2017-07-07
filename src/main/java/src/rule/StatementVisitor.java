package src.rule;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import src.model.Visitor;

/**
 * Created by chenyu on 2017/7/5.
 */
public class StatementVisitor extends Visitor {


    @Override
    public boolean visit(ExpressionStatement node) {
      //  System.out.println("ExpressionStatementType:" + node.getExpression().getNodeType());
        if (ASTNode.ASSIGNMENT == node.getExpression().getNodeType()) {
            node.accept(new AssignmentVisitor());
        }
        if (ASTNode.METHOD_INVOCATION == node.getExpression().getNodeType()) {
            node.accept(new MethodInvocationVisitor());
        }
        return true;
    }


}
