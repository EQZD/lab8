package lab7.lab.service;

import lab7.lab.dto.RoleDto;
import lab7.lab.entity.Role;
import lab7.lab.mapper.RoleMapper;
import lab7.lab.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository,
                       RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleDto> getAll() {
        return roleMapper.toDtoList(roleRepository.findAll());
    }

    public RoleDto getById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDto)
                .orElse(null);
    }

    public RoleDto create(RoleDto dto) {
        Role saved = roleRepository.save(roleMapper.toEntity(dto));
        return roleMapper.toDto(saved);
    }

    public RoleDto update(Long id, RoleDto dto) {
        return roleRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    Role saved = roleRepository.save(existing);
                    return roleMapper.toDto(saved);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
