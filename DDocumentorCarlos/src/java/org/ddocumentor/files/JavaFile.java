package org.ddocumentor.files;

import org.ddocumentor.files.javaclass.JavaClass;
import java.io.BufferedReader;
import org.ddocumentor.files.exception.FileWithoutNameException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Java 7 Files API: http://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html
 * More Java 7 Files documentation here:
 * http://www.drdobbs.com/jvm/java-se-7-new-file-io/231600403
 * @author carlos
 */
public class JavaFile extends DDocumentorFile {

    private String packageDeclaration;

    private String[] importDeclarations;

    private List<JavaClass> javaClasses = new LinkedList<JavaClass>();

    public JavaFile() {
    }
    
    public JavaFile(String fileSubPath, String fileName)
            throws FileWithoutNameException, IOException {

        if (fileSubPath != null) {
            init(fileSubPath, fileName);
        } else {
            throw new FileWithoutNameException();
        }

    }

    private void init(String fileSubPath, String fileName)
        throws IOException {
        
        setFileName(fileName);
        setFileSubPath(fileSubPath);

        setJavaClasses();
    }

    public String getPackageDeclaration() {
        return this.packageDeclaration;
    }

    public String[] getImportDeclarations() {
        return this.importDeclarations;
    }

    public List<JavaClass> getJavaClasses() {
        return this.javaClasses;
    }

    public List<String> getLines() throws IOException {
        if (getPath() != null) {
            return Files.readAllLines(getPath(), Charset.defaultCharset() );
        } else {
            return null;
        }
    }

    public int setPackageDeclaration(String fileContents, int fileIndex) {

        this.packageDeclaration = StringUtils.substringBetween(
                            fileContents, "package", ";");



        if (packageDeclaration != null) {
            this.packageDeclaration =
                    StringUtils.deleteWhitespace(packageDeclaration);

            int packageDeclarationIndex = fileContents.indexOf(packageDeclaration);
            int packageDeclarationEndIndex = fileContents.indexOf(";",
                    packageDeclarationIndex) + 1;

            return packageDeclarationEndIndex;

        } else {

            return fileIndex;
        }


    }

    public int setImportDeclarations(String fileContents, int fileIndex) {

        String subFileContents = fileContents.substring(fileIndex);
        
        this.importDeclarations = StringUtils.substringsBetween(
                            subFileContents, "import", ";");

        if (importDeclarations != null) {

            int lastImportDeclarationIndex = subFileContents.indexOf(
                    importDeclarations[importDeclarations.length - 1]);

            int lastImportDeclarationEndIndex = subFileContents.indexOf(";",
                    lastImportDeclarationIndex) + 1;

            return lastImportDeclarationEndIndex;
        } else {

            return fileIndex;
        }

    }

    public String readFile(String fileName) throws IOException {

        InputStream resourceAsStream = Files.newInputStream(getPath());
        
	StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(resourceAsStream));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    private void setJavaClasses() throws IOException {

        if (getPath() != null) {

            String fileContents = readFile(getFileName());

            int fileIndex = 0;            
            fileIndex = setPackageDeclaration(fileContents, fileIndex);
            fileIndex = setImportDeclarations(fileContents, fileIndex);

            int nextClassIndex = -1;

            do {

                String subFileContents = fileContents.substring(fileIndex);

                String javaClassName = StringUtils.substringBetween(
                                fileContents, "class", "{");

                if (javaClassName != null) {

                    javaClassName = StringUtils.deleteWhitespace(javaClassName);

                    String javaClassType = StringUtils.substringBefore(
                            subFileContents, "class");
                    javaClassType = StringUtils.deleteWhitespace(javaClassType);

                    JavaClass javaClass = new JavaClass(javaClassName,
                            javaClassType);

                    javaClasses.add(javaClass);

                    fileIndex = subFileContents.indexOf("{") + 1;

                    fileIndex = javaClass.setMethods(
                            subFileContents, fileIndex);

                    nextClassIndex =
                       StringUtils.indexOf(subFileContents, "class", fileIndex);
                }

            } while (nextClassIndex != -1);

        }//end-if path!=null
         
    }



}
