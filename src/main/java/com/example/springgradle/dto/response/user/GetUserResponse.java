package com.example.springgradle.dto.response.user;

import com.example.springgradle.models.User;

/*   Added by Melissa
 *   This class is used as a response for Get User Request
 */

public class GetUserResponse {

    private String status;
    private String message;
    private User user;

    public GetUserResponse() { super(); }

    public GetUserResponse(String status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public GetUserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
