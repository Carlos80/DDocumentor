/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ddocumentor.files;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * See
 * @author carlos
 */
public class JavaAPIExampleFile {

    public static final String exampleTitleId = "exampletitle";
    public static final String exampleId = "example";

    private static final String webSubPath = "web";
    private Path path;

    private static final String header =
       "<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>\n"
      +"<html>\n"
      +"<body>";

    private static final String footer =
       "\n</body>\n"
      +"</html>";

    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private String URL;
    private String fileName;

    private String exampleName;
    private String exampleCode;

    public JavaAPIExampleFile() {
    }

    public JavaAPIExampleFile(String exampleName, String exampleCode) {
        init(exampleName, exampleCode);
    }

    public String getURL() {
        return this.URL;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getExampleName() {
        return this.exampleName;
    }

    public String getExampleCode() {
        return this.exampleCode;
    }

    public Path getPath() {
        return this.path;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setExampleName(String exampleName) {
        this.exampleName = exampleName;
    }

    public void setExampleCode(String exampleCode) {
        this.exampleCode = exampleCode;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void generateExampleFile() throws IOException {

        ArrayList<String> lines = new ArrayList<String>();
        lines.add(header);

        Files.write(getPath(), lines, ENCODING, StandardOpenOption.CREATE);

    }

    private void refreshPath() {
        setPath(FileSystems.getDefault().getPath(webSubPath));
    }

    private void init(String exampleName, String exampleCode) {
        setExampleName(exampleName);
        setExampleCode(exampleCode);
        refreshPath();
    }



}
