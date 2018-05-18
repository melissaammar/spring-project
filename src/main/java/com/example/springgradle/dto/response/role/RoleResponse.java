package com.example.springgradle.dto.response.role;

/*   Added by Melissa
 *   This class is used in order to return the role created/edited in the response
 */

import com.example.springgradle.models.Role;

public class RoleResponse {

    private int id;
    private String display_name;

    public RoleResponse() { super(); }

    public RoleResponse(int id, String display_name) {
        this.id = id;
        this.display_name = display_name;
    }

    public RoleResponse (Role role) {
        this.id = role.getId();
        this.display_name = role.getDisplay_name();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
