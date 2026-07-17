package production.ready.learn.springdev.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import production.ready.learn.springdev.dto.LoginResponsedto;
import production.ready.learn.springdev.dto.Logindto;
import production.ready.learn.springdev.dto.SignUpdto;
import production.ready.learn.springdev.dto.Userdto;
import production.ready.learn.springdev.service.AuthService;
import production.ready.learn.springdev.service.UserService;
import production.ready.learn.springdev.dto.LoginResponsedto;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Userdto> signup(@RequestBody SignUpdto signUpdto){
        Userdto userdto = userService.signup(signUpdto);
        return ResponseEntity.ok(userdto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponsedto> login(@RequestBody Logindto logindto , HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse){

        LoginResponsedto loginResponsedto = authService.login(logindto);

        Cookie cookie = new Cookie("refreshtoken" , loginResponsedto.getRefreshtoken());
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(loginResponsedto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponsedto> refresh(HttpServletRequest request){
        String refreshtoken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshtoken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token not found inside the cookie ........ exception "));

        LoginResponsedto loginResponsedto = authService.refreshtoken(refreshtoken);

        return ResponseEntity.ok(loginResponsedto);


    }




}
