package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity @Table(name = "review")
public class Review extends PanacheEntity{

    public Integer rating;
    public String comment;
    
}
