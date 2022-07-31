package ca.yorku.eecs;


public class computeBaconNumber {
	public computeBaconNumber() {
		
	}
	
	public boolean checkActorExists(String actorId) {
		Neo4jcomputeBaconPath dbcbaconpath = new Neo4jcomputeBaconPath();
		boolean checkActor = dbcbaconpath.checkActor(actorId);
		//checks if actor exists, return type of dbcbaconpath.checkActor is boolean
		if(checkActor) {
			return true;
		}else {
			return false;
		}
	}
	
	public String computeBaconNumberResult(String actorId , String kevin) {
		
		String ans = "{\n" + "	\"BaconNumber\": ";
		if(actorId.equals(kevin)) {
			ans += "0\n";
			//when input is kevin himself
		}else {
			Neo4jcomputeBaconNumber cbn = new Neo4jcomputeBaconNumber();
			String result = cbn.computeBaconNumber(actorId, kevin);
			ans += result + "\n";
		}
		ans += "}";
		return ans;
	}

}
