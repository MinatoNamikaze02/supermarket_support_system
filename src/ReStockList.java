import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ReStockList extends Suggestions{

    public ArrayList<Integer> calcDPRatio(Product [] array) throws ParseException {
        double[] DPRatio = new double[array.length];
        int[] duplicate = new int[array.length];
        List<Integer> blackList;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        Date date = new Date();


        for(int i = 0; i< array.length; i++){

            String d1 =(array[i].getLastDateOfPurchase());
            Date firstDate = formatter.parse(d1);

            long diff = date.getTime() - firstDate.getTime();

            long noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            double k = (double) (array[i].quantityPurchased - array[i].getQuantity())/(array[i].quantityPurchased);
            double l = (array[i].getSellingPrice() - array[i].getCostPrice()) / (array[i].getCostPrice());
            DPRatio[i] = (double) (k/(noOfDays ) *l);
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
                if (DPRatio[j] < DPRatio[i]) {

                    // Swapping
                    temp1 = DPRatio[i];
                    DPRatio[i] = DPRatio[j];
                    DPRatio[j] = temp1;
                    temp2 = duplicate[i];
                    duplicate[i] = duplicate[j];
                    duplicate[j] = temp2;
                }
            }
        }
        Arrays.sort(DPRatio);
        BlackList b1 = new BlackList();
        blackList = b1.calcStagnancyRatio(array);
        for (int i = DPRatio.length-1; i >= 0; i--){

            if (!(blackList.contains(duplicate[i])))
                list.add(duplicate[i]);
        }
        return list;
    }
}