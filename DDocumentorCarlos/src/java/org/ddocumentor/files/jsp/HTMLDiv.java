package org.ddocumentor.files.jsp;

/**
 *
 * @author carlos
 */
public class HTMLDiv {

    private String id;
    private String content;

    public HTMLDiv(String id) {
        this.id = id;
    }

    //gets
    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    //sets
    public void setContent(String content) {
        this.content = content;
    }

}
