package production.ready.learn.springdev.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpdto {

    private String name;
    private String email;
    private String password;

}
