package ca.yorku.eecs;

public class addActor {

	public addActor() {
		
	}
	
    public boolean addAct(String name , String actorId) {

        Neo4jaddActor dbAddActor = new Neo4jaddActor();
       
        boolean nameduplicate = dbAddActor.checkActorDuplicate(name , actorId);
        
        if((nameduplicate)) {
        	
            dbAddActor.addActor(name, actorId);
            dbAddActor.close();
            return true;
        }else {
        	
        	dbAddActor.close();
        	return false;
        }
        
    }
}
