/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ddocumentor.files.javaclass;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.ddocumentor.files.JavaDoc;

/**
 *
 * @author carlos
 */
public class JavaMethod {

    private String name;

    private String[] modifiers;

    private List<Parameter> parameters = new LinkedList<Parameter>();

    private List<Instruction> instructions = new LinkedList<Instruction>();

    private List<Throw> throwList;

    private JavaDoc javaDoc;

    public JavaMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public List<Instruction> getInstructions() {
        return this.instructions;
    }

    public void setParameters(String substringBetween) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    public int setInstructions(String methodContent, int fileIndex) {

        int methodContentIndex = -1;

        String methodBody = StringUtils.substringBetween(
                methodContent, "{", "}");

        String[] instructionStrings = StringUtils.split(methodBody, ";");

        for (String instructionString : instructionStrings) {

            instructionString = StringUtils.deleteWhitespace(instructionString);

            if (!instructionString.equals("")) {
                Instruction instruction = 
                        new Instruction(instructionString+";");
                this.instructions.add(instruction);

                methodContentIndex = methodContent.indexOf(instructionString);
                methodContentIndex += instructionString.length() + 1;
            }
        }

        return fileIndex + methodContentIndex;
    }

}
