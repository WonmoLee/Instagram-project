package com.insta.sns.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.insta.sns.config.auth.LoginUserAnnotation;
import com.insta.sns.config.auth.dto.LoginUser;
import com.insta.sns.service.UserService;
import com.insta.sns.web.dto.UserProfileRespDto;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, @LoginUserAnnotation LoginUser loginUser, Model model) {
		UserProfileRespDto userProfileRespDto = userService.회원프로필(id, loginUser);
		model.addAttribute("respDto", userProfileRespDto);
		return "user/profile";
	}
}
