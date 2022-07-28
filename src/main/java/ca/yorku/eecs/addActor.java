package ca.yorku.eecs;

public class addActor {

	public addActor() {
		
	}
	
    public boolean addAct(String name , String actorId) {

        Neo4jaddActor dbAddActor = new Neo4jaddActor();
       
        boolean nameduplicate = dbAddActor.checkActorDuplicate(name , actorId);
        //check if there is duplicate for actor
        if((nameduplicate)) {
        	//if there isn't actor in the DB, add actor. Then return true to tell
        	//that it was able to add actor
            dbAddActor.addActor(name, actorId);
            dbAddActor.close();
            return true;
        }else {
        	//else return false, telling that it failed to add actor
        	dbAddActor.close();
        	return false;
        }
        
    }
}
