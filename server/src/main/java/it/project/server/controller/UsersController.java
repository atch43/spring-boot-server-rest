package it.project.server.controller;
import io.swagger.annotations.ApiResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import it.project.server.model.Page;
import it.project.server.model.SimpleUser;
import it.project.server.pojo.Skill;
import it.project.server.model.User;
import it.project.server.model.UserSearch;
import it.project.server.models.converters.ModelToPojoI;
import it.project.server.models.converters.PojoToModelI;
import it.project.server.pojo.Address;
import it.project.server.pojo.Log;
import it.project.server.pojo.Pagination;
import it.project.server.privileges.enums.Privileges;
import it.project.server.privileges.enums.Sex;
import it.project.server.security.CustomTokenHandler;
import it.project.server.security.SecurityConstants;
import it.project.server.security.SecurityUtils;
import it.project.server.services.LogService;
import it.project.server.services.UserService;
import it.project.server.services.Utils;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/users")
@Scope("request")
public class UsersController {

	@Autowired
	ModelToPojoI modelToPojo;

	@Autowired
	PojoToModelI pojoToModel;

	@Autowired
	CustomTokenHandler customTokenHandler;

	@Autowired
	UserService userService;

	@Autowired
	LogService logService;
	
	@Autowired
	Utils utils;

	@Autowired
	SecurityUtils securityUtils;

