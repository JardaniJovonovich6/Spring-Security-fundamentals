package production.ready.learn.springdev.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import production.ready.learn.springdev.dto.Logindto;
import production.ready.learn.springdev.dto.SignUpdto;
import production.ready.learn.springdev.dto.Userdto;
import production.ready.learn.springdev.entity.User;
import production.ready.learn.springdev.exceptions.ResourceNotFoundException;
import production.ready.learn.springdev.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new BadCredentialsException("Resouce was nto found with the username : "+ username));
    }

    public User getUserById(Long userid){
        return modelMapper.map(userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("Resource with the ID " + userid + " was not found..........") ) , User.class);
    }

    public User getuserbyemail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public Userdto signup(SignUpdto signUpdto) {

        Optional<User> optionalbox = userRepository.findByEmail(signUpdto.getEmail());

        if(optionalbox.isPresent()){
            throw new BadCredentialsException("User with the email is present already" + signUpdto.getEmail());
        }

        User creatinguser = modelMapper.map(signUpdto , User.class);  // Mapping signupsto to user , then saving it to repository

        creatinguser.setPassword(passwordEncoder.encode(creatinguser.getPassword()));

        User saveduser = userRepository.save(creatinguser);
        return modelMapper.map(creatinguser , Userdto.class);

    }

    public User save(User newuser) {

        return userRepository.save(newuser);

    }
}

