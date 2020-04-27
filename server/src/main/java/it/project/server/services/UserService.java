package it.project.server.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.project.server.mapper.query.UserQuery;
import it.project.server.pojo.Skill;
import it.project.server.model.UserSearch;
import it.project.server.pojo.Address;
import it.project.server.pojo.User;

@Service
public class UserService implements IUserService{
	
	@Autowired
	UserQuery userQuery;
	
	@Override
	public User checkUser(User user) {
		User res = userQuery.checkUser(user);
		return res;
	}
	
	@Override
	public List<User> getBasicUsers(RowBounds rowBounds) {
		List<User> res = userQuery.getBasicUsers(rowBounds);
		return res;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User res = userQuery.getUserByUsername(username);
		return res;
	}
	
	@Override
	public Integer getUsersCount() {
		return userQuery.getUsersCount();
	}
	
	// SEARCH
	@Override
	public Integer getUsersCountByField(UserSearch search) {
		return userQuery.getUsersCountByField(search);
	}
	
	@Override
	public List<User> getUsersByField(RowBounds rowBounds, UserSearch search) {
		return userQuery.getUsersByField(rowBounds, search);
	}

	@Override
	public Integer getUsersCountByKeyword(String search) {
		return userQuery.getUsersCountByKeyword(search);
	}

	@Override
	public List<User> getUsersByKeyword(RowBounds rowBounds, String search) {
		return userQuery.getUsersByKeyword(rowBounds, search);
	}
	
	@Override
	public List<User> getUsernames() {
		return userQuery.getUsernames();
	}
	
	
	// ADDRESSES
	@Override
	public List<Address> getUserAddressesById(Integer user_id){
		return userQuery.getUserAddressesById(user_id);
	}
	
	@Override
	public List<Address> getUserAddressesByUsername(String username){
		return userQuery.getUserAddressesByUsername(username);
	}
	
	@Override
	public Integer setResidence(Integer user_id, Integer id) {
		return userQuery.setResidence(user_id, id);
	}

	@Override
	public Integer removeResidence(Integer user_id) {
		return userQuery.removeResidence(user_id);
	}

	@Override
	public Integer updateUser(User user, it.project.server.model.User updateUser) {
		user.setFirstName(updateUser.getFirstName());
		user.setLastName(updateUser.getLastName());
		user.setSex(updateUser.getSex());
		user.setTelephone(updateUser.getTelephone());
		user.setDob(updateUser.getDob().getTime());
		
		return userQuery.updateUser(user);
	}
	
	
	// SKILLS
	@Override
	public List<Skill> getUserSkillsById(Integer user_id) {
		return userQuery.getUserSkillsById(user_id);
	}
	@Override
	public List<Skill> getUserSkillsByUsername(String username) {
		return userQuery.getUserSkillsByUsername(username);
	}

	 

	
	
}
