package com.example.springgradle.dto.response.role;

/*   Added by Melissa
 *   This class is used as a response for Role Editing
 */

public class RoleEditResponse {

    private String status;
    private String message;
    private RoleResponse role;

    public RoleEditResponse () { super(); }

    public RoleEditResponse(String status, String message, RoleResponse role) {
        this.status = status;
        this.message = message;
        this.role = role;
    }

    public RoleEditResponse(String status, String message) {
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

    public RoleResponse getRole() {
        return role;
    }

    public void setRole(RoleResponse role) {
        this.role = role;
    }
}
