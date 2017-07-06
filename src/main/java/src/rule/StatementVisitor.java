package src.rule;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ExpressionStatement;

/**
 * Created by chenyu on 2017/7/5.
 */
public class StatementVisitor extends ASTVisitor {

    @Override
    public boolean visit(ExpressionStatement node) {
        System.out.println("ExpressionStatementType:" + node.getExpression().getNodeType());
        if (ASTNode.ASSIGNMENT == node.getNodeType()) {
            node.accept(new AssignmentVisitor());
        }
        return true;
    }


}
