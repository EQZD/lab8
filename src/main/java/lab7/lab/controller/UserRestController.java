package lab7.lab.controller;

import lab7.lab.dto.RoleDto;
import lab7.lab.dto.UserDto;
import lab7.lab.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDto dto = service.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        UserDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id,
                                          @RequestBody UserDto dto) {
        UserDto updated = service.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> addRole(@PathVariable Long userId,
                                           @PathVariable Long roleId) {
        UserDto updated = service.addRoleToUser(userId, roleId);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{userId}/roles")
    public ResponseEntity<UserDto> setRoles(@PathVariable Long userId,
                                            @RequestBody List<RoleDto> roles) {
        UserDto updated = service.setRolesToUser(userId, roles);
        return ResponseEntity.ok(updated);
    }
}
