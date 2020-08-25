package com.insta.sns.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insta.sns.domain.image.Image;
import com.insta.sns.domain.image.ImageRepository;
import com.insta.sns.domain.tag.Tag;
import com.insta.sns.domain.tag.TagRepository;
import com.insta.sns.domain.user.User;
import com.insta.sns.domain.user.UserRepository;
import com.insta.sns.domain.user.UserRole;

@RestController
public class TestApiController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private TagRepository tagRepository;

	@GetMapping("/test/api/join")
	public User join() {
		User user = User.builder()
				.name("이원모")
				.password("1234")
				.phone("01022223333")
				.bio("안녕하십니까 이원모입니다.")
				.role(UserRole.USER)
				.build();
		User userEntity = userRepository.save(user);
		return userEntity;
	}

	@GetMapping("/test/api/image")
	public String image() {
		User userEntity = userRepository.findById(1).get();

		Image image = Image.builder()
				.location("다낭")
				.caption("설명")
				.user(userEntity)
				.build();

		Image imageEntity = imageRepository.save(image);

		List<Tag> tags = new ArrayList<>();
		Tag tag1 = Tag.builder()
				.name("#다낭")
				.image(imageEntity)
				.build();
		Tag tag2 = Tag.builder()
				.name("#여행")
				.image(imageEntity)
				.build();
		tags.add(tag1);
		tags.add(tag2);

		tagRepository.saveAll(tags);

		return "Image Insert 잘됨";
	}

	@GetMapping("/test/api/image/list")
	public List<Image> imageList(){
		return imageRepository.findAll();
	}

	@GetMapping("/test/api/tag/list")
	public List<Tag> tagList(){
		return tagRepository.findAll();
	}
}
