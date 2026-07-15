package production.ready.learn.springdev.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import production.ready.learn.springdev.dto.Logindto;
import production.ready.learn.springdev.entity.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(Logindto logindto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmail() , logindto.getPassword()));

        User user = (User) authentication.getPrincipal();

        return jwtService.generateToken(user);

    }



}
