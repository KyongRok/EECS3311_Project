package ca.yorku.eecs;

import java.util.ArrayList;

public class getMovie {
	public getMovie() {
		
	}
	
	public boolean checkMovieExsist(String movieId) {
		Neo4jgetMovie dbGetMovie = new Neo4jgetMovie();
		boolean movieExists = dbGetMovie.checkMovieID(movieId);
		if((movieExists)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getmov(String movieId) {
		Neo4jgetMovie dbGetMovie = new Neo4jgetMovie();
		ArrayList<String> list = dbGetMovie.getMovie_actors(movieId);
		String moviename = dbGetMovie.getMovie_info(movieId);
		String ans = "{\n" + "\"movieId\": \"" + movieId +"\", \n" 
				+ "\"name\": \"" + moviename +"\", \n" 
				+"\"actors\": [\n";
		//return type of dbGetMovie.getMovie_actors is arraylist
		for(int i = 0; i < list.size(); i++) {
			if(i == list.size() -1) {
				ans += "		\"" + list.get(i) +"\"\n";
			}else {
				ans += "		\"" + list.get(i) +"\",\n"; 
			}
			
		}
		ans += "	]\n" + "}";
		
		
		return ans;
	}
}
