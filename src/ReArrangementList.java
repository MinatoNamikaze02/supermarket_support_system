import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.*;

public class ReArrangementList extends Suggestions{

    public void RAList( ArrayList<String[]> splitdata) {
        ArrayList<Integer> orderProducts = new ArrayList<Integer>();
        String[] arrayOfProductsOrig = new String[200];

        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        String month = String.valueOf(currentMonth);

        for (String[] s : splitdata) {

            if (Objects.equals(s[2], month)) {
                arrayOfProductsOrig = s[1].split(" ");
                for(int i = 0; i < arrayOfProductsOrig.length; i++) {
                    orderProducts.add(Integer.parseInt(arrayOfProductsOrig[i]));
                }
            }
        }
        int size = orderProducts.size();
        int[] frequency = new int[size];
        int c = 0;
        for(int a : orderProducts){
            frequency[c] = Collections.frequency(orderProducts, a);
            c++;
        }
        int mx = 0;
        for(int j: frequency){
            if(j > mx){
                mx = j;
            }
        }
        Set<Integer> hash_Set = new HashSet<>();
        for(int b:  orderProducts){
            if(Collections.frequency(orderProducts, b) == mx){
                hash_Set.add(b);
            }
        }
        this.list.addAll(hash_Set);
    }
}