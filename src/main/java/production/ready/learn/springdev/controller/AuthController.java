package production.ready.learn.springdev.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import production.ready.learn.springdev.dto.Logindto;
import production.ready.learn.springdev.dto.SignUpdto;
import production.ready.learn.springdev.dto.Userdto;
import production.ready.learn.springdev.service.AuthService;
import production.ready.learn.springdev.service.UserService;

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
    public ResponseEntity<String> signup(@RequestBody Logindto logindto , HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse){

        String token = authService.login(logindto);

        Cookie cookie = new Cookie("token" , token);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(token);
    }




}
