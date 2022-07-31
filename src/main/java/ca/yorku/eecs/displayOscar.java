package ca.yorku.eecs;

public class displayOscar {
	
	public displayOscar() {
		
	}
	
	public boolean check_actor(String actorId) {
		Neo4jdisplayOscar dbdoscar = new Neo4jdisplayOscar();
		boolean check_act = dbdoscar.checkActorID(actorId);
		if(check_act) {
			return true;
		}else {
			return false;
		}
	}
	
	public int getOscarNumber(String actorId) {
		Neo4jdisplayOscar dbdoscar = new Neo4jdisplayOscar();
		int ans = dbdoscar.displayOscar(actorId);
		return ans;
	}
	public String getActorInfo(String actorId) {
		Neo4jdisplayOscar dbdoscar = new Neo4jdisplayOscar();
		String ans = dbdoscar.getActor_info(actorId);
		return ans;
	}
}
