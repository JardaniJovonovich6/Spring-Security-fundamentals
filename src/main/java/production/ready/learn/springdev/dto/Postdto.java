package production.ready.learn.springdev.dto;


import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Postdto {
    Long postid;
    private String title;
    private String description;

}
