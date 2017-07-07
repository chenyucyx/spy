package src.rule;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import src.model.Visitor;

/**
 * Created by chenyu on 2017/7/6.
 */
public class MethodInvocationVisitor extends Visitor {

    public MethodInvocationVisitor(String fileName, String filePath) {
        super(fileName, filePath);
    }

    public MethodInvocationVisitor() {
    }

    @Override
    public boolean visit(MethodInvocation node) {
      //  System.out.println("METHODINVOCATION=" + node.getParent().getParent().getNodeType());
        if (node.toString().contains("Pattern.matches")) {
            ASTNode astNode = node;
            while (ASTNode.METHOD_DECLARATION != astNode.getNodeType()) {
                //System.out.println("astNode.type="+astNode.getNodeType());
                astNode = astNode.getParent();
                //System.out.println("astNode.parent="+node.getParent().getParent());
            }
            MethodVisitor methodVisitor =new MethodVisitor();
            astNode.accept(methodVisitor);
            this.getResultList().addAll(methodVisitor.getResultList());
        }
        return true;
    }
}
