package com.example.springgradle.dto.response.user;

/*   Added by Melissa
 *   This class is used as a response for User Editing
 */

public class UserEditResponse {

    private String status;
    private String message;
    private UserResponse user;

    public UserEditResponse() { super(); }

    public UserEditResponse(String status, String message, UserResponse user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public UserEditResponse(String status, String message) {
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

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
