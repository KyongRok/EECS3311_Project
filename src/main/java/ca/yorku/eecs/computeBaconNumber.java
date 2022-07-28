package ca.yorku.eecs;


public class computeBaconNumber {
	public computeBaconNumber() {
		
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
	
	public String computeBaconNumberResult(String actorId , String kevin) {
		
		String ans = "{\n" + "	\"BaconPath\": ";
		if(actorId.equals(kevin)) {
			ans += "0\n";
		}else {
			Neo4jcomputeBaconNumber cbn = new Neo4jcomputeBaconNumber();
			String result = cbn.computeBaconNumber(actorId, kevin);
			ans += result + "\n";
		}
		ans += "}";
		return ans;
	}

}
