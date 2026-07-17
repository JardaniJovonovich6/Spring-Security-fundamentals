package production.ready.learn.springdev.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponsedto {

    private Long id;
    private String accesstoken;
    private String refreshtoken;

}
