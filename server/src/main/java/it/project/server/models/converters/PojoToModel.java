package it.project.server.models.converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PojoToModel implements PojoToModelI {

	@Override
	public it.project.server.model.User convertUser(it.project.server.pojo.User pojo) {
		it.project.server.model.User model = new it.project.server.model.User();

		if (pojo.getDob() != null)
			model.setDob(new Date(pojo.getDob()));

		model.setId(pojo.getId());
		model.setFirstName(pojo.getFirstName());
		model.setLastName(pojo.getLastName());
		model.setPassword(pojo.getPassword());

		if (pojo.getSex() != null)
			model.setSex(pojo.getSex());

		model.setTelephone(pojo.getTelephone());
		model.setUsername(pojo.getUsername());

		if (pojo.getPrivilege() != null)
			model.setPrivilege(convertPrivilege(pojo.getPrivilege()));

		if (pojo.getAddresses() != null)
			model.setAddresses(convertAddresses(pojo.getAddresses()));

		if (pojo.getSkills() != null)
			model.setSkills(convertSkills(pojo.getSkills()));

		
		model.setAge(pojo.getAge());

		return model;
	}

	@Override
	public it.project.server.model.SimpleUser convertToSimpleUser(it.project.server.pojo.User pojo) {
		it.project.server.model.SimpleUser model = new it.project.server.model.SimpleUser();

		model.setId(pojo.getId());
		model.setFirstName(pojo.getFirstName());
		model.setLastName(pojo.getLastName());
		model.setUsername(pojo.getUsername());

		return model;
	}

	@Override
	public List<it.project.server.model.SimpleUser> convertToSimpleUsers(List<it.project.server.pojo.User> pojo) {
		List<it.project.server.model.SimpleUser> model = new ArrayList<it.project.server.model.SimpleUser>();

		if (pojo != null) {
			pojo.stream().forEach(item -> model.add(convertToSimpleUser(item)));
		}
		return model;
	}

	@Override
	public List<it.project.server.model.User> convertUsers(List<it.project.server.pojo.User> pojo) {
		List<it.project.server.model.User> model = new ArrayList<it.project.server.model.User>();

		if (pojo != null) {
			pojo.stream().forEach(item -> model.add(convertUser(item)));
		}
		return model;
	}

	@Override
	public it.project.server.model.Privilege convertPrivilege(it.project.server.pojo.Privilege pojo) {
		it.project.server.model.Privilege model = new it.project.server.model.Privilege();
		model.setId(pojo.getId());
		model.setLevel(pojo.getLevel());
		model.setDescription(pojo.getDescription());

		return model;
	}

	@Override
	public it.project.server.model.Address convertAddress(it.project.server.pojo.Address pojo) {
		it.project.server.model.Address model = new it.project.server.model.Address();

		model.setId(pojo.getId());
		model.setStreet(pojo.getStreet());
		model.setNumber(pojo.getNumber());
		model.setCAP(pojo.getCAP());
		model.setResidence(pojo.getResidence());
		model.setUserId(pojo.getUserId());

		return model;
	}

	@Override
	public List<it.project.server.model.Address> convertAddresses(List<it.project.server.pojo.Address> pojo) {
		List<it.project.server.model.Address> model = new ArrayList<it.project.server.model.Address>();
		if (pojo != null) {
			pojo.stream().forEach(item -> model.add(convertAddress(item)));
		}
		return model;
	}

	@Override
	public it.project.server.model.Skill convertSkill(it.project.server.pojo.Skill pojo) {
		it.project.server.model.Skill model = new it.project.server.model.Skill();

		model.setId(pojo.getId());
		model.setSkill(pojo.getSkill());
		model.setValue(pojo.getValue());

		return model;
	}

	@Override
	public List<it.project.server.model.Skill> convertSkills(List<it.project.server.pojo.Skill> pojo) {
		List<it.project.server.model.Skill> model = new ArrayList<it.project.server.model.Skill>();
		if (pojo != null) {
			pojo.stream().forEach(item -> model.add(convertSkill(item)));
		}
		return model;
	}

}
