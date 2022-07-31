package ca.yorku.eecs;

public class wonOscar {
	public wonOscar() {
		
	}
	
	public boolean setOscarNumber(String actorId , int number) {
		  Neo4jwonOscar dbwonOscar = new Neo4jwonOscar();
		  boolean check_actor = dbwonOscar.check_actor(actorId);
		  
		  if(check_actor) {
			  dbwonOscar.updateOscarWon(actorId, number);
			  
			  return true;
		  }else {
			  return false;
		  }
	}
  
   
}
