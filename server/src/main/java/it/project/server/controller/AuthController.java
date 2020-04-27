package it.project.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.project.server.model.AuthUser;
import it.project.server.model.User;
import it.project.server.models.converters.ModelToPojoI;
import it.project.server.models.converters.PojoToModelI;
import it.project.server.pojo.Log;
import it.project.server.security.CustomTokenHandler;
import it.project.server.security.SecurityUtils;
import it.project.server.services.LogService;
import it.project.server.services.UserService;


@RestController
@RequestMapping("/auth")
@Scope("request")
public class AuthController {

	@Autowired
	ModelToPojoI modelToPojo;

	@Autowired
	PojoToModelI pojoToModel;

	@Autowired
	CustomTokenHandler customTokenHandler;

	@Autowired
	UserService userService;

	@Autowired
	SecurityUtils securityUtils;
	
	@Autowired
	LogService logService;

	public ModelToPojoI getModelToPojo() {
		return modelToPojo;
	}

	public void setModelToPojo(ModelToPojoI modelToPojo) {
		this.modelToPojo = modelToPojo;
	}

	public PojoToModelI getPojoToModel() {
		return pojoToModel;
	}

	public void setPojoToModel(PojoToModelI pojoToModel) {
		this.pojoToModel = pojoToModel;
	}
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> Login(@RequestBody AuthUser user) {
		
		String token = "";
		User fullUser = null;
		
		try {
			if (user == null || user.getUsername() == null || user.getPassword() == null) {
				//@formatter:off
				logService.addLog(
						new Log("Internal server error: Login"
								, user!=null?user.getUsername()+":"+user.getPassword():""
								, System.currentTimeMillis()));
				//@formatter:on
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			user.setPassword(securityUtils.EncodePassword(user.getPassword()));
			it.project.server.pojo.User pojo = userService.checkUser(modelToPojo.convertAuthUser(user));
			
			if (pojo != null) {
				fullUser = pojoToModel.convertUser(pojo);
				token = customTokenHandler.createTokenForUser(fullUser);
			} else {
				//@formatter:off
				logService.addLog(
						new Log("Access forbidden: getUserSkills"
								, "Unauthorized"
								, System.currentTimeMillis()));
				//@formatter:on
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			AuthUser u = new AuthUser(fullUser.getUsername(), token, fullUser.getPrivilege());

			return new ResponseEntity<AuthUser>(u, HttpStatus.OK);
		} catch (Exception e) {
			//@formatter:off
			logService.addLog(
					new Log("Internal server error: Login"
							, e.getMessage()
							, System.currentTimeMillis()));
			//@formatter:on
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/logout")
	ResponseEntity<?> logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
}
