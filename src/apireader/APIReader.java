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
    
       private void testIt(){
      String https_url = "https://medium.com/feed/@angelmurchison09";
      
      URL url;
      try {

	     url = new URL(https_url);
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
             con.addRequestProperty("Accept", "application/json");
             con.addRequestProperty("Content-Type", "application/json");
             con.addRequestProperty("Authorization", "Bearer" + token);

	     //dumpl all cert info
	     print_https_cert(con);

	     //dump all the content
	     print_content(con);

      } catch (MalformedURLException e) {
	     e.printStackTrace();
      } catch (IOException e) {
	     e.printStackTrace();
      }

   }

   private void print_https_cert(HttpsURLConnection con){

    if(con!=null){

      try {

	System.out.println("Response Code : " + con.getResponseCode());
	System.out.println("Cipher Suite : " + con.getCipherSuite());
	System.out.println("\n");

	Certificate[] certs = con.getServerCertificates();
	for(Certificate cert : certs){
	   System.out.println("Cert Type : " + cert.getType());
	   System.out.println("Cert Hash Code : " + cert.hashCode());
	   System.out.println("Cert Public Key Algorithm : "
                                    + cert.getPublicKey().getAlgorithm());
	   System.out.println("Cert Public Key Format : "
                                    + cert.getPublicKey().getFormat());
	   System.out.println("\n");
	}

	} catch (SSLPeerUnverifiedException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	}

     }

   }

   private void print_content(HttpsURLConnection con){
	if(con!=null){

	try {

	   System.out.println("****** Content of the URL ********");
	   BufferedReader br =
		new BufferedReader(
			new InputStreamReader(con.getInputStream()));

	   String input;

	   while ((input = br.readLine()) != null){
	      System.out.println(input);
	   }
	   br.close();

	} catch (IOException e) {
	   e.printStackTrace();
	}

       }
   }
}
