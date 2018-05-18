package com.example.springgradle.dto.response.role;

import com.example.springgradle.models.Role;

/*   Added by Melissa
 *   This class is used as a response for Get User Request
 */

public class GetRoleResponse {

    private String status;
    private String message;
    private Role role;

    public GetRoleResponse () { super(); }

    public GetRoleResponse(String status, String message, Role role) {
        this.status = status;
        this.message = message;
        this.role = role;
    }

    public GetRoleResponse(String status, String message) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
