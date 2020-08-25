package com.insta.sns.web.dto;

import com.insta.sns.domain.user.User;
import com.insta.sns.domain.user.UserRole;

import lombok.Data;

@Data
public class JoinReqDto {
	private String email;
	private String name;
	private String username;
	private String password;

	public User toEntity() {
		return User.builder()
				.email(email)
				.name(name)
				.username(username)
				.password(password)
				.role(UserRole.USER)
				.build();
	}
}