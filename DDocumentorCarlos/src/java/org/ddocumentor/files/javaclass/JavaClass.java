/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ddocumentor.files.javaclass;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author carlos
 */
public class JavaClass {

    private String name;
    private String type;

    private List<JavaMethod> javaMethods = new LinkedList<JavaMethod>();

    public JavaClass(String name) {
        this.name = name;
    }

    public JavaClass(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int setMethods(String fileContents, int fileIndex) {

        int nextMethodIndex = -1;
        do {

            String subFileContents = fileContents.substring(fileIndex);
            
            String methodDeclaration =
                    StringUtils.substringBefore(subFileContents, "(");

            if (methodDeclaration != null) {

                String[] methodDeclarationSplitted = StringUtils.split(
                        methodDeclaration);

                String methodName = methodDeclarationSplitted[
                        methodDeclarationSplitted.length - 1];
                methodName  = StringUtils.deleteWhitespace(methodName);

                JavaMethod javaMethod = new JavaMethod(methodName);

                javaMethods.add(javaMethod);                

                javaMethod.setParameters(
                       StringUtils.substringBetween(subFileContents, "(", ")"));

                fileIndex = javaMethod.setInstructions(subFileContents,
                        fileIndex);
               
                fileIndex =
                        StringUtils.indexOf(fileContents, "}", fileIndex);

                nextMethodIndex =
                        StringUtils.indexOf(fileContents, "(", fileIndex);

            }

        } while (nextMethodIndex != -1);

        return fileIndex;

    }
    
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public List<JavaMethod> getMethods() {
        return this.javaMethods;
    }
}
