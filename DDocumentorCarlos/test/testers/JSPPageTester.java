package testers;

import java.io.IOException;
import org.ddocumentor.files.jsp.JSPFile;
import org.ddocumentor.files.JavaFile;
import org.ddocumentor.files.javaclass.Instruction;
import expectations.println.ExpectedInstruction;
import expectations.println.PrintlnExpectedPage;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author carlos
 */
public class JSPPageTester {

    public static void canCreatePrintlnJSPPage(JavaFile actualJavaFile) {

        JSPFile actualJspPage = new JSPFile();
        actualJspPage.setFromJavaFile(actualJavaFile, "web");

        PrintlnExpectedPage expectedJspPage = new PrintlnExpectedPage();

        assertThat(actualJspPage.getPath().toString(),
                is(equalTo(expectedJspPage.getPath().toString())));

        assertThat(actualJspPage.getFileName(),
                is(equalTo(expectedJspPage.getFileName())));

        assertThat(actualJspPage.getTitle(),
                is(equalTo(expectedJspPage.getTitle())));

        assertThat(actualJspPage.getHTMLDivs().get(0).getId(),
                is(equalTo(expectedJspPage.getHTMLDivs().get(0).getId())));

        assertThat(actualJspPage.getHTMLDivs().get(0).getContent(),
                is(equalTo(expectedJspPage.getHTMLDivs().get(0).getContent())));

        assertThat(actualJspPage.getHTMLDivs().get(1).getId(),
                is(equalTo(expectedJspPage.getHTMLDivs().get(1).getId())));

        assertThat(actualJspPage.getHTMLDivs().get(1).getContent(),
                is(equalTo(expectedJspPage.getHTMLDivs().get(1).getContent())));
    }

    public static void canGeneratePrintlnJSPPage(JavaFile actualJavaFile)
        throws IOException {

        JSPFile actualJspPage = new JSPFile();
        actualJspPage.setFromJavaFile(actualJavaFile, "web");
        actualJspPage.generateJSPFile();


        PrintlnExpectedPage expectedJspPage = new PrintlnExpectedPage();

        assertThat(actualJspPage.getFileName(),
                is(equalTo(expectedJspPage.getFileName())));

        assertThat(actualJspPage.getTitle(),
                is(equalTo(expectedJspPage.getTitle())));

        assertThat(actualJspPage.getHTMLDivs().get(0).getId(),
                is(equalTo(expectedJspPage.getHTMLDivs().get(0).getId())));

        assertThat(actualJspPage.getHTMLDivs().get(0).getContent(),
                is(equalTo(expectedJspPage.getHTMLDivs().get(0).getContent())));

        assertThat(actualJspPage.getHTMLDivs().get(1).getId(),
                is(equalTo(expectedJspPage.getHTMLDivs().get(1).getId())));

        assertThat(actualJspPage.getHTMLDivs().get(1).getContent(),
                is(equalTo(expectedJspPage.getHTMLDivs().get(1).getContent())));
    }


}
