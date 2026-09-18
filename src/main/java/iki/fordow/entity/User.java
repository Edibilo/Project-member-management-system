package iki.fordow.entity;

import iki.fordow.enumerator.GenderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Username cant be Null")
    private String username;
    @Email
    private String email;
    @NotNull(message = "Message cant be Null")
    private String password;
    @Enumerated(EnumType.STRING)
    private GenderStatus status;
    @ManyToOne
    private Role role;
    @PrePersist
    void prePersist(){
        if(status==null){
            status=GenderStatus.MALE;
        }
    }

    public User(){

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public GenderStatus getStatus() {
        return status;
    }

    public void setStatus(GenderStatus status) {
        this.status = status;
    }

    public User(Long id, String username, String email, String password, GenderStatus status, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
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
}
