package org.ddocumentor.println;


import org.ddocumentor.files.JavaFile;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.ddocumentor.files.javaclass.Instruction;
import org.ddocumentor.files.javaclass.JavaClass;
import org.ddocumentor.files.javaclass.JavaMethod;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.ddocumentor.resources.sourcecode.println.
        CodeExampleOfPrintlnWithDouble;
import testers.InstructionTester;
import testers.JSPPageTester;



public class CanExtractExampleOfPrintlnWithDouble {

    private final static String printlnJspURL =
            "http://localhost:8080/DDocumentorCarlos";

    private final static String printlnJspFileName =
            "DocumentedExampleOfPrintln.jsp";

    private final static String expectedFileName =
            "CodeExampleOfPrintlnWithDouble.java";

    private final static String expectedFileSubPath =
            "src/java/org/ddocumentor/resources/sourcecode/println";

    private final static String expectedPackageDeclaration =
            "org.ddocumentor.resources.sourcecode.println";

    private final static String expectedJavaClassName =
            "CodeExampleOfPrintlnWithDouble";

    private final static String expectedJavaClassType =
            "public";

    private final static String expectedMethodName =
            "codeExample";

    private final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();

    @Before
    public void prepare() {
    }

    @Test
    public void isCodeExampleExecutable() {

        System.setOut(new PrintStream(outContent));
        CodeExampleOfPrintlnWithDouble codeExample =
                new CodeExampleOfPrintlnWithDouble();
        codeExample.codeExample();
        assertThat(outContent.toString(), containsString("22.35"));
    }

    @Test
    public void canSavePrintlnJavaFileInMemory()
            throws Exception {

        JavaFile javaFile =
                new JavaFile(expectedFileSubPath, expectedFileName);

        canSaveJavaFile(javaFile);

        canSaveJavaClasses(javaFile.getJavaClasses());

        canSaveJavaMethods(javaFile.getJavaClasses().get(0).getMethods());

        List<Instruction> instructions = javaFile.getJavaClasses().get(0)
                .getMethods().get(0).getInstructions();

        InstructionTester.canSavePrintlnInstruction(
                instructions.get(0).getText());

    }

    @Test
    public void canCreatePrintlnJSPPageFromJavaFile() throws Exception {

        JavaFile actualJavaFile =
                new JavaFile(expectedFileSubPath, expectedFileName);

        JSPPageTester.canCreatePrintlnJSPPage(actualJavaFile);

    }

    @Test
    public void canGeneratePrintlnJSPPageFromJavaFile() throws Exception {

        JavaFile actualJavaFile =
                new JavaFile(expectedFileSubPath, expectedFileName);

        JSPPageTester.canGeneratePrintlnJSPPage(actualJavaFile);
      
        setBaseUrl(printlnJspURL);

        beginAt(printlnJspFileName);

        assertTextInElement("exampletitle", "Official Example of: Println");

        assertTextInElement("example","Example:");

        assertTextInElement("example", "System.out.println(22.35);");
    }

    @Test
    public void canExtractExampleOfPrintlnWithDouble() {
        //TODO
    }

    private void canSaveJavaFile(JavaFile javaFile) throws Exception {

        assertThat(javaFile.getFileName(),
                is(equalTo(expectedFileName)));

        assertThat(javaFile.getFileSubPath(),
                is(equalTo(expectedFileSubPath)));

        String expectedPathString = expectedFileSubPath+"/"+expectedFileName;
        expectedPathString = expectedPathString.replace("/", "\\");
        
        assertThat(javaFile.getPath().toString(),
                is(equalTo(expectedPathString)));

        assertThat(javaFile.getPackageDeclaration(),
                is(equalTo(expectedPackageDeclaration)));

        assertThat(javaFile.getImportDeclarations(), is(equalTo(null)));

        assertThat(javaFile.getJavaClasses().size(), is(equalTo(1)));


    }

    private void canSaveJavaClasses(List<JavaClass> javaClasses) throws Exception {

        JavaClass javaClass = javaClasses.get(0);


        assertThat(javaClass.getName(),
                is(equalTo(expectedJavaClassName)));

        assertThat(javaClass.getType(),
                is(equalTo(expectedJavaClassType)));

        assertThat(javaClass.getMethods().size(), is(equalTo(1)));

    }

    private void canSaveJavaMethods(List<JavaMethod> javaMethods) {

        JavaMethod javaMethod = javaMethods.get(0);

        assertThat(javaMethod.getName(),
                is(equalTo(expectedMethodName)));

        assertThat(javaMethod.getParameters().size(), is(equalTo(0)));

        assertThat(javaMethod.getInstructions().size(),
                is(equalTo(1)));
        
    }




}
