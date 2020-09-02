package com.insta.sns.web.dto;

import java.util.List;

import com.insta.sns.domain.image.Image;
import com.insta.sns.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRespDto {
	private boolean pageHost;
	private User user;
	private List<Image> images;
	private int followerCount;
	private int followingCount;
	private int imageCount;
}
