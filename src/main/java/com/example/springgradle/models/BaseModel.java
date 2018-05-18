package com.example.springgradle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/*   Added by Melissa
 *   This class in order to dynamically add the Created_at and Updated_at Timestamps in all 3 models
 */

public class BaseModel implements Serializable {

    @CreationTimestamp
    @Column(name = "created_at")
    @JsonIgnore
    protected Timestamp created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonIgnore
    protected Timestamp updated_at;

    public BaseModel() {
        super();
    }

    public BaseModel(Timestamp created_at, Timestamp updated_at) {
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

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
