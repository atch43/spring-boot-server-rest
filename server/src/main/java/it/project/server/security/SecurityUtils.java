package it.project.server.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.project.server.model.User;

@Service
public class SecurityUtils {

	public String EncodePassword(String rawPassword) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(rawPassword.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User getCurrentUser() {
		if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal().toString() != "anonymousUser") {
			try {
				if (SecurityContextHolder.getContext().getAuthentication() != null
						&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
					User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
					return user;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
