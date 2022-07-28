package ca.yorku.eecs;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
		String body = Utils.convert(request.getRequestBody());
			try {
				JSONObject jobj = new JSONObject(body);
				URI uri = request.getRequestURI();
				String method = uri.getPath();
				if(method.equals("/api/v1/getActor")) {
					
				}else if(method.equals("/api/v1/getMovie")) {
					
				}else if (method.equals("/api/v1/hasRelation")) {
					
				}else if(method.equals("/api/v1/computeBaconNumber")) {
					
				}else if(method.equals("/api/v1/computeBaconPath")) {
					
				}else {
					String msg = "{\n	msg : unimplemented method 501\n}";
					sendString(request , msg , 501);
					throw new IOException("unimplemented method 501\n" );
				}
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
		
        
	}
	
	public void PUThandler(HttpExchange request) throws IOException{
		String body = Utils.convert(request.getRequestBody());
			try {
				JSONObject jobj = new JSONObject(body);
				System.out.println(jobj.toString());
				
				URI uri = request.getRequestURI();
				String method = uri.getPath();
				
				if(method.equals("/api/v1/addActor")) {
					try {
						String name = jobj.getString("name");
						String actorId = jobj.getString("actorId");
						addActor a = new addActor();
						boolean addActor_Flag = a.addAct(name, actorId);
						String response = "";
						if((addActor_Flag)) {
							response +=   "{\n" + "	\"name\" : " +"\"" + name +"\" , \n"
            						+"	\"actorId\" : " + "\"" +actorId +"\n}";
							sendString(request , response , 200);
						}else {
							response += "{msg : actor already exists}";
							sendString(request , response , 400);
							
						}
						
					}catch(Exception e) {
						sendString(request , "bad request" , 400);
						throw new IOException("key does not exists");
					}
					
				}else if(method.equals("/api/v1/addMovie")) {
					try {
						String name = jobj.getString("name");
						String movieId = jobj.getString("movieId");
						addMovie m = new addMovie();
						boolean addMovie_Flag = m.addmov(name, movieId);
						String response = "";
						if(addMovie_Flag) {
							response +=   "{\n" + "	\"name\" : " +"\"" + name +"\" , \n"
            						+"	\"movieId\" : " + "\"" +movieId +"\n}";
							sendString(request , response , 200);
						}else {
							response += "{msg : movie already exists}";
							sendString(request , response , 400);
						}
						
					}catch(Exception e){
						sendString(request , "bad request" , 400);
						throw new IOException("key does not exists");
					}
					
					
				}else if(method.equals("/api/v1/addRelation")) {
					try {
						String actorId = jobj.getString("actorId");
						String movieId = jobj.getString("movieId");
						addRelation r = new addRelation();
						int addRelation_flag = r.addRel(movieId, actorId);
						String response = "";
						if(addRelation_flag == 1) {
							response +=   "{\n" + "	\"actorId\" : " +"\"" + actorId +"\" , \n"
            						+"	\"movieId\" : " + "\"" +movieId +"\n}";
							sendString(request , response , 200);
						}else if(addRelation_flag == 2) {
							response += "Relation already exists";
							sendString(request , response , 400);
						}else if(addRelation_flag == 3) {
							response += "actor not found";
							sendString(request , response, 404);
						}else {
							response += "movie not found";
							sendString(request , response, 404);
						}
					}catch(Exception e) {
						sendString(request , "bad request" , 400);
						throw new IOException("key does not exists");
					}
					
					
				}else {
					String msg = "{\n	msg : unimplemented method 501\n}";
					sendString(request , msg , 501);
					throw new IOException("unimplemented method 501\n" );
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
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
