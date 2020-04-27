package it.project.server.security;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import it.project.server.model.User;
import it.project.server.models.converters.PojoToModelI;
import it.project.server.services.IUserService;

@Service("customTokenHandler")
public class CustomTokenHandler {


	@Autowired
	IUserService userBLM;

	@Autowired
	PojoToModelI pojoToModel;
	
	public CustomTokenHandler() {
    }

    public it.project.server.model.User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("username").toString();

        it.project.server.model.User res = pojoToModel.convertUser(userBLM.getUserByUsername(username));
        return res;
    }

    public String createTokenForUser(User user) {
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("privilegeId", user.getPrivilege().getId());
    		map.put("username", user.getUsername());
    		
        return Jwts.builder()
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
                .compact();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return "";
    	}
    }
    
    public boolean validateToken(String token) {
    	 try {
//    	       Jws<Claims> jwt = 
    	    		   Jwts.parser() 
    	                .setSigningKey(SecurityConstants.JWT_SECRET)
    	                .parseClaimsJws(token);
    	       return true;

    	    } catch (SignatureException e) {
    	        e.printStackTrace();
    	        return false;
    	     }
    }
    
    public Integer getPrivilegeFromToken(String token) {
   	 try {
   	       String privilegeId = Jwts.parser() 
   	                .setSigningKey(SecurityConstants.JWT_SECRET)
   	                .parseClaimsJws(token)
   	                .getBody()
   	                .get("privilegeId").toString();
   	       return Integer.parseInt(privilegeId);

   	    } catch (SignatureException e) {
   	        e.printStackTrace();
   	        return 0;
   	     }
   }
}