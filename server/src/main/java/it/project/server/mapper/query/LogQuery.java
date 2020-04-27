package it.project.server.mapper.query;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import it.project.server.pojo.Log;

@Mapper
public interface LogQuery {
	
	@Update("INSERT INTO log (message, extra, createdAt) "
			+ "VALUES (#{message}, #{extra}, #{createdAt})")
	Boolean insertLog(Log log);
	
	@Update("SELECT id, message, extra, createdAt FROM log"
			+ " ORDER BY createdAt DESC")
	List<Log> getLogs();

}
