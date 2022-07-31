package ca.yorku.eecs;

public class addPersonalRelation {
	
	public addPersonalRelation() {
		
	}
	
	public int  addPersonalRel(String actor1Id , String actor2Id) {
		Neo4jaddPersonalRelation r = new Neo4jaddPersonalRelation();
		boolean both_exist = r.checkActorExsist1(actor1Id) && r.checkActorExsist2(actor2Id);
		boolean relation_exists = r.checkRelationExsist(actor1Id, actor2Id);
		if(both_exist) {
			if(!(relation_exists)) {
				r.addPersonalRelation(actor1Id, actor2Id);
				//success addition of relation
				return 1;
			}else {
				return 2;
				//relation already exists.
			}
		}else {
			return 3;
		}
	}
}
