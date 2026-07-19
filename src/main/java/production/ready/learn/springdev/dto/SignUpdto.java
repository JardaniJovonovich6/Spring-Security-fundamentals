package production.ready.learn.springdev.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import production.ready.learn.springdev.entity.enums.Roles;

import java.util.Set;

@Data
@AllArgsConstructor
public class SignUpdto {

    private String name;
    private String email;
    private String password;
    private Set<Roles> roles;

}
