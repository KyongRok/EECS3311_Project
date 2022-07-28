package ca.yorku.eecs;

import java.util.ArrayList;

public class getActor {
	public getActor() {
		
	}
	
	public boolean checkActorExsist(String actorId) {
		Neo4jgetActor dbGetActor = new Neo4jgetActor();
		boolean actorExists = dbGetActor.checkActorID(actorId);
		//check if actor exists
		if((actorExists)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getact(String actorId) {
		Neo4jgetActor dbGetActor = new Neo4jgetActor();
		ArrayList<String> list = dbGetActor.getActor_movies(actorId);
		String actorName = dbGetActor.getActor_info(actorId);
		//return type of method getActor_Movies is ArrayList<String>
		String ans = "{\n" + "\"actorId\": \"" + actorId +"\", \n" 
				+ "\"name\": \"" + actorName +"\", \n" 
				+"\"Movies\": [\n";
		
		for(int i = 0; i < list.size(); i++) {
			if(i == list.size() -1) {
				ans += "		\"" + list.get(i) +"\"\n"; 
			}else {
				ans += "		\"" + list.get(i) +"\",\n"; 
			}
			
		}
		ans += "	]\n" + "}";
		//String builder
		
		return ans;
	}
}
