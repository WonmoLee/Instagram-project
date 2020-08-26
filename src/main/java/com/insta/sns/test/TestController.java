package com.insta.sns.test;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insta.sns.config.auth.PrincipalDetails;
import com.insta.sns.config.handler.ex.MyUsernameNotFoundException;

@Controller
public class TestController {
	
	@GetMapping("/test/facebook")
	public @ResponseBody String facebook(Authentication authentication) {
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println("authentication : " + authentication.getPrincipal());
		System.out.println("authentication : " + principalDetails.getUser());
		
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
		System.out.println("authentication : " + oAuth2User.getAttributes());
		return "facebook 로그인완료";
	}
	
	@GetMapping("/test/facebook2")
	public @ResponseBody String facebook(@AuthenticationPrincipal PrincipalDetails principal) {
		
		System.out.println(principal.getUsername());
		return "facebook 로그인완료2";
	}

	@GetMapping("/test/login")
	public String test1() {
		return "auth/loginForm";
	}

	@GetMapping("/test/join")
	public String test2() {
		return "auth/joinForm";
	}

	@GetMapping("/test/following")
	public String test3() {
		return "follow/following";
	}

	@GetMapping("/test/explore")
	public String test4() {
		return "image/explore";
	}

	@GetMapping("/test/feed")
	public String test5() {
		return "image/feed";
	}

	@GetMapping("/test/imageUpload")
	public String test6() {
		return "image/image-upload";
	}

	@GetMapping("/test/profileEdit")
	public String test7() {
		return "user/profile-edit";
	}

	@GetMapping("/test/profile")
	public String test8() {
		return "user/profile";
	}
	
	@GetMapping("/test/username/{username}")
	public @ResponseBody String test9(@PathVariable String username) {
		if(!username.equals("ssar")) {
			throw new MyUsernameNotFoundException("유저네임 못찾음");
		}

		return "username test";
	}
}

