package it.project.server.services;

import java.util.List;

import it.project.server.pojo.Log;

public interface ILogService {

	Boolean addLog(Log log);

	List<Log> getLogs();

}
