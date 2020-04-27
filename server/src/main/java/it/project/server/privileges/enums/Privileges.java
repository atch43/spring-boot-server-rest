package it.project.server.privileges.enums;

import java.util.HashMap;

import it.project.server.pojo.Privilege;
import it.project.server.privileges.PrivilegeInterface;

public enum Privileges implements PrivilegeInterface{
	USER, ADMIN;

	private HashMap<Privileges, Privilege> privileges = new HashMap<>();
	
	@Override
	public int getLevel() {
		return privileges.compute(this, (k,v)-> { 
			if(v!=null)
			return v;
			else {
				return getPrivilegeOnDB(this.name());
			}
		}).getLevel();
	}


	@Override
	public String getDescription() {
		return privileges.compute(this, (k,v)-> { 
			if(v!=null)
			return v;
			else {
				return getPrivilegeOnDB(this.name());
			}
		}).getDescription();
	}

	private static Privilege getPrivilegeOnDB(String privilege) {
		return PrivilegeLoadDB.getQuery().getPrivilege(privilege);
	}
}
