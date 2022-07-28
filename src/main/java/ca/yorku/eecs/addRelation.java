package ca.yorku.eecs;

public class addRelation {
	
	public addRelation() {
		
	}
	
	public int addRel(String movieId , String actorId) {
		Neo4jaddRelation r = new Neo4jaddRelation();
		boolean both_exist = r.checkActorExsist(actorId) && r.checkMovieExsist(movieId);
		boolean relation_exists = r.checkRelationExsist(actorId, movieId);
		if(both_exist) {
			if(!(relation_exists)) {
				r.addRelation(actorId, movieId);
				return 1;
			}else {
				return 2;
			}
			
		}else if(!(r.checkActorExsist(actorId)) && r.checkMovieExsist(movieId)) {
			return 3;
		}else if((r.checkActorExsist(actorId)) && !(r.checkMovieExsist(movieId))){
			return 4;
		}else {
			return 5;
		}
	}
}
