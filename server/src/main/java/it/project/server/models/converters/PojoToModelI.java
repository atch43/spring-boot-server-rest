package it.project.server.models.converters;

import java.util.List;

import it.project.server.model.Address;
import it.project.server.model.SimpleUser;
import it.project.server.model.Skill;
import it.project.server.model.User;


public interface PojoToModelI {
	public it.project.server.model.User convertUser(it.project.server.pojo.User pojo);

	public List<User> convertUsers(List<it.project.server.pojo.User> pojo);

	public it.project.server.model.Privilege convertPrivilege(it.project.server.pojo.Privilege pojo);

	public Address convertAddress(it.project.server.pojo.Address pojo);

	public List<Address> convertAddresses(List<it.project.server.pojo.Address> pojo);

	List<Skill> convertSkills(List<it.project.server.pojo.Skill> pojo);

	Skill convertSkill(it.project.server.pojo.Skill pojo);

	SimpleUser convertToSimpleUser(it.project.server.pojo.User pojo);
	
	List<SimpleUser> convertToSimpleUsers(List<it.project.server.pojo.User> pojo);

}
