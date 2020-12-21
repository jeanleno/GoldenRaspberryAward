package com.jlheidemann.goldenraspberryaward.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jean.heidemann
 */
@Entity
@Table(name = "STUDIO")
public class Studio implements Serializable {
    @Id    
    @GeneratedValue
    @Column(name = "STUDIO_ID")
    private Long id;
    
    @Column(name = "NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Studio() {
    }

    public Studio(String name) {
        this.name = name;
    }
    
    public Studio(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "{\"id\": \"" + this.id + "\", \"name\" = \"" + this.name + "\"}";
    }
}
