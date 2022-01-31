import com.google.common.collect.SetMultimap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Aisle {
    private Department department;
    private ArrayList<Slot> aisle;

    public int getNoOfSlots() {
        return noOfSlots;
    }

    private final int noOfSlots;

    public Aisle(int noOfSlots, Department department) {
        this.noOfSlots = noOfSlots;
        this.aisle = new ArrayList<>(noOfSlots);
        this.department = department;
    }

    public void storeLayout(SetMultimap<Integer, Integer> pairs) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(0, 1);
        int i=1;
        int check = 0;
        while(true){
            if(i>noOfSlots){
                break;
            }
            if(check == 1){
                if(temp.contains(i-1)){
                    Set<Integer> set = pairs.get(temp.get(i-3));
                    List<Integer> value = new ArrayList<>(set);
                    try{
                        temp.add(i - 1, value.get(1));
                    }catch(IndexOutOfBoundsException e){
                        temp.add(i - 1, value.get(0));
                    }
                }else temp.add(i-1, i-1);
            }
            Set<Integer> set = pairs.get(temp.get(i-1));
            List<Integer> value = new ArrayList<>(set);
            if(temp.contains(value.get(0))) {
                check = 1;
                i=i+1;
                continue;
            }else{
                check = 0;
                temp.add(i, value.get(0));
                i=i+1;
            }
        }
        System.out.println("The final store layout calculated is: ");
        int cnt = 1;
        for(int j: temp) {
            Slot tmp = new Slot(cnt, department.getProductList().get(j-1).getProductID());
            cnt++;
            aisle.add(tmp);
        }

    }
    public void printLayout(){
        System.out.println(department);
        System.out.println("\nThe aisle layout is: ");
        for(Slot i: aisle){
            System.out.println(i);
        }
    }
}
