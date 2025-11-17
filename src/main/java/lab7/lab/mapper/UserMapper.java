package lab7.lab.mapper;

import lab7.lab.dto.UserDto;
import lab7.lab.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {PostMapper.class, RoleMapper.class}
)
public interface UserMapper {

    UserDto toDto(User user);

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true) // заполним отдельно
    @Mapping(target = "roles", ignore = true) // заполним отдельно
    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> users);
}
