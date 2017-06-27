package src.model;

/**
 * Created by chenyu on 2017/6/26.
 */
public class Result {
    private String fileName;
    private String fileDirt;
    private String bugType;
    private int lineNumber;
    private String description;

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName=fileName;
    }
    public String getFileDirt(){
        return fileDirt;
    }
    public void setFileDirt(String fileDirt){
        this.fileDirt=fileDirt;
    }
    public String getBugType() {
        return bugType;
    }
    public void setBugType(String bugType){
        this.bugType=bugType;
    }
    public int getLineNumber(){
        return lineNumber;
    }
    public void setLineNumber(int lineNumber){
        this.lineNumber=lineNumber;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
}
