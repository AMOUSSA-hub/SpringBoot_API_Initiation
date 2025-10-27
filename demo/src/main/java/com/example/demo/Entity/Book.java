package com.example.demo.Entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
//import java.util.Date;


@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(unique = true,nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Writer author;


    private Date publicationDate;


    protected Book(){}

    public Book(String title, Writer author, Date publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;

    }

    // Getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Writer getAuthor() { return author; }
    public void setAuthor(Writer author) { this.author = author; }

    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) { this.publicationDate = publicationDate; }


}