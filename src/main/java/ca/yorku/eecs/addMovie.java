package ca.yorku.eecs;

public class addMovie {
	
	public addMovie() {
		
	}
    public boolean addmov(String name , String movieId) {

        Neo4jaddMovie dbAddmovie = new Neo4jaddMovie();
       
        boolean nameduplicate = dbAddmovie.checkMovieDuplicate(movieId);
        //check duplicate for movie
        if((nameduplicate)) {
        	//if there isn't movie with same Id already, add movie and return true
        	//telling that it was added
            dbAddmovie.addMovie(name, movieId);
            dbAddmovie.close();
            return true;
        }else {
        	//else return false, telling it failed to add new movie
        	dbAddmovie.close();
        	return false;
        }
        
    }
}
