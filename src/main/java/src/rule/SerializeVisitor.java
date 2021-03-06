package src.rule;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import src.model.Result;
import src.model.RuleDefinition;
import src.model.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by chenyu on 2017/6/27.
 */
public class SerializeVisitor extends Visitor {

    public SerializeVisitor(String fileName, String filePath) {
        super(fileName, filePath);
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
                result.setFileDirt(this.getFilePath());
                result.setBugType(RuleDefinition.SERIALIZABLE.getCode().toString());
                result.setDescription("增加变量serialVersionUID");
                result.setFileName(this.getFileName());
                this.getResultList().add(result);
            }
        }
        return true;
    }


}
