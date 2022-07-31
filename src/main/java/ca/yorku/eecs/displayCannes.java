package ca.yorku.eecs;

public class displayCannes {
	
	public displayCannes() {
		
	}
	
	public boolean check_movie(String movieId) {
		Neo4jdisplayCannes dbdcannes = new Neo4jdisplayCannes();
		boolean check_mov = dbdcannes.checkmovieID(movieId);
		//check if movie exists from DB
		if(check_mov) {
			return true;
		}else {
			return false;
		}
	}
	
	public int getCannesNumber(String movieId) {
		Neo4jdisplayCannes dbdcannes = new Neo4jdisplayCannes();
		int ans = dbdcannes.displayCannes(movieId);
		return ans;
	}
	public String getmovieInfo(String movieId) {
		Neo4jdisplayCannes dbdcannes = new Neo4jdisplayCannes();
		String ans = dbdcannes.getMovie_info(movieId);
		return ans;
	}
}
