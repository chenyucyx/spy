package src.rule;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.osgi.service.component.annotations.Component;
import src.model.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by lihaixin on 2017/7/5.
 */
@Component
public class SpringBootSpELInject {

    public List<Result> springSpELScan(File dir){
        File[] files = dir.listFiles();
        List<Result> lineNubs= new ArrayList<Result>();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                springSpELScan(files[i]);
            } else {
                try {
                    if (files[i].getName().equals("pom.xml")) {
                        String pomstring =files[i].getAbsolutePath();
                        SAXReader reader = new SAXReader();

                        try {
                            Document doc = reader.read(pomstring);
                            Element root = doc.getRootElement();
                            Element element;
                            Element element2;
                            for (Iterator h = root.elementIterator("build"); h.hasNext();) {
                                element = (Element) h.next();

                                for (Iterator j = element.elementIterator("plugin"); j
                                        .hasNext();) {
                                    element2 = (Element) j.next();
                                    if(element2.elementText("groupId").equals("org.springframework.boot")&& (element2.elementText("version").startsWith("1.3"))){
                                        Result res= new Result();

                                        res.setBugType("SpringBootSpELInject");
                                        res.setFileName(files[i].getName());
                                        res.setFileDirt(files[i].getAbsolutePath());
                                        res.setLineNumber(0);
                                        res.setDescription("The org springframework boot version is too low.you should update it");
                                        lineNubs.add(res);

                                    }
                                }
                            }
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }


                    }else {
                        continue;
                    }
                } catch (Exception e){

                }
            }
        }

        return lineNubs;


    }
}
