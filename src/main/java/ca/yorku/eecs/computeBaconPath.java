package ca.yorku.eecs;

import java.util.ArrayList;

public class computeBaconPath {
	public computeBaconPath() {
		
	}
	
	public boolean checkActorExists(String actorId) {
		Neo4jcomputeBaconPath dbcbaconpath = new Neo4jcomputeBaconPath();
		boolean checkActor = dbcbaconpath.checkActor(actorId);
		
		if(checkActor) {
			return true;
		}else {
			return false;
		}
	}
	
	public String computeBaconPathResult(String actorId ,String kevin){
		String ans = "{\n" + "	\"BaconPath\": [\n";
		if(actorId.equals(kevin)) {
			ans += "		\"" + kevin + "\"\n";
			//when input is kevin himself
		}else {
			Neo4jcomputeBaconPath dbcbaconpath = new Neo4jcomputeBaconPath();
			ArrayList<String> list = dbcbaconpath.computeBaconPath(actorId , kevin);
			for(int i = 0; i < list.size(); i++) {
				if(i == list.size()-1) {
					ans += "		" + list.get(i)+ "\n";
				}else {
					ans += "		" + list.get(i)+ ",\n";
				}
					//string builder
			}
		}
		
		ans += "	]\n" + "}";
		return ans;
	}
}
