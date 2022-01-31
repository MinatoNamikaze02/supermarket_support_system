import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesData extends Data{

    public SalesData(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public ArrayList<String[]> processData() {
        ArrayList <String[]> data = new ArrayList<>();
        int flag = 1;
        while(true){
            try {
                if(flag == 0) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("File not found");
                    System.out.println("Please enter the correct file name");
                    this.fileName = sc.next();
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    //csv files
                    String[] split = line.split(",");
                    data.add(split);
                }
                break;
            } catch (IOException e) {
                flag = 0;
            }
        }
        return data;
    }

}
