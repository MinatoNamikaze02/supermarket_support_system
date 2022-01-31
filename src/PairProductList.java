
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.util.*;


public class PairProductList {
    int[][] productOrderMatrix;
    SetMultimap<Integer, Integer> finalPairRecommendations;

    public int maxSoldProd() {
        assert this.productOrderMatrix != null;
        int max = 0;
        for (int[] orderMatrix : this.productOrderMatrix) {
            int count = 0;
            for (int matrix : orderMatrix) {
                if (matrix == 1) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        max = max + 1;
        return max;
    }

    public int[][] matrixFormulation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the file ");
        String fileName = sc.next();
        SalesData salesData = new SalesData(fileName);
        ArrayList<String[]> matrixList = salesData.processData();
        this.productOrderMatrix = new int[matrixList.size()][matrixList.get(0).length];
        for (int i = 0; i < matrixList.size(); i++) {
            for (int j = 0; j < matrixList.get(i).length; j++) {
                this.productOrderMatrix[i][j] = Integer.parseInt(matrixList.get(i)[j]);
            }
        }
        return productOrderMatrix;

    }


    public SetMultimap<Integer, Integer> getFinalPairRecommendations() {
        return finalPairRecommendations;
    }


    public void finalPairRecommendation() {
        int[][] cnt = new int[productOrderMatrix.length][productOrderMatrix.length];
        for (int i = 0; i < productOrderMatrix.length; i++) {
            for (int k = 0; k < productOrderMatrix.length; k++) {
                if (i == k) {
                    continue;
                }
                for (int j = 0; j < productOrderMatrix[i].length; j++) {
                    if (productOrderMatrix[i][j] == 1 && productOrderMatrix[k][j] == 1) {
                        cnt[i][k]++;
                    }
                }
            }
        }
        int[] maxAt = new int[productOrderMatrix.length];
        ArrayList<int[]> maxin = new ArrayList<>();

        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt.length; j++) {
                maxAt[i] = cnt[i][j] > cnt[i][maxAt[i]] ? j : maxAt[i];

            }
            maxin.add(new int[]{i, maxAt[i]});
        }
        int ct = 0;

        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt.length; j++) {
                if (cnt[i][j] == cnt[i][maxAt[i]]) {
                    maxin.add(new int[]{i, j});
                }
            }
        }
        for (int[] integers : maxin) {
            integers[0] = integers[0] + 1;
            integers[1] = integers[1] + 1;
        }
        SetMultimap<Integer, Integer> multimap = HashMultimap.create();
        for (int[] integers : maxin) {
            multimap.put(integers[0], integers[1]);
        }
        for (Integer integer : multimap.keySet()) {
            System.out.println("Product " + integer + " is preferred to be paired with " + multimap.get(integer));
        }

        finalPairRecommendations = multimap;
    }

}
