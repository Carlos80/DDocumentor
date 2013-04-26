package org.ddocumentor.files.javaclass;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author carlos
 */
public class MethodCall {

    private String text;
    private String objectName;
    private JavaMethod method;

    public MethodCall(String objectName, JavaMethod method) {
        this.objectName = objectName;
        this.method = method;
    }

    public MethodCall(String instructionText) {
        init(instructionText);
    }

    public String getText() {
        return this.text;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public JavaMethod getMethod() {
        return this.method;
    }

    public void init(String instructionText) {

        this.text = StringUtils.substringBefore(instructionText, "(");

        String[] textItems = StringUtils.split(this.text, ".");

        if (textItems.length > 0) {
            if (textItems.length == 1) {
                this.objectName = "this";
            }

            if (textItems.length > 1) {
                for (int i=0; i<textItems.length - 1; i++) {
                    this.objectName += textItems[i];
                }
            }

            setMethod(textItems[textItems.length-1]);
        }//end-if
    }

    public void setMethod(String methodName) {
        this.method = new JavaMethod(methodName);
    }


}
