package ca.yorku.eecs;

import java.util.ArrayList;

public class getPersonalRelation {

	public getPersonalRelation() {
		
	}
	
	public boolean checkActorExsist(String actorId) {
		Neo4jgetPersonalRelation dbGetP_relation = new Neo4jgetPersonalRelation();
		boolean actorExists = dbGetP_relation.checkActorID(actorId);
		//check if actor exists
		if((actorExists)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getP_Relation(String actorId) {
		Neo4jgetPersonalRelation dbGetP_relation = new Neo4jgetPersonalRelation();
		ArrayList<String> list = dbGetP_relation.getActor_PersonalRelation(actorId);
		String actorName = dbGetP_relation.getActor_info(actorId);
		//return type of method getActor_PersonalRelation is ArrayList<String>
		String ans = "{\n" + "\"actorId\": \"" + actorId +"\", \n" 
				+ "\"name\": \"" + actorName +"\", \n" 
				+"\"actors\": [\n";
		
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
