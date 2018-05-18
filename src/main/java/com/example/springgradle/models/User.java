package com.example.springgradle.models;

import com.example.springgradle.dto.request.user.UserCreateRequest;
import com.example.springgradle.dto.request.user.UserEditRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/*   Added by Melissa
 *   This class is used to define the User Model/Entity
 */

@Entity
@Table(name = "users")
public class User extends BaseModel {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(4);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "role_id"))
    private List<Role> roles;

    public User() {
        super();
    }

    public User (String name, String email, String username, String password, List<Role> roles, Timestamp created_at, Timestamp updated_at) {
        this.name = name;
        this.email = email;
        this.username = username;
        setPassword(password);
        this.roles = roles;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User (UserCreateRequest user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        setPassword(user.getPassword());
    }

    public User (UserEditRequest user)
    {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        setPassword(user.getPassword());
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public List<Role> getRoles() { return roles; }

    public void setRoles(List<Role> roles) { this.roles = roles; }
}