	@ApiOperation(value = "Fetch users by keyword", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved users", response = User.class, responseContainer = "List"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Something went wrong")
	})
	@GetMapping(value = "/page/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> GetUsersByKeyword(@PathVariable("page") Integer page,
			@RequestParam(name = "searchAll", required = false) String searchAll,
			@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "nominativo", required = false) String nominativo,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "sort", required = false) String sort) {

		//@formatter:off
		logService.addLog(
				new Log("Trying getUsersByKeyword(page="+page+")"
						, ""
						, System.currentTimeMillis()));
		//@formatter:on

		if (page < 0)
			page = -page;

		User authenticated = securityUtils.getCurrentUser();
		if (authenticated == null || authenticated.getPrivilege().getLevel() != Privileges.ADMIN.getLevel()) {
			//@formatter:off
			logService.addLog(
					new Log("Access forbidden: getUsersByKeyword"
							, authenticated!=null? authenticated.getUsername(): "Unauthenticated"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	
		List<it.project.server.pojo.User> list = new ArrayList<it.project.server.pojo.User>();

		try {
			Pagination p = null;
			Integer nRows = 0;
			if (searchAll != null && !searchAll.isEmpty()) {
				nRows = userService.getUsersCountByKeyword(searchAll);
				p = new Pagination(utils.perPage, nRows, page);
				list = userService.getUsersByKeyword(p.getRowBounds(), searchAll);
			} else if (sortBy != null) {
				UserSearch search = new UserSearch(id, username, nominativo, sortBy, sort);
				nRows = userService.getUsersCountByField(search);
				p = new Pagination(utils.perPage, nRows, page);
				list = userService.getUsersByField(p.getRowBounds(), search);
			} else {
				nRows = userService.getUsersCount();
				p = new Pagination(utils.perPage, nRows, page);
				list = userService.getBasicUsers(p.getRowBounds());
			}

			List<User> model = pojoToModel.convertUsers(list);

			Page<User> res = new Page<User>(page, nRows, utils.perPage, model);

			return new ResponseEntity<Page<User>>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: getUsersByKeyword"
							, e.getMessage()
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Fetch a single user by username", response = User.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved user", response = User.class),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Something went wrong")	
	})
	ResponseEntity<?> getUser(@PathVariable("username") String username) {

		if (username == null || username == "") {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: getUser"
							, "username cannot be empty"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		User authenticated = securityUtils.getCurrentUser();

		if (authenticated == null || (authenticated.getPrivilege().getLevel() != Privileges.ADMIN.getLevel()
				&& !authenticated.getUsername().contentEquals(username))) {
			//@formatter:off
			logService.addLog(
					new Log("Access forbidden: getUser"
							, authenticated!=null? authenticated.getUsername(): "Unauthenticated"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		it.project.server.pojo.User user = new it.project.server.pojo.User();
		List<Address> addresses = new ArrayList<Address>();

		try {
			user = userService.getUserByUsername(username);
			addresses = userService.getUserAddressesById(user.getId());

			user.setAddresses(addresses);

			User res = new User();
			res = pojoToModel.convertUser(user);
			return new ResponseEntity<User>(res, HttpStatus.OK);
		} catch (Exception e) {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: getUser"
							, e.getMessage()
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Fetch all usernames", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved usernames", response = SimpleUser.class, responseContainer = "List"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Something went wrong")
	})
	@GetMapping(value = "/usernames", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getUsernames() {

		User authenticated = securityUtils.getCurrentUser();

		if (authenticated == null || (authenticated.getPrivilege().getLevel() != Privileges.ADMIN.getLevel())) {
			//@formatter:off
			logService.addLog(
					new Log("Access forbidden: getUsernames"
							, authenticated!=null? authenticated.getUsername(): "Unauthenticated"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		List<SimpleUser> users = new ArrayList<SimpleUser>();

		try {
			users = pojoToModel.convertToSimpleUsers(userService.getUsernames());

			return new ResponseEntity<List<SimpleUser>>(users, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Update user", response = Integer.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated user"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Something went wrong")
	})
	@PostMapping(value = "/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody User modelUser) {

		if (username == null || username == "") {
			//@formatter:off
			logService.addLog(
					new Log("Access forbidden: getUser"
							, "username cannot be empty"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			User user = securityUtils.getCurrentUser();
			if (user == null) {
				//@formatter:off
				logService.addLog(
						new Log("Access forbidden: getUser"
								, "Unauthenticated"
								, System.currentTimeMillis()));
				//@formatter:on
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}

			it.project.server.pojo.User pojoUser;
			if (!user.getUsername().contentEquals(username)) {
				if (user.getPrivilege().getLevel() == Privileges.ADMIN.getLevel()) {
					pojoUser = userService.getUserByUsername(username);
				} else {
					//@formatter:off
					logService.addLog(
							new Log("Access forbidden: getUsernames"
									, user!=null? user.getUsername(): "Unauthenticated"
									, System.currentTimeMillis()));
					//@formatter:on
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}
			} else {
				pojoUser = userService.getUserByUsername(user.getUsername());
			}

			if (modelUser.getSex() != null && !Sex.contains(modelUser.getSex().getCode())) {
				//@formatter:off
				logService.addLog(
						new Log("Internal server error: updateUser"
								, "Invalid value for field sex"
								, System.currentTimeMillis()));
				//@formatter:on
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				if (!modelUser.getTelephone().matches(SecurityConstants.PHONE_REGEX)) {
					//@formatter:off
					logService.addLog(
							new Log("Internal server error: updateUser"
									, "Invalid value for field telephone"
									, System.currentTimeMillis()));
					//@formatter:on
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					modelUser.setTelephone(modelUser.getTelephone().replaceAll("\\s+", ""));
				}
			}

			userService.updateUser(pojoUser, modelUser);

			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}

		catch (Exception e) {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: updateUser"
							, e.getMessage()
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Fetch all user addresses", response = Integer.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved addresses", response = Address.class, responseContainer = "List"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Something went wrong")
	})
	@GetMapping(value = "{username}/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getUserAddresses(@PathVariable("username") String username) {

		if (username == null || username == "") {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: getUserAddresses"
							, "username cannot be empty"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		User authenticated = securityUtils.getCurrentUser();
		if (authenticated == null) {
			//@formatter:off
			logService.addLog(
					new Log("Access forbidden: getUserAddresses"
							,  "Unauthenticated"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		if (authenticated.getPrivilege().getLevel() != Privileges.ADMIN.getLevel()
				&& !authenticated.getUsername().contentEquals(username)) {
			//@formatter:off
			logService.addLog(
					new Log("Access forbidden: getUserAddresses"
							, authenticated!=null? authenticated.getUsername(): "Unauthenticated"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		List<Address> addresses = new ArrayList<Address>();

		try {
			addresses = userService.getUserAddressesByUsername(username);

			List<it.project.server.model.Address> res = pojoToModel.convertAddresses(addresses);

			return new ResponseEntity<List<it.project.server.model.Address>>(res, HttpStatus.OK);
		} catch (Exception e) {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: getUserAddresses"
							, e.getMessage()
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Fetch all user skills", response = Skill.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved skills", response = Skill.class, responseContainer = "List"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Something went wrong")
	})
	@GetMapping(value = "{username}/skills", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getUserSkills(@PathVariable("username") String username) {

		if (username == null || username == "") {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: getUserSkills"
							, "username cannot be empty"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		User authenticated = securityUtils.getCurrentUser();
		if (authenticated == null) {
			//@formatter:off
			logService.addLog(
					new Log("Access forbidden: getUserSkills"
							, "Unauthenticated"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		if (authenticated.getPrivilege().getLevel() != Privileges.ADMIN.getLevel()
				&& !authenticated.getUsername().contentEquals(username)) {
			//@formatter:off
			logService.addLog(
					new Log("Access forbidden: getUserSkills"
							, authenticated!=null? authenticated.getUsername(): "Unauthenticated"
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		List<Skill> skills = new ArrayList<Skill>();

		try {
			skills = userService.getUserSkillsByUsername(username);

			List<it.project.server.model.Skill> res = pojoToModel.convertSkills(skills);

			return new ResponseEntity<List<it.project.server.model.Skill>>(res, HttpStatus.OK);
		} catch (Exception e) {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: getUserSkills"
							, e.getMessage()
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Change user residence address", response = Boolean.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated residence address"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Something went wrong")
	})
	@PostMapping(value = "{username}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> setResidence(@PathVariable("username") String username, @RequestBody Integer id) {
		try {
			User user = securityUtils.getCurrentUser();

			if (username == null || id == null)
			{
				//@formatter:off
				logService.addLog(
						new Log("Internal server error: setResidence"
								,"username cannot be null"
								, System.currentTimeMillis()));
				//@formatter:on
				return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (user == null)
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);

			Integer user_id;

			if (!user.getUsername().contentEquals(username)) {
				if (user.getPrivilege().getLevel() != Privileges.ADMIN.getLevel()) {
					user_id = userService.getUserByUsername(username).getId();
				} else {
					//@formatter:off
					logService.addLog(
							new Log("Access forbidden: setResidence"
									, user!=null? user.getUsername(): "Unauthenticated"
									, System.currentTimeMillis()));
					//@formatter:on
					return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
				}
			} else {
				user_id = user.getId();
			}

			if (user_id != null) {
				userService.removeResidence(user_id);
				userService.setResidence(user_id, id);
			}
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch (Exception e) {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: setResidence"
							, e.getMessage()
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<Boolean>(false,  HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
