package com.example.springgradle.models;

import com.example.springgradle.dto.request.role.RoleCreateRequest;
import com.example.springgradle.dto.request.role.RoleEditRequest;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/*   Added by Melissa
 *   This class is used to define the Role Model/Entity
 */

@Entity
@Table(name = "roles")
public class Role extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "display_name")
    private String display_name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    @JoinTable(name="role_permission",
            joinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id",
                    referencedColumnName = "permission_id"))
    private List<Permission> permissions;

    public Role() {
        super();
    }

    public Role(String name, String display_name, List<Permission> permissions, Timestamp created_at, Timestamp updated_at) {
        this.name = name;
        this.display_name = display_name;
        this.permissions = permissions;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Role(RoleCreateRequest roleCreateRequest) {
        this.name = roleCreateRequest.getName();
        this.display_name = roleCreateRequest.getDisplay_name();
    }

    public Role(RoleEditRequest roleEditRequest) {
        this.id = roleEditRequest.getId();
        this.name = roleEditRequest.getName();
        this.display_name = roleEditRequest.getDisplay_name();
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public List<Permission> getPermissions() { return permissions; }

    public void setPermissions(List<Permission> permissions) { this.permissions = permissions; }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
