package it.project.server.models.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ModelToPojo implements ModelToPojoI {
	@Override
	public it.project.server.pojo.User convertUser(it.project.server.model.User model) {
		it.project.server.pojo.User pojo = new it.project.server.pojo.User();
		pojo.setId(model.getId());
		pojo.setFirstName(model.getFirstName());
		pojo.setLastName(model.getLastName());
		pojo.setPassword(model.getPassword());
		pojo.setSex(model.getSex());
		pojo.setDob(model.getDob().getTime());
		pojo.setTelephone(model.getTelephone());
		pojo.setUsername(model.getUsername());

		if (model.getPrivilege() != null)
			pojo.setPrivilege(convertPrivilege(model.getPrivilege()));

		if (model.getAddresses() != null)
			pojo.setAddresses(convertAddresses(model.getAddresses()));

		if (model.getSkills() != null)
			pojo.setSkills(convertSkills(model.getSkills()));

		return pojo;
	}

	@Override
	public it.project.server.pojo.User convertAuthUser(it.project.server.model.AuthUser model) {
		it.project.server.pojo.User pojo = new it.project.server.pojo.User();

		pojo.setPassword(model.getPassword());
		pojo.setUsername(model.getUsername());

		if (model.getPrivilege() != null) {
			pojo.setPrivilege(convertPrivilege(model.getPrivilege()));
		}

		return pojo;
	}

	@Override
	public List<it.project.server.pojo.User> convertUsers(List<it.project.server.model.User> model) {
		List<it.project.server.pojo.User> pojo = new ArrayList<it.project.server.pojo.User>();

		if (model != null) {
			model.stream().forEach(item -> pojo.add(convertUser(item)));
		}
		return pojo;
	}

	@Override
	public it.project.server.pojo.Privilege convertPrivilege(it.project.server.model.Privilege model) {
		it.project.server.pojo.Privilege pojo = new it.project.server.pojo.Privilege();
		pojo.setId(model.getId());
		pojo.setLevel(model.getLevel());
		pojo.setDescription(model.getDescription());

		return pojo;
	}

	@Override
	public it.project.server.pojo.Address convertAddress(it.project.server.model.Address model) {
		it.project.server.pojo.Address pojo = new it.project.server.pojo.Address();

		pojo.setId(model.getId());
		pojo.setStreet(model.getStreet());
		pojo.setNumber(model.getNumber());
		pojo.setCAP(model.getCAP());
		pojo.setResidence(model.getResidence());
		pojo.setUserId(model.getUserId());

		return pojo;
	}
	@Override
	public it.project.server.pojo.Skill convertSkill(it.project.server.model.Skill model) {
		it.project.server.pojo.Skill pojo = new it.project.server.pojo.Skill();
		
		pojo.setId(model.getId());
		pojo.setSkill(model.getSkill());
		pojo.setValue(model.getValue());
		
		return pojo;
	}

	@Override
	public List<it.project.server.pojo.Address> convertAddresses(List<it.project.server.model.Address> model) {
		List<it.project.server.pojo.Address> pojo = new ArrayList<it.project.server.pojo.Address>();
		if (model != null) {
			model.stream().forEach(item -> pojo.add(convertAddress(item)));
		}
		return pojo;
	}
	
	@Override
	public List<it.project.server.pojo.Skill> convertSkills(List<it.project.server.model.Skill> model) {
		List<it.project.server.pojo.Skill> pojo = new ArrayList<it.project.server.pojo.Skill>();
		if (model != null) {
			model.stream().forEach(item -> pojo.add(convertSkill(item)));
		}
		return pojo;
	}

}
