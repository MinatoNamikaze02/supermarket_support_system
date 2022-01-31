import java.util.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class Order{
    static final String origin = "Chicago";
    private final String deliveryArea;
    private final int orderId;
    private final Date dateOfOrder;
    private int Distance;
    private int Duration;
    private String DistanceText;
    private String DurationText;
    private double finalDuration;
    private static Map<Double, Order> map = new HashMap<>();
    private void setFinalDuration(double finalDuration) {
        this.finalDuration = finalDuration;
    }

    Order(int orderId, Date dateOfOrder, String deliveryArea){
        this.orderId = orderId;
        this.dateOfOrder = dateOfOrder;
        this.deliveryArea = deliveryArea;
    }

    public void distanceCalc(){
        //using external API to get distance and duration
        String API_KEY = "uKAlC5KS4HqEAFSHxrGfLLdQzt0Qp";
        String URL = "https://api.distancematrix.ai/maps/api/distancematrix/json?origins="+origin;
        String url = URL + "&destinations=" + this.deliveryArea + "&key=" + API_KEY;
        //making an http request
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).method("GET", null).build();
        try{
            //validating the response
            Response response = client.newCall(request).execute();
            if(response.body() == null){
                System.out.println("No response");
            }
            String responseData = response.body().string();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(responseData).getAsJsonObject();
            JsonElement rows = jsonObject.get("rows");
            JsonElement elements = rows.getAsJsonArray().get(0).getAsJsonObject().get("elements");
            JsonElement distance = elements.getAsJsonArray().get(0).getAsJsonObject().get("distance");
            JsonElement text = distance.getAsJsonObject().get("text");
            JsonElement value = distance.getAsJsonObject().get("value");
            JsonElement duration = elements.getAsJsonArray().get(0).getAsJsonObject().get("duration");
            JsonElement text2 = duration.getAsJsonObject().get("text");
            JsonElement value2 = duration.getAsJsonObject().get("value");
            this.DistanceText = text.getAsString();
            this.DurationText = text2.getAsString();
            this.Distance = value.getAsInt();
            this.Duration = value2.getAsInt();
            try{
                Date date = new Date();
                long difference = date.getTime() - this.dateOfOrder.getTime();
                long differenceInSeconds = (difference / 1000) % 60;
                this.finalDuration = differenceInSeconds + this.Duration;
                if(Order.map.containsKey(this.finalDuration)){
                    this.finalDuration = this.finalDuration + 1;
                }
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Origin: " + Order.origin + " Delivery Area: " + this.deliveryArea + " Distance: " + text.getAsString() + " Duration: " + text2.getAsString());
            //now pushing into orderQueue based on the final duration
            Order.map.put(this.finalDuration, this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //sorting the list in reverse order
    public static void sortQueue(){
        DeliveryQueue.queue.clear();
        ArrayList<Double> sort = new ArrayList<>(Order.map.keySet());
        Collections.sort(sort);
        Collections.reverse(sort);
        for(double key: sort){
            DeliveryQueue.queue.add(Order.map.get(key));
        }

    }


    public static Map<Double, Order> getMap() {
        return map;
    }

    public double getFinalDuration() {
        return finalDuration;
    }



    public int getDistance() {
        return Distance;
    }

    public int getDuration() {
        return Duration;
    }


    public int getOrderId(){
        return orderId;
    }

    public Date getDateOfOrder(){
        return dateOfOrder;
    }


    public String getDeliveryArea(){
        return deliveryArea;
    }

    public String toString(){
        return "Order ID: " + orderId + "\n" + "Date of Order: " + dateOfOrder + "\n" + "Delivery Area: " + deliveryArea + "\n" + "Distance: " + DistanceText + "\n" + "Duration: " + DurationText + "\n" + "Final Duration: " + finalDuration;
    }
}
