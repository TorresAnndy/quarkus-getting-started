package org.acme.micro;

import java.util.Set;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity @Table(name = "movies")
public class Movie extends PanacheEntity{

    public String title;
    public Set review;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Set getReview() {
        return review;
    }
    public void setReview(Set review) {
        this.review = review;
    }

    
    
}
