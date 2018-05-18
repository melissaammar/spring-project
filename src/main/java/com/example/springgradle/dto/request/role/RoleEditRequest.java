package com.example.springgradle.dto.request.role;

/*   Added by Melissa
 *   This class is used as a Model to edit an existing Role and extract it from the payload
 */

public class RoleEditRequest {

    private int id;
    private String name;
    private String display_name;

    public RoleEditRequest () { super(); }

    public RoleEditRequest(int id, String name, String display_name) {
        this.id = id;
        this.name = name;
        this.display_name = display_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
