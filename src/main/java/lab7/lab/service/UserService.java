package lab7.lab.service;

import lab7.lab.dto.RoleDto;
import lab7.lab.dto.UserDto;
import lab7.lab.entity.Role;
import lab7.lab.entity.User;
import lab7.lab.mapper.UserMapper;
import lab7.lab.repository.RoleRepository;
import lab7.lab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDto getById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    public UserDto create(UserDto dto) {
        User entity = userMapper.toEntity(dto);
        User saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    public UserDto update(Long id, UserDto dto) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setEmail(dto.getEmail());
                    existing.setAge(dto.getAge());
                    User saved = userRepository.save(existing);
                    return userMapper.toDto(saved);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow();
        Role role = roleRepository.findById(roleId).orElseThrow();
        user.getRoles().add(role);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto setRolesToUser(Long userId, List<RoleDto> roleDtos) {
        User user = userRepository.findById(userId).orElseThrow();
        Set<Role> roles = roleDtos.stream()
                .map(dto -> roleRepository.findById(dto.getId()).orElseThrow())
                .collect(Collectors.toSet());
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
