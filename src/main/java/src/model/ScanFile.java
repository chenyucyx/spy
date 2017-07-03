package src.model;

/**
 * Created by chenyu on 2017/7/3.
 */
public class ScanFile {

    private String filePath;
    private String fileName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ScanFile(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }
}
