package lab7.lab.dto;

import java.util.List;
import java.util.Set;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private int age;

    private List<PostDto> posts;
    private Set<RoleDto> roles;

    public UserDto() {}

    public UserDto(Long id, String name, String email, int age,
                   List<PostDto> posts, Set<RoleDto> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.posts = posts;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
}
