package it.project.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.project.server.mapper.query.LogQuery; 
import it.project.server.pojo.Log;

@Service
public class LogService implements ILogService{
	
	@Autowired
	LogQuery logQuery;
	
	@Override
	public List<Log> getLogs() {
		List<Log> res = logQuery.getLogs();
		return res;
	}
	
	@Override
	public Boolean addLog(Log log) {
		Boolean res = logQuery.insertLog(log);
		return res;
	}
	
 
	 

	
	
}
