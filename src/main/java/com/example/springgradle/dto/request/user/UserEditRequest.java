package com.example.springgradle.dto.request.user;

/*   Added by Melissa
 *   This class is used as a Model to edit an existing User and extract it from the payload
 */

public class UserEditRequest {

    private int id;
    private String name;
    private String email;
    private String username;
    private String password;

    public UserEditRequest () { super(); }

    public UserEditRequest(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public void setPassword(String password) { this.password = password; }
}
