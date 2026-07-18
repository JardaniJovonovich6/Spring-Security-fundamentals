package production.ready.learn.springdev.service;

import production.ready.learn.springdev.dto.Postdto;

import java.util.List;

public interface PostService {
    List<Postdto> getallposts() ;

    Postdto createposts(Postdto inputdto);


    Postdto getpostbyid(Long id);

    Postdto updatepostbyid(Long id , Postdto postdto);
}
