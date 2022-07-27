package ca.yorku.eecs;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
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
		URI uri = request.getRequestURI();
		String addActorURI = "http://localhost8080/api/v1/addActor";
		String addMovieURI = "http://localhost8080/api/v1/addMovie";
		String addRelationURI = "http://localhost8080/api/v1/addRelation";
		
		System.out.println("here");
	}
	
	private void sendString(HttpExchange request, String data, int restCode) 
			throws IOException {
		request.sendResponseHeaders(restCode, data.length());
        OutputStream os = request.getResponseBody();
        os.write(data.getBytes());
        os.close();
	}
	
	private static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }


}
