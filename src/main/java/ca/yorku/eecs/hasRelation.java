package ca.yorku.eecs;

import java.util.ArrayList;

public class hasRelation {
	public hasRelation() {
		
	}
	
	public boolean checkMovieExsist(String movieId) {
		Neo4jhasRelation dbhasRelation = new Neo4jhasRelation();
		boolean movieExists = dbhasRelation.checkMovieID(movieId);
		//checks if movie exists
		if((movieExists)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkActorExsist(String actorId) {
		Neo4jhasRelation dbhasRelation = new Neo4jhasRelation();
		boolean actorExists = dbhasRelation.checkActorID(actorId);
		//checks if actor exists
		if(actorExists) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkRelation(String actorId , String movieId) {
		Neo4jhasRelation dbhasRelation = new Neo4jhasRelation();
		boolean relationExists = dbhasRelation.checkRelationExsist(actorId, movieId);
		//check if they have relation
		if(relationExists) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
