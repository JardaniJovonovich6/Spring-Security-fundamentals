package production.ready.learn.springdev.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import production.ready.learn.springdev.entity.enums.PERMISSIONS;
import production.ready.learn.springdev.entity.enums.Roles;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static production.ready.learn.springdev.entity.enums.PERMISSIONS.*;
import static production.ready.learn.springdev.entity.enums.Roles.*;


//FIXED NO ISSUES HERE
public class PermissionMapping{

    private static final Map<Roles , Set<PERMISSIONS>> map = Map.of(

            USER , Set.of(POST_VIEW , USER_VIEW) ,

            ADMIN , Set.of(USER_VIEW , USER_CREATE , USER_DELETE , USER_UPDATE , POST_VIEW , POST_CREATE , POST_DELETE , POST_UPDATE),

            CREATOR , Set.of(USER_VIEW , POST_VIEW , POST_CREATE) ,

            EDITOR , Set.of(USER_VIEW , USER_UPDATE , POST_VIEW , POST_UPDATE),

            WATCHER , Set.of(POST_VIEW , USER_VIEW , POST_DELETE , USER_DELETE)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesFromRoles(Roles role){
        return map.get(role).stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());
    }

}
