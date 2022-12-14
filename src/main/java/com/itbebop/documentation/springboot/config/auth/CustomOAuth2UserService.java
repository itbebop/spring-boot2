package com.itbebop.documentation.springboot.config.auth;

import com.itbebop.documentation.springboot.config.auth.dto.OAuthAttributes;
import com.itbebop.documentation.springboot.config.auth.dto.SessionUser;
import com.itbebop.documentation.springboot.domain.user.User;
import com.itbebop.documentation.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        /*
        registrationId
         - 현재 로그인 진행 중인 서비스를 구분하는 코드
         - 지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시에 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용함
         */
        // oauth2 로그인 진행 시 키가 되는 필드값
        String usrNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        /*
        usrNameAttributeName
         - OAuth2 로그인 진행 시 키가 되는 필드값을 이야기함. Primary Key와 같은 의미
         - 구글의 경우 기본적으로 코드를 지원(구글의 기본 코드는 "sub")하지만, 네이버 카카오 등은 기본으로 지원하지 않음
         - 이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용함
         */
        // OAuthAttributes: attribute를 담을 클래스 (개발자가 생성)
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, usrNameAttributeName, oAuth2User.getAttributes());
        /*
        OAuthAttributes
         - OAuth2UsreService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
         - 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용함
         */
        User user = saveOrUpdate(attributes);

        // SessionUser: 세션에 사용자 정보를 저장하기 위한 DTO 클래스 (개발자가 생성)
        httpSession.setAttribute("user", new SessionUser(user));
        /*
        SessionUser
         - 세션에 사용자 정보를 저장하기 위한 Dto 클래스
         - User 클래스를 쓰지 않고 새로 만들어서 쓰는 이유
          > User 클래스를 그대로 사용한다면 다음과 같은 에러 발생함
           >> Failed to conver from type [java.lang.Object] to type [byte[]] for value 'com....domain.usr.User@4A43d6'
          > 위 에러는 User 클래스에 직렬화를 구현하지 않았다는 의미의 에러임
          > 그렇다고 User 클래스에 직렬화 코드를 넣어서 해결하긴 어려운 것이 User 클래스는 엔티티이고,
           엔티티 클래스에는 언제 다른 엔티티와 관계가 형성될지 모름.
          > 자식 엔티티를 갖고 있다면 직렬화 대상에 자식들까지 포함되어 성능 이슈, 부수 효과가 발생할 확률이 높으므로
           운영 및 유지보수 차원에서 직렬화 기능을 가진 세션 Dto를 추가로 만드는 것이 유리함
         */
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),attributes.getAttributes(),attributes.getNameAttributeKey());

    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(), attributes.getPicture())).orElse(attributes.toEntity());

        return userRepository.save(user);
    }


}
