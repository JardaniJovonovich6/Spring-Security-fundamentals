package production.ready.learn.springdev.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import production.ready.learn.springdev.config.Appconfig;
import production.ready.learn.springdev.dto.Postdto;
import production.ready.learn.springdev.entity.PostEntity;
import production.ready.learn.springdev.entity.User;
import production.ready.learn.springdev.exceptions.ResourceNotFoundException;
import production.ready.learn.springdev.repository.PostRepository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Postdto> getallposts() {
        return postRepository.findAll().
                stream().
                map(postEntity -> modelMapper.map(postEntity , Postdto.class)).
                toList();
    }

    @Override
    public Postdto createposts(Postdto inputdto) {
        PostEntity postEntity = modelMapper.map(inputdto , PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity) , Postdto.class);
    }

    @Override
    public Postdto getpostbyid(Long id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        log.info("User info - > :  {} ", user);

        PostEntity postEntity = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resouce with the ID was not found " + id));
        return modelMapper.map(postEntity , Postdto.class);
    }

    @Override
    public Postdto updatepostbyid(Long id, Postdto postdto) {
        PostEntity newpostentity = postRepository.save(modelMapper.map(postdto , PostEntity.class));

        return modelMapper.map(newpostentity , Postdto.class);
    }
}
