package it.project.server.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import it.project.server.pojo.Skill;
import it.project.server.model.UserSearch;
import it.project.server.pojo.Address;
import it.project.server.pojo.User;


public interface IUserService {
	User checkUser(User user);

	List<User> getBasicUsers(RowBounds rowBounds);

	User getUserByUsername(String username);
	
	Integer getUsersCount();
	

	List<User> getUsersByKeyword(RowBounds rowBounds, String search);
	Integer getUsersCountByKeyword(String search);

	List<User> getUsersByField(RowBounds rowBounds, UserSearch search);
	Integer getUsersCountByField(UserSearch search);

	List<Address> getUserAddressesById(Integer user_id);
	
	List<Address> getUserAddressesByUsername(String username);
	
	Integer setResidence(Integer user_id, Integer id);
	
	Integer removeResidence(Integer user_id);

	Integer updateUser(User oldUser, it.project.server.model.User updateUser);

	List<Skill> getUserSkillsById(Integer user_id);

	List<Skill> getUserSkillsByUsername(String username);

	List<User> getUsernames();
	




}
