package expectations.println;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import org.ddocumentor.files.jsp.HTMLDiv;
import org.ddocumentor.files.jsp.JSPFile;

/**
 *
 * @author carlos
 */
public class PrintlnExpectedPage extends JSPFile {

    public PrintlnExpectedPage() {
        
        super("DocumentedExampleOfPrintln.jsp", "");

        setPath(FileSystems.getDefault().getPath(
                "web",
                "DocumentedExampleOfPrintln.jsp"));

        List<HTMLDiv> divs = new ArrayList<HTMLDiv>();

        HTMLDiv exampleTitleDiv = new HTMLDiv("exampletitle");
        exampleTitleDiv.setContent(
                "<h1>Official Example of: Println</h1>");

        HTMLDiv exampleDiv = new HTMLDiv("example");
        exampleDiv.setContent(
                "<h3>Example:</h3>\n" +
                "<br>\n" +
                "System.out.println(22.35);");

        divs.add(exampleTitleDiv);
        divs.add(exampleDiv);

        super.setHTMLDivs(divs);
    }


}
