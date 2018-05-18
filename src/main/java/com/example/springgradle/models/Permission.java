package com.example.springgradle.models;

import javax.persistence.*;
import java.sql.Timestamp;

/*   Added by Melissa
 *   This class is used to define the Permission Model/Entity
 */

@Entity
@Table(name = "permissions")
public class Permission extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "display_name")
    private String display_name;

    public Permission() {
        super();
    }

    public Permission(String name, String display_name, Timestamp created_at, Timestamp updated_at) {
        this.name = name;
        this.display_name = display_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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
