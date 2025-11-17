package lab7.lab.service;

import lab7.lab.dto.PostDto;
import lab7.lab.entity.Post;
import lab7.lab.entity.User;
import lab7.lab.mapper.PostMapper;
import lab7.lab.repository.PostRepository;
import lab7.lab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    public List<PostDto> getAll() {
        return postMapper.toDtoList(postRepository.findAll());
    }

    public PostDto getById(Long id) {
        return postRepository.findById(id)
                .map(postMapper::toDto)
                .orElse(null);
    }

    public PostDto create(PostDto dto) {
        Post entity = postMapper.toEntity(dto);
        User author = userRepository.findById(dto.getUserId()).orElseThrow();
        entity.setAuthor(author);
        Post saved = postRepository.save(entity);
        return postMapper.toDto(saved);
    }

    public PostDto update(Long id, PostDto dto) {
        return postRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(dto.getTitle());
                    existing.setContent(dto.getContent());
                    if (dto.getUserId() != null) {
                        User author = userRepository.findById(dto.getUserId()).orElseThrow();
                        existing.setAuthor(author);
                    }
                    Post saved = postRepository.save(existing);
                    return postMapper.toDto(saved);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
