package src.rule;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import src.model.Visitor;

/**
 * Created by chenyu on 2017/7/6.
 */
public class BlockVisitor extends Visitor {


    @Override
    public boolean visit(Block node) {

        return true;
    }
}
