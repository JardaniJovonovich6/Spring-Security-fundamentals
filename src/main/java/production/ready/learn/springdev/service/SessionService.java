package production.ready.learn.springdev.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import production.ready.learn.springdev.entity.SessionEntity;
import production.ready.learn.springdev.entity.User;
import production.ready.learn.springdev.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user , String refreshToken){

        log.info("generating new Session");
        log.info("finding user by it's User");
        List<SessionEntity> sessionEntityList = sessionRepository.findByUser(user);
        if(sessionEntityList.size() == SESSION_LIMIT){

            log.info("session limit reached ............");

           sessionEntityList.sort(Comparator.comparing(SessionEntity::getLastUsedAt));

           SessionEntity leastrecentlyusedsession =  sessionEntityList.getFirst();

           log.info("Session of the user which is least recently used is Deleted");

           sessionRepository.delete(leastrecentlyusedsession);

           log.info("leastrecentlyusedsession for the User -> Deletion completed .........");
        }

        log.info("Building new Session.........");
        SessionEntity newsession  = SessionEntity.builder()
                .refreshtoken(refreshToken)
                .user(user)
                .build();

        log.info("New session building is done >>>  Now saving the session");
        sessionRepository.save(newsession);
        log.info("New Session saved Confirmed");

    }

    public void validateSession(String refreshToken){

        log.info("Validating the Session...................");
        log.info("findding session using refreshtoken");

        SessionEntity sessionEntity = sessionRepository.findbyRefreshToken(refreshToken);

        if(sessionEntity == null){
            throw new SessionAuthenticationException("Session for the Token : " + refreshToken + " was not found........");
        }else {

            log.info("changing or validating the LastUsedAt of the session to the current time............");
            sessionEntity.setLastUsedAt(LocalDateTime.now());
            log.info("Session with changed time is done and saved");
            sessionRepository.save(sessionEntity);
            log.info("Session is saved used validateSession method in SessionService");
        }


    }


}
