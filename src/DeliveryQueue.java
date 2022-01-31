
import java.util.LinkedList;
import java.util.Queue;

public class DeliveryQueue {
    static Queue<Order> queue = new LinkedList<Order>();

    public static void printQueue(){
        for(Order o : queue){
            System.out.println(o);
        }
    }

}
