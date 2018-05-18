package com.example.springgradle.dto.request.role;

/*   Added by Melissa
 *   This class is used as a Model to add a new Role and extract it from the payload
 */
public class RoleCreateRequest {

    private String name;
    private String display_name;

    public RoleCreateRequest () { super(); }

    public RoleCreateRequest(int id, String name, String display_name) {
        this.name = name;
        this.display_name = display_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
