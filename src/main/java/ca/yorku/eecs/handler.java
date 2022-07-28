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
					try {
						String actorId = jobj.getString("actorId");
						getActor gact = new getActor();
						boolean checkAct = gact.checkActorExsist(actorId);
						if(checkAct) {
							String ans = gact.getact(actorId);
							sendString(request ,ans , 200);
						}else {
							sendString(request , "actor not found" , 404);
							throw new IOException("actor does not exists");
						}
					}catch(Exception e) {
						sendString(request , "bad request" , 400);
						throw new IOException("key does not exists");
					}
				}else if(method.equals("/api/v1/getMovie")) {
					try {
						String movieId = jobj.getString("movieId");
						getMovie gmov = new getMovie();
						boolean checkMov = gmov.checkMovieExsist(movieId);
						
						if(checkMov) {
							String ans = gmov.getmov(movieId);
							sendString(request , ans , 200);
						}else {
							sendString(request , "movie not found" , 404);
							throw new IOException("movie does not exists");
						}
					}catch(Exception e) {
						sendString(request , "bad request" , 400);
						throw new IOException("key does not exists");
					}
				}else if (method.equals("/api/v1/hasRelation")) {
					try {
						String actorId = jobj.getString("actorId");
						String movieId = jobj.getString("movieId");
						hasRelation hRel = new hasRelation();
						boolean CheckMov = hRel.checkMovieExsist(movieId);
						boolean CheckAct = hRel.checkActorExsist(actorId);
						boolean Actor_Movie_Exists = CheckMov && CheckAct;
						boolean hasRel = hRel.checkRelation(actorId, movieId);
						String ans = "";
						if(Actor_Movie_Exists) {
							ans     += "{\n" + "	\"" + "actorId\" : \"" + actorId +"\",\n"
									+ "	\"" + "movieId\" : \"" + movieId +"\",\n"
									+ "	\"" + "hasRelationship\" : \"" + hasRel +"\"\n"
									+ "}";
							sendString(request , ans , 200);
						}else if(!(CheckMov) && CheckAct) {
							//movie does not exists
							sendString(request , "Movie Not Found" , 404);
							throw new IOException("Movie not found");
						}else {
							//actor does not exists
							sendString(request , "Actor Not Found" , 404);
							throw new IOException("Actor not found");
						}
					}catch(Exception e) {
						sendString(request , "bad request" , 400);
						throw new IOException("key does not exists");
					}
				}else if(method.equals("/api/v1/computeBaconNumber")) {
					try {
						String actorId = jobj.getString("actorId");
						String kevin =  "nm0000102";
						computeBaconNumber cbnum = new computeBaconNumber();
						boolean checkactor = cbnum.checkActorExists(actorId);
						
						if(checkactor) {
							String check_empty = "{\n" + "	\"BaconPath\": "+ "\n"+"}";
							String ans = cbnum.computeBaconNumberResult(actorId, kevin);
							if(check_empty.equals(ans)) {
								sendString(request , "No path Exists",404);
								throw new IOException("No path Exists");
							}else {
								sendString(request , ans , 200);
							}
						}else {
							sendString(request , "Actor not found",404);
							throw new IOException("Actor not found");
						}
					}catch(Exception e) {
						sendString(request , "bad request" , 400);
						throw new IOException("key does not exists");
					}
				}else if(method.equals("/api/v1/computeBaconPath")) {
					try {
						String actorId = jobj.getString("actorId");
						System.out.println(actorId);
						computeBaconPath cbpath = new computeBaconPath();
						boolean checkactor = cbpath.checkActorExists(actorId);
						String kevin =  "nm0000102";
						if(checkactor) {
							String check_empty = "{\n" + "	\"BaconPath\": [\n" + "	]\n" + "}";
							String ans = cbpath.computeBaconPathResult(actorId , kevin);
							if(ans.equals(check_empty)) {
								sendString(request , "No path Exists",404);
								throw new IOException("No path Exists");
							}else {
								sendString(request , ans , 200);
							}
						}else {
							sendString(request,"Actor not found", 404);
							throw new IOException("Actor not found");
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

	}
	
	private void sendString(HttpExchange request, String data, int restCode) 
			throws IOException {
		request.sendResponseHeaders(restCode, data.length());
        OutputStream os = request.getResponseBody();
        os.write(data.getBytes());
        os.close();
	}


}
