/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ddocumentor;

import org.ddocumentor.files.JavaFile;
import org.ddocumentor.files.jsp.JSPFile;

/**
 *
 * @author carlos
 */
public class DDocumentor {

    private final static String JAVA_SOURCE_FILE_NAME =
            "CodeExampleOfPrintlnWithDouble.java";

    private final static String JAVA_SOURCE_FILES_PATH =
            "src/java/org/ddocumentor/resources/sourcecode/println";

    private final static String JSP_TARGET_FOLDER = "web";

    public static void main(String[] args) {

        try {

            JavaFile javaSouceFile =
                    new JavaFile(JAVA_SOURCE_FILES_PATH, JAVA_SOURCE_FILE_NAME);

            JSPFile jspTargetFile = new JSPFile();
            jspTargetFile.setFromJavaFile(javaSouceFile, JSP_TARGET_FOLDER);
            jspTargetFile.generateJSPFile();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
