package production.ready.learn.springdev.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Userdto {

    private Long id;
    private String email;
    private String name;

}
