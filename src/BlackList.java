import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BlackList extends Suggestions{

    public ArrayList<Integer> calcStagnancyRatio(Product [] array) throws ParseException {

        double[] stagnancyRatio = new double[array.length];
        int[] duplicate = new int[array.length];
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String d2 =(formatter.format(date));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        Date secondDate = sdf.parse(d2);
        for(int i = 0; i< array.length; i++){
            String d1 = array[i].getLastDateOfPurchase();
            Date firstDate = sdf.parse(d1);
            long diff = secondDate.getTime() - firstDate.getTime();
            TimeUnit time = TimeUnit.DAYS;
            double noOfDays = time.convert(diff, TimeUnit.MILLISECONDS);

            double k = (double) (array[i].quantityPurchased - array[i].getQuantity())/(array[i].quantityPurchased);
            stagnancyRatio[i] = (double) (k/(noOfDays )*100);
        }
        for (int i = 0; i< array.length;i++){
            duplicate[i] = array[i].getProductID();
        }
        for (int i = 0; i < array.length; i++) {

            // Inner nested loop pointing 1 index ahead
            for (int j = i + 1; j < array.length; j++) {

                // Checking elements
                double temp1 = 0;
                int temp2 = 0;
                if (stagnancyRatio[j] < stagnancyRatio[i]) {

                    // Swapping
                    temp1 = stagnancyRatio[i];
                    stagnancyRatio[i] = stagnancyRatio[j];
                    stagnancyRatio[j] = temp1;
                    temp2 = duplicate[i];
                    duplicate[i] = duplicate[j];
                    duplicate[j] = temp2;

                }
            }
            //System.out.println(array[i].getProductID());

        }
        for (int i = 0;i<5;i++){
            list.add(duplicate[i]);
        }
        return list;
    }
}
