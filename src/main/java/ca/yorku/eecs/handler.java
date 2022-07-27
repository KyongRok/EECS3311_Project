package ca.yorku.eecs;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class handler implements HttpHandler {

	@Override
	public void handle(HttpExchange request) throws IOException {
		try {
			if(request.getRequestMethod().equals("GET")) {
				GEThandler(request);
			}else if (request.getRequestMethod().equals("PUT")) {
				PUThandler(request);
			}else {
				sendString(request, "Unimplemented method\n", 501);
				throw new IOException("unimplemented method 501\n" );
			}
		}catch (Exception e) {
        	e.printStackTrace();
        	sendString(request, "Server error\n", 500);
        }
		
	}
	
	public void GEThandler(HttpExchange request) throws IOException{
		URI uri = request.getRequestURI();
        
	}
	
	public void PUThandler(HttpExchange request) throws IOException{
		String body = Utils.convert(request.getRequestBody());
		
			
			URI uri = request.getRequestURI();
			String method = uri.getPath();
			
			if(method.equals("/api/v1/addActor")) {
				System.out.println(body);
				
				sendString(request , "Yes" , 200);
			}else if(method.equals("/api/v1/addMovie")) {
				
			}else if(method.equals("/api/v1/addRelation")) {
				
			}else {
				String msg = "{msg : unimplemented method 501}";
				sendString(request , msg , 501);
				throw new IOException("unimplemented method 501\n" );
			}
			
		
		
		 //"http://localhost8080/api/v1/addActor";
		 //"http://localhost8080/api/v1/addMovie";
		 //"http://localhost8080/api/v1/addRelation";
		
	}
	
	private void sendString(HttpExchange request, String data, int restCode) 
			throws IOException {
		request.sendResponseHeaders(restCode, data.length());
        OutputStream os = request.getResponseBody();
        os.write(data.getBytes());
        os.close();
	}


}
