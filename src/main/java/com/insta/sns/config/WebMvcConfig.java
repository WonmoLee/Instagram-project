package com.insta.sns.config;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

<<<<<<< HEAD
=======
import com.insta.sns.config.auth.LoginUserAnnotation;
>>>>>>> 6ce0676d695307c2bf7f54537e029d687aa873fb
import com.insta.sns.config.auth.dto.LoginUser;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
<<<<<<< HEAD
public class WebMvcConfig implements WebMvcConfigurer{
	
=======
public class WebMvcConfig implements WebMvcConfigurer {

>>>>>>> 6ce0676d695307c2bf7f54537e029d687aa873fb
	private final HttpSession httpSession;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
<<<<<<< HEAD

		resolvers.add(new HandlerMethodArgumentResolver() {

			// 1. supportsParameter() 에서 true가 리턴되면!!
			@Override
			public boolean supportsParameter(MethodParameter parameter) {
				boolean isLoginUserAnnotation = 
						parameter.getParameterAnnotation(LoginUserAnnotation.class) != null;
				boolean isUserClass = 
						LoginUser.class.equals(parameter.getParameterType());
				return isLoginUserAnnotation && isUserClass;
			}
			
			// 2. 세션을 만들어서 @LoginUserAnnotation 에 주입해준다.
=======
		resolvers.add(new HandlerMethodArgumentResolver() {

			// 1. supportsParameter() 에서 true가 리턴되면
			@Override
			public boolean supportsParameter(MethodParameter parameter) {
				boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUserAnnotation.class) != null;
				boolean isUserClass = LoginUser.class.equals(parameter.getParameterType());
				return isLoginUserAnnotation && isUserClass;
			}
			
			// 2.세션을 만들어서 @LoginUserAnnotation에 주입해준다.
>>>>>>> 6ce0676d695307c2bf7f54537e029d687aa873fb
			@Override
			public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
					NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
				return httpSession.getAttribute("loginUser");
			}
		});
	}
	
	// 이미지 경로 찾기를 위해 추가 시작
<<<<<<< HEAD
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
	
		registry
			.addResourceHandler("/upload/**")
			.addResourceLocations("file:///" + uploadFolder)
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
	}
	// 이미지 경로 찾기를 위해 추가 끝
}
=======
		@Value("${file.path}")
		private String uploadFolder;

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			WebMvcConfigurer.super.addResourceHandlers(registry);

			registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + uploadFolder).setCachePeriod(3600)
					.resourceChain(true).addResolver(new PathResourceResolver());
		}
		// 이미지 경로 찾기를 위해 추가 끝
}
>>>>>>> 6ce0676d695307c2bf7f54537e029d687aa873fb
