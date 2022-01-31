import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestDriver {

    public static void main(String[] args) throws ParseException {

        ArrayList<Integer> prodList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int cont = 1;


        do {
            System.out.println("\n\nWelcome to Supermarket Support System\n");
            System.out.println("Enter 1 for product suggestions through File Processing\nEnter 2 for Product Suggestion through manual Order Entry\nEnter 3 for giving file input to Order Queue\nEnter 4 for adding a new Order to the Order Queue\nEnter 5 for Pairing Products\nEnter 6 for Store Layout\nEnter 7 for Re Stock Product Suggestion\nEnter 8 for Seasonal Rearrangement \nEnter 9 to Exit");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    //getting the current order vector using order ID's
                    SalesData sd = new SalesData("D:\\currentVector.csv");
                    ArrayList <String[]> ord = new ArrayList<>();
                    ord = sd.processData();
                    int[] ordersID = new int[ord.get(0).length];
                    for(String[] s: ord){
                        for(int i = 0; i < s.length; i++){
                            ordersID[i] = Integer.parseInt(s[i]);
                        }
                    }

                    RecommendedProductList recomProdList = new RecommendedProductList(ordersID);
                    recomProdList.matrixFormulation();
                    recomProdList.finalProductRecommendation();
                    System.out.println("\n\nRecommended Products are(id):\n");
                    System.out.println(recomProdList.getList());
                }
                case 2 -> {
                    System.out.println("Enter the Order ID:");
                    int ord[] = new int[10];
                    int orderID = sc.nextInt();
                    do {

                        System.out.println("Enter the Product ID:");
                        int productID = sc.nextInt();
                        if (productID == -1) {
                            break;
                        }

                        for (int pl = 0; pl < prodList.size(); pl++) {
                            if (prodList.get(pl) == productID) {
                                ord[pl] = 1;
                            }
                        }
                    } while (true);
                    RecommendedProductList recomProdList = new RecommendedProductList(ord);
                    recomProdList.matrixFormulation();
                    System.out.println("\n\nRecommended Products are(id):\n");
                    System.out.println(recomProdList.finalProductRecommendation());
                }
                case 3 -> {
                    String fileName = "D:\\orders.csv";
                    Date dt = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    String str = formatter.format(dt);
                    //using sales data class to process the CSV file
                    SalesData sd = new SalesData(fileName);
                    ArrayList<String[]> split = sd.processData();
                    try {
                        for(String[] s : split) {
                            int orderId = Integer.parseInt(s[0]);
                            int hour = Integer.parseInt(s[1]);
                            int minutes = Integer.parseInt(s[2]);
                            DateFormat df = new SimpleDateFormat("hh:mm");
                            Date date = df.parse(hour + ":" + minutes);
                            String destination = s[3];
                            System.out.println("Order ID: " + orderId + " Date: " + str + " Time:" + hour + ":" + minutes + " PM" + " Destination: " + destination);
                            Order temp = new Order(orderId, date, destination);
                            temp.distanceCalc();
                            Order.sortQueue();
                        }
                        DeliveryQueue.printQueue();
                    } catch (ParseException e) {
                        System.out.println("Error:" + e.getMessage());
                        e.printStackTrace();
                    }
                }
                case 4 -> {
                    //getting a specific order and pushing it to the pre-existing order-queue
                    sc.useDelimiter("\n");
                    int flag = 1;
                    Date dt = new Date();
                    System.out.println("Enter the Order ID:");
                    int orderID = sc.nextInt();
                    DateFormat sdf = new SimpleDateFormat("hh:mm aa");
                    while (flag == 1) {
                        try {
                            System.out.println("Enter the Time (hh:mm):");
                            String time = sc.next();
                            dt = sdf.parse(time);
                            flag = 0;
                        } catch (ParseException e) {
                            System.out.println("Invalid Date Format");
                        }
                    }
                    System.out.println("Enter the Destination:");
                    String destination = sc.next();
                    Order temp = new Order(orderID, dt, destination);
                    temp.distanceCalc();
                    Order.sortQueue();
                    System.out.println("Order Added");
                    System.out.println("Updated Order Queue is printed below");
                    DeliveryQueue.printQueue();
                }
                case 5 -> {
                    //pairing products inputted through a file matrix

                    PairProductList pairProdList = new PairProductList();
                    pairProdList.matrixFormulation();
                    System.out.println("\n\nFinal Pair Products are(id):\n");

                    pairProdList.finalPairRecommendation();

                }
                case 6 -> {
                    //pairing products initially

                    PairProductList pairProdList = new PairProductList();
                    pairProdList.matrixFormulation();
                    pairProdList.finalPairRecommendation();
                    int max = pairProdList.maxSoldProd();
                    //dept
                    System.out.println("Enter the department name:");
                    Categories deptName = Categories.valueOf(sc.next());
                    Department dept = new Department(deptName, pairProdList.productOrderMatrix.length, max);
                    dept.getProductInfo();
                    //store layout updation
                    Aisle aisle = new Aisle(pairProdList.productOrderMatrix.length, dept);
                    aisle.storeLayout(pairProdList.getFinalPairRecommendations());
                    aisle.printLayout();
                }
                case 7 -> {
                    SalesData sd = new SalesData("D:\\case-7.csv");
                    ArrayList<String[]> splitdata = sd.processData();
                    int size = splitdata.size();
                    Product[] arr = new Product[size];
                    int count = 0;
                    for (String[] str : splitdata){
                        arr[count] = new Product(Integer.parseInt(str[0]),str[1],Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]),Integer.parseInt(str[5]),str[6]);
                        count++;
                    }
                    BlackList b = new BlackList();
                    b.calcStagnancyRatio(arr);
                    System.out.println("The products in the Blacklist are:" + b.getList());
                    ReStockList c = new ReStockList();
                    c.calcDPRatio(arr);
                    System.out.println("The products in the Re-Stock list in the descending order are:" + c.getList());
                }
                case 8 -> {
                    SalesData sd = new SalesData("D:\\case-8.csv");
                    ArrayList<String[]> splitdata = sd.processData();
                    ReArrangementList re = new ReArrangementList();
                    re.RAList(splitdata);
                    System.out.println("The most frequent products are: "+ re.getList());
                }
                case 9 -> {
                    System.out.println("Thank you for using our system");
                    cont = 0;
                }
            }
        } while (cont == 1);
        System.exit(0);
    }
}
