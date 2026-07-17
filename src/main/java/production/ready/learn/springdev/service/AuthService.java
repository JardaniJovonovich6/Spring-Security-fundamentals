package production.ready.learn.springdev.service;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import production.ready.learn.springdev.dto.LoginResponsedto;
import production.ready.learn.springdev.dto.Logindto;
import production.ready.learn.springdev.entity.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final SessionService sessionService;

    public LoginResponsedto login(Logindto logindto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmail() , logindto.getPassword()));

        User user = (User) authentication.getPrincipal();

        String refreshToken = jwtService.generateRefreshToken(user);
        String accessToken = jwtService.generateAccessToken(user);

        sessionService.generateNewSession(user , refreshToken);

        return new LoginResponsedto(user.getId() , accessToken , refreshToken);

    }


    public LoginResponsedto refreshtoken(String refreshtoken) {

        Long userid = jwtService.getUserIdFromToken(refreshtoken);
        User user = userService.getUserById(userid);

        String accesstoken = jwtService.generateAccessToken(user);

        return new LoginResponsedto(userid , accesstoken , refreshtoken);

    }



}
