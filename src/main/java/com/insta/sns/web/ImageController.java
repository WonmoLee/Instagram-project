package com.insta.sns.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.insta.sns.config.auth.LoginUserAnnotation;
import com.insta.sns.config.auth.dto.LoginUser;
import com.insta.sns.service.ImageService;
import com.insta.sns.web.dto.ImageReqDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ImageController {
	
	private final ImageService imageService;
	
	@GetMapping({"", "/", "/image/feed"})
	public String feed(@LoginUserAnnotation LoginUser loginUser) {
		return "image/feed";
	}
	
	@GetMapping("/image/uploadForm")
	public String imageUploadForm() {
		return "image/image-upload";
	}

	@PostMapping("/image")
	public String imageUpload(@LoginUserAnnotation LoginUser loginUser, ImageReqDto imageReqDto) {

		imageService.사진업로드(imageReqDto, loginUser.getId());

		return "redirect:/";
	}
	
}
