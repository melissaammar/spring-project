package com.example.springgradle.dto.response.user;

import com.example.springgradle.models.User;

/*   Added by Melissa
 *   This class is used in order to return the user created/edited in the response
 */

public class UserResponse {

    private int id;
    private String username;

    public UserResponse(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
