/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ddocumentor.files;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class DDocumentorFile {

    private String fileName;

    private String fileSubPath;
    private Path path;

    //gets
    public String getFileName() {
        return this.fileName;
    }

    public String getFileSubPath() {
        return this.fileSubPath;
    }

    public Path getPath() {
        return this.path;
    }

    //sets
    public void setFileName(String fileName) {
        this.fileName = fileName;
        refreshPath();
    }

    public void setFileSubPath(String fileSubPath) {
        this.fileSubPath = fileSubPath;
        refreshPath();
    }

    public void setPath(Path path) {
        this.path = path;
    }

    private void refreshPath() {
        
        Path newPath = FileSystems.getDefault().getPath(
                fileSubPath==null?"":fileSubPath,
                fileName==null?"":fileName);

        setPath(newPath);
    }
}
