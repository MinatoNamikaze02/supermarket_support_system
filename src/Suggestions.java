import java.util.ArrayList;
import java.util.List;

public abstract class Suggestions {
    protected ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public void printList() {
        for(int i: list) {
            System.out.print(i + " ");
        }
    }
}
