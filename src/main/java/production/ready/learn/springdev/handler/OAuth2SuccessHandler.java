package production.ready.learn.springdev.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import production.ready.learn.springdev.entity.User;
import production.ready.learn.springdev.service.JwtService;
import production.ready.learn.springdev.service.UserService;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = token.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        User user = userService.getuserbyemail(email);

        if(user == null){
            User newuser = User.builder()
                    .name(oAuth2User.getName())
                    .email(email)
                    .build();

            System.out.println( "craeted new User : " + userService.save(newuser));
        }

        String accesstoken = jwtService.generateAccessToken(user);
        String refreshtoken = jwtService.generateRefreshToken(user);


        Cookie cookie = new Cookie("refreshtoken" , refreshtoken);
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        response.addCookie(cookie);

        String frontendurl = "http://localhost:8080/home.html?token=" + accesstoken;

        getRedirectStrategy().sendRedirect(request , response , frontendurl);




    }



}
