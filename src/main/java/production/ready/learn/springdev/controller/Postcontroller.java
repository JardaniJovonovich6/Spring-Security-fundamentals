package production.ready.learn.springdev.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import production.ready.learn.springdev.dto.Postdto;
import production.ready.learn.springdev.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class Postcontroller {

    public final PostService postService;

    @GetMapping
    public List<Postdto> getallposts(){
        return postService.getallposts();

    }

    @GetMapping(path = "/{id}")
    public Postdto getpostbyid(@PathVariable Long id){
        return postService.getpostbyid(id);
    }

    @PostMapping
    public Postdto createnewpost(@RequestBody Postdto inputpost){
        return postService.createposts(inputpost);
    }

    @PutMapping(path = "/update")
    public Postdto updateposts(@RequestBody Postdto inputupdateposts ){

        return postService.updatepostbyid(inputupdateposts.getPostid() , inputupdateposts);

    }


}
