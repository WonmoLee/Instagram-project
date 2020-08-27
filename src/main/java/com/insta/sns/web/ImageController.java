package com.insta.sns.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.insta.sns.config.auth.LoginUserAnnotation;
import com.insta.sns.config.auth.dto.LoginUser;

@Controller
public class ImageController {
	
	@GetMapping({"", "/", "/image/feed"})
	public String feed(@LoginUserAnnotation LoginUser loginUser) {
		return "image/feed";
	}
	
}
