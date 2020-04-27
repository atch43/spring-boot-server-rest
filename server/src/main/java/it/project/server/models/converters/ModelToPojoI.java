package it.project.server.models.converters;

import java.util.List;

import it.project.server.model.AuthUser;
import it.project.server.pojo.Address;
import it.project.server.pojo.Skill;
import it.project.server.pojo.User;

public interface ModelToPojoI {
	public it.project.server.pojo.User convertUser(it.project.server.model.User pojo);

	public List<User> convertUsers(List<it.project.server.model.User> pojo);

	public it.project.server.pojo.Privilege convertPrivilege(it.project.server.model.Privilege pojo);

	public Address convertAddress(it.project.server.model.Address pojo);

	public List<Address> convertAddresses(List<it.project.server.model.Address> pojo);

	List<Skill> convertSkills(List<it.project.server.model.Skill> model);

	Skill convertSkill(it.project.server.model.Skill model);

	User convertAuthUser(AuthUser model);


}
