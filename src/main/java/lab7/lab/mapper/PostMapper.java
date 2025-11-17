package lab7.lab.mapper;

import lab7.lab.dto.PostDto;
import lab7.lab.entity.Post;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "author.id", target = "userId")
    PostDto toDto(Post post);

    @InheritInverseConfiguration
    @Mapping(target = "author", ignore = true) // user подставим в сервисе
    Post toEntity(PostDto dto);

    List<PostDto> toDtoList(List<Post> posts);
}
