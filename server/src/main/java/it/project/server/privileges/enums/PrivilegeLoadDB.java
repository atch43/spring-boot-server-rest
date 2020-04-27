package it.project.server.privileges.enums;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.project.server.mapper.query.UserQuery;

@Service
public class PrivilegeLoadDB {
	
	@Autowired
	private UserQuery _query;
	
	public static UserQuery query;
	
	@PostConstruct
	void init(){
		query = _query;
	}
	
	public static UserQuery getQuery() {
		return query;
	}
	
}
