package apireader;
import java.security.cert.Certificate;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.*;
import javax.net.ssl.HttpsURLConnection;

public class APIReader {
    
     private final String USER_AGENT = "Mozilla/5.0";
     private final String token = "2edc30d0dbb4cbd4272c9d16968c300c4876ffae0375e0f804dd699a7a40ddee2";
     
    public static void main(String[] args){
         try {
             new APIReader().sendGet();
         } catch (Exception ex) {
             Logger.getLogger(APIReader.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    // HTTP GET request
	private void sendGet() throws Exception {

		String url = "https://medium.com/feed/@angelmurchison09";

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Authorization", "Bearer" + token);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
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

	}
}
