package com.insta.sns.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insta.sns.domain.image.Image;
import com.insta.sns.domain.image.ImageRepository;
import com.insta.sns.domain.tag.Tag;
import com.insta.sns.domain.tag.TagRepository;
import com.insta.sns.domain.user.User;
import com.insta.sns.domain.user.UserRepository;
import com.insta.sns.util.Utils;
import com.insta.sns.web.dto.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	private final TagRepository tagRepository;
	private final UserRepository userRepository;

	@Value("${file.path}")
	private String uploadFolder;

	@Transactional
	public void 사진업로드(ImageReqDto imageReqDto, int userId) {
		User userEntity = userRepository.findById(userId).
				orElseThrow(null);

		UUID uuid = UUID.randomUUID();
		String imageFilename = 
				uuid+"_"+imageReqDto.getFile().getOriginalFilename();
		Path imageFilepath = Paths.get(uploadFolder+imageFilename);
		try {
			Files.write(imageFilepath, imageReqDto.getFile().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 1. Image 저장
		Image image = imageReqDto.toEntity(imageFilename, userEntity);
		Image imageEntity = imageRepository.save(image);

		// 2. Tag 저장
		List<String> tagNames = Utils.tagParse(imageReqDto.getTags());
		for (String name : tagNames) {
			Tag tag = Tag.builder()
					.image(imageEntity)
					.name(name)
					.build();
			tagRepository.save(tag);
		}
	}
}
