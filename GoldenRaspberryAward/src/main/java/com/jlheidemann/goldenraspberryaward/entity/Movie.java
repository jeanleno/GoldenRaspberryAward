package com.jlheidemann.goldenraspberryaward.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jean.heidemann
 */
@Entity
@Table(name = "MOVIE")
public class Movie implements Serializable {
    @Id    
    @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Long id;
    
    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "YEAR")
    private int year;
    
    @ManyToMany(cascade=CascadeType.MERGE)  
    @JoinTable(name="MOVIE_STUDIO", joinColumns=@JoinColumn(name="MOVIE_ID"), inverseJoinColumns=@JoinColumn(name="STUDIO_ID"))  
    private List<Studio> studios = new ArrayList<>();
    
    @ManyToMany(cascade=CascadeType.MERGE)  
    @JoinTable(name="MOVIE_PRODUCER", joinColumns=@JoinColumn(name="MOVIE_ID"), inverseJoinColumns=@JoinColumn(name="PRODUCER_ID"))
    private List<Producer> producers;
    private boolean winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Studio> getStudios() {
        return studios;
    }

    public void setStudios(List<Studio> studios) {
        this.studios = studios;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Movie() {
    }

    public Movie(String title, int year, List<Studio> studios, List<Producer> producers, boolean winner) {
        this.title = title;
        this.year = year;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }
    
    @Override
    public String toString() {
        String str =  "{\"id\": \"" + this.id + "\""
                + ", \"title\": \"" + this.title + "\""
                + ", \"winner\": \"" + this.winner + "\""
                + ", \"studios\": [";
                
        if (this.studios != null && this.studios.size() > 0) {
            boolean firstStudio = true;
            for (Studio studio : this.studios) {
                if (!firstStudio) {
                    str += ", ";
                }
                str += studio.toString();
                firstStudio = false;
            }
        }
        str += "], \"producers\": [";
        if (this.producers != null && this.producers.size() > 0) {
            boolean firstProducer = true;
            for (Producer producer : this.producers) {
                if (!firstProducer) {
                    str += ", ";
                }
                str += producer.toString();
                firstProducer = false;
            }
        }
                
        str += "]}";
        return str;
    }
}
