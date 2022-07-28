package ca.yorku.eecs;

public class addMovie {
	
	public addMovie() {
		
	}
    public boolean addmov(String name , String movieId) {

        Neo4jaddMovie dbAddmovie = new Neo4jaddMovie();
       
        boolean nameduplicate = dbAddmovie.checkMovieDuplicate(movieId);
        
        if((nameduplicate)) {
        	
            dbAddmovie.addMovie(name, movieId);
            dbAddmovie.close();
            return true;
        }else {
        	
        	dbAddmovie.close();
        	return false;
        }
        
    }
}
