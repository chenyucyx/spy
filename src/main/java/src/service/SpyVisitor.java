package src.service;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.util.Arrays;
import java.util.List;


/**
 * Created by chenyu on 2017/6/27.
 */
public class SpyVisitor extends ASTVisitor {
    @Override
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
            /* 放入map存在的错误信息 */
        }
        return serial;
    }

/*    @Override
    public boolean visit(MethodDeclaration node) {
        System.out.println("Method:\t" + node.getName());
        return true;
    }*/


    @Override
    public boolean visit(TypeDeclaration node) {
        System.out.println("Class:\t" + node.getName() + "\t implment class:" + node.superInterfaceTypes());
        if (null != node.superInterfaceTypes() && node.superInterfaceTypes().stream().anyMatch(type -> type.toString().equals("Serializable"))) {
            return true;
        }
        return false;
    }



}
