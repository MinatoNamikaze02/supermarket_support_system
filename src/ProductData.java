
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProductData extends Data{

    private Categories department;

    public Categories getDepartment() {
        return department;
    }

    public ProductData(Categories department, String fileName) {
        this.department = department;
        this.fileName = fileName;
    }

    @Override
    public ArrayList<String[]> processData() {
        ArrayList<String[]> data = new ArrayList<>();
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), StandardCharsets.UTF_8));
            String line;
            while((line=br.readLine())!=null) {
                //csv files
                String[] split =  line.split(",");
                data.add(split);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
