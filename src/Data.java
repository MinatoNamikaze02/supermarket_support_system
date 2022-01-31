
import java.util.ArrayList;

public abstract class Data {
    protected String fileName;
    public abstract ArrayList<String[]> processData();

    public void setFile(String fileName) {
        this.fileName = fileName;
    }
    public String getFile() {
        return fileName;
    }
}
