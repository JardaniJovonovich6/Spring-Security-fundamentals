package production.ready.learn.springdev.dto;


import lombok.Data;

@Data
public class Postdto {
    Long postid;
    private String title;
    private String description;

}
