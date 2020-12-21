package com.jlheidemann.goldenraspberryaward.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jean.heidemann
 */
@Entity
@Table(name = "PRODUCER")
public class Producer implements Serializable {
    @Id    
    @GeneratedValue
    @Column(name = "PRODUCER_ID")
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

    public Producer() {
    }

    public Producer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\"id\": \"" + this.id + "\", \"name\" = \"" + this.name + "\"}";
    }
    
    
}
