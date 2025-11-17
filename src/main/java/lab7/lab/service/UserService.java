package lab7.lab.service;

import lab7.lab.dto.UserDto;
import lab7.lab.entity.User;
import lab7.lab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public UserDto create(UserDto dto) {
        User entity = toEntity(dto);
        entity.setId(null);
        User saved = repository.save(entity);
        return toDto(saved);
    }

    public UserDto update(Long id, UserDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setEmail(dto.getEmail());
                    existing.setAge(dto.getAge());
                    User saved = repository.save(existing);
                    return toDto(saved);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public UserDto toDto(User entity) {
        if (entity == null) return null;
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        return dto;
    }

    public User toEntity(UserDto dto) {
        if (dto == null) return null;
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());
        return entity;
    }
}
