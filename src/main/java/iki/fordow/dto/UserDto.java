package iki.fordow.dto;

import iki.fordow.enumerator.GenderStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserDto {
    private Long id;
    @NotNull(message = "Username cant be Null")
    private String username;
    @Email
    private String email;
    @NotNull(message = "Message cant be Null")
    private String password;
    private GenderStatus status;
    private Long roleId;
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserDto(Long id, String username, String email, String password, GenderStatus status, Long roleId, String roleName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public GenderStatus getStatus() {
        return status;
    }

    public void setStatus(GenderStatus status) {
        this.status = status;
    }

    public UserDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
