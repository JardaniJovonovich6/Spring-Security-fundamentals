package production.ready.learn.springdev.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class Postdto {
    Long postid;
    private String title;
    private String description;

}
