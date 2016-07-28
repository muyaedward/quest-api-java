package questapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nderitu on 7/27/16.
 */
public class CheckBalance {

    private String url = "http://account.questdesigners.com/API/?action=balance";
    private String username;
    private  String api_key;

    public CheckBalance() {

    }

    public CheckBalance(String username, String api_key) {
        this.username = username;
        this.api_key = api_key;
    }

    public String check(){
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
            return response.toString();
        }catch (Exception e){
            System.out.println(e);
            return "";
        }
    }

    private String makeUrl(){
        if(username == null)
            throw new IllegalArgumentException("username cannot be null");
        if (api_key == null)
            throw  new  IllegalArgumentException("Api key cannot be null");


        String u = url+"&username="+username+"&api_key="+api_key;
        u = u.replaceAll(" ", "%20");
        System.out.println(u);
        return u;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
