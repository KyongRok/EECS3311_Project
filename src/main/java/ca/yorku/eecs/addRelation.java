package ca.yorku.eecs;

public class addRelation {
	
	public addRelation() {
		
	}
	
	public int addRel(String movieId , String actorId) {
		Neo4jaddRelation r = new Neo4jaddRelation();
		//used return type as int because it was more easier for me... KyongRok Kim
		boolean both_exist = r.checkActorExsist(actorId) && r.checkMovieExsist(movieId);
		//check if both actor and movie exists
		boolean relation_exists = r.checkRelationExsist(actorId, movieId);
		//check if relation exists
		if(both_exist) {
			if(!(relation_exists)) {
				//if relation does not exists add and return 1
				r.addRelation(actorId, movieId);
				return 1;
			}else {
				//relation exists return 2
				return 2;
			}
			
		}else if(!(r.checkActorExsist(actorId)) && r.checkMovieExsist(movieId)) {
			//see which one doesn't exists, this case, actor doesn't return 3
			return 3;
		}else if((r.checkActorExsist(actorId)) && !(r.checkMovieExsist(movieId))){
			//this case, movie doesn't exists return 4
			return 4;
		}else {
			//something is wrong... return 5
			return 5;
		}
	}
}
