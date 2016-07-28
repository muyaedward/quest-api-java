package questapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nderitu on 7/27/16.
 */
public class SendSms {

    private String url = "http://account.questdesigners.com/API/?action=compose";
    private String username;
    private  String api_key;
    private  String sender;
    private  String to;
    private List<String> toList;
    private  String message;

    public SendSms(String username, String api_key, String sender, String to, String message){
        this.username = username;
        this.api_key = api_key;
        this.sender = sender;
        this.to = to;
        this.message = message;

        makeUrl();
    }

    public  SendSms(String username, String api_key, String sender, ArrayList<String> to, String message){
        this.username = username;
        this.api_key = api_key;
        this.sender = sender;
        this.toList = to;
        this.message = message;


    }

    public List getToList() {
        return toList;
    }

    public SendSms(){}

    public void sendMultipleSms(){
        for (Object i : toList.toArray()){

            String final_url = makeUrl2(i.toString());
            try{
                URL obj = new URL(final_url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // optional default is GET
                con.setRequestMethod("GET");

                //add request header
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + final_url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void send(){
        String final_url = makeUrl();

        try {
            URL obj = new URL(final_url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + final_url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void setToList(List toList) {
        this.toList = toList;
    }

    private String makeUrl(){
        if(username == null)
            throw new IllegalArgumentException("username cannot be null");
        if (api_key == null)
            throw  new  IllegalArgumentException("Api key cannot be null");
        if (sender == null)
            throw new IllegalArgumentException("Sender cannot be null");
        if (to == null)
            throw new IllegalArgumentException("you have not supplied any recipients");
        if (message == null)
            throw new  IllegalArgumentException("message cannot be null");

        String u = url+"&username="+username+"&api_key="+api_key+"&sender="+sender+"&to="+to+"&message="+message;
        u = u.replaceAll(" ", "%20");
        System.out.println(u);
        return u;
    }

    private String makeUrl2(String phone_no){
        if(username == null)
            throw new IllegalArgumentException("username cannot be null");
        if (api_key == null)
            throw  new  IllegalArgumentException("Api key cannot be null");
        if (sender == null)
            throw new IllegalArgumentException("Sender cannot be null");
        if (message == null)
            throw new  IllegalArgumentException("message cannot be null");

        String u = url+"&username="+username+"&api_key="+api_key+"&sender="+sender+"&to="+phone_no+"&message="+message;
        u = u.replaceAll(" ", "%20");
        System.out.println(u);
        return u;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUsername() {
        return username;
    }

    public String getApi_key() {
        return api_key;
    }

    public String getSender() {
        return sender;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public void setTo(String to) {

        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
