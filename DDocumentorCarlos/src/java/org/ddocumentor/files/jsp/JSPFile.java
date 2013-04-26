package org.ddocumentor.files.jsp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.ddocumentor.files.DDocumentorFile;
import org.ddocumentor.files.JavaFile;
import org.ddocumentor.files.javaclass.JavaMethod;

/**
 *
 * @author carlos
 */
public class JSPFile extends DDocumentorFile {

    private String title;
    private List<HTMLDiv> htmlDivs;

    public JSPFile() {
    }
    
    public JSPFile(String fileName, String title) {
        init(fileName, title);
    }

    private void init(String fileName, String title) {
        setFileName(fileName);
        setTitle(title);
    }

    //gets
    public String getTitle() {
        return this.title;
    }

    public List<HTMLDiv> getHTMLDivs() {
        return this.htmlDivs;
    }

    //sets
    public void setTitle(String title) {
        this.title = title;
    }

    public void setHTMLDivs(List<HTMLDiv> htmlDivs) {
        this.htmlDivs = htmlDivs;
    }

    //general methods
    public void setFromJavaFile(JavaFile javaFile, String targetFolder) {


        JavaMethod exampleMethod = javaFile
                .getJavaClasses().get(0)
                .getMethods().get(0)
                .getInstructions().get(0)
                .getIsExampleOf().get(0);

        String capitalizedExampleName = StringUtils.capitalize(
                exampleMethod.getName());
       
        setFileName("DocumentedExampleOf"
                  + capitalizedExampleName
                  + ".jsp");

        setFileSubPath(targetFolder);

        this.title = "";

        HTMLDiv exampleTitleDiv = new HTMLDiv("exampletitle");
        exampleTitleDiv.setContent(
                "<h1>Official Example of: "+ capitalizedExampleName + "</h1>");

        HTMLDiv exampleDiv = new HTMLDiv("example");
        exampleDiv.setContent(
                "<h3>Example:</h3>\n"
               +"<br>\n"
               +javaFile
                .getJavaClasses().get(0)
                .getMethods().get(0)
                .getInstructions().get(0).getText());

        htmlDivs = new ArrayList<HTMLDiv>();
        htmlDivs.add(exampleTitleDiv);
        htmlDivs.add(exampleDiv);
    }

    public void generateJSPFile() throws IOException {

        StringBuilder contentsSB = new StringBuilder();

        contentsSB
                .append("<%@ page contentType=\"text/html;charset=UTF-8\"")
                .append("language=\"java\" %>\n\n")
                .append("<html>\n\n\n")
                .append("<body>\n\n")

                .append("<div id=\"").append(htmlDivs.get(0).getId())
                    .append("\">\n")
                    .append(htmlDivs.get(0).getContent())
                .append("\n</div>\n\n")

                .append("<hr>\n\n")
                
                .append("<div id=\""+ htmlDivs.get(1).getId() +"\">\n")
                .append(htmlDivs.get(1).getContent())
                .append("\n</div>\n\n")

                .append("</body>\n")
                .append("</html>");

        Files.write(getPath(), contentsSB.toString().getBytes(),
                StandardOpenOption.CREATE);

    }

    
}
