
import java.util.*;

public class RecommendedProductList extends Suggestions {
    //matrix of products
    private int[][] productSuggestionMatrix;
    private int[] currentOrderVector;
    private int[] cnt;

    //constructor

    public RecommendedProductList(int[] currentOrderVector) {
        this.currentOrderVector = currentOrderVector;
    }

    public int[][] matrixFormulation() {
        Scanner sc = new Scanner(System.in);
        SalesData salesData = new SalesData("D:\\recomProd.csv");
        ArrayList<String[]> matrixList = salesData.processData();
        this.productSuggestionMatrix = new int[matrixList.size()][matrixList.get(0).length];
        for (int i = 0; i < matrixList.size(); i++) {
            for (int j = 0; j < matrixList.get(i).length; j++) {
                this.productSuggestionMatrix[i][j] = Integer.parseInt(matrixList.get(i)[j]);
            }
        }
        cnt = new int[productSuggestionMatrix.length];
        return productSuggestionMatrix;
    }

    public ArrayList<Integer> removeDuplicates() {
        ArrayList<Integer> newlist = new ArrayList<Integer>();
        for (Integer i : list) {
            if (!newlist.contains(i)) {
                newlist.add(i);
            }
        }
        return newlist;
    }


    public ArrayList<Integer> finalProductRecommendation() {
        for (int i = 0; i < productSuggestionMatrix.length; i++) {
            for (int j = 0; j < productSuggestionMatrix[i].length; j++) {

                if (productSuggestionMatrix[i][j] == 1 && currentOrderVector[j] == 1) {
                    cnt[i] += 1;
                }
            }
        }

        int maxAt = 0;
        ArrayList<Integer> maxin = new ArrayList<>();

        for (int i = 0; i < cnt.length; i++) {
            maxAt = cnt[i] > cnt[maxAt] ? i : maxAt;
        }
        int ct = 0;

        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == cnt[maxAt]) {
                maxin.add(i);
            }

        }
        maxin.add(maxAt);

        for (int i = 0; i < maxin.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (productSuggestionMatrix[maxin.get(i)][j] != currentOrderVector[j] && productSuggestionMatrix[maxin.get(i)][j] == 1) {
                    list.add(j + 1);
                }
            }
        }
        list = this.removeDuplicates();
        return list;
    }
}


