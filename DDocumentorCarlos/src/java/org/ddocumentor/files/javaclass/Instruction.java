package org.ddocumentor.files.javaclass;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author carlos
 */
public class Instruction {

    private String text;
    private MethodCall methodCall;
    private List<Object> arguments;
    private List<JavaMethod> isExampleOf;

    public Instruction() {
    }

    public Instruction(String text) {
        init(text);
    }

    public String getText() {
        return this.text;
    }

    public MethodCall getMethodCall() {
        return this.methodCall;
    }

    public List<Object> getArguments() {
        return this.arguments;
    }

    public List<JavaMethod> getIsExampleOf() {
        return this.isExampleOf;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMethodCall(MethodCall methodCall) {
        this.methodCall = methodCall;
    }

    public void setIsExampleOf(List<JavaMethod> isExampleOf) {
        this.isExampleOf = isExampleOf;
    }

    /*
    public void setServesUsageExamplesFromText() {

       //String parameters = StringUtils.su(text, ".");
       String[] splitInstructions = StringUtils.split(text, ".");

       String tet = "";

    }
     * 
     */

    private void init(String text) {
        this.text = text;
        this.methodCall = new MethodCall(this.text);
        this.isExampleOf = new ArrayList<JavaMethod>();
        this.isExampleOf.add(methodCall.getMethod());

    }







}
