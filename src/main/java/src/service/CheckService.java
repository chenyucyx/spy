package src.service;

import org.eclipse.jdt.core.dom.ASTNode;
import org.springframework.stereotype.Service;

/**
 * Created by chenyu on 2017/7/6.
 */
@Service
public class CheckService {

    public boolean checkParamInject(ASTNode paramNode, ASTNode node) {
        if (node.toString().contains(paramNode.toString())) {
            return true;
        }
        return false;
    }

}
