import java.util.ArrayList;
import java.util.Scanner;

public class Department {

    private Categories departmentName;
    private ArrayList<Product> productList = new ArrayList<>();
    private int noOfProducts;
    private int mostSoldProductId;

    Department(Categories departmentName, int noOfProducts, int mostSoldProductId) {
        this.departmentName = departmentName;
        this.noOfProducts = noOfProducts;
        this.mostSoldProductId = mostSoldProductId;
    }
    public Categories getDepartmentName() {
        return departmentName;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public int getNoOfProducts() {
        return noOfProducts;
    }

    public void getProductInfo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path of the file");
        String filename = sc.next();
        ProductData pd = new ProductData(this.departmentName, filename);
        ArrayList<String[]> productInfo = pd.processData();
        for(String[] product : productInfo){
            Product temp = new Product(Integer.parseInt(product[0]), product[1]);
            this.productList.add(temp);
        }
    }

    public String printList() {
        StringBuilder sb = new StringBuilder();
        for (Product p : this.productList) {
            sb.append(p + "\n");
        }
        return sb.toString();
    }

    public int getMostSoldProductId() {
        return mostSoldProductId;
    }
    public String toString() {
        return "Department: \ndepartmentName=" + departmentName + "\nproductList=\n" + this.printList() + "\nnoOfProducts="
                + noOfProducts + "\nmostSoldProduct =" + productList.get(mostSoldProductId-1);
    }


}
