package src.rule;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import src.model.Visitor;

/**
 * Created by chenyu on 2017/7/5.
 */
public class AssignmentVisitor extends Visitor {

    @Override
    public boolean visit(Assignment node) {
        Expression rightExpression = node.getRightHandSide();
        System.out.println("表达式右侧为：" + rightExpression);
        return true;
    }



}
