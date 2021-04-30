/**
 * 
 * @author Wissam Siblini (https://github.com/wissam-sib/)
 * 
 */

import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Requester {

	public String url;
	
	

	public Requester(String url) {
		super();
		this.url = url;
	}
	
	
	public String getResponse(String sentence) throws IOException {
		String finalURL = this.url + "?question=" + URLEncoder.encode(sentence, "UTF-8");
				
		URL url = new URL(finalURL);
		
		HttpURLConnection con = null;		
		con = (HttpURLConnection) url.openConnection();
		
		//con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		
				
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		
		return content.toString();
	}
	
	

	public static void main(String[] args) {
		
		
		Logger.getLogger("Make sure the python service is running");

		Requester myEmbeddingRequester = new Requester("http://localhost:5000/api/embed");
		try {
			System.out.println(myEmbeddingRequester.getResponse("Bonjour ça va"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}