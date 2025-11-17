package lab7.lab.mapper;

import lab7.lab.dto.RoleDto;
import lab7.lab.entity.Role;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);

    Role toEntity(RoleDto dto);

    Set<RoleDto> toDtoSet(Set<Role> roles);

    List<RoleDto> toDtoList(List<Role> roles);
}
