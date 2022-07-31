package ca.yorku.eecs;

public class wonCannesAward {
	public wonCannesAward() {
		
	}
	public boolean setCannesWon(String movieId , int number) {
		  Neo4jwonCannesAward dbwonCannes = new Neo4jwonCannesAward();
		  boolean check_movie = dbwonCannes.check_movie(movieId);
		  
		  if(check_movie) {
			  dbwonCannes.updatecannesWon(movieId, number);
			  
			  return true;
		  }else {
			  return false;
		  }
	}
}
