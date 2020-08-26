package com.insta.sns.config.oauth;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.insta.sns.config.auth.PrincipalDetails;
import com.insta.sns.domain.user.User;
import com.insta.sns.domain.user.UserRepository;
import com.insta.sns.domain.user.UserRole;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService{

	private static final Logger log = LoggerFactory.getLogger(PrincipalOAuth2UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Value("${test.secret}")
	private String testSecret;

	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("1"+userRequest.toString());
		log.info("2"+userRequest.getAccessToken().getTokenValue());
		log.info("3"+userRequest.getAccessToken().getExpiresAt().toString());
		log.info("4"+userRequest.getClientRegistration().toString());
		
		//super.loadUser()는 OAuth서버에 내 서버정보와 AccessToken을 던져서 회원 프로필 정보를 OAuth2User타입으로 받아온다.
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("OAuth2User : " + oAuth2User.getAttributes());
		
		User userEntity = oauthLoginOrJoin(oAuth2User);
		
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}
	
	private User oauthLoginOrJoin(OAuth2User oAuth2User) {
		String provider = "facebook";
		String providerId = oAuth2User.getAttribute("id");
		String username = provider+"_"+providerId;
		String password = bCryptPasswordEncoder.encode(testSecret);
		String email = oAuth2User.getAttribute("email");
		User userEntity = userRepository.findByUsername(username).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
					//회원가입
					User user = User.builder()
							.username(username)
							.password(password)
							.email(email)
							.role(UserRole.USER)
							.provider(provider)
							.providerId(oAuth2User.getAttribute(providerId))
							.build();
					return userRepository.save(user);
			}
		});
		
		return userEntity;
	}
}